package net.snake.gamemodel.map.logic;

import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.commons.CertificationUtil;
import net.snake.commons.NetTool;
import net.snake.commons.message.SimpleResponse;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.response.YuanfuToken10118;
import net.snake.gamemodel.auth.persistence.AuthConfigManager;
import net.snake.gamemodel.faction.response.factioncity.FactionCityChangeLineMsg51134;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.response.hero.EnterSceneBronPosInfo10020;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.gamemodel.trade.response.TradeMsg10840;
import net.snake.serverenv.vline.VLineServer;
import org.apache.log4j.Logger;


/**
 * 切换地图场景
 * 
 * 
 */
public class ExchangeMapTrans {
	private static Logger logger = Logger.getLogger(ExchangeMapTrans.class);

	/**
	 * 根据传送点传送
	 */
	public static void trans(Teleport telport, Hero character) {
		// 传送前停止移动
		character.getMoveController().stopMove();
		if (telport == null) {
			Scene scene = character.getVlineserver().getSceneManager().getSceneByID(ClientConfig.Scene_Xianjing);
			TempTeleport defaultTelePortWhenException = new TempTeleport(scene.getId(), scene.getName(), new short[] { 50, 50 });
			telport = defaultTelePortWhenException;
			// 没有此传送点
			// return;
		}
		// 根据传送点传送时要找到传送点附近的点
		Scene targetscene = character.getVlineserver().getSceneManager().getSceneByID(telport.getTargetSceneID());
		short[] enterPoint = targetscene.getRandomPoint(telport.getTargetX(), telport.getTargetY(), 9);
		try {
			trans(telport.getTargetSceneID(), enterPoint[0], enterPoint[1], character);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 根据目标场景引用传送
	 */
	public static void trans(Scene targetScene, Short x, Short y, Hero character) {
		// 保证进入场景的同步性
		if (!character.getVlineserver().isFrameUpdateThread()) {
			logger.error("can not invoke this method ,otherwise u are in frameupdateThread");
			return;
		}
		// 离开之前的场景
		if (character.getSceneRef() != null) {
			character.getSceneRef().leaveScene(character, targetScene);
		}
		// 准备进入目标场景
		character.setSceneRef(targetScene);
		character.setX(x);
		character.setY(y);
		character.sendMsg(new EnterSceneBronPosInfo10020(targetScene, character));
		// 人物属性 10022
	}

	/**
	 * 查找目标场景,后传送
	 */
	public static void trans(int targetSceneId, short x, short y, final Hero character) {
		if (!character.getVlineserver().isFrameUpdateThread()) {
			logger.error("can not invoke this method ,otherwise u are in frameupdateThread");
			return;
		}
//		if (targetSceneId != 0) {
//			// 检查一下是否传到了障碍物点上,地图数据的更新可能会导致这个问题的发生
//			Scene checkscene = character.getVlineserver().getSceneManager().getSceneByID(targetSceneId);
//			if (checkscene == null || !checkscene.isPermitted(x, y)) {
//				trans(null, character);
//				return;
//			}
//
//		}

		// try {
		// 新手村判断
		Scene targetScene = null;
		if (targetSceneId == 0) {// 新手村
			targetScene = character.getVlineserver().getSceneManager().getScene4Novice();
			x = (short) targetScene.getEnterX();
			y = (short) targetScene.getEnterY();
			short[] randompoint = targetScene.getRandomPoint(x, y, 6);
			x = randompoint[0];
			y = randompoint[1];
			targetSceneId = targetScene.getId();
		} else {
			targetScene = character.getVlineserver().getSceneManager().getSceneByID(targetSceneId);
		}
		if (targetScene == null) {
			character.sendMsg(SimpleResponse.failReasonMsg(10020, 40024));
			return;
		}

		if (targetScene.isClearPkProtect()) {
			character.getEffectController().clearPkProtectEffect(false);
		}

		if (!targetScene.isInstanceScene()) {
			trans(targetScene, x, y, character);
			return;
		}

		// 如果是副本场景
		if (character.getSceneRef() == null) {// 如果角色目前没有任何场景引用,则说明它第一次进入游戏
			// 副本中下线 上限处理是进入副本还是进入普通场景
			character.getMyCharacterInstanceManager().firstEnterScene();

		} else {// 已经在其他场景中要求切换到副本场景
			// 副本间切换
			character.getMyCharacterInstanceManager().instanceExChangeScene(targetScene);
		}
		// } catch (Exception e) {
		// logger.warn("切换场景失败，要求切换到的场景为{}", targetSceneId);
		// logger.error(e.getMessage(), e);
		// }
	}

	/**
	 * 角色地图传送（包括跨线地图传送）
	 * 
	 * @param character
	 * @param line
	 * @param scene
	 * @param enterX
	 * @param exterY
	 */
	public static void transWithChangeLine(final Hero character, VLineServer line, final Scene scene, final short enterX, final short exterY) {
		// 结束双修
		try {
			character.getMyDazuoManager().endShuangXiuWhenDownLine();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// 处于交易中的双方玩家在开启交易界面窗口之后某一方离线或掉线，交易窗口自动关闭，交易失败；
		try {
			IMyTradeController mtc = character.getMyTradeController();
			if (mtc.isTrading()) {
				Hero trader = mtc.getTradeCharacter();
				mtc.cancelTrade();
				trader.sendMsg(new TradeMsg10840(13501));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// 离开场景
		try {
			Scene leavescene = character.getSceneRef();
			if (leavescene != null) {
				leavescene.leaveScene(character, scene);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		int lineId = line.getLineid();
		if (lineId != character.getVlineserver().getLineid()) {
			// 离开队伍
			try {
				character.getMyTeamManager().playerDownLineWhenTeam();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}

			// 清除弓箭升阶祝福
			try {
				character.getBowController().clearBowZhufu();
			} catch (Exception e) {
				logger.error("broadcast hero offline with err", e);
			}
			// 原分线不再维护次角色的缓存
			try {
				GameServer.vlineServerManager.removeCharacterById(character.getId());
				// 角色持有目标分线的引用
				character.setVlineserver(line);
				// 目标分线添加角色的缓存
				line.getOnlineManager().addCharacter(character);
				character.sendMsg(new FactionCityChangeLineMsg51134(lineId, line.getLinename()));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		// 添加一个任务到循环中执行一次完成进入场景
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				ExchangeMapTrans.trans(scene, enterX, exterY, character);
			}
		});
	}

	/**
	 * 跨服服务器传送回原服务器襄阳城
	 * 
	 * @throws SQLException
	 */
	public static void transAcrossToYuanfuXiangyangcheng(final Hero character) throws SQLException {
		GameServer.executorService.execute(new Runnable() {
			@Override
			public void run() {
				AcrossEtc ae = character.getCharacterAcrossServerManager().getAce();
				int newcharacterId = ae.getOldCharacterId();
				int newaccountId = ae.getOldAccountId();
				character.getMyCharacterAcrossIncomeManager().cacheInComeToDb();
				character.getDownLineManager().saveToDb();
				String param = newaccountId + "_" + character.getAntiAddictedSystem().getIsLimitToByte() + "_" + System.currentTimeMillis();
				String auth = CertificationUtil.encodeBase64(param);
				String key = "";
				key = AuthConfigManager.getInstance().getMd5Key();
				String sign = CertificationUtil.md5(auth + key);
				NetTool.sendAndClose(character.getPlayer(), new YuanfuToken10118(auth, sign, ae, newcharacterId, newaccountId), 3000);

			}
		});

	}
}
