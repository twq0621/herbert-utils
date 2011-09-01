package cn.hxh.string;

import junit.framework.TestCase;

public class StringTest extends TestCase {

	public void testSplitAnd() {
		String xx = "11&22";
		String[] res = xx.split("&");
		System.out.println(res.length);
		for (String string : res) {
			System.out.println(string);
		}
	}

	public void testStringFormat() {
		String src = "herbert is %s";
		src = String.format(src, "No.1");
		System.out.println(src);
		src = "herbert is %s, here is %s";
		src = String.format(src, "No.1", "she");
		System.out.println(src);
		src = "herbert is %s, here' is %s";
		src = String.format(src, "No.1", "she");
		System.out.println(src);
	}

}
