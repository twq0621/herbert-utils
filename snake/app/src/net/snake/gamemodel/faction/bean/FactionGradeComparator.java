package net.snake.gamemodel.faction.bean;

import java.util.Comparator;

import net.snake.gamemodel.faction.logic.FactionController;

/**
 * 按帮主等级排序
 * 
 * @author serv_dev
 * 
 */
public class FactionGradeComparator implements Comparator<FactionController> {
	@Override
	public int compare(FactionController o1, FactionController o2) {
		if (o1.getFaction().getFactionFlagId() > o2.getFaction()
				.getFactionFlagId()) {
			return -1;
		} else if (o1.getFaction().getFactionFlagId() < o2.getFaction()
				.getFactionFlagId()) {
			return 1;
		} else {
			FactionCharacter fc1 = o1.getBangzhu();
			FactionCharacter fc2 = o2.getBangzhu();
			if (fc1.getCce().getGrade() > fc2.getCce().getGrade()) {
				return -1;
			} else if (fc1.getCce().getGrade() < fc2.getCce().getGrade()) {
				return 1;
			} else {
				if (fc1.getId() > fc2.getId()) {
					return 1;
				} else if (fc1.getId() < fc2.getId()) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

}
