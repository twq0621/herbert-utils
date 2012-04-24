package net.snake.gamemodel.hero.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.snake.GameServer;
import net.snake.across.character.CatchXuanyuanJianActionController;
import net.snake.across.character.CharacterAcrossServerManager;
import net.snake.across.character.CharacterAcrossZhengzuoManager;
import net.snake.ai.IEyeShotManager;
import net.snake.ai.eyeshot.CharacterEyeShotManager;
import net.snake.ai.fight.bean.FighterVO;
import net.snake.ai.fight.controller.CharacterFightController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.ai.fight.response.CharacterDie20046;
import net.snake.ai.fight.response.CharacterShockEndMsg;
import net.snake.ai.fight.response.CharacterShockMsg;
import net.snake.ai.formula.CharacterFormula;
import net.snake.ai.move.CharacterMoveController;
import net.snake.ai.move.IMoveController;
import net.snake.ai.move.Location;
import net.snake.api.IHurtListener;
import net.snake.api.IShockListener;
import net.snake.commons.GenerateProbability;
import net.snake.commons.NetTool;
import net.snake.commons.VisibleObjectState;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SCharacterGoodController;
import net.snake.commons.script.SHorse;
import net.snake.commons.script.SRole;
import net.snake.consts.ArrowWay;
import net.snake.consts.BuffId;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.EffectType;
import net.snake.consts.GameConstant;
import net.snake.consts.HorseContainerType;
import net.snake.consts.Position;
import net.snake.consts.Symbol;
import net.snake.consts.TakeMethod;
import net.snake.consts.WugongType;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveCountManger;
import net.snake.gamemodel.achieve.logic.MyCharacterAchieveManger;
import net.snake.gamemodel.across.bean.AcrossLock;
import net.snake.gamemodel.across.logic.MyCharacterAcrossIncomeManager;
import net.snake.gamemodel.activities.persistence.CharacterActivitesTempInfoManager;
import net.snake.gamemodel.chat.bean.CharacterLimitConfig;
import net.snake.gamemodel.chat.logic.ChatManager;
import net.snake.gamemodel.chest.logic.MyChestManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.equipment.logic.CombineController;
import net.snake.gamemodel.equipment.logic.EquipmentController;
import net.snake.gamemodel.fabao.response.RideFabaoResponse;
import net.snake.gamemodel.faction.logic.MyFactionManager;
import net.snake.gamemodel.faction.persistence.MyFactionCityZhengDuoManager;
import net.snake.gamemodel.fight.logic.MyCharacterVehicleManager;
import net.snake.gamemodel.fight.logic.SuggestManager;
import net.snake.gamemodel.friend.logic.MyFriendManager;
import net.snake.gamemodel.gift.logic.MyCharacterGiftpacksManger;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.CharacterGoodControllerImp;
import net.snake.gamemodel.goods.logic.CharacterGoodsDCController;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.guide.logic.MyCharacterMsgManager;
import net.snake.gamemodel.guide.logic.MyNewGuideManager;
import net.snake.gamemodel.hero.logic.AddPointManager;
import net.snake.gamemodel.hero.logic.AntiAddictedSystem;
import net.snake.gamemodel.hero.logic.AsynchronousTask;
import net.snake.gamemodel.hero.logic.CharacterCountController;
import net.snake.gamemodel.hero.logic.CharacterDownLineManager;
import net.snake.gamemodel.hero.logic.CharacterPropertyAdditionControllerImp;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.logic.DayInComeController;
import net.snake.gamemodel.hero.logic.MyPersonalsManager;
import net.snake.gamemodel.hero.logic.MyWorshipcontemptManager;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.heroext.biguandazuo.logic.MyBiguanManager;
import net.snake.gamemodel.heroext.biguandazuo.logic.MyDazuoAndUnDaduoManager;
import net.snake.gamemodel.heroext.channel.logic.MyChannelManager;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.heroext.dantian.persistence.DanTianController;
import net.snake.gamemodel.heroext.lianti.logic.CharacterLianTiController;
import net.snake.gamemodel.heroext.wudao.persistence.DGWDController;
import net.snake.gamemodel.instance.logic.MyCharacterInstanceManager;
import net.snake.gamemodel.instance.logic.MyFubenTimeRecordManager;
import net.snake.gamemodel.instance.logic.MyInstanceDayStatManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.response.hero.CharacterEnterEyeShot10028;
import net.snake.gamemodel.map.response.hero.CharacterInstantMove10120;
import net.snake.gamemodel.map.response.hero.CharacterLeaveEyeShot10080;
import net.snake.gamemodel.map.response.hero.RankingTitleChange10506;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneBangqiMonster;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.catchpet.CatchHorseActionController;
import net.snake.gamemodel.pet.catchpet.CatchYoulongActionController;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.pet.response.HorseListResponse60018;
import net.snake.gamemodel.quickbar.logic.QuickBarController;
import net.snake.gamemodel.shop.logic.MyShopManger;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bow.logic.BowController;
import net.snake.gamemodel.skill.logic.BaseSkillManager;
import net.snake.gamemodel.skill.logic.CharacterContinuumKillSys;
import net.snake.gamemodel.skill.logic.CharacterHiddenWeaponController;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.logic.CharacterSkillManager;
import net.snake.gamemodel.skill.logic.ShenTongSkillManager;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.task.logic.CharacterTaskController;
import net.snake.gamemodel.team.logic.MyCharacterTeamfightingManager;
import net.snake.gamemodel.team.logic.MyTeamManager;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.gamemodel.trade.logic.MyTradeController;
import net.snake.gamemodel.vip.logic.MyCharacterVipManger;
import net.snake.gamemodel.wedding.logic.MyCharacterWeddingringManager;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.gamemodel.wedding.response.wedfeast.WedFeastMessageResponse52248;
import net.snake.ibatis.IbatisEntity;
import net.snake.netio.message.ResponseMsg;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.stall.OnlineStallManagerImp;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 人物角色
 * 
 * @author serv_dev
 */

final public class Hero extends VisibleObject implements IbatisEntity, SRole, Updatable {
	private static Logger logger = Logger.getLogger(Hero.class);
	private static long KeyTime = 5L * 60 * 60 * 1000;

	private final static int[] heedSceneObject = { SceneObjType_Hero, SceneObjType_MonsterScene, SceneObjType_MonsterBangqi, SceneObjType_MonsterFactionCt, SceneObjType_Horse,
			SceneObjType_DropedGood, SceneObjType_MonsterFeast, SceneObjType_MonsterXuanyuan };

	/** 角色所在的虚拟分线服务器 */
	private VLineServer vlineserver;
	private Account account;
	private boolean isFristEnterMap = true;
	/** 最后一次心跳的时间 */
	public volatile long lastHeatBeat = System.currentTimeMillis();
	/*** 对此用户禁用心跳检测 */
	private boolean enableHeatBeat = false;
	private boolean dieByRole = false;
	// 数据库映射字段==============================================
	/** 原始分区id 合服用 **/
	private int originalSid;//
	/** 真气 */
	private int zhenqi;// ---真元
	/** 进攻加点 */
	private int attackAddpoint;//
	/** 防御加点 */
	private int defenceAddpoint;//
	/** 轻身（改为身法）加点 */
	private int lightAddpoint;//
	/** 健体(改为爆发) 加点 */
	private int strongAddpoint;//
	@Deprecated
	/**战场声望*/
	private int prestige;//

	/** pkmodel 0和平模式 */
	private int pkModel;//
	/** 角色创建时的IP */
	private String createip;//
	private String lastip;//

	/** 人物普通攻击的技能id */
	private int skillid;//
	/** 在线时间，指该角色的总在线时间(毫秒) */
	private long onlinedate;//
	/** 可以开始说话的时间 */
	private Date allowchatStarttime;//
	/** 角色创建时间 */
	private Date createtime;//
	/** 最后登入时间 */
	private Date lastLogindate;//
	/** 最后退出时间 */
	private Date lastdate;//
	private int leijiGainLijin;
	// private Integer lineId;
	/** 最后一次骑乘的坐骑 */
	private Integer lastRidehorse;//

	// 铜币是游戏产生的 和交子,元宝没有换算关系 元宝是人民币充值产生的 交子是活动赠送的<br>
	// 但也可以当元宝使用
	/** 铜币 */
	private int copper;//
	/** 礼金 */
	private int jiaozi;//
	/** 当前的经验值 */
	private long nowExperience;//
	/** 下次升级所需要的经验值 */
	private long nextExperience;//
	/** 姓名 */
	private String name;//
	/** 性别 */
	private byte sex;//
	/** 人物头像 */
	private byte headimg;//
	/** 账户id */
	private int accountId;//
	/**
	 * 职业 0 - 无, 人男（道士1),妖男(战士2),人女（乐师3),妖女（法师4)
	 * */
	private int popsinger;//
	/** 玩家当前显示称谓Id */
	private Integer nowAppellationid;//
	/** 人物包裹的最大索引下标 */
	private short maxBagAmount = 30;//
	/***/
	private short maxStorageAmount = 30;
	/** 是否封号0不封号 1标识封号 禁止登入 */
	private byte iscloseCharacter;//
	/** 是否禁止聊天 0允许，1不允许 */
	private byte isallowChat;//
	/** 是否在线，0不在线，1在线 */
	private byte isonline;//
	/** 潜能(加点用。加完了点数为0) */
	private int potential;//
	/** 人物穴位个数 */
	private int channelXuewei; //
	/** 人物学习的经脉id，多的用"，"区分 */
	private String channelJingmai;//
	/** 人物学习的经脉id，多的用"，"区分 */
	private String channelRealdragon;//
	/** 最大可携带坐骑数量 */
	private int maxHorseAmount = 5;//
	/** 武学境界 */
	private int wuxueJingjie;//
	/** 人物鄙视 */
	private int contemptcount; //
	/** 人物崇拜 */
	private int worshipcount;//
	/** 仓库里存的铜钱数量 */
	private int storageCopper;//
	/** 仓库里允许马的最大值 */
	private int maxStorageHorseAmount;//
	/** 连斩数 */
	private int maxContinueKillcount;//
	/** 击杀BOSS数 */
	private int bossKill;//
	/** 摊位名 */
	private String stallName;//
	/** 角色当前心情 */
	private String nowXingqing;//
	/** 今日表情 */
	private String nowBiaoqing;//
	/** 收到赠送的花的数量 */
	private Integer flowerCount;//
	/** 0正常状态/1打坐状态 */
	private Short state;//
	/** 闭关开始时间 */
	private Date biguanDate;//
	/** 0未摆摊 1摆摊 */
	private int stallStatus;//
	/***/
	private Integer weekLoginCount;
	/** 新手引导用 */
	private Integer dropGood;//
	/***/
	private Integer maxJumpCount;
	private Integer dayOnline;
	private Integer dazuoSkill;
	/** 充气次数 */
	private int chongqiRecord;//
	/** 杀死挂机中玩家的次数 */
	private int devilcnt;//
	/***/
	private Integer chengjiuPoint;
	/** 人物经脉被动经验加成次数 */
	private Integer channelBeidongExp;//
	/***/
	private Integer contribution;
	/** 技能突破计时开始时间 */
	private Date skillpoBeginTime;//
	/** 技能突破冷却时间 **/
	private int skillpoTime;//
	/** 跳过新手导航 */
	private String skipNoviceNavigation;//
	/** 账号的原始ID */
	private Integer accountInitiallyId;//
	/** 原始的角色ID */
	private Integer characterInitiallyId; //
	private int dantiangrade;
	/** 城战声望 */
	private int chengzhanShengWang; //
	/** 论剑台声望 */
	private int lunjianShengWang;//
	/** 当日获得城战声望 */
	private int todayChengzhanShengwang;//
	/** 今天疗伤获得经验 */
	private long todayLiaoshangExp;//
	/** 今天开始疗伤时间 */
	private Date todayLiaoshangTime;//
	// 数据库映射字段结束==============================================
	public boolean isWudiBuffer = false;
	private boolean isWedfeast = false;
	private int[] rankingTitle;//
	private final CharacterDieData diedata = new CharacterDieData();
	/** 玩家状态 */
	private int status = net.snake.commons.VisibleObjectState.Idle;
	// private Status status = Status.idle; //

