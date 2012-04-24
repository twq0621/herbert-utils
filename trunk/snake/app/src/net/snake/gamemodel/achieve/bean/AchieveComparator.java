package net.snake.gamemodel.achieve.bean;

import java.util.Comparator;

/**
 * 成就排序比较。倒序
 * @author serv_dev
 *
 */
public class AchieveComparator implements  Comparator<Achieve>{

	@Override
	public int compare(Achieve o1, Achieve o2) {
		int count1=o1.getAchieveCount();
		int count2=o2.getAchieveCount();
		if(count1>count2){
			return -1;
		}else if(count1<count2){
			return 1;
		}
		return 0;
	}

}
