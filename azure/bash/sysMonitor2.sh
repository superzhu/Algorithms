#!/bin/bash
memUsed=`free | awk 'NR==3{print $3}'`
sReclaimable=`cat /proc/meminfo | awk 'NR==22{print $2}'`
memTotal=`free | awk 'NR==2{print $2}'`
let memDelta=$memUsed-$sReclaimable
memThreshold=`echo "scale=2;$memDelta / $memTotal"|bc`
RETENTION=7

if [ ! -n "$1" ]
then
  threshold=0.85
else
  threshold=$1
fi

if [ ! -n "$2" ]
then
  loc="./rm_performance.log"
else
  loc=$2
fi

log_rotate()
{
    LOGFILENAME=$1
    for ((i=${RETENTION}-1; i>0; i--)); do
        if [ -f ${LOGFILENAME}-day${i} ]; then
            let j=${i}+1
            mv -f ${LOGFILENAME}-day${i} ${LOGFILENAME}-day${j}
        fi
    done
    if [ -f ${LOGFILENAME} ]; then
        let j=${i}+1
        mv -f ${LOGFILENAME} ${LOGFILENAME}-day1
    fi
    touch ${LOGFILENAME}
}

#keepLogIn7days(){
#  if [ -f "$loc" ]
#  then 
#    getOldestDate 
#    nowtime=`date`
#    ot=`date -d "$oldtime" +%s`
#    nt=`date -d "$nowtime" +%s`
#    
#    del=$[(nt-ot)]
#    if [ $del -le 604800 ]  # 604800 = 7days 
#    then 
#      echo "no need to clean the oldest log "
#    else 
#      echo "remove the old log 7 days ago"
#      sline=`echo $start | awk -F":" '{print$1}'`
#      #count=`grep -i "logging start" $loc | grep -i $sdate | wc -l`
#      calEndIndex
#      grep -i "logging start" $loc | awk '{print $1}' 
#      end=`grep -n -i "logging end" $loc | head -n $index | tail -1`
#      eline=`echo $end | awk -F":" '{print$1}'` 
#      sed -i "${sline},${eline}d" $loc  
#    fi
#  else
#    #echo "no such file $loc"  
#    touch $loc
#  fi    
#}

calEndIndex(){
  for i in $(grep -i "logging start" $loc | awk '{print $1}');
  do
    t1=`date -d "${i}" +%s`
    nt=`date -d "$nowtime" +%s`

    del=$[(nt-t1)]
    if [ $del -gt 604800 ]  # 604800 = 7days
    then
       index=$(($index+1))
    else
       break
    fi
  done
}

getOldestDate(){
  times=`grep -n -i "logging start" $loc | wc -l`
  if [ $times -ne 0 ] 
  then
    start=`grep -n -i "logging start" $loc | head -n 1`  
    stime=`echo $start | awk '{print$2}'`
    sd=`echo $start | awk -F":" '{print $2}'`
    sdate=`echo $sd | awk '{print $1}'`
    oldtime=$sdate" "$stime 
  else
    echo "no logging start"
  fi
}


logCPU(){
  echo -e "\n********Start Log CPU:" `date` "******************" >> $loc
  echo "top result: `sar -u 5 3` " >> $loc
  echo -e "\n********End Log CPU:" `date` "******************" >> $loc
}

logMem(){
  echo -e "\n********Start Log Memory:" `date` "******************" >> $loc
  echo "total mem use: $memThreshold" >> $loc
  echo "/proc/meminfo: `cat /proc/meminfo` " >> $loc
  echo -e "\n********End Log Memory:" `date` "******************" >> $loc
}

