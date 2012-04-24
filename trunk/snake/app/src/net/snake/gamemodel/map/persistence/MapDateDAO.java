package net.snake.gamemodel.map.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MapDateDAO {
	private SqlMapClient sqlMapClient;

	public MapDateDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectWithBLOBs() throws SQLException {
		List list = sqlMapClient.queryForList("t_map.selectByExampleWithBLOBs");
		return list;
	}
}
