package net.snake.gamemodel.pet.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 马和人物模型关系表
 * 
 * @author serv_dev
 * 
 */
public class CharacterHorseDataProvider {
	private static final Logger logger = Logger.getLogger(CharacterHorseDataProvider.class);
	private CharacterHorseDAO characterHorseDAO = new CharacterHorseDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterHorseDataProvider instance;

	private CharacterHorseDataProvider() {
	}

	public static CharacterHorseDataProvider getInstance() {
		if (instance == null) {
			instance = new CharacterHorseDataProvider();
		}
		return instance;
	}

	// 单例实现========================================

	public void insertCharacterHorse(CharacterHorse characterhorse) throws SQLException {
		int id = characterHorseDAO.insert(characterhorse);
		characterhorse.setId(id);
	}

	public void deleteCharacterHorse(CharacterHorse characterhorse) throws SQLException {
		characterHorseDAO.deleteByPrimaryKey(characterhorse.getId());
	}

	public void updateCharacterHorse(CharacterHorse characterhorse) throws SQLException {
		characterHorseDAO.update(characterhorse);
	}

	public void asynDeleteCharacterHorse(Hero character, final CharacterHorse characterhorse) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					deleteCharacterHorse(characterhorse);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	/**
	 * 获得当前骑乘的坐骑
	 * 
	 * @param characterid
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public CharacterHorse getRideHorseByCharacterID(int characterid) throws SQLException {
		List<CharacterHorse> list = characterHorseDAO.selectByStatus(characterid);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	public List<CharacterHorse> getCharacterHorseByCharacterID(int characterid) throws SQLException {
		return characterHorseDAO.selectByCharacterId(characterid);
	}

	public void asynchronousUpdateCharacterHorse(final CharacterHorse ch, Hero character) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					updateCharacterHorse(ch);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void updateXingfu() {
		try {
			characterHorseDAO.updateXingfu();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
