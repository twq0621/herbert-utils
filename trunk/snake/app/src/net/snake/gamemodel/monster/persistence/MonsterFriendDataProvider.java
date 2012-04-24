package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterFriendData;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class MonsterFriendDataProvider {
	private static final Logger logger = Logger.getLogger(MonsterFriendDataProvider.class);
	private static MonsterFriendDataProvider instance;

	private MonsterFriendDataProvider() {
	}

	public static MonsterFriendDataProvider getInstance() {
		if (instance == null) {
			instance = new MonsterFriendDataProvider();
		}
		return instance;
	}

	private MonsterFriendDataDAO dao = new MonsterFriendDataDAO(SystemFactory.getCharacterSqlMapClient());

	@SuppressWarnings("unchecked")
	public List<MonsterFriendData> getMyMonsterFriend(int characterid) throws SQLException {
		List<MonsterFriendData> selectByExample = dao.selectByCharacterId(characterid);
		return selectByExample;
	}

	public void asynInsert(Hero character, final MonsterFriendData mfd) {
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				try {
					dao.insert(mfd);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void insert(MonsterFriendData mfd) throws SQLException {
		dao.insert(mfd);
	}

	public void delete(MonsterFriendData mfd) throws SQLException {
		dao.deleteById(mfd.getId());
	}

	public void asynUpdate(final Hero character, final MonsterFriendData friendData) {
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				try {
					dao.updateStateById(friendData);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void asynDelete(final Hero character, final MonsterFriendData friendData) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.deleteById(friendData.getId());
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});

	}

}
