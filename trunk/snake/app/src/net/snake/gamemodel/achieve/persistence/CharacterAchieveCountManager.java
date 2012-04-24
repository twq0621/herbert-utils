package net.snake.gamemodel.achieve.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.fight.bean.CharacterAchieveCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterAchieveCountManager {
	private static final Logger logger = Logger.getLogger(CharacterAchieveCountManager.class);
	private static CharacterAchieveCountDAO dao = new CharacterAchieveCountDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterAchieveCountManager instance;

	private CharacterAchieveCountManager() {
	}

	public static CharacterAchieveCountManager getInstance() {
		if (instance == null) {
			instance = new CharacterAchieveCountManager();
		}
		return instance;
	}

	/**
	 * 更具角色id 进行的新手引导
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterAchieveCount> selecteListByCharacterId(int characterId) {
		try {
			return dao.select(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterAchieveCount>();
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	public void insert(CharacterAchieveCount cf) {
		try {
			dao.insert(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 异步插入CharacterGiftpacks
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynInsertCharacterAchieveCount(Hero character, final CharacterAchieveCount cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	public void update(CharacterAchieveCount cf) {
		try {
			dao.updateById(cf);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * CharacterGiftpacks异步更新
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynUpdateCharacterAchieveCount(Hero character, final CharacterAchieveCount cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				update(cf);
			}
		});
	}

	public void delete(CharacterAchieveCount cgp) {
		try {
			dao.deleteById(cgp.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynDeleteCharacterAchieveCount(Hero character, final CharacterAchieveCount cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				delete(cf);
			}
		});
	}
}
