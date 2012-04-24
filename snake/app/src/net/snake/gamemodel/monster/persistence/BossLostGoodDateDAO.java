package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BossLostGoodDateDAO {
	private SqlMapClient sqlMapClient;

	public BossLostGoodDateDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_boss_lostgood.queryall");
		return list;
	}

}
