package cn.hxh.codec;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Test {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String queryStr = "tip=骑士&rank=20&qnum=30";
		System.out.println(Base64.encodeBase64String(queryStr.getBytes()).trim());
		System.out.println(Base64.encodeBase64String(queryStr.getBytes("GBK")));
		System.out.println(Base64.encodeBase64String(queryStr.getBytes("ISO-8859-1")));
		System.out.println(new String(Base64.encodeBase64(queryStr.getBytes("GBK")),"GBK"));
		System.out.println(new String(Base64.encodeBase64Chunked(queryStr.getBytes())));
	//	System.out.println(new String(Base64.encodeBase64URLSafe(queryStr.getBytes())));
	}

}
