package datatransport.bean.characterbow;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BowTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public BowTransportDataDAO(SqlMapClient sqlMapClient) {
		super();
		this.sqlMapClient = sqlMapClient;
	}

	public BowTransportData selectByPrimaryKey(Integer characterid) throws SQLException {
		BowTransportData key = new BowTransportData();
		key.setCharacterid(characterid);
		BowTransportData record = (BowTransportData) sqlMapClient.queryForObject("t_character_bow.selectByPrimaryKey", key);
		return record;
	}

	public int deleteByPrimaryKey(Integer characterid) throws SQLException {
		BowTransportData key = new BowTransportData();
		key.setCharacterid(characterid);
		int rows = sqlMapClient.delete("t_character_bow.deleteByPrimaryKey", key);
		return rows;
	}

	public void insert(BowTransportData record) throws SQLException {
		sqlMapClient.insert("t_character_bow.insert", record);
	}

}
