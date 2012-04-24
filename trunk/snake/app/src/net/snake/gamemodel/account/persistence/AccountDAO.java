package net.snake.gamemodel.account.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.account.bean.Account;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AccountDAO {

	private SqlMapClient sqlMapClient;

	public AccountDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public int updateAccountInitiallyId(Account record) throws SQLException {
		int rows = sqlMapClient.update("t_account.updateAccountInitiallyId", record);
		return rows;
	}

	public int updateOnline(Account record) throws SQLException {
		int rows = sqlMapClient.update("t_account.updateOnline", record);
		return rows;
	}

	public int updateLoginInfo(Account record) throws SQLException {
		int rows = sqlMapClient.update("t_account.updateLoginInfo", record);
		return rows;
	}

	public Account selectByPrimaryKey(Integer id) throws SQLException {
		Account record = (Account) sqlMapClient.queryForObject("t_account.selectByPrimaryKey", id);
		return record;
	}

	public Integer insert(Account record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_account.insert", record);
		return (Integer) newKey;
	}

	@SuppressWarnings("rawtypes")
	public List getAccounts_GM() throws SQLException {
		List list = sqlMapClient.queryForList("t_account.getAccountsGM");
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getByLoginname(String loginname) throws SQLException {
		List list = sqlMapClient.queryForList("t_account.getByLoginname", loginname);
		return list;
	}

	public Account selectByYunyingIdAndSid(String yunyingId,String sid) throws SQLException {
		Account account = new Account();
		account.setYunyingId(yunyingId);
		account.setServer(sid);
		account = (Account)sqlMapClient.queryForObject("t_account.selectByYunyingIdAndSid", account);
		return account;
	}

	/**
	 * 增加消费的元宝数　
	 * 
	 * @param record
	 * @param consum
	 * @return
	 * @throws SQLException
	 */
	public int updateConsumYuanbaoByPrimaryKey(Account record, int consum) throws SQLException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", record.getId());
		map.put("consum", consum);
		int rows = sqlMapClient.update("t_account.update_consum_yuanbaoByPrimaryKey", map);
		return rows;
	}

	/**
	 * 交易减少元宝数
	 * 
	 * @param record
	 * @param yuanbao
	 * @return
	 * @throws SQLException
	 */
	public int updateTradeReduceYuanbaoByPrimaryKey(Account record, int yuanbao) throws SQLException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", record.getId());
		map.put("yuanbao", yuanbao);
		int rows = sqlMapClient.update("t_account.update_trade_reduceyuanbaoByPrimaryKey", map);
		return rows;
	}

	/**
	 * 交易增加元宝数
	 * 
	 * @param record
	 * @param yuanbao
	 * @return
	 * @throws SQLException
	 */
	public int updateTradeAddYuanbaoByPrimaryKey(Account record, int yuanbao) throws SQLException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", record.getId());
		map.put("yuanbao", yuanbao);
		int rows = sqlMapClient.update("t_account.update_trade_addyuanbaoByPrimaryKey", map);
		return rows;
	}
}
