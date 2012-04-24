package net.snake.gamemodel.heroext.lianti.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.heroext.lianti.bean.CharacterLiantiDataEntry;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterLiantiDataEntryDAO {
	private SqlMapClient sqlMapClient;

	public CharacterLiantiDataEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public int updateByPrimaryKeySelective(CharacterLiantiDataEntry record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"t_character_lianti.updateByPrimaryKeySelective", record);
		return rows;
	}

	public CharacterLiantiDataEntry selectByPrimaryKey(Integer characterId)
			throws SQLException {
		CharacterLiantiDataEntry record = (CharacterLiantiDataEntry) sqlMapClient
				.queryForObject("t_character_lianti.selectByPrimaryKey",
						characterId);
		return record;
	}

	public void insertSelective(CharacterLiantiDataEntry record)
			throws SQLException {
		sqlMapClient.insert("t_character_lianti.insertSelective", record);
	}

}
