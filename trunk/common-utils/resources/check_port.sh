#!/bin/bash
echo -e "checking cs"
perl ./check_port.pl -h 10.40.244.70 -p 9529
echo -e "---------------------\nchecking gs1"
perl ./check_port.pl -h 10.40.244.70 -p 9526
echo -e "---------------------\nchecking gs2"
perl ./check_port.pl -h 10.40.244.70 -p 9526
echo -e "---------------------\nchecking ps"
perl ./check_port.pl -h 10.40.244.66 -p 9528
echo -e "---------------------\nchecking ms"
perl ./check_port.pl -h 10.40.244.66 -p 9534
echo -e "---------------------\nchecking js"
perl ./check_port.pl -h 10.40.244.76 -p 9530