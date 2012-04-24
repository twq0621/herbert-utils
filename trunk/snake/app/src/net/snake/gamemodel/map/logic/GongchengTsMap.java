package net.snake.gamemodel.map.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.CharacterFuhuoDelayMsg20078;
import net.snake.ai.formula.DistanceFormula;
import net.snake.commons.GenerateProbability;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.logic.MyFactionManager;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.faction.persistence.GongchengDateManager;
import net.snake.gamemodel.faction.persistence.MyFactionCityZhengDuoManager;
import net.snake.gamemodel.faction.response.factioncity.FactionCtMsg51130;
import net.snake.gamemodel.fight.bean.GongchengVehicle;
import net.snake.gamemodel.fight.persistence.GongchengVehicleManager;
import net.snake.gamemodel.fight.response.CatchYoulongOK51126;
import net.snake.gamemodel.fight.response.PkModelChanageMsg;
import net.snake.gamemodel.fight.response.VehicleMsg10046;
import net.snake.gamemodel.fight.response.VehicleMsg51122;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.DynamicUpdateObjManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneFactionCtMonster;
import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 帮战地图
 * 
 * @author serv_dev
 */
public class GongchengTsMap extends GameMap {
	private static final Logger logger = Logger.getLogger(GongchengTsMap.class);
	/** false 表示没有处于攻城时间内 true表示处于攻城状态 */
	public boolean isGongchengState = false; //
	/** 表示是否处于正在攻城中 */
	public static boolean isGongchenging = false; //
	public int factionOutSceneId = ClientConfig.Scene_Xianjing;
	public static int youlongzhirenId = 21000;
	public static short[] monsterPoint = { 42, 15 };
	/** 持有游龙时间胜利 */
	public static int catchYOULONGTime = 20;//
	public static int successPresite = 200;
	public static int failPresite = 100;
	public static int successExp = 8000000;
	public static int failExp = 5000000;
	private long tenMinuteGainShengwang = 0l;
	private DynamicUpdateObjManager updateObjManager = new DynamicUpdateObjManager();
	public SceneXuanyuanMonster[] xuanyuanjian = new SceneXuanyuanMonster[4];

	@Override
	public void enterScene(Hero player) {
		if (!isGongchenging || player.getVlineserver().getLineid() != FactionCityManager.gongchengLine) {
			Scene scene = player.getVlineserver().getSceneManager().getScene(factionOutSceneId);
			ExchangeMapTrans.trans(scene, scene.getReliveX(), scene.getReliveY(), player);
			// 传送进入藏龙潭地图时自动将玩家的PK模式切换为帮派模式

			return;
		}
		player.getMyFactionCityZhengDuoManager().enterFactionCtSceneInfoInit();
		super.enterScene(player);
		// if (player.isWudiBuffer) {
		// player.setWudiBuffer(false);
		player.getEffectController().addUnWithstandBuff(0);
		// }
		// player.sendMsg(new FactionCityChangeLineMsg51134(
		// FactionCityManager.gongchengLine));
		if (player.getMyFactionManager().isFaction()) {
			player.getFightController().setPkModel((int) 2);
			PkModelChanageMsg msg = new PkModelChanageMsg(2);
			player.sendMsg(msg);
		}
	}

	@Override
	public void leaveScene(Hero character, Scene newScene) {
		character.getMyFactionCityZhengDuoManager().leaveFactionCtSceneClearInfo();
		super.leaveScene(character, null);
	}

