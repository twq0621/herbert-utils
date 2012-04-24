package net.snake.gamemodel.faction.bean;

import java.util.Comparator;


/**
 * 按帮主等级排序
 * 
 * @author serv_dev
 * 
 */
public class FcByPositionComparator implements Comparator<FactionCharacter> {

	@Override
	public int compare(FactionCharacter o1, FactionCharacter o2) {
		int position1 = o1.getPosition();
		int position2 = o2.getPosition();
		if (position1 == 0) {
			position1 = 100;
		}
		if (position2 == 0) {
			position2 = 100;
		}
		if (position1 > position2) {
			return 1;
		} else if (position1 < position2) {
			return -1;
		} else {
			if (o1.getCce().getGrade() > o2.getCce().getGrade()) {
				return -1;
			} else if (o1.getCce().getGrade() > o2.getCce().getGrade()) {
				return 1;
			} else {
				if (o1.getCce().getWuxueJingjie() > o2.getCce()
						.getWuxueJingjie()) {
					return -1;
				} else if (o1.getCce().getWuxueJingjie() < o2.getCce()
						.getWuxueJingjie()) {
					return 1;
				} else {
					if (o1.getCce().getChannelXuewei() > o2.getCce()
							.getChannelXuewei()) {
						return -1;
					} else if (o1.getCce().getChannelXuewei() < o2.getCce()
							.getChannelXuewei()) {
						return 1;
					} else {
						return 0;
					}
				}
			}
		}
	}

}
