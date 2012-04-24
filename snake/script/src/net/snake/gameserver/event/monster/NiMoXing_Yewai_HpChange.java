package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class NiMoXing_Yewai_HpChange implements IEventListener {
	/**
	 * Logger for this class
	 */
	private final int MonsterID = 1561;// 尼摩星野外的怪物模型ID
	


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {
			//2.	受到攻击时20%几率给自身上增加50%防御
			//3.	受到攻击时20%几率给自身上增加100%暴击
			//4.	受到攻击时20%几率给自身上增加100%闪避
			if(Math.random() < 0.2) {			
				api.addMonsterDefence(monster, 1000, 5);
				api.addMonsterDogde(monster, 1000, 5);
				api.addMonsterAttack(monster, 500, 5);
			}
			//5.	气血小于30%时30几率放群体范围技能（技能等级=70）攻击玩家
			if(monster.getHpPercent() < 0.3) {
				if(Math.random() < 0.3) {
					api.restoreMonsterHp(monster, 2000);
					//@todo取消
					//api.setMonsterSkill(monster, 70004, 70, 10000, 1);
				}
			}
			//6.	快死亡时会放群体范围技能（技能等级=300）
			//@todo
			

			
		}
	}

}//end of class
