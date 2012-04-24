package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;

public class XiaoXiangZi_Zhuxian_HpChange implements IEventListener{
private final int MonsterID = 1490;// 潇湘子的怪物模型ID
	private int hitCount = 0;
	


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {
			//2.	气血小于一半时召唤出另外一个潇湘子镜像进行战斗
			
			//@todo取消
			
			//3.	气血小于20%时每隔6秒大范围释放强攻击法术（技能等级=70）
			//4.	气血小于15%时会35%几率使用技能隔空渡气（技能等级=70）
			if(monster.getHpPercent() < 0.15) {
				if(Math.random() < 0.35) {
					//@todo 取消技能
					//api.setMonsterSkill(monster, 52006, 70, 10000, 1);
				}
				
			} else if(monster.getHpPercent() <  0.2) {
				hitCount++;
				if(hitCount > 6) {
					//@todo 取消技能
					//api.setMonsterSkill(monster, 70005, 70, 10000, 1);
					hitCount = 0;
				}
			}
			
		}
	}

}//end of class
