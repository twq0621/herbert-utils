package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.gamemodel.faction.bean.BangqiPosition;
import net.snake.gamemodel.faction.bean.BangqiPositionByIdComparator;
import net.snake.gamemodel.faction.bean.SceneBangqi;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.ibatis.SystemFactory;

/**
 * 帮会数据库表管理
 * 
 */

public class BangqiPositionManager {
	private static final Logger logger = Logger.getLogger(BangqiPositionManager.class);
	private BangqiPositionDAO dao = new BangqiPositionDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, BangqiPosition> idMap = new ConcurrentHashMap<Integer, BangqiPosition>();
	private List<BangqiPosition> positionList = new ArrayList<BangqiPosition>();
	// private Map<Integer,FactionFlag> idMap=new ConcurrentHashMap<Integer,
	// FactionFlag>();
	// 单例实现=====================================
	private static BangqiPositionManager instance;

	private BangqiPositionManager() {
		initDate();
	}

	public static BangqiPositionManager getInstance() {
		if (instance == null) {
			instance = new BangqiPositionManager();
		}
		return instance;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initDate() {
		List<BangqiPosition> list = selectAllBangqiPosition();
		if (list == null || list.size() == 0) {
			return;
		}
		try {
			BeanTool.addOrUpdate(idMap, list, "id");
			List tempList = new ArrayList<BangqiPosition>();
			for (BangqiPosition bp : idMap.values()) {
				tempList.add(bp);
			}
			Collections.sort(tempList, new BangqiPositionByIdComparator());
			positionList = tempList;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<BangqiPosition> selectAllBangqiPosition() {
		try {
			return dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public BangqiPosition getBangqiPositionById(int id) {
		return idMap.get(id);
	}

	public List<BangqiPosition> getBangqiListByType(byte type, FactionController factionC) {
		List<BangqiPosition> temp = new ArrayList<BangqiPosition>();
		int factionId = 0;
		if (factionC != null) {
			factionId = factionC.getFaction().getId();
		}
		SceneBangqiManager sbm = SceneBangqiManager.getInstance();
		if (type == 0) {
			return positionList;
		} else if (type == 1) {
			for (int i = 0; i < positionList.size(); i++) {
				BangqiPosition position = positionList.get(i);
				SceneBangqi bangqi = sbm.getSceneBangqiByPositionId(position.getId());
				if (bangqi != null && bangqi.getFactionId() == factionId) {
					temp.add(position);
				}
			}
			return temp;
		} else if (type == 2) {
			for (int i = 0; i < positionList.size(); i++) {
				BangqiPosition position = positionList.get(i);
				SceneBangqi bangqi = sbm.getSceneBangqiByPositionId(position.getId());
				if (bangqi != null && bangqi.getFactionId() != factionId) {
					temp.add(position);
				}
			}
			return temp;
		} else if (type == 3) {
			for (int i = 0; i < positionList.size(); i++) {
				BangqiPosition position = positionList.get(i);
				SceneBangqi bangqi = sbm.getSceneBangqiByPositionId(position.getId());
				if (bangqi == null) {
					temp.add(position);
				}
			}
			return temp;
		}
		return temp;
	}
}
