#!/bin/bash
SERVER_SID=$1
cd /shiyue/svn_server
echo "cleaning svn directory...."
svn revert --depth=infinity .
echo "fetching latest source code...."
svn up
cd game_app
ant build
if [ $? -ne  0 ];
then
        echo -e "\e[1;31m build failed! \e[0m"
        exit 1;
fi
cd ../gameApp/
sed -i 's/\(server\.sid=\)[0-9]\+/\18/' $PWD/WEB-INF/gameservices/serverparam.properties
tar -zcvf WEB-INF.tar.gz WEB-INF/
cp WEB-INF.tar.gz /shiyue/app/server/baishe
echo "stopping resin server......"
sh /opt/resin-3.1.12/bin/httpd.sh stop
while [ $? -eq  0 ]
do
    echo "checking server stop...."
    sleep 3
    nc -vv -z -w 2 192.168.1.110 8002 2> /dev/null
done
cd /shiyue/app/server/baishe
rm -rf WEB-INF/
tar -zxvf WEB-INF.tar.gz
echo "starting resin server...."
sh /opt/resin-3.1.12/bin/httpd.sh start
