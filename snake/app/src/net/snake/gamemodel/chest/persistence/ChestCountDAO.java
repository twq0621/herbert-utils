package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.chest.bean.ChestCount;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChestCountDAO {

	private SqlMapClient sqlMapClient;

	public ChestCountDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public ChestCount selectByPrimaryKey(Integer characterId) throws SQLException {
		ChestCount record = (ChestCount) sqlMapClient.queryForObject("t_chest_count.selectById", characterId);
		return record;
	}

	public void insertSelective(ChestCount record) throws SQLException {
		sqlMapClient.insert("t_chest_count.insertSelective", record);
	}

	public int updateByPrimaryKeySelective(ChestCount record) throws SQLException {
		int rows = sqlMapClient.update("t_chest_count.updateByPrimaryKeySelective", record);
		return rows;
	}

	public void reSetChestCountExchangeCount() throws SQLException {
		sqlMapClient.update("t_chest_count.ResetExchangeCount");
	}
}
