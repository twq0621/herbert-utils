package net.snake.gamemodel.heroext.wudao.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.heroext.wudao.bean.CharacterDGWD;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterDGWDDAO {

	private SqlMapClient sqlMapClient;

	public CharacterDGWDDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public CharacterDGWD selectByPrimaryKey(Integer characterid) throws SQLException {
		CharacterDGWD record = (CharacterDGWD) sqlMapClient.queryForObject("t_character_dgwd.selectByPrimaryKey", characterid);
		return record;
	}

	public void insert(CharacterDGWD record) throws SQLException {
		sqlMapClient.insert("t_character_dgwd.insert", record);
	}

	public int updateByPrimaryKey(CharacterDGWD record) throws SQLException {
		int rows = sqlMapClient.update("t_character_dgwd.updateById", record);
		return rows;
	}

	public int updateByPrimaryKeySelective(CharacterDGWD record) throws SQLException {
		int rows = sqlMapClient.update("t_character_dgwd.updateByPrimaryKeySelective", record);
		return rows;
	}
}
