package cn.hxh.collection;

import java.util.Collections;
import java.util.List;

public class MyCollectionUtils {

	/**
	 * 传入的list需要排好序，按照从小到大的顺序排列
	 * 选取数列中最小匹配值，例如 针对这个数组[1,10,100]，传入参数为20，则返回10
	 * @param list
	 * @param key
	 * @return
	 */
	public static <T> int binarySearchDownlimit(List<? extends Comparable<? super T>> list, T key) {
		int resIndex = Collections.binarySearch(list, key);
		if (resIndex < 0) {
			resIndex = -(resIndex + 2);
		}
		if (resIndex < 0)
			resIndex = 0;
		return resIndex;
	}

	/**
	 * 传入的list需要排好序，按照从小到大的顺序排列
	 * 选取数列中最大匹配值，例如针对这个数组 [1,10,100]，传入参数为20，则返回100
	 * @param list
	 * @param key
	 * @return
	 */
	public static <T> int binarySearchUplimit(List<? extends Comparable<? super T>> list, T key) {
		int resIndex = Collections.binarySearch(list, key);
		if (resIndex < 0) {
			resIndex = -(resIndex + 1);
		}
		int maxIndex = list.size() - 1;
		if (resIndex > maxIndex)
			resIndex = maxIndex;
		return resIndex;
	}
}
