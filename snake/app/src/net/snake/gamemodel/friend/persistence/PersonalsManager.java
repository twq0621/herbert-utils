package net.snake.gamemodel.friend.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.gamemodel.hero.bean.Personals;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 交友系统
 * 
 */

public class PersonalsManager {

	private static final Logger logger = Logger.getLogger(PersonalsManager.class);

	private static PersonalsDAO dao = new PersonalsDAO(SystemFactory.getCharacterSqlMapClient());

	private List<Personals> rankingList = null;

	public List<Personals> getRankingList() {
		return rankingList;
	}

	// 单例实现=====================================
	private static PersonalsManager instance;

	private PersonalsManager() {
	}

	public static PersonalsManager getInstance() {
		// AcrossUtil.checkNoAcross();
		if (instance == null) {
			instance = new PersonalsManager();
		}
		return instance;
	}

	// 单例实现========================================
	public void addPersonals(Personals personals) {
		try {
			personals.setShenhe(0);
			personals.setBaomichengdu(0);
			personals.setSex(1);
			personals.setAge(0);
			dao.insert(personals);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Personals selectPersonalById(int id) {
		Personals personals = null;
		try {
			personals = dao.selectByCharacterId(id);
			if (null == personals) {
				personals = new Personals();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return personals;
	}

	public void updatePersonals(Personals personals) {
		try {
			dao.updateByPrimaryKeySelective(personals);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void ranKingHua() {
		List<CharacterRanking> characterslist = CharacterManager.getInstance().rankingshua();
		List<Personals> ranKingList = new ArrayList<Personals>();
		for (CharacterRanking characterRanking : characterslist) {
			Personals personals = PersonalsManager.getInstance().selectPersonalById(characterRanking.getId());
			if (null != personals) {
				personals.setHuxcount(characterRanking.getFlowerCount());
				personals.setXingqing(characterRanking.getNowXingqing());
				personals.setBiaoqingtubiao(characterRanking.getNowBiaoqing());

				ranKingList.add(personals);
			}
		}
		this.rankingList = ranKingList;
	}
}
