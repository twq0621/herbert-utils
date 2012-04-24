package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.resource.GlobalLanguage;
public class JinlunFawang_Yewai_HpChange implements IEventListener {
	private final int MonsterID = 1841;// 金轮法王的怪物模型ID


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster=(SMonster)args[0];
		if (monster.getModel() == MonsterID) {

			if(Math.random() < 0.3) {
				if(Math.random() < 0.5) {
					//@todo取消技能
					//api.setMonsterSkill(monster, 53008, 70, 10000, 1);
				} else {
					//@todo取消技能
					//api.setMonsterSkill(monster, 53010, 70, 10000, 1);
				}
			}
			if(monster.getHpPercent() < 0.8) {
				if(Math.random() < 0.3) {
					api.restoreMonsterHp(monster, 1000);
				}
				if (monster.getAttribute("firstevent") == null) {//
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("firstevent", "done");
					for(int i = 0; i < 3; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1820, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}
			}

			if(monster.getHpPercent() < 0.5) {
				if(Math.random() < 0.3) {
					api.restoreMonsterHp(monster, 3000);
				}
				if (monster.getAttribute("secondevent") == null) {//
					api.monsterSay(monster,GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("secondevent", "done");
					for(int i = 0; i < 3; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1830, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}
			}
			
		}
	}

}//end of class
