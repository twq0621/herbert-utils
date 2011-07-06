package cn.hxh.script;

public class AvScript { 

	public static final int MALE=0; 

	public static final int FEMALE=1; 

	private static final String maleNameArray[]={"成", "德", "龙", "清", "宏", "弘", "圣", "戊", "嘉", "杰", "蒂", "胧", "特", "尼", "昊", "萨", "伟", 
	"剑", "新", "杨", "森", "昆", "仑", "仓", "颉", "浑", "沌", "风", "峰", "智", "天", "空", "亚", "田", "野", "丸", "太", "郎", "潘", "高", "寿", "胜", 
	"乾", "坤"}; 

	private static final String femaleNameArray[]={"音", "芯", "伊", "唯", "郁", "绫", "妮", "美", "蔷", "薇", "佳", "玫", "瑰", "珠", "玑", "睿", 
	"苍", "井", "空", "玛", "丽", "杏", "子", "樱", "桃", "颐", "畅", "春", "园", "妖", "娆", "裴", "沛", "宁", "珊", "姗", "晓", "红", "婷", "蕊", "楠", 
	"慧", "琳"}; 

	public static void main(String args[]) { 
	for(int i=0; i < 10; i++) { 
	System.out.println(makeName(FEMALE)); 
	} 

	} 

	public static String makeName(int sex) { 
	String name=""; 
	if(sex == MALE) { 
	name=_makeName(maleNameArray); 
	} else { 
	name=_makeName(femaleNameArray); 
	} 
	return name; 
	} 

	private static String _makeName(String[] names) { 
	int length=getRangedRandom(4, 4); 
	String[] name=new String[length]; 
	int count=0; 
	while(count < length) { 
	String random=names[getRandomZeroToN_1(names.length)]; 
	if(count - 1 >= 0) { 
	if(random.equals(name[count - 1])) 
	continue; 
	} 
	name[count]=random; 
	count++; 
	} 
	StringBuffer buf=new StringBuffer(); 
	for(String s: name) { 
	buf.append(s); 
	} 
	return buf.toString(); 
	} 

	private static int getRandomOneToN(int n) { 
	double tmp=Math.ceil(Math.random() * n); 
	return (int)tmp; 
	} 

	private static int getRangedRandom(int min, int max) { 
	min=min - 1; 
	max=max - min; 
	return getRandomOneToN(max) + min; 
	} 

	private static int getRandomZeroToN_1(int n) { 
	int r=getRandomOneToN(n) - 1; 
	return r < 0 ? 0 : r; 
	} 
	} 
