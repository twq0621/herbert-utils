package net.snake.gamemodel.pet.bean;

import java.util.Comparator;



public class HorseModelComparator implements Comparator<HorseModel> {
	@Override
	public int compare(HorseModel o1, HorseModel o2) {
		if(o1.getId()>o2.getId()){
			return 1;
		}else if(o1.getId()<o2.getId()){
			return -1;
		}
		return 0;
	}
	
}