	/** 在线的累计时间 **/
	private Integer limitOnlineTime;
	/** 角色的网络连接 */
	private volatile GamePlayer player = null;//
	private final AcrossLock acrossLock = new AcrossLock();
	/** 人物经脉管理器 **/
	private final MyChannelManager myChannelManager = new MyChannelManager(this);
	/** 装备管理器 **/
	private final EquipmentController equipmentController = new EquipmentController(this);
	private final CharacterActivitesTempInfoManager characterActivitesTempInfoManager = new CharacterActivitesTempInfoManager();
	private final MyFactionCityZhengDuoManager myFactionCityZhengDuoManager = new MyFactionCityZhengDuoManager(this);
	private final MyShopManger myShopManger = new MyShopManger(this);
	
	/** 轮询方式实现人走路方法类 **/
	private final IMoveController movecontroller = new CharacterMoveController(this);
	/** 交易管理者 **/
	private final IMyTradeController myTradeController = new MyTradeController(this);
	/** 人物物品管理者 **/
	private final CharacterGoodController characterGoodController = new CharacterGoodControllerImp(this);
	/** 防沉迷 **/
	private final AntiAddictedSystem antiAddictedSystem = new AntiAddictedSystem(this);
	// ==========================================
	/** 任务管理器 **/
	private final CharacterTaskController taskController = new CharacterTaskController(this);
	/** 组队 管理器 **/
	private final MyTeamManager myTeamManager = new MyTeamManager(this);
	/** 角色与其他角色关系管理器 好友 仇人 黑名单 最近联系人 **/
	private final MyFriendManager myFriendManager = new MyFriendManager(this);
	/** 离婚后纪念玉佩领取 **/
	private final MyCharacterWeddingringManager myCharacterWeddingringManager = new MyCharacterWeddingringManager(this);
	/** 帮会管理器 **/
	private final MyFactionManager myFactionManager = new MyFactionManager(this);
	/** 新手引导管理器 **/
	private final MyNewGuideManager myNewGuideManager = new MyNewGuideManager(this);
	/** 连斩系统管理器 **/
	private final CharacterContinuumKillSys characterContinuumKillSys = new CharacterContinuumKillSys(this);
	/** 玩家阵法管理器 **/
	private final MyCharacterTeamfightingManager myZhenfaManager = new MyCharacterTeamfightingManager(this);
	private final MyFubenTimeRecordManager myFubenTimeRecordManager = new MyFubenTimeRecordManager(this);
	private final MyCharacterVehicleManager myCharacterVehicleManager = new MyCharacterVehicleManager(this);
	/** 玩家上线提示消息管理器 **/
	private final MyCharacterMsgManager myCharacterMsgManager = new MyCharacterMsgManager(this);
	/** 打坐 **/
	private final MyDazuoAndUnDaduoManager myDazuoAndUnDaduoManager = new MyDazuoAndUnDaduoManager(this);

	private final MyCharacterGiftpacksManger myCharacterGiftpacksManger = new MyCharacterGiftpacksManger(this);
	private final CatchYoulongActionController catchYoulongActionController = new CatchYoulongActionController(this);
	/** 技能管理器 **/
	private final CharacterSkillManager characterSkillManager = new CharacterSkillManager(this);
	private final MyBiguanManager myBiguanManager = new MyBiguanManager(this);
	/** 玩家的异步任务列表 **/
	private final AsynchronousTask asynchronousTask = new AsynchronousTask(this);
	/** 玩家的战斗控制 **/
	private final CharacterFightController fightController = new CharacterFightController(this);

	/*** 所有坐骑管理器 ***/
	private final CharacterHorseController characterHorseController = new CharacterHorseController(this);

	/** 坐骑捕获动作管理器 **/
	private final CatchHorseActionController catchHorseActionController = new CatchHorseActionController(this);
	/** 崇拜、鄙视管理 **/
	private final MyWorshipcontemptManager myWorshipcontemptManager = new MyWorshipcontemptManager(this);
	/** 交友管理 **/
	private final MyPersonalsManager myPersonalsManager = new MyPersonalsManager(this);
	private final MyCharacterAchieveManger myCharacterAchieveManger = new MyCharacterAchieveManger(this);
	private final MyCharacterAchieveCountManger myCharacterAchieveCountManger = new MyCharacterAchieveCountManger(this);
	// ===================================================
	/** 合成管理器 **/
	private final CombineController combineController = new CombineController(this);
	private final QuickBarController quickbarController = new QuickBarController(this);
	// 用于难度选择的临时副本

	/** 自动挂机管理 **/
	private final CharacterOnHoorController characterOnHoorController = new CharacterOnHoorController(this);

	private final IEyeShotManager eyeshot = new CharacterEyeShotManager(this);
	private final FuhuoTimer fuhuotimer = new FuhuoTimer(this);
	private final CharacterDownLineManager downLineManager = new CharacterDownLineManager(this);
	private final CharacterLimitConfig characterLimitConfig = new CharacterLimitConfig();
	private final AddPointManager addPointManager = new AddPointManager(this);
	/** 日收益管理 **/
	private final DayInComeController dayInCome = new DayInComeController(this);
	private final MyInstanceDayStatManager myInstanceDayStatManager = new MyInstanceDayStatManager(this);
	private final MyCharacterInstanceManager myCharacterInstanceManager = new MyCharacterInstanceManager(this);
	private final MyCharacterVipManger myCharacterVipManger = new MyCharacterVipManger(this);
	/** 每15分钟往数据库保存一次 */
	private final SafeTimer characterDbUpdateTimer = new SafeTimer(15 * 60 * 1000);//

	private final CharacterGoodsDCController characterGoodsDCController = new CharacterGoodsDCController(this);

	private final CharacterHiddenWeaponController characterHiddenWeaponController = new CharacterHiddenWeaponController(this);
	private final CharacterCountController characterCountController = new CharacterCountController(this);
	/** 聊天管理器 */
	private final ChatManager chatmanager = new ChatManager(this);
	// 角色图片管理器
	// private final MyCharacterPhotoManager myCharacterPhotoManager = new
	// MyCharacterPhotoManager(this);
	private final SuggestManager suggestManager = new SuggestManager(this);
	//
	/** 人物宝箱信息管理 */
	private final MyChestManager myChestManager = new MyChestManager(this);
	private final CharacterLianTiController liantiController = new CharacterLianTiController(this);
	private final BowController bowController = new BowController(this);
	private final DanTianController dantianController = new DanTianController(this);
	private final CharacterAcrossServerManager characterAcrossServerManager = new CharacterAcrossServerManager(this);
	private final MyCharacterAcrossIncomeManager myCharacterAcrossIncomeManager = new MyCharacterAcrossIncomeManager(this);
	private final CharacterAcrossZhengzuoManager mycharacterAcrossZhengzuoManager = new CharacterAcrossZhengzuoManager(this);
	private final CatchXuanyuanJianActionController catchXuanyuanJianActionController = new CatchXuanyuanJianActionController(this);
	private final DGWDController dgwdController = new DGWDController(this);
	private final DujieCtrlor dujieCtrlor = new DujieCtrlor(this);
	public int[] worldPos = new int[] { 0, 0, 0 };

	private long changeHeadTime = 0l;

	private long fuhuaCdtime;
	private long fuhuaStarttime;
	private int fuhuaNeidanId;// 孵化中的内丹id，，-1表示没有在孵化
	private byte isfuhua = -1;// 是否在孵化中，-1表示没有在孵化，0是在孵化的冷却中，1表示在孵化中
	private String fuhuaCount = "";// 孵化次数累计,格式：内丹id,次数;内丹id,次数;
	private Map<Integer, Integer> fuhuaCountMap = new HashMap<Integer, Integer>();
	private CharacterHorse fuhuaCharacterHorse;// 记录孵化成功后产生的灵宠
	private int killNum;// 强制杀死的怪物数量
	private ShenTongSkillManager shenTongSkillManager = new ShenTongSkillManager(this);

	public long getChangeHeadTime() {
		return changeHeadTime;
	}

	public void setChangeHeadTime(long changeHeadTime) {
		this.changeHeadTime = changeHeadTime;
	}

	public int getDevilcnt() {
		return devilcnt;
	}

	public void setDevilcnt(int devilcnt) {
		this.devilcnt = devilcnt;
	}

	public String getSkipNoviceNavigation() {
		return skipNoviceNavigation;
	}

	public void setSkipNoviceNavigation(String skipNoviceNavigation) {
		this.skipNoviceNavigation = skipNoviceNavigation;
	}

	public MyCharacterAcrossIncomeManager getMyCharacterAcrossIncomeManager() {
		return myCharacterAcrossIncomeManager;
	}

	public CatchXuanyuanJianActionController getCatchXuanyuanJianActionController() {
		return catchXuanyuanJianActionController;
	}

	public AcrossLock getAcrossLock() {
		return acrossLock;
	}

	public CharacterAcrossZhengzuoManager getMycharacterAcrossZhengzuoManager() {
		return mycharacterAcrossZhengzuoManager;
	}

	public CharacterAcrossServerManager getCharacterAcrossServerManager() {
		return characterAcrossServerManager;
	}

	public DGWDController getDgwdController() {
		return dgwdController;
	}

	public Integer getLimitOnlineTime() {
		if (this.limitOnlineTime == null) {
			this.limitOnlineTime = 0;
		}
		return limitOnlineTime;
	}

	public void setLimitOnlineTime(Integer limitOnlineTime) {
		this.limitOnlineTime = limitOnlineTime;
	}

	public boolean isEnableHeatBeat() {
		return enableHeatBeat;
	}

	public boolean isDieByRole() {
		return dieByRole;
	}

	public void setDieByRole(boolean dieByRole) {
		this.dieByRole = dieByRole;
	}

	public void setEnableHeatBeat(boolean enableHeatBeat) {
		this.enableHeatBeat = enableHeatBeat;
	}

	public Date getSkillpoBeginTime() {
		return skillpoBeginTime;
	}

	public void setSkillpoBeginTime(Date skillpoBeginTime) {
		this.skillpoBeginTime = skillpoBeginTime;
	}

	public int getSkillpoTime() {
		return skillpoTime;
	}

	public MyShopManger getMyShopManger() {
		return myShopManger;
	}

	public void setSkillpoTime(int skillpoTime) {
		this.skillpoTime = skillpoTime;
	}

	public Integer getChannelBeidongExp() {
		return channelBeidongExp;
	}

	public void setChannelBeidongExp(Integer channelBeidongExp) {
		this.channelBeidongExp = channelBeidongExp;
	}

	public String getChannelRealdragon() {
		return channelRealdragon;
	}

	public void setChannelRealdragon(String channelRealdragon) {
		this.channelRealdragon = channelRealdragon;
	}

	public int getLeijiGainLijin() {
		return leijiGainLijin;
	}

	public void setLeijiGainLijin(int leijiGainLijin) {
		this.leijiGainLijin = leijiGainLijin;
	}

	public CharacterActivitesTempInfoManager getCharacterActivitesTempInfoManager() {
		return characterActivitesTempInfoManager;
	}

	public CharacterLianTiController getLiantiController() {
		return liantiController;
	}

	public BowController getBowController() {
		return bowController;
	}

	public DanTianController getDanTianController() {
		return dantianController;
	}

	// 销毁角色数据，防止产生垃圾
	public void destory() {
		super.destroy();
		myChestManager.destroy();
		quickbarController.destory();
		characterGoodsDCController.destroy();
		movecontroller.destory();
		myShopManger.destroy();
		equipmentController.destroy();
		myTeamManager.destroy();
		taskController.destroy();
		myFriendManager.destroy();
		myNewGuideManager.destory();
		myZhenfaManager.destory();
		myCharacterMsgManager.destory();
		myDazuoAndUnDaduoManager.destory();
		myCharacterGiftpacksManger.destory();
		// chatLabaSpeakManager.destory();
		characterSkillManager.destroy();
		myBiguanManager.destroy();
		asynchronousTask.destory();
		fightController.destroy();
		characterHorseController.destory();
		myWorshipcontemptManager.destory();
		myPersonalsManager.destory();
		myCharacterAchieveManger.destory();
		myCharacterAchieveCountManger.destroy();
		characterOnHoorController.destroy();
		myInstanceDayStatManager.destroy();
		myCharacterInstanceManager.destroy();
		myCharacterVipManger.destroy();
		characterCountController.destroy();
		characterGoodController.destory();
		diedata.clear();
		rankingTitle = null;
		characterActivitesTempInfoManager.destory();
		myFactionManager.destory();
		myFubenTimeRecordManager.destroy();
		myCharacterVehicleManager.destory();
		catchYoulongActionController.destory();
		catchHorseActionController.destory();
		dantianController.destroy();
		characterContinuumKillSys.destroy();
	}

