package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.gamemodel.hero.bean.Charactergrade;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 等级管理
 * 
 * @author serv_dev
 * 
 */

public class CharactergradeManager {
	private static final Logger logger = Logger.getLogger(CharactergradeManager.class);
	private Map<String, Charactergrade> charactergradeMap = new HashMap<String, Charactergrade>();
	private static CharactergradeDAO dao = new CharactergradeDAO(SystemFactory.getGamedataSqlMapClient());
	// 单例实现=====================================
	private static CharactergradeManager instance;

	private CharactergradeManager() {
		reload();
	}

	public static CharactergradeManager getInstance() {
		if (instance == null) {
			instance = new CharactergradeManager();
		}
		return instance;
	}

	// 单例实现========================================

	public Charactergrade getCharactergradeByKey(String key) {
		return charactergradeMap.get(key);
	}

	/**
	 * 获得等级对应的最大经验值
	 * 
	 * @param grade
	 * @return
	 */
	public long getCharacterNextExpByGrade(int popsinger, short grade) {
		Long exp = charactergradeMap.get(popsinger + "_" + grade).getExpAdd();
		return exp == null ? 0l : exp;
	}

	private Map<String, Charactergrade> getCharactergrade() {
		try {
			Map<String, Charactergrade> chMap = dao.getcharactergrade();
			return chMap;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void reload() {
		try {
			BeanTool.addOrUpdate(charactergradeMap, getCharactergrade(), "key");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

}
