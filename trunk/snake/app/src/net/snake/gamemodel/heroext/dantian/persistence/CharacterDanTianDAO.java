package net.snake.gamemodel.heroext.dantian.persistence;

import java.sql.SQLException;

import net.snake.GameServer;
import net.snake.gamemodel.heroext.dantian.bean.CharacterDanTian;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class CharacterDanTianDAO {
	private static final Logger logger = Logger.getLogger(CharacterDanTianDAO.class);
	private SqlMapClient sqlMapClient;

	public CharacterDanTianDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public CharacterDanTian selectByPrimaryKey(Integer characterid)
			throws SQLException {
		CharacterDanTian record = (CharacterDanTian) sqlMapClient
				.queryForObject("t_character_dantian.selectById", characterid);
		return record;
	}

	public void syncUpdate(final DanTian dantian) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				try {
					updateByPrimaryKey(dantian);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});

	}

	public void syncInsert(final DanTian dantian) {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				try {
					insert(dantian);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void syncClearZhuFu() {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				try {
					int rows = sqlMapClient
							.update("t_character_dantian.clearZhuFu");
					if (logger.isInfoEnabled()) {
						logger.info("己执行丹田祝福值更新,共影响记录) - int rows=" + rows);
					}
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void insert(CharacterDanTian record) throws SQLException {
		sqlMapClient.insert("t_character_dantian.insert", record);
	}

	public int updateByPrimaryKey(CharacterDanTian record) throws SQLException {
		int rows = sqlMapClient
				.update("t_character_dantian.updateById", record);
		return rows;
	}
}
