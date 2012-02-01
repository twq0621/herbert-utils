#!/bin/bash
echo -e "checking cs"
perl ./check_port.pl -h 10.40.244.70 -p 9529
echo -e "checking gs1"
perl ./check_port.pl -h 10.40.244.70 -p 9526
echo -e "checking gs2"
perl ./check_port.pl -h 10.40.244.70 -p 9526
echo -e "checking ps"
perl ./check_port.pl -h 10.40.244.66 -p 9528
echo -e "checking ms"
perl ./check_port.pl -h 10.40.244.66 -p 9534
echo -e "checking js"
perl ./check_port.pl -h 10.40.244.76 -p 9530