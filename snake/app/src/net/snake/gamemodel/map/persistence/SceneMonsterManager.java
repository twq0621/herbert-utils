package net.snake.gamemodel.map.persistence;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.gamemodel.exception.DataException;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.serverenv.vline.VLineServer;

/**
 * 场景怪物表管理
 * 
 */

public class SceneMonsterManager {
	private static final Logger logger = Logger.getLogger(SceneMonsterManager.class);
	public Map<Integer, SceneMonster> map = new HashMap<Integer, SceneMonster>();
	private static SceneMonsterDao dao = new SceneMonsterDao();
	// 单例实现=====================================
	private static SceneMonsterManager instance;

	private SceneMonsterManager() {
	}

	public static SceneMonsterManager getInstance() {
		if (instance == null) {
			instance = new SceneMonsterManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void reloadDate() {
		map.clear();
		try {
			Map<Integer, SceneMonster> mapt = dao.getSqlMapClient().queryForMap("sceneMonsterGetAll", null, "id");
			if (mapt != null) {
				map = mapt;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<SceneMonster> getSceneMonstersByMapid(Integer mapid) throws DataException {
		try {
			List<SceneMonster> list = dao.getSqlMapClient().queryForList("sceneMonstersByMapid", mapid);
			return list;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new DataException(this.getClass().getName(), "getSceneMonstersByMapid()", "query all data error！", e);
		}
	}

	public SceneMonster getSceneMonsterById(int id) {
		return map.get(id);
	}

	public void reload() {
		reloadDate(); // 初始化数据
		Collection<VLineServer> collection = GameServer.vlineServerManager.getLineServersList();
		for (VLineServer line : collection) {
			line.reloadSceneMonster();
		}
	}
}
