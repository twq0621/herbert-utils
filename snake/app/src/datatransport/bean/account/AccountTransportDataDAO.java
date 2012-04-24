package datatransport.bean.account;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AccountTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public AccountTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public Integer insert(AccountTransportData record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_account.insert", record);
		return (Integer) newKey;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_account.deleteByPrimaryKey", id);
		return rows;
	}

	public AccountTransportData selectByPrimaryKey(Integer id) throws SQLException {
		AccountTransportData record = (AccountTransportData) sqlMapClient.queryForObject("t_account.selectByPrimaryKey", id);
		return record;
	}

}
