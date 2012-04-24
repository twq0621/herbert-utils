package net.snake.gameserver.event.copy;

import java.util.Collection;
import java.util.Iterator;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;

public class Lianzhan_SceneInit implements IEventListener {
	// public final int mianguSkillId = 52003;
	// public final int huagongSkillId = 52002;
	// public final int dianxueSkillId = 53010;

	private static final int Normal_MnGrade = 20;
	private static final int Normal_MxGrade = 65;

	private static final int[] Boss_Grade = { 25, 30, 35, 40, 45, 50, 55, 60 };
	private static final short Boss_x = 123;
	private static short Boss_y = 100;

	public final int yichiTime = 1000 * 60;
	public final int bossYichiTime = 1000 * 5;

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SScene scene
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];

		if (instance.getInstanceId() != 1) {
			return;
		}
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return;
		}
		Iterator<SRole> iterator = roleCollection.iterator();
		SRole role = iterator.next();
		if (role == null) {
			return;
		}
		// int grade = role.getMaxTeamerGrade() - 4;
		int ptId = 0;
		int bossId = 0;
		Collection<SMonster> collection = scene.getSMonsterCollection();
		for (SMonster monster : collection) {
			if (monster.isPT()) {
				if (ptId == 0) {
					int grade =role.getMaxTeamerGrade() -2;
					grade = getNomalMonsterGrade(grade);
					ptId = instance.getInstanceMonsterId(1, grade);
				}
				SMonster sceneMonster = api.createMonsterToScene(ptId, monster.getX(), monster.getY(), 7, 1, 1, false, scene);
				api.changeInstanceSceneMonsterflushCount(sceneMonster, 1);
			} else if (monster.isBoss()) {
				if (bossId == 0) {
					int grade = getBossMonsterGrade(role);
					bossId = instance.getInstanceMonsterId(3, grade);
				}
				SMonster sceneMonster = api.createMonsterToScene(bossId, Boss_x, Boss_y, 7, 2, 1, false, scene);
				// api.addMonsterSkill(sceneMonster, mianguSkillId, 1000);
				// api.addMonsterSkill(sceneMonster, huagongSkillId, 1000);
				// api.addMonsterSkill(sceneMonster, dianxueSkillId, 1000);
				api.changeInstanceSceneMonsterflushCount(sceneMonster, 1);
			}
		}
		// for (SRole roles : roleCollection) {
		// api.sendMsg(roles, (byte) 7, GlobalLanguage.instanceLianzhanStr);
		// }
	}

	private int getNomalMonsterGrade(int grade) {
//		int grade = hero.getMaxTeamerGrade() - 4;
		if (grade < Normal_MnGrade) {
			return Normal_MnGrade;
		}
		if (grade > Normal_MxGrade) {
			return Normal_MxGrade;
		}
		return grade;
	}

	private int getBossMonsterGrade(SRole hero) {
		int grade = hero.getMaxTeamerGrade();
		for (int i = 0; i < Boss_Grade.length; i++) {
			if (grade < Boss_Grade[i]) {
				return Boss_Grade[i];
			}
		}

		return Boss_Grade[Boss_Grade.length - 1];
	}
}
