package net.snake.gamemodel.gift.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.bean.GiftPacksComparator;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 物品包裹表数据管理
 * 
 */

public class GiftPacksManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(GiftPacksManager.class);

	private GiftPacksDAO dao = new GiftPacksDAO(SystemFactory.getGamedataSqlMapClient());
	private Map<Integer, GiftPacks> giftMap = new HashMap<Integer, GiftPacks>();
	private List<GiftPacks> gradeGiftList = new ArrayList<GiftPacks>();// 升级
	private List<GiftPacks> meetGiftList = new ArrayList<GiftPacks>();// 见面
	private List<GiftPacks> inviteGiftList = new ArrayList<GiftPacks>();// 邀请
	private List<GiftPacks> loginGiftList = new ArrayList<GiftPacks>();// 登入
	private List<GiftPacks> onlineGiftList = new ArrayList<GiftPacks>();// 登入
	private List<GiftPacks> dayQiandaoGiftList = new ArrayList<GiftPacks>();// 每日签到礼包
	private Map<Integer, GiftPacks> eachLevel = new HashMap<Integer, GiftPacks>();
	// 单例实现=====================================
	private static GiftPacksManager instance;

	private GiftPacksManager() {
	}

	public static GiftPacksManager getInstance() {
		if (instance == null) {
			instance = new GiftPacksManager();
		}
		return instance;
	}

	public GiftPacks getGiftPacksById(int giftId) {
		return giftMap.get(giftId);
	}

	public void initGiftPacksMap() {
		List<GiftPacks> list = selectAllGiftPacksList();
		if (list == null || list.size() == 0) {
			return;
		}
		try {
			BeanTool.addOrUpdate(this.giftMap, list, "id");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		List<GiftPacks> gradeGift = new ArrayList<GiftPacks>();// 升级
		List<GiftPacks> meetGiftList = new ArrayList<GiftPacks>();//
		List<GiftPacks> onlineGiftList = new ArrayList<GiftPacks>();//
		List<GiftPacks> loginGiftList = new ArrayList<GiftPacks>();//
		List<GiftPacks> dayQiandaoGiftList1 = new ArrayList<GiftPacks>();
		Map<Integer, GiftPacks> eachLevel = new HashMap<Integer, GiftPacks>();
		for (GiftPacks gp : this.giftMap.values()) {
			gp.init();
			if (gp.getType() == GiftPacks.gradeType) {
				gradeGift.add(gp);
			} else if (gp.getType() == GiftPacks.meetType) {
				meetGiftList.add(gp);
			} else if (gp.getType() == GiftPacks.inviteType) {
				inviteGiftList.add(gp);
			} else if (gp.getType() == GiftPacks.loginType) {
				loginGiftList.add(gp);
			} else if (gp.getType() == GiftPacks.onlineType) {
				onlineGiftList.add(gp);
			} else if (gp.getType() == GiftPacks.dayQiandaoType) {
				dayQiandaoGiftList1.add(gp);
			} else if (gp.getType() == GiftPacks.eachLevelType) {
				eachLevel.put(gp.getGradeLimit(), gp);
			}
		}
		this.gradeGiftList = gradeGift;
		this.meetGiftList = meetGiftList;
		this.onlineGiftList = onlineGiftList;
		this.loginGiftList = loginGiftList;
		this.dayQiandaoGiftList = dayQiandaoGiftList1;
		this.eachLevel = eachLevel;
		Collections.sort(this.gradeGiftList, new GiftPacksComparator());
		Collections.sort(this.meetGiftList, new GiftPacksComparator());
		Collections.sort(this.onlineGiftList, new GiftPacksComparator());
		Collections.sort(this.loginGiftList, new GiftPacksComparator());
		Collections.sort(this.dayQiandaoGiftList, new GiftPacksComparator());
	}

	@SuppressWarnings("unchecked")
	public List<GiftPacks> selectAllGiftPacksList() {
		try {
			return this.dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void reload() {
		initGiftPacksMap();
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "giftpacks";
	}

	public List<GiftPacks> getGradeGiftList() {
		return gradeGiftList;
	}

	public List<GiftPacks> getMeetGiftList() {
		return meetGiftList;
	}

	public List<GiftPacks> getInviteGiftList() {
		return inviteGiftList;
	}

	public List<GiftPacks> getDayQiandaoGiftList() {
		return dayQiandaoGiftList;
	}

	public List<GiftPacks> getLoginGiftList() {
		return loginGiftList;
	}

	public List<GiftPacks> getOnlineGiftList() {
		return onlineGiftList;
	}

	public GiftPacks getEachLevelByGrade(int grade) {
		return eachLevel.get(grade);
	}
}
