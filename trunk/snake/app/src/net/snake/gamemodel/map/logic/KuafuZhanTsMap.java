package net.snake.gamemodel.map.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.across.character.msg.XuanyuanjianBelongToMsg51152;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.CharacterFuhuoDelayMsg20078;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ai.formula.DistanceFormula;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.fight.bean.GongchengVehicle;
import net.snake.gamemodel.fight.bean.XuanyuanjianConfig;
import net.snake.gamemodel.fight.persistence.GongchengVehicleManager;
import net.snake.gamemodel.fight.persistence.XuanyuanjianConfigManager;
import net.snake.gamemodel.fight.response.CatchYoulongOK51126;
import net.snake.gamemodel.fight.response.PkModelChanageMsg;
import net.snake.gamemodel.fight.response.VehicleMsg10046;
import net.snake.gamemodel.fight.response.VehicleMsg51122;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.logic.DynamicUpdateObjManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiGoodsManager;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;
import net.snake.commons.GenerateProbability;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

/**
 * 跨服地图
 * 
 * @author serv_dev
 */
public class KuafuZhanTsMap extends GameMap {
	private static final Logger logger = Logger.getLogger(KuafuZhanTsMap.class);
	public static boolean isClearFirstDate = false;
	public static int KuafuSceneId = 20197;
	/** false 表示没有处于攻城时间内 true表示处于攻城状态 */
	public boolean isKuafuZhanState = false; //
	/** 表示是否处于正在攻城中 */
	public boolean KuafuZhaning = false; //
	public static int startHourse = 21;
	public static int endHourse = 22;
	private DynamicUpdateObjManager updateObjManager = new DynamicUpdateObjManager();
	/** 当玩家进入本地图后，每隔1分钟增加一次经验。 */
	private static final int upExp60seconds = 60 * 1000;//
	private long beginUpExpTime = 0l;
	public SceneXuanyuanMonster[] xuanyuanjian = new SceneXuanyuanMonster[4];

	public SceneXuanyuanMonster[] getXuanyuanjian() {
		return xuanyuanjian;
	}

	public void setXuanyuanjian(SceneXuanyuanMonster[] xuanyuanjian) {
		this.xuanyuanjian = xuanyuanjian;
	}

	@Override
	public void enterScene(Hero player) {
		if (!KuafuZhaning) {
			exChangeYuanGameServer(player);
			return;
		}
		super.enterScene(player);
		player.sendMsg(new XuanyuanjianBelongToMsg51152(xuanyuanjian));
		if (player.getMyFactionManager().isFaction()) {
			player.getFightController().setPkModel((int) 2);
			PkModelChanageMsg msg = new PkModelChanageMsg(2);
			player.sendMsg(msg);
		}
	}

	/**
	 * 随机一个复活点 1无剑复活 2有剑复活
	 * 
	 * @param flag
	 * @return
	 */
	public int[] getRandomFuhuoPoint(int flag) {
		if (flag == 1) {// 无剑复活
			List<int[]> col = getWujianFuHuo();
			if (!col.isEmpty()) {
				int size = col.size();
				int num = GenerateProbability.randomIntValue(0, size - 1);
				return col.get(num);
			}
		} else if (flag == 2) {// 有剑复活
			List<int[]> col = getYoujianFuHuo();
			if (!col.isEmpty()) {
				int size = col.size();
				int num = GenerateProbability.randomIntValue(0, size - 1);
				return col.get(num);
			}
		}
		return null;
	}

	/**
	 * 是否是复活区
	 * 
	 * @param x
	 * @param y
	 * @return true 是复活区
	 */
	public boolean isFuhuoQu(short x, short y) {

		if (getFuhuoTiles() == null)
			return false;

		int lenX = getFuhuoTiles().length;
		int lenY = getFuhuoTiles()[0].length;
		if (x < 0 || y < 0 || x >= lenX || y >= lenY) {
			return false;
		}

		return getFuhuoTiles()[x][y] != 0;
	}

	@Override
	public void leaveScene(Hero character, Scene newScene) {
		character.getMycharacterAcrossZhengzuoManager().onLeaveKuafuTsMap(null);
		super.leaveScene(character, null);
	}

