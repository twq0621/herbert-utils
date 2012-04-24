package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.GameServer;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.exception.DataException;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.CharacterForList;
import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.persistence.SkillManager;


import org.apache.log4j.Logger;
/**
 * 人物角色管理
 * 
 * @author benchild
 */

public class CharacterManager {
	private static Logger logger = Logger.getLogger(CharacterManager.class);
	private static CharacterDao dao = new CharacterDao();
	// 单例实现=====================================
	private static CharacterManager instance;

	private CharacterManager() {
	}

	public static CharacterManager getInstance() {
		if (instance == null) {
			instance = new CharacterManager();
		}
		return instance;
	}

	// 单例实现========================================

	public void deleteCharacter(int id) throws SQLException {
		dao.delete(id);
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, CharacterCacheEntry> getCharacterMap() {
		Map<Integer, CharacterCacheEntry> characterMap = null;
		try {
			characterMap = dao.getSqlMapClient().queryForMap("characterGetAll", null, "id");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return characterMap;
	}

	public boolean isExistName(String name) throws SQLException {
		return dao.isExistname(name);
	}

	public void insert(Hero character) throws SQLException {
		dao.insert(character);
		// 更新角色的原始ID为当前主键ID
		dao.updateCharacterInitiallyId(character);
	}

	public void init(Hero character) throws SQLException {
		character.setMoveSpeed(ClientConfig.CharacterDefaultSpeed);
		logger.info("初始化人物属性。。。。");
		Map<Integer, Skill> skillMap = SkillManager.getInstance().getCacheSkillMap();
		for (Iterator<Skill> iterator = skillMap.values().iterator(); iterator.hasNext();) {
			Skill skill = iterator.next();
			if (skill.isGeneral() && skill.getPopsinger() == character.getPopsinger()) {
				// 初始化玩家平砍技能
				character.setSkillid(skill.getId());
			}
		}
		dao.update(character);
	}

	public void update(Hero character) throws SQLException {
		dao.update(character);
	}

	public List<CharacterForList> getCharacterByAccountId(int account) throws SQLException {
		List<CharacterForList> cList = dao.getCharacterByAccountId(account);
		ArrayList<CharacterForList> oklist = new ArrayList<CharacterForList>();
		for (CharacterForList entry : cList) {
			if (entry.getDeleteMark() == 0) {// 为了不在表上做索引
				oklist.add(entry);
			}
		}
		return oklist;
	}

	public List<CharacterForList> getCharacterByAccountIdAndSid(int account, int sid) throws SQLException {
		List<CharacterForList> cList = dao.getCharacterByAccountIdAndSid(account, sid);
		ArrayList<CharacterForList> oklist = new ArrayList<CharacterForList>();
		for (CharacterForList entry : cList) {
			if (entry.getDeleteMark() == 0) {// 为了不在表上做索引
				oklist.add(entry);
			}
		}
		return oklist;

	}

	public Hero getCharacterById(int id) {
		try {
			return dao.get(id);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public Hero getCache(int key) {
		return GameServer.vlineServerManager.getCharacterById(key);
	}

	public void cacheToDB(Hero character) throws DataException {
		try {
			dao.update(character);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 顺序批处理
	 * 
	 * @param sqls
	 */
	public void excuteSql(List<String> sqls) {
		try {
			dao.processCharacterRelation(sqls);
		} catch (DataException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @return 更新充气娃娃记录
	 */
	public void updateClock12Characters() {
		dao.updateCharacterByName("updateClock12Characters");
	}

	/**
	 * @return 清空幸运值
	 */
	public void clearLuckValueForHiddenWeaponCharacter() {
		dao.updateCharacterByName("clearLuckValueForHiddenWeapon");
	}

	/**
	 * @return 清空幸运值
	 */
	public void clearLuckValueForDantian() {
		GameServer.executorServiceForDB.execute(new Runnable() {

			@Override
			public void run() {
				dao.updateCharacterByName("clearZhuFu");
			}
		});

	}

	/**
	 * 清空悟道幸运值
	 */
	public void clearLuckValueForDGWD() {
		GameServer.executorServiceForDB.execute(new Runnable() {
			@Override
			public void run() {
				dao.updateCharacterByName("clearDGWDZhuFu");
			}
		});
	}

	/**
	 * @return 人物等级 前100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingCharacters() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingCharacter", map);
	}

	/**
	 * @return 财富榜排行，前100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingsMoney2() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingMoney2", map);
	}

	/**
	 * @return 经脉100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingsjingmai() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingjingmai", map);
	}

	/**
	 * @return 连斩100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingslianzhan() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankinglianzhan", map);
	}

	/**
	 * @return 战场声望100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingszhanchang() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingzhanchang", map);
	}

	/**
	 * @return boss100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingsboss() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingbossjisha", map);
	}

	/**
	 * @return 武学境界100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingswuxuejingjie() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingwuxuejingjie", map);
	}

	/**
	 * @return 成就100
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingchengjiu() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingchingjiu", map);
	}

	/**
	 * @return 人物城战
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingchengzhan() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankingchengzhan", map);
	}

	/**
	 * @return 人物论剑
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankinglunjian() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankinglunjian", map);
	}

	public List<CharacterRanking> getRankingtongjilunjian(int level) {
		return dao.selectLevel(level, "getRankingtongjilunjian");
	}

	public List<CharacterRanking> getRankingtongjimoney(int level) {
		return dao.selectLevel(level, "getRankingtongjimoney");
	}

	public List<CharacterRanking> getRankingtongjichengzhan(int level) {
		return dao.selectLevel(level, "getRankingtongjichengzhan");
	}

	public List<CharacterRanking> getRankingtongjibossjisha(int level) {
		return dao.selectLevel(level, "getRankingtongjibossjisha");
	}

	public List<CharacterRanking> getRankingtongjilianzhan(int level) {
		return dao.selectLevel(level, "getRankingtongjilianzhan");
	}

	public List<CharacterRanking> getRankingtongjijingmai(int level) {
		return dao.selectLevel(level, "getRankingtongjijingmai");
	}

	public List<CharacterRanking> getRankingtongjiwuxuejingjie(int level) {
		return dao.selectLevel(level, "getRankingtongjiwuxuejingjie");
	}

	public List<CharacterRanking> getRankingtongjizhanchang(int level) {
		return dao.selectLevel(level, "getRankingtongjizhanchang");
	}

	public List<CharacterRanking> getRankingtongjichengjiu(int level) {
		return dao.selectLevel(level, "getRankingtongjichengjiu");
	}

	public List<CharacterRanking> getRankingtongjianqi(int level) {
		return dao.selectLevel(level, "getRankingtongjianqi");
	}

	/**
	 * @return 花最多前10
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<CharacterRanking> rankingshua() {
		Map map = new HashMap();
		map.put("id", RankingManager.getInstance().getCharactersList_GM());
		return dao.selectRanKing("getRankinghua", map);
	}

	/**
	 * @return 更新崇拜
	 */
	public void updateCharacterchongbai(int characterid) {
		try {
			dao.update("updateCharacterchongbai", characterid);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @return 更新鄙视
	 */
	public void updateCharacterbishi(int characterid) {
		try {
			dao.update("updateCharacterbishi", characterid);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 重置boss 归零
	 */
	public void updateCharacterBoss() {
		try {
			dao.update("ResetBoss", 0);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 重置城站和论剑 归零
	 */
	public void updateCharacterChengZhanAndLunJian() {
		try {
			dao.update("ResetChengzhanAndLunJian", 0);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 经脉被动经验添加
	 */
	public void addCharacter_ChannelBeiDongExp() {
		try {
			dao.update("addUpdateChannelBeidongExp", 0);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
