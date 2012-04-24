package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.logic.CouplesController;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 夫妻数据库表管理
 * 
 */

public class CouplesManager {
	private static final Logger logger = Logger.getLogger(CouplesManager.class);
	public Object lock = new Object();
	private CouplesDAO dao = new CouplesDAO(SystemFactory.getCharacterSqlMapClient());
	private Map<Integer, CouplesController> map = new ConcurrentHashMap<Integer, CouplesController>();
	// 单例实现=====================================
	private static CouplesManager instance;

	private CouplesManager() {
		init();
	}

	public void init() {
		List<Couples> list = getCouplesList();
		if (list.size() < 1) {
			return;
		}
		for (Couples couples : list) {
			CouplesController cc = new CouplesController(couples);
			map.put(couples.getMaleId(), cc);
			map.put(couples.getFemaleId(), cc);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Couples> getCouplesList() {
		try {
			return dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<Couples>();
	}

	public static CouplesManager getInstance() {
		if (instance == null) {
			instance = new CouplesManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<Couples> getListByMaleId(int male) {
		try {
			return dao.selectByMale(male);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return new ArrayList<Couples>();
	}

	/**
	 * 玩家登入获取结婚数据
	 * 
	 * @param maleId
	 * @return
	 */
	public CouplesController getCouplesController(Hero character) {
		synchronized (lock) {
			return map.get(character.getId());
		}
	}

	/**
	 * 玩家登入获取结婚数据
	 * 
	 * @param maleId
	 * @return
	 */
	public CouplesController getCouplesController(int characterId) {
		synchronized (lock) {
			return map.get(characterId);
		}
	}

	/**
	 * 删除Couples
	 * 
	 * @param cc
	 */
	public void removeCouplesController(Couples cc) {
		synchronized (lock) {
			this.map.remove(cc.getMaleId());
			this.map.remove(cc.getFemaleId());
		}
		final int id = cc.getId();
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				deleteById(id);
			}
		});
	}

	/**
	 * @param cs
	 */
	public void insert(Couples cs) {
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
	 * @param character
	 * @param wedder
	 * @param wr
	 */
	public boolean createCouples(Hero appllyer, Hero wedder, WeddingRing wr) {
		Couples couples = null;
		synchronized (lock) {
			if (appllyer.getMyFriendManager().getRoleWedingManager().isWedding()) {
				appllyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17517, wedder.getViewName()));
				return false;
			}
			if (wedder.getMyFriendManager().getRoleWedingManager().isWedding()) {
				appllyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17517, wedder.getViewName()));
				return false;
			}
			boolean b = wedder.getCharacterGoodController().getBagGoodsContiner().deleteGoodsByModel(wr.getRingId(), 1);
			if (!b) {
				appllyer.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17517, wedder.getViewName()));
				return false;
			}
			couples = new Couples();
			couples.setApplyWeddingId(appllyer.getId());
			couples.setRingId(wr.getRingId());
			couples.setWeddingDate(new Date());
			CouplesController cc = new CouplesController(couples);
			appllyer.getMyFriendManager().getRoleWedingManager().weddingSuccess(cc, wedder, wr);
			wedder.getMyFriendManager().getRoleWedingManager().weddingSuccess(cc, appllyer, wr);
			map.put(appllyer.getId(), cc);
			map.put(wedder.getId(), cc);
		}
		final Couples temp = couples;
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				insert(temp);
			}
		});
		return true;
	}

	/**
	 * 夫妻数据更新
	 * 
	 * @param temp
	 */
	public void update(Couples temp) {
		try {
			dao.updateByPrimaryKeySelective(temp);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
