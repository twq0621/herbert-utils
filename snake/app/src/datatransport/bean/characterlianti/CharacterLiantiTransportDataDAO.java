package datatransport.bean.characterlianti;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterLiantiTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public CharacterLiantiTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public CharacterLiantiTransportData selectByPrimaryKey(Integer characterId) throws SQLException {
		CharacterLiantiTransportData record = (CharacterLiantiTransportData) sqlMapClient.queryForObject("t_character_lianti.selectByPrimaryKey", characterId);
		return record;
	}

	public int deleteByPrimaryKey(Integer characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_lianti.deleteByPrimaryKey", characterId);
		return rows;
	}

	public void insert(CharacterLiantiTransportData record) throws SQLException {
		sqlMapClient.insert("t_character_lianti.insert", record);
	}
}
