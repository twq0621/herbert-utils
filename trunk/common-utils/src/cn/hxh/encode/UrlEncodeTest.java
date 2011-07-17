package cn.hxh.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncodeTest {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(URLEncoder.encode("金币", "UTF-8"));
		System.out.println(URLDecoder.decode("%E9%AD%94%E6%99%B6", "UTF-8"));
	}

}
