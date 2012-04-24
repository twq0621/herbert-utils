package net.snake.gamemodel.gift.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.ai.util.ArithmeticUtils;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.gift.logic.GiftPacksHuchiRandomGoods;
import net.snake.gamemodel.gift.logic.GiftPacksRandom;
import net.snake.gamemodel.gift.logic.GiftPacksRandomGoods;
import net.snake.gamemodel.gift.logic.HuchiRandom;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.ibatis.IbatisEntity;

public class GiftPacks implements IbatisEntity {
	private static final Logger logger = Logger.getLogger(GiftPacks.class);
	/**
	 * 游戏礼包标记id t_gift_packs.id
	 * 
	 * 
	 */
	private Integer id;
	/**
	 * 0升级礼包/1见面有礼/2邀请有礼/3登入有礼/4在线有礼 t_gift_packs.type
	 * 
	 * 
	 */
	private Byte type;
	/**
	 * 升级礼包等级限制 t_gift_packs.grade_limit
	 * 
	 * 
	 */
	private Integer gradeLimit;
	/**
	 * 见面有礼时间限制 单位 分 t_gift_packs.time_limit
	 * 
	 * 
	 */
	private Integer timeLimit;
	/**
	 * 邀请次数限制 t_gift_packs.invite_limit
	 * 
	 * 
	 */
	private Integer inviteLimit;
	/**
	 * 登入有礼 连续登入次数限制 t_gift_packs.login_limit
	 * 
	 * 
	 */
	private Integer loginLimit;
	/**
	 * 本月在线时间限制 单位小时 t_gift_packs.online_time_limit
	 * 
	 * 
	 */
	private Integer onlineTimeLimit;
	/**
	 * 奖励的铜钱 t_gift_packs.copper
	 * 
	 * 
	 */
	private Integer copper;
	/**
	 * 奖励礼金 t_gift_packs.lijin
	 * 
	 * 
	 */
	private Integer lijin;
	/**
	 * 奖励的物品ID与数量（格式：道具ID*道具数量;!yyyymmddhhmmss#道具ID*道具数量;!持续秒数#道具ID*道具数量;） t_gift_packs.goods
	 * 
	 * 
	 */
	private String goods;
	/**
	 * t_gift_packs.desc_str
	 * 
	 * 
	 */
	private String descStr;
	/**
	 * 物品某型id 关联物品模型表 t_gift_packs.good_id
	 * 
	 * 
	 */
	private Integer goodId;
	/**
	 * 互斥几率开出物品 格式 几率#goodid*count,几率#goodid*count;几率#goodid*count,几率#goodid*count t_gift_packs.f_huchi_random_good
	 * 
	 * 
	 */
	private String fHuchiRandomGood;
	/**
	 * 随机几率开除物品 几率#goodId*count;几率#goodId*count t_gift_packs.f_random_good
	 * 
	 * 
	 */
	private String fRandomGood;
	/**
	 * 国际化 t_gift_packs.desc_str_i18n
	 * 
	 * 
	 */
	private String descStrI18n;
	/**
	 * 礼包名称 t_gift_packs.f_name
	 * 
	 * 
	 */
	private String fName;

	/**
	 * 游戏礼包标记id t_gift_packs.id
	 * 
	 * @return the value of t_gift_packs.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 游戏礼包标记id t_gift_packs.id
	 * 
	 * @param id
	 *            the value for t_gift_packs.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 0升级礼包/1见面有礼/2邀请有礼/3登入有礼/4在线有礼/5礼包/6签到类型礼包/7每级惊喜 t_gift_packs.type
	 * 
	 * @return the value of t_gift_packs.type
	 * 
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 0升级礼包/1见面有礼/2邀请有礼/3登入有礼/4在线有礼/5礼包/6签到类型礼包/7每级惊喜 t_gift_packs.type
	 * 
	 * @param type
	 *            the value for t_gift_packs.type
	 * 
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * 升级礼包等级限制 t_gift_packs.grade_limit
	 * 
	 * @return the value of t_gift_packs.grade_limit
	 * 
	 */
	public Integer getGradeLimit() {
		return gradeLimit;
	}

