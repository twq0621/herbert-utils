package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterLastKill;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.cache.CharacterCacheManager;

import org.apache.log4j.Logger;

/**
 * @author serv_dev
 */
public class BossLastKillManager {
	Logger logger = Logger.getLogger(getClass());
	MonsterLastKillDAO dao;
	HashMap<String, MonsterLastKill> map = new HashMap<String, MonsterLastKill>();
	private static BossLastKillManager instance;

	public static BossLastKillManager getInstance() {
		if (instance == null) {
			instance = new BossLastKillManager();
		}
		return instance;
	}

	private BossLastKillManager() {
		try {
			init();
		} catch (SQLException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(e.getMessage());
			}

			logger.error(e.getMessage(), e);
		}
	}
	@SuppressWarnings("unchecked")
	private void init() throws SQLException {
		dao = new MonsterLastKillDAO(SystemFactory.getCharacterSqlMapClient());
		List<MonsterLastKill> selectByExample = dao.select();
		for (MonsterLastKill monsterLastKill : selectByExample) {
			String key = monsterLastKill.getModelid() + "_" + monsterLastKill.getLineid();
			map.put(key, monsterLastKill);
		}
	}

	public void update(SceneMonster monster, Hero killer) {
		// 更新最后击杀者
		MonsterLastKill monsterLastKill = map.get(monster.getModel() + "_" + killer.getVlineserver().getLineid());
		if (monsterLastKill != null) {
			monsterLastKill.setKillerid(killer.getId());
			monsterLastKill.setUpdatetime(new Date());
			update(monsterLastKill);
		} else {
			monsterLastKill = new MonsterLastKill();
			monsterLastKill.setKillerid(killer.getId());
			monsterLastKill.setModelid(monster.getModel());
			monsterLastKill.setLineid(killer.getVlineserver().getLineid());
			monsterLastKill.setUpdatetime(new Date());
			map.put(monster.getModel() + "_" + killer.getVlineserver().getLineid(), monsterLastKill);
			insert(monsterLastKill);
		}
	}

	private void update(final MonsterLastKill kill) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateById(kill);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	private void insert(final MonsterLastKill kill) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(kill);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public CharacterCacheEntry getLastKiller(int modelid, int lineid) {
		String key = modelid + "_" + lineid;
		MonsterLastKill monsterLastKill = map.get(key);
		if (monsterLastKill != null) {
			Integer killerid = monsterLastKill.getKillerid();
			if (killerid != null && killerid != 0) {
				return CharacterCacheManager.getInstance().getCharacterCacheEntryById(killerid);
			}
		}
		return null;
	}
}
