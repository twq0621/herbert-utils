package net.snake.gamemodel.vip.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.vip.bean.CharacterVip;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterVipDAO {

	private SqlMapClient sqlMapClient;

	public CharacterVipDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"character_vip.selectByCharacterId", characterId);
		return list;
	}

	public void insert(CharacterVip record) throws SQLException {
		sqlMapClient.insert("character_vip.insert", record);
	}

	public int updateByPrimaryKey(CharacterVip record) throws SQLException {
		int rows = sqlMapClient.update("character_vip.updateByPrimaryKey",
				record);
		return rows;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("character_vip.deleteByPrimaryKey", id);
		return rows;
	}
}
