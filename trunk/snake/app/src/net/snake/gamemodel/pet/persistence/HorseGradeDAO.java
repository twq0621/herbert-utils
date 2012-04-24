package net.snake.gamemodel.pet.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HorseGradeDAO {

	private SqlMapClient sqlMapClient;

	public HorseGradeDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_horse_grade.queryall");
		return list;
	}
}
