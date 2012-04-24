package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.resource.GlobalLanguage;

public class DuZhuWang_Zhuxian_HpChange implements IEventListener {
	private final int MonsterID = 1390;// 毒株王的怪物模型ID

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == MonsterID) {
			// 2. 30%几率使用点穴技能（技能等级=70）攻击玩家
			if (Math.random() < 0.3) {
				// @todo 取消技能
				// api.setMonsterSkill(monster, 53010, 70, 10000, 1);
			}
			// 2. 气血小于60%、30%、10%时召唤出6只蜘蛛使用怪物近战技能（技能等级=60）攻击玩家
			if (monster.getHpPercent() < 0.6) {
				if (monster.getAttribute("firstevent") == null) {//
					api.monsterSay(monster,
							GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("firstevent", "done");
					for (int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),
								monster.getY(), 10, 1400, false, true, 20,
								60 * 1000);
						monster2.getEnmityManager().addEnmityValueToRole(
								monster.getEnmityManager().getHatesetRole(),
								100);
						// api.setMonsterSkill(monster2, skillid, skillgrade,
						// gaiLv, usedCnt)
					}
				}
			}
			if (monster.getHpPercent() < 0.3) {
				if (monster.getAttribute("secondevent") == null) {//
					api.monsterSay(monster,
							GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("secondevent", "done");
					for (int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),
								monster.getY(), 10, 1400, false, true, 20,
								60 * 1000);
						monster2.getEnmityManager().addEnmityValueToRole(
								monster.getEnmityManager().getHatesetRole(),
								100);
						// api.setMonsterSkill(monster2, skillid, skillgrade,
						// gaiLv, usedCnt)
					}
				}
			}

		}

	}

}// end of class
