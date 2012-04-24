package net.snake.gamemodel.achieve.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.fight.bean.CharacterAchieve;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterAchieveManager {
	private static Logger logger = Logger.getLogger(CharacterAchieveManager.class);

	private static CharacterAchieveDAO dao = new CharacterAchieveDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterAchieveManager instance;

	private CharacterAchieveManager() {
	}

	public static CharacterAchieveManager getInstance() {
		if (instance == null) {
			instance = new CharacterAchieveManager();
		}
		return instance;
	}

	/**
	 * 根据角色id得到角色的成就
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterAchieve> selecteListByCharacterId(int characterId) {
		try {
			return dao.select(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return new ArrayList<CharacterAchieve>();
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	public void insert(CharacterAchieve cf) {
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

	public void asynInsertCharacterAchieve(Hero character, final CharacterAchieve cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	public void update(CharacterAchieve cf) {
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

	public void asynUpdateCharacterAchieve(Hero character, final CharacterAchieve cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				update(cf);
			}
		});
	}

	public void delete(CharacterAchieve cgp) {
		try {
			dao.deleteById(cgp.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynDeleteCharacterAchieve(Hero character, final CharacterAchieve cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				delete(cf);
			}
		});
	}
}
