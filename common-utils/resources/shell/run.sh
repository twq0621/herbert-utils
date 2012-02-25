#!/bin/bash
cd /home/server_git/jact/
echo "fetch latest source code"
git pull
echo "compile jact project"
ant
if [ $? -ne  0 ];
then
        echo -e "\e[1;31m build failed! \e[0m"
        exit 1;
fi
cd build
chmod u+x start.sh
chmod u+x stop.sh
echo "shuting down jact server"
./stop.sh
echo "starting jact server"
./start.sh
#echo "archive jact project"
#tar -zcvf $PWD/ACTGame.tar.gz build/