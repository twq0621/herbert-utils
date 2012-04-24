package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.activities.persistence.XianshiActivityRewardManager;
import net.snake.gamemodel.fight.bean.XuanyuanjianConfig;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class XuanyuanjianConfigManager implements CacheUpdateListener {
	private static final Logger logger = Logger
			.getLogger(XuanyuanjianConfigManager.class);
	private XuanyuanjianConfigDAO dao = new XuanyuanjianConfigDAO(
			SystemFactory.getGamedataSqlMapClient());
	List<XuanyuanjianConfig> list = new ArrayList<XuanyuanjianConfig>();
	// 单例实现=====================================
	private static XuanyuanjianConfigManager instance;

	private XuanyuanjianConfigManager() {
		reload();
	}

	public static XuanyuanjianConfigManager getInstance() {
		if (instance == null) {
			instance = new XuanyuanjianConfigManager();
		}
		return instance;
	}

	public List<XuanyuanjianConfig> getXuanjianList() {
		return list;
	}

	/**
	 * 初始化hu数据
	 */
	@SuppressWarnings("unchecked")
	private void initDate() {
		XianshiActivityRewardManager.getInstance().reload();
		try {
			list = dao.select();
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
		return "xuanyuanjian";
	}

	@Override
	public void reload() {
		initDate();
	}

}
