package net.snake.gamemodel.hero.persistence;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.commons.ErrorSQLPrint;
import net.snake.gamemodel.exception.DataException;
import net.snake.gamemodel.hero.bean.CharacterForList;
import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 人物角色数据库操作类
 * 
 * @author benchild
 */
public class CharacterDao {
	private static Logger logger = Logger.getLogger(CharacterDao.class);

	public CharacterDao() {
	}

	/**
	 * 判断角色名是否存在 不存在的话返回true
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */

	public boolean isExistname(String name) throws SQLException {
		Object object = this.getSqlMapClient().queryForObject("isExistname", name);
		return object != null;
	}

	public Hero get(int id) throws SQLException {
		Hero object = (Hero) this.getSqlMapClient().queryForObject("characterGet", id);
		return object;
	}

	public void processCharacterRelation(Collection<String> sqls) throws DataException {
		Connection conn = null;
		Statement stm = null;
		try {
			conn = this.getSqlMapClient().getDataSource().getConnection();
			Iterator<String> iterator = sqls.iterator();
			stm = conn.createStatement();
			while (iterator.hasNext()) {
				String sql = iterator.next();
				if (sql == null) {
					continue;
				}
				stm.addBatch(sql);
			}
			stm.executeBatch();
		} catch (BatchUpdateException e3) {
			List<String> list = new ArrayList<String>(sqls);
			int a[] = e3.getUpdateCounts();
			for (int i = 0; i < a.length; i++) {
				if (a[i] == Statement.EXECUTE_FAILED) {
					ErrorSQLPrint.println(list.get(i));
				}
			}
			throw new DataException(this.getClass().getName(), "processCacheData(Map charactergoodsMap)", "执行数据库更新失败！", e3);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					throw new DataException(this.getClass().getName(), "processCacheData(Map charactergoodsMap)", "关闭数据库失败！", e);
				}
			}
		}
	}

	public void update(Hero character) throws SQLException {
		getSqlMapClient().update("characterUpdate", character);
	}

	public void update(String sql, int id) throws SQLException {
		getSqlMapClient().update(sql, id);

	}

	public void delete(Integer id) throws SQLException {
		this.getSqlMapClient().delete("characterDelete", id);
	}

	/**
	 * 根据账户id，得到该账户下的所有人物角色
	 * 
	 * @param account
	 *            账户id（int）
	 * @return List<Character>
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<CharacterForList> getCharacterByAccountId(int account) throws SQLException {
		List<CharacterForList> cList = this.getSqlMapClient().queryForList("getCharacterByAccountId", account);
		return cList;
	}

	/**
	 * 根据账户id，分区sid得到该账户下的所有人物角色
	 * 
	 * @param account
	 *            账户id（int）
	 * @return List<Character>
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CharacterForList> getCharacterByAccountIdAndSid(int account, int sid) throws SQLException {
		List<CharacterForList> cList = null;
		Map map = new HashMap();
		map.put("accountId", account);
		map.put("sid", sid);
		cList = this.getSqlMapClient().queryForList("getCharacterByAccountIdAndSid", map);
		return cList;
	}

	@SuppressWarnings("unchecked")
	public Hero getCharacterByName(String name) {
		List<Hero> cList = null;
		try {
			cList = this.getSqlMapClient().queryForList("getMyCharacterByName", name);
			if (cList != null && cList.size() > 0) {
				return cList.get(0);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public void insert(Hero character) throws SQLException {
		getSqlMapClient().insert("characterInsert", character);
	}

	public void updateCharacterByName(String name) {
		try {
			getSqlMapClient().update(name);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<CharacterRanking> selectRanKing(String name, Map map) {
		List<CharacterRanking> list = null;
		try {
			list = getSqlMapClient().queryForList(name, map);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> selectLevel(int level, String name) {
		List<CharacterRanking> list = null;
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		map.put("grade", level);
		try {
			list = getSqlMapClient().queryForList(name, map);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

		return list;
	}

	/**
	 * @description 更新账号原始ID
	 * @param character
	 */
	public void updateCharacterInitiallyId(Hero character) {
		character.setCharacterInitiallyId(character.getId());
		try {
			getSqlMapClient().update("updateCharacterInitiallyId", character);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public SqlMapClient getSqlMapClient() {
		return SystemFactory.getCharacterSqlMapClient();
	}
}
