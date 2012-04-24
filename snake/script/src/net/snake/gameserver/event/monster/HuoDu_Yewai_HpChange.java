package net.snake.gameserver.event.monster;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;
public class HuoDu_Yewai_HpChange implements IEventListener {
	private final int MonsterID = 1082;// 霍都野外的怪物模型ID

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == MonsterID) {

			if (Math.random() < 0.3) {// 30%几率 使用 战天伏魔技能
				// @todo取消技能
				// api.setMonsterSkill(monster, 51013, 25, 10000, 1);
			}
			if (monster.getHpPercent() < 0.5) {// 3. 气血小于50%时召唤4个强盗
				if (monster.getAttribute("firstevent") == null) {//
					monster.setAttribute("firstevent", "done");// 做一下标记,只当这种事件产生一次时使用
					// 召唤2个强盗
					for (int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),
								monster.getY(), 10, 1060, false, true, 20,
								60 * 1000);
						monster2.getEnmityManager().addEnmityValueToRole(
								monster.getEnmityManager().getHatesetRole(),
								100);
					}
				}
			}
			if (monster.getHpPercent() < 0.3) { // 4. 气血小于25%时召唤4个蒙古斥侯
				if (monster.getAttribute("secondevent") == null) {//
					monster.setAttribute("secondevent", "done");// 做一下标记,只当这种事件产生一次时使用
					// 召唤2个蒙古斥侯
					for (int i = 0; i < 2; i++) {
						SMonster monster2 = api.createMonsterAroundPoint(
								monster.getSceneRef(), monster.getX(),
								monster.getY(), 10, 1070, false, true, 20,
								60 * 1000);
						monster2.getEnmityManager().addEnmityValueToRole(
								monster.getEnmityManager().getHatesetRole(),
								100);
					}
				}
			}
		}
	}

}// end of class
