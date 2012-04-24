package datatransport.bean.characteronhoor;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterOnHoorConfigTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public CharacterOnHoorConfigTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public CharacterOnHoorConfigTransportData selectByPrimaryKey(Integer characterId) throws SQLException {
		CharacterOnHoorConfigTransportData record = (CharacterOnHoorConfigTransportData) sqlMapClient.queryForObject(
				"t_character_onhoor_config.selectByPrimaryKey", characterId);
		return record;
	}

	public int deleteByPrimaryKey(Integer characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_onhoor_config.deleteByPrimaryKey", characterId);
		return rows;
	}

	public void insert(CharacterOnHoorConfigTransportData record) throws SQLException {
		sqlMapClient.insert("t_character_onhoor_config.insert", record);
	}

}
