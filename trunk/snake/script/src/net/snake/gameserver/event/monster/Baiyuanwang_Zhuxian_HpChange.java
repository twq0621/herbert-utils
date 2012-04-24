package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.resource.GlobalLanguage;

public class Baiyuanwang_Zhuxian_HpChange implements IEventListener{
	/**
	 * Logger for this class
	 */
	private final int MonsterID = 1380;// 白猿王的怪物模型ID
	private int hitCount = 0;


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {
			hitCount ++; 
			if(hitCount > 10) {
				api.addMonsterAttack(monster, 500, 3*1000);
				//@todo取消技能
				//api.setMonsterSkill(monster, 70004, 60, 10000, 1);
				hitCount = 0;
			}
			if(monster.getHpPercent() < 0.25) {//2.		自身气血低于25%一定值时增加自身防御力100%，并召唤出10只小猴使用近战 技能（技能等级=60）攻击玩家
				if (monster.getAttribute("firstevent") == null) {//
					api.addMonsterDefence(monster, 500, 30*1000);
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("firstevent", "done");
					for(int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1320, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}
			}
		}
		
	}

}//end of class
