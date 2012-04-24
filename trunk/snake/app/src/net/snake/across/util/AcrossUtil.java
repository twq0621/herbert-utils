package net.snake.across.util;

import net.snake.commons.CertificationUtil;
import net.snake.gamemodel.auth.persistence.AuthConfigManager;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 跨服工具类
 * 
 * @author serv_dev
 */
public class AcrossUtil {

	private static final Logger logger = Logger.getLogger(AcrossUtil.class);

	/**
	 * 
	 * @param publicKey
	 *            公钥
	 * @param content
	 *            传入BASE64未解码的字符串
	 * @param ts
	 *            时间戳
	 * @return
	 */
	public static boolean check(String publicKey, String content, long ts) {
		long timeinterval = Math.abs((ts - System.currentTimeMillis()) / 1000);
		// 15分钟的容错
		if (timeinterval > 900) {
			if (logger.isInfoEnabled()) {
				logger.info("无效时间戳" + publicKey + " " + content + " " + ts);
			}
			return false;
		}
		return CertificationUtil.md5(content + ts + AuthConfigManager.getInstance().getAcrossMd5Key()).equals(publicKey);
	}

	public static String getPublicKey(String content) {
		String enBase64 = CertificationUtil.encodeBase64(content);
		String acrossMd5Key = "";

		acrossMd5Key = AuthConfigManager.getInstance().getAcrossMd5Key();

		return CertificationUtil.md5(enBase64 + System.currentTimeMillis() + acrossMd5Key);
	}

	/**
	 * 用于探查跨服论剑不支持的操作,检查跨服的非法调用
	 */
	public static void checkNoAcross() {
		if (Options.IsCrossServ) {
			throw new UnsupportedOperationException("跨服论剑版本不支持该操作,但发生了调用,请检查");
		}
	}

	public static boolean isAcrossServer() {
		return Options.IsCrossServ;
	}
}
