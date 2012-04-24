package net.snake.gamemodel.monster.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 怪物模板表数据库操作类
 * 
 * @author benchild
 */
public class MonsterModelDao {

	public MonsterModelDao() {
	}

	public SqlMapClient getSqlMapClient() {
		return SystemFactory.getGamedataSqlMapClient();
	}

	@SuppressWarnings({ "unchecked" })
	public List<MonsterModel> getAll() throws SQLException {
		List<MonsterModel> all = getSqlMapClient().queryForList("monsterModelGetAll");
		return all;
	}
}
