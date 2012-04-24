package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.hero.bean.CharDayInComeData;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharDayInComeDataDAO {

	private SqlMapClient sqlMapClient;

	public CharDayInComeDataDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void insertInit() throws SQLException {
		sqlMapClient.insert("t_character_dayincome_count.insertInit");
	}

	public CharDayInComeData selectByPrimaryKey(Integer fCharacterid) throws SQLException {
		CharDayInComeData record = (CharDayInComeData) sqlMapClient.queryForObject("t_character_dayincome_count.selectById", fCharacterid);
		return record;
	}

	public int updateByPrimaryKey(CharDayInComeData record) throws SQLException {
		int rows = sqlMapClient.update("t_character_dayincome_count.updateById", record);
		return rows;
	}

	public void insert(CharDayInComeData record) throws SQLException {
		sqlMapClient.insert("t_character_dayincome_count.insert", record);
	}

}
