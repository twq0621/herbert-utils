#!/bin/bash
find . -type f -name "*.txt" -print0 | xargs -0 rm -f
#删除所有txt结尾的文件 