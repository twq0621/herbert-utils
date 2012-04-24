package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.resource.GlobalLanguage;
public class MenggudaJiang_Yewai_HpChange implements IEventListener {
	/**
	 * Logger for this class
	 */
	//private static final Logger logger = Logger.getLogger(HuoDu_Zhuxian.class);
	private final int MonsterID = 1471;// 蒙古大将的怪物模型ID



	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {
			//2.	30%几率使用击退技能（技能等级=70）击退玩家
			//3.	30%几率使用点穴技能（技能等级=70）点中玩家使玩家不能移动
			if(Math.random() < 0.3) {
				if(Math.random() < 0.5) {
					//@todo取消技能
					//api.setMonsterSkill(monster, 53008, 70, 10000, 1);
				} else {
					//@todo取消技能
					//api.setMonsterSkill(monster, 53010, 70, 10000, 1);
				}
			}
			//2.	气血小于40%时召唤蒙古刀兵12
			if(monster.getHpPercent() < 0.4) {
				if(Math.random() < 0.3) {
					api.restoreMonsterHp(monster, 1000);
				}
				if (monster.getAttribute("firstevent") == null) {//
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("firstevent", "done");
					for(int i = 0; i < 3; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1440, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}
			} else if(monster.getHpPercent() < 0.2) {//5.	气血小于20%值时召唤蒙古骑兵12
				if(Math.random() < 0.3) {
					api.restoreMonsterHp(monster, 2000);
				}
				if (monster.getAttribute("secondevent") == null) {//
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("secondevent", "done");
					for(int i = 0; i < 3; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1450, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}
			} 

		}
	}

}//end of class
