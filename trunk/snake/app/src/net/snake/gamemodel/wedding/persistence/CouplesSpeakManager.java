package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.gamemodel.wedding.bean.CouplesSpeak;
import net.snake.gamemodel.wedding.logic.CouplesSpeakController;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 夫妻话语
 * 
 */

public class CouplesSpeakManager {
	private static final Logger logger = Logger.getLogger(CouplesSpeakManager.class);
	public Object lock = new Object();
	private CouplesSpeakDAO dao = new CouplesSpeakDAO(SystemFactory.getCharacterSqlMapClient());
	private Map<Integer, CouplesSpeakController> map = new ConcurrentHashMap<Integer, CouplesSpeakController>();
	// 单例实现=====================================
	private static CouplesSpeakManager instance;

	private CouplesSpeakManager() {

	}

	public static CouplesSpeakManager getInstance() {
		if (instance == null) {
			instance = new CouplesSpeakManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<CouplesSpeak> getListByMaleId(int male) {
		try {
			return dao.selectByMale(male);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<CouplesSpeak>();
	}

	/**
	 * 添加登入玩家
	 * 
	 * @param maleId
	 * @return
	 */
	public CouplesSpeakController fuqiLonginInitCouplesSpeak(int maleId, int feMaleId) {
		CouplesSpeakController csc = null;
		synchronized (lock) {
			csc = map.get(maleId);
			if (csc != null) {
				return csc;
			}
			csc = new CouplesSpeakController(maleId, feMaleId);
			map.put(maleId, csc);
		}
		final CouplesSpeakController temp = csc;
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				temp.init();
			}
		});
		return csc;
	}

	public void removeCouplesSpeakController(int maleId) {
		CouplesSpeakController csc = null;
		synchronized (lock) {
			csc = map.remove(maleId);
		}
		if (csc != null) {
			csc.destroy();
		}
	}

	public void deleteCouplesSpeakContriller(final int maleId) {
		removeCouplesSpeakController(maleId);
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				deleteByMaleId(maleId);
			}
		});
	}

	/**
	 * @param cs
	 */
	public void insert(CouplesSpeak cs) {
		try {
			dao.insert(cs);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param id
	 */
	public void deleteById(int id) {
		try {
			dao.deleteByPrimaryKey(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 更新
	 * 
	 * @param speakId
	 */
	public void updateIsNoticeBySpeakId(Integer speakId) {
		try {
			dao.updateIsNoticeBySpeakId(speakId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteByMaleId(int maleId) {
		try {
			dao.deleteByMaleId(maleId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
