/**
 * 
 */
package net.snake.gamemodel.gift.logic;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.goods.bean.CharacterGoods;

/**
 * 互斥随机掉落 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */

public class GiftPacksHuchiRandomGoods extends GiftPacksRandom {
	public int randomMax = 0;
	private List<HuchiRandom> list = new ArrayList<HuchiRandom>();

	public void addHuchiRandom(HuchiRandom random) {
		list.add(random);
	}

	public int getRandomMax() {
		return randomMax;
	}

	public void setRandomMax(int randomMax) {
		this.randomMax = randomMax;
	}

	public List<HuchiRandom> getList() {
		return list;
	}

	public void setList(List<HuchiRandom> list) {
		this.list = list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.bean.giftpacks.GiftPacksRandom#randomGoods()
	 */
	@Override
	public CharacterGoods randomGoods() {
		if (list == null || list.size() == 0) {
			return null;
		}
		int random = GenerateProbability.randomIntValue(0, randomMax);
		for (int i = 0; i < list.size(); i++) {
			HuchiRandom r = list.get(i);
			if (r == null) {
				continue;
			}
			if (random <= r.getRandCount()) {
				return r.getCg();
			}
		}
		return null;
	}

}
