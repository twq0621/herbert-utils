package net.snake.gamemodel.across.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class AcrossEtcManager {
	private static final Logger logger = Logger.getLogger(AcrossEtcManager.class);
	private Object lock = new Object();
	private static AcrossEtcDAO dao = new AcrossEtcDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static AcrossEtcManager instance;
	private Map<Integer, AcrossEtc> characterIDMap = new ConcurrentHashMap<Integer, AcrossEtc>();
	private Map<Integer, AcrossEtc> accountIDMap = new ConcurrentHashMap<Integer, AcrossEtc>();
	private Map<String, AcrossEtc> oldcharacterIdMap = new ConcurrentHashMap<String, AcrossEtc>();
	private Map<String, AcrossEtc> oldaccountIDMap = new ConcurrentHashMap<String, AcrossEtc>();

	private AcrossEtcManager() {
	}

	public static AcrossEtcManager getInstance() {
		if (instance == null) {
			instance = new AcrossEtcManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		try {
			List<AcrossEtc> list = dao.select();
			for (AcrossEtc ae : list) {
				characterIDMap.put(ae.getCharacterId(), ae);
				accountIDMap.put(ae.getAccountId(), ae);
				oldcharacterIdMap.put(ae.getOldAreaId() + "_" + ae.getOldCharacterInitiallyId(), ae);
				oldaccountIDMap.put(ae.getOldAreaId() + "_" + ae.getOldAccountInitiallyId(), ae);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void addAcrossEtc(AcrossEtc ae) {
		synchronized (lock) {
			characterIDMap.put(ae.getCharacterId(), ae);
			accountIDMap.put(ae.getAccountId(), ae);
			oldcharacterIdMap.put(ae.getOldAreaId() + "_" + ae.getOldCharacterInitiallyId(), ae);
			oldaccountIDMap.put(ae.getOldAreaId() + "_" + ae.getOldAccountInitiallyId(), ae);
		}
		insert(ae);
	}

	public void insert(AcrossEtc ae) {
		try {
			dao.insert(ae);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public AcrossEtc removeAcrossEtcByOldCharacterId(int oldCharacterID, int oldServerId) {
		AcrossEtc ae = null;
		synchronized (lock) {
			ae = oldcharacterIdMap.remove(oldServerId + "_" + oldCharacterID);
			if (ae != null) {
				accountIDMap.remove(ae.getAccountId());
				characterIDMap.remove(ae.getCharacterId());
				oldaccountIDMap.remove(ae.getOldAreaId() + "_" + ae.getOldAccountId());

			}
		}
		if (ae != null) {
			delete(ae);
		}
		return ae;
	}

	/**
	 * 根据角色原始id，原始服务器id 获取角色信息
	 * 
	 * @param oldCharacterID
	 * @param oldServerId
	 * @return
	 */
	public AcrossEtc getAcrossEtcByOldCharacterInitiallyId(int oldServerId, int oldCharacterID) {
		AcrossEtc ae = oldcharacterIdMap.get(oldServerId + "_" + oldCharacterID);
		return ae;
	}

	public void delete(AcrossEtc ae) {
		try {
			dao.deleteById(ae.getId());
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public AcrossEtc getAcorssByCharacterId(Integer characterid) {
		return characterIDMap.get(characterid);
	}

	/**
	 * 原始角色id
	 * 
	 * @param serverId
	 * @param accountId
	 * @return
	 */
	public AcrossEtc getAcrossEtcByOldAccountInitiallyId(int serverId, int accountId) {
		AcrossEtc ae = oldaccountIDMap.get(serverId + "_" + accountId);
		return ae;
	}

	/**
	 * @param ae
	 */
	public void updateToDb(AcrossEtc ae) {
		try {
			dao.updateById(ae);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