	/**
	 * 升级礼包等级限制 t_gift_packs.grade_limit
	 * 
	 * @param gradeLimit
	 *            the value for t_gift_packs.grade_limit
	 * 
	 */
	public void setGradeLimit(Integer gradeLimit) {
		this.gradeLimit = gradeLimit;
	}

	/**
	 * 见面有礼时间限制 单位 分 t_gift_packs.time_limit
	 * 
	 * @return the value of t_gift_packs.time_limit
	 * 
	 */
	public Integer getTimeLimit() {
		return timeLimit;
	}

	/**
	 * 见面有礼时间限制 单位 分 t_gift_packs.time_limit
	 * 
	 * @param timeLimit
	 *            the value for t_gift_packs.time_limit
	 * 
	 */
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * 邀请次数限制 t_gift_packs.invite_limit
	 * 
	 * @return the value of t_gift_packs.invite_limit
	 * 
	 */
	public Integer getInviteLimit() {
		return inviteLimit;
	}

	/**
	 * 邀请次数限制 t_gift_packs.invite_limit
	 * 
	 * @param inviteLimit
	 *            the value for t_gift_packs.invite_limit
	 * 
	 */
	public void setInviteLimit(Integer inviteLimit) {
		this.inviteLimit = inviteLimit;
	}

	/**
	 * 登入有礼 连续登入次数限制 t_gift_packs.login_limit
	 * 
	 * @return the value of t_gift_packs.login_limit
	 * 
	 */
	public Integer getLoginLimit() {
		return loginLimit;
	}

	/**
	 * 登入有礼 连续登入次数限制 t_gift_packs.login_limit
	 * 
	 * @param loginLimit
	 *            the value for t_gift_packs.login_limit
	 * 
	 */
	public void setLoginLimit(Integer loginLimit) {
		this.loginLimit = loginLimit;
	}

	/**
	 * 本月在线时间限制 单位小时 t_gift_packs.online_time_limit
	 * 
	 * @return the value of t_gift_packs.online_time_limit
	 * 
	 */
	public Integer getOnlineTimeLimit() {
		return onlineTimeLimit;
	}

	/**
	 * 本月在线时间限制 单位小时 t_gift_packs.online_time_limit
	 * 
	 * @param onlineTimeLimit
	 *            the value for t_gift_packs.online_time_limit
	 * 
	 */
	public void setOnlineTimeLimit(Integer onlineTimeLimit) {
		this.onlineTimeLimit = onlineTimeLimit;
	}

	/**
	 * 奖励的铜钱 t_gift_packs.copper
	 * 
	 * @return the value of t_gift_packs.copper
	 * 
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 奖励的铜钱 t_gift_packs.copper
	 * 
	 * @param copper
	 *            the value for t_gift_packs.copper
	 * 
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 奖励礼金 t_gift_packs.lijin
	 * 
	 * @return the value of t_gift_packs.lijin
	 * 
	 */
	public Integer getLijin() {
		return lijin;
	}

	/**
	 * 奖励礼金 t_gift_packs.lijin
	 * 
	 * @param lijin
	 *            the value for t_gift_packs.lijin
	 * 
	 */
	public void setLijin(Integer lijin) {
		this.lijin = lijin;
	}

	/**
	 * 奖励的物品ID与数量（格式：道具ID*道具数量;!yyyymmddhhmmss#道具ID*道具数量;!持续秒数#道具ID*道具数量;） t_gift_packs.goods
	 * 
	 * @return the value of t_gift_packs.goods
	 * 
	 */
	public String getGoods() {
		return goods;
	}

	/**
	 * 奖励的物品ID与数量（格式：道具ID*道具数量;!yyyymmddhhmmss#道具ID*道具数量;!持续秒数#道具ID*道具数量;） t_gift_packs.goods
	 * 
	 * @param goods
	 *            the value for t_gift_packs.goods
	 * 
	 */
	public void setGoods(String goods) {
		this.goods = goods;
	}

	/**
	 * t_gift_packs.desc_str
	 * 
	 * @return the value of t_gift_packs.desc_str
	 * 
	 */
	public String getDescStr() {
		return descStr;
	}

	/**
	 * t_gift_packs.desc_str
	 * 
	 * @param descStr
	 *            the value for t_gift_packs.desc_str
	 * 
	 */
	public void setDescStr(String descStr) {
		this.descStr = descStr;
	}

