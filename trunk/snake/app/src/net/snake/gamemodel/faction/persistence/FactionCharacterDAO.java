package net.snake.gamemodel.faction.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.faction.bean.FactionCharacter;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FactionCharacterDAO {

	private SqlMapClient sqlMapClient;

	public FactionCharacterDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public int deleteByFactionID(int factionID) throws SQLException {
		int rows = sqlMapClient.delete("t_faction_character.deleteByFactionID",
				factionID);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_faction_character.queryall");
		return list;
	}

	public int insert(FactionCharacter record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_faction_character.insert",
				record);
		record.setId((Integer) newKey);
		return record.getId();
	}

	public int deleteById(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_faction_character.deleteById", id);
		return rows;
	}

	public int updateByPrimaryKeySelective(FactionCharacter record)
			throws SQLException {
		int rows = sqlMapClient.update(
				"t_faction_character.updateByPrimaryKeySelective", record);
		return rows;
	}
}
