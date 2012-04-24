package net.snake.commons;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
/**
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class CertificationUtil {
	private static Logger logger = Logger.getLogger(CertificationUtil.class);

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String decodeBase64(String b64string) throws Exception {
		return new String(Base64.decodeBase64(b64string.getBytes()), "utf-8");
	}

	public static String encodeBase64(String stringsrc) {
		try {
			Base64 base64encode = new Base64();
			return new String(base64encode.encode(stringsrc.getBytes("utf-8")));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public static String md5(String s) {

		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
