package net.snake.gamemodel.gift.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterGiftpacksDAO {

	private SqlMapClient sqlMapClient;

	public CharacterGiftpacksDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_giftpacks.selectByCharacterId", characterId);
		return list;
	}

	public Integer insert(CharacterGiftpacks record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_giftpacks.insert", record);
		return (Integer) newKey;
	}

	public int updateByPrimaryKey(CharacterGiftpacks record) throws SQLException {
		int rows = sqlMapClient.update("t_character_giftpacks.updateByPrimaryKey", record);
		return rows;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_giftpacks.deleteByPrimaryKey", id);
		return rows;
	}

}
