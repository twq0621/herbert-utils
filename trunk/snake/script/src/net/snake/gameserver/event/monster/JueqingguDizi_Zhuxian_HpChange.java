package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class JueqingguDizi_Zhuxian_HpChange implements IEventListener{
private final int MonsterID = 1290;// 绝情谷弟子的怪物模型ID



	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {

			if(Math.random() < 0.5) {//2.	每次受到攻击时50%几率会使用飞燕环诀技能（技能等级=40）
				//@todo取消技能
				//api.setMonsterSkill(monster, 51031, 40, 10000, 1);
			}
		}
	}

}//end of class
