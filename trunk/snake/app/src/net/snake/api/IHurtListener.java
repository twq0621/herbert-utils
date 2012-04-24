package net.snake.api;

import net.snake.ai.fight.bean.FighterVO;
import net.snake.gamemodel.hero.bean.VisibleObject;

/**
 * 伤害监听器
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public interface IHurtListener {
	public boolean beforeHurt(VisibleObject killer, VisibleObject behurted, FighterVO vo, int hurtValue, boolean nohurt);
	public void onBehurted(VisibleObject killer, VisibleObject behurted, FighterVO vo, int hurtValue);
}
