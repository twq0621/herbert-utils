package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.wedding.bean.CharacterWeddingring;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 夫妻
 * 
 */

public class CharacterWeddingringManager {
	private static final Logger logger = Logger.getLogger(CharacterWeddingringManager.class);
	public Object lock = new Object();
	private CharacterWeddingringDAO dao = new CharacterWeddingringDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterWeddingringManager instance;

	private CharacterWeddingringManager() {

	}

	public static CharacterWeddingringManager getInstance() {
		if (instance == null) {
			instance = new CharacterWeddingringManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<CharacterWeddingring> getCharacterWedingRingList(int characterId) {
		try {
			return dao.selectByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<CharacterWeddingring>();
	}

	/**
	 * 线程插入
	 * 
	 * @param ring
	 */
	public void threadInsert(final CharacterWeddingring ring) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				insert(ring);
			}
		});
	}

	private void insert(CharacterWeddingring ring) {
		try {
			dao.insert(ring);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param ring
	 */
	public void threadUpdate(final CharacterWeddingring ring) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				update(ring);
			}
		});
	}

	private void update(CharacterWeddingring ring) {
		try {
			dao.updateByPrimaryKey(ring);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param femaleGood
	 * @param maleId
	 * @param date
	 * @param femaleId
	 */
	public void threadAddOrUpdate(Integer ringId, Integer myId, Date date, Integer partnerId) {
		final CharacterWeddingring ring = new CharacterWeddingring();
		ring.setCharacterId(myId);
		ring.setRingId(ringId);
		ring.setPartnerId(partnerId);
		ring.setWeddingDate(date);
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				addOrUpdate(ring);
			}
		});
	}

	private void addOrUpdate(CharacterWeddingring ring) {
		try {
			int sun = dao.updateByCharacterId(ring);
			if (sun < 1) {
				dao.insert(ring);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param temp
	 */
	public void threadDelete(final CharacterWeddingring temp) {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					dao.deleteByPrimaryKey(temp.getId());
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});

	}
}
