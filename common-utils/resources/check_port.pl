#!/usr/bin/perl

use strict;
use IO::Socket::INET;
use Getopt::Long();

my $host='127.0.0.1';
my $port=0;
my $proto='tcp';

Getopt::Long::GetOptions(
	'h=s' => \$host,
	'p=s' => \$port
) or usage("Invalid Command line options");

print "$host\:$port checking\n";
my $socket = new IO::Socket::INET(
	PeerHost => $host,
	PeerPort => $port,
	Proto    => $proto
);  # or die "ERROR in Socket Creation : $!\n";

if($socket){
	print "found port!\n";
	close($socket);
}
else{
	printf "\x1b\x5b1;31;40m not found port!\n\x1b\x5b0;37;40m";
}