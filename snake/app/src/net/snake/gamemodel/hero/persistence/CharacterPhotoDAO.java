package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.hero.bean.CharacterPhoto;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterPhotoDAO {

	private SqlMapClient sqlMapClient;

	public CharacterPhotoDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public CharacterPhoto selectByPrimaryKey(Integer characterId) throws SQLException {
		CharacterPhoto record = (CharacterPhoto) sqlMapClient.queryForObject("t_character_photo.selectByPrimaryKey", characterId);
		return record;
	}

	public int deleteByPrimaryKey(Integer characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_photo.deleteByPrimaryKey", characterId);
		return rows;
	}

	public int updateByPrimaryKeySelective(CharacterPhoto record) throws SQLException {
		int rows = sqlMapClient.update("t_character_photo.updateByPrimaryKeySelective", record);
		return rows;
	}

	public void insertSelective(CharacterPhoto record) throws SQLException {
		sqlMapClient.insert("t_character_photo.insertSelective", record);
	}
}
