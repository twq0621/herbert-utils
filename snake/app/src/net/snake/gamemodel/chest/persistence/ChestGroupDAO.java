package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChestGroupDAO {

	private SqlMapClient sqlMapClient;

	public ChestGroupDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_chest_group.queryall");
		return list;
	}	
}
