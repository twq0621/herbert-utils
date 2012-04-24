package datatransport.bean.character;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public CharacterTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public CharacterTransportData selectByPrimaryKey(Integer id) throws SQLException {
		CharacterTransportData record = (CharacterTransportData) sqlMapClient.queryForObject("t_character.selectByPrimaryKey", id);
		return record;
	}

	public int deleteByPrimaryKey(Integer id) throws SQLException {
		int rows = sqlMapClient.delete("t_character.deleteByPrimaryKey", id);
		return rows;
	}

	public Integer insert(CharacterTransportData record) throws SQLException {
		Object newKey = sqlMapClient.insert("t_character.insert", record);
		return (Integer) newKey;
	}

}
