/**
 * 
 */
package net.snake.gamemodel.gift.logic;

import net.snake.gamemodel.goods.bean.CharacterGoods;

/**
 * 随机开除物品
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */

public class HuchiRandom {
private int randCount=0;
private CharacterGoods cg;
public HuchiRandom(CharacterGoods cg,int randCount){
	this.cg=cg;
	this.randCount=randCount;
}
public int getRandCount() {
	return randCount;
}
public void setRandCount(int randCount) {
	this.randCount = randCount;
}
public CharacterGoods getCg() {
	return cg;
}
public void setCg(CharacterGoods cg) {
	this.cg = cg;
}

}
