package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.CharacterCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * @author serv_dev
 */
public class CharacterCountManager {
	private static final Logger logger = Logger.getLogger(CharacterCountManager.class);
	CharacterCount characterCount;
	CharacterCountDAO dao = new CharacterCountDAO(SystemFactory.getCharacterSqlMapClient());
	private static CharacterCountManager instacne;

	private CharacterCountManager() {

	}

	public static CharacterCountManager getInstance() {
		if (instacne == null) {
			instacne = new CharacterCountManager();
		}
		return instacne;
	}

	public void asyncUpdate(final CharacterCount count) {
		GameServer.executorServiceForDB.submit(new Runnable() {
			@Override
			public void run() {
				try {
					dao.update(count);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void asyncInsert(final CharacterCount count) {
		GameServer.executorServiceForDB.submit(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insert(count);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CharacterCount> loadByCharacter(Hero character) throws SQLException {
		List selectByExample = dao.select(character.getId());
		return selectByExample;
	}
}
