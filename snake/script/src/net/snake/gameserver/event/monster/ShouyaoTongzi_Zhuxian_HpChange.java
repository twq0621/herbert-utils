package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;

public class ShouyaoTongzi_Zhuxian_HpChange implements IEventListener{
private final int MonsterID = 1300;// 守药童子的怪物模型ID

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {
			if ( monster.getHpPercent() < 0.4) {//2.	气血小于40%时30%几率自己使用技能隔空渡气（技能等级=40）
				if(Math.random() < 0.3) {
					//@todo没有目标
					//api.setMonsterSkill(monster, 52006, 40, 10000, 1);
				}
			} else if (monster.getHpPercent() < 0.2) {
				if(Math.random() < 0.3) {//3.	气血小于20%时30%几率自己使用技能隔空渡气（技能等级=60）
					//@todo 没有目标
					//api.setMonsterSkill(monster, 52006, 60, 10000, 1);
				}
			}
		}
	}

}//end of class
