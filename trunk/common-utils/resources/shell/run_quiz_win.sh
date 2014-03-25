#!/bin/bash
PROCESS="mobile_server"
DEPLOY_DIR="/root/deploy/"
SOURCE_DIR="/root/source/ServerSide/"
echo "stop the mobile server"
MY_PID=`ps -ef|grep "${PROCESS}" | grep -v "grep"|awk '{print $2} '`
if [ ${MY_PID} ]
then
	cd $DEPLOY_DIR/mobile_server-0.2.1/
        sh ./stop.sh
        while [ -e /proc/${MY_PID} ];
        do
                echo "check ${MY_PID} exist"
                sleep 0.5;
        done
else
        echo "already closed"
fi

cd $SOURCE_DIR
echo "cleaning svn directory...."
svn revert --depth=infinity .
echo "fetching latest source code...."
svn up
cd mobile_app
svn info > svn.version
mvn -Dmaven.package.phase=true -Dmaven.antrun.skip=true clean package
if [ $? -ne  0 ];
then
        echo -e "\e[1;31m build failed! \e[0m"
        exit 1;
fi
NOW=$(date +"%Y-%m-%d %H:%M:%S")
echo "build date:$NOW" >> svn.version
cp target/mobile_server-0.2.1-bin.zip $DEPLOY_DIR
cd $DEPLOY_DIR
rm -rf mobile_server-0.2.1
unzip mobile_server-0.2.1-bin.zip
echo "starting ${PROCESS}......"
cd mobile_server-0.2.1
sh start.sh
