package net.snake.gamemodel.shop.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.shop.bean.TaskShoppingGoods;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 购买商城道具时任务所需的
 * 
 * @author serv_dev
 */

public class TaskShoppingManager {
	private static final Logger logger = Logger.getLogger(TaskShoppingManager.class);

	private static TaskShoppingGoodsDAO dao = new TaskShoppingGoodsDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static TaskShoppingManager instance;

	private TaskShoppingManager() {
	}

	public static TaskShoppingManager getInstance() {
		if (instance == null) {
			instance = new TaskShoppingManager();
		}
		return instance;
	}

	// 单例实现========================================
	@SuppressWarnings("unchecked")
	public List<TaskShoppingGoods> getCharacterTaskShoppingGoodsList(Integer characterId) throws SQLException {
		return dao.selectByCharacterId(characterId);
	}

	public void delTaskShoppingByCharacterId(int characterId) {
		try {
			dao.deleteByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynUpdataCharacterTaskShoppingGoods(Hero character, final TaskShoppingGoods taskShoppingGoods) {
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				try {
					dao.updateByPrimaryKey(taskShoppingGoods);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void asynInsertCharacterTaskShoppingGoods(Hero character, final TaskShoppingGoods taskShoppingGoods) {
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				try {
					dao.insert(taskShoppingGoods);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

}
