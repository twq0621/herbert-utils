package net.snake.gamemodel.fight.bean;

import java.util.Comparator;

public class GongchengDayComparator implements Comparator<DayGongchengDateList> {

	@Override
	public int compare(DayGongchengDateList o1, DayGongchengDateList o2) {
		if(o1.getGongchengDate().before(o2.getGongchengDate())){
			return -1;
		}else{
			return 1;
		}
	}

}
