package net.snake.gamemodel.monster.logic;

import java.util.HashMap;
import java.util.HashSet;

import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.SceneMonster;

/***
 * 怪物的路径管理器 系统初始化的时候 计算出每个怪物的路径 游戏启动后不会 即时的为怪物寻路
 * 
 * @author serv_dev
 * 
 */
public class SceneMonsterLineManager {
	private SceneMonster scenemonster;
	/** 有几条路可选 ***/
	private int linecount;
	/** 初始化状态 **/
	boolean initstatus = false;
	/** key=x_y_线路数 **/
	private HashMap<String, short[]> paths = new HashMap<String, short[]>();
	/** 每个怪物的随机路径的点数 ***/
	private static final int maxPointCount = 5;
	/** 为了找到以上maxPointCount，最多重试多少次 **/
	private static final int maxTryCount = 20;

	public SceneMonsterLineManager(SceneMonster scenemonster) {
		this.scenemonster = scenemonster;
	}

	public void destory() {
		if (paths != null) {
			paths.clear();
		}
	}

	/***
	 * 初始化怪物路径管理器
	 */
	public void initLineManager() {
		if (initstatus) {
			return;
		}
		initstatus = true;
		if (scenemonster.getMonsterModel().getPatrol() == 0) {// 巡逻范围为0,则没有路径
			return;
		}
		short fromx = (short) scenemonster.getOriginalX();
		short fromy = (short) scenemonster.getOriginalY();
		if (!scenemonster.getSceneRef().isPermitted(fromx, fromy)) {
			// 起始点就是障碍物
			return;
		}

		HashSet<Point> points = new HashSet<Point>();
		// 先找几个可走的并且不重合的点;
		points.add(new Point(fromx, fromy));
		for (int i = 0; i < maxTryCount; i++) {
			Point nextpoint = getNextPoint();//里面有一次寻路计算
			if (nextpoint != null) {
				points.add(nextpoint);
			}
			if (points.size() >= maxPointCount) {
				break;
			}
		}
		linecount = points.size() - 1;
		// 计算每个点到每个点的路径
		Scene scene = scenemonster.getSceneRef();
		for (Point from : points) {
			int fcount = 0;
			for (Point to : points) {
				if (!from.equals(to)) {
					String key = from.x + "_" + from.y + "_" + fcount;
					paths.put(key, scene.findWay(from.x, from.y, to.x, to.y));
					fcount++;
				}
			}
		}
		points.clear();
	}

	/***
	 * 获得下一个点
	 * 
	 * @return
	 */
	private Point getNextPoint() {
		int dirx = (Math.random() > 0.5f) ? 1 : -1;
		int diry = (Math.random() > 0.5f) ? 1 : -1;
		short fromX = (short) scenemonster.getOriginalX();
		short fromY = (short) scenemonster.getOriginalY();
		short targetX = (short) (fromX + dirx * Math.random() * scenemonster.getPatrolWidth());
		short targetY = (short) (fromY + diry * Math.random() * scenemonster.getPatrolHeight());
		if (!scenemonster.getSceneRef().isPermitted(targetX, targetY)) {
			return null;
		}
		short[] path = scenemonster.getSceneRef().findWay(fromX, fromY, targetX, targetY);
		if (path == null || path.length <= 2) {
			return null;
		}
		return new Point(path[path.length - 2], path[path.length - 1]);

	}

	/***
	 * 返回一条路径
	 * 
	 * @return
	 */
	public short[] getLine() {
		if (linecount <= 0) {
			return null;
		}
		int x = scenemonster.getX();
		int y = scenemonster.getY();
		int rand = (int) (Math.random() * linecount);
		short[] s = paths.get(x + "_" + y + "_" + rand);
		return s;
	}

	private class Point {
		short x;
		short y;

		public Point(short x, short y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Point other = (Point) obj;
			return this.x == other.x && this.y == other.y;
		}

		@Override
		public int hashCode() {
			return (x + "_" + y).hashCode();
		}

	}
}
