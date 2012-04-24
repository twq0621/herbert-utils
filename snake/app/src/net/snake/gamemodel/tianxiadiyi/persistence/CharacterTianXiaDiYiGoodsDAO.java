package net.snake.gamemodel.tianxiadiyi.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYiGoods;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterTianXiaDiYiGoodsDAO {

	private SqlMapClient sqlMapClient;
	public CharacterTianXiaDiYiGoodsDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_character_tianxiadiyi_goods.queryall");
		return list;
	}
	
	public void insertSelective(CharacterTianXiaDiYiGoods record) throws SQLException {
		sqlMapClient.insert("t_character_tianxiadiyi_goods.insertSelective", record);
	}
	
	public int deleteAll() throws SQLException {
		int rows = sqlMapClient.delete("t_character_tianxiadiyi_goods.deleteAll");
		return rows;
	}
}
