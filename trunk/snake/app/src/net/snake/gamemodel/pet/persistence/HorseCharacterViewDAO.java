package net.snake.gamemodel.pet.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.pet.bean.HorseCharacterView;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HorseCharacterViewDAO {

	private SqlMapClient sqlMapClient;

	public HorseCharacterViewDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HorseCharacterView> selectRanKing()
			throws SQLException {
		List<HorseCharacterView> list = null;
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		list = sqlMapClient.queryForList("t_horse_character_view.getRankingma", map);
		return list;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<HorseCharacterView> selectLevel(int level)
			throws SQLException {
		List<HorseCharacterView> list = null;
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		map.put("horseModelId", level);
		list = sqlMapClient.queryForList("t_horse_character_view.getRankingmatongji", map);
		return list;
	}
}
