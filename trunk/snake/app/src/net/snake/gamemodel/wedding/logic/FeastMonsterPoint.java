package net.snake.gamemodel.wedding.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import net.snake.ai.astar.Point;
import net.snake.commons.GenerateProbability;

/**
 * @author serv_dev
 */
public class FeastMonsterPoint {

	public FeastMonsterPoint(WedFeastManagerVline line) {

	}

	public final static List<Point> points = new Vector<Point>();
	static {
		points.add(new Point(20, 33));
		points.add(new Point(21, 108));
		points.add(new Point(21, 84));
		points.add(new Point(24, 77));
		points.add(new Point(28, 104));
		points.add(new Point(28, 27));
		points.add(new Point(30, 126));
		points.add(new Point(30, 39));
		points.add(new Point(31, 72));
		points.add(new Point(32, 111));
		points.add(new Point(37, 122));
		points.add(new Point(39, 32));
		points.add(new Point(39, 68));
		points.add(new Point(41, 134));
		points.add(new Point(44, 25));
		points.add(new Point(45, 117));
		points.add(new Point(46, 63));
		points.add(new Point(48, 38));
		points.add(new Point(48, 51));
		points.add(new Point(52, 113));
		points.add(new Point(54, 140));
		points.add(new Point(54, 68));
		points.add(new Point(55, 46));
		points.add(new Point(58, 13));
		points.add(new Point(59, 122));
		points.add(new Point(60, 109));
		points.add(new Point(63, 42));
		points.add(new Point(66, 147));
		points.add(new Point(67, 74));
		points.add(new Point(67, 9));
		points.add(new Point(68, 105));
		points.add(new Point(68, 128));
		points.add(new Point(70, 51));
		points.add(new Point(70, 59));
		points.add(new Point(73, 83));
		points.add(new Point(74, 93));
		points.add(new Point(78, 135));
		points.add(new Point(78, 152));
		points.add(new Point(80, 42));
		points.add(new Point(80, 79));
		points.add(new Point(82, 114));
		points.add(new Point(83, 56));
		points.add(new Point(83, 99));
		points.add(new Point(89, 51));
		points.add(new Point(90, 74));
		points.add(new Point(91, 151));
		points.add(new Point(91, 95));
		points.add(new Point(93, 105));
		points.add(new Point(94, 41));
		points.add(new Point(100, 146));
		points.add(new Point(101, 102));
		points.add(new Point(104, 61));
		points.add(new Point(104, 95));
		points.add(new Point(105, 36));
		points.add(new Point(109, 142));
		points.add(new Point(111, 34));
		points.add(new Point(113, 107));
		points.add(new Point(115, 29));
		points.add(new Point(115, 78));
		points.add(new Point(107, 82));
		points.add(new Point(120, 72));
		points.add(new Point(121, 103));
		points.add(new Point(124, 26));
		points.add(new Point(124, 63));
		points.add(new Point(127, 137));
		points.add(new Point(128, 19));
		points.add(new Point(128, 94));
		points.add(new Point(129, 58));
		points.add(new Point(130, 112));
		points.add(new Point(132, 78));
		points.add(new Point(130, 130));
		points.add(new Point(143, 84));
	}

	public Point getAndLockPoint() {
		// 此处要改成随机
		List<Point> ll = new ArrayList<Point>();
		ll.addAll(points);
		ll.removeAll(monsterPoint);
		int randomIntValue = GenerateProbability.randomIntValue(0, ll.size() - 1);
		Point point = ll.get(randomIntValue);
		lockPoint(point);
		return point;
		// 所有固定刷怪点己经用完
	}

	private void lockPoint(Point point) {
		if (point != null)
			monsterPoint.add(point);
	}

	public void resize() {
		monsterPoint.clear();
	}

	private List<Point> monsterPoint = new ArrayList<Point>();

}
