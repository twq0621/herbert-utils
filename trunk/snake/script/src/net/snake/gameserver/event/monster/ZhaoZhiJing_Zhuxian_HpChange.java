package net.snake.gameserver.event.monster;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SMonster;

public class ZhaoZhiJing_Zhuxian_HpChange implements IEventListener {
	private final int MonsterID = 1100;// 赵志敬的怪物模型ID

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterHPChange;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		SMonster monster = (SMonster) args[0];
		if (monster.getModel() == MonsterID) {

			if (monster.getHpPercent() < 0.5) {// 3. 气血小于50%时
				if (Math.random() < 0.3) {// 30%几率 使用 两仪万象技能
					api.setMonsterSkill(monster, 51023, 30, 10000, 1);
				}
			}
			if (monster.getHpPercent() < 0.3) { // 4. 气血小于30%时召唤赵志敬镜像攻击
				if (monster.getAttribute("secondevent") == null) {//
					monster.setAttribute("secondevent", "done");
					SMonster monster2 = api.createMonsterAroundPoint(
							monster.getSceneRef(), monster.getX(),
							monster.getY(), 10, 1101, false, true, 20,
							60 * 1000);
					monster2.getEnmityManager().addEnmityValueToRole(
							monster.getEnmityManager().getHatesetRole(), 100);
				}
			}
		}

	}

}// end of class