	@Override
	public void update(long now) {
		try {
			if (this.getLineId() != FactionCityManager.gongchengLine) {
				return;
			}
			super.update(now);
			int hourse = getTodayHourse();
			if (!isGongchengState) {
				if (hourse == FactionCityManager.startHorse - 1) {
					GongchengDateManager.getInstance().sendGongchengMsg(this, hourse);
				}
				if (hourse == FactionCityManager.startHorse) {
					GongchengDateManager.getInstance().startGongchengInit(this, hourse);
					isGongchengState = true;
				}
				return;
			}
			try {
				updateObjManager.update(System.currentTimeMillis());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			try {
				youlongCatchUpdate();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			if (hourse == FactionCityManager.endHorse) {
				endGongcheng();
				Collection<Hero> collection = this.getAllCharacters();
				for (Hero role : collection) {
					exchargeToXiangyang(role);
				}
				isGongchengState = false;
				return;
			}

			boolean gainshengwang = false;
			if (isGongchenging && (now - tenMinuteGainShengwang > 10 * 1000 * 60)) {
				gainshengwang = true;
				tenMinuteGainShengwang = now;
			}
			Collection<Hero> collection = this.getAllCharacters();
			for (Hero role : collection) {
				try {
					role.getMyFactionCityZhengDuoManager().update();
					if (gainshengwang) {
						role.getMyFactionCityZhengDuoManager().gainShengWangByTenMinute();
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 玩家攻城开始引导
	 */
	public void sendGongchengMsgToAllRole() {
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.getMyNewcomeManager().sendGongchengZhanActivityMsg();
			}
		});
	}

	/**
	 * 检查更新持有游龙之剑者
	 */
	private void youlongCatchUpdate() {
		if (MyFactionCityZhengDuoManager.monster == null) {
			return;
		}
		Hero character = MyFactionCityZhengDuoManager.monster.getYoulongCharacter();
		if (character == null) {
			MyFactionCityZhengDuoManager.monster = null;
			return;
		}
		long time = MyFactionCityZhengDuoManager.monster.getStartCatchYoulongTime();
		if (System.currentTimeMillis() - time > catchYOULONGTime * 60 * 1000) {
			gongchengSuccess(character);
			MyFactionCityZhengDuoManager.monster = null;
			endGongcheng();
			return;
		}
		if (System.currentTimeMillis() - time > (catchYOULONGTime - 3) * 60 * 1000) {
			MyFactionCityZhengDuoManager.monster.updateCacthTimeTishi();
		}
	}

	/**
	 * 攻城胜利方信息初始化以及消息广播
	 */
	private void gongchengSuccess(Hero character) {
		character.getMyFactionManager().changXiangYangChengzhu(character);
		if (isGongchenging) {
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14523, character.getMyFactionManager().getFactionName(), character
					.getViewName()));
		} else {
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14552, character.getMyFactionManager().getFactionName(), character
					.getViewName()));
		}
	}

	public void exchargeToXiangyang(Hero character) {
		Scene newScene = character.getVlineserver().getSceneManager().getScene(factionOutSceneId);
		short[] point = newScene.getRandomPoint(newScene.getReliveX(), newScene.getReliveY(), 7);
		ExchangeMapTrans.trans(newScene, point[0], point[1], character);
	}

	public boolean isGongchengTime() {
		int horse = getTodayHourse();
		if (horse > 9 || horse < 8) {
			return false;
		} else {
			return true;
		}
	}

