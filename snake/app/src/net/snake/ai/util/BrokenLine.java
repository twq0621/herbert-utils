package net.snake.ai.util;

import java.util.Collection;

import net.snake.ai.astar.Point;

/**
 * 折线行走
 * 
 * @author serv_dev
 * 
 */
public class BrokenLine {

	public static Point getAroundPoint(short tx, short ty, int distance, short[][] arr, Collection<short[]> block) {
		int size = distance * 8;
		int[] circleArr = new int[size * 2];
		int minX = tx - distance;
		int maxX = tx + distance;
		int minY = ty - distance;
		int maxY = ty + distance;
		int tempX = minX;
		int tempY = minY;
		for (int i = 0; i < size; i++) {
			circleArr[2 * i] = tempX;
			circleArr[2 * i + 1] = tempY;

			if (tempX == maxX && tempY < maxY) {
				tempY++;
			} else if (tempY == maxY && tempX > minX) {
				tempX--;
			} else if (tempX == minX && tempY > minY) {
				tempY--;
			} else if (tempX < maxX) {
				tempX++;
			}
		}
		for (int i = 0; i < circleArr.length; i = i + 2) {
			short tmpX = (short) circleArr[2 * i];
			short tmpY = (short) circleArr[2 * i + 1];
			if (!isBlock(tmpX, tmpY, arr, block)) {
				Point p = new Point(tmpX, tmpY);
				return p;
			}
		}
		return null;
	}

	/**
	 * 获得追击标准点,即目标最近的点(8方向)
	 * 
	 * @param ox
	 * @param oy
	 * @param tx
	 * @param ty
	 * @param distance
	 * @return
	 */
	private static short[] getPursueStandardPoint(short ox, short oy, short tx, short ty, int distance) {
		int xDis = tx - ox;
		int yDis = ty - oy;
		int xDisABS = Math.abs(xDis);
		int yDisABS = Math.abs(yDis);
		if (yDisABS == 0) {
			if (xDisABS > distance) {
				return new short[] { (short) (xDis > 0 ? tx - distance : tx + distance), ty };
			} else {
				// 原地不动即可
				return new short[] { ox, oy };
			}
		}
		if (xDisABS == 0) {
			if (yDisABS > distance) {
				return new short[] { tx, (short) (yDis > 0 ? ty - distance : ty + distance) };
			} else {
				return new short[] { ox, oy };
			}
		}

		if (xDisABS > distance) {
			if (yDisABS > distance) {
				return new short[] { (short) (xDis > 0 ? tx - distance : tx + distance), (short) (yDis > 0 ? ty - distance : ty + distance) };

			} else {
				return new short[] { (short) (xDis > 0 ? tx - distance : tx + distance), (short) (yDis > 0 ? ty - yDisABS : ty + yDisABS) };
			}
		} else {
			if (yDisABS > distance) {
				return new short[] { (short) (xDis > 0 ? tx - xDisABS : tx + xDisABS), (short) (yDis > 0 ? ty - distance : ty + distance) };
			} else {
				return new short[] { ox, oy };
			}

		}
	}

	/**
	 * 取得追击的点
	 * 
	 * @param ox
	 *            怪物当前位置
	 * @param oy
	 * @param tx
	 *            玩家当前位置
	 * @param ty
	 * @param distance
	 *            怪物与玩家的距离
	 * @param arr
	 *            地图存放下标数组
	 * @param block
	 *            玩家周围引起怪的坐标数组
	 * @return
	 */
	public static short[] getPursuePoint(short ox, short oy, short tx, short ty, int distance, short[][] arr, Collection<short[]> block) {
		short[] standardpoint = getPursueStandardPoint(ox, oy, tx, ty, distance);
		if (standardpoint[0] == ox && standardpoint[1] == oy) {
			return standardpoint;
		} else {
			// 这个折线上那个索引点正好是怪物攻击我的最近距离点
			short rx = standardpoint[0];
			short ry = standardpoint[1];
			if (isBlock(rx, ry, arr, block)) {// 如果这个点是障碍物,或被别的怪物先占去了
				// 以我为中心画四条边,并填充这四条边的点
				int size = distance * 8;
				int[] circleArr = new int[size * 2];
				int minX = tx - distance;
				int maxX = tx + distance;
				int minY = ty - distance;
				int maxY = ty + distance;
				int tempX = minX;
				int tempY = minY;
				// 这个折线上那个索引点正好是怪物攻击我的最近距离点,这个点在边上的位置索引
				int tempI = 0;
				for (int i = 0; i < size; i++) {// 顺时针方向记录下四条边的坐标
					circleArr[2 * i] = tempX;
					circleArr[2 * i + 1] = tempY;
					if (tempX == rx && tempY == ry) {
						tempI = 2 * i;
					}

					if (tempX == maxX && tempY < maxY) {
						tempY++;
					} else if (tempY == maxY && tempX > minX) {
						tempX--;
					} else if (tempX == minX && tempY > minY) {
						tempY--;
					} else if (tempX < maxX) {
						tempX++;
					}
				}
				// ==========================
				// 从最新近的点开始找下一个点
				int time = size / 2;
				int temp_jia = tempI + 2;// 从之前的那个不合适的坐标向后
				int temp_jian = tempI - 2;// 从之前的那个不合适的坐标向前
				int returnX = 0;
				int returnY = 0;
				for (int i = 0; i < time; i++) {
					if (temp_jia >= size * 2)
						temp_jia = 0;
					returnX = circleArr[temp_jia];
					returnY = circleArr[temp_jia + 1];
					if (!isBlock(returnX, returnY, arr, block)) {
						return new short[] { (short) returnX, (short) returnY };
					}
					if (temp_jian < 0)
						temp_jian = size - 2;
					returnX = circleArr[temp_jian];
					returnY = circleArr[temp_jian + 1];
					if (!isBlock(returnX, returnY, arr, block)) {
						return new short[] { (short) returnX, (short) returnY };
					}
					temp_jia = temp_jia + 2;
					temp_jian = temp_jian - 2;
				}
				return null;
			} else {
				return new short[] { rx, ry };
			}
		}
	}

	private static boolean isBlock(int x, int y, short[][] arr, Collection<short[]> block) {
		if (block != null) {
			for (short[] point : block) {
				if (point[0] == x && point[1] == y) {
					return true;
				}
			}
		}

		if (x < 0 || x >= arr.length || y < 0 || y >= arr[0].length) {
			return true;
		}

		if (arr[x][y] == 1)
			return true;
		return false;
	}

}
