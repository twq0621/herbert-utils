package net.snake.gamemodel.instance.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 副本排行
 * 
 * 
 */

public class FubenrankingManager {
	private static final Logger logger = Logger.getLogger(FubenrankingManager.class);

	private static FubenrankingDAO dao = new FubenrankingDAO(SystemFactory.getCharacterSqlMapClient());

	// 单例实现=====================================
	private static FubenrankingManager instance;

	private FubenrankingManager() {

	}

	public static FubenrankingManager getInstance() {
		if (instance == null) {
			instance = new FubenrankingManager();
		}
		return instance;
	}

	// 单例实现========================================
	@SuppressWarnings("unchecked")
	public List<Fubenranking> selecteFubenListByCharacterId(int characterId) {
		try {
			return dao.selecteFubenListByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void addFubenRanking(final Fubenranking fubenranking) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(fubenranking);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void updateByTime(final Fubenranking fubenranking) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByTime(fubenranking);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});
	}

	public List<Integer> getFubenRankingDistinct() {
		return dao.getFuBendistinct();
	}

	@SuppressWarnings("unchecked")
	public List<Fubenranking> getFubenRanking(int fu_ben_id) {
		List<Fubenranking> fubenrankingsList = null;
		try {
			fubenrankingsList = dao.getFubenRanking(fu_ben_id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return fubenrankingsList;
	}

	public List<Fubenranking> getFubenRankingTongJi(int leve) {
		List<Fubenranking> fubenrankingsList = null;
		try {
			fubenrankingsList = dao.getFuBendistinctTongJi(leve);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return fubenrankingsList;
	}

	public Fubenranking getFubenRankingTopOne(int fuBenId) {
		List<Fubenranking> list = RankingManager.getInstance().getMapfubenRanking().get((int) fuBenId);
		Fubenranking fubenranking = null;
		if (null != list) {
			fubenranking = list.get(0);
		}
		return fubenranking;

	}

}