	// public MyCharacterPhotoManager getMyCharacterPhotoManager() {
	// return myCharacterPhotoManager;
	// }
	public CharacterCountController getCharacterCountController() {
		return characterCountController;
	}

	public MyCharacterWeddingringManager getMyCharacterWeddingringManager() {
		return myCharacterWeddingringManager;
	}

	public CharacterHiddenWeaponController getCharacterHiddenWeaponController() {
		return characterHiddenWeaponController;
	}

	public MyFactionCityZhengDuoManager getMyFactionCityZhengDuoManager() {

		return myFactionCityZhengDuoManager;
	}

	public MyInstanceDayStatManager getMyInstanceDayStatManager() {

		return myInstanceDayStatManager;
	}

	public MyCharacterInstanceManager getMyCharacterInstanceManager() {

		return myCharacterInstanceManager;
	}

	public CatchYoulongActionController getCatchYoulongActionController() {
		return catchYoulongActionController;
	}

	public MyCharacterVehicleManager getMyCharacterVehicleManager() {
		return myCharacterVehicleManager;
	}

	public DayInComeController getDayInCome() {
		return dayInCome;
	}

	public AddPointManager getAddPointManager() {
		return addPointManager;
	}

	public void changeRankingTitle(int[] rankingTitle) {
		this.rankingTitle = rankingTitle;
		getEyeShotManager().sendMsg(new RankingTitleChange10506(this));
	}

	public int[] getRankingTitle() {
		return rankingTitle;
	}

	public CharacterLimitConfig getCharacterLimitConfig() {
		return characterLimitConfig;
	}

	public MyCharacterAchieveManger getMyCharacterAchieveManger() {

		return myCharacterAchieveManger;
	}

	public MyCharacterAchieveCountManger getMyCharacterAchieveCountManger() {

		return myCharacterAchieveCountManger;
	}

	public CharacterDownLineManager getDownLineManager() {
		return downLineManager;
	}

	public MyChestManager getMyChestManager() {

		return myChestManager;
	}

	public CharacterDieData getDiedata() {
		return diedata;
	}

	public Hero() {
	}

	public void setStallStatus(int stallStatus) {
		this.stallStatus = stallStatus;
	}

	public boolean isWedfeast() {
		return isWedfeast;
	}

	public void setWedfeast(boolean isWedfeast) {
		this.isWedfeast = isWedfeast;
	}

	public int getStallStatus() {
		return stallStatus;
	}

	public MyCharacterVipManger getMyCharacterVipManger() {
		return myCharacterVipManger;
	}

	/**
	 * 获取摆摊 婚姻状态 婚姻优先 0无 1婚姻 2摆摊
	 * 
	 * @return
	 */
	public int getCharacterStatus() {
		if (this.isWedfeast) {
			return 1;
		}
		if (stallStatus == 1) {
			return 2;
		}
		return stallStatus;
	}

	public QuickBarController getQuickbarController() {
		return quickbarController;
	}

	public SuggestManager getSuggestManager() {
		return suggestManager;
	}

	public ChatManager getChatManager() {
		return chatmanager;
	}

	@Override
	public void setTarget(VisibleObject target) {
		if (target != null) {
			if (target.getId() == getId())
				return;
		}
		super.setTarget(target);
	}

