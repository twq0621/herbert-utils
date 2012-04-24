package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FactionCityConfigDAO {
	private SqlMapClient sqlMapClient;

	public FactionCityConfigDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_faction_city_config.queryall");
		return list;
	}

}
