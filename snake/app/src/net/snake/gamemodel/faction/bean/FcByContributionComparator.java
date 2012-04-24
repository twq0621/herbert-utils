package net.snake.gamemodel.faction.bean;

import java.util.Comparator;


/**
 * 按帮主等级排序
 * 
 * @author serv_dev
 * 
 */
public class FcByContributionComparator implements Comparator<FactionCharacter> {

	@Override
	public int compare(FactionCharacter o1, FactionCharacter o2) {
		int contribution1 = o1.getContribution();
		int contribution2 = o2.getContribution();
		if (contribution1 > contribution2) {
			return -1;
		} else if (contribution1 < contribution2) {
			return 1;
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
