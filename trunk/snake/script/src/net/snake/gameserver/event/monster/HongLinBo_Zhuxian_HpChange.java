package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class HongLinBo_Zhuxian_HpChange implements IEventListener {
	/**
	 * Logger for this class
	 */
	//private static final Logger logger = Logger.getLogger(HuoDu_Zhuxian.class);
	private final int MonsterID = 1150;// 洪林波的怪物模型ID

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {

			if ( monster.getHpPercent() < 0.2) {//3.	气血小于20%时
				//@todo 取消
				//api.setMonsterSkill(monster, 51032, 30, 10000, 1);
			} else if(monster.getHpPercent() < 0.4) { // 4.	气血小于40%
				if(Math.random() < 0.5) {//50%几率 使用 大范围攻击
					//@todo 取消
					//api.setMonsterSkill(monster, 70004, 30, 10000, 1);
				}
			}
		}
		
	}

}//end of class
