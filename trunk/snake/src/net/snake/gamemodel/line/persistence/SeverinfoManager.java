package net.snake.gamemodel.line.persistence;

import java.sql.SQLException;
import java.util.Date;

import net.snake.gamemodel.line.bean.Severinfo;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 开服信息
 * 
 */

public class SeverinfoManager {
	private static final Logger logger = Logger.getLogger(SeverinfoManager.class);
	private SeverinfoDAO dao = new SeverinfoDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static SeverinfoManager instance;
	private Severinfo serverInfo;

	private SeverinfoManager() {
		init();
	}

	public void init() {
		try {
			this.serverInfo = dao.selectByPrimaryKey(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateServerInfo() {
		if (serverInfo != null) {
			return;
		}
		serverInfo = new Severinfo();
		serverInfo.setId(1);
		serverInfo.setKaifuTime(new Date());
		try {
			dao.insert(serverInfo);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public static SeverinfoManager getInstance() {
		if (instance == null) {
			instance = new SeverinfoManager();
		}
		return instance;
	}

	public Date getKaifuTime() {
		if (serverInfo == null) {
			return new Date();
		}
		return serverInfo.getKaifuTime();
	}

}
