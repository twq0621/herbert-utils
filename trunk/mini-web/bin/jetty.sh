#!/bin/bash
cd ..
export MAVEN_OPTS="$MAVEN_OPTS -XX:MaxPermSize=128m"
mvn jetty:run -Djetty.port=8080
