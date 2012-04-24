package net.snake.gamemodel.hero.persistence;


import java.sql.SQLException;
import java.util.Map;

import net.snake.gamemodel.hero.bean.Charactergrade;

import com.ibatis.sqlmap.client.SqlMapClient;


public class CharactergradeDAO {
    
	private SqlMapClient sqlMapClient;

	public CharactergradeDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}
	@SuppressWarnings({"unchecked" })
	public Map<String, Charactergrade> getcharactergrade() throws SQLException {
		return sqlMapClient.queryForMap("t_character_grade.queryall",null, "key");
	}
	
}
