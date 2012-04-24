package datatransport.bean.characterhorse;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterHorseTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public CharacterHorseTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_horse.selectByCharacterId", characterId);
		return list;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_horse.deleteByCharacterId", characterId);
		return rows;
	}

	public Integer insert(CharacterHorseTransportData record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_horse.insert", record);
		return (Integer) newKey;
	}

}
