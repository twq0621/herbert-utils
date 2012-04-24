package net.snake.gamemodel.skill.logic.buff.spouse;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 
 * 守护
 * BUFF作用：令自己的配偶获得一个BUFF，该BUFF的作用是当配偶血量变为0时，
 * 立即原地复活（不读倒计时，倒地后立即站起），恢复一定的血量值，免除死亡惩罚一次，并获得3秒钟的无敌时间。
 * 重生后的血量值=夫妻之间的友好度（最低为1000，最大为10000）
 * Copyright (c) 2011 Kingnet
 * @author serv_dev
 */
public class Guard extends Buff {

	public Guard(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		controller.setGuard(true);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		controller.setGuard(false);
		return true;
	}

}
