package datatransport.bean.acrossincome;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AcrossIncomeTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public AcrossIncomeTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public AcrossIncomeTransportData selectByPrimaryKey(Integer characterId) throws SQLException {
		AcrossIncomeTransportData record = (AcrossIncomeTransportData) sqlMapClient.queryForObject("t_across_income.selectByPrimaryKey", characterId);
		return record;
	}

	public void insert(AcrossIncomeTransportData record) throws SQLException {
		sqlMapClient.insert("t_across_income.insert", record);
	}

	public int deleteByPrimaryKey(Integer characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_across_income.deleteByPrimaryKey", characterId);
		return rows;
	}

	public int updateByPrimaryKey(AcrossIncomeTransportData record) throws SQLException {
		int rows = sqlMapClient.update("t_across_income.updateByPrimaryKey", record);
		return rows;
	}
}
