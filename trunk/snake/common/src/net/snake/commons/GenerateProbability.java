package net.snake.commons;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/**
 * 根据几率 计算 是否命中
 * 
 * @author Administrator
 * 
 */
public class GenerateProbability {
	public static final short gailv = 10000;// 总概率
	public static final short qianv = 1000;// 总概率
	public static final Random random = new Random();

	/**
	 * 根据几率 计算是否生成
	 * 
	 * @param probability
	 * @return true表示成功
	 */
	public static boolean isGenerate(int probability, int gailv) {
		if (gailv == 0) {
			gailv = 1000;
		}
		int random_seed = random.nextInt(gailv + 1);
		return probability >= random_seed;
	}

	/**
	 * 
	 * gailv/probability 比率形式
	 * 
	 * @param probability
	 * @param gailv
	 * @return
	 */
	public static boolean isGenerate2(int probability, int gailv) {
		if (probability == gailv)
			return true;
		if (gailv == 0) {
			gailv = 1;
		}
		int random_seed = random.nextInt(probability);
		return random_seed + 1 <= gailv;
	}

	/**
	 * 根据几率 计算是否生成
	 * 
	 * @param probability
	 * @return
	 */
	public static boolean defaultIsGenerate(int probability) {
		int random_seed = random.nextInt(gailv);
		return probability >= random_seed;
	}

	/**
	 * 从 min 和 max 中间随机一个值
	 * 
	 * @param max
	 * @param min
	 * @return 包含min max
	 */
	public static int randomValue(int max, int min) {
		int temp = max - min;
		temp = GenerateProbability.random.nextInt(temp + 1);
		temp = temp + min;
		return temp;
	}

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
	 * 返回在0-maxcout之间产生的随机数时候小于num
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isGenerateToBoolean(float num, int maxcout) {
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

	public static float randomFloatValue(float min, float max) {
		return (float) (Math.random() * (double) (max - min)) + min;
	}

	@SuppressWarnings("rawtypes")
	public static Object randomItem(Collection collection) {
		if (collection == null || collection.size() == 0) {
			return null;
		}
		int t = (int) (collection.size() * Math.random());
		int i = 0;
		for (Iterator item = collection.iterator(); i <= t && item.hasNext();) {
			Object next = item.next();
			if (i == t) {
				return next;
			}
			i++;
		}
		return null;
	}
	/**
	 * 半开区间[mn,mx)
	 * @param mn
	 * @param mx
	 * @return
	 */
	public static int rdmValue(int mn,int mx){
		int diff = mx - mn;
		int v=random.nextInt(diff);
		return v+mn;
	}

}
