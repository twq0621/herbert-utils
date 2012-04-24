/**
 * 
 */
package net.snake.gamemodel.gift.logic;

import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.goods.bean.CharacterGoods;

/**
 * 普通随机掉落 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class GiftPacksRandomGoods extends GiftPacksRandom {
	private CharacterGoods cg;

	/**
	 * @param cg
	 */
	public GiftPacksRandomGoods(CharacterGoods cg) {
		this.cg = cg;
	}

	@Override
	public CharacterGoods randomGoods() {
		boolean istrue = GenerateProbability.isGenerateToBoolean(getProbability(), GiftPacksRandom.probabilityMax);
		if (istrue) {
			return cg;
		} else {
			return null;
		}
	}

}
