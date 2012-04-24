package com.qq.open;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.qq.QQBaseInfoConsts;

public class CheckUtils {
	private static MessageDigest md;

	private static void init() {
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static boolean checkHash(long uid, String hash) {
		if (md == null) {
			init();
		} else {
			md.reset();
		}
		String key1 = QQBaseInfoConsts.key1;
		String key2 = QQBaseInfoConsts.key2;
		// String hashDecode = null;
		// try {
		// hashDecode = hash;// URLDecoder.decode(hash, "UTF-8");
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// return false;
		// }
		byte[] des = Base64Coder.decode(hash);
		md.update(des, 8, 4);
		md.update(key1.getBytes(), 0, key1.getBytes().length);
		md.update(des, 0, 4);
		byte[] uidBytes = new byte[8];
		ByteBuffer bbBuffer = ByteBuffer.allocate(8);
		bbBuffer.order(ByteOrder.LITTLE_ENDIAN);
		bbBuffer.putLong(uid);
		bbBuffer.position(0);
		bbBuffer.get(uidBytes);
		md.update(uidBytes, 0, 8);
		md.update(des, 12, 4);
		md.update(key2.getBytes(), 0, key2.getBytes().length);
		md.update(des, 4, 4);
		byte[] src = md.digest();
		for (int i = 0; i < 20; i++) {
			if (src[i] != des[i + 16])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(checkHash(9, "r2Y//yRi+8pMYW4lhT2ET4VOXVgwq8Orc3WimLHtEn2/oGYp"));
		// System.out.println(checkHash(1, "SSySqaKOJsCTMq%2BDyn%2BCT0WCEhdEQkxMTbcuF6taZjaFkww2"));
		// System.out.println(checkHash(1, "zMf9KOeB2LPZ7wb5wn%2BCT5%2BMj3zBFsA4SUN23HoG85yJBeOw"));
		// System.out.println(checkHash(1, "bnR4jMRL180nqFSTuH%2BCT09kNVkSYTcyXHWL%2Fa59b5XPzyBm"));
		// System.out.println(checkHash(1, "G2GhRu%2BBwxorkR9uCD%2BET%2Fwej6SSCnpP4YKBZFxWuPT%2FtC9U"));
	}
}
