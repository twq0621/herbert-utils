package net.snake.gamemodel.account.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.exception.DataException;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 账号管理
 * 
 */

public class AccountManager {
	private static final Logger logger = Logger.getLogger(AccountManager.class);
	private AccountDAO dao = null;

	// 单例实现=====================================
	private static AccountManager instance;

	private AccountManager() {
		dao = new AccountDAO(SystemFactory.getAccountSqlMapClient());
	}

	public static AccountManager getInstance() {
		if (instance == null) {
			instance = new AccountManager();
		}
		return instance;
	}

	// 单例实现========================================
	/**
	 * 标记玩家目前是否在线的更新 上线、下线个更新一次
	 * 
	 * @param accountid
	 *            帐号id
	 * @param online
	 *            0不在线， 1在线
	 * @throws SQLException
	 */
	public void markOnline(Account account) throws SQLException {
		dao.updateOnline(account);
	}

	/**
	 * 通过帐号id，查询帐号信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Account selectByAccountid(int id) throws SQLException {
		return dao.selectByPrimaryKey(id);
	}

	/**
	 * 创建一个新帐号
	 * 
	 * @param account
	 * @throws DataException
	 */
	public void insert(Account account) throws DataException {
		try {
			// dao.insert(account);
			// 更新用户账户原始ID为账户主键ID
			// by:gudongbo
			dao.insert(account);
			dao.updateAccountInitiallyId(account);
		} catch (Exception e) {
			throw new DataException(this.getClass().getName(), "isInsert(Account account)", "注册用户失败！", e);
		}
	}

	/**
	 * 检查用户是否可以登录，登录成功返回登录信息
	 * 
	 * @param loginname
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public Account getByLoginname(String loginname) {
		try {
			List<Account> accountlist = dao.getByLoginname(loginname);
			if (accountlist.size() > 0) {
				return (Account) accountlist.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw null;
		}
		return null;
	}

	/**
	 * 更新玩家的登录信息
	 * 
	 * @param account
	 * @throws SQLException
	 */
	public void updateAccount(Account account) throws SQLException {
		dao.updateLoginInfo(account);
	}

	/**
	 * 通过运营id查询帐号信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Account selectByYunyingId(String yunyingId, String sid) throws SQLException {
		Account account = dao.selectByYunyingIdAndSid(yunyingId,sid);
		return account;
	}

	/**
	 * 返回是gm的人员id “,”隔开
	 * 
	 * @param list
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public String getAccounts_GM() {
		StringBuilder sb = new StringBuilder();
		List<Account> list = null;
		try {
			list = dao.getAccounts_GM();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		int count = list.size();
		for (int a = 0; a < count; a++) {
			sb.append(list.get(a).getId());
			if (a != count - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 更新消费值字段
	 * 
	 * @param account
	 * @param consum
	 * @throws SQLException
	 */
	public void updateConsumYuanbaoById(Account account, int consum) throws SQLException {
		dao.updateConsumYuanbaoByPrimaryKey(account, consum);
	}

	/**
	 * 交易增加无宝数，同时更新获得的元宝数和当前元宝数　
	 * 
	 * @param account
	 * @param consum
	 * @throws SQLException
	 */
	public void updateTradeAddYuanbaoById(Account account, int value) throws SQLException {
		dao.updateTradeAddYuanbaoByPrimaryKey(account, value);
	}

	/**
	 * 交易减少元宝数，同时更新失去的元宝数和当前元宝数
	 * 
	 * @param account
	 * @param consum
	 * @throws SQLException
	 */
	public void updateTradeReduceYuanbaoById(Account account, int value) throws SQLException {
		dao.updateTradeReduceYuanbaoByPrimaryKey(account, value);
	}

}
