package net.snake.gamemodel.wedding.bean;

import java.util.Comparator;



public class WeddingRingComparator implements Comparator<WeddingRing> {

	@Override
	public int compare(WeddingRing o1, WeddingRing o2) {
		if (o1.getGrade() > o2.getGrade()) {
			return -1;
		} else if (o1.getGrade() < o2.getGrade()) {
			return 1;
		} else {
			if (o1.getId() > o1.getId()) {
				return -1;
			} else {
				return 1;
			}
		}
	}

}
