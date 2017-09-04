#!/usr/bin/env python
import sys
DEFAULT_RACK = "/apm/default-rack"
HOST_RACK_FILE = "/pang/hadoop265/etc/hadoop/host-rack.map"
host_rack = {}
for line in open(HOST_RACK_FILE):
	#print line
	(host,rack) = line.split(",")
	host_rack[host] = rack
for host in sys.argv[1:]:
	if host in host_rack:
		print host_rack[host]
	else:
		print DEFAULT_RACK
