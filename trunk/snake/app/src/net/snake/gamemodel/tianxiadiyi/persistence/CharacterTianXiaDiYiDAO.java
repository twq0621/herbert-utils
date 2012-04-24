package net.snake.gamemodel.tianxiadiyi.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterTianXiaDiYiDAO {

	private SqlMapClient sqlMapClient;

	public CharacterTianXiaDiYiDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_character_tianxiadiyi.queryall");
		return list;
	}

	public int deleteAll() throws SQLException {
		int rows = sqlMapClient.delete("t_character_tianxiadiyi.deleteAll");
		return rows;
	}

	public void insertSelective(CharacterTianXiaDiYi record) throws SQLException {
		sqlMapClient.insert("t_character_tianxiadiyi.insertSelective", record);
	}

	public int updateByPrimaryKeySelective(CharacterTianXiaDiYi record) throws SQLException {
		int rows = sqlMapClient.update("t_character_tianxiadiyi.updateByPrimaryKeySelective", record);
		return rows;
	}

	public void resetCharacterTianXiaDiYiChestCount() throws SQLException {
		sqlMapClient.update("t_character_tianxiadiyi.resetCharacterTianXiaDiYiChestCount");

	}
}
