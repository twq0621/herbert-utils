package net.snake.gamemodel.activities.bean;

import java.util.Comparator;



public class XianshiActivtyRewardByOrderComparator implements Comparator<XianshiActivityReward>{


	/**
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(XianshiActivityReward o1, XianshiActivityReward o2) {
		if(o1.getOrder()>o2.getOrder()){
			return 1;
		}else{
			return -1;
		}
	}

}
