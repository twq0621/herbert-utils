package net.snake.gamemodel.instance.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.instance.bean.Fubenranking;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class FubenrankingDAO {

	private static final Logger logger = Logger
			.getLogger(FubenrankingDAO.class);
	private SqlMapClient sqlMapClient;

	public FubenrankingDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings("rawtypes")
	public List  getFubenRanking(int fu_ben_id)throws SQLException {
		List list = sqlMapClient.queryForList(	"t_fubenranking.getFubenRanking", fu_ben_id);
		return list;
	}
	@SuppressWarnings("rawtypes")
	public List selecteFubenListByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(	"t_fubenranking.selecteFubenListByCharacterId", characterId);
		return list;
	}
	
	public void insert(Fubenranking record) throws SQLException {
		sqlMapClient.insert("t_fubenranking.insert",
				record);
	}

	public int updateByTime(Fubenranking record)
			throws SQLException {
		int rows = sqlMapClient.update("t_fubenranking.updateByTime",	record);
		return rows;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getFuBendistinct() {
		try {
			return sqlMapClient.queryForList("t_fubenranking.getFuBendistinct");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Fubenranking> getFuBendistinctTongJi(int leve) {
		try {
			return sqlMapClient.queryForList("t_fubenranking.getFuBendistinctTongJi", leve);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