	/**
	 * 物品某型id 关联物品模型表 t_gift_packs.good_id
	 * 
	 * @return the value of t_gift_packs.good_id
	 * 
	 */
	public Integer getGoodId() {
		return goodId;
	}

	/**
	 * 物品某型id 关联物品模型表 t_gift_packs.good_id
	 * 
	 * @param goodId
	 *            the value for t_gift_packs.good_id
	 * 
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	/**
	 * 互斥几率开出物品 格式 几率#goodid*count,几率#goodid*count;几率#goodid*count,几率#goodid*count t_gift_packs.f_huchi_random_good
	 * 
	 * @return the value of t_gift_packs.f_huchi_random_good
	 * 
	 */
	public String getfHuchiRandomGood() {
		return fHuchiRandomGood;
	}

	/**
	 * 互斥几率开出物品 格式 几率#goodid*count,几率#goodid*count;几率#goodid*count,几率#goodid*count t_gift_packs.f_huchi_random_good
	 * 
	 * @param fHuchiRandomGood
	 *            the value for t_gift_packs.f_huchi_random_good
	 * 
	 */
	public void setfHuchiRandomGood(String fHuchiRandomGood) {
		this.fHuchiRandomGood = fHuchiRandomGood;
	}

	/**
	 * 随机几率开除物品 几率#goodId*count;几率#goodId*count t_gift_packs.f_random_good
	 * 
	 * @return the value of t_gift_packs.f_random_good
	 * 
	 */
	public String getfRandomGood() {
		return fRandomGood;
	}

	/**
	 * 随机几率开除物品 几率#goodId*count;几率#goodId*count t_gift_packs.f_random_good
	 * 
	 * @param fRandomGood
	 *            the value for t_gift_packs.f_random_good
	 * 
	 */
	public void setfRandomGood(String fRandomGood) {
		this.fRandomGood = fRandomGood;
	}

	/**
	 * 国际化 t_gift_packs.desc_str_i18n
	 * 
	 * @return the value of t_gift_packs.desc_str_i18n
	 * 
	 */
	public String getDescStrI18n() {
		return descStrI18n;
	}

	/**
	 * 国际化 t_gift_packs.desc_str_i18n
	 * 
	 * @param descStrI18n
	 *            the value for t_gift_packs.desc_str_i18n
	 * 
	 */
	public void setDescStrI18n(String descStrI18n) {
		this.descStrI18n = descStrI18n;
	}

