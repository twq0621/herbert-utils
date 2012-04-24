package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * boss怪物铜币掉落对象
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class BossCopperGood extends BossGood{
	public int count;
    public BossCopperGood(int count){
    	this.count=count;
    }
	@Override
	public CharacterGoods dropLostGoods(Hero character,MonsterModel mm) {
		CharacterGoods cg=new CharacterGoods();
		cg.setGoodmodelId(-1);
		int countTemp=count;
		if(character!=null&&character.isAllBornEquip()){
			countTemp=(int)(countTemp+count*0.1f);
		} 
		cg.setCount(countTemp);
		return cg;
	}

  
}
