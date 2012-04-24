package net.snake.gamemodel.chest.logic;

import net.snake.gamemodel.chest.bean.ChestCount;

/**
 * 
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-6-11 下午05:29:39
 */

public class ChestTools {
	public static void addChestExchange(int countExchange, ChestCount chestCount, int exchangeCiShu) {
		int exchangecount = 0;
		int exchangecount2 = 1500;
		if (exchangeCiShu != 0) {
			exchangecount = 1500 * exchangeCiShu;
		}
		if (chestCount.getChesttypeReceive3015() != 0) {
			int a = chestCount.getChesttypeReceive3015().intValue();
			a = a + 1;
			exchangecount2 = 1500 * a;
		}
		if (countExchange >= 100 + exchangecount && chestCount.getChesttypeReceive3011() == exchangeCiShu) {
			chestCount.setChesttypeReceive3011(1 + exchangeCiShu);
		}
		if (countExchange >= 300 + exchangecount && chestCount.getChesttypeReceive3012() == chestCount.getChesttypeReceive3011() - 1) {
			chestCount.setChesttypeReceive3012(chestCount.getChesttypeReceive3011());
		}
		if (countExchange >= 600 + exchangecount && chestCount.getChesttypeReceive3013() == chestCount.getChesttypeReceive3012() - 1) {
			chestCount.setChesttypeReceive3013(chestCount.getChesttypeReceive3012());
		}
		if (countExchange >= 1000 + exchangecount && chestCount.getChesttypeReceive3014() == chestCount.getChesttypeReceive3013() - 1) {
			chestCount.setChesttypeReceive3014(chestCount.getChesttypeReceive3013());
		}

		if (countExchange >= exchangecount2 && chestCount.getChesttypeReceive3015().intValue() == chestCount.getChesttypeReceive3014() - 1) {
			chestCount.setChesttypeReceive3015(chestCount.getChesttypeReceive3014());
		}
	}
}
