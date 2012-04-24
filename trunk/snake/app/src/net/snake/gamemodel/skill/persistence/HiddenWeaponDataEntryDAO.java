package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.skill.bean.HiddenWeaponDataEntry;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

public class HiddenWeaponDataEntryDAO {

	private SqlMapClient sqlMapClient;

	private static final Logger logger = Logger
			.getLogger(HiddenWeaponDataEntryDAO.class);

	public HiddenWeaponDataEntryDAO(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	public int deleteByCharacterId(int characterId) throws SQLException {
		int rows = sqlMapClient.delete(
				"t_character_hidden_weapon.deleteByCharacterId", characterId);
		return rows;
	}

	@SuppressWarnings("rawtypes")
	public List selectByCharacterId(int characterId) throws SQLException {
		List list = sqlMapClient.queryForList(
				"t_character_hidden_weapon.selectByCharacterId", characterId);
		return list;
	}

	public void insertSelective(HiddenWeaponDataEntry record)
			throws SQLException {
		sqlMapClient
				.insert("t_character_hidden_weapon.insertSelective", record);
	}

	public int updateByPrimaryKeySelective(HiddenWeaponDataEntry record)
			throws SQLException {
		int rows = sqlMapClient
				.update("t_character_hidden_weapon.updateByPrimaryKeySelective",
						record);
		return rows;
	}

	@SuppressWarnings("unchecked")
	public List<HiddenWeaponDataEntry> getCharacterHiddenWeaponDataEntriesRanking() {
		List<HiddenWeaponDataEntry> list = null;
		try {
			list = sqlMapClient
					.queryForList("t_character_hidden_weapon.getRankinganqi");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<HiddenWeaponDataEntry> getCharacterHiddenWeaponDataEntriesTongJiRanking(
			int leve) {
		List<HiddenWeaponDataEntry> list = null;
		try {
			list = sqlMapClient.queryForList(
					"t_character_hidden_weapon.getRankingtongjianqi", leve);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
