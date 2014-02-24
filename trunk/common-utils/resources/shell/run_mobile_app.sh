#!/bin/bash
PROCESS="mobile_server"
cd /opt/server_source/ServerSide
echo "cleaning svn directory...."
svn revert --depth=infinity .
echo "fetching latest source code...."
svn up
cd mobile_app
mvn -Dmaven.package.phase=true -Dmaven.antrun.skip=true clean package
if [ $? -ne  0 ];
then
        echo -e "\e[1;31m build failed! \e[0m"
        exit 1;
fi
cp target/mobile_server-0.2.1-bin.zip /opt/deploy/
cd /opt/deploy/
rm -rf mobile_server-0.2.1
unzip mobile_server-0.2.1-bin.zip
echo "stop the mobile server"
PID=`ps -ef|grep "${PROCESS}" | grep -v "grep"|awk '{print $2} '`
sh mobile_server-0.2.1/stop.sh
while [ -e /proc/${PID} ]; do sleep 0.1; done
echo "starting ${PROCESS}......"
cd mobile_server-0.2.1
sh start.sh