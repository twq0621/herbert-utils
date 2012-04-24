package datatransport.bean.characterhiddenweapon;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HiddenWeaponTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public HiddenWeaponTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_hidden_weapon.selectByCharacterId", characterId);
		return list;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_hidden_weapon.deleteByCharacterId", characterId);
		return rows;
	}

	public void insert(HiddenWeaponTransportData record) throws SQLException {
		sqlMapClient.insert("t_character_hidden_weapon.insert", record);
	}
}
