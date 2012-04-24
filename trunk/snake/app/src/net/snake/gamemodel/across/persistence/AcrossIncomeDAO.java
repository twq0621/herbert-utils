package net.snake.gamemodel.across.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.across.bean.AcrossIncome;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AcrossIncomeDAO {

	private SqlMapClient sqlMapClient;

	public AcrossIncomeDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public AcrossIncome selectByPrimaryKey(Integer characterId) throws SQLException {
		AcrossIncome record = (AcrossIncome) sqlMapClient.queryForObject("t_across_income.selectByPrimaryKey", characterId);
		return record;
	}

	public void insert(AcrossIncome record) throws SQLException {
		sqlMapClient.insert("t_across_income.insert", record);
	}

	public int updateByPrimaryKey(AcrossIncome record) throws SQLException {
		int rows = sqlMapClient.update("t_across_income.updateByPrimaryKey", record);
		return rows;
	}

	public void update() throws SQLException {
		sqlMapClient.update("t_across_income.ResetAcrossIncome");

	}
}
