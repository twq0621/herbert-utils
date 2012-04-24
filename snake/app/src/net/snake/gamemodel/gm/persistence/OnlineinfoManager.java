package net.snake.gamemodel.gm.persistence;

import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.gamemodel.gm.bean.Onlineinfo;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;



/**
 * 人物在线统计
 * 
 */

public class OnlineinfoManager {
	private static Logger logger = Logger.getLogger(OnlineinfoManager.class);

	private static OnlineinfoDAO dao = new OnlineinfoDAO(SystemFactory.getAccountSqlMapClient());

	private Onlineinfo onlineinfo;
	// 单例实现=====================================
	private static OnlineinfoManager instance;

	private OnlineinfoManager() {
		onlineinfo = new Onlineinfo();
	}

	public static OnlineinfoManager getInstance() {
		if (instance == null) {
			instance = new OnlineinfoManager();
		}
		return instance;
	}

	// 单例实现========================================

	/**
	 * 日志添加
	 * 
	 * @throws SQLException
	 */
	private void insert(final Onlineinfo onlineinfo) throws SQLException {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				try {
					dao.insert(onlineinfo);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});

	}

	public void init() {
		int players = GameServer.vlineServerManager.getOnlineCount();
		Date t = new Date();
		onlineinfo.setCount(players);
		onlineinfo.setServerId(Options.ServerId);
		onlineinfo.setCreateDate(t);
		onlineinfo.setServerLineid(0);

		try {
			insert(onlineinfo);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	Runnable beeper = new Runnable() {
		public void run() {
			try {
				int players = GameServer.vlineServerManager.getOnlineCount();
				Date t = new Date();
				onlineinfo.setCount(players);
				onlineinfo.setServerId(Options.ServerId);
				onlineinfo.setCreateDate(t);

				insert(onlineinfo);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	};
}