logIO(){
  echo "********Start Log IO:" `date` "******************" >> $loc
  sum=0
  for i in $(ls -d /proc/*/); 
  do 
    if [ -d "${i%%/}/fd" ]; then
      fd="${i%%/}/fd"
      fdcount=`ls -lrt $fd | wc -l` 
      echo "$fd number:" "$fdcount" &>> $loc 
      sum=$(($sum+$fdcount))
      #echo "`ls -lrt $fd`" >> $loc
    else 
      echo ${i%%/} "do not have fd" &>> $loc
    fi
  done
  echo "total fd number: $sum" &>> $loc
  
  echo -e "\n\n jserver pid : `ps -ef | grep jserver | grep org.jboss.Main | awk '{print $2}'`" &>> $loc
  echo "********End Log IO:" `date` "******************" &>> $loc
}

logThreaddump(){ 
  echo -e "\n********Start Thread dump:" `date` "******************" >> $loc
  echo "service jserver threaddump: `service jserver threaddump`" >> $loc
}

logJserverThread(){
  echo `date` "log Jserver thread" >> $loc 
  echo "`ps -mp $(ps -ef | grep -i jboss | awk '{print $2}' | head -n 1) -o THREAD,tid,time,rss,size,%mem`" >> $loc
}

logNetwork(){
  echo -e "\n********Start Log Network:" `date` "******************" >> $loc
  #echo "`netstat -an | awk '/tcp/ {print $6}' | sort | uniq -c`" >> $loc
  #echo "`netstat -anp`" &>> $loc
  echo -e "connections statistics on port 8443:\n `netstat -ntu |grep ':8443' | awk ' $5 ~ /^(::ffff:|[0-9|])/ { gsub("::ffff:","",$5); print $6 "%" $5 }' | cut -d: -f1 | awk '{++S[$NF]}END{for (key in S) print key,S[key]}' | awk -F '[%]' '{print $2,$1}' | sort`" >> $loc 
  echo -e "\n\nconnections statistics on port 9443:\n `netstat -ntu |grep ':9443' | awk ' $5 ~ /^(::ffff:|[0-9|])/ { gsub("::ffff:","",$5); print $6 "%" $5 }' | cut -d: -f1 | awk '{++S[$NF]}END{for (key in S) print key,S[key]}' | awk -F '[%]' '{print $2,$1}' | sort`" >> $loc 
  
  echo `date` "log network" >> $loc
  echo -e "\n\nconnections statistics on port 443:\n `netstat -ntu |grep ':443' |awk '/^tcp/{++S[$NF]}END{for (key in S) print key,S[key]}' | sort`" >> $loc 
  echo -e "\n\nconnections statistics on port 1443:\n `netstat -ntu |grep ':1443' |awk '/^tcp/{++S[$NF]}END{for (key in S) print key,S[key]}' | sort`" >> $loc 
  echo -e "\n\nconnections statistics on port 389:\n `netstat -ntu |grep ':389' |awk '/^tcp/{++S[$NF]}END{for (key in S) print key,S[key]}' | sort`" >> $loc

  echo -e "\n********End Log Network:" `date` "******************" &>> $loc
  #if [ $con -gt 500 ]
  #then
  #  echo -e "log all 8443 connections:\n `netstat -anp | grep 8443`" >> $loc 
  #else
  #  echo "8443 connection num is ok" >> $loc 
  #fi 
}

logDisk() {
 echo -e "\n********Start Log Disk:" `date` "******************" >> $loc
 echo -e "disk status:\n `df -h`" >> $loc
 echo -e "\n********End Log Disk:" `date` "******************" >> $loc
}

########################### Main ###########################################
# Create log files
if [ ! -f $loc ]; then
    touch $loc
    echo "--------------------- Date for this log file:`date +%Y%m%d`" > $loc
else
    LOGDATE=`head -1 $loc | awk -F ":" '{print $2}'`
    if [ "`date +%Y%m%d`" != "${LOGDATE}" ]; then
        log_rotate $loc
        echo "--------------------- Date for this log file:`date +%Y%m%d`" > $loc
    fi
fi

if (($(echo "$memThreshold $threshold" | awk '{print ($1 > $2)}')))
then
   # keepLogIn7days 
   echo `date "+%Y%m%d %H:%M:%S"` "logging start. Performance is very low. Recording CPU Mem IO Threaddump network" >> $loc 
   echo  "Bad current memory used: $memThreshold ,threshold=$threshold" >> $loc
   logCPU
   # logJserverThread
   logMem
   logThreaddump
   logNetwork
   logIO
   logDisk
   echo -e "=logging end= \n\n" >> $loc
else 
   echo  "Better current memory used: $memThreshold ,threshold=$threshold" >> $loc
   logCPU
   logMem
   logNetwork
   logIO
   logDisk
fi  

exit 0


