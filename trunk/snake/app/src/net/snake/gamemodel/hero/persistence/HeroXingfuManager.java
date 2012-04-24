package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.HeroXingfu;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class HeroXingfuManager {
	private static final Logger logger = Logger.getLogger(BreakthroughManager.class);

	private static HeroXingfuDAO dao = new HeroXingfuDAO(SystemFactory.getCharacterSqlMapClient());

	private static HeroXingfuManager instance;

	public static HeroXingfuManager getInstance() {
		if (instance == null) {
			instance = new HeroXingfuManager();
		}
		return instance;
	}

	private HeroXingfuManager() {
	}

	@SuppressWarnings("rawtypes")
	public Map<String, HeroXingfu> getHeroXingfu(int heroId) {
		Map<String, HeroXingfu> heroXingfus = new HashMap<String, HeroXingfu>();
		List list;
		try {
			list = dao.getHeroXingfus(heroId);
			for (Object obj : list) {
				HeroXingfu xingfu = (HeroXingfu) obj;
				heroXingfus.put(xingfu.getType() + "_" + xingfu.getModelId(), xingfu);
			}
		} catch (SQLException e) {
			logger.error("hero xingfu init data error", e);
		}

		return heroXingfus;
	}

	public void asyncUpdate(final HeroXingfu xingfu) {
		GameServer.executorServiceForDB.submit(new Runnable() {
			@Override
			public void run() {
				try {
					dao.update(xingfu.getId());
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void asyncInsert(final HeroXingfu xingfu) {
		GameServer.executorServiceForDB.submit(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(xingfu);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

}
