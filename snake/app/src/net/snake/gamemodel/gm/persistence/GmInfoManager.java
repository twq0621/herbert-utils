package net.snake.gamemodel.gm.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.gm.bean.GmInfo;
import net.snake.ibatis.SystemFactory;



import com.ibatis.sqlmap.client.SqlMapClient;
import org.apache.log4j.Logger;
/**
 * GM账号处理,单例实现
 * 
 * @author serv_dev
 */
public class GmInfoManager implements CacheUpdateListener {

	private static Logger logger = Logger.getLogger(GmInfoManager.class);
	private static GmInfoManager instance = new GmInfoManager();

	private static Map<String, GmInfo> map = new ConcurrentHashMap<String, GmInfo>();

	private SqlMapClient sqlMapClient = SystemFactory.getGamedataSqlMapClient();
	GmInfoDAO gmInfoDAO = new GmInfoDAO(sqlMapClient);

	private GmInfoManager() {
	}

	public static GmInfoManager getInstance() {
		return instance;
	}

	/**
	 * 验证并处理GM账号 验证是否为GM账号，并操作信息处理
	 * 
	 * @param account
	 *            用户对象
	 * @return true为是GM账号，false为否
	 */
	public boolean validateAndOptionGM(Account account) {
		if (isGm(account)) {
			account.setGm(Integer.valueOf(1));
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	/**
	 * 获得全部可用GM信息，并填充内存查询集合
	 * 
	 * @param sqlMapClient
	 */
	@SuppressWarnings("unchecked")
	private void findAllGmInfo() {
		List<GmInfo> list = null;
		try {
			list = gmInfoDAO.select(1);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return;
		}
		// 是否有GM信息
		if (list == null || list.isEmpty()) {
			return;
		}
		for (GmInfo info : list) {
			map.put(info.getYunyingId(), info);
		}
	}

	/**
	 * 验证是否为GM账号
	 * 
	 * @param account
	 *            用户对象
	 * @return true为是，false为否
	 */
	private boolean isGm(Account account) {
		if (account.getYunyingId() == null) {
			return Boolean.FALSE;
		}
		return map.containsKey(account.getYunyingId());
	}

	@Override
	public void reload() {
		if (map == null) {
			map = new ConcurrentHashMap<String, GmInfo>();
		}
		map.clear();
		findAllGmInfo();
	}

	@Override
	public String getAppname() {

		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {

		return "gminfo";
	}
}
