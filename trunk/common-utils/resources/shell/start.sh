#!/bin/bash
java -server -Xms512M -Xmx1024M -XX:MaxPermSize=256m -XX:+UseParallelGC -jar $PWD/ACTGame.jar &
