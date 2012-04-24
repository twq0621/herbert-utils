package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.gamemodel.skill.bean.Skill;
import net.snake.ibatis.SystemFactory;



/**
 * 玩家技能数据库操作类
 * 
 * @author benchild
 */
public class SkillDao {
	private static Logger logger = Logger.getLogger(SkillDao.class);


	@SuppressWarnings("unchecked")
	public Map<Integer, Skill> getSkillMap() {
		Map<Integer, Skill> skillMap = null;
		try {
			skillMap = SystemFactory.getGamedataSqlMapClient().queryForMap("skillGetAll", null, "id");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return skillMap;
	}
}
