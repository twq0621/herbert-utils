package net.snake.gamemodel.wedding.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FeastPlayConfigDAO {

	private SqlMapClient sqlMapClient;

	public FeastPlayConfigDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_feast_playconfig.queryall");
		return list;
	}

}
