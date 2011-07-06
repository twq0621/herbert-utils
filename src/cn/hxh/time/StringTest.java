package cn.hxh.time;

public class StringTest {
	public static void main(String[] args) {
		StringTest t = new StringTest();
		t.test2();
	}
	public void test2(){
		String x = "123;567";
		int fhIndex = x.indexOf(";");
		System.out.println(x.substring(0,fhIndex));
	}
	public void test1(){
		String gpvluVal = "test=1;GPVLU=xx1;expire=12322";
		int gpvluIndex = gpvluVal.indexOf("GPVLU");
		gpvluVal = gpvluVal.substring(gpvluIndex + 5);
		int fenhaoIndex = gpvluVal.indexOf(";");
		gpvluVal = gpvluVal.substring(0, fenhaoIndex);
		System.out.println(gpvluVal);
	}
}
