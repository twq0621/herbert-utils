package net.snake.api;

import net.snake.gamemodel.hero.bean.VisibleObject;

public interface IAttackListener {
	public boolean beforeAttack(VisibleObject killer, VisibleObject behurted);

	public void oneAttacked(VisibleObject killer, VisibleObject behurted);
}
