package net.snake.gamemodel.skill.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.skill.bean.SkillEffect;

/**
 * 技能效果管理
 * 
 * @author benchild
 */

public class SkillEffectManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(SkillEffectManager.class);
	private static SkillEffectDao dao = new SkillEffectDao();
	// 单例实现=====================================
	private static SkillEffectManager instance;

	private SkillEffectManager() {
	}

	public static SkillEffectManager getInstance() {
		if (instance == null) {
			instance = new SkillEffectManager();
		}
		return instance;
	}

	// 单例实现========================================
	private Map<Integer, SkillEffect> cacheSkillEffect = new HashMap<Integer, SkillEffect>();

	private Map<String, SkillEffect> getSkillEffectMap() {
		return dao.getSkillEffectMap();
	}

	public Map<Integer, SkillEffect> getCacheSkillEffect() {
		return cacheSkillEffect;
	}

	@Override
	public void reload() {
		try {
			BeanTool.addOrUpdate(cacheSkillEffect, getSkillEffectMap(), "id");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 通过id获取skillEffect
	 * 
	 * @param id
	 * @return
	 */
	public SkillEffect getSkillEffectById(int id) {
		return this.cacheSkillEffect.get(id);
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "skilleffect";
	}
}
