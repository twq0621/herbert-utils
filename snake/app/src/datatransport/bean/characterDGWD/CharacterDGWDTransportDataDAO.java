package datatransport.bean.characterDGWD;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterDGWDTransportDataDAO {
    
	  private SqlMapClient sqlMapClient;

	    public CharacterDGWDTransportDataDAO(SqlMapClient sqlMapClient) {
	        this.sqlMapClient = sqlMapClient;
	    }
	    
    public CharacterDGWDTransportData selectByPrimaryKey(Integer characterid) throws SQLException {
        CharacterDGWDTransportData record = (CharacterDGWDTransportData) sqlMapClient.queryForObject("t_character_dgwd.selectByPrimaryKey", characterid);
        return record;
    }

    public void insert(CharacterDGWDTransportData record) throws SQLException {
        sqlMapClient.insert("t_character_dgwd.insert", record);
    }
}
