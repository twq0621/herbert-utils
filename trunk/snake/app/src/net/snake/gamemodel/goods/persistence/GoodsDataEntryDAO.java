package net.snake.gamemodel.goods.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.goods.bean.GoodsDataEntry;

import com.ibatis.sqlmap.client.SqlMapClient;

public class GoodsDataEntryDAO {

	private SqlMapClient sqlMapClient;

	public GoodsDataEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List getCharacterGoodsList(Map<String, Integer> map) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_goods.getCharacterGoodsListByPosition", map);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getCharacterAvatarGoodsList(int characterid) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_goods.getCharacterAvatarGoodsList", characterid);
		return list;
	}

	@SuppressWarnings("rawtypes")
	public List getCharacterGoodsList(int characterid) throws SQLException {
		List list = sqlMapClient.queryForList("t_character_goods.getCharacterGoodsList", characterid);
		return list;
	}

	public int updateByPrimaryKeySelective(GoodsDataEntry record) throws SQLException {
		int rows = sqlMapClient.update("t_character_goods.updateByPrimaryKeySelective", record);
		return rows;
	}

	public int updatePosition(GoodsDataEntry record) throws SQLException {
		int rows = sqlMapClient.update("t_character_goods.updatePosition", record);
		return rows;
	}

	public void insertSelective(GoodsDataEntry record) throws SQLException {
		sqlMapClient.insert("t_character_goods.insertSelective", record);
	}

	public int deleteById(String id) throws SQLException {
		int rows = sqlMapClient.delete("t_character_goods.deleteById", id);
		return rows;
	}

	public int deleteByPosition(Map<String, Integer> map) throws SQLException {
		int rows = sqlMapClient.delete("t_character_goods.deleteByPosition", map);
		return rows;
	}
	
	public void updateXingfu() throws SQLException{
		sqlMapClient.update("t_character_goods.updateXingfu");
	}
}
