#!/bin/bash
start=$(date +%s)
ls -l
sleep 1
end=$(date +%s)
timeTake=$((end - start))
echo "time take : $timeTake"