#!usr/bin/perl
use strict;
use Getopt::Long;

sub println {
	print "$_[0]\n";
}

sub usage {
	my $msg = $_[0];
	if(defined $msg && length $msg){
		$msg .= unless $msg =~ /\n$/;		
	}
	
	my $cmd = $0;
	$cmd =~ s#^.*/##;
	
	print STDERR {
		$msg,
		"usage: $cmd -r 代码根目录[WebServer/branch] -d 策划数据根目录[WebDesign/GameData/DevGameData] -b 分支[1.2.10]\n"
		."	...\n"
		."	...\n"
		."	...\n"
	};
	die("\n");
}

#从svn获取最新的策划数据
#输入数据根目录，分支号
#返回 SVN版本号
sub updateData {
	my $dataRoot = $_[0];
	my $branch = $_[1];
	println "获取最新的策划数据,分支为 $branch";
	my $svnResult = `svn up $dataRoot/$branch -username hxh -password hxh123`;
	my $tip = 0;
	my $changed = 0;
	my @array = split(/\n/,$svnResult);
	my $num = @array;
	
	if($num >1){
		$changed = 1;
	}
	else {
		$changed = 0;
	}
	foreach my $line (@array){
		$tip = $line;
	}
	
	foreach my $line(@array){
		$tip = $line;
		if(($line =~ /^Updated/) || ($line=~ /^更新/)){
			$changed = 1;
		}
		elsif(($line=~ /^At/) || ($line =~ /^版本/)){
			$changed = 0;
		}
		elsif(($line =~ /^Skipped/) || ($line =~ /^跳过/)){
			println "svn更新出错。";
		}
	}
	if($changed == 1){
		println "策划数据有更新 :$tip";
	}
	else {
		println "策划数据无更新 :$tip";
	}
}

#创建db，并刷新策划数据
sub createDb{
	my $codeRoot   = $_[0];
	my $dataRoot   = $_[1];
	my $branch     = $_[2];
	my $schema     = $_[3];
	my $ifCreateDb = $_[4];
	my $date = `date +%Y%m%d`;
	chomp($schema);
	my $cmd_start = "cat $codeRoot/$branch/db/sql";
	my $cmd_end = "mysql -h172.17.107.31 -urat -ptar $schema";
	println "当前的数据库为：$schema";
	if($ifCreateDb){
		updateData($codeRoot, "$branch/db/sql");
		println "开始创建新区";
		my $result = `mysql -h172.17.107.31 -urat -ptar create database $schema if not exist`;
		println "创建数据库完成：$schema";
		print "初始化表结构...";
		`$cmd_start/other_db.sql|$cmd_end`;
		`$cmd_start/mem_db.sql|$cmd_end`;
		`$cmd_start/monitor.sql|$cmd_end`;
		`$cmd_start/quartz_mysql.sql|$cmd_end`;
		`$cmd_start/sp.sql|$cmd_end`;
		`$cmd_start/trigger.sql|$cmd_end`;
		`$cmd_start/template.sql|$cmd_end`;
	}
	println "初始化策划表数据...";
	`rm -rf $codeRoot/$branch/db/sql/all.sql`;
	`cat $dataRoot/$branch/DesignData/data/cn_old/*.sql > $codeRoot/$branch/db/sql/all.sql`;
	`$cmd_start/all.sql|$cmd_end`;
	println "成功创建新区数据库:$schema";
	return $schema;
}

my $codeRoot; #"WebServer/branch"
my $dataRoot; #"WebDesign/GameData/DevGameData"
my $branch;   #1.2.7
my $dbName;   #hxh136
my $ifCreateDb;#0

Getopt::Long::GetOptions(
	'r=s' => \$codeRoot,
	'd=s' => \$dataRoot,
	'b=s' => \$branch,
	'db=s'=> \$dbName,
	'c=i' => \$ifCreateDb
)
or usage("Invalid command line options.");

$codeRoot = "/home/$codeRoot";
$dataRoot = "/home/$dataRoot";
my $date = `date`;
chomp($date);
println "-------------------$date---------------------";

#更新策划数据
updateData($dataRoot,$branch);

#创建db，并刷策划数据
$dbName = createDb($codeRoot,$dataRoot,$branch,$dbName,$ifCreateDb);
