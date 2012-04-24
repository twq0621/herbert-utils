package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.activities.bean.CharacterActivationCard;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterActivationCardDAO {

	private SqlMapClient sqlMapClient;

	public CharacterActivationCardDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient
				.queryForList("t_character_activation_card.queryall");
		return list;
	}

	public Integer insert(CharacterActivationCard record) throws SQLException {
		Object newKey = sqlMapClient.insert(
				"t_character_activation_card.insert", record);
		return (Integer) newKey;
	}
}
