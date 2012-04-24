package datatransport.bean.dantian;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterDanTianTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public CharacterDanTianTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public CharacterDanTianTransportData selectByPrimaryKey(Integer characterid) throws SQLException {
		CharacterDanTianTransportData record = (CharacterDanTianTransportData) sqlMapClient.queryForObject("t_character_dantian.selectByPrimaryKey", characterid);
		return record;
	}

	public int deleteByPrimaryKey(Integer characterid) throws SQLException {
		int rows = sqlMapClient.delete("t_character_dantian.deleteByPrimaryKey", characterid);
		return rows;
	}

	public void insert(CharacterDanTianTransportData record) throws SQLException {
		sqlMapClient.insert("t_character_dantian.insert", record);
	}
}
