package net.snake.api.log.udp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.log4j.Logger;
public class MaintainBuffHelper {
	private Map<String, Integer> uidMapping = new ConcurrentHashMap<String, Integer>();

	private static MaintainBuffHelper mInstance = new MaintainBuffHelper();

	public static MaintainBuffHelper getInstance() {
		return mInstance;
	}

	private static Logger logger = Logger.getLogger(MaintainBuffHelper.class);

	public static void encodeString(IoBuffer buf, String content) throws UnsupportedEncodingException {
		byte[] contentByte = (content + "\0").getBytes("UTF-8");
		// 编入字符串
		buf.putShort((short) (contentByte.length));
		// 字符串内容
		buf.put(contentByte);
		buf.flip();
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
