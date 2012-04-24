package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.skill.bean.SkillUpgradeExp;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * @author serv_dev
 */
public class SkillUpgradeExpManage implements CacheUpdateListener {

	private static final Logger logger = Logger.getLogger(SkillUpgradeExpManage.class);
	private static SkillUpgradeExpManage manage;
	private SkillUpgradeExpDAO dao = new SkillUpgradeExpDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, HashMap<Integer, SkillUpgradeExp>> map = new HashMap<Integer, HashMap<Integer, SkillUpgradeExp>>();

	public static SkillUpgradeExpManage getInstance() {
		if (manage == null) {
			manage = new SkillUpgradeExpManage();
		}
		return manage;
	}

	private SkillUpgradeExpManage() {
	}

	@Override
	public void reload() {
		try {
			@SuppressWarnings("unchecked")
			List<SkillUpgradeExp> selectByExample = dao.select();
			for (SkillUpgradeExp skillUpgradeExp : selectByExample) {
				Integer skillId = skillUpgradeExp.getSkillId();
				HashMap<Integer, SkillUpgradeExp> hashMap = map.get(skillId);
				if (hashMap == null) {
					hashMap = new HashMap<Integer, SkillUpgradeExp>();
					map.put(skillId, hashMap);
				}
				Integer skillGrade = skillUpgradeExp.getSkillGrade();
				hashMap.put(skillGrade, skillUpgradeExp);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取当前等级所需的消耗
	 * 
	 * @param skillid技能ID
	 * @param grade
	 *            当前等级
	 * @return
	 */
	public SkillUpgradeExp getSkillUpgradeExp(int skillid, int grade) {
		HashMap<Integer, SkillUpgradeExp> hashMap = map.get(skillid);
		if (hashMap != null) {
			return hashMap.get(grade);
		}
		return null;
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "skillUpgradeExp";
	}
}
