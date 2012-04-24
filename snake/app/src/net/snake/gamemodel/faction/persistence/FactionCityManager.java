package net.snake.gamemodel.faction.persistence;

import net.snake.commons.Language;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.bean.FactionCityConfig;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.faction.response.factioncity.FactionCityMsg51144;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.ibatis.SystemFactory;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
public class FactionCityManager {
	private static Logger logger = Logger.getLogger(FactionCityManager.class);
	public static int gongchengMapId = 20037;
	public static int youlongzhirenId = 2003;
	public static int gongchengLine = 3;
	// 守方复活点 【66，0】 - 【158，58】
	// 攻方复活点 【0，102】 - 【92，154】
	public static short[] gongRelive = { 28, 120, 74, 142 };
	public static short[] shouRelive = { 103, 29, 136, 49 };
	public static short[] gongdefalseRelive = { 33, 137 };
	public static short[] shoudefalseRelive = { 134, 32 };
	public static int reliveRand = 25;
	public static int startHorse = 20;
	public static int endHorse = 21;
	public static int defaltTaxRate = 500;
	private static FactionCityDAO dao = new FactionCityDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static FactionCityManager instance;
	private FactionCity factionCity;

	private FactionCityManager() {
	}

	public static FactionCityManager getInstance() {
		if (instance == null) {
			instance = new FactionCityManager();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public void init() {
		try {
			List<FactionCity> list = dao.select();
			if (list == null || list.size() == 0) {
				factionCity = new FactionCity();
				factionCity.setAllTaxes(0l);
				factionCity.setFactionId(0);
				factionCity.setNotice("");
				factionCity.setTaxRate(FactionCityManager.defaltTaxRate);
				factionCity.setTdTaxes(0);
				factionCity.setUpdateTime(new Date());
				factionCity.setYdFactionId(0);
				factionCity.setYdTaxes(0);
				factionCity.initTodayInfo();
				insert(factionCity);
				return;
			}
			factionCity = list.get(0);

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}


	public void insert(FactionCity factionCity) {
		try {
			dao.insert(factionCity);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void updateFactionCity(FactionCity factionCity) {
		try {
			dao.updateById(factionCity);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public FactionCity getFactionCity() {
		return factionCity;
	}

	public void setFactionCity(FactionCity factionCity) {
		this.factionCity = factionCity;
	}

	public synchronized int lingquTaxs(FactionController factionController, Hero character) {
		if (factionCity == null || factionCity.getFactionId() <= 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11068));
			return 0;
		}
		if (factionCity.getIsgetTaxes() == 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11071));
			return 0;
		}
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().getFactionCityConfig();
		long num = factionCity.getAllTaxes() * fcc.getAlltaxsScale() / 10000;
		int copper = CharacterPropertyManager.changeCopper(character, (int) num, CopperAction.ADD_OTHER);
		if (copper > 0) {
			factionCity.setAllTaxes(factionCity.getAllTaxes() - num);
			factionCity.setIsgetTaxes(1);
		}
		return copper;
	}

	/**
	 * 领取占城帮会物品奖励
	 * 
	 * @param factionController
	 * @param character
	 * @return
	 */
	public synchronized int lingquGoods(FactionController factionController, Hero character) {
		if (factionCity == null || factionCity.getFactionId() <= 0) {
			character.sendMsg(new FactionCityMsg51144(14511));
			return 0;
		}
		int factionID = factionController.getFaction().getId();
		if (factionCity.getFactionId() != factionID) {
			character.sendMsg(new FactionCityMsg51144(14511));
			return 0;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc == null || fc.getPosition() == 0) {
			character.sendMsg(new FactionCityMsg51144(14511));
			return 0;
		}
		FactionCityConfig fcc = FactionCityConfigManager.getInstance().factionCtConfig;
		if (fc.getPosition() == 1) {
			if (factionCity.getIsgetBangzhu() == 0) {
				if (addCharacterBad(fcc.getBangzhuGood(), fcc.getBangzhuGoodCount(), character)) {
					factionCity.setIsgetBangzhu(1);
					return fcc.getBangzhuGoodCount();
				} else {
					character.sendMsg(new FactionCityMsg51144(14513, Language.FACTION_BANGZHONG));
					return 0;
				}
			} else {
				character.sendMsg(new FactionCityMsg51144(14512, Language.FACTION_BANGZHONG));
				return 0;
			}
		} else if (fc.getPosition() == 2) {
			if (factionCity.getIsgetFubangzhu() == 0) {
				if (addCharacterBad(fcc.getFubangzhuGood(), fcc.getFubangzhuGoodCount(), character)) {
					factionCity.setIsgetFubangzhu(1);
					return fcc.getFubangzhuGoodCount();
				} else {
					character.sendMsg(new FactionCityMsg51144(14513));
					return 0;
				}
			} else {
				character.sendMsg(new FactionCityMsg51144(14512, Language.FACTION_FUBANGZHU));
				return 0;
			}
		} else if (fc.getPosition() == 3) {
			if (factionCity.getIsgetdazhanglao() == 0) {
				if (addCharacterBad(fcc.getDazhanglaoGood(), fcc.getDazhanglaoGoodCount(), character)) {
					factionCity.setIsgetdazhanglao(1);
					return fcc.getDazhanglaoGoodCount();
				} else {
					character.sendMsg(new FactionCityMsg51144(14513));
					return 0;
				}
			} else {
				character.sendMsg(new FactionCityMsg51144(14512, Language.FACTION_DAZHANGLAO));
				return 0;
			}
		} else if (fc.getPosition() == 4) {
			if (factionCity.getIsgetdashixiong() == 0) {
				if (addCharacterBad(fcc.getDashixiongGood(), fcc.getDashixiongGoodCount(), character)) {
					factionCity.setIsgetdashixiong(1);
					return fcc.getDashixiongGood();
				} else {
					character.sendMsg(new FactionCityMsg51144(14513));
					return 0;
				}
			} else {
				character.sendMsg(new FactionCityMsg51144(14512, Language.FACTION_DASHIXIONG));
				return 0;
			}
		} else if (fc.getPosition() == 5) {
			if (factionCity.getIsgetdashijie() == 0) {
				if (addCharacterBad(fcc.getDashijieGood(), fcc.getDashijieGoodCount(), character)) {
					factionCity.setIsgetdashijie(1);
					return fcc.getDashijieGoodCount();
				} else {
					character.sendMsg(new FactionCityMsg51144(14513));
					return 0;
				}
			} else {
				character.sendMsg(new FactionCityMsg51144(14512, Language.FACTION_DASHIJIE));
				return 0;
			}
		}
		return 0;
	}

	private boolean addCharacterBad(int goodId, int count, Hero character) {
		CharacterGoods cg = CharacterGoods.createCharacterGoods(count, goodId, 0);
		cg.setBind(CommonUseNumber.byte1);
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(cg)) {
			return false;
		}
		return character.getCharacterGoodController().getBagGoodsContiner().addGoods(cg);
	}

	/**
	 * 税收的税率
	 * 
	 * @return
	 */
	public float getTaxRate() {
		if (factionCity == null) {
			return defaltTaxRate / 10000f;
		}
		FactionCityConfigManager.getInstance().getFactionCityConfig();
		return factionCity.getTaxRate() / 10000f;
	}

	/**
	 * 税收入账
	 * 
	 * @param addTaxs
	 */
	public synchronized void addTaxs(int addTaxs) {
		if (factionCity == null) {
			return;
		}
		factionCity.setAllTaxes(factionCity.getAllTaxes() + addTaxs);
		factionCity.setTdTaxes(factionCity.getTdTaxes() + addTaxs);
		factionCity.setUpdateTime(new Date());
	}

	public void update() {
		if (factionCity != null) {
			factionCity.initTodayInfo();
		}
	}

	/**
	 * 定时更新数据库
	 */
	public void updateToDb() {
		if (this.factionCity != null) {
			try {
				if (this.factionCity.getId() == null || this.factionCity.getId() < 1) {
					return;
				}
				dao.updateById(this.factionCity);
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void startGongchengInit(GongchengTsMap scene) {
		if (factionCity == null || factionCity.getFactionId() <= 0) {
			scene.startGongcheng();
		} else {
			scene.startGongcheng();
			FactionController factionController = FactionManager.getInstance().getFactionControllerByFactionID(factionCity.getFactionId());
			factionController.lostFactionCityPosition();

		}
	}

	/**
	 * 角色是否可领取奖励类行
	 * 
	 * @param character
	 * @return
	 */
	public int getLingQuRewardType(Hero character) {
		if (!character.getMyFactionManager().isFaction()) {
			return 0;
		}
		FactionController factionController = character.getMyFactionManager().getFactionController();
		int factionID = factionController.getFaction().getId();
		if (factionCity.getFactionId() != factionID || factionID == 0) {
			return 0;
		}
		FactionCharacter fc = factionController.getFactionCharacterByCharacterId(character.getId());
		if (fc.getPosition() == 1 && factionCity.getIsgetBangzhu() == 0) {
			return fc.getPosition();
		} else if (fc.getPosition() == 2 && factionCity.getIsgetFubangzhu() == 0) {
			return fc.getPosition();
		} else if (fc.getPosition() == 3 && factionCity.getIsgetdazhanglao() == 0) {
			return fc.getPosition();

		} else if (fc.getPosition() == 4 && factionCity.getIsgetdashixiong() == 0) {
			return fc.getPosition();
		} else if (fc.getPosition() == 5 && factionCity.getIsgetdashijie() == 0) {
			return fc.getPosition();
		} else {
			return 0;
		}
	}

}
