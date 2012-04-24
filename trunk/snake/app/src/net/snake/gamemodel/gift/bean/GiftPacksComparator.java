package net.snake.gamemodel.gift.bean;


import java.util.Comparator;



/**
 * 按帮主等级排序
 * 
 * @author serv_dev
 * 
 */
public class GiftPacksComparator implements Comparator<GiftPacks> {

	@Override
	public int compare(GiftPacks o1, GiftPacks o2) {
		if (o1.getId() > o2.getId()) {
			return 1;
		} else{
			return -1;
		}
	}

}
