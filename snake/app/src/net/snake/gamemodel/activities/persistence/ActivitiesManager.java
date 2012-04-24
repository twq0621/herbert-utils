package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.snake.GameServer;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.account.persistence.AccountManager;
import net.snake.gamemodel.activities.bean.Activities;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;


/**
 * 账号管理
 * 
 */

public class ActivitiesManager {
	private static final Logger logger = Logger.getLogger(ActivitiesManager.class);
	// 运营账号在rmsvip001-rmsvip510之间 则奖励1000礼金
	public static final int YUN_YING_TYPE = 103;
	// 老用户登陆有礼 活动
	public static final int OLD_USERNAME_TYPE = 102;
	// 老用户奖励数据
	public static final String OLD_USERNAME_GOODS = "{\"funType\":\"1\",\"num\":\"1\",\"ext\":\"["
			+ "{\"id\":\"1300\",\"num\":\"1\",\"bind\":\"1\"}," + "{\"id\":\"1301\",\"num\":\"1\",\"bind\":\"1\"},"
			+ "{\"id\":\"3300\",\"num\":\"2\",\"bind\":\"1\"}" + "]\"}";

	static List<String> yunyingIds = new ArrayList<String>();
	private ActivitiesDAO dao = new ActivitiesDAO(SystemFactory.getAccountSqlMapClient());

	// 单例实现=====================================
	private static ActivitiesManager instance;

	private ActivitiesManager() {
	}

	// 单例实现========================================
	public static ActivitiesManager getInstance() {
		if (instance == null) {
			instance = new ActivitiesManager();
		}
		return instance;
	}

	public void addActivities(Activities activities) {
		try {
			dao.insert(activities);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Activities> getActivities(int account_id) {
		try {
			return dao.selectByaId(account_id);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return new ArrayList<Activities>();
	}

	@SuppressWarnings("unchecked")
	public List<Activities> getActivitiesByaIdcId(int accountId, int characterId) {
		try {
			return dao.selectByaIdcId(accountId, characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return new ArrayList<Activities>();
	}

	@SuppressWarnings("unchecked")
	public List<Activities> getActivities(int account_id, int type) {
		try {
			return dao.selectByAccountAndType(account_id, type);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return new ArrayList<Activities>();
	}

	@SuppressWarnings("rawtypes")
	public Activities getActivitie(int accountId, int characterId, int type) {
		try {
			List list = dao.selectByaIdcIdType(accountId, characterId, type);
			if (list.size() > 0)
				return (Activities) list.get(0);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public void updateCount(Activities activities) {
		try {
			dao.updateCount(activities);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
		}
	}

	public void addActivities(int accoount_id, int countlimit, int type, String ext) {
		Activities activities = new Activities();
		activities.setAccountId(accoount_id);
		activities.setType(type);
		activities.setCount(0);
		activities.setCountLimit(countlimit);
		activities.setChongzhiqian(-1f);
		activities.setExt(ext);
		addActivities(activities);
	}

	/**
	 * 为玩家添加奖励物品
	 * 
	 * @param character
	 */
	public void addActivitiesGoods(Hero ncharacter) {
		for (Activities activities : getActivitiesByaIdcId(ncharacter.getAccountId(), ncharacter.getId())) {
			addActivitieGoods(ncharacter, activities.getType());
		}
	}

	/**
	 * 为玩家添加奖励物品
	 * 
	 * @param character
	 * @param type
	 */
	public void addActivitieGoods(Hero character, int type) {
		Activities activities = getActivitie(character.getAccountId(), character.getId(), type);
		if (activities != null && activities.getCount() == 0) {
			String jsonStr = activities.getExt();
			if (jsonStr != null && !jsonStr.equals("")) {
				JSONObject jsonObj = JSONObject.fromObject(jsonStr);
				int funType = jsonObj.getInt("funType");
				if (funType == 1) { // 道具奖励
					JSONArray jsonArr = JSONArray.fromObject(jsonObj.get("ext"));
					List<CharacterGoods> addlist = new ArrayList<CharacterGoods>();
					for (int i = 0; i < jsonArr.size(); i++) {
						JSONObject obj = jsonArr.getJSONObject(i);
						CharacterGoods cg = CharacterGoods.createCharacterGoods(obj.getInt("num"), obj.getInt("id"), 0);
						cg.setBind((byte) obj.getInt("bind"));
						addlist.add(cg);
					}
					character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(null, addlist);
				} else if (funType == 2) { // 礼金奖励
					CharacterPropertyManager.changeLijin(character, jsonObj.getInt("ext"));
				}
				activities.setCount(1);
				ActivitiesManager.getInstance().updateCount(activities);
			}
		}
	}

//	/**
//	 * RMS老用户登陆有礼 活动期间，所有RMS游戏平台的玩家只要登入RMS即可获得奖励。
//	 * 
//	 * @param character
//	 * @throws SQLException
//	 */
//	public void addOldUserActivitiesGoods(Hero character) throws SQLException {
//		Account account = AccountManager.getInstance().selectByAccountid(character.getAccountId());
//		if (yunyingIds.contains(account.getYunyingId())) {
//			addActivities(account.getId(), character.getId(), OLD_USERNAME_TYPE, OLD_USERNAME_GOODS);
//			addActivitieGoods(character, OLD_USERNAME_TYPE);
//		}
//	}

	/**
	 * 马来西亚版本能做一个这样的策略吗？ 登陆的时候检测如果 运营账号在rmsvip001-rmsvip510之间
	 * 则奖励1000礼金，只奖励一次，不重复奖励
	 * 
	 * @param ncharacter
	 * @throws SQLException
	 */
	public void addYunYingActivitiesGoods(Hero character) throws SQLException {
		Account account = AccountManager.getInstance().selectByAccountid(character.getAccountId());
		if (isTheVip(account.getYunyingId())) {
			if (getActivities(account.getId(), YUN_YING_TYPE).size() == 0) {
				CharacterPropertyManager.changeLijin(character, 2000);
				addActivities(account.getId(), character.getId(), YUN_YING_TYPE, "");
			}
		}
	}

	private boolean isTheVip(String yunyingId) {
		if (yunyingId == null)
			return false;
		if (yunyingId.length() != 9)
			return false;
		if (!yunyingId.substring(0, 6).equals("rmsvip"))
			return false;
		int i = 0;
		try {
			i = Integer.parseInt(yunyingId.substring(6));
		} catch (Exception e) {
			return false;
		}
		if (i >= 1 && i <= 510)
			return true;
		return false;
	}

	static {
		// 初始化老用户数据
		String oldusernames = GameServer.serverparam.getProperty("server.oldusernames") == null ? ""
				: GameServer.serverparam.getProperty("server.oldusernames");
		String names[] = oldusernames.split(",");
		yunyingIds.clear();
		for (String s : names) {
			yunyingIds.add(s);
		}
	}
}
