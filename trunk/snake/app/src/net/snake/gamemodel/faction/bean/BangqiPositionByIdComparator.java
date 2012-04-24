package net.snake.gamemodel.faction.bean;


import java.util.Comparator;

/**
 * 按帮主等级排序
 * @author serv_dev
 *
 */
public class BangqiPositionByIdComparator implements Comparator<BangqiPosition>{


	@Override
	public int compare(BangqiPosition o1, BangqiPosition o2) {
		int id1=o1.getId();
		int id2=o2.getId();
		if(id1>id2){
			return -1;
		}else{
			return 1;
		}
	}

}