	/**
	 * 初始化角色数据
	 */
	public void initCharacterData() {
		// 说明角色各模块的初始化顺序存在一定的依赖性
		// 所有的属性加成开始时,可能会更改这个值,所以,先缓存起来
		// 主角的技能初始化必须先于坐骑的初始化(必须在其他模块初始化之前初始化)
		propertyController = new CharacterPropertyAdditionControllerImp(this);
		getCharacterAcrossServerManager().init();
		getMyCharacterAchieveManger().initData();
		int dbNowhp = getNowHp();
		int dbNowmp = getNowMp();
		int dbNowsp = getNowSp();
		getPropertyAdditionController().addChangeListener(addPointManager);
		getPropertyAdditionController().recompute();
		getCharacterGoodController().initData();
		// 要保证顺序的
		getMyCharacterAchieveCountManger().init();
		// wutao getLiantiController().init();
		getCharacterGoodController().getBodyGoodsContiner().initEquipPropertiesToCharacter();
		getMyChannelManager().initData();
		getMyChannelManager().initDataZhenLong();
		// wutao getDanTianController().init();
		// wutao getDgwdController().init();
		getCharacterSkillManager().initData();
		List<Skill> skills = SkillManager.getInstance().getCanLearnSkills(this);
		int size = skills.size();
		for (int i = 0; i < size; i++) {
			this.getCharacterSkillManager().learnSkill(skills.get(i));
		}
		getCharacterHorseController().initData();
		// getNPCFriendController().initDate();
		// 战斗管理器加载在人物技能与坐骑都初始化之后加载
		// 战斗管理器加载在人物技能与坐骑都初始化之后加载
		getFightController().initData();
		// 初始化装备属性 把身上的属性初始化到人身上
		// 必须后于坐骑初始化
		// 挂机的初始化 优先于effectController的加载
		getCharacterOnHoorController().initData();
		getTaskController().init();
		getMyFriendManager().init();
		getMyFactionManager().init();
		getMyCharacterVehicleManager().init();
		// 玩家阵营信息初始化
		// getMyCharacterCampManager().init();
		// C初始化组队阵法
		getMyZhenfaManager().init();
		// 玩家提示消息初始化
		// getMyCharacterMsgManager().init();
		myFubenTimeRecordManager.init();

		// 初始化人物崇拜鄙视数据
		getMyWorshipcontemptManager().initData();
		// 初始化交友信息
		// getMyPersonalsManager().initAddPersonals();
		// 初始化人物上传图片数据
		// getMyCharacterPhotoManager().init();
		getMyDazuoManager().init();
		// 初始化人物宝箱
		getMyChestManager().initData();
		// 初始化人物宝箱统计
		try {
			getMyChestManager().initChestCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// if(LOGGER.isDebugEnabled()){
		// LOGGER.debug("人物上传令牌!!!character{"+getMyCharacterPhotoManager().getLingPai()+"}");
		// }
		getEffectController().init();
		myCharacterWeddingringManager.init();
		// 初始化人物最后一次退出时间和本次的登录时间
		myInstanceDayStatManager.init();
		long nowtime = System.currentTimeMillis();
		setLastLogindate(new Date());
		setIsonline(CommonUseNumber.byte1);
		if (nowtime - getLastLogindate().getTime() >= KeyTime) {
			setOnlinedate(0L);
		}
		getMyNewcomeManager().init();
		getMyCharacterGiftpacksManger().init();
		// getChatLabaSpeakManager().init();
		getMyCharacterVipManger().init();
		// 初始化是否正在摆摊
		OnlineStallManagerImp.getInstance().updateStallInfo(this);
		getDayInCome().init();
		// bowController.init();
		// 属性
		setNowHp(Math.min(propertyController.getExtraMaxHp(), dbNowhp));
		setNowMp(Math.min(propertyController.getExtraMaxMp(), dbNowmp));
		setNowSp(Math.min(propertyController.getExtraMaxSp(), dbNowsp));
		rankingTitle = RankingManager.getInstance().getRankingTitle(getId());
		//
		characterGoodsDCController.initData();
		getCharacterCountController().initData();
		characterContinuumKillSys.initial();
		dujieCtrlor.init();

		// 初始化孵化灵宠的次数
		initFuhuaCount();
	}

	private void initFuhuaCount() {
		String fuhuacntStr = this.getFuhuaCount();
		if (fuhuacntStr == null || "".equals(fuhuacntStr)) {
			return;
		}
		String fuhuacntStrs[] = fuhuacntStr.split(Symbol.FENHAO);
		for (String cntStr : fuhuacntStrs) {
			String cntStrs[] = cntStr.split(Symbol.DOUHAO);
			if (cntStrs.length != 2) {
				continue;
			}
			fuhuaCountMap.put(Integer.parseInt(cntStrs[0]), Integer.parseInt(cntStrs[1]));
		}
	}

	public MyFubenTimeRecordManager getMyFubenTimeRecordManager() {
		return myFubenTimeRecordManager;
	}

	public CharacterGoodsDCController getCharacterGoodsDCController() {
		return characterGoodsDCController;
	}

	// 是否在摆摊
	public boolean isInStall() {
		return characterGoodController.getStallGoodsContainer().getGoodsCount() > 0 || characterHorseController.getHorseContainer(HorseContainerType.onStall).getHorseCount() > 0;
	}

	public MyDazuoAndUnDaduoManager getMyDazuoManager() {
		return myDazuoAndUnDaduoManager;
	}

	public int getMaxContinueKillcount() {
		return maxContinueKillcount;
	}

	public MyPersonalsManager getMyPersonalsManager() {
		return myPersonalsManager;
	}

	public void setMaxContinueKillcount(int maxContinueKillcount) {
		this.maxContinueKillcount = maxContinueKillcount;
	}

	public MyWorshipcontemptManager getMyWorshipcontemptManager() {
		return myWorshipcontemptManager;
	}

	public int getContemptcount() {
		return contemptcount;
	}

	public void setContemptcount(int contemptcount) {
		this.contemptcount = contemptcount;
	}

	public int getWorshipcount() {
		return worshipcount;
	}

	public void setWorshipcount(int worshipcount) {
		this.worshipcount = worshipcount;
	}

	public MyCharacterTeamfightingManager getMyZhenfaManager() {
		return myZhenfaManager;
	}

	public CharacterContinuumKillSys getContinuumKillSys() {
		return characterContinuumKillSys;
	}

	public MyChannelManager getMyChannelManager() {
		return myChannelManager;
	}

	public int getChannelXuewei() {
		return channelXuewei;
	}

	public void setChannelXuewei(int channelXuewei) {
		this.channelXuewei = channelXuewei;
	}

	public String getChannelJingmai() {
		return channelJingmai;
	}

	public void setChannelJingmai(String channelJingmai) {
		this.channelJingmai = channelJingmai;
	}

	public CatchHorseActionController getCatchHorseActionController() {
		return catchHorseActionController;
	}

	public CharacterHorseController getCharacterHorseController() {
		return characterHorseController;
	}

	@Override
	public CharacterFightController getFightController() {
		return fightController;
	}

	public MyCharacterGiftpacksManger getMyCharacterGiftpacksManger() {
		return myCharacterGiftpacksManger;
	}

	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	/**
	 * 玩家下线更新
	 * 
	 * @return
	 */
	public Date getLastdate() {
		if (lastdate == null) {
			lastdate = new Date();
		}
		return lastdate;
	}

	public int getOriginalSid() {
		return originalSid;
	}

	public void setOriginalSid(int originalSid) {
		this.originalSid = originalSid;
	}

	public int getZhenqi() {
		return zhenqi;
	}

	public void setZhenqi(int zhenqi) {
		if (zhenqi < 0)
			this.zhenqi = 0;
		this.zhenqi = zhenqi;
	}

	public int getAttackAddpoint() {
		return attackAddpoint;
	}

	public void setAttackAddpoint(int attackAddpoint) {
		this.attackAddpoint = attackAddpoint;
	}

	public int getDefenceAddpoint() {
		return defenceAddpoint;
	}

	public void setDefenceAddpoint(int defenceAddpoint) {
		this.defenceAddpoint = defenceAddpoint;
	}

	/** 轻身（改为身法）加点 */
	public int getLightAddpoint() {
		return lightAddpoint;
	}

	/** 轻身（改为身法）加点 */
	public void setLightAddpoint(int lightAddpoint) {
		this.lightAddpoint = lightAddpoint;
	}

	/** 健体(改为爆发) 加点 */
	public int getStrongAddpoint() {
		return strongAddpoint;
	}

	/** 健体(改为爆发) 加点 */
	public void setStrongAddpoint(int strongAddpoint) {
		this.strongAddpoint = strongAddpoint;
	}

	public int getPrestige() {
		return prestige;
	}

	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	public String getCreateip() {
		return createip;
	}

	public void setCreateip(String createip) {
		this.createip = createip;
	}

	public String getLastip() {
		return lastip;
	}

	public void setLastip(String lastip) {
		this.lastip = lastip;
	}

	public int getSkillid() {
		return skillid;
	}

	public void setSkillid(int skillid) {
		this.skillid = skillid;
	}

	public long getOnlinedate() {
		return onlinedate;
	}

	public void setOnlinedate(long onlinedate) {
		this.onlinedate = onlinedate;
	}

	public Date getAllowchatStarttime() {
		return allowchatStarttime;
	}

	public void setAllowchatStarttime(Date allowchatStarttime) {
		this.allowchatStarttime = allowchatStarttime;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 登入时间
	 * 
	 * @return
	 */
	public Date getLastLogindate() {
		return lastLogindate;
	}

	public void setLastLogindate(Date lastLogindate) {
		this.lastLogindate = lastLogindate;
	}

	public int getPotential() {
		return potential;
	}

	public void setPotential(int potential) {
		this.potential = potential;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getBiguanDate() {
		if (biguanDate == null) {
			this.biguanDate = new Date();
		}
		return biguanDate;
	}

	public void setBiguanDate(Date biguanDate) {
		this.biguanDate = biguanDate;
	}

	public void setJiaozi(int jiaozi) {
		this.jiaozi = jiaozi;
	}

	public void setNextExperience(long nextExperience) {
		this.nextExperience = nextExperience;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public byte getIscloseCharacter() {
		return iscloseCharacter;
	}

	@Override
	public byte getIsonline() {
		return isonline;
	}

	public void setIsonline(byte isonline) {
		this.isonline = isonline;
	}

	public void setIscloseCharacter(byte iscloseCharacter) {
		this.iscloseCharacter = iscloseCharacter;
	}

	public byte getIsallowChat() {
		return isallowChat;
	}

	public void setIsallowChat(byte isallowChat) {
		this.isallowChat = isallowChat;
	}

	public MyFriendManager getMyFriendManager() {
		return myFriendManager;
	}

	public MyTeamManager getMyTeamManager() {
		return myTeamManager;
	}

	public MyFactionManager getMyFactionManager() {

		return myFactionManager;
	}

	public MyCharacterMsgManager getMyCharacterMsgManager() {
		return myCharacterMsgManager;
	}

	public MyBiguanManager getMyBiguanManager() {
		return myBiguanManager;
	}

	public MyNewGuideManager getMyNewcomeManager() {

		return myNewGuideManager;
	}

	public IMoveController getMoveController() {
		return movecontroller;
	}

	public void addToBatchUpdateTask(Runnable run) {
		asynchronousTask.addToBatchUpdateTask(run);
	}

	public AsynchronousTask getAsynchronousTask() {
		return asynchronousTask;
	}

	public void setMaxBagAmount(short maxBagAmount) {
		this.maxBagAmount = maxBagAmount;
	}

	public short getMaxBagAmount() {
		return maxBagAmount;
	}

	@Override
	public void die(VisibleObject attacker) {
		long now = System.currentTimeMillis();
		if (now - diedata.dieTime < 1000) {
			return;
		}
		diedata.dieTime = now;
		boolean isGuard = this.getEffectController().isGuard();
		myCharacterVipManger.removeHuQiFeibaohuBuffer();
		diedata.showhorse = characterHorseController.getShowHorse();

		setObjectState(VisibleObjectState.Die);
		getMoveController().stopMove();
		super.die(attacker);

		getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		characterHorseController.onOwnerDie();
		// 队伍状态广播
		getEyeShotManager().sendMsg(getDieMsg());

		if (attacker.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero _attaCharacter = (Hero) attacker;
			_attaCharacter.setKillNum(_attaCharacter.getKillNum() + GameConstant.KILL_HERO_NUM);
			_attaCharacter.sendMsg(new CharacterOneAttributeMsg49990(_attaCharacter, EffectType.killNum, _attaCharacter.getKillNum()));
			if (getCharacterOnHoorController().isAutoOnHoor()) {
				_attaCharacter.setDevilcnt(_attaCharacter.getDevilcnt() + 1);
			}
			this.getMyFriendManager().characterPkDieBy(_attaCharacter);
			this.getMyCharacterAchieveCountManger().getCharacterDieCount().characterDie(_attaCharacter);
			this.getMyFactionManager().pkDie(_attaCharacter);
			if (_attaCharacter.getFightController().isZiweiTarget(getId())) {
				_attaCharacter.getFightController().removeZiweiTarget(getId());
			}
			_attaCharacter.getFightController().win(this);
			setDieByRole(true);
		}
		fightController.releaseFightStatus();
		// ScriptEventCenter.getInstance().onRoleDie(null, this);
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_RoleDie, new Object[] { this });

		getEnmityManager().clearEnmityList();
		// 我死了,把我的仇恨移除掉
		getEnmityManager().clearWhosEnmityisMe();
		attacker.getEnmityManager().removeFromMyEnmityList(this);
		setTarget(attacker);// 最后是死在谁手里的
		// 发送日志
		CharacterGoods fabaogoods = getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);
		if (fabaogoods != null && fabaogoods.getIsUse() == 1) {
			this.getEquipmentController().changeProperty(this, fabaogoods, TakeMethod.off);
			this.getEyeShotManager().sendMsg(new RideFabaoResponse(this.getId(), CommonUseNumber.byte0, fabaogoods.getGoodmodelId()));
		}
		if (!this.sceneRef.dieAffterRelive(this, isGuard)) {
			diepunish(attacker);// 立即复活后，不用死亡惩罚
		}
	}

	private void shock(VisibleObject attacker) {
		List<IShockListener> shockListeners = sceneRef.getShockListeners();
		int size = shockListeners.size();
		for (int i = 0; i < size; i++) {
			if (!shockListeners.get(i).onShocked(this, attacker, sceneRef)) {
				return;
			}
		}

		CharacterGoods fabaogoods = getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);
		if (fabaogoods != null && fabaogoods.getIsUse() == 1) {
			fabaogoods.setIsUse(CommonUseNumber.byte0);
			this.getEquipmentController().changeProperty(this, fabaogoods, TakeMethod.off);
			this.getEyeShotManager().sendMsg(new RideFabaoResponse(this.getId(), CommonUseNumber.byte0, fabaogoods.getGoodmodelId()));
		}
		long now = System.currentTimeMillis();
		myCharacterVipManger.removeHuQiFeibaohuBuffer();

		setObjectState(VisibleObjectState.Shock);
		getMoveController().stopMove();
		super.die(attacker);

		getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		characterHorseController.onOwnerDie();

		if (attacker.getSceneObjType() == SceneObj.SceneObjType_Hero) {

			Hero _attaCharacter = (Hero) attacker;
			if (getCharacterOnHoorController().isAutoOnHoor()) {
				_attaCharacter.setDevilcnt(_attaCharacter.getDevilcnt() + 1);
			}
			this.getMyFriendManager().characterPkDieBy(_attaCharacter);
			this.getMyCharacterAchieveCountManger().getCharacterDieCount().characterDie(_attaCharacter);
			this.getMyFactionManager().pkDie(_attaCharacter);
			if (_attaCharacter.getFightController().isZiweiTarget(getId())) {
				_attaCharacter.getFightController().removeZiweiTarget(getId());
			}
			_attaCharacter.getFightController().win(this);
			setDieByRole(true);
		}
		fightController.releaseFightStatus();
		getEnmityManager().clearEnmityList();
		// 我死了,把我的仇恨移除掉
		getEnmityManager().clearWhosEnmityisMe();
		attacker.getEnmityManager().removeFromMyEnmityList(this);
		setTarget(attacker);// 最后是死在谁手里的

		if (attacker.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			attacker.setObjectState(VisibleObjectState.ShockWaiting);
			this.someoneShocksMe(attacker.getId(), SceneMonster.class, this.getId(), Hero.class, now);
			attacker.iShockSomeone(attacker.getId(), SceneMonster.class, this.getId(), Hero.class, now);
		} else {
			attacker.setObjectState(VisibleObjectState.Idle);
			this.someoneShocksMe(attacker.getId(), Hero.class, this.getId(), Hero.class, now);
			attacker.iShockSomeone(attacker.getId(), Hero.class, this.getId(), Hero.class, now);
		}
		// 队伍状态广播
		getEyeShotManager().sendMsg(getShockMsg());
	}

	public FuhuoTimer getFuhuotimer() {
		return fuhuotimer;
	}

	public AntiAddictedSystem getAntiAddictedSystem() {
		return antiAddictedSystem;
	}

	public IMyTradeController getMyTradeController() {

		return myTradeController;
	}

	public CharacterGoodController getCharacterGoodController() {
		return characterGoodController;
	}

	public GamePlayer getPlayer() {
		return player;
	}

	public void setPlayer(GamePlayer player) {
		this.player = player;
	}

	public void setNowAppellationid(Integer nowAppellationid) {
		this.nowAppellationid = nowAppellationid;
	}

	public Integer getDazuoSkill() {
		if (dazuoSkill == null) {
			this.dazuoSkill = 0;
		}
		return dazuoSkill;
	}

	public void setDazuoSkill(Integer dazuoSkill) {
		this.dazuoSkill = dazuoSkill;
	}

	public long getTodayLiaoshangExp() {
		return todayLiaoshangExp;
	}

	public void setTodayLiaoshangExp(long todayLiaoshangExp) {
		this.todayLiaoshangExp = todayLiaoshangExp;
	}

	public Date getTodayLiaoshangTime() {
		return todayLiaoshangTime;
	}

	public void setTodayLiaoshangTime(Date todayLiaoshangTime) {
		this.todayLiaoshangTime = todayLiaoshangTime;
	}

	/**
	 * 玩家当前显示称谓Id
	 * 
	 * @return Integer
	 */
	public Integer getNowAppellationid() {
		if (nowAppellationid == null) {
			this.nowAppellationid = 0;
		}
		return nowAppellationid;
	}

	public void setNowExperience(long nowExperience) {
		if (nowExperience < 0){
			nowExperience = 0;
		}
		int i = (int) (nowExperience - this.nowExperience);
		if (i > 0) {
			// 置零时不处理
			getDayInCome().dealExp(i);
		}
		this.nowExperience = nowExperience;
	}

	public long getNowExperience() {
		return nowExperience;
	}

	public long getNextExperience() {
		// return
		// CharactergradeManager.getInstance().getCharacterNextExpByGrade(getGrade());
		return nextExperience;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取名字（但是客户端显示名字获得 调用getViewName()）
	 */
	public String getName() {
		return this.name;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Byte getSex() {
		return this.sex;
	}

	public byte getHeadimg() {
		return headimg;
	}

	public void setHeadimg(byte headimg) {
		this.headimg = headimg;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getAccountId() {
		return this.accountId;
	}

	/**
	 * 人男（道士1),妖男(战士2),人女（乐师3),妖女（法师4)
	 * 
	 * @param popsinger
	 */
	public void setPopsinger(int popsinger) {
		this.popsinger = popsinger;
	}

	/**
	 * 人男（道士1),妖男(战士2),人女（乐师3),妖女（法师4)
	 */
	public int getPopsinger() {
		return popsinger;
	}

	public int getCopper() {
		return copper;
	}

	public void setCopper(int copper) {
		if (copper < 0) {
			copper = 0;
		}
		this.copper = copper;
	}

	/**
	 * 0和平模式 1组队模式 2帮会模式 3全体模式
	 * 
	 * @return
	 */
	public int getPkModel() {
		return pkModel;
	}

	@Override
	public void sendMsg(ResponseMsg msg) {
		NetTool.send(this, msg);
	}

	public void sendPrompMsg(int msgkey) {
		sendMsg(new PrompMsg(msgkey));
	}

	public void sendPrompMsg(int position, int msgkey, Object... vars) {
		sendMsg(new PrompMsg(position, msgkey, vars));
	}

	public void sendPrompMsg(int position, int msgkey, String... vars) {
		sendMsg(new PrompMsg(position, msgkey, vars));
	}

	public void sendPrompMsg(String position, int msgkey, String... vars) {
		sendMsg(new PrompMsg(position, msgkey, vars));
	}

	public void sendRightPrompMsg(int msgkey, String... vars) {
		sendPrompMsg(TipMsg.MSGPOSITION_RIGHT, msgkey, vars);
	}

	public void sendRightPrompMsg(int msgkey) {
		sendPrompMsg(TipMsg.MSGPOSITION_RIGHT, msgkey);
	}

	public int getJiaozi() {
		return jiaozi;
	}

	public void setJiaozi(Integer jiaozi) {
		if (jiaozi == null || jiaozi < 0) {
			jiaozi = 0;
		}
		this.jiaozi = jiaozi;
	}

	@Override
	public void update(long now) {
		if (status == VisibleObjectState.Shock) {
			ShockImg img = getShockMeImg();
			boolean timeout = now - img.shockTimestamp > (long) (Options.Shock_Timeout) * 1000;
			if (timeout) {
				getEyeShotManager().sendMsg(new CharacterShockEndMsg(this));
				CharacterResurrect.yuandiFreeResurrectProcess(this, this.getPropertyAdditionController().getExtraMaxHp() / 3);
				// setObjectState(VisibleObjectState.Idle);
				effectController.addUnWithstandBuff(3000);
			}
		}

		super.update(now);
		if (characterDbUpdateTimer.isIntervalOK(now)) {
			addToBatchUpdateTask(new Runnable() {
				@Override
				public void run() {
					try {
						if (!Options.IsCrossServ) {
							CharacterManager.getInstance().cacheToDB(Hero.this);
						} else {
							getMyCharacterAcrossIncomeManager().cacheInComeToDb();
						}
					} catch (Exception e) {
						logger.error("cacheInComeToDb" + e.getMessage(), e);
					}
				}
			});
		}

		try {
			if (!(getCatchYoulongActionController().isCatchYoulongState() || getMyCharacterVehicleManager().isSending())) {
				getCharacterOnHoorController().update(now);
			}
		} catch (Exception e) {
			logger.error("getCharacterOnHoorController " + e.getMessage(), e);
		}

		try {
			// if (!getCharacterOnHoorController().isAutoOnHoor()) {
			if (!(getCatchYoulongActionController().isCatchYoulongState() || getMyCharacterVehicleManager().isSending())) {
				// int skillid = SkillManager.getInstance().getCommonSkill(this.getPopsinger()).getId();
				// this.setSkillid(skillid);
				getFightController().fightInWheel(now);
			}
			// }
		} catch (Exception e) {
			logger.error("fight：" + e.getMessage(), e);
		}

		try {
			getMyTradeController().update(now);// 交易
		} catch (Exception e) {
			logger.error("trade：" + e.getMessage(), e);
		}

		try {
			getCharacterHorseController().update(now);// 坐骑
		} catch (Exception e) {
			logger.error("horse：" + e.getMessage(), e);
		}

		try {
			getTaskController().update(now);// task
		} catch (Exception e) {
			logger.error("task:" + e.getMessage(), e);
		}

		try {
			characterContinuumKillSys.update(now);// 连斩
		} catch (Exception e) {
			logger.error("continue kill:" + e.getMessage(), e);
		}

		try {
			myFactionManager.update();// 帮会更新
		} catch (Exception e) {
			logger.error("myFactionManager" + e.getMessage(), e);
		}

		try {
			myCharacterVipManger.update();
		} catch (Exception e) {
			logger.error("myCharacterVipManger" + e.getMessage(), e);
		}
		// 效果buff的处理
		// 作用效果施加在身上，更改相应值
		// 效果时间到，则移除效果 。并告诉客户端消除该buff效果
		try {
			getEffectController().update(now);
		} catch (Exception e) {
			logger.error("getEffectController" + e.getMessage(), e);
		}
		// 打坐状态给新
		try {
			myDazuoAndUnDaduoManager.autoDazuoOrUndatduoUpate(now);
		} catch (Exception e) {
			logger.error("myDazuoAndUnDaduoManager" + e.getMessage(), e);
		}

		try {
			myBiguanManager.update(now);
		} catch (Exception e) {
			logger.error("myBiguanManager" + e.getMessage(), e);
		}

		try {
			myCharacterGiftpacksManger.update(now);
		} catch (Exception e) {
			logger.error("myCharacterGiftpacksManger" + e.getMessage(), e);
		}
		// 人物行走
		try {
			if (getLocation() == Location.Location_KONG) {
				// 空中的情况永远检查
				getMoveController().checkArrived();
			} else {
				int charStatus = getObjectState();
				if (charStatus == VisibleObjectState.Walk) {
					if (!getEffectController().isSleep() && !getEffectController().isImmb() && !isJiTui()) {
						getMoveController().checkArrived();
					} else {
						if (logger.isDebugEnabled()) {
							logger.debug("update() - imm when moving");
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("checkArrived" + e.getMessage(), e);
		}

		try {
			liantiController.update(now);
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
		}

		try {
			this.getMyTeamManager().timerSendTeamPlayerPosition();
		} catch (Exception e) {
			logger.error("timerSendTeamPlayerPosition" + e.getMessage(), e);
		}

		try {
			this.getAntiAddictedSystem().update();
		} catch (Exception e) {
			logger.error("getAntiAddictedSystem：" + e.getMessage(), e);
		}

		fuhua(now);

		if (this.getObjectState() != VisibleObjectState.Attack && this.getObjectState() != VisibleObjectState.BeAttacked && this.getNowSp() != 0) {
			this.notAttackLoseSp(now);
		}
		dujieCtrlor.update(now);
	}

	public void fuhua(final long nowtime) {
		if (this.getFuhuaNeidanId() == -1 || getIsfuhua() == -1) {
			return;
		}
		if (this.getIsfuhua() == CommonUseNumber.int0) {
			if (nowtime - this.getFuhuaStarttime() >= this.getFuhuaCdtime()) {
				this.setFuhuaNeidanId(-1);
				this.setIsfuhua((byte) -1);
			}
			return;
		}
		if (nowtime - this.getFuhuaStarttime() < this.getFuhuaCdtime()) {
			return;
		}

		// 没空间的情况下不能收获
		if (this.getCharacterHorseController().getBagHorseContainer().getLeaveSpace() < CommonUseNumber.int1) {
			logger.info("没空间的情况下不能收获");
			this.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 893));
			return;
		}

		final CharacterHorse characterHorse = this.getFuhuaCharacterHorse();
		Goodmodel goodmodel = GoodmodelManager.getInstance().get(fuhuaNeidanId);
		this.setFuhuaCdtime((goodmodel.getCoolingtime() / 2) * 1000);
		this.setFuhuaStarttime(nowtime);
		setIsfuhua(CommonUseNumber.byte0);
		if (characterHorse == null) {
			this.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60045));
			return;
		}
		HorseModel horseModel = HorseModelDataProvider.getInstance().getHorseModelByID(characterHorse.getHorseModelId());
		characterHorse.setNeidanCdtime(horseModel.getNeidanCdtime() * 1000);
		characterHorse.setNeidanUsetime(0);
		this.getMyCharacterAchieveCountManger().getHorseCount().catchHorse(horseModel);
		this.getDayInCome().dealGethorse(1);
		final Hero ch = this;
		this.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				HorseContainer horsecontainer = ch.getCharacterHorseController().getBagHorseContainer();
				horsecontainer.addHorse(characterHorse);
				characterHorse.setNeidanStarttime(nowtime);
				ch.sendMsg(new HorseListResponse60018(ch.getCharacterHorseController().getBagHorseContainer()));
				ch.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60049));
			}
		});
	}

	public CharacterTaskController getTaskController() {
		return taskController;
	}

	public CharacterSkillManager getCharacterSkillManager() {
		return characterSkillManager;
	}

	public EquipmentController getEquipmentController() {
		return equipmentController;
	}

	public CombineController getCombineController() {
		return combineController;
	}

	public void setMaxHorseAmount(int maxHorseAmount) {
		this.maxHorseAmount = maxHorseAmount;
	}

	public int getMaxHorseAmount() {
		return maxHorseAmount;
	}

	public String getNowXingqing() {
		if (nowXingqing == null) {
			return "";
		}
		return nowXingqing;
	}

	public void setNowXingqing(String nowXingqing) {
		this.nowXingqing = nowXingqing;
	}

	public String getNowBiaoqing() {
		if (nowBiaoqing == null) {
			return "0000";
		}
		return nowBiaoqing;
	}

	public void setNowBiaoqing(String nowBiaoqing) {
		this.nowBiaoqing = nowBiaoqing;
	}

	@Override
	public BaseSkillManager<Hero> getSkillManager() {
		return characterSkillManager;
	}

	public int getWuxueJingjie() {
		// initwuxueJingjie();
		return wuxueJingjie;
	}

	/**
	 * 刷新武学境界
	 */
	public void initwuxueJingjie() {
		// XXX 可优化
		Map<Integer, CharacterSkill> characterSkillMap = getCharacterSkillManager().getCharacterSkillMap();
		if (characterSkillMap != null) {
			int sumgade = 0;
			Set<Entry<Integer, CharacterSkill>> entrySet = characterSkillMap.entrySet();
			for (Entry<Integer, CharacterSkill> entry : entrySet) {
				WugongType wt = entry.getValue().getSkill().getWugongTypeConsts();
				if (entry.getValue().isPinKan()) {
					continue;
				}
				if (wt == WugongType.BANG_PAI || wt == WugongType.JIANG_HU_JUE_XUE || wt == WugongType.MENG_PAI) {
					sumgade += entry.getValue().getGrade();
				}
			}
			wuxueJingjie = sumgade;
		} else {
			wuxueJingjie = 0;
		}

	}

	public void setWuxueJingjie(int wuxueJingjie) {
		this.wuxueJingjie = wuxueJingjie;
	}

	public void setMaxStorageAmount(short maxStorageAmount) {
		this.maxStorageAmount = maxStorageAmount;
	}

	public short getMaxStorageAmount() {
		return maxStorageAmount;
	}

	/**
	 * 
	 * @return 初 1 高 2 侠 3 武 4 义 5 英 6 尊 7
	 */
	public int getWuxueJingjieGrade() {
		return AppEventCtlor.getInstance().getEvtSkillFormula().getSkillTotalGrade(getWuxueJingjie());
	}

	public void setMaxStorageHorseAmount(int maxStorageHorseAmount) {
		this.maxStorageHorseAmount = maxStorageHorseAmount;
	}

	public int getMaxStorageHorseAmount() {
		return maxStorageHorseAmount;
	}

	public void setStorageCopper(int storageCopper) {
		this.storageCopper = storageCopper;
	}

	public int getStorageCopper() {
		return storageCopper;
	}

	public void setBossKill(int bossKill) {
		this.bossKill = bossKill;
	}

	public int getBossKill() {
		return bossKill;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	public String getStallName() {
		return stallName;
	}

	@Override
	public ResponseMsg getInstanceMoveMsg() {
		return new CharacterInstantMove10120(getId(), getX(), getY());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Hero) {
			Hero o = (Hero) obj;
			return getId().equals(o.getId());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	public Integer getFlowerCount() {
		return flowerCount == null ? 0 : flowerCount;
	}

	public void setFlowerCount(Integer flowerCount) {
		this.flowerCount = flowerCount;
	}

	public CharacterOnHoorController getCharacterOnHoorController() {
		return characterOnHoorController;
	}

	private Map<String, Object> scriptPropertiesMap = new HashMap<String, Object>();

	@Override
	public void setAttribute(String key, Object value) {
		scriptPropertiesMap.put(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return scriptPropertiesMap.get(key);
	}

	@Override
	public Object removeAttribute(String key) {
		return scriptPropertiesMap.remove(key);
	}

	@Override
	public void removeAllAttribute() {
		scriptPropertiesMap.clear();
	}

	public void setPkModel(int pkModel) {
		this.pkModel = pkModel;
	}

	@Override
	public ResponseMsg getEnterEyeshotMsg() {
		return new CharacterEnterEyeShot10028(this);
	}

	@Override
	public ResponseMsg getLeaveEyeshotMsg() {
		int horseId = 0;
		if (this.getCharacterHorseController().getShowHorse() != null) {
			horseId = this.getCharacterHorseController().getShowHorse().getId();
		}
		return new CharacterLeaveEyeShot10080(getId(), horseId);
	}

	public ResponseMsg getDieMsg() {
		return new CharacterDie20046(this);
	}

	public ResponseMsg getShockMsg() {
		return new CharacterShockMsg(this);
	}

	@Override
	public IEyeShotManager getEyeShotManager() {
		return eyeshot;
	}

	@Override
	public byte getSpriteType() {
		return 1;
	}

	public int getDantiangrade() {
		return dantiangrade;
	}

	public void setDantiangrade(int dantiangrade) {
		this.dantiangrade = dantiangrade;
	}

	@Override
	public int changeNowHp(int changeV) {
		if (changeV == 0)
			return 0;
		int maxHp = this.getPropertyAdditionController().getExtraMaxHp();
		if (this.getNowHp() + changeV > maxHp) {
			changeV = maxHp - this.getNowHp();
		}
		int now = this.getNowHp();
		this.setNowHp(now + changeV);
		if (this.getNowHp() < 0) {
			this.setNowHp(0);
			changeV = -now;
		}
		if (changeV != 0) {
			this.getMyTeamManager().updateHpMsgToTeamer();
		}
		return changeV;
	}

	@Override
	public int changeNowMp(int changeV) {
		int maxMp = this.getPropertyAdditionController().getExtraMaxMp();
		if (this.getNowMp() + changeV > maxMp) {
			changeV = maxMp - this.getNowMp();
		}
		this.setNowMp(this.getNowMp() + changeV);
		if (changeV != 0) {
			this.getMyTeamManager().updateMpMsgToTeamer();
		}
		return changeV;
	}

	@Override
	public int changeNowSp(int changeV) {
		int maxSp = this.getPropertyAdditionController().getExtraMaxSp();
		if (this.getNowSp() + changeV > maxSp) {
			changeV = maxSp - this.getNowSp();
		}
		this.setNowSp(this.getNowSp() + changeV);
		return changeV;
	}

	@Override
	public void onBeAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
		if (status == VisibleObjectState.Die) {
			return;
		}
		if (getEffectController().isUnWithstand()) {
			return;
		}
		/*
		 * 【正气】：持此剑将完全无视对方暗器，弓箭，丹田武学带来的伤害
		 */
		Skill skill = fighterVO.getSkill();

		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero attacker = (Hero) whoAttackMe;
			attacker.getEffectController().breakCatch();
			if (attacker.getMyFactionManager().isHaveYoulongBuffer()) {// 臣服：战斗时有额外5%的几率将对手从坐骑上击落
				if (GenerateProbability.defaultIsGenerate(500)) {
					Horse currentRideHorse = getCharacterHorseController().getCurrentRideHorse();
					if (currentRideHorse != null) {
						getCharacterHorseController().unRide();
					}
				}
			}
		}

		catchHorseActionController.breakCatch();
		// rideHorseActionController.breakRideAction();
		myDazuoAndUnDaduoManager.closeDazuo();
		getFightController().onBeAttack(whoAttackMe);
		// 人物主动发起对其他玩家的PK，则和平保护BUFF立即消失，返回系统消息提示：
		// “您对其他玩家发起了PK，和平保护状态消失了“
		if (!fighterVO.isDeBuff()) {
			whoAttackMe.getEffectController().clearPkProtectEffect(true);
		}
		// 反弹伤害：受到伤害之后，承受全部伤害，并将百分比的伤害返还给攻击方
		// logger.info(fighterVO+"---------------character----------------"+fighterVO.getHurtValue());
		int reduceHp = fighterVO.getHurtValue();
		int returnHurt = (int) (reduceHp * getPropertyAdditionController().getExtraFtsh() / 100f);
		if (returnHurt < 0)
			returnHurt = 0;
		if (returnHurt > 0) {
			FighterVO _fighterVO = new FighterVO(this, this, fighterVO.getFighter());
			_fighterVO.setHurtValue(returnHurt);
			logger.info("onBeAttack-------------character --1");
			fighterVO.getFighter().onBeAttack(this, _fighterVO);
		}
		// 伤害减免：在受到伤害后，所受伤害*（1-本值）后再作用到人物身上
		reduceHp = (int) (reduceHp * (1 - getPropertyAdditionController().getExtraShjm() / 100f));

		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			SceneMonster monster = (SceneMonster) whoAttackMe;
			// 攻击玩家按比例少血，（无视玩家防御力）（单位1/10000）（0不生效）(按最大的血量比例少血)
			if (monster.getMonsterModel().getAttackHp() > 0) {
				reduceHp = (int) (getPropertyAdditionController().getExtraMaxHp() * monster.getMonsterModel().getAttackHp() / 10000f);
			}
		}

		boolean isSleep = getEffectController().isSleep();
		boolean nohurt = false;
		try {
			if (isSleep && skill != null && skill.getArrowWay() == ArrowWay.NULL) {
				EffectInfo effectInfo = getEffectController().getBufferInBufferListByBufferId(BuffId.sleepId);
				if (System.currentTimeMillis() - effectInfo.getBufBeginTime() > 2000) {
					nohurt = true;
					getEffectController().removeEffect(effectInfo);
				}
			}
		} catch (Exception e) {
			logger.error("removeEffect：" + e.getMessage(), e);
		}

		List<IHurtListener> listeners = this.getSceneRef().getHurtListeners();
		int size = listeners.size();
		for (int i = 0; i < size; i++) {
			boolean goon = listeners.get(i).beforeHurt(whoAttackMe, this, fighterVO, reduceHp, nohurt);
			if (!goon) {
				return;
			}
		}

		if (reduceHp > 0) {

			hurt(reduceHp, fighterVO, whoAttackMe, skill, nohurt);
		} else {
			if (fighterVO.isForceAttack()) {
				hurt(reduceHp, fighterVO, whoAttackMe, skill, nohurt);
			} else {
				FightMsgSender.directHurtBroadCase(fighterVO.getSkill() == null ? 0 : fighterVO.getSkill().getId(), whoAttackMe, this, 1, fighterVO.getBaoji());
			}

		}

		if (getObjectState() == VisibleObjectState.Attack) {
			if (getTarget() == null || getTarget().isZeroHp())
				setTarget(whoAttackMe);
		}

		for (int i = 0; i < size; i++) {
			listeners.get(i).onBehurted(whoAttackMe, this, fighterVO, reduceHp);
		}
	}

	/**
	 * 计算怒气获得
	 * 
	 * @param hurtValue
	 * @return
	 */
	public int getHurtSp(int hurtValue) {
		int val = 0;
		float xishu = 1;
		switch (popsinger) {
		case 1:
			xishu = GameServer.configParamManger.getConfigParam().getGetspCoef1();
			break;
		case 2:
			xishu = GameServer.configParamManger.getConfigParam().getGetspCoef2();
			break;
		case 3:
			xishu = GameServer.configParamManger.getConfigParam().getGetspCoef3();
			break;
		case 4:
			xishu = GameServer.configParamManger.getConfigParam().getGetspCoef4();
			break;
		}
		val = (int) (xishu * hurtValue);
		// logger.info(xishu+"*"+hurtValue+"=-----"+val);
		return val;
	}

	public int getBeHurtSp(int hurtValue) {
		int val = 0;
		float xishu = 1;
		switch (popsinger) {
		case 1:
			xishu = GameServer.configParamManger.getConfigParam().getGetbespCoef1();
			break;
		case 2:
			xishu = GameServer.configParamManger.getConfigParam().getGetbespCoef2();
			break;
		case 3:
			xishu = GameServer.configParamManger.getConfigParam().getGetbespCoef3();
			break;
		case 4:
			xishu = GameServer.configParamManger.getConfigParam().getGetbespCoef4();
			break;
		}
		val = (int) (xishu * hurtValue);
		// logger.info(""+val);
		return val;
	}

	// 怒气减少操作
	private long loseSpTime = 0;
	private long lastFinishAttackTime = 0;
	private static final long disLoseSpTime = 60 * 1000;

	public void notAttackLoseSp(long now) {
		if (sceneRef.getInstanceModelId() == 16) {
			return;
		}
		if (lastFinishAttackTime == 0) {
			// logger.info("last==0----set last=now");
			lastFinishAttackTime = now;
			return;
		}
		// logger.info("now-sys="+(System.currentTimeMillis()-now));
		// logger.info("last="+lastFinishAttackTime);
		// logger.info("---"+(now-lastFinishAttackTime));
		if (now - lastFinishAttackTime < disLoseSpTime) {
			return;
		}
		if (loseSpTime == 0) {
			loseSpTime = now;
			return;
		}
		if (now - loseSpTime < 1000) {
			return;
		}
		// logger.info("now-loseSpTime="+now+"-"+loseSpTime+"="+(now-loseSpTime));
		loseSpTime = now;
		int loseValue = GameServer.configParamManger.getConfigParam().getPresecLoseSp();
		if (loseValue > 0) {
			loseValue = -loseValue;
		}
		CharacterPropertyManager.changeNowSp(this, loseValue);
		if (this.getNowSp() == 0) {
			// logger.info("nowsp==0--set lastfinish");
			loseSpTime = 0;
			lastFinishAttackTime = 0;
		}
	}

	private void hurt(int reduceHp, FighterVO fighterVO, VisibleObject whoAttackMe, Skill skill, boolean nohurt) {
		boolean nojump = (skill != null && skill.getArrowWay() == ArrowWay.point_one_nojump);// 属于诱导箭
		// 必须命中
		if ((!isJumping() || nojump) && !nohurt) {// 不在跳跃的状态
			int changeHp = changeNowHp(-reduceHp);
			FightMsgSender.directHurtBroadCase(fighterVO.getSkill() == null ? 0 : fighterVO.getSkill().getId(), whoAttackMe, this, 0, fighterVO.getBaoji());
			// 渡劫副本中不增加怒气
			// if (this.getSceneRef().getInstanceModelId() != 2) {
			if ((this.getGrade() - whoAttackMe.getGrade() <= GameConstant.SP_LIMIT_GRADE) && this.getNowSp() < this.getPropertyAdditionController().getExtraMaxSp()) {
				int changeSp = 0;
				// if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
				// Hero tmpch = (Hero) whoAttackMe;
				// changeSp = tmpch.getHurtSp(reduceHp);
				// } else {
				changeSp = this.getBeHurtSp(reduceHp);
				// }
				if (changeSp > 0) {
					CharacterPropertyManager.changeNowSp(this, changeSp);
				}
				// }
			}
			if (status == VisibleObjectState.Shock) {
				if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
					ShockImg img = this.getShockMeImg();
					if (img.killerId != whoAttackMe.getId()) {
						whoAttackMe.setTarget(null);
						return;
					}
				}

				setTarget(whoAttackMe);
				die(whoAttackMe);
			} else if (isZeroHp()) {
				// if (this.getSceneRef().getInstanceModelId() == 2) {
				// dieInInstance(this.getSceneRef().getInstanceController());
				// } else {
				setTarget(whoAttackMe);
				shock(whoAttackMe);
				// }

			} else {
				getEquipmentController().fightInfluenceEquitment();
			}
			getEnmityManager().addEnmityValue(fighterVO.getFighter(), fighterVO.getEnmityValue());
			getEnmityManager().addHurtValue(fighterVO.getFighter(), -changeHp);
		} else {
			FightMsgSender.directHurtBroadCase(fighterVO.getSkill() == null ? 0 : fighterVO.getSkill().getId(), whoAttackMe, this, 2, fighterVO.getBaoji());
			getMyCharacterAchieveCountManger().getCharacterOtherCount().tiaoshanCount(1);
		}
	}

	/**
	 * 死亡惩罚
	 * 
	 * @param whoAttackMe
	 */
	private void diepunish(VisibleObject whoAttackMe) {
		/*
		 * 玩家被怪物杀死，下降耐久度10%，返回消息提示： 很遗憾，您被怪物杀死，当前装备耐久度下降了10% 玩家被其他玩家杀死，下降耐久度调整为5%，返回消息提示： 向胜利方发送消息提示：您杀死了XXX玩家，战场声望+X 向失败方发送消息提示：您被XXX玩家杀死，战场声望-X，当前装备耐久度下降了5%
		 */
		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			List<CharacterGoods> goodList = getEquipmentController().pveDieInfluenceDefend(0.1f);
			String msg = "";
			if (!goodList.isEmpty()) {
				for (Iterator<CharacterGoods> iterator = goodList.iterator(); iterator.hasNext();) {
					CharacterGoods characterGoods = iterator.next();
					msg = msg + "【" + characterGoods.getGoodModel().getNameI18n() + "】";
				}
			}
			sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 533, msg));
		} else {

			List<CharacterGoods> goodList = getEquipmentController().pveDieInfluenceDefend(0.05f);
			Hero attacker = (Hero) whoAttackMe;
			// attacker.getFightController().win(this);
			String msg = "";
			if (!goodList.isEmpty()) {
				for (Iterator<CharacterGoods> iterator = goodList.iterator(); iterator.hasNext();) {
					CharacterGoods characterGoods = iterator.next();
					msg = msg + "【" + characterGoods.getGoodModel().getNameI18n() + "】";
				}
			}

			sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 534, attacker.getViewName(), msg));

			attacker.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 535, getViewName()));
		}
	}

	// 被暗器所伤
	@Override
	public void onBeHiddenAttack(VisibleObject whoAttackMe, FighterVO fighterVO) {
		// if (isZeroHp())
		// return;
		if (status == VisibleObjectState.Die) {
			return;
		}
		if (getEffectController().isUnWithstand()) {
			return;
		}

		catchHorseActionController.breakCatch();
		// rideHorseActionController.breakRideAction();
		myDazuoAndUnDaduoManager.closeDazuo();

		getFightController().onBeAttack(whoAttackMe);
		// 人物主动发起对其他玩家的PK，则和平保护BUFF立即消失，返回系统消息提示：
		// “您对其他玩家发起了PK，和平保护状态消失了“
		whoAttackMe.getEffectController().clearPkProtectEffect(true);

		// 暗器免伤：在受到暗器伤害后，所受伤害*（1-本值）后再作用到人物身上
		int reduceHp = fighterVO.getHiddenValue();
		// reduceHp = (int)(reduceHp * (1 -
		// getPropertyAdditionController().getZhuangbei().getAqms() / 100f));

		if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_MonsterScene) {
			SceneMonster monster = (SceneMonster) whoAttackMe;
			if (monster.getMonsterModel().getAttackHp() > 0) {
				reduceHp = monster.getMonsterModel().getAttackHp();
			}
		} else if (whoAttackMe.getSceneObjType() == SceneObj.SceneObjType_Hero) {
			Hero attacker = (Hero) whoAttackMe;
			attacker.getEffectController().breakCatch();
		}

		if (reduceHp > 0) {
			if (!isJumping()) {
				int changeHp = changeNowHp(-reduceHp);
				FightMsgSender.hiddeweaponDirectHurtBroadCase(whoAttackMe, this, 0, fighterVO.getBaoji());

				if (status == VisibleObjectState.Shock) {
					setTarget(whoAttackMe);
					die(whoAttackMe);
				} else if (isZeroHp()) {
					setTarget(whoAttackMe);
					shock(whoAttackMe);
				} else {
					getEquipmentController().fightInfluenceEquitment();
				}

				getEnmityManager().addEnmityValue(fighterVO.getFighter(), fighterVO.getEnmityValue());
				getEnmityManager().addHurtValue(fighterVO.getFighter(), -changeHp);
			} else {
				FightMsgSender.hiddeweaponDirectHurtBroadCase(whoAttackMe, this, 3, fighterVO.getBaoji());
				// getMyCharacterAchieveCountManger().getCharacterOtherCount()
				// .tiaoshanCount(1);
			}

		} else {
			FightMsgSender.hiddeweaponDirectHurtBroadCase(whoAttackMe, this, 1, fighterVO.getBaoji());
		}

		if (getObjectState() == VisibleObjectState.Attack) {
			if (getTarget() == null || getTarget().isZeroHp())
				setTarget(whoAttackMe);
		}
	}

	public void onJump() {
		catchHorseActionController.breakCatch();
		// rideHorseActionController.breakRideAction();
	}

	public void setLastRidehorse(Integer lastRidehorse) {
		this.lastRidehorse = lastRidehorse;
	}

	public Integer getLastRidehorse() {
		return lastRidehorse;
	}

	@Override
	public void onEnterScene(Scene scene) {
		getEyeShotManager().onEnterScene(scene, true);
	}

	@Override
	public void onLeaveScene(Scene scene, Scene newScene) {
		if (getObjectState() != VisibleObjectState.Shock) {
			setObjectState(VisibleObjectState.Idle);
		}
		getEyeShotManager().onLeaveScene(scene);

		// 如果当前场景是副本场景，新场景不是副本场景
		if (scene != null) {
			if (scene.isInstanceScene()) {
				// ScriptEventCenter.getInstance().onInstanceSceneExit(null,
				// scene.getInstanceController(), scene, this);
				AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_InstanceSceneExit, new Object[] { scene.getInstanceController(), scene, this });
			}
		}
		if (newScene == null) {// 离线
			if (scene.getInstanceController() != null) {
				scene.getInstanceController().onleaveInstanceWhenDownLine(this);
			}
		} else {// 换场景
			if (newScene.getInstanceModelId() != scene.getInstanceModelId()) {
				if (scene.getInstanceModelId() != 0) {
					scene.getInstanceController().onleaveInstance(this);
				}
			}
			// setInstanceId(null);
		}
		getMoveController().stopMove();
		getCatchHorseActionController().breakCatch();
		// getRideHorseActionController().breakRideAction();
		getEnmityManager().clearEnmityList();
		// 跟随者离开
		getCharacterHorseController().onOwnerLeaveScene();

		// getFollowMeController().onMyselfLeaveScene(scene);
		// 要我换场景了,清除哪些仇恨是我的人
		getEnmityManager().clearWhosEnmityisMe();
		getPursuitPointManager().clearArroundWithMeInFightMonsterPositions();
		setSceneRef(null);
	}

	public void setVlineserver(VLineServer vlineserver) {
		this.vlineserver = vlineserver;
	}

	public VLineServer getVlineserver() {
		return vlineserver;
	}

	/** 是否在战斗中 */
	public boolean inFighting(long now) {
		return getFightController().inFight(now);
	}

	public Integer getWeekLoginCount() {
		if (weekLoginCount == null) {
			weekLoginCount = 0;
		}
		return weekLoginCount;
	}

	public void setWeekLoginCount(Integer weekLoginCount) {
		this.weekLoginCount = weekLoginCount;
	}

	public Integer getDropGood() {
		if (this.dropGood == null) {
			this.dropGood = 0;
		}
		return dropGood;
	}

	public void setDropGood(Integer dropGood) {
		this.dropGood = dropGood;
	}

	public int getChongqiRecord() {
		return chongqiRecord;
	}

	public void setChongqiRecord(int chongqiRecord) {
		this.chongqiRecord = chongqiRecord;
	}

	public Integer getMaxJumpCount() {
		return maxJumpCount;
	}

	public void setMaxJumpCount(Integer maxJumpCount) {
		this.maxJumpCount = maxJumpCount;
	}

	public Integer getDayOnline() {
		if (dayOnline == null) {
			this.dayOnline = 0;
		}
		return dayOnline;
	}

	public void setDayOnline(Integer dayOnline) {
		if (this.dayOnline == null) {
			this.dayOnline = 0;
		}
		this.dayOnline = dayOnline;
	}

	@Override
	public void onBeJiTui(int jituiDistance) {
		super.onBeJiTui(jituiDistance);
		setObjectState(VisibleObjectState.Jitui);
	}

	@Override
	public boolean isJiTui() {
		return getObjectState() == VisibleObjectState.Jitui;
	}

	@Override
	public boolean isAblePk(VisibleObject vo) {

		if (vo.getSceneObjType() == SceneObj.SceneObjType_Hero) {

			Hero cvo = (Hero) vo;
			if (getSceneRef().isPkGradeProtect()) {
				if (vo.getGrade() < GameConstant.PK_GRADE) {
					sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 536));
					return false;
				}

				if (getGrade() < GameConstant.PK_GRADE) {
					sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 536));
					return false;
				}
			}

			if (getSceneRef().isPkGradeProtect() && !cvo.getMyCharacterVipManger().isFeibaohuState()) {// 被攻击者不是"非保护和平状态"
				if (Math.abs(vo.getGrade() - getGrade()) >= GameConstant.PK_GRADE_CHA) {// 如果被攻击者处于非保护和平状态或者地图不是pk保护的地图，则跳过12等级差的判断
					if (getGrade() < vo.getGrade()) {
						sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 537));
					} else {
						sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 538));
					}
					return false;
				}
			}

			if (vo.getEffectController().isIspkProtect()) {
				sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 539));
				return false;
			}

			CharacterOnHoorController characterOnHoorController = cvo.getCharacterOnHoorController();
			if (characterOnHoorController.isAutoOnHoor()) {
				// 今日的杀孽已然过重，将导致心魔滋生，进而导致修为停滞不前。今日将无法再对挂机状态中的玩家发起PK
				// if (getDevilcnt() >= 30) {
				// sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20126));
				// return false;
				// }

				if (characterOnHoorController.isPkProtectTime() && characterOnHoorController.isProtect()) {
					sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20086));// 夜间0点-8点之间处于挂机状态的玩家为免战保护，在此期间您无法PK处于挂机模式下的该玩家
					return false;
				}

			}
		} else if (vo instanceof SceneBangqiMonster) {
			SceneBangqiMonster sbm = (SceneBangqiMonster) vo;
			int lind = getVlineserver().getLineid();
			if (!sbm.isPkTime()) {
				sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 540));
				return false;
			}
			if (sbm.getSceneBangqi().getLine() != lind) {
				//
				sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 541, sbm.getSceneBangqi().getLine() + "", sbm.getSceneBangqi().getLine() + ""));
				return false;
			}
			if (getGrade() < 50) {
				sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 542));
				return false;
			}
			if (!getMyFactionManager().isFaction()) {
				sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 543));
				return false;
			}
			int factionId = getMyFactionManager().getFactionId();
			if (factionId == sbm.getSceneBangqi().getFactionId()) {
				sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 544));
				return false;
			}
		}

		return true;
	}

	/**
	 * 获取当前副本层数
	 * 
	 * @return
	 */
	public int getInstanceflushGrade() {
		return myCharacterInstanceManager.getInstanceflushGrade();
	}

	public void setInstanceflushGrade(int instanceflushGrade) {
		myCharacterInstanceManager.setInstanceflushGrade(instanceflushGrade);
	}

	public int getMaxTeamerGrade() {
		int grade = this.getGrade();
		if (this.getMyTeamManager().isTeam()) {
			List<Hero> list = this.getMyTeamManager().getMyTeam().getCharacterPlayers();
			for (Hero character : list) {
				if (grade < character.getGrade()) {
					grade = character.getGrade();
				}
			}
		}
		return grade;
	}

	@Override
	public int getContinuumKillHate() {
		return getEffectController().getContinuumKillHate();
	}

	@Override
	public float getContinuumExp() {
		return getEffectController().getContinuumExp();
	}

	@Override
	public int getDaguaiExp() {
		return GameServer.doubActivityManager.getCacheDoubActivity().getDaguaiExp();
	}

	@Override
	public SCharacterGoodController getSCharacterGoodController() {
		return getCharacterGoodController();
	}

	@Override
	public int getTeamNum() {
		int teamNum = 1;
		if (getMyTeamManager().isTeam()) {
			teamNum = getMyTeamManager().getMyTeam().getTeamPopulationInScene(getSceneRef());
		}
		return teamNum;
	}

	@Override
	public boolean isContinuumKill() {
		return getEffectController().isContinuumKill();
	}

	@Override
	public int getDoubExpNum() {
		if (getEffectController().isDouble10Exp()) {
			return 9;
		}

		if (getEffectController().isDouble5Exp()) {
			return 4;
		}

		if (getEffectController().isDoubleExp()) {
			return 1;
		}

		return 0;
	}

	/**
	 * 当角色杀死monster时
	 * 
	 * @param monster
	 */
	public void onKillMonster(SceneMonster monster) {
		try {
			int _experience = AppEventCtlor.getInstance().getEvtFightFormula().getExp(this, monster);
			// int _experience = new FightFormula().getExp(this, monster);
			CharacterFormula.experienceProcess(this, _experience);
		} catch (Exception e) {
			logger.error("onKillMonster-exp：" + e.getMessage(), e);
		}
		getCharacterHorseController().gainedMonsterExp(monster);
		if (getMyTeamManager().isTeam()) {
			getMyFriendManager().updateFavorWhenTeam();
		}
	}

	public boolean isFristEnterMap() {
		return isFristEnterMap;
	}

	public void setFristEnterMap(boolean isFristEnterMap) {
		this.isFristEnterMap = isFristEnterMap;
	}

	public Integer getChengjiuPoint() {
		return chengjiuPoint;
	}

	public void setChengjiuPoint(Integer chengjiuPoint) {
		this.chengjiuPoint = chengjiuPoint;
	}

	@Override
	public int getBadGoodCountByGoodId(int goodId) {
		return this.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(goodId);
	}

	@Override
	public int getStorageGoodCountByGoodId(int goodId) {
		return this.getCharacterGoodController().getStorageGoodsContainer().getGoodsCountByModelID(goodId);
	}

	public int getStallGoodCountByGoodId(int goodId) {
		return this.getCharacterGoodController().getStallGoodsContainer().getGoodsCountByModelID(goodId);
	}

	@Override
	public Horse getMaxBagPinjieHorse() {
		return this.getCharacterHorseController().getBagHorseContainer().getMaxPingJieHorse();
	}

	@Override
	public Horse getMaxStoragePinjieHorse() {
		return this.getCharacterHorseController().getBagHorseContainer().getMaxPingJieHorse();
	}

	public String getViewName() {
		// if (GameServer.configParamManger.getConfigParam().isShowMyServerId()) {
		// return "[" + this.getOriginalSid() + Language.QU + "]" + this.name;
		// }
		return this.name;
	}

	public Integer getContribution() {
		if (this.contribution == null) {
			this.contribution = 0;
		}
		return contribution;
	}

	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}

	@Override
	public int getBanghuiJiacheng() {
		return this.myFactionManager.getMonsterExpJiacheng();
	}

	@Override
	public int xiangyangFactionJiacheng() {
		return getMyFactionManager().xiangyangFactionJiacheng();
	}

	public boolean isWudiBuffer() {
		return isWudiBuffer;
	}

	public void setWudiBuffer(boolean isWudiBuffer) {
		this.isWudiBuffer = isWudiBuffer;
	}

	@Override
	public int getExpAttack() {
		return this.propertyController.getExtraAttack();
	}

	@Override
	public int getExtDefence() {
		return this.propertyController.getExtraDefend();
	}

	@Override
	public int getExtCrt() {
		return this.propertyController.getExtraCrt();
	}

	@Override
	public int getExtDodge() {
		return this.propertyController.getExtraDodge();
	}

	@Override
	public int getExtAttackSpeed() {
		return this.propertyController.getExtraAttackSpeed();
	}

	@Override
	public int getExtMoveSpeed() {
		return this.propertyController.getExtraMoveSpeed();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<SRole> getTeamCharacters() {
		if (this.myTeamManager.isTeam()) {
			List roles = this.myTeamManager.getMyTeam().getCharacterPlayers();
			return roles;
		} else {
			return null;
		}

	}

	@Override
	public int getCount(int type) {
		CharacterCount characterCount = characterCountController.getCharacterCount(type);
		if (characterCount == null) {
			return 0;
		}
		return characterCount.getCount();
	}

	@Override
	public int[] getHeedSceneObject() {
		return heedSceneObject;
	}

	/**
	 * 是否是男性玩家`
	 * 
	 * @return
	 */
	@Override
	public boolean isMale() {
		if (this.getPopsinger() < 3) {
			return true;
		}
		return false;
	}

	public int getChengzhanShengWang() {
		return chengzhanShengWang;
	}

	public void setChengzhanShengWang(int chengzhanShengWang) {
		this.chengzhanShengWang = chengzhanShengWang;
	}

	public int getLunjianShengWang() {
		return lunjianShengWang;
	}

	public void setLunjianShengWang(int lunjianShengWang) {
		this.lunjianShengWang = lunjianShengWang;
	}

	public int getTodayChengzhanShengwang() {
		return todayChengzhanShengwang;
	}

	public void setTodayChengzhanShengwang(int todayChengzhanShengwang) {
		this.todayChengzhanShengwang = todayChengzhanShengwang;
	}

	@Override
	public boolean hasFengYuTongJiBuff() {
		return getEffectController().isFengYuTongJi();
	}

	@Override
	public SHorse getCurrRidingHorse() {
		return getCharacterHorseController().getCurrentRideHorse();
	}

	/**
	 * 检查是否有离线消息并发送
	 */
	public void checkLeaveMsg() {
		WedFeast feast = WedFeastManager.getInstance().getFeastByRoleid(getId());
		if (feast != null && feast.isUNStart() && feast.getMateId().equals(getId())) {
			new WedFeastMessageResponse52248(0, WedFeastManager.getNextDay(feast), feast.getFasttype(), feast.getLine());
		}

	}

	public CharacterPropertyAdditionControllerImp getCharacterPropertyAdditionController() {
		return (CharacterPropertyAdditionControllerImp) super.getPropertyAdditionController();
	}

	@Override
	public boolean isAllBornEquip() {
		return getEquipmentController().isAllBornEquip();
	}

	@Override
	public int getCharacterHiddenWeaponGrade() {
		CharacterHiddenWeapon characterHiddenWeapon = getCharacterHiddenWeaponController().getCharacterHiddenWeapon();
		if (characterHiddenWeapon == null) {
			return 0;
		}
		return characterHiddenWeapon.getGrade();
	}

	public Integer getAccountInitiallyId() {
		return accountInitiallyId;
	}

	public void setAccountInitiallyId(Integer accountInitiallyId) {
		this.accountInitiallyId = accountInitiallyId;
	}

	public Integer getCharacterInitiallyId() {
		return characterInitiallyId;
	}

	public void setCharacterInitiallyId(Integer characterInitiallyId) {
		this.characterInitiallyId = characterInitiallyId;
	}

	@Override
	public float getAntiAddictedSystemPlusScale() {
		return getAntiAddictedSystem().getExpPlusScale();
	}

	@Override
	public int getDantianGrade() {
		int size = 0;
		DanTian dantian = getDanTianController().getDanTian();
		if (dantian != null) {
			size = dantian.getModel().getId();
		}
		return size;
	}

	@Override
	public void setObjectState(int state) {
		if (this.status == VisibleObjectState.Shock) {
			if (state != VisibleObjectState.Die) {
				return;
			}
		}

		if (this.status == VisibleObjectState.Dazuo && state != VisibleObjectState.Dazuo) {
			myDazuoAndUnDaduoManager.closeDazuo();
		}
		if (this.status == VisibleObjectState.Attack && state != VisibleObjectState.Attack) {
			this.lastFinishAttackTime = System.currentTimeMillis();
		}
		if (state != VisibleObjectState.Walk) {
			movecontroller.stopMove();
		}
		if (state == VisibleObjectState.Idle) {
			if (getCharacterOnHoorController().isAutoOnHoor()) {// 带动挂机状态切换
				getCharacterOnHoorController().getRoleState().switchState(VisibleObjectState.Idle);
			}
			myDazuoAndUnDaduoManager.updateIdelTime();
		}

		if (state == VisibleObjectState.Shock) {
			getUpdateObjManager().clearEffectFromOther();
			getEffectController().clearEffectListAndRemoveBuffOnBody();
		}

		this.status = state;
	}

	@Override
	public int getObjectState() {
		return status;
	}

	@Override
	public int getSceneObjType() {
		return SceneObjType_Hero;
	}

	/**
	 * 记录孵化成功后产生的灵宠
	 * 
	 * @return
	 */
	public CharacterHorse getFuhuaCharacterHorse() {
		return fuhuaCharacterHorse;
	}

	/**
	 * 记录孵化成功后产生的灵宠
	 * 
	 * @return
	 */
	public void setFuhuaCharacterHorse(CharacterHorse fuhuaCharacterHorse) {
		this.fuhuaCharacterHorse = fuhuaCharacterHorse;
	}

	public long getFuhuaCdtime() {
		return fuhuaCdtime;
	}

	public void setFuhuaCdtime(long fuhuaCdtime) {
		this.fuhuaCdtime = fuhuaCdtime;
	}

	public long getFuhuaStarttime() {
		return fuhuaStarttime;
	}

	public void setFuhuaStarttime(long fuhuaStarttime) {
		this.fuhuaStarttime = fuhuaStarttime;
	}

	/**
	 * 孵化中的内丹id，-1表示没有在孵化
	 * 
	 * @return
	 */
	public int getFuhuaNeidanId() {
		return fuhuaNeidanId;
	}

	/**
	 * 孵化中的内丹id，-1表示没有在孵化
	 * 
	 * @param isfuhua
	 */
	public void setFuhuaNeidanId(int fuhuaNeidanId) {
		this.fuhuaNeidanId = fuhuaNeidanId;
	}

	/**
	 * 是否在孵化中，-1表示没有在孵化，0是在孵化的冷却中，1表示在孵化中
	 * 
	 * @return
	 */
	public byte getIsfuhua() {
		return isfuhua;
	}

	/**
	 * 是否在孵化中，-1表示没有在孵化，0是在孵化的冷却中，1表示在孵化中
	 * 
	 */
	public void setIsfuhua(byte isfuhua) {
		this.isfuhua = isfuhua;
	}

	/**
	 * //孵化次数累计,格式：内丹id,次数;内丹id,次数;
	 * 
	 * @return
	 */
	public String getFuhuaCount() {
		return fuhuaCount;
	}

	/**
	 * //孵化次数累计,格式：内丹id,次数;内丹id,次数;
	 * 
	 * @param fuhuaCount
	 */
	public void setFuhuaCount(String fuhuaCount) {
		this.fuhuaCount = fuhuaCount;
	}

	public DujieCtrlor getDujieCtrlor() {
		return dujieCtrlor;
	}

	/**
	 * 孵化次数累计,格式：key=内丹id,value=次数 在初始化人物的时候进行赋值 在发生改变的时候，累计赋值给fuhuaCount
	 * 
	 * @return
	 */
	public int getFuhuaCount(int neidanid) {
		int cnt = 0;
		if (fuhuaCountMap.containsKey(neidanid)) {
			cnt = fuhuaCountMap.get(neidanid);
		}
		return cnt;
	}

	public void setFuhuaCount(int neidanid, int cnt) {
		fuhuaCountMap.put(neidanid, cnt);
		StringBuilder sb = new StringBuilder();
		for (int key : fuhuaCountMap.keySet()) {
			sb.append(key).append(Symbol.DOUHAO).append(fuhuaCountMap.get(key)).append(Symbol.FENHAO);
		}
		this.setFuhuaCount(sb.toString());
	}

	/**
	 * 得到神通技能管理器
	 * 
	 * @return
	 */
	public ShenTongSkillManager getShenTongSkillManager() {
		return shenTongSkillManager;
	}

	/**
	 * 8 client ip 用户ip 9 game server log time stamp 游戏记录时间点 10 ouid 唯一游戏外部id，如qq平台id 11 iuid 唯一游戏内部id 12 生日 yyyy-mm-dd 13 性别 1男，2女，3未知 14 游戏好友数 15 平台好友数 16 游戏等级 17 游戏经验 18 付费货币余额
	 * 类似MB 19 免费货币余额 类似GB 20 附加资源值1 1.2.3 21 附加资源值2 22 游戏属性1 比如战斗次数，邀请次数 23 游戏属性2 24 VIP等级 如QQ黄钻，0代表非黄钻，如有黄钻等级传1,2,3,4... 25 新手进入游戏时间 Timestamp 26 游戏版本 8位字符 27 国家 字符 28 用户入口或者游戏环境
	 * 比如朋友，空间，qqgame不同入口 6位字符
	 * 
	 * @return
	 */
	public String getHeroInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getLastip());
		sb.append(Symbol.SEPARATOR_OR);
		sb.append(System.currentTimeMillis() / 1000);
		sb.append(Symbol.SEPARATOR_OR);
		sb.append(this.id);
		sb.append(Symbol.SEPARATOR_OR);
		sb.append(this.getAccount().getAccountInitiallyId());
		sb.append(Symbol.SEPARATOR_OR);
		// 生日
		sb.append(Symbol.SEPARATOR_OR);
		// 性别
		sb.append(Symbol.SEPARATOR_OR);
		// 游戏好友数
		sb.append(Symbol.SEPARATOR_OR);
		// 平台好友数
		sb.append(Symbol.SEPARATOR_OR);
		sb.append(this.getGrade());
		return sb.toString();
	}

	public String getLogUid() {
		return this.getId().toString();
	}

	@Override
	public int getCurrentStat() {
		return this.getObjectState();
	}

	public int getKillNum() {
		return killNum;
	}

	public void setKillNum(int killNum) {
		this.killNum = killNum;
	}

	@Override
	public void setStandState() {
		if (getCharacterOnHoorController().isAutoOnHoor()) {// 带动挂机状态切换
			getCharacterOnHoorController().getRoleState().switchState(VisibleObjectState.Idle);
		}
		myDazuoAndUnDaduoManager.updateIdelTime();
		this.status = VisibleObjectState.Idle;
	}
}
