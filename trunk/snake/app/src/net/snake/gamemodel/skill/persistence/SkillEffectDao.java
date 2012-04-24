package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 技能效果数据库操作类
 * 
 * @author benchild
 */
public class SkillEffectDao {
	private static final Logger logger = Logger.getLogger(SkillEffectDao.class);

	public SqlMapClient getSqlMapClient() {
		return SystemFactory.getGamedataSqlMapClient();
	}

	@SuppressWarnings("unchecked")
	public Map<String, SkillEffect> getSkillEffectMap() {
		try {
			return getSqlMapClient().queryForMap("skillEffectGetAll", null,
					"id");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
