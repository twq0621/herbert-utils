package net.snake.gamemodel.dujie.logic;

import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.dujie.bean.Hufazhen;
import net.snake.gamemodel.hero.logic.PropertyEntity;

/**
 * 法阵提供的属性加成Buff
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class HufazhenBuff implements net.snake.gamemodel.hero.logic.PropertyChangeListener {
	private PropertyEntity pro = null;

	public net.snake.consts.PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.buff;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		return pro;
	}

	public HufazhenBuff(Hufazhen zhen) {
		pro = new PropertyEntity();

		pro.setAttack(zhen.getAdd_ap());
		pro.setDefend(zhen.getAdd_dp());
		pro.setMaxHp(zhen.getAdd_hp());
		pro.setCrt(zhen.getAdd_crt());
		pro.setHit(zhen.getAdd_ht());
		pro.setDodge(zhen.getAdd_dodge());
		pro.setAttackSpeed(zhen.getAdd_as());
		pro.setMoveSpeed(zhen.getAdd_ms());

	}

}
