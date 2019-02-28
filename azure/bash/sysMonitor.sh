#!/bin/bash

loc="/var/log/polycom/cma/performance/rm_performance.log"
THRESHOLD_8443=800
THRESHOLD_443=1000
THRESHOLD_FD=10000
ABNORMAL=false

RETENTION=7

JBOSS_PID=`ps -ef | grep jboss | grep -v grep | awk '{print $2}'`
UCS_PID=`ps \`pgrep java\` | grep ucs-engine-service | awk '{print $1}'`

log_rotate()
{
    LOGFILENAME=$1
    for ((i=${RETENTION}-1; i>0; i--)); do
        if [ -f ${LOGFILENAME}-day${i}.gz ]; then
            let j=${i}+1
            mv -f ${LOGFILENAME}-day${i}.gz ${LOGFILENAME}-day${j}.gz
        fi
    done
    if [ -f ${LOGFILENAME} ]; then
        let j=${i}+1
        mv -f ${LOGFILENAME} ${LOGFILENAME}-day1
        gzip ${LOGFILENAME}-day1
    fi
    touch ${LOGFILENAME}
}

log()
{
    echo "$1" >> $loc
}

logCPU()
{
    log "******************************** CPU Information ********************************"
##  log "`mpstat`"
    log "`top -b -n 1 | head -50`"
}

logMem()
{
    log "******************************** Mem Information ********************************"
    log "`cat /proc/meminfo`"
    log "`top -b -an 1 | head -50`"
}

logIO()
{
    log "******************************** FD Information ********************************"
    local JBOSS_FD=`ls /proc/${JBOSS_PID}/fd | wc -l`
    local UCS_FD=`ls /proc/${UCS_PID}/fd | wc -l`
    log "FD amount of JBoss process: ${JBOSS_FD}"
    log "FD amount of JBoss process: ${UCS_FD}"
    if [ ${JBOSS_FD} -gt ${THRESHOLD_FD} ]; then
        ABNORMAL=true
    fi
}

logThreaddump(){
  echo `date` "log Thread dump" >> $loc 
    local HEAD
    local TAIL
    local CMD
    service jserver threaddump
    sleep 15

    HEAD=`nl -b a /var/log/polycom/cma/log/stdout.log | tac | grep "Full thread dump OpenJDK 64-Bit Server VM " | head -1 | awk '{print $1}'`
    TAIL=`nl -b a /var/log/polycom/cma/log/stdout.log | tac | grep "class space    " | head -1 | awk '{print $1}'`
    let HEAD=${HEAD}-1
    CMD="sed -n ${HEAD},${TAIL}p /var/log/polycom/cma/log/stdout.log"

    log "`${CMD}`"
}

logJserverThread(){
  echo `date` "log Jserver thread" >> $loc 
  echo "`ps -mp $(ps -ef | grep -i jboss | awk '{print $2}' | head -n 1) -o THREAD,tid,time,rss,size,%mem`" >> $loc
}

logNetwork()
{
    log "******************************** Network Information ********************************"
    local TMP_FILE=/tmp/networkinfo-$$
    local CONNECTION_AMOUNT

    netstat -anp | grep 443 | grep -v TIME_WAIT | grep -v LISTEN > ${TMP_FILE}

    CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":8443" | wc -l`
    log "Port 8443 connections : ${CONNECTION_AMOUNT}"
    if [ ${CONNECTION_AMOUNT} -gt ${THRESHOLD_8443} ]; then
        CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":9443" | wc -l`
        log "    Port 9443 connections : ${CONNECTION_AMOUNT}"
        ABNORMAL=true
    fi


    CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":443" | wc -l`
    log "Port 443 connections : ${CONNECTION_AMOUNT}"
    if [ ${CONNECTION_AMOUNT} -gt ${THRESHOLD_443} ]; then
        CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":1443" | wc -l`
        log "    Port 1443 connections : ${CONNECTION_AMOUNT}"
        CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":2443" | wc -l`
        log "    Port 2443 connections : ${CONNECTION_AMOUNT}"
        CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":3443" | wc -l`
        log "    Port 3443 connections : ${CONNECTION_AMOUNT}"
        CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":4443" | wc -l`
        log "    Port 4443 connections : ${CONNECTION_AMOUNT}"
        CONNECTION_AMOUNT=`cat ${TMP_FILE} | awk '{print $4}' |  grep ":5443" | wc -l`
        log "    Port 5443 connections : ${CONNECTION_AMOUNT}"
        ABNORMAL=true
    fi
    rm ${TMP_FILE}
}

########################### Main ###########################################
# Create log files
if [ ! -f $loc ]; then
    touch $loc
    echo -e "--------------------- Date for this log file:`date +%Y%m%d` ---------------------\n" > $loc
else
    LOGDATE=`head -1 $loc | awk -F ":" '{print $2}' | awk -F " " '{print $1}'`
    if [ "`date +%Y%m%d`" != "${LOGDATE}" ]; then
        log_rotate $loc
        echo -e "--------------------- Date for this log file:`date +%Y%m%d` ---------------------\n" > $loc
    fi
fi


echo "####################### `date` -- Record started #######################" >> $loc 
logCPU
####logJserverThread
logMem
logNetwork
logIO
if [ "${ABNORMAL}" = "true" ]; then
    logThreaddump
fi
echo "==================================== Record End ====================================" >> $loc

exit 0

