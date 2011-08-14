package cn.hxh.string;

import junit.framework.TestCase;

public class StringTest extends TestCase {

	public void testSplitAnd(){
		String xx = "11&22";
		String[] res = xx.split("&");
		System.out.println(res.length);
		for (String string : res) {
			System.out.println(string);
		}
	}
	
}
