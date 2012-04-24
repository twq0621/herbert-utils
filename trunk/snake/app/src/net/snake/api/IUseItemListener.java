package net.snake.api;

import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;

public interface IUseItemListener {
	/**
	 * 能否使用这个道具
	 * @param hero
	 * @param m
	 * @return
	 */
	public boolean beforeUseItem(Hero hero,int goodId, int position,Goodmodel gm);
	public void onUseItem(Hero hero,Goodmodel m);
}
