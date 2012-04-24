package net.snake.gamemodel.panel.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.panel.bean.RoleUpgradeDesc;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 面板管理类。
 * 
 * @@author serv_dev.
 * @version: 1.0
 * @Create at: 2011-2-21 上午09:58:58
 */
public class RoleUpgradeDescManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(RoleUpgradeDescManager.class);
	// private Map<Integer, RoleGradeExpDescController> map = new HashMap<Integer, RoleGradeExpDescController>();
	private RoleUpgradeDescDAO dao = new RoleUpgradeDescDAO(SystemFactory.getGamedataSqlMapClient());

	private static RoleUpgradeDescManager instance;

	public static RoleUpgradeDescManager getInstance() {
		if (instance == null) {
			instance = new RoleUpgradeDescManager();
		}
		return instance;
	}

	private RoleUpgradeDescManager() {
	}

	private void addRoleUpgradeDesc(Map<Integer, RoleGradeExpDescController> map1, RoleUpgradeDesc desc) {
		RoleGradeExpDescController rgc = map1.get(desc.getRoleGrade());
		if (rgc == null) {
			rgc = new RoleGradeExpDescController();
			map1.put(desc.getRoleGrade(), rgc);
		}
		rgc.addRoleUpgradeDesc(desc);
	}

	@SuppressWarnings("unchecked")
	public void reload() {
		try {
			List<RoleUpgradeDesc> list = dao.select();
			Map<Integer, RoleGradeExpDescController> map1 = new HashMap<Integer, RoleGradeExpDescController>();
			for (RoleUpgradeDesc desc : list) {
				addRoleUpgradeDesc(map1, desc);
			}
			// map = map1;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "RoleUpgradeDesc";
	}

	/**
	 * 角色升级调用此方法 发送升级新手引导
	 * 
	 * @param character
	 */

	// public void upGradeMSG(Hero character, long priExp) {
	// int grade = character.getGrade();
	// RoleGradeExpDescController rgc = map.get(grade);
	// if (rgc != null) {
	// rgc.upGradeMSG(character, priExp);
	// }
	// }
}
