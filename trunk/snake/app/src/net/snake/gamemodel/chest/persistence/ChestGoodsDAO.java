package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.chest.bean.ChestGoods;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChestGoodsDAO {

	private SqlMapClient sqlMapClient;

	public ChestGoodsDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insertSelective(ChestGoods record) throws SQLException {
		sqlMapClient.insert("t_chest_goods.insertSelective",
				record);
	}

	public int deleteByPrimaryKey(Integer chestGoodsId) throws SQLException {
		int rows = sqlMapClient.delete("t_chest_goods.deleteById", chestGoodsId);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(	"t_chest_goods.selectByCharacterId", characterId);
		return list;
	}

}
