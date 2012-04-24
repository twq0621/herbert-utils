package net.snake.gamemodel.achieve.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.fight.bean.CharacterAchieve;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterAchieveDAO {

	private SqlMapClient sqlMapClient;

	public CharacterAchieveDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_achieve.queryall",
				characterId);
		return list;
	}

	public void insert(CharacterAchieve record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_achieve.insert",
				record);
		record.setId((Integer) newKey);
	}

	public int updateById(CharacterAchieve record) throws SQLException {
		int rows = sqlMapClient
				.update("t_character_achieve.updateById", record);
		return rows;
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_achieve.deleteById", id);
		return rows;
	}

}
