#!/bin/bash
kill -9 `ps -ef|grep "ACTGame.jar" | grep -v "grep"|awk '{print $2} '`
