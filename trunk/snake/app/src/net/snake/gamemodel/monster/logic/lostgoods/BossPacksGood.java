package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodspackge;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;
/**
 * boss组包物品掉落
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class BossPacksGood extends BossGood{
	public Goodspackge gp;
    public BossPacksGood(Goodspackge gp){
    	this.gp=gp;
    }
	@Override
	public CharacterGoods dropLostGoods(Hero character,MonsterModel monster) {
		return gp.dropLostGoods(monster);
	}
	
	public void goodIndexReset(){
		gp.setNowlistIndex(0);
	}
}
