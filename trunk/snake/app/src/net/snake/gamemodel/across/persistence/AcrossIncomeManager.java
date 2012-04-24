/**
 * 
 */
package net.snake.gamemodel.across.persistence;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import net.snake.gamemodel.across.bean.AcrossIncome;
import net.snake.ibatis.SystemFactory;

/**
 * 
 */

public class AcrossIncomeManager {
	private static final Logger logger = Logger.getLogger(AcrossIncomeManager.class);
	private static AcrossIncomeDAO dao = new AcrossIncomeDAO(SystemFactory.getCharacterSqlMapClient());

	// 单例实现=====================================
	private static AcrossIncomeManager instance;

	private AcrossIncomeManager() {

	}

	public static AcrossIncomeManager getInstance() {
		if (instance == null) {
			instance = new AcrossIncomeManager();
		}
		return instance;
	}

	public AcrossIncome selectAcrossIncomeByCharacterId(int characterId) {
		try {
			AcrossIncome ai = dao.selectByPrimaryKey(characterId);
			return ai;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * @param ai
	 */
	public void insert(AcrossIncome ai) {
		try {
			dao.insert(ai);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * @param ai
	 */
	public void updateAcrossIncome(AcrossIncome ai) {
		try {
			dao.updateByPrimaryKey(ai);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void resetAcrossIncomeShengWang() {
		try {
			dao.update();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
