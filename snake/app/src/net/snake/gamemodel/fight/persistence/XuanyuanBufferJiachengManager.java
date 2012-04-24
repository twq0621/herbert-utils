package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.fight.bean.XuanyuanBufferJiacheng;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class XuanyuanBufferJiachengManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(XuanyuanBufferJiachengManager.class);
	private XuanyuanBufferJiachengDAO dao = new XuanyuanBufferJiachengDAO(SystemFactory.getGamedataSqlMapClient());
	Map<Integer, XuanyuanBufferJiacheng> map = new HashMap<Integer, XuanyuanBufferJiacheng>();
	// 单例实现=====================================
	private static XuanyuanBufferJiachengManager instance;

	private XuanyuanBufferJiachengManager() {
		reload();
	}

	public static XuanyuanBufferJiachengManager getInstance() {
		if (instance == null) {
			instance = new XuanyuanBufferJiachengManager();
		}
		return instance;
	}

	/**
	 * 初始化hu数据
	 */
	@SuppressWarnings("unchecked")
	private void initDate() {
		List<XuanyuanBufferJiacheng> list2 = new ArrayList<XuanyuanBufferJiacheng>();
		try {
			list2 = dao.select();
			BeanTool.addOrUpdate(map, list2, "bufferId");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public XuanyuanBufferJiacheng getXuanyuanBufferByBufferID(int bufferId) {
		return map.get(bufferId);
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "xuanyuanjianBuffer";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.snake.commons.dbversioncache.CacheUpdateListener#reload()
	 */
	@Override
	public void reload() {
		initDate();
	}

}
