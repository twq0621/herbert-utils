package cn.hxh.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import junit.framework.TestCase;

public class UrlEncodeTest extends TestCase {

	public void test1() throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode("金币", "UTF-8"));
		System.out.println(URLDecoder.decode("%E9%AD%94%E6%99%B6", "UTF-8"));
	}

}
