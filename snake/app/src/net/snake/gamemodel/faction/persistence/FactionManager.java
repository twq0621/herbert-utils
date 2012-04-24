package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.response.FactionBoardMsg51068;
import net.snake.gamemodel.faction.response.FactionCreateMsg51038;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 帮会数据库表管理
 * 
 */

public class FactionManager {
	private static final Logger logger = Logger.getLogger(FactionManager.class);
	private FactionDAO dao = new FactionDAO(SystemFactory.getCharacterSqlMapClient());

	private Map<Integer, FactionController> map = new ConcurrentHashMap<Integer, FactionController>();
	private Map<String, FactionController> nameMap = new ConcurrentHashMap<String, FactionController>();
	// 单例实现=====================================
	private static FactionManager instance;

	private FactionManager() {
	}

	/**
	 * 0点清楚击杀boss数
	 */
	public void clearAllFactionBossKill() {
		for (FactionController factionController : map.values()) {
			factionController.clearBossKill();
		}
	}

	public static FactionManager getInstance() {
		if (instance == null) {
			instance = new FactionManager();
		}
		return instance;
	}

	public void initData() {
		List<Faction> list = selectAllFaction();
		if (list == null || list.size() == 0) {
			return;
		}
		map.clear();
		for (Faction faction : list) {
			addFactionController(faction);
		}
	}

	private void addFactionController(Faction faction) {
		int factionid = faction.getId();
		List<FactionCharacter> list = FactionCharacterManager.getInstance().getFcListByFaction(factionid);
		if (list == null) {
			return;
		}
		FactionController factionController = new FactionController(faction, list);
		map.put(factionid, factionController);
		nameMap.put(factionController.getFaction().getName(), factionController);
	}

	@SuppressWarnings("unchecked")
	public List<Faction> selectAllFaction() {
		try {
			return dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public FactionController getFactionControllerByFactionID(int factionID) {
		return map.get(factionID);
	}

	public Collection<FactionController> getAllFactionCollection() {
		return map.values();
	}

	public FactionController getFactionControllerByFactionName(String factionName) {
		return nameMap.get(factionName);
	}

	public synchronized String createFaction(final Hero character, final Faction faction) {
		String msg = null;
		FactionController factionC = getFactionControllerByFactionName(faction.getName());
		if (factionC != null) {
			msg = "很抱歉，该帮会名称已被其他帮会占用";
			character.getMyFactionManager().setStartIntoFaction(false);
			return msg;
		}
		character.getCharacterGoodController().deleteGoodsFromBag(GoodItemId.BANGZHULING_ID, 1);
		CharacterPropertyManager.changeCopper(character, -FactionController.FactionCreateCopper, CopperAction.CONSUME);
		nameMap.put(faction.getName(), new FactionController());
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(faction);
					FactionCharacter fc = FactionCharacter.createFactionCharacter(faction, character, CommonUseNumber.byte1, 0);
					fc.getCce().update(character, CommonUseNumber.byte1);
					FactionCharacterManager.getInstance().addFactionCharacter(fc);
					FactionController factionController = new FactionController(faction, fc);
					addFactionController(factionController);
					character.getMyFactionManager().init(factionController);
					character.sendMsg(new FactionCreateMsg51038(factionController));
					factionController.updateBossAndOnlineCount();
					character.getEyeShotManager().sendMsg(new FactionBoardMsg51068(character.getId(), faction.getId(), CommonUseNumber.byte1, faction.getViewName()));
					GameServer.vlineServerManager
					//
							.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 1022, faction.getViewName()));
					character.getMyCharacterAchieveCountManger().getFactionCount().factionAddCount(1);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
				character.getMyFactionManager().setStartIntoFaction(false);
			}
		});
		return msg;
	}

	private void addFactionController(FactionController factionController) {
		map.put(factionController.getFaction().getId(), factionController);
		nameMap.put(factionController.getFaction().getName(), factionController);
	}

	public synchronized void delete(final FactionController factionController) {
		map.remove(factionController.getFaction().getId());
		nameMap.remove(factionController.getFaction().getName());
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					FactionCharacterManager.getInstance().deleteFactionCharacterByFactionID(factionController.getFaction().getId());
					dao.deleteById(factionController.getFaction().getId());
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void threadUpdateFaction(final Faction faction) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				update(faction);
			}
		});
	}

	public synchronized void update(Faction faction) {
		try {
			dao.updateByPrimaryKeySelective(faction);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
