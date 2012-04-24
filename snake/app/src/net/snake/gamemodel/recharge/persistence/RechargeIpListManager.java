package net.snake.gamemodel.recharge.persistence;

import java.util.HashMap;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.recharge.bean.RechargeIpList;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class RechargeIpListManager implements CacheUpdateListener {
	private RechargeIpListDAO rechargeIpListDAO = new RechargeIpListDAO(SystemFactory.getGamedataSqlMapClient());

	private Map<String, RechargeIpList> ipMap = new HashMap<String, RechargeIpList>();

	private static Logger logger = Logger.getLogger(RechargeIpListManager.class);

	private static RechargeIpListManager instance;

	private RechargeIpListManager() {
	}

	public static RechargeIpListManager getInstance() {
		if (null == instance) {
			instance = new RechargeIpListManager();
		}
		return instance;
	}

	/**
	 * @description 是否是允许的充值IP
	 * @param ip
	 *            验证的IP
	 * @return true为合法，false为非法
	 */
	public boolean isRechargeIp(String ip) {
		if (ip == null || ip.length() < 7 || ip.length() > 15) {
			return Boolean.FALSE;
		}
		return ipMap.containsKey(ip);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			ipMap = BeanTool.listToMap(rechargeIpListDAO.select(), "rechargeIp");
		} catch (Exception e) {
			logger.error(e);
		}
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "rechargeIpList";
	}

	public RechargeIpListDAO getRechargeIpListDAO() {
		return rechargeIpListDAO;
	}

	public void setRechargeIpListDAO(RechargeIpListDAO rechargeIpListDAO) {
		this.rechargeIpListDAO = rechargeIpListDAO;
	}

}
