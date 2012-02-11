#!/bin/bash
find source_code_dir_path -type f -name "*.c" -print0 | xargs -0 wc -l
#统计c文件的总行数