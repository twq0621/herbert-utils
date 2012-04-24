package datatransport.bean.charactergoods;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GoodsTransportDataDAO {

	private SqlMapClient sqlMapClient;

	public GoodsTransportDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByPostion(Map<String,Integer> queryMap) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_goods.selectByPostion", queryMap);
		return list;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete("t_character_goods.deleteByCharacterId", characterId);
		return rows;
	}

	public void insert(GoodsTransportData record) throws SQLException {
		sqlMapClient.insert("t_character_goods.insert", record);
	}

}
