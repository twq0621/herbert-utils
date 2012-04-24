package net.snake.gamemodel.monster.logic;

import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;

public class SceneMonsterHelper {
	/**
	 * 根据怪物模型动态产生一个怪物,并放到场景中去
	 * 
	 * @param modelid
	 * @param x
	 * @param y
	 * @param isrelive
	 * @param scene
	 * @return
	 */
	public static SceneMonster createMonsterToScene(final int modelid, final int x, final int y, final boolean isrelive, final Scene scene) {

		SceneMonster sceneMonster = new SceneMonster();
		sceneMonster.setScene(scene.getId());
		sceneMonster.setId(SceneMonster.getNewID());
		sceneMonster.setX((short) x);
		sceneMonster.setY((short) y);
		sceneMonster.setOriginalX((short) x);
		sceneMonster.setOriginalY((short) y);
		sceneMonster.setRelive(isrelive ? 1 : 0);
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(modelid);
		sceneMonster.setMonsterModel(monstermodel);

		// <result property="dodge" column="f_dodge" />
		// <result property="grade" column="f_grade"/>
		// <result property="crt" column="f_knowing" />
		// <result property="hit" column="f_tp" />
		// <result property="pa" column="f_pa" />
		// <result property="ma" column="f_ma" />
		// <result property="pd" column="f_pd" />
		// <result property="md" column="f_md" />
		sceneMonster.init();
		scene.enterScene(sceneMonster);
		return sceneMonster;
	}

	/**
	 * 
	 * @param modelid
	 * @param x
	 * @param y
	 * @param grade
	 * @param attack
	 * @param defence
	 * @param crt
	 * @param dodge
	 * @param atkColdtime
	 * @param moveSpeed
	 * @param exp
	 * @param isrelive
	 * @param scene
	 * @return
	 */
	public static SceneMonster createMonsterToScene(final int modelid, final int x, final int y, short grade, int attack, int defence, int crt, int dodge, int atkColdtime,
			int moveSpeed, int exp, final boolean isrelive, final Scene scene) {
		SceneMonster sceneMonster = new SceneMonster();
		sceneMonster.setScene(scene.getId());
		sceneMonster.setId(SceneMonster.getNewID());
		sceneMonster.setX((short) x);
		sceneMonster.setY((short) y);
		sceneMonster.setOriginalX((short) x);
		sceneMonster.setOriginalY((short) y);
		sceneMonster.setRelive(isrelive ? 1 : 0);
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(modelid);
		sceneMonster.setMonsterModel(monstermodel);
		sceneMonster.setGrade(grade);
		sceneMonster.init();
		sceneMonster.setGrade(grade);
		scene.enterScene(sceneMonster);

		sceneMonster.setAttack(attack);
		sceneMonster.setDefence(defence);
		sceneMonster.setCrt(crt);
		sceneMonster.setDodge(dodge);
		sceneMonster.setAtkColdtime(atkColdtime);
		sceneMonster.setMoveSpeed(moveSpeed);
		sceneMonster.setExp(exp);
		return sceneMonster;
	}

	/**
	 * 连斩副本调用此方法
	 * 
	 * @param modelid
	 * @param x
	 * @param y
	 * @param isrelive
	 * @param scene
	 * @return
	 */
	public static SceneMonster createMonsterToScene(int modelid, int x, int y, float defenceJiacheng, float expJiacheng, boolean isrelive, Scene scene) {
		SceneMonster sceneMonster = new SceneMonster();
		sceneMonster.setScene(scene.getId());
		sceneMonster.setId(SceneMonster.getNewID());
		sceneMonster.setX((short) x);
		sceneMonster.setY((short) y);
		sceneMonster.setOriginalX((short) x);
		sceneMonster.setOriginalY((short) y);
		sceneMonster.setRelive(isrelive ? 1 : 0);
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(modelid);
		sceneMonster.setMonsterModel(monstermodel);
		sceneMonster.setHit(monstermodel.getHit());
		// <result property="dodge" column="f_dodge" />
		// <result property="grade" column="f_grade"/>
		// <result property="crt" column="f_knowing" />
		// <result property="hit" column="f_tp" />
		// <result property="pa" column="f_pa" />
		// <result property="ma" column="f_ma" />
		// <result property="pd" column="f_pd" />
		// <result property="md" column="f_md" />
		sceneMonster.init();
		scene.enterScene(sceneMonster);
		int defence = (int) (monstermodel.getDefence() * defenceJiacheng);
		sceneMonster.setDefence(defence);
		int exp = (int) (monstermodel.getExper() * expJiacheng);
		sceneMonster.setExp(exp);
		return sceneMonster;
	}

