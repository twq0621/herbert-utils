package net.snake.ai.astar;

import java.util.Deque;

import net.snake.ai.move.MoveInfo;

public class PathSplit {
	// 最大的路径点数
	public final static int MAXPATHPOINT = 20; // (MAXPATHPOINT/2) 个路径点

	public static void splitPathToQueue(Deque<MoveInfo> queue, short[] src) {
		if (src == null) {
			return;
		}
		if (src.length < MAXPATHPOINT) {// 如果路径的长度小于阀值，就直接放到队列里。
			queue.addLast(MoveInfo.createWalkPath(src));
			return;
		}

		int startLocation = 0;
		int endLocation = 0;
		while (startLocation < src.length) {// 如果路径段的开始位置还在其中
			endLocation = startLocation + MAXPATHPOINT;
			if (endLocation < src.length) {// 如果路径段的结束位置还在其中
				short[] t = new short[MAXPATHPOINT];
				System.arraycopy(src, startLocation, t, 0, t.length);
				queue.addLast(MoveInfo.createWalkPath(t));
				startLocation = endLocation - 2;
			} else {// 超过了下标
				short[] t = new short[src.length - startLocation];
				System.arraycopy(src, startLocation, t, 0, t.length);
				queue.addLast(MoveInfo.createWalkPath(t));
				break;
			}
		}
	}

	/*
	 * public static void main(String[] args) { short[] t=new
	 * short[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}; Deque<MoveInfo> array=new
	 * ArrayDeque<MoveInfo>(); splitPathToQueue(array, t); for(MoveInfo
	 * a:array){ for(int i=0;i<a.path.length;i++){
	 * System.out.print(a.path[i]+" "); } System.out.println(); }
	 * 
	 * }
	 */
}
