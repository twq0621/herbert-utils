package cn.hxh.udplog;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaintainBuffHelper {

	private Map<String, Integer> uidMapping = new ConcurrentHashMap<String, Integer>();

	private static MaintainBuffHelper mInstance = new MaintainBuffHelper();

	public static MaintainBuffHelper getInstance() {
		return mInstance;
	}

	private static Logger logger = LoggerFactory
			.getLogger(MaintainBuffHelper.class);

	public void encodeString(ChannelBuffer buf, String content)
			throws UnsupportedEncodingException {
		byte[] contentByte = content.getBytes("UTF-8");
		// 编入字符串
		buf.writeShort(contentByte.length + 1);
		// 字符串内容
		buf.writeBytes(contentByte);
		// \0结尾
		buf.writeByte('\0');
	}

	public int uidToInt(String uid) {
		if (uidMapping.containsKey(uid)) {
			return uidMapping.get(uid);
		}
		MessageDigest messageDigest;
		int result = 0;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			byte[] ret = messageDigest.digest("s3gg3ddd".getBytes());
			byte[] byteArray = new byte[8];
			int index = 0;
			for (int i = 0; i < ret.length; i += ret.length / 8) {
				byteArray[index] = ret[i];
				index++;
			}
			result = byteArray4ToInt(byteArray);
		} catch (NoSuchAlgorithmException e) {
			logger.error("", e);
		}
		uidMapping.put(uid, result);
		return result;
	}

	private int byteArray4ToInt(byte[] byteValue) {
		if (byteValue.length != 8)
			return 0;

		int intValue = 0;
		try {
			intValue = toInt(byteValue[0]);
			intValue = (intValue << 4) + toInt(byteValue[1]);
			intValue = (intValue << 4) + toInt(byteValue[2]);
			intValue = (intValue << 4) + toInt(byteValue[3]);
			intValue = (intValue << 4) + toInt(byteValue[4]);
			intValue = (intValue << 4) + toInt(byteValue[5]);
			intValue = (intValue << 4) + toInt(byteValue[6]);
			intValue = (intValue << 4) + toInt(byteValue[7]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return intValue;
	}

	private int toInt(byte b) {
		if (b >= 0)
			return (int) b;
		else
			return (int) (b + 256);
	}

}
