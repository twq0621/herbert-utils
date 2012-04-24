package net.snake.gamemodel.dujie.logic;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 渡劫副本不允许使用精神类药物。
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public class DujieSpdrugListener implements IUseItemListener {

	@Override
	public boolean beforeUseItem(Hero hero,int goodId, int position,Goodmodel gm) {
		return false;
	}

	@Override
	public void onUseItem(Hero hero, Goodmodel m) {
	}

}
