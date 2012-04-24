package net.snake.script;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.MonsterSpeak10298;
import net.snake.commons.GenerateProbability;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SInstance;
import net.snake.commons.script.SMonster;
import net.snake.commons.script.SRole;
import net.snake.commons.script.SScene;
import net.snake.commons.script.STeleport;
import net.snake.consts.ClientConfig;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.EffectType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.dujie.response.StartCountdownResp;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.response.GoodScripToBadMsg53052;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.Teleport;
import net.snake.gamemodel.map.response.TeleportUpdateName10340;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.MonsterSkillManager;
import net.snake.gamemodel.monster.logic.SceneMonsterHelper;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.skill.bean.DynamicSkillEffect;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.SkillManager;

import org.apache.log4j.Logger;

public class ScriptApiImpl implements SApi {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScriptApiImpl.class);

	@Override
	public void transTo(SRole role, int sceneid, int x, int y) {
		try {
			ExchangeMapTrans.trans(sceneid, (short) x, (short) y, (Hero) role);
		} catch (Exception e) {
		}
	}

	public void transToInstanceScene(SRole role, SInstance instance, int sceneid, int x, int y) {
		try {
			if (instance == null) {
				return;
			}
			InstanceController instanceC = (InstanceController) instance;
			Hero hero = (Hero) role;
			Scene scene = hero.getVlineserver().getSceneManager().getInstanceScene(sceneid);
			if (x == 0 && y == 0) {
				x = scene.getEnterX();
				y = scene.getEnterY();
			}
			instanceC.transToInstanceScene(sceneid, (short) x, (short) y, (Hero) role);
		} catch (Exception e) {
		}
	}

	private SMonster createMonsterAroundPoint(SScene sceneid, int x, int y, int radius, int monstermodelid, boolean relive) {
		Scene scene = (Scene) sceneid;
		short[] newpoint = scene.getRandomPoint((short) x, (short) y, radius);
		return SceneMonsterHelper.createMonsterToScene(monstermodelid, newpoint[0], newpoint[1], relive, (Scene) sceneid);
	}

	@Override
	public SMonster createMonsterAroundPoint(SScene scene, int x, int y, int radius, int monstermodelid, boolean relive, int lifetime) {
		SceneMonster monster = (SceneMonster) createMonsterAroundPoint(scene, x, y, radius, monstermodelid, relive);
		monster.setInitiativeAttack(false);
		monster.setLifetime(lifetime);

		// final SScene scene2 = scene;
		// final int _x = x;
		// final int _y = y;
		// final SceneMonster _monster = monster;
		// if (monstermodelid == 21101) {
		// GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun()
		// {
		// @Override
		// public void run(Character character) {
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST,
		// 20125,"在地图{"+scene2.getName()+"} 位置{"+_x + "," + _y+"} 刷出怪物:{" +
		// _monster.getMonsterModel().getName()+"} "));
		// }
		// });
		// logger.debug("在地图{} 位置{} 刷出怪物:{} 生命：{}毫秒",new
		// Object[]{scene.getName(),x + "," + y,
		// monster.getMonsterModel().getName(),lifetime});
		// }
		return monster;
	}

	@Override
	public SMonster createMonsterAroundPoint(SScene scene, int x, int y, int radius, int monstermodelid, boolean relive, boolean initiativeAttack, int pursuitScope, int lifetime) {
		SceneMonster monster = (SceneMonster) createMonsterAroundPoint(scene, x, y, radius, monstermodelid, relive);
		monster.setPursuitScope(pursuitScope);
		monster.setInitiativeAttack(initiativeAttack);
		monster.setLifetime(lifetime);
		return monster;
	}

	@Override
	public void restoreMonsterHp(SMonster monster, int hp) {
		if (monster == null)
			return;
		if (hp == 0)
			return;
		SceneMonster sceneMonster = (SceneMonster) monster;
		sceneMonster.onlychangeHpValue(hp);
		FightMsgSender.directHurtBroadCase(null, sceneMonster, 0, CommonUseNumber.byte0);
	}

	@Override
	public void restoreMonsterHpPercent(SMonster monster, int hp) {
		if (monster == null)
			return;
		if (hp == 0)
			return;
		SceneMonster sceneMonster = (SceneMonster) monster;
		sceneMonster.onlychangeHpValue((int) (sceneMonster.getMaxHp() * hp * 0.01));
		FightMsgSender.directHurtBroadCase(null, sceneMonster, 0, CommonUseNumber.byte0);
	}

	@Override
	public void addMonsterAttack(SMonster monster, int addAttackValue, int duration) {
		if (monster == null)
			return;
		SceneMonster sceneMonster = (SceneMonster) monster;
		addEffectInfo(addAttackValue, duration, sceneMonster, EffectType.attack);
	}

	@Override
	public void addMonsterDefence(SMonster monster, int addDefenceValue, int duration) {
		if (monster == null)
			return;
		SceneMonster sceneMonster = (SceneMonster) monster;
		addEffectInfo(addDefenceValue, duration, sceneMonster, EffectType.defence);
	}

	@Override
	public void addMonsterDogde(SMonster monster, int addDogdeValue, int duration) {
		if (monster == null)
			return;
		SceneMonster sceneMonster = (SceneMonster) monster;
		addEffectInfo(addDogdeValue, duration, sceneMonster, EffectType.dodge);
	}

	/**
	 * 触发一个buff效果
	 * 
	 * @param addAttackValue
	 *            效果值
	 * @param duration
	 *            持续时间
	 * @param sceneMonster
	 * @param effectType
	 *            类型
	 */
	private void addEffectInfo(int addValue, int duration, SceneMonster sceneMonster, int effectType) {
		DynamicSkillEffect skillEffect = new DynamicSkillEffect();
		skillEffect.setType((short) effectType);
		skillEffect.setIsDieClean((short) 1);
		skillEffect.setEffectRepeatOption((short) 3);
		skillEffect.setBuffFlag((short) 0);
		skillEffect.setHurtValue(addValue);
		EffectInfo effectInfo = new EffectInfo(skillEffect);
		effectInfo.setBufValue(addValue);
		effectInfo.setDuration(duration);
		effectInfo.setAffecter(sceneMonster);
		effectInfo.setAttacker(sceneMonster);

		try {
			sceneMonster.getEffectController().addEffect(effectInfo);
			// FightMsgSender.sendEffectResponse_only(sceneMonster,
			// sceneMonster,
			// 0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void setMonsterSkill(SMonster monster, int skillid, int skillgrade, int gaiLv, int usedCnt) {

		if (SkillManager.getInstance().getSkillById(skillid) == null) {
			//			logger.error("setMonsterSkill(SMonster, int, int, int, int) - 不存在的技能:"+ skillid); //$NON-NLS-1$
			return;
		}

		SceneMonster sceneMonster = (SceneMonster) monster;
		CharacterSkill characterSkill = sceneMonster.getSkillManager().getCharacterSkillById(skillid);
		if (characterSkill == null) {
			characterSkill = new CharacterSkill(sceneMonster, sceneMonster);
			characterSkill.setSkillId(skillid);
			characterSkill.setGrade(skillgrade);
			characterSkill.setTriggerProbability(gaiLv);
			characterSkill.setMaxUsedCnt(usedCnt);
		}
		((MonsterSkillManager) sceneMonster.getSkillManager()).addTopChoiceSkill(characterSkill);
	}

	@Override
	public void monsterSay(SMonster monster, String content) {
		SceneMonster sceneMonster = (SceneMonster) monster;
		sceneMonster.getEyeShotManager().sendMsg(new MonsterSpeak10298(sceneMonster.getId(), content));
	}

	@Override
	public void monsterAttackRoleIgnoreEnmity(SMonster monster, SRole role, int duration) {
		SceneMonster sceneMonster = (SceneMonster) monster;
		sceneMonster.getEnmityManager().setTargetIgnoreEnmity((VisibleObject) role, duration);

	}

	@Override
	public void monsterAttackRandomRoleIgnoreEnmity(SMonster monster, int duration) {
		SceneMonster sceneMonster = (SceneMonster) monster;
		VisibleObject vo = (VisibleObject) GenerateProbability.randomItem(sceneMonster.getEnmityManager().getEnmityList());
		if (vo != null) {
			sceneMonster.getEnmityManager().setTargetIgnoreEnmity(vo, duration);
		}

	}

	public void sendMsg(SRole role, byte type, String msg) {
		Hero character = (Hero) role;
		if (msg != null) {
			character.sendMsg(new PrompMsg(type, 982, msg));
		}
	}

	@Override
	public boolean openHideTeleport(SScene scene) {
		Scene tempScene = (Scene) scene;
		return tempScene.openHideTeleport();

	}

	@Override
	public boolean closeTeleport(SScene scene) {
		Scene tempScene = (Scene) scene;
		return tempScene.closeTeleport();
	}

	@Override
	public SMonster createMonsterToScene(final int modelid, final int x, final int y, short grade, int attack, int defence, int crt, int dodge, int atkColdtime, int moveSpeed,
			int exp, final boolean isrelive, final SScene scene) {
		SMonster monster = SceneMonsterHelper.createMonsterToScene(modelid, x, y, grade, attack, defence, crt, dodge, atkColdtime, moveSpeed, exp, isrelive, (Scene) scene);
		return monster;
	}

	@Override
	public void changeInstanceBossflushCount(SInstance instance, int count) {
		InstanceController inc = (InstanceController) instance;
		inc.changeBossflushCount(count);
	}

	@Override
	public void changeInstanceMonsterflushCount(SInstance instance, int count) {
		InstanceController inc = (InstanceController) instance;
		inc.changeMonsterflushCount(count);
	}

	@Override
	public void changeInstanceSceneMonsterflushCount(SMonster monster, int count) {
		SceneMonster sm = (SceneMonster) monster;
		sm.setInstanceLianzhanGrade(count);
	}

	@Override
	public SMonster createMonsterToScene(int modelid, int x, int y, int radius, float defenceJiacheng, float expJiacheng, boolean isrelive, SScene scene) {
		Scene scenet = (Scene) scene;
		short[] newpoint = scenet.getRandomPoint((short) x, (short) y, radius);
		SMonster monster = SceneMonsterHelper.createMonsterToScene(modelid, newpoint[0], newpoint[1], defenceJiacheng, expJiacheng, isrelive, scenet);
		return monster;
	}

	public void addMonsterSkill(SMonster monster, int skillId, int probability) {
		SceneMonster sceneMonster = (SceneMonster) monster;
		CharacterSkill skill = sceneMonster.getMonsterSkillManager().addSkill(skillId, sceneMonster.getGrade());
		skill.setTriggerProbability(probability);
	}

	@Override
	public SMonster createTimerMonster(int modelid, int x, int y, int radius, float defenceJiacheng, float expJiacheng, boolean isrelive, SScene scene, int yachiTime) {
		Scene scenet = (Scene) scene;
		short[] newpoint = scenet.getRandomPoint((short) x, (short) y, radius);
		SMonster monster = SceneMonsterHelper.createTimerMonsterToScene(modelid, newpoint[0], newpoint[1], defenceJiacheng, expJiacheng, isrelive, scenet, yachiTime);
		return monster;
	}

	public void initInstanceSceneMonster(SScene scene) {
		Scene scenet = (Scene) scene;
		scenet.initInstanceSceneMonster();
	}

	public void initInstanceSceneMonster(SScene scene, int dropJiacheng, int shuxingJiacheng) {
		Scene scenet = (Scene) scene;
		scenet.initInstanceSceneMonster(dropJiacheng, shuxingJiacheng);
	}

	@Override
	public void setInstanceflushGrade(SRole role, int instanceflushGrade) {
		Hero character = (Hero) role;
		character.setInstanceflushGrade(instanceflushGrade);
	}

	@Override
	public void setInstanceEnterMsg(SRole role, String msg, boolean isAbleEnter) {
		Hero character = (Hero) role;
		character.getMyCharacterInstanceManager().changeInstanceEnterInfo(msg, isAbleEnter);
	}

	@Override
	public int getSRoleBadGoodCountByGoodId(SRole role, int goodId) {
		Hero character = (Hero) role;
		return character.getCharacterGoodController().getBagGoodsCountByModelID(goodId);
	}

	@Override
	public boolean deleteSRoleBadGoodCountByGoodId(SRole role, int goodId, int count) {
		Hero character = (Hero) role;
		return character.getCharacterGoodController().getBagGoodsContiner().deleteGoodsByModel(goodId, count);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean removeAndAddGoods(SRole role, Collection removepostions, Collection addlist) {
		Hero character = (Hero) role;
		return character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(removepostions, addlist);
	}

	@Override
	public SGoods createCharacterGood(int goodId, int count, byte bind) {
		CharacterGoods cg = CharacterGoods.createCharacterGood(goodId, count, bind);
		return cg;
	}

	@Override
	public SGoods createCharacterGood(int goodId, int count, int loop, int bind, Date date) {
		CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, loop);
		if (cg == null) {
			return null;
		}
		cg.setBind((byte) bind);
		if (date != null) {
			cg.setLastDate(date);
		}
		return cg;
	}

	public void sendScripGoodToBadMsg(SRole role, int goodId) {
		Hero character = (Hero) role;
		character.sendMsg(new GoodScripToBadMsg53052(goodId));
	}

	@Override
	public boolean addCharacterGoods(SRole role, SGoods good) {
		Hero character = (Hero) role;
		return character.getCharacterGoodController().getBagGoodsContiner().addGoods((CharacterGoods) good);
	}

	@Override
	public List<Integer> getEquiteCollectionByGradeAndMenpai(int grade, byte menpai) {
		return GoodmodelManager.getInstance().getEquiteCollectionByGradeAndMenpai(grade, menpai);
	}

	public List<Integer> getHorseEquiteCollectionByGradeAndMenpai(int grade) {
		return GoodmodelManager.getInstance().getHorseEquiteCollectionByGradeAndMenpai(grade);
	}

	@Override
	public SGoods getNewSGoods(SGoods good, int count) {
		CharacterGoods cg = (CharacterGoods) good;
		try {
			CharacterGoods temp = (CharacterGoods) cg.clone();
			// temp.setPosition((short)0);
			temp.setCount(count);
			return temp;
		} catch (CloneNotSupportedException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void finishiInstance(SInstance instance) {
		InstanceController instanceC = (InstanceController) instance;
		instanceC.finishiInstance();
	}

	@Override
	public SMonster createMonsterToScene(int modelid, boolean isRelive, int x, int y, String name, SScene scene, int delaySecond, short grate) {

		return SceneMonsterHelper.createTimerMonsterToScene(modelid, isRelive, x, y, name, (Scene) scene, delaySecond, grate);
	}

	@Override
	public void characterCount(SRole role, int type, int amount) {
		Hero character = (Hero) role;
		character.getCharacterCountController().count(type, amount);
	}

	public void sendMsgToAll(byte position, int msgkey, String... vars) {
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(position, msgkey, vars));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.script.api.SApi#getMonsterNameById()
	 */
	@Override
	public String getMonsterNameById(int monsterId) {
		MonsterModel mm = MonsterModelManager.getInstance().getFromCache(monsterId);
		if (mm == null) {
			return "";
		}
		return mm.getNameI18n();
	}

	@Override
	public void updateTeleportName(SRole sRole, final SScene scene, final STeleport sTeleport, final String name) {
		Teleport teleport = (Teleport) sTeleport;
		if (teleport.getTargetSceneID() == ClientConfig.Scene_Xianjing) {
			return;
		}
		((VisibleObject) sRole).sendMsg(new TeleportUpdateName10340(scene.getId(), sTeleport.getId(), name));
	}

	@Override
	public void finishiInstance(SInstance instance, SRole role) {
		InstanceController instanceC = (InstanceController) instance;
		instanceC.finishiInstance(role);
	}

	@Override
	public void updateNpcTip(SRole role, int npcId, String str) {
		// ((VisibleObject) role).sendMsg(new NpcUpdateTip52060(npcId, str));
	}

	@Override
	public void backWorld(SRole hero) {
		Hero hero2 = (Hero) hero;
		hero2.getMyCharacterInstanceManager().exitInstance2World(hero2.worldPos[0], (short) hero2.worldPos[1], (short) hero2.worldPos[2]);
	}

	@Override
	public SScene getScene(SRole role, int sceneId) {
		Hero hero = (Hero) role;
		return hero.getVlineserver().getSceneManager().getScene(sceneId);
	}

	@Override
	public void sendCountDownMsg(SRole role, int ms, boolean dujie) {
		StartCountdownResp countdownResp = new StartCountdownResp(ms, dujie ? 1 : 0);
		((Hero) role).sendMsg(countdownResp);
	}

	@Override
	public void sendCountDownMsg(Collection<SRole> roles, int ms, boolean dujie) {
		StartCountdownResp countdownResp = new StartCountdownResp(ms, dujie ? 1 : 0);
		Iterator<SRole> iterator = roles.iterator();
		while (iterator.hasNext()) {
			SRole sRole = iterator.next();
			((Hero) sRole).sendMsg(countdownResp);
		}
	}

	@Override
	public void sendDujieEndMsg(SRole role, boolean suc) {
		int num = ((Hero) role).getDujieCtrlor().currentTianjieNum();
		int need = DujieDataTbl.getInstance().getDujie(num).getZhenyuan();
		((Hero) role).getDujieCtrlor().getHeroDujieData().setProcess(need);

		InstanceController controller = ((Hero) role).getMyCharacterInstanceManager().getInstanceController();
		controller.getPlugin().onCompleted(controller, (Hero) role);
	}

	@Override
	public void clearSceneMonster(SScene scene) {
		Scene scene2 = (Scene) scene;
		scene2.clearScene();
	}

}
