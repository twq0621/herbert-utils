package net.snake.gamemodel.line.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.line.bean.ServerEntry;
import net.snake.ibatis.SystemFactory;

public class ServerListManager implements CacheUpdateListener {
	ServerEntryDAO serverEntryDAO = new ServerEntryDAO(SystemFactory.getGamedataSqlMapClient());
	Map<Integer, ServerEntry> serverlistmap = new HashMap<Integer, ServerEntry>();

	private static Logger logger = Logger.getLogger(ServerListManager.class);

	private static ServerListManager instance;

	private ServerListManager() {
	}

	public static ServerListManager getInstance() {
		if (null == instance) {
			instance = new ServerListManager();
		}
		return instance;
	}

	public ServerEntry getServerEntryByID(int id) {
		return serverlistmap.get(id);
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "serverlist";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			serverlistmap = BeanTool.listToMap(serverEntryDAO.select(), "loginserverid");
		} catch (Exception e) {
			logger.error(e);
		}
	}
}
