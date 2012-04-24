package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.gamemodel.skill.bean.BuffPersisData;
import net.snake.ibatis.SystemFactory;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 
 * 
 * @author wutao
 */
public class CharacterBuffDao {
	private static final Logger logger = Logger.getLogger(CharacterBuffDao.class);

	public SqlMapClient getSqlMapClient() {
		return SystemFactory.getCharacterSqlMapClient();
	}

	@SuppressWarnings("unchecked")
	public List<BuffPersisData> getListByCharacterId(int characterId) {
		List<BuffPersisData> buffPersisDataList = null;
		try {
			buffPersisDataList = this.getSqlMapClient().queryForList("characterBuffGetAllByCharacterid", characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return buffPersisDataList;
	}

}
