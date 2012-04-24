package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.BangqiPosition;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.SceneBangqi;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 帮会与玩家角色关系表管理
 * 
 */

public class SceneBangqiManager {
	private static final Logger logger = Logger.getLogger(SceneBangqiManager.class);
	private SceneBangqiDAO dao = new SceneBangqiDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	public static int enterBangqiTimeJiange = 10 * 60 * 1000;
	private static SceneBangqiManager instance;
	private Map<Integer, SceneBangqi> bangqiMap = new ConcurrentHashMap<Integer, SceneBangqi>(); // 安插点id对应

	private SceneBangqiManager() {
	}

	public static SceneBangqiManager getInstance() {
		if (instance == null) {
			instance = new SceneBangqiManager();
		}
		return instance;
	}

	public void deleteFactionCharacterByFactionID(int positionId) {
		try {
			dao.deleteByFactionId(positionId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void initDate() {
		try {
			List<SceneBangqi> list = selectAllSceneBangqi();
			if (list == null || list.size() == 0) {
				return;
			}
			if (bangqiMap.size() > 0) {
				return;
			}
			for (int i = 0; i < list.size(); i++) {
				SceneBangqi sb = list.get(i);
				FactionController factionC = FactionManager.getInstance().getFactionControllerByFactionID(sb.getFactionId());
				if (factionC != null) {
					bangqiMap.put(sb.getBangqiPositionId(), sb);
					sb.getFactionController();
					factionC.addBangqiMonsterToSceneAndFaction(sb);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<SceneBangqi> selectAllSceneBangqi() {
		try {
			List<SceneBangqi> list = dao.select();
			return list;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	// 单例实现========================================
	public synchronized int insert(SceneBangqi sb) {
		try {
			return dao.insert(sb);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}

	public synchronized int delete(SceneBangqi sb) {
		try {
			return dao.deleteById(sb.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return -1;
	}

	public SceneBangqi getSceneBangqiByPositionId(int positionId) {
		return bangqiMap.get(positionId);
	}

	public synchronized SceneBangqi bangqiToScene(int positionId, Hero bangzhu) {
		BangqiPosition position = BangqiPositionManager.getInstance().getBangqiPositionById(positionId);
		if (position == null) {
			bangzhu.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 826));
			return null;
		}
		SceneBangqi bangqi = this.getSceneBangqiByPositionId(positionId);
		if (bangqi != null) {
			bangzhu.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 827));
			return null;
		}
		Faction faction = bangzhu.getMyFactionManager().getFactionController().getFaction();
		if (faction.getCopper() < FactionController.BangQiEnterSceneCopper) {
			bangzhu.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 828));
			return null;
		}
		if (!position.isEnterSceneByFactionId(faction.getId())) {
			bangzhu.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14580));
			return null;
		}
		faction.setCopper(faction.getCopper() - FactionController.BangQiEnterSceneCopper);
		bangqi = new SceneBangqi(position, bangzhu.getVlineserver().getLineid(), bangzhu.getMyFactionManager().getFactionController());
		addSceneBangqiToDb(bangqi);
		return bangqi;
	}

	public void addSceneBangqiToDb(SceneBangqi bangqi) {
		this.bangqiMap.put(bangqi.getBangqiPositionId(), bangqi);
		final SceneBangqi temp = bangqi;
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				insert(temp);
			}
		});
	}

	public synchronized boolean bangqiLeaveScene(SceneBangqi bangqi) {
		if (bangqi == null) {
			return false;
		}
		removeSceneBangqiToDb(bangqi);
		return true;
	}

	private void removeSceneBangqiToDb(SceneBangqi bangqi) {
		this.bangqiMap.remove(bangqi.getBangqiPositionId());
		final SceneBangqi temp = bangqi;
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				delete(temp);
			}
		});
	}

	public synchronized void update() {
		for (SceneBangqi bangqi : bangqiMap.values()) {
			try {
				dao.updateHpMpById(bangqi);
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}
