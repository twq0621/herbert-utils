package net.snake.commons;

import java.util.regex.Pattern;

/**
 * 正则 工具
 * 
 * @author dev
 * 
 */
public class RegexUtil {

	/**
	 * @param flag
	 *            标志
	 * @param sourceStr
	 *            原字符串
	 * @return
	 */
	public static boolean validateTaskReges(String flag, String sourceStr) {
		return Pattern.matches("^(.*," + flag + ",{1}.*|" + flag + ",{1}.*|.*," + flag + "|" + flag + "{1})$", sourceStr);
	}

	public static boolean validateTaskReges2(String flag, String sourceStr) {
		return Pattern.matches("^(.*," + flag + ".*|" + flag + ".*)$", sourceStr);
	}

	public static boolean validateTask_monsterCond(String flag, String sourceStr) {
		//70100019#10#30003_130_113_1#6,70100016#10#30003_102_107_1#5,70100013#10#30003_121_105_1#4
		return Pattern.matches("^(.*#" + flag + ",?.*)$", sourceStr);
	}

	public static String getMonsterStr(int modelId, int modelNum) {
		return (modelId + "#" + modelNum + "#" + "1111" + "#" + "1111" + "_" + "0_0");
	}

}