	public int getTodayHourse() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		return hours;
	}

	@Override
	public boolean dieAffterRelive(Hero character, boolean isGuard) {
		if (isGuard) {
			try {
				/**
				 * BUFF作用：令自己的配偶获得一个BUFF，该BUFF的作用是当配偶血量变为0时，
				 * 立即原地复活（不读倒计时，倒地后立即站起），恢复一定的血量值，免除死亡惩罚一次，并获得3秒钟的无敌时间。
				 * 重生后的血量值=夫妻之间的友好度（最低为1000，最大为10000）
				 */
				int minHp = 1000;
				int maxHp = 10000;
				int recoverHp = character.getMyFriendManager().getRoleWedingManager().getPeiouFavor();
				if (recoverHp < minHp) {
					recoverHp = minHp;
				} else if (recoverHp > maxHp) {
					recoverHp = maxHp;
				}
				CharacterResurrect.yuandiFreeResurrectProcess(character, recoverHp);
				character.setObjectState(VisibleObjectState.Idle);
				character.getEffectController().addUnWithstandBuff(3000);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return true;
		}
		// character.getMyFactionCityZhengDuoManager().dropYoulongToScene(null);
		character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
		if (MyFactionCityZhengDuoManager.monster == null) {
			gongFangRelive(character);
			return false;
		}
		Hero youlonger = MyFactionCityZhengDuoManager.monster.getYoulongCharacter();
		if (youlonger == null) {
			gongFangRelive(character);
			return false;
		}
		MyFactionManager factionManger = character.getMyFactionManager();
		if (!factionManger.isFaction()) {
			gongFangRelive(character);
			return false;
		}
		int factionId = youlonger.getMyFactionManager().getFactionId();
		if (factionId != factionManger.getFactionId()) {
			gongFangRelive(character);
			return false;
		}
		showFangRelive(character);
		return false;
	}

	/**
	 * 守方死亡复活
	 * 
	 * @param character
	 */
	private void showFangRelive(final Hero character) {
		// character.getMyFactionCityZhengDuoManager().leaveFactionCtSceneClearInfo();
		if (isInShowRandomPoint(character)) {
			huichenfuhuo(character);
		} else {
			short[] point = this.getRandomShowPoint();
			sameSceneHuichengFuhuo(character, point);

		}
	}

	/**
	 * 获得守方 复活点
	 * 
	 * @return
	 */
	public boolean isInShowRandomPoint(Hero character) {
		short[] relive = FactionCityManager.shouRelive;
		float xCha = relive[0] - relive[2];
		float yCha = relive[1] - relive[3];
		float xielv = xCha / yCha;
		int c = (int) (xielv * relive[0] - relive[1]);
		int nx = character.getX();
		if (nx < relive[0] || nx > relive[2]) {
			return false;
		}
		int minY = relive[1];
		int maxY = (int) (nx * xielv) - c;
		if (nx < minY || nx > maxY) {
			return false;
		}
		return true;
	}

	/**
	 * 获得守方 复活点
	 * 
	 * @return
	 */
	public short[] getRandomShowPoint() {
		short[] relive = FactionCityManager.shouRelive;
		float xCha = relive[0] - relive[2];
		float yCha = relive[1] - relive[3];
		float xielv = xCha / yCha;
		// { 66, 0,158,58 };
		int c = (int) (relive[0] / xielv - relive[1]);
		for (int n = 0; n < 15; n++) {
			int nx = GenerateProbability.randomIntValue(relive[0] + 1, relive[2] - 1);
			int minY = relive[1];
			int maxY = (int) (nx / xielv) - c;
			short[] point = new short[2];
			for (int i = 0; i < 20; i++) {
				int ny = GenerateProbability.randomIntValue(minY, maxY);
				if (isPermitted((short) nx, (short) ny)) {
					point[0] = (short) nx;
					point[1] = (short) ny;
					return point;
				}
			}
		}
		return FactionCityManager.shoudefalseRelive;
	}

	/**
	 * 获取攻防区域复活点
	 * 
	 * @return
	 */
	public boolean isInGongRandomPoint(Hero character) {

		short[] relive = FactionCityManager.gongRelive;
		float xCha = relive[0] - relive[2];
		float yCha = relive[1] - relive[3];
		float xielv = xCha / yCha;
		int nx = character.getX();
		int c = (int) (relive[0] / xielv - relive[1]);
		if (nx < relive[0] || nx > relive[2]) {
			return false;
		}
		int minY = (int) (nx / xielv) - c;
		int maxY = (int) (relive[3] - 1);
		if (character.getY() < minY || character.getY() > maxY) {
			return false;
		}
		return true;
	}

	/**
	 * 获取攻防区域复活点
	 * 
	 * @return
	 */
	public short[] getRandomGongPoint() {

		short[] relive = FactionCityManager.gongRelive;
		float xCha = relive[0] - relive[2];
		float yCha = relive[1] - relive[3];
		float xielv = xCha / yCha;
		// 0,102,92,154
		int c = (int) (xielv * relive[0] - relive[1]);
		for (int n = 0; n < 15; n++) {
			int nx = GenerateProbability.randomIntValue(relive[0] + 1, relive[2] - 1);
			int minY = (int) (nx * xielv - c);
			int maxY = (int) (relive[3] - 1);
			short[] point = new short[2];
			for (int i = 0; i < 20; i++) {
				int ny = GenerateProbability.randomIntValue(minY, maxY);
				if (isPermitted((short) nx, (short) ny)) {
					point[0] = (short) nx;
					point[1] = (short) ny;
					return point;
				}
			}
		}
		return FactionCityManager.gongdefalseRelive;
	}

	/**
	 * 攻防死亡复活
	 * 
	 * @param character
	 */
	private void gongFangRelive(final Hero character) {
		character.getMyFactionCityZhengDuoManager().leaveFactionCtSceneClearInfo();
		if (this.isInGongRandomPoint(character)) {
			huichenfuhuo(character);
		} else {
			short[] point = this.getRandomGongPoint();
			sameSceneHuichengFuhuo(character, point);
		}
	}

	/**
	 * 同地图复活
	 * 
	 * @param character
	 * @param relive
	 */
	private void sameSceneHuichengFuhuo(final Hero character, final short[] relive) {
		try {
			final Scene scene = this;
			character.getEyeShotManager().sendMsg(new CharacterFuhuoDelayMsg20078(character.getId()));
			character.setWudiBuffer(true);
			character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
				@Override
				public void run() {
					try {
						CharacterResurrect.fuhuo(character);
						ExchangeMapTrans.trans(scene, relive[0], relive[1], character);
						character.getDiedata().clear();
						character.setObjectState(VisibleObjectState.Idle);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}, (Options.Relive_Timeout + 1) * 1000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 襄阳城复活
	 * 
	 * @param character
	 */
	private void huichenfuhuo(final Hero character) {
		character.getEyeShotManager().sendMsg(new CharacterFuhuoDelayMsg20078(character.getId()));
		character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				try {
					CharacterResurrect.huichengFuhuo(character);
					character.setObjectState(VisibleObjectState.Idle);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}, (Options.Relive_Timeout + 1) * 1000);
	}

	public int getFactionOutSceneId() {
		return factionOutSceneId;
	}

	public void setFactionOutSceneId(int factionOutSceneId) {
		this.factionOutSceneId = factionOutSceneId;
	}

	@SuppressWarnings("unchecked")
	public void initGongchengTsMap() {
		Collection<SceneMonster> collection = this.getAllCurrentSceneMonster();
		for (SceneMonster temp : collection) {
			this.leaveScene(temp);
		}
		Collection<SceneFactionCtMonster> SceneFactionCtMonstercollection = this.getSceneObjByClass(SceneObj.SceneObjType_MonsterFactionCt);
		for (SceneMonster monster : SceneFactionCtMonstercollection) {
			this.leaveScene(monster);
		}
		SceneFactionCtMonster sceneMonster = new SceneFactionCtMonster();
		sceneMonster.setScene(this.getId());
		sceneMonster.setId(SceneMonster.getNewID());
		sceneMonster.setX(monsterPoint[0]);
		sceneMonster.setY(monsterPoint[1]);
		sceneMonster.setOriginalX(monsterPoint[0]);
		sceneMonster.setOriginalY(monsterPoint[1]);
		sceneMonster.setRelive(0);
		MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(youlongzhirenId);
		sceneMonster.setMonsterModel(monstermodel);
		sceneMonster.init();
		this.enterScene(sceneMonster);
		tenMinuteGainShengwang = System.currentTimeMillis();
	}

	public void startGongcheng() {
		if (isGongchenging) {
			return;
		}
		isGongchenging = true;
		initGongchengTsMap();
		GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51130());
		sendGongchengMsgToAllRole();
	}

	/**
	 * 攻城结束
	 */
	@SuppressWarnings("unchecked")
	public void endGongcheng() {
		if (!isGongchenging) {
			return;
		}
		isGongchenging = false;
		if (MyFactionCityZhengDuoManager.monster != null) {
			Hero character = MyFactionCityZhengDuoManager.monster.getYoulongCharacter();
			if (character != null) {
				MyFactionCityZhengDuoManager.monster = null;
				gongchengSuccess(character);
			}
		}
		MyFactionCityZhengDuoManager.monster = null;
		GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51130());
		Collection<SceneMonster> collection = this.getAllCurrentSceneMonster();
		for (SceneMonster monster : collection) {
			this.leaveScene(monster);
		}
		Collection<SceneFactionCtMonster> SceneFactionCtMonstercollection = this.getSceneObjByClass(SceneObj.SceneObjType_MonsterFactionCt);
		for (SceneMonster monster : SceneFactionCtMonstercollection) {
			this.leaveScene(monster);
		}
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14524));
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14550));
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		final int ydFactionId = factionCity.getYdFactionId();
		final int nowFactionId = factionCity.getFactionId();
		GameServer.executorService.schedule(new Runnable() {
			@Override
			public void run() {
				GongchengDateManager.getInstance().clearTodayGongchengDateAndSharaFactionCtShouyi();
				if (ydFactionId > 0) {
					FactionController factionController = FactionManager.getInstance().getFactionControllerByFactionID(ydFactionId);
					if (factionController != null) {
						if (ydFactionId == nowFactionId) {
							factionController.sharafactionCtShouyi(successPresite, successExp);
						} else {
							factionController.sharafactionCtShouyi(failPresite, failExp);
						}
					}
				}

			}
		}, 60, TimeUnit.SECONDS);
		factionCity.setYdFactionId(0);
	}

	/**
	 * 某帮会帮主成功不出游龙之刃
	 * 
	 * @param scenemonster
	 * @param character
	 */
	public void catchYoulongSucess(SceneFactionCtMonster scenemonster, Hero character) {
		this.leaveScene(scenemonster);
		scenemonster.catchyoulong(character);
		character.sendMsg(new CatchYoulongOK51126(scenemonster.getId()));
//		GameServer.vlineServerManager.sendMsgToAllLineServer(new FactionCtMsg51140(character));
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14521, character.getMyFactionManager().getFactionName(), character
				.getViewName()));
	}

	/**
	 * 炮弹落入场景对人的伤害
	 * 
	 * @param sendVechieveId
	 * @param x
	 * @param y
	 */
	public void sendVechieveToHurtRole(int sendVechieveId, short x, short y, final Hero sender) {
		final GongchengVehicle vehice = GongchengVehicleManager.getInstance().getGongchengVehicleByVehicleId(sendVechieveId);
		if (vehice == null) {
			return;
		}
		final short[] point = randLuodianXy(x, y, vehice.getWuchaScope());
		Collection<Hero> collection = this.getAllCharacters();
		for (Hero role : collection) {
			if (role.getEyeShotManager().testIsInMyEyeShot(point)) {
				role.sendMsg(new VehicleMsg10046(vehice, point[0], point[1]));
			}
		}
		updateObjManager.addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				vehicleHurtRole(vehice, point, sender);
			}
		}, 400);

	}

	public void vehicleHurtRole(GongchengVehicle vehice, short[] point, Hero sender) {
		Collection<Hero> collection = this.getAllCharacters();
		List<Hero> dieRoles = new ArrayList<Hero>();
		List<Hero> hurtRoles = new ArrayList<Hero>();
		int hurtValue = vehice.getHurtValue();
		int hurtScope = vehice.getHurtScope();
		for (Hero role : collection) {
			int type = vehicleHurtRole(role, vehice, point, hurtValue, hurtScope, sender);
			if (type == 1) {
				hurtRoles.add(role);
			} else if (type == 2) {
				dieRoles.add(role);
			}
		}
		sender.sendMsg(new VehicleMsg51122(vehice.getId(), point, hurtScope, hurtRoles, dieRoles));
	}

	private int vehicleHurtRole(Hero role, GongchengVehicle vehice, short[] point, int hurtValue, int hurtScope, Hero sender) {
		if (role.isZeroHp()) {
			return 0;
		}
		int lan = DistanceFormula.getDistanceRound(point[0], point[1], role.getX(), role.getY());
		if (lan > hurtScope) {
			return 0;
		}
		if (lan < 1) {
			lan = 1;
		}
		int shuanjia = 10000 / hurtScope;
		// shuanjia=1000;
		float hurt = hurtValue * (10000f - (lan - 1) * shuanjia) / 10000f;
		int hp = (int) hurt;
		role.changeNowHp(-hp);
		FightMsgSender.directHurtBroadCase(sender, role, 0, 0);
		// TODO need shock
		if (role.isZeroHp()) {
			role.setNowHp(0);
			role.die(sender);
		}
		role.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14540, sender.getViewName(), hp + ""));
		if (role.isZeroHp()) {
			return 2;
		}
		return 1;
		// 您被XXX玩家在襄阳府发出的火炮命中，损失生命：XXXXX14540
		// 您被XXX玩家在襄阳府发出的火炮命中，损失生命：XXXXX
	}

	private short[] randLuodianXy(short x, short y, int wuchangScope) {
		int minX = x - wuchangScope;
		if (minX < 0) {
			minX = 1;
		}
		int maxX = x + wuchangScope;
		if (maxX > this.getWidth()) {
			maxX = this.getWidth() - 1;
		}
		int luodianX = GenerateProbability.randomIntValue(minX, maxX);
		int minY = y - wuchangScope;
		if (minY < 0) {
			minY = 1;
		}
		int maxY = y + wuchangScope;
		if (maxY > this.getHeight()) {
			maxY = this.getHeight() - 1;
		}
		int luodianY = GenerateProbability.randomIntValue(minY, maxY);
		short[] point = { (short) luodianX, (short) luodianY };
		return point;
	}
}
