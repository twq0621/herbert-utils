package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 宝箱
 * 
 * 
 */

public class ChestManager {
	private static final Logger logger = Logger.getLogger(ChestManager.class);

	private static ChestDAO dao = new ChestDAO(SystemFactory.getCharacterSqlMapClient());

	// 单例实现=====================================
	private static ChestManager instance;

	private ChestManager() {

	}

	public static ChestManager getInstance() {
		if (instance == null) {
			instance = new ChestManager();
		}
		return instance;
	}

	// 单例实现========================================

	/**
	 * 领奖
	 * 
	 * @param character_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Chest> getChestType_1(int character_id) {
		List<Chest> list = null;
		try {
			Chest chest = new Chest();
			chest.setCharacterId(character_id);
			chest.setType(CommonUseNumber.byte1);
			list = dao.selectByTpe(chest);
		} catch (SQLException e) {
			list = new ArrayList<Chest>();
			logger.error(e.getMessage(), e);
		}

		return list;
	}

	/**
	 * 未领奖
	 * 
	 * @param character_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Chest> getChestType_0(int character_id) {
		List<Chest> list = null;
		try {
			Chest chest = new Chest();
			chest.setCharacterId(character_id);
			chest.setType(CommonUseNumber.byte0);
			list = dao.selectByTpe(chest);
		} catch (SQLException e) {
			list = new ArrayList<Chest>();
			logger.error(e.getMessage(), e);
		}

		return list;
	}

	public void deleteChest() {
		try {
			dao.deleteChest();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<Chest> selectDeleteChest() {
		try {
			List<Chest> list = dao.getDeleteChestList();
			return list;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void addChest(Chest chest) {
		try {
			dao.insertSelective(chest);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateChest(Chest chest) {
		try {
			dao.updateByPrimaryKeySelective(chest);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
