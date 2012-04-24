package net.snake.gamemodel.team.persistence;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.team.bean.TeamFighting;
import net.snake.gamemodel.team.logic.TeamFightingController;
import net.snake.gamemodel.team.logic.TeamFightingControllerFactory;
import net.snake.ibatis.SystemFactory;

public class TeamFightingManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(TeamFightingManager.class);
	private TeamFightingDAO teamFightingDAO = new TeamFightingDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, TeamFightingController> tfcMap = new HashMap<Integer, TeamFightingController>();
	private Map<Integer, TeamFighting> tfMap = new HashMap<Integer, TeamFighting>();
	// 单例实现=====================================
	private static TeamFightingManager instance;

	private TeamFightingManager() {
		reload();
	}

	public static TeamFightingManager getInstance() {
		if (instance == null) {
			instance = new TeamFightingManager();
		}
		return instance;
	}

	public Collection<TeamFighting> getAllTeamFightingCollection() {
		return tfMap.values();
	}

	@SuppressWarnings("unchecked")
	public List<TeamFighting> selectAllTeamFighting() {
		try {
			return teamFightingDAO.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 初始化阵营数据
	 */

	private void initTeamFightGameDate() {
		List<TeamFighting> list = selectAllTeamFighting();
		Map<Integer, TeamFightingController> tfcMap1 = new HashMap<Integer, TeamFightingController>();
		Map<Integer, TeamFighting> tfMap1 = new HashMap<Integer, TeamFighting>();
		for (TeamFighting tf : list) {
			TeamFightingController tfc = TeamFightingControllerFactory.getTfc(tf);
			if (tfc != null) {
				tfcMap1.put(tf.getId(), tfc);
				tfMap1.put(tf.getId(), tf);
			}
		}
		tfcMap = tfcMap1;
		tfMap = tfMap1;
	}

	/**
	 * 通过阵法id获取阵法控制器
	 * 
	 * @param id
	 * @return
	 */
	public TeamFightingController getFightingControllerById(int id) {
		return tfcMap.get(id);
	}

	public Collection<TeamFightingController> getAllTeamFightingControllerCollection() {
		return tfcMap.values();
	}

	// 单例实现========================================
	@Override
	public void reload() {
		initTeamFightGameDate();

	}

	@Override
	public String getAppname() {
		return "teamfighting";
	}

	@Override
	public String getCachename() {
		return "teamfighting";
	}
}
