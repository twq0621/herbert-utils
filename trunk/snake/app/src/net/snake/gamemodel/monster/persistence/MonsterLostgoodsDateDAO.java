package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MonsterLostgoodsDateDAO {
	private SqlMapClient sqlMapClient;

	public MonsterLostgoodsDateDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_monster_lostgoods.queryall");
		return list;
	}
}
