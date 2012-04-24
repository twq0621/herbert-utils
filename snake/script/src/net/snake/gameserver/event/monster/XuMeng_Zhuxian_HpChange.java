package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
import net.snake.resource.GlobalLanguage;

public class XuMeng_Zhuxian_HpChange implements IEventListener{
private final int MonsterID = 1040;//徐猛的怪物模型ID为1040
	


	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {

			if(Math.random() < 0.3) {//30%几率 使用 战天伏魔技能
				
				api.setMonsterSkill(monster, 51013, 1, 10000, 1);
			}

			if ( monster.getHpPercent() < 0.5) {//50%血量的时候召唤
				
				if (monster.getAttribute("firstevent") == null) {//
					api.monsterSay(monster, GlobalLanguage.Baiyuanwang_zhuxuanStr);
					
					monster.setAttribute("firstevent", "");// 做一下标记,只当这种事件产生一次时使用
					//召唤2个山贼
					for(int i = 0; i < 4; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),monster.getY(), 
								10, 1020, false, true, 20, 60*1000);
						monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
					}
				}

			}
		}
		
	}

	
}//end of class
