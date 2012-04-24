package datatransport.bean.characterskilldata;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterSkillTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public CharacterSkillTransportDataDAO(SqlMapClient sqlMapClient) {
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
	
	public Integer insert(CharacterSkillTransportData record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character_skill.insert", record);
		return (Integer) newKey;
	}

}
