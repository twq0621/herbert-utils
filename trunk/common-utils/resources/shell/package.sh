#!/bin/bash
cd /home/server_qa/jact/
rm -rf jact
echo "fetch latest source code"
git pull
echo "compile jact project"
ant
if [ $? -ne  0 ];
then
        echo -e "\e[1;31m build failed! \e[0m"
        exit 1;
fi
echo "package project"
mv build jact
tar -zcvf $PWD/ACTGame.tar.gz jact/
echo -e "\e[1;32m package success! \e[0m"