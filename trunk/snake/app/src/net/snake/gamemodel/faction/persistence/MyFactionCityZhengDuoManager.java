package net.snake.gamemodel.faction.persistence;

import java.util.Collection;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ai.util.ArithmeticUtils;
import net.snake.consts.BuffId;
import net.snake.consts.Property;
import net.snake.consts.PropertyAdditionType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.bean.FactionCity;
import net.snake.gamemodel.faction.logic.MyFactionManager;
import net.snake.gamemodel.faction.response.factioncity.FactionCityMsg51104;
import net.snake.gamemodel.faction.response.factioncity.FactionCityMsg51106;
import net.snake.gamemodel.faction.response.factioncity.FactionCityMsg51108;
import net.snake.gamemodel.faction.response.factioncity.FactionCtMsg51128;
import net.snake.gamemodel.fight.bean.DayGongchengDateList;
import net.snake.gamemodel.fight.bean.GongchengDate;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.logic.PropertyChangeListener;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.gamemodel.line.persistence.SeverinfoManager;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.logic.SceneFactionCtMonster;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.serverenv.vline.VLineServer;

/**
 * 帮会攻城战逻辑处理管理
 * 
 * @author serv_dev
 */
public class MyFactionCityZhengDuoManager implements PropertyChangeListener {
	private static final Logger logger = Logger.getLogger(MyFactionCityZhengDuoManager.class);
	public static SceneFactionCtMonster monster;
	public static SceneFactionCtMonster youlongWeiBaMonster;

	private static long applyTime = 60 * 60 * 1000 * 24 * 12; // 开服多长时间后可以进行帮战申请
	public static int applyGongchengBangzhuLing = 1;
	public EffectInfo catchHuYoulongBuffer;// 帮战中游龙之刃持有buffer 降低抵抗力
	private Hero character;
	private long enterFactionCtSceneTime = 0; // 进入帮会攻城地图时间 0表示没有进入帮会攻城地图
	private long leijiInFactionCtTime = 0;

	/**
	 * 清楚攻城期间手握游龙之刃经验抵抗力衰减buffer
	 */
	public void clearcatchHuYoulongBuffer() {
		if (catchHuYoulongBuffer == null) {
			return;
		}
		try {
			character.getPropertyAdditionController().removeChangeListener(this);
			FightMsgSender.sendCancelSustainEffectMsg(character, catchHuYoulongBuffer);
			this.catchHuYoulongBuffer = null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 每十分钟获得一次声望 为持有游龙之刃的守城帮会成员增加20点城战声望； 为不持有游龙之刃的攻城帮会成员增加10点城战声望；
	 */
	public void gainShengWangByTenMinute() {
		if (isShouChengState()) {
			CharacterPropertyManager.changeGongChengShengWang(character, 20, false);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20146, 20));
		} else {
			CharacterPropertyManager.changeGongChengShengWang(character, 10, false);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20147, 10));
		}
	}

	/**
	 * 是否守城
	 * 
	 * @return
	 */
	public boolean isShouChengState() {
		if (!GongchengTsMap.isGongchenging) {
			return false;
		}
		if (MyFactionCityZhengDuoManager.monster == null) {
			return false;
		}
		int factionId = MyFactionCityZhengDuoManager.monster.getYoulongCharacter().getMyFactionManager().getFactionId();
		if (factionId != character.getMyFactionManager().getFactionId()) {
			return false;
		}
		return true;
	}

	/**
	 * 初始化攻城期间 玩家信息（包括攻城期间玩家掉线重新上线计时机制）
	 */
	public void init() {
		enterFactionCtSceneTime = 0;
		if (character.getMyFactionManager().isFaction()) {
			FactionCharacter fc = character.getMyFactionManager().getFactionController().getFactionCharacterByCharacterId(character.getId());
			if (fc != null) {
				leijiInFactionCtTime = fc.getFactionCityTime();
			}
		}
	}

	/**
	 * 攻城期间手握游龙之刃添加抵抗力衰减buffer
	 */
	public void addCatchHuYoulongBuffer() {
		if (catchHuYoulongBuffer != null) {
			return;
		}
		SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(BuffId.yourongzhirenChiyouBuffer);
		catchHuYoulongBuffer = new EffectInfo(se);
		createTeamPropertyChangerListener();
		character.getPropertyAdditionController().addChangeListener(this);
		FightMsgSender.broastSustainEffect(catchHuYoulongBuffer, character);
	}

	public MyFactionCityZhengDuoManager(Hero character) {
		this.character = character;
	}

	public int getonlineTime() {
		if (enterFactionCtSceneTime < 60000) {
			enterFactionCtSceneTime = System.currentTimeMillis();
		}
		long time = leijiInFactionCtTime + System.currentTimeMillis() - enterFactionCtSceneTime;
		if (time < 0) {
			time = 0;
		}
		return (int) (time / 60000);
	}

	public void setEnterFactionCtSceneTime() {
		this.enterFactionCtSceneTime = System.currentTimeMillis();
	}

	public long getEnterFactionCtSceneTime() {
		return enterFactionCtSceneTime;
	}

	public void setEnterFactionCtSceneTime(long enterFactionCtSceneTime) {
		this.enterFactionCtSceneTime = enterFactionCtSceneTime;
	}

	public void clearEnterFactionCtSceneTime() {
		if (enterFactionCtSceneTime < 60000) {
			return;
		}
		leijiInFactionCtTime = leijiInFactionCtTime + System.currentTimeMillis() - enterFactionCtSceneTime;
		if (character.getMyFactionManager().isFaction()) {
			FactionCharacter fc = character.getMyFactionManager().getFactionController().getFactionCharacterByCharacterId(character.getId());
			if (fc != null) {
				fc.saveFactionCityTime(leijiInFactionCtTime);
			}
		}
		this.enterFactionCtSceneTime = 0;
	}

	/**
	 * 进入帮战地图 信息初始化
	 */
	public void enterFactionCtSceneInfoInit() {
		setEnterFactionCtSceneTime();
	}

	/**
	 * 离开帮战地图 信息清除操作
	 */
	public void leaveFactionCtSceneClearInfo() {
		clearEnterFactionCtSceneTime();
		dropYoulongToScene(null);
		character.getCatchYoulongActionController().breakCatch();
	}

	private int count = 0;

	public void update() {
		if (enterFactionCtSceneTime == 0) {
			return;
		}
		if (monster == null && youlongWeiBaMonster == null) {
			return;
		}
		count++;
		if (count % 20 == 0) {
			character.sendMsg(new FactionCtMsg51128(this));
		}
	}

	/**
	 * 发送襄阳归属面板信息信息
	 */
	public void sendXiangyangChengzhuInfo() {
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		if (factionCity == null || factionCity.getFactionId() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14503));
			return;
		}
		character.sendMsg(new FactionCityMsg51104(factionCity, character));
	}

	/**
	 * 请求攻城日程查询
	 */
	public void sendGongchengInfo() {
		Collection<DayGongchengDateList> collection = GongchengDateManager.getInstance().getAllGongchengDateCollection();
		character.sendMsg(new FactionCityMsg51106(collection));
	}

	/**
	 * 提交攻城申请
	 */
	public void tijiaoGongchengInfo() {
		MyFactionManager myfactionManger = character.getMyFactionManager();
		if (!myfactionManger.isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11069));
			return;
		}
		if (!myfactionManger.getFactionController().isBangzhu(character.getId())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11069));
			return;
		}
		if (GongchengDateManager.getInstance().isHaveApplyGongcheng(myfactionManger.getFactionId())) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 11070));
			return;
		}
		FactionCity ct = FactionCityManager.getInstance().getFactionCity();
		int chengzhuFiD = ct.getFactionId();
		if (chengzhuFiD == myfactionManger.getFactionId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14541));
			return;
		}
		if (myfactionManger.getFactionController().getFaction().getBangzhulingCount() < applyGongchengBangzhuLing) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14555));
			return;
		}
		// if (myfactionManger.getFactionController().getFaction().getCopper() <
		// applyGongchengCopper) {
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14556));
		// return;
		// }
		if (System.currentTimeMillis() - SeverinfoManager.getInstance().getKaifuTime().getTime() < applyTime) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14557));
			return;
		}
		// myfactionManger.getFactionController().changeFactionCopper(
		// -applyGongchengCopper, character);
		myfactionManger.getFactionController().changeBazhuling(-applyGongchengBangzhuLing, character);
		GongchengDate gongchengDate = new GongchengDate(myfactionManger.getFactionId());
		GongchengDateManager.getInstance().addGongchengDate(gongchengDate);
		character.sendMsg(new FactionCityMsg51108());
	}

	/**
	 * 进入场景请求
	 */
	public void requestEnterGongchengScene() {
		int sceneId = FactionCityManager.gongchengMapId;
		if (character.getScene() == sceneId) {
			return;
		}
		if (!GongchengTsMap.isGongchenging) {
			DayGongchengDateList dayList = GongchengDateManager.getInstance().getFirsteGongchengDate();
			if (dayList == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14532));
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16002, ArithmeticUtils.DateToString(dayList.getGongchengDate())));
			}
			return;
		}
		int lineid = FactionCityManager.gongchengLine;
		VLineServer line = GameServer.vlineServerManager.getVLineServerByLineID(lineid);
		Scene scene = line.getSceneManager().getScene(sceneId);
		changeLineWhenEnterCanglongtan(line, scene);
	}

	/**
	 * 切线 进入苍龙惨
	 */
	public void changeLineWhenEnterCanglongtan(VLineServer line, final Scene scene) {
		ExchangeMapTrans.transWithChangeLine(character, line, scene, scene.getEnterX(), scene.getEnterY());
	}

	public boolean isFactionCityZhengduoState() {
		int sceneId = FactionCityManager.gongchengMapId;
		if (character.getSceneRef().getId() == sceneId && GongchengTsMap.isGongchenging) {
			return true;
		}
		return false;
	}

	/**
	 * 返回今天是否是攻城时间
	 * 
	 * @return
	 */
	public boolean isHaveTodayGongchengInfo() {
		DayGongchengDateList ddList = GongchengDateManager.getInstance().getTodayGongChengDateList();
		if (ddList == null) {
			return false;
		}
		int factionId = character.getMyFactionManager().getFactionId();
		FactionCity factionCity = FactionCityManager.getInstance().getFactionCity();
		if (factionCity != null && factionCity.getYdFactionId() == factionId) {
			return true;
		}
		return ddList.isHaveFaction(factionId);
	}

	/**
	 * 剑掉落场景
	 */
	public void dropYoulongToScene(Hero other) {
		if (monster == null) {
			return;
		}
		Hero youlonger = monster.getYoulongCharacter();
		int characterId = character.getId();
		if (characterId != youlonger.getId()) {
			return;
		}
		clearcatchHuYoulongBuffer();
		character.getMyFactionManager().takeOffYoulong();
		monster.dropYoulong(other);
	}

	/**
	 * 帮战结束 经验战场声望分配
	 * 
	 * @param addPrestige
	 * @param addExp
	 */
	public void sharaFactionCtShouyi(int addPrestige, int addExp) {
		int time = getonlineTime();
		if (character.getGrade() >= 60 && time >= 20) {
			try {
				CharacterPropertyManager.changePrestige(character, addPrestige);
				CharacterFormula.experienceProcess(character, addExp);
				if (addExp == GongchengTsMap.successExp) {
					character.getMyCharacterAchieveCountManger().getFactionCount().xiangyangchengzhuCount();
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		this.leijiInFactionCtTime = 0;
		this.enterFactionCtSceneTime = 0;
	}

	@Override
	public PropertyAdditionType getPropertyControllerType() {
		return PropertyAdditionType.shouhuyoulong;
	}

	@Override
	public PropertyEntity getPropertyEntity() {
		return pe;
	}

	private PropertyEntity pe;

	private void createTeamPropertyChangerListener() {
		PropertyEntity pe = new PropertyEntity();
		pe.addExtraProperty(Property.defence, catchHuYoulongBuffer.getPercent());
		this.pe = pe;
	}
}
