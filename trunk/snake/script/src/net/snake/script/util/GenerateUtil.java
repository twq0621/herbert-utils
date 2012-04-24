package net.snake.script.util;

public class GenerateUtil {
	/**
	 * 返回在0-maxcout之间产生的随机数时候小于num
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isGenerateToBoolean(int num, int maxcout) {
		double count = Math.random() * maxcout;
		if (count < num) {
			return true;
		}
		return false;
	}

	/**
	 * 随机产生min到max之间的整数值 包括min max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomIntValue(int min, int max) {
		return (int) (Math.random() * (double) (max - min + 1)) + min;
	}
}
