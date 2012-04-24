package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.monster.bean.MonsterLastKill;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MonsterLastKillDAO {

	private SqlMapClient sqlMapClient;

	public MonsterLastKillDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_monster_lastkill.queryall");
		return list;
	}

	public int updateById(MonsterLastKill record) throws SQLException {
		int rows = sqlMapClient.update("t_monster_lastkill.updateById", record);
		return rows;
	}

	public void insert(MonsterLastKill record) throws SQLException {
		sqlMapClient.insert("t_monster_lastkill.insert", record);
	}
}
