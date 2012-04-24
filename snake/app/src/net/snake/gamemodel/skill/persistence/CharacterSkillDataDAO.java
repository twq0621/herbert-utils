package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.skill.bean.CharacterSkillData;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterSkillDataDAO {

	private SqlMapClient sqlMapClient;

	public CharacterSkillDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_skill.selectByCharacterId", characterId);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List selectByHorseId(int horseId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_skill.selectByHorseId", horseId);
		return list;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_skill.deleteByCharacterId", characterId);
		return rows;
	}
	
	public int deleteByHorseId(int horseId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_skill.deleteByHorseId", horseId);
		return rows;
	}

	public int updateByPrimaryKey(CharacterSkillData record) throws SQLException {
		int rows = sqlMapClient.update("t_character_skill.updateByPrimaryKey", record);
		return rows;
	}

	public Integer insert(CharacterSkillData record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_skill.insert", record);
		record.setId((Integer) newKey);
		return (Integer) newKey;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_skill.deleteByPrimaryKey", id);
		return rows;
	}
}
