package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * boss某一物品掉落
 * 
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class BossOneGood extends BossGood{
	public Goodmodel gm;
    public BossOneGood(Goodmodel gm){
    	this.gm=gm;
    }
	@Override
	public CharacterGoods dropLostGoods(Hero character,MonsterModel monster) {
		return CharacterGoods.createCharacterGoods(1, gm, monster.getLoopCount(), 0);
	}

  
}
