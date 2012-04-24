package net.snake.gamemodel.pet.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.pet.bean.CharacterHorse;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterHorseDAO {

	private SqlMapClient sqlMapClient;

	public CharacterHorseDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public Integer insert(CharacterHorse record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_horse.insert", record);
		return (Integer) newKey;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_horse.deleteByPrimaryKey", id);
		return rows;
	}

	public int update(CharacterHorse record) throws SQLException {
		int rows = sqlMapClient.update("t_character_horse.update", record);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_horse.selectByCharacterId", characterId);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List selectByStatus(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_horse.selectByStatus", characterId);
		return list;
	}

	public void updateXingfu() throws SQLException {
		sqlMapClient.update("t_character_horse.updateXingfu");
	}
}
