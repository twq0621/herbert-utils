package net.snake.gameserver.event.monster;



import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.resource.GlobalLanguage;
public class JueqingguShouwei_Zhuxian_HpChange implements IEventListener{
private final int MonsterID = 1360;//绝情谷守卫的怪物模型ID
	


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {

			if(Math.random() < 0.25) {//2.	受到攻击时25%几率几率使用高伤害技能（技能等级=50）
				//@todo 取消技能
				//api.setMonsterSkill(monster, 70005, 50, 10000, 1);
			}

			if ( monster.getHpPercent() < 0.2) {//4.	气血低于20%时召唤飞贼10只使用双凤穿云技能（技能等级=50）攻击仇恨玩家
				if (monster.getAttribute("firstevent") == null) {//
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					monster.setAttribute("firstevent", "");// 做一下标记,只当这种事件产生一次时使用
					for(int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1340, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
						//api.setMonsterSkill(monster2, skillid, skillgrade, gaiLv, usedCnt)
					}
				}

			}
		}
	}

	
}//end of class
