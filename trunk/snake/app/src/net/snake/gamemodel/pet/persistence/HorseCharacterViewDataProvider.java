package net.snake.gamemodel.pet.persistence;


import java.sql.SQLException;

import java.util.List;

import org.apache.log4j.Logger;

import net.snake.gamemodel.pet.bean.HorseCharacterView;
import net.snake.ibatis.SystemFactory;

/**
 * 马和人物联合查询
 * 
 * 
 */
public class HorseCharacterViewDataProvider {
	private static final Logger logger = Logger.getLogger(HorseCharacterViewDataProvider.class);
	private HorseCharacterViewDAO horseCharacterViewDAO = new HorseCharacterViewDAO(
			SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static HorseCharacterViewDataProvider instance;

	private HorseCharacterViewDataProvider() {
	}

	public static HorseCharacterViewDataProvider getInstance() {
		if (instance == null) {
			instance = new HorseCharacterViewDataProvider();
		}
		return instance;
	}

	// 单例实现========================================

	public List<HorseCharacterView> selectRanKing() {
		List< HorseCharacterView> list = null;
			try {
				list =horseCharacterViewDAO.selectRanKing();
				
			} catch (SQLException e) {
				logger.error(e.getMessage(),e);
			}
			return list;
	}
	public List<HorseCharacterView> getRankingmatongji(int level) {
		List< HorseCharacterView> list = null;
		try {
			list =  horseCharacterViewDAO.selectLevel(level);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return list;
	}
	
	
}
