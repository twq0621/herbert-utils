package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class SkillUpgradeExpDAO {
	private SqlMapClient sqlMapClient;

	public SkillUpgradeExpDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings({ "rawtypes" })
	public List select() throws SQLException {
		List list = sqlMapClient.queryForList("t_skillupgrade_exp.queryall");
		return list;
	}
}
