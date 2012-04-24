package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChestResourcesDAO {

	private SqlMapClient sqlMapClient;

	public ChestResourcesDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_chest_resources.queryall");
		return list;
	}

}
