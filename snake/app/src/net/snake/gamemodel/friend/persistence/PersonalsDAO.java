package net.snake.gamemodel.friend.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.hero.bean.Personals;

import com.ibatis.sqlmap.client.SqlMapClient;

public class PersonalsDAO {

	private SqlMapClient sqlMapClient;

	public PersonalsDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insert(Personals record) throws SQLException {
		sqlMapClient.insert("t_personals.insert", record);
	}

	public Personals selectByCharacterId(int characterId) throws SQLException {
		Personals personals = (Personals) sqlMapClient.queryForObject(
				"t_personals.selectByCharacterId", characterId);
		return personals;
	}

	public int updateByPrimaryKeySelective(Personals record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"t_personals.updateByPrimaryKeySelective",
				record);
		return rows;
	}
}
