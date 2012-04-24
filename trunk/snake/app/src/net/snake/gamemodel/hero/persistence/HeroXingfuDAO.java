package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.HeroXingfu;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HeroXingfuDAO {
	private SqlMapClient sqlMapClient;

	public HeroXingfuDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public void update(int id) throws SQLException {
		sqlMapClient.update("t_hero_xingfu.update", id);
	}

	public void insert(HeroXingfu xingfu) throws SQLException {
		Object obj = sqlMapClient.insert("t_hero_xingfu.insert", xingfu);
		xingfu.setId((Integer) obj);
	}

	@SuppressWarnings("rawtypes")
	public List getHeroXingfus(int heroId) throws SQLException {
		List list = sqlMapClient.queryForList("t_hero_xingfu.queryall", heroId);
		return list;
	}
}
