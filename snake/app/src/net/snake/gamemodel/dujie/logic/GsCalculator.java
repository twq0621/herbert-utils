package net.snake.gamemodel.dujie.logic;

import java.util.Collection;
import java.util.Iterator;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.shell.Options;

/**
 * 玩家装备GS数值计算器
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
public class GsCalculator {
	public static int calcHeroGs(Hero hero) {
		int gs=0;
		Collection<CharacterGoods>characterGoods =hero.getCharacterGoodController().getBodyGoodsList();
		for (Iterator<CharacterGoods> iterator = characterGoods.iterator();iterator.hasNext();) {
			CharacterGoods equip = iterator.next();
			gs +=equip.getGS();
		}
		return gs;
	}
	
	public static int calcHeroFee(int gs,int tianjieNum){
		int fee = Options.Coefficient * tianjieNum * gs;
		return fee;
	}
	
	public static int calcHeroZhenyuan(int gs){
		int zh = 10000 +  gs*1;
		return zh;
	}
}
