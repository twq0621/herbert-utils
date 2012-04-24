package net.snake.gamemodel.pet.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HorseModelDAO {

	private SqlMapClient sqlMapClient;

	public HorseModelDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_horse_model.queryall");
		return list;
	}
}