	/**
	 * 礼包名称 t_gift_packs.f_name
	 * 
	 * @return the value of t_gift_packs.f_name
	 * 
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * 礼包名称 t_gift_packs.f_name
	 * 
	 * @param fName
	 *            the value for t_gift_packs.f_name
	 * 
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	private Map<Integer, List<CharacterGoods>> goodMap = new HashMap<Integer, List<CharacterGoods>>();
	private List<GiftPacksRandom> randomGoodsList = new ArrayList<GiftPacksRandom>();

	private void initRandomGoodsInfo() {
		List<GiftPacksRandom> temp = new ArrayList<GiftPacksRandom>();
		if (this.fHuchiRandomGood != null && this.fHuchiRandomGood.length() > 0) {
			String[] goodStr = this.fHuchiRandomGood.split(";");
			for (int i = 0; i < goodStr.length; i++) {
				GiftPacksRandom random = randHuchiGoodStrToRandom(goodStr[i]);
				temp.add(random);
			}
		}
		if (this.fRandomGood != null && this.fRandomGood.length() > 0) {
			String[] goodStr = this.fRandomGood.split(";");
			for (int i = 0; i < goodStr.length; i++) {
				GiftPacksRandom random = randGoodStrToRandom(goodStr[i]);
				temp.add(random);
			}
		}
		randomGoodsList = temp;
	}

	/**
	 * 互斥物品随机出
	 * 
	 * @param goodString
	 *            互斥百分百开除一件物品格式如： @!date#jilv_goodId*count,jilv_goodId*count (说明@标识开出物品要公告 !表示开出物品不绑定 #前面date标识物品失效时间（支持两种失效时间）特别说明：开出多个组互斥物品用;隔开)
	 * @return
	 */
	private GiftPacksRandom randHuchiGoodStrToRandom(String goodString) {
		String[] strGoods = goodString.split(",");
		int randMax = 0;
		GiftPacksHuchiRandomGoods random = new GiftPacksHuchiRandomGoods();
		for (int i = 0; i < strGoods.length; i++) {
			String[] str = strGoods[i].split("[*]");
			String goodstr = str[0];
			boolean isIgnoreBind = false;
			if (str[0].contains("!")) {
				goodstr = str[0].replace("!", "");
				isIgnoreBind = true;
			}
			boolean isNoticGood = false;
			if (str[0].contains("@")) {
				goodstr = goodstr.replace("@", "");
				isNoticGood = true;
			}
			String goodIdStr = goodstr;
			Date lostDate = null;
			int toBadLostTime = 0;
			if (goodstr.contains("#")) {
				String[] goods = goodstr.split("#");
				goodIdStr = goods[1];
				if (goods[0].length() > 8) {
					lostDate = ArithmeticUtils.stringToDate(goods[0]);
				} else {
					toBadLostTime = Integer.parseInt(goods[0]);
				}
			}
			String[] ss = goodIdStr.split("_");
			int jilv = Integer.parseInt(ss[0]);
			int goodId = Integer.parseInt(ss[1]);
			randMax = randMax + jilv;
			Goodmodel gm = GoodmodelManager.getInstance().get(goodId);
			if (gm == null) {
				return null;
			}
			int count = Integer.parseInt(str[1]);
			CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, 0);
			cg.setGoodmodelId(goodId);
			cg.setCount(count);
			if (this.getType() != 5) {
				cg.setBind(CommonUseNumber.byte1);
			} else {
				cg.setBind(CommonUseNumber.byte0);
				cg.setIgnoreBind(isIgnoreBind);
			}
			cg.setNoticeGood(isNoticGood);
			cg.setLastDate(lostDate);
			cg.setToBadLostTime(toBadLostTime);
			HuchiRandom cgRandom = new HuchiRandom(cg, randMax);
			random.addHuchiRandom(cgRandom);
			random.setRandomMax(randMax);
		}
		return random;
	}

	/**
	 * 普通物品随机出
	 * 
	 * @param goodString
	 *            随机开除物品的格式： @!date#jilv_goodId*count (说明@标识开出物品要公告 !表示开出物品不绑定 #前面date标识物品失效时间（支持两种失效时间）jilv/10000是该物品实际开出几率，特别说明：多组随机物品用;隔开)
	 * @return
	 */
	private GiftPacksRandom randGoodStrToRandom(String goodString) {
		String[] str = goodString.split("[*]");
		String goodstr = str[0];
		boolean isIgnoreBind = false;
		if (str[0].contains("!")) {
			goodstr = str[0].replace("!", "");
			isIgnoreBind = true;
		}
		boolean isNoticGood = false;
		if (str[0].contains("@")) {
			goodstr = goodstr.replace("@", "");
			isNoticGood = true;
		}
		String goodIdStr = goodstr;
		Date lostDate = null;
		int toBadLostTime = 0;
		if (goodstr.contains("#")) {
			String[] goods = goodstr.split("#");
			goodIdStr = goods[1];
			if (goods[0].length() > 8) {
				lostDate = ArithmeticUtils.stringToDate(goods[0]);
			} else {
				toBadLostTime = Integer.parseInt(goods[0]);
			}
		}
		String[] ss = goodIdStr.split("_");
		int jilv = Integer.parseInt(ss[0]);
		int goodId = Integer.parseInt(ss[1]);
		Goodmodel gm = GoodmodelManager.getInstance().get(goodId);
		if (gm == null) {
			return null;
		}
		int count = Integer.parseInt(str[1]);
		CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, 0);
		cg.setGoodmodelId(goodId);
		cg.setCount(count);
		if (this.getType() != 5) {
			cg.setBind(CommonUseNumber.byte1);
		} else {
			cg.setBind(CommonUseNumber.byte0);
			cg.setIgnoreBind(isIgnoreBind);
		}
		cg.setNoticeGood(isNoticGood);
		cg.setLastDate(lostDate);
		cg.setToBadLostTime(toBadLostTime);
		GiftPacksRandom random = new GiftPacksRandomGoods(cg);
		random.setProbability(jilv);
		return random;
	}

	public void init() {
		try {
			initGoodsInfo();
			initRandomGoodsInfo();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void initGoodsInfo() {
		if (this.goods == null || this.goods.equals("")) {
			return;
		}
		String[] goodStr = this.goods.split(";");
		Map<Integer, List<CharacterGoods>> map = initMenpaiGoodsMap();
		for (int i = 0; i < goodStr.length; i++) {
			putmenpaiGoodsToMap(map, goodStr[i]);
		}
		this.goodMap = map;
	}

	private Map<Integer, List<CharacterGoods>> initMenpaiGoodsMap() {
		Map<Integer, List<CharacterGoods>> map = new HashMap<Integer, List<CharacterGoods>>();
		for (int i = 1; i < 5; i++) {
			map.put(i, new ArrayList<CharacterGoods>());
		}
		return map;
	}

	private void putmenpaiGoodsToMap(Map<Integer, List<CharacterGoods>> map, String goodsStr) {
		String[] str = goodsStr.split("[*]");// 物品部分*数量部分
		String goodstr = str[0];
		boolean isIgnoreBind = false;
		if (str[0].contains("!")) {
			goodstr = str[0].replace("!", "");
			isIgnoreBind = true;
		}

		boolean isNoticGood = false;
		if (str[0].contains("@")) {
			goodstr = goodstr.replace("@", "");
			isNoticGood = true;
		}

		String goodIdStr = goodstr;
		Date lostDate = null;
		int toBadLostTime = 0;
		if (goodstr.contains("#")) {
			String[] goods = goodstr.split("#");
			goodIdStr = goods[1];
			if (goods[0].length() > 8) {
				lostDate = ArithmeticUtils.stringToDate(goods[0]);
			} else {
				toBadLostTime = Integer.parseInt(goods[0]);
			}
		}
		int goodId = Integer.parseInt(goodIdStr);
		Goodmodel gm = GoodmodelManager.getInstance().get(goodId);
		if (gm == null) {
			return;
		}
		if (str[1].contains("_")) {
			String[] limitStr = str[1].split("_");
			int count = Integer.parseInt(limitStr[0]);
			int menpai = Integer.parseInt(limitStr[1]);
			List<CharacterGoods> list = map.get(menpai);
			if (list != null) {
				CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, 0);
				cg.setGoodmodelId(goodId);
				cg.setCount(count);
				if (this.getType() != 5) {
					cg.setBind(CommonUseNumber.byte1);
				} else {
					cg.setBind(CommonUseNumber.byte0);
					cg.setIgnoreBind(isIgnoreBind);
				}
				cg.setNoticeGood(isNoticGood);
				cg.setLastDate(lostDate);
				cg.setToBadLostTime(toBadLostTime);
				list.add(cg);
			}
		} else {
			int count = Integer.parseInt(str[1]);
			CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, 0);
			cg.setGoodmodelId(goodId);
			cg.setCount(count);
			if (this.getType() != 5) {
				cg.setBind(CommonUseNumber.byte1);
			} else {
				cg.setBind(CommonUseNumber.byte0);
				cg.setIgnoreBind(isIgnoreBind);
			}
			cg.setNoticeGood(isNoticGood);
			cg.setLastDate(lostDate);
			cg.setToBadLostTime(toBadLostTime);
			Collection<List<CharacterGoods>> collection = map.values();
			for (List<CharacterGoods> list : collection) {
				list.add(cg);
			}
		}
	}

	public List<CharacterGoods> getGoodlist(int menpai) {
		List<CharacterGoods> list = new ArrayList<CharacterGoods>();
		List<CharacterGoods> list1 = this.goodMap.get(menpai);
		if (list1 != null) {
			list.addAll(list1);
		}
		init();
		for (GiftPacksRandom random : randomGoodsList) {
			CharacterGoods cg = random.randomGoods();
			if (cg != null) {
				list.add(cg);
			}
		}
		return list;
	}

	public static int gradeType = 0;
	public static int meetType = 1;
	public static int inviteType = 2;
	public static int loginType = 3;
	public static int onlineType = 4;
	public static int shopType = 5;
	public static int dayQiandaoType = 6;
	public static int eachLevelType = 7;
}
