package net.snake.gamemodel.guide.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.guide.bean.CharacterIdea;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterIdeaDAO {

	private SqlMapClient sqlMapClient;

	public CharacterIdeaDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public Integer insert(CharacterIdea record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_idea.insert", record);
		return (Integer) newKey;
	}

}
