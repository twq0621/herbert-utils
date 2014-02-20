#!/bin/bash
cd /opt/server_source/ServerSide/mobile_app
echo "cleaning svn directory...."
svn revert --depth=infinity .
echo "fetching latest source code...."
svn up
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
sh mobile_server-0.2.1/stop.sh
while [ $? -eq  0 ]
do
    echo "checking server stop...."
    sleep 3
    nc -vv -z -w 2 192.168.78.31 8653 2> /dev/null
done
echo "starting mobile server......"
cd mobile_server-0.2.1
sh start.sh
