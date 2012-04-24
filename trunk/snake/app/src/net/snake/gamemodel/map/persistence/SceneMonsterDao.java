package net.snake.gamemodel.map.persistence;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;
/**
 * 场景怪物表数据库操作类
 * 
 * @author benchild
 */
public class SceneMonsterDao  {
	public SqlMapClient getSqlMapClient() {		
		return SystemFactory.getGamedataSqlMapClient();
	}
}
