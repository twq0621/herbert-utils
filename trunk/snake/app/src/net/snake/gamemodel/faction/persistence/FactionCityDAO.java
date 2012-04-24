package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.faction.bean.FactionCity;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FactionCityDAO {

	private SqlMapClient sqlMapClient;

	public FactionCityDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_faction_city.queryall");
		return list;
	}

	public void insert(FactionCity record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_faction_city.insert", record);
		record.setId( (Integer) newKey);
	}

	public int updateById(FactionCity record) throws SQLException {
		int rows = sqlMapClient.update("t_faction_city.updateById", record);
		return rows;
	}
}
