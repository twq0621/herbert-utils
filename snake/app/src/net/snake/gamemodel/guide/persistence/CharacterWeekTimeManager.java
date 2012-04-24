package net.snake.gamemodel.guide.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.guide.bean.CharacterWeekTime;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class CharacterWeekTimeManager {
	private static final Logger logger = Logger.getLogger(CharacterWeekTimeManager.class);
	private static CharacterWeekTimeDAO dao = new CharacterWeekTimeDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterWeekTimeManager instance;

	private CharacterWeekTimeManager() {
	}

	public static CharacterWeekTimeManager getInstance() {
		if (instance == null) {
			instance = new CharacterWeekTimeManager();
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
	public CharacterWeekTime selecteListByCharacterId(int characterId, int week) {
		try {
			CharacterWeekTime query = new CharacterWeekTime();
			query.setfCharacterId(characterId);
			query.setfWeek(week);
			List<CharacterWeekTime> list = dao.selectByWeek(query);
			if (list == null || list.size() == 0) {
				return null;
			}
			return list.get(0);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	public void insert(CharacterWeekTime cf) {
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

	public void asynInsertCharacterGiftpacks(Hero character, final CharacterWeekTime cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	public void update(CharacterWeekTime cf) {
		try {
			dao.updateByPrimaryKey(cf);
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

	public void asynUpdateCharacterGiftpacks(Hero character, final CharacterWeekTime cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				if (cf.getfId() != null) {
					update(cf);
				} else {
					insert(cf);
				}
			}
		});
	}

	public void delete(CharacterWeekTime cgp) {
		try {
			dao.deleteByPrimaryKey(cgp.getfId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynDeleteCharacterGiftpacks(Hero character, final CharacterWeekTime cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				delete(cf);
			}
		});
	}
}
