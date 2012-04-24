package net.snake.serverenv.languageserver;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.gamemodel.language.bean.LanguageServer;
import net.snake.gamemodel.language.persistence.LanguageServerDAO;
import net.snake.ibatis.SystemFactory;


/**
 * 服务器国际化初始
 * 
 * @author serv_dev
 */
public class InitLanguageServer {

	private static Logger logger = Logger.getLogger(InitLanguageServer.class);

	/**
	 * 为服务器国际化静态类提供初始化数据集
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<Integer, String> init() {
		LanguageServerDAO languageServerDAO = new LanguageServerDAO( SystemFactory.getGamedataSqlMapClient());
		List<LanguageServer> languages = null;
		try {
			languages = languageServerDAO.selectLessThanId(500);
		} catch (SQLException e) {
			logger.error("init LanguageServer error : ");
			logger.error(e.getMessage(), e);
			languages = null;
		}
		return fillLanguageServerMap(languages);
	}

	/**
	 * @description 封装服务器国际化数据集
	 * @param languageServerList
	 * @return
	 */
	private static Map<Integer, String> fillLanguageServerMap(List<LanguageServer> languageServerList) {
		if (languageServerList == null || languageServerList.isEmpty()) {
			logger.error("init LanguageServer error : languageServerList is null!");
			return null;
		}
		Map<Integer, String> map = new HashMap<Integer, String>();
		Iterator<LanguageServer> it = languageServerList.iterator();
		while (it.hasNext()) {
			LanguageServer languageServer = it.next();
			map.put(languageServer.getId(), languageServer.getValue());
		}

		return map;
	}

}
