package cn.hxh.thread;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

import org.apache.commons.lang.ArrayUtils;

public class BiSearchWithForkJoin extends RecursiveAction {

	private static final long serialVersionUID = 5335995944849121976L;

	private int[] toSearch;

	private int target;

	public int result = -1;

	public BiSearchWithForkJoin(int[] toSearch, int target) {
		this.toSearch = toSearch;
		this.target = target;
	}

	private int searchArray() {
		return Arrays.binarySearch(toSearch, target);
	}

	@Override
	protected void compute() {
		int searchLength = toSearch.length;
		if (toSearch.length <= 4) {
			result = searchArray();
		} else {
			int newLength = searchLength / 2;
			int[] newArray1 = ArrayUtils.subarray(toSearch, 0, newLength);
			int[] newArray2 = ArrayUtils.subarray(toSearch, newLength,
					searchLength);
			BiSearchWithForkJoin left = new BiSearchWithForkJoin(newArray1,
					target);
			BiSearchWithForkJoin right = new BiSearchWithForkJoin(newArray2,
					target);
			invokeAll(left, right);
			if (left.result >= 0) {
				result = left.result;
			}
			if (right.result >= 0) {
				result = right.result + newArray1.length;
			}
		}
	}

}
