package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FactionFlagDAO {

	private SqlMapClient sqlMapClient;
	public FactionFlagDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_faction_flag.queryall");
		return list;
	}
	
}
