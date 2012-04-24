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
 * 两仪阵副本
 * 
 */
public class LiangYiZhen_SceneInit extends SuperInstance implements IEventListener {
	public int sceneId = 20176;
	private int groupNum = 5;// 一共5组怪物
	private Random random = new Random();

	// 30个坐标点
	private int point[][] = new int[][] { { 139, 94 }, { 69, 69 }, { 44, 70 }, { 111, 45 }, { 121, 52 }, { 126, 102 }, { 149, 67 }, { 111, 63 }, { 113, 80 }, { 136, 77 },
			{ 125, 68 }, { 132, 39 }, { 120, 32 }, { 107, 26 }, { 62, 92 }, { 56, 67 }, { 29, 48 }, { 46, 56 }, { 35, 62 }, { 53, 80 }, { 72, 98 }, { 83, 73 }, { 73, 83 },
			{ 63, 57 }, { 51, 43 }, { 40, 36 }, { 28, 27 }, { 46, 22 }, { 18, 88 }, };
	private int bossId = 12001;// 两仪阵主

	/**
	 * 刷怪
	 * 
	 * @param currGroupNum
	 *            当前第几组 怪物
	 */
	private boolean flushMonster(SApi api, SInstance instance, SScene scene, int currGroupCount) {
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return false;
		}
		Collection<SRole> roles;
		if (currGroupCount == 0) {
			roles = this.getTeamAllRole(roleCollection);
		} else {
			roles = instance.getInstanceAllCharacters();
		}

		currGroupCount++;
		instance.setAttribute("currGroupCount", currGroupCount);
		if (currGroupCount > groupNum) {
			sendMsgs(api, GlobalLanguage.LiangYiEnterTip3, roles);
			return false;
		}
		@SuppressWarnings("unchecked")
		List<SMonster> monsters = (ArrayList<SMonster>) instance.getAttribute("monsters");
		if (monsters == null) {
			monsters = new ArrayList<SMonster>();
			instance.setAttribute("monsters", monsters);
		}
		monsters.clear();

		List<int[]> points = getPoints();
		int rolesGrade = this.getRolesMaxGrate(roles);
		int grade = rolesGrade - 6 + currGroupCount;// 怪物等级=人物等级-6+怪物刷新波次
		int monsterId1 = 12000 + currGroupCount * 10 + 1;// 12000+波次*10+性别（男1，女2）;
		int monsterId2 = 12000 + currGroupCount * 10 + 2;// 12000+波次*10+性别（男1，女2）;
		for (int i = 0; i < 10; i++) {
			int[] p1 = getRandomPoint(points);
			int[] p2 = getRandomPoint(points);
			// 怪物生命，攻击，防御，爆击，闪避等值均读取monster数据库字段值
			SMonster sceneMonster1 = api.createMonsterToScene(monsterId1, false, p1[0], p1[1], "", scene, 0, (short) grade);
			SMonster sceneMonster2 = api.createMonsterToScene(monsterId2, false, p2[0], p2[1], "", scene, 0, (short) grade);

			sceneMonster1.setAttribute("originalAttack", sceneMonster1.getAttack());
			sceneMonster1.setAttribute("originalDefence", sceneMonster1.getDefence());
			sceneMonster2.setAttribute("originalAttack", sceneMonster2.getAttack());
			sceneMonster2.setAttribute("originalDefence", sceneMonster2.getDefence());
			monsters.add(sceneMonster1);
			monsters.add(sceneMonster2);
		}
		sendMsgs(api, GlobalLanguage.exChangeParamToString(GlobalLanguage.LiangYiEnterTip1, String.valueOf(currGroupCount)), roles);

		if (currGroupCount == 1) {
			// 进入副本后立即刷出第1波怪物，第一波怪物刷新时在屏幕中央弹出红字公告
			sendMsgs(api, GlobalLanguage.LiangYiEnterTip, roles);
			// BOSS等级 = 人物等级+5（如果组队进入则读入队伍中等级最高的玩家的等级）
			SMonster sceneMonster = api.createMonsterToScene(bossId, true, 94, 65, "", scene, 0, (short) (rolesGrade + 5));
			sceneMonster.changeAttack(rolesGrade * (rolesGrade * 2 - 20)); // BOSS攻击值=人物等级*(人物等级*3-20)
			sceneMonster.changeDefence(rolesGrade * (rolesGrade - 5));// BOSS防御值=人物等级*(人物等级-5)
			sceneMonster.changeDodge(rolesGrade * rolesGrade);// BOSS闪避值=人物等级*人物等级
			sceneMonster.changeCrt(rolesGrade * rolesGrade / 2);// BOSS爆击值=人物等级*人物等级/2
		}
		return true;
	}

	/**
	 * 初始化坐标点
	 * 
	 * @return
	 */
	private List<int[]> getPoints() {
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
		return IEventListener.Evt_InstanceSceneInit;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SInstance instance, SScene scene
		SInstance instance = (SInstance) args[0];
		SScene scene = (SScene) args[1];

		if (scene.getId() != sceneId) {
			return;
		}
		Collection<SRole> roleCollection = instance.getInstanceAllCharacters();
		if (roleCollection == null || roleCollection.size() == 0) {
			return;
		}

		// 刷 第一波 怪物
		flushMonster(api, instance, scene, 0);
	}
}
