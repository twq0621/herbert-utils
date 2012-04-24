package net.snake.ai.astar;

import java.io.IOException;

import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;

/**
 * 路径序列化和反序列化
 * 
 * @author serv_dev
 * 
 */
public class PathConvert {

	public static short[] readToPathArray(RequestMsg ioutil) throws IOException {
		short X = ioutil.getShort();
		short Y = ioutil.getShort();
		// 一共有几个方向
		int dircount = ioutil.getByte();
		if (dircount < 1) {
			return null;
		}
		short[] result = new short[dircount * 2 + 2];
		result[0] = X;
		result[1] = Y;

		int arrayposition = 2;

		// int readbytecount = dircount % 2 == 0 ? dircount / 2 : dircount / 2 + 1;
		int readbytecount2 = dircount >> 1;
		for (int i = 0; i < readbytecount2; i++) {// 先折半
			int value = ioutil.getByte();
			int dir = (value & 0xf0) >> 4;
			// 得到高位方向
			// result[arrayposition] = (short) getNextDirectionX(X, dir);
			// result[arrayposition + 1] = (short) getNextDirectionY(Y, dir);
			fillNextDirection(result, arrayposition, X, Y, dir);
			X = result[arrayposition];
			Y = result[arrayposition + 1];
			arrayposition += 2;

			// if (arrayposition < result.length) {
			dir = value & 0x0f;
			// result[arrayposition] = (short) getNextDirectionX(X, dir);
			// result[arrayposition + 1] = (short) getNextDirectionY(Y, dir);
			fillNextDirection(result, arrayposition, X, Y, dir);
			X = result[arrayposition];
			Y = result[arrayposition + 1];
			arrayposition += 2;
			// }

		}
		if ((dircount & 0x01) == 1) {// 如果是奇数再补上，省却循环内的多次判断
			int value = ioutil.getByte();
			int dir = (value & 0xf0) >> 4;
			// 得到高位方向
			fillNextDirection(result, arrayposition, X, Y, dir);
		}
		return result;

	}

	public static void writeToPathArray(short[] path, int index, ServerResponse ioutil) throws IOException {
		if (path.length < 2) {
			return;
		}
		// 格式 起点x(short)起点y(short) 路径向量数(byte) [方向(4bit)方向(4bit)]...
		ioutil.writeShort(path[index]);
		ioutil.writeShort(path[index + 1]);

		short X = path[index];
		short Y = path[index + 1];

		// int dircount = (path.length - index - 2) / 2;
		int dircount = (path.length - index - 2) >> 1;// 这是有多少个坐标
		ioutil.writeByte(dircount);
		int temp = 0;

		int xIdxBgn = index + 2;

		int dircount2 = dircount >> 1;
		int i = 0;
		for (; i < dircount2; i++) {
			int off = i << 2;
			int xIdx = off + xIdxBgn;
			int yIdx = off + xIdxBgn + 1;
			// int dir = getNextDirection(X, Y, path[i * 2 + index + 2], path[i * 2 + index + 3]);
			// X = path[i * 2 + index + 2];
			// Y = path[i * 2 + index + 3];
			int dir = getNextDirection(X, Y, path[xIdx], path[yIdx]);
			X = path[xIdx];
			Y = path[yIdx];
			temp = dir << 4;
			// if (i % 2 == 0) {// 第一个 /偶数
			// temp = dir << 4;
			// } else {
			// temp = temp | dir;
			// ioutil.writeByte(temp);
			// }
			xIdx = off + xIdxBgn + 2;
			yIdx = off + xIdxBgn + 3;
			// int dir = getNextDirection(X, Y, path[i * 2 + index + 2], path[i * 2 + index + 3]);
			// X = path[i * 2 + index + 2];
			// Y = path[i * 2 + index + 3];
			dir = getNextDirection(X, Y, path[xIdx], path[yIdx]);
			X = path[xIdx];
			Y = path[yIdx];
			temp = temp | dir;

			ioutil.writeByte(temp);
		}
		if ((dircount & 0x01) == 1) {
			int off = i << 2;
			int xIdx = off + xIdxBgn;
			int yIdx = off + xIdxBgn + 1;

			int dir = getNextDirection(X, Y, path[xIdx], path[yIdx]);
			X = path[xIdx];
			Y = path[yIdx];
			temp = dir << 4;
			ioutil.writeByte(temp);
		}
	}

	private static void fillNextDirection(short[] path, int cur, int X, int Y, int dir) {

		int px;
		int py;
		switch (dir) {
		case 0:
			px = X - 1;
			py = Y - 1;
			break;
		case 1:
			px = X;
			py = Y - 1;
			break;
		case 2:
			px = X + 1;
			py = Y - 1;
			break;
		case 3:
			px = X + 1;
			py = Y;
			break;
		case 4:
			px = X + 1;
			py = Y + 1;
			break;
		case 5:
			px = X;
			py = Y + 1;
			break;
		case 6:
			px = X - 1;
			py = Y + 1;
			break;
		case 7:
			px = X - 1;
			py = Y;
			break;

		default:
			return;
		}
		path[cur] = (short) px;
		path[cur + 1] = (short) py;
	}

	private static int getNextDirection(short X, short Y, short nextX, short nextY) {
		if (nextX < X) {
			if (nextY < Y) {
				return 0;
			} else if (nextY == Y) {
				return 7;
			} else {
				return 6;
			}

		} else if (nextX == X) {
			if (nextY < Y) {
				return 1;
			} else {
				return 5;
			}
		} else {
			if (nextY < Y) {
				return 2;
			} else if (nextY == Y) {
				return 3;
			} else {
				return 4;
			}
		}
	}

	// private static int getNextDirectionX(short X, int dir) {
	// if (dir == 0 || dir == 6 || dir == 7) {
	// return X - 1;
	// } else if (dir == 1 || dir == 5) {
	// return X;
	// } else if (dir == 2 || dir == 3 || dir == 4) {
	// return X + 1;
	// }
	// // 不应该
	// return -1;
	// }

	// private static int getNextDirectionY(short Y, int dir) {
	// if (dir == 0 || dir == 1 || dir == 2) {
	// return Y - 1;
	// } else if (dir == 3 || dir == 7) {
	// return Y;
	// } else if (dir == 4 || dir == 5 || dir == 6) {
	// return Y + 1;
	// }
	// // 不应该
	// return -1;
	// }

	// public static void main(String[] args) throws IOException {
	// DataIOUtil ioutil = DataIOUtil.newInstance4Wr();
	// PathConvert.writeToPathArray(new short[] { 10, 10,11,11,12,12,12,11},2,
	// ioutil);
	// FileOutputStream fos = new FileOutputStream(new File("d:\\test"));
	// fos.write(ioutil.toByteArray());
	// fos.close();
	//
	// DataIOUtil readutil = DataIOUtil.newInstance4Rd(ioutil.toByteArray());
	// short[] t = readToPathArray(readutil);
	// for (int i = 0; i < t.length; i++) {
	// System.out.println(t[i]);
	// }
	//
	// }
}