	/**
	 * 连斩副本调用此方法 延迟加入场景
	 * 
	 * @param modelid
	 * @param x
	 * @param y
	 * @param isrelive
	 * @param scene
	 * @return
	 */
	public static SceneMonster createTimerMonsterToScene(int modelid, int x, int y, float defenceJiacheng, float expJiacheng, boolean isrelive, Scene scene, int reservationTime) {
		SceneMonster sceneMonster = new SceneMonster();
		sceneMonster.setScene(scene.getId());
		sceneMonster.setId(SceneMonster.getNewID());
		sceneMonster.setX((short) x);
		sceneMonster.setY((short) y);
		sceneMonster.setOriginalX((short) x);
		sceneMonster.setOriginalY((short) y);
		sceneMonster.setRelive(isrelive ? 1 : 0);
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(modelid);
		sceneMonster.setMonsterModel(monstermodel);
		sceneMonster.setHit(monstermodel.getHit());
		// <result property="dodge" column="f_dodge" />
		// <result property="grade" column="f_grade"/>
		// <result property="crt" column="f_knowing" />
		// <result property="hit" column="f_tp" />
		// <result property="pa" column="f_pa" />
		// <result property="ma" column="f_ma" />
		// <result property="pd" column="f_pd" />
		// <result property="md" column="f_md" />
		sceneMonster.init();
		sceneMonster.setReservationTime(System.currentTimeMillis() + reservationTime);
		scene.getRefreshMonsterController().addSceneMonster(sceneMonster);
		// scene.enterScene(sceneMonster);
		int defence = (int) (monstermodel.getDefence() * defenceJiacheng);
		sceneMonster.setInstanceDefence(defence);
		int exp = (int) (monstermodel.getExper() * expJiacheng);
		sceneMonster.setInstanceExp(exp);
		return sceneMonster;
	}

	/**
	 * 创建指定名字的怪物指定坐标点 并指定延时多长时间后 添加到场景
	 * 
	 * @param modelid
	 * @param name
	 * @param scene
	 * @param reservationTime
	 * @return
	 */
	public static SceneMonster createTimerMonsterToScene(int modelid, boolean isRelive, int x, int y, String name, Scene scene, int reservationTime, short grade) {
		// int width=scene.getWidth();
		// int height=scene.getHeight();
		// int centerx=(width/2);
		// int centery=(height/2);
		// short [] point=scene.getRandomPoint(centerx, centery, centerx>centery?centerx:centery);
		SceneMonster sceneMonster = new SceneMonster(false);
		sceneMonster.setScene(scene.getId());
		sceneMonster.setId(SceneMonster.getNewID());
		sceneMonster.setX((short) x);
		sceneMonster.setY((short) y);
		sceneMonster.setOriginalX((short) x);
		sceneMonster.setOriginalY((short) y);
		sceneMonster.setRelive(isRelive ? 1 : 0);
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(modelid);
		sceneMonster.setMonsterModel(monstermodel);
		sceneMonster.setReplaceName(name);
		sceneMonster.init();
		sceneMonster.setGrade(grade);
		sceneMonster.setReservationTime(System.currentTimeMillis() + reservationTime);
		scene.getRefreshMonsterController().addSceneMonster(sceneMonster);
		return sceneMonster;
	}
}
