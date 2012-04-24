package net.snake.gameserver.event.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.resource.GlobalLanguage;

/**
 * 夫子阵 副本
 * 
 */
public class FuZiZhen_MonsterDie extends SuperInstance implements IEventListener {
	private int groupNum = 10;// 一共10组怪物 打完后 + 刷一个BOSS
	private int flushMonsterIntervalTime = 10;// 刷怪时间 间隔
	private int npcId = 1738; // 夫子阵 副本NPCID.
	private String currTips; // 当前NPC提示语.
	private Random random = new Random();
	// 随机的坐标点
	private int point[][] = new int[][] { { 20, 20 }, { 24, 20 }, { 28, 20 }, { 32, 20 }, { 20, 23 }, { 28, 23 }, { 32, 23 }, { 20, 17 }, { 24, 17 }, { 28, 17 }, { 32, 17 },
			{ 36, 17 }, { 40, 17 }, { 36, 23 }, { 36, 20 }, { 40, 20 }, { 40, 23 }, { 44, 23 }, { 48, 23 }, { 28, 29 }, { 28, 26 }, { 32, 29 }, { 50, 26 }, { 46, 29 }, { 42, 29 },
			{ 42, 26 }, { 37, 30 }, { 46, 26 }, { 32, 26 }, { 50, 29 } };
	private int bossPoint[] = new int[] { 23, 20 };

	/**
	 * 刷怪
	 * 
	 * @param currGroupNum
	 *            当前第几组 怪物
	 */
	@SuppressWarnings("unchecked")
	private boolean flushMonster(SApi api, SInstance instance, SScene scene, int currGroupCount) {
		if (currGroupCount > groupNum) {
			return false;
		}
		// 当前刷的是第几组怪物
		instance.setAttribute("currGroupCount", currGroupCount + 1);
		Collection<SMonster> collection = scene.getSMonsterCollection();
		SMonster monsterModel[] = collection.toArray(new SMonster[collection.size()]);
		// 第一次进来的时候 getInstanceAllCharacters 只获得一个角色
		Collection<SRole> roles;
		if (currGroupCount == 0) {
			roles = this.getTeamAllRole(instance.getInstanceAllCharacters());
		} else {
			roles = instance.getInstanceAllCharacters();
		}
		SMonster monster = monsterModel[currGroupCount];
		List<SMonster> monsters = (ArrayList<SMonster>) instance.getAttribute("monsters");
		if (monsters == null) {
			monsters = new ArrayList<SMonster>();
			instance.setAttribute("monsters", monsters);
		}
		monsters.clear();

		int roleGrate = this.getRolesMaxGrate(roles);
		if (currGroupCount == groupNum) {
			SMonster sceneMonster = api.createMonsterToScene(monster.getModel(), false, bossPoint[0], bossPoint[1], GlobalLanguage.FuZiZhenBoss, scene, flushMonsterIntervalTime,
					(short) (roleGrate + 4));
			int skillgrade = sceneMonster.getMonsterSkillGrade(0);
			int dodge = sceneMonster.getDodge() + roleGrate * skillgrade;
			sceneMonster.changeDodge(dodge);
			sceneMonster.changeAttackModel((short) 1);
			instance.removeAttribute("currGroupIdioms");
			monsters.add(sceneMonster);
			sendMsg(api, GlobalLanguage.FuZiZhenBossTip, roles);
		} else {
			List<int[]> points = getPoints();
			// 随机获得一个诗句
			char[] currGroupIdioms = randomGetNextGroupIdioms(instance);
			instance.setAttribute("currGroupIdioms", currGroupIdioms);
			for (int i = 0; i < currGroupIdioms.length; i++) {
				int[] xy = getRandomPoint(points);// 随机坐标
				SMonster sceneMonster = api.createMonsterToScene(monster.getModel(), false, xy[0], xy[1], currGroupIdioms[i] + "", scene, flushMonsterIntervalTime,
						(short) (roleGrate + (currGroupCount - 5)));
				int skillgrade = sceneMonster.getMonsterSkillGrade(0);
				sceneMonster.changeGrade((short) (roleGrate + (currGroupCount - 5)));
				int dodge = sceneMonster.getDodge() + roleGrate * skillgrade;
				sceneMonster.changeDodge(dodge);
				monsters.add(sceneMonster);
			}
			String msg;
			if (currGroupCount == 0) {
				msg = GlobalLanguage.exChangeParamToString(GlobalLanguage.FuZiZhenEnterTip, new String(currGroupIdioms));
			} else {
				msg = GlobalLanguage.exChangeParamToString(GlobalLanguage.FuZiZhenIdiomsTip, currGroupCount + "", new String(currGroupIdioms));
			}
			currTips = new String(currGroupIdioms);
			for (SRole role : roles) {
				api.updateNpcTip(role, npcId, GlobalLanguage.FuZiZhenNpcTip + currTips);
			}
			sendMsgs(api, msg, roles);
		}
		return true;

	}

	@SuppressWarnings("unchecked")
	private char[] randomGetNextGroupIdioms(SInstance instance) {
		List<String> list = (ArrayList<String>) instance.getAttribute("idioms");
		int index = random.nextInt(list.size());
		char currGroupIdioms1[] = list.get(index).toCharArray();
		list.remove(index);
		return currGroupIdioms1;
	}

	private List<int[]> getPoints() {
		// 初始化 坐标点
		List<int[]> points = new ArrayList<int[]>();
		for (int i = 0; i < point.length; i++) {
			points.add(point[i]);
		}
		return points;
	}

	/**
	 * 随机获得一个坐标点
	 * 
	 * @param instance
	 * @return
	 */
	private int[] getRandomPoint(List<int[]> lpoints) {
		int index = random.nextInt(lpoints.size());
		int point[] = lpoints.get(index);
		lpoints.remove(index);
		return point;
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_InstanceMonsterDie;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SMonster monster
		SInstance instance = (SInstance) args[0];
		SMonster monster = (SMonster) args[1];

		if (monster.getSceneRef().getId() != 20175) {
			return;
		}
		int currGroupCount = (Integer) instance.getAttribute("currGroupCount");
		// monster.getGrade();
		// 最后一个boss
		if (currGroupCount > groupNum) {
			instance.removeAllAttribute();
			instance.missionComplete();
		} else {
			List<SMonster> monsters = (ArrayList<SMonster>) instance.getAttribute("monsters");
			monsters.remove(monster);
			if (monsters.size() == 0) {
				flushMonster(api, instance, monster.getSceneRef(), currGroupCount);
			}
		}
	}

}
