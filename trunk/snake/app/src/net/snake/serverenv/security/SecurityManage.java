package net.snake.serverenv.security;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.gm.bean.ManageIP;
import net.snake.gamemodel.gm.persistence.ManageIPDAO;
import net.snake.ibatis.SystemFactory;

/**
 * 检查IP是否是管理IP
 * 
 * @author serv_dev
 */
public class SecurityManage implements CacheUpdateListener {
	private static Logger logg = Logger.getLogger(SecurityManage.class);

	public List<String> list = new ArrayList<String>();
	ManageIPDAO dao = new ManageIPDAO(SystemFactory.getGamedataSqlMapClient());

	public boolean checkManageIP(String ip) {
		return list.contains(ip);
	}

	public SecurityManage() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		List<ManageIP> selectByExample = null;
		try {
			selectByExample = dao.select();
		} catch (SQLException e) {
			logg.error(e.getMessage(), e);
			return;
		}
		if (selectByExample == null || selectByExample.size() == 0) {
			return;
		}

		list.clear();
		for (ManageIP manageIP : selectByExample) {
			list.add(manageIP.getIp());
		}
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "manageip";
	}

}
