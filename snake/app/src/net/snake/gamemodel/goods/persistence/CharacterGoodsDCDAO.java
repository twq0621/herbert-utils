package net.snake.gamemodel.goods.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.goods.bean.CharacterGoodsDC;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterGoodsDCDAO {

	private SqlMapClient sqlMapClient;

	public CharacterGoodsDCDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_goods_dc.selectByCharacterId", characterId);
		return list;
	}

	public int updateByPrimaryKey(CharacterGoodsDC record) throws SQLException {
		int rows = sqlMapClient.update(
				"t_character_goods_dc.updateByPrimaryKey", record);
		return rows;
	}

	public void insert(CharacterGoodsDC record) throws SQLException {
		sqlMapClient.insert("t_character_goods_dc.insert", record);
	}

}
