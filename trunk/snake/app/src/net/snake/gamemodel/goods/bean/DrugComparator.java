package net.snake.gamemodel.goods.bean;

import java.util.Comparator;



public class DrugComparator implements Comparator<Goodmodel> {

	@Override
	public int compare(Goodmodel o1, Goodmodel o2) {
		if (o1.getDrugValue() > o2.getDrugValue()) {
			return -1;
		} else if(o1.getDrugValue() < o2.getDrugValue()) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
