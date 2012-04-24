package net.snake.gamemodel.chest.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.chest.bean.Chest;

import com.ibatis.sqlmap.client.SqlMapClient;

public class ChestDAO {

	private SqlMapClient sqlMapClient;

	public ChestDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List selectByTpe(Chest chest) throws SQLException {
		List list = sqlMapClient.queryForList(	"t_chest.selectByTpe", chest);
		return list;
	}

	public void deleteChest() throws SQLException {
		sqlMapClient.update("t_chest.deleteChest");
	}

	@SuppressWarnings("unchecked")
	public List<Chest> getDeleteChestList() throws SQLException {
		return sqlMapClient.queryForList("t_chest.selectChest");
	}

	public void insertSelective(Chest record) throws SQLException {
		sqlMapClient.insert("t_chest.insertSelective", record);
	}

	public int updateByPrimaryKeySelective(Chest record) throws SQLException {
		int rows = sqlMapClient.update(
				"t_chest.updateByPrimaryKeySelective", record);
		return rows;
	}

}
