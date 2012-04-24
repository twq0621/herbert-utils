package net.snake.gamemodel.across.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.across.bean.CharacterAcross;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterAcrossDAO {

	private SqlMapClient sqlMapClient;

	public CharacterAcrossDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_character_across.queryall");
		return list;
	}

	public int updateById(CharacterAcross record) throws SQLException {
		int rows = sqlMapClient.update("t_character_across.updateById", record);
		return rows;
	}

	public int updateByPrimaryKeySelective(CharacterAcross record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"t_character_across.updateByPrimaryKeySelective", record);
		return rows;
	}

	public Integer insert(CharacterAcross record) throws SQLException {
		Object newKey = sqlMapClient
				.insert("t_character_across.insert", record);
		return (Integer) newKey;
	}

}
