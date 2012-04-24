package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class LiuWuBian_zhiXian_HpChange implements IEventListener{
private final int MonsterID = 1105;// 柳无变的怪物模型ID



	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster)args[0];
		if (monster.getModel() == MonsterID) {

			if(monster.getHpPercent() < 0.3) { // 4.	气血小于30%时
				if(Math.random() < 0.5) {
					//@todo取消技能
					//api.setMonsterSkill(monster, 51023, 30, 10000, 1);
				}
				if (monster.getAttribute("secondevent") == null) {//
					monster.setAttribute("secondevent", "done");
					//召唤出1个强盗使用罡风扫叶技能
					SMonster monster2 = api.createMonsterAroundPoint(
							monster.getSceneRef(), monster.getX(),monster.getY(), 
							10, 1060, false, true, 10, 60*1000);
					monster2.getEnmityManager().addEnmityValueToRole(monster.getEnmityManager().getHatesetRole(), 100);
					//@todo 取消
					//api.setMonsterSkill(monster2, 51012, 15, 10000, 100);
				}
			}
		}
	}

}//end of class
