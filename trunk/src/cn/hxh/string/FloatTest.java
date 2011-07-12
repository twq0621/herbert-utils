package cn.hxh.string;

import java.util.Date;

public class FloatTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a1 = "1.0";
		System.out.println(Math.round(Float.valueOf(a1)));
		a1 = "1.5";
		System.out.println(Math.round(Float.valueOf(a1)));
		a1 = "2";
		System.out.println(Math.round(Float.valueOf(a1)));
		System.out.println(new Date(1000000000000L));
	}

}
