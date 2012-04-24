package net.snake.gamemodel.language.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.language.bean.LanguageScript;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class LanguageScriptManager {
	private static final Logger logger = Logger.getLogger(LanguageScriptManager.class);
	private LanguageScriptDAO languageScriptDAO;
	
	public LanguageScriptManager(SqlMapClient sqlMapClient) {
		languageScriptDAO = new LanguageScriptDAO(sqlMapClient);
	}
	
	/**
	 * @description 获得所有脚本中文提示的map
	 * @return
	 */
	public Map<String, String> getAllLanguageScriptMap() {
		return this.fillLanguageScriptMapByList(this.getAllLanguageScript());
	}
	
	/**
	 * @description 获得所有脚本中文提示
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<LanguageScript> getAllLanguageScript() {
		try {
			return languageScriptDAO.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
	
	/**
	 * @description 包装填充中文提示集合
	 * @param list
	 * @return
	 */
	private Map<String, String> fillLanguageScriptMapByList (List<LanguageScript> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		for (LanguageScript languageScript : list) {
			map.put(languageScript.getKey(), languageScript.getValueI18n());
		}
		return map;
	}
}
