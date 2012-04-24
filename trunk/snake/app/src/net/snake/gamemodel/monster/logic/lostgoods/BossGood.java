package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * boss怪物特殊掉落（boss特使掉落物品继承此类）
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public abstract class BossGood {
	private int randNum;

	public abstract CharacterGoods dropLostGoods(Hero character, MonsterModel mm);

	public int getRandNum() {
		return randNum;
	}

	public void setRandNum(int randNum) {
		this.randNum = randNum;
	}
	
	public void goodIndexReset(){
		
	}
}