	@Override
	public void update(long now) {
		try {
			if (!AcrossServerDateManager.isOpenAcross) {
				return;
			}
			if (Options.IsCrossServ) {
				super.update(now);
			}

			int hourse = getTodayHourse();
			if (!isKuafuZhanState) {
				if (hourse == startHourse) {
					startKuanfuzhanInitDate();
					isKuafuZhanState = true;
				}
				return;
			}
			try {
				updateObjManager.update(System.currentTimeMillis());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			boolean isUpExp = false;
			if (now - beginUpExpTime > upExp60seconds) {
				isUpExp = true;
				beginUpExpTime = now;
			}

			if (hourse >= endHourse) {
				isKuafuZhanState = false;
				endKuafuzhan();
				Collection<Hero> collection = this.getAllCharacters();
				for (Hero role : collection) {
					if (isUpExp) {
						upExp(role);
					}
					exChangeYuanGameServer(role);
				}
				return;
			}

			Collection<Hero> collection = this.getAllCharacters();
			for (Hero role : collection) {
				try {
					if (isUpExp) {
						upExp(role);
					}
					role.getMycharacterAcrossZhengzuoManager().update(this);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void upExp(Hero character) {
		// 等级 * 等级 * 当前所在区域的经验倍数 * 所获轩辕剑BUFF的经验倍数
		long _experience = CharacterFormula.getKuafuOneMinute(character);
		try {
			CharacterFormula.experienceProcess(character, _experience);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 统一回到原来服务器
	 * 
	 * @param charactger
	 */
	public void exChangeYuanGameServer(Hero charactger) {
		try {
			ExchangeMapTrans.transAcrossToYuanfuXiangyangcheng(charactger);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
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
				 * BUFF作用：令自己的配偶获得一个BUFF，该BUFF的作用是当配偶血量变为0时， 立即原地复活（不读倒计时，倒地后立即站起），恢复一定的血量值，免除死亡惩罚一次，并获得3秒钟的无敌时间。 重生后的血量值=夫妻之间的友好度（最低为1000，最大为10000）
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
				// character.getEffectController().addUnWithstandBuff(3000);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return true;
		}
		character.getMycharacterAcrossZhengzuoManager().dropXuanyuanToScene(null);
		character.getCatchXuanyuanJianActionController().breakCatch();
		character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
		int factionId = character.getMyFactionManager().getFactionId();
		for (int i = 0; i < xuanyuanjian.length; i++) {
			Hero bangzhu = xuanyuanjian[i].getXuanyuanjianCharacter();
			if (bangzhu != null) {
				if (bangzhu.getMyFactionManager().getFactionId() == factionId) {
					showFangRelive(character);
					return false;
				}
			}
		}
		gongFangRelive(character);
		return false;
	}

	/**
	 * 守方死亡复活
	 * 
	 * @param character
	 */
	private void showFangRelive(final Hero character) {
		int[] point = this.getRandomShowPoint();
		sameSceneHuichengFuhuo(character, point);
	}

	/**
	 * 获得守方 复活点（有剑）
	 * 
	 * @return
	 */
	public int[] getRandomShowPoint() {
		return getRandomFuhuoPoint(2);
	}

	// /**
	// * 获取攻防区域复活点
	// *
	// * @return
	// */
	// public boolean isInGongRandomPoint(Character character) {
	//
	// short[] relive = FactionCityManager.gongRelive;
	// float xCha = relive[0] - relive[2];
	// float yCha = relive[1] - relive[3];
	// float xielv = xCha / yCha;
	// int nx = character.getX();
	// int c = (int) (relive[0] / xielv - relive[1]);
	// if (nx < relive[0] || nx > relive[2]) {
	// return false;
	// }
	// int minY = (int) (nx / xielv) - c;
	// int maxY = (int) (relive[3] - 1);
	// if (character.getY() < minY || character.getY() > maxY) {
	// return false;
	// }
	// return true;
	// }

	/**
	 * 获取攻防区域复活点
	 * 
	 * @return
	 */
	public int[] getRandomGongPoint() {
		return getRandomFuhuoPoint(1);
	}

	/**
	 * 攻防死亡复活
	 * 
	 * @param character
	 */
	private void gongFangRelive(final Hero character) {
		int[] point = this.getRandomGongPoint();
		sameSceneHuichengFuhuo(character, point);
	}

	/**
	 * 同地图复活
	 * 
	 * @param character
	 * @param relive
	 */
	private void sameSceneHuichengFuhuo(final Hero character, final int[] relive) {
		try {
			final Scene scene = this;
			character.getEyeShotManager().sendMsg(new CharacterFuhuoDelayMsg20078(character.getId()));
			character.setWudiBuffer(true);
			character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
				@Override
				public void run() {
					try {
						CharacterResurrect.fuhuo(character);
						ExchangeMapTrans.trans(scene, (short) relive[0], (short) relive[1], character);
						character.getDiedata().clear();
						character.setObjectState(VisibleObjectState.Idle);
						character.getMycharacterAcrossZhengzuoManager().addFuHuoProtectBuffWhenDie();
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}, (Options.Relive_Timeout + 1) * 1000);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void initKuafuTsMap() {
		// Collection<SceneMonster> collection =
		// this.getAllCurrentSceneMonster();
		// for (SceneMonster temp : collection) {
		// this.leaveScene(temp);
		// }
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (asd != null) {
			if (!asd.isTimeExpression(System.currentTimeMillis() + 6000)) {
				return;
			}
		}
		if (!Options.IsCrossServ) {
			if (getLineId() != FactionCityManager.gongchengLine) {
				return;
			}
			// 非跨服服务器 收回轩辕剑
			GameServer.executorService.execute(new Runnable() {
				@Override
				public void run() {
					Collection<VLineServer> col = GameServer.vlineServerManager.getLineServersList();
					for (VLineServer line : col) {
						Collection<Hero> roles = line.getOnlineManager().getCharacterList();
						for (Hero role : roles) {
							role.getMycharacterAcrossZhengzuoManager().takeOffBangzhuXuanyuanjianBuffer();
						}
					}
				}
			});
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 14790));
			sendKuaFuMsgToAllRole();
			return;
		}
		try {
			if (!isClearFirstDate) {
				isClearFirstDate = true;
				GameServer.executorService.schedule(new Runnable() {
					@Override
					public void run() {
						CharacterTianXiaDiYiManager.getInstance().updateData();

					}
				}, 1, TimeUnit.SECONDS);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		beginUpExpTime = System.currentTimeMillis();
		Collection<SceneXuanyuanMonster> SceneXuanyuanMonstercollection = this.getSceneObjByClass(SceneObj.SceneObjType_MonsterXuanyuan);
		if (SceneXuanyuanMonstercollection != null) {
			for (SceneXuanyuanMonster monster : SceneXuanyuanMonstercollection) {
				if (monster != null) {
					monster.setRelive(0);
					monster.setObjectState(VisibleObjectState.Dispose);
				}
			}
		}
		xuanyuanjian = new SceneXuanyuanMonster[4];
		List<XuanyuanjianConfig> list = XuanyuanjianConfigManager.getInstance().getXuanjianList();
		int i = 0;
		for (XuanyuanjianConfig xuanConfig : list) {
			SceneXuanyuanMonster sceneMonster = new SceneXuanyuanMonster(xuanConfig);
			sceneMonster.setScene(this.getId());
			sceneMonster.setId(SceneMonster.getNewID());
			sceneMonster.setX(xuanConfig.getX());
			sceneMonster.setY(xuanConfig.getY());
			sceneMonster.setOriginalX(xuanConfig.getX());
			sceneMonster.setOriginalY(xuanConfig.getY());
			sceneMonster.setRelive(0);
			MonsterModel monstermodel = MonsterModelManager.getInstance().getFromCache(xuanConfig.getMonsterId());
			sceneMonster.setMonsterModel(monstermodel);
			sceneMonster.init();
			this.enterScene(sceneMonster);
			xuanyuanjian[i] = sceneMonster;
			i++;
		}
		getSceneManager().getVlineServer().getAcrossFactionManager().clearFaction();
		getSceneManager().getVlineServer().getAcrossVehicleManager().clearVehicle();
	}

	/**
	 * 玩家kaufufu战开始引导
	 */
	public void sendKuaFuMsgToAllRole() {
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.getMyNewcomeManager().sendKuafuzhanMsg();
			}
		});
	}

	public void startKuanfuzhanInitDate() {
		if (KuafuZhaning) {
			return;
		}
		KuafuZhaning = true;
		try {
			initKuafuTsMap();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// GameServer.vlineServerManager
		// .sendMsgToAllLineServer(new FactionCtMsg51130());
	}

	/**
	 * 产生天下第一
	 * 
	 * @param monster
	 */
	private void produceFirstCharacter(SceneXuanyuanMonster monster) {
		try {
			Hero first = monster.getXuanyuanjianCharacter();
			if (first != null) {
				first.getMyCharacterAcrossIncomeManager().receiveXuanyuanShenjian(monster.getXuanjianConfig());
				CharacterTianXiaDiYiGoodsManager.getInstance().updateCharacterTianXiaDiYiTopOneSave(first);
				CharacterTianXiaDiYiManager.getInstance().updateCharacterTianXiaDiYiTopOneSave(first, first.getVlineserver().getLineid());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 攻城结束
	 */
	@SuppressWarnings("unchecked")
	public void endKuafuzhan() {
		if (!KuafuZhaning) {
			return;
		}
		isClearFirstDate = false;
		KuafuZhaning = false;
		AcrossServerDate asd = AcrossServerDateManager.getInstance().getList().get(0);
		if (!asd.isTimeExpression(System.currentTimeMillis() - 90000)) {
			return;
		}
		if (!Options.IsCrossServ) {
			try {
				if (getLineId() == FactionCityManager.gongchengLine) {
					GameServer.executorService.schedule(new Runnable() {
						@Override
						public void run() {
							CharacterTianXiaDiYiManager.getInstance().updateData();
						}
					}, 300, TimeUnit.SECONDS);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			return;
		}
		try {
			produceFirstCharacter(xuanyuanjian[0]);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		Collection<SceneMonster> collection = this.getAllCurrentSceneMonster();
		if (collection != null) {
			for (SceneMonster monster : collection) {
				monster.setNowHp(0);
				monster.setObjectState(VisibleObjectState.Die);
			}
		}
		Collection<SceneXuanyuanMonster> SceneXuanyuanMonstercollection = this.getSceneObjByClass(SceneObj.SceneObjType_MonsterXuanyuan);
		if (SceneXuanyuanMonstercollection == null) {
			return;
		}
		for (SceneXuanyuanMonster monster : SceneXuanyuanMonstercollection) {
			monster.setRelive(0);
			monster.setObjectState(VisibleObjectState.Dispose);
		}
		// GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(
		// TipMsg.MSGPOSITION_SYSBROADCAST, 14524));
		// GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(
		// TipMsg.MSGPOSITION_SYSBROADCAST, 14550));

	}

	/**
	 * 某帮会帮主成功不出游龙之刃
	 * 
	 * @param scenemonster
	 * @param character
	 */
	public void catchXuanyuanSucess(SceneXuanyuanMonster scenemonster, Hero character) {
		this.leaveScene(scenemonster);
		scenemonster.setSceneRef(this);
		scenemonster.catchXuanyuan(character, this);
		character.sendMsg(new CatchYoulongOK51126(scenemonster.getId()));
		// GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(
		// TipMsg.MSGPOSITION_SYSBROADCAST, 14521, character
		// .getMyFactionManager().getFactionName(), character
		// .getViewName()));
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

		if (role.getEffectController().isUnWithstand()) {// 处于无敌的状态
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

	@Override
	public Object clone() throws CloneNotSupportedException {
		KuafuZhanTsMap t = (KuafuZhanTsMap) super.clone();// 用于备份BaseMapInfo里的东西
		// ================
		updateObjManager = new DynamicUpdateObjManager();
		xuanyuanjian = new SceneXuanyuanMonster[4];
		t.isKuafuZhanState = false;
		t.isKuafuZhanState = false; // false 表示没有处于攻城时间内 true表示处于攻城状态
		return t;
	}
}
