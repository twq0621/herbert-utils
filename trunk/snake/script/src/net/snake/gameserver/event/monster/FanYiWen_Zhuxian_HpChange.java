package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class FanYiWen_Zhuxian_HpChange implements IEventListener {
	/**
	 * Logger for this class
	 */
	//private static final Logger logger = Logger.getLogger(HuoDu_Zhuxian.class);
	private final int MonsterID = 1370;// 樊一翁的怪物模型ID


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {
			if(monster.getHpPercent() > 0.2) {//2.	受到攻击时一定30%几率使用大范围伤害技能（技能等级=60）
				if(Math.random() < 0.3) {
					//@todo取消技能
					//api.setMonsterSkill(monster, 70004, 60, 10000, 1);
				}
			} else { //3.	气血低于20%时50%几率使用高伤害技能（技能等级=60）
				//@todo取消技能
				//api.setMonsterSkill(monster, 70005, 60, 10000, 1);
			}
		}
		
	}

}//end of class
