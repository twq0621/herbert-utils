#!/bin/bash
SERVER=$1
PORT=$2
nc -vv -z -w 2 $SERVER $PORT 2> /dev/null
if [ "$?" -ne 0 ]; then
	echo "Connection to $SERVER on port $PORT failed"
	exit 1
else
	echo "Connection to $SERVER on port $PORT succeeded"
	exit 0
fi
#执行方法 ./check_port_full.sh 127.0.0.1 9527