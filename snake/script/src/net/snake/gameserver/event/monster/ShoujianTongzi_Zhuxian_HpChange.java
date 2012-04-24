package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.resource.GlobalLanguage;

public class ShoujianTongzi_Zhuxian_HpChange implements IEventListener{
private final int MonsterID = 1280;// 守剑童子的怪物模型ID


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {
			
			if ( monster.getHpPercent() < 0.6) {//2.	气血小于60%时会召唤出8个土匪
				if (monster.getAttribute("firstevent") == null) {//
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("firstevent", "");
					//召唤8个土匪
					for(int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1120, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}
			}

			if ( monster.getHpPercent() < 0.4) {//3.	气血小于40%时召唤出8个小偷
				if (monster.getAttribute("secondevent") == null) {//
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("secondevent", "done");
					for(int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1130, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}
			}
		}
	}

}//end of class
