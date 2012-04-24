package net.snake.gamemodel.hero.persistence;

import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.commons.StringUtil;
import net.snake.commons.Timer;
import net.snake.gamemodel.across.persistence.AcrossIncomeManager;
import net.snake.gamemodel.faction.persistence.FactionManager;
import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.gamemodel.instance.persistence.FubenrankingManager;
import net.snake.gamemodel.pet.bean.HorseCharacterView;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.persistence.HorseCharacterViewDataProvider;
import net.snake.gamemodel.pet.persistence.HorseModelDataProvider;
import net.snake.gamemodel.skill.bean.HiddenWeaponDataEntry;
import net.snake.gamemodel.skill.persistence.CharacterHiddenWeaponManager;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.shell.Options;



public class RankingManager {
	private static Logger logger = Logger.getLogger(RankingManager.class);
	// action 1等级排行榜 2财富排行榜 3坐骑身价榜 4连斩次数榜 5BOSS击杀榜 6战场声望榜 7武功境界榜 8经脉进度榜 9成就进度榜
	// 13 天下第一 11 论剑 12 城战排行
	public final static int rendengjichenghao = 1;// 等级称号 十大高手
	public final static int renqianchenghao = 2;// 钱 十大富豪
	public final static int renzuoqichenghao = 3;// 坐骑 十大神骑
	public final static int renlianzhanchenghao = 4;// 连斩 十大杀神
	public final static int renbooschenghao = 5;// boos 十大侠士
	// public final static int renzhanchangchenghao = 6;// 战场 十大魔头
	public final static int renwugongjingjiechenghao = 7;// 武功 武术宗师
	public final static int renjingmaichenghao = 8;// 经脉 经脉通玄
	public final static int renchengjiuchenghao = 9;// 成就 传说中人
	public final static int rencikechenghao = 10; // 十大刺客
	public final static int rentianxiadiyi = 13; // 天下第一
	public final static int renlunjian = 11; // 华山霸主
	public final static int renchengzhan = 12; // 城战王者

	private List<CharacterRanking> leveList = null;
	private List<CharacterRanking> characterRankingList;
	private List<CharacterRanking> characterRankingMoneyList;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingMoneyList = new HashMap<Integer, List<CharacterRanking>>();
	// 经脉87个穴位是百分之50
	private List<CharacterRanking> characterRankingJingMaiList;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingJingMaiList = new HashMap<Integer, List<CharacterRanking>>();
	// 连斩
	private List<CharacterRanking> characterRankingLianZhanList;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingLianZhanList = new HashMap<Integer, List<CharacterRanking>>();
	// 马
	private List<HorseCharacterView> characterRankingMaList;
	private Map<Integer, List<HorseCharacterView>> mapCharacterRankingMAList = new HashMap<Integer, List<HorseCharacterView>>();
	// 战场声望
	private List<CharacterRanking> characterRankingZhanChangList;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingZhanChangList = new HashMap<Integer, List<CharacterRanking>>();
	// boss排行
	private List<CharacterRanking> characterRankingBossList;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingBossList = new HashMap<Integer, List<CharacterRanking>>();
	// 武学境界
	private List<CharacterRanking> characterRankingWuGongJingJieList;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingWuGongJingJieList = new HashMap<Integer, List<CharacterRanking>>();
	// 成就排行
	private List<CharacterRanking> characterRankingChengjiuList;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingChengjiu = new HashMap<Integer, List<CharacterRanking>>();
	// 暗器排行
	private List<HiddenWeaponDataEntry> characterRankingAnQiList;
	private Map<Integer, List<HiddenWeaponDataEntry>> mapCharacterRankingAnQiList = new HashMap<Integer, List<HiddenWeaponDataEntry>>();
	// 副本排行
	private List<Fubenranking> fubenrankingList;
	private Map<Integer, List<Fubenranking>> mapfubenRanking = new HashMap<Integer, List<Fubenranking>>();
	private Map<Integer, List<Fubenranking>> mapfubenRankingTongJi = new HashMap<Integer, List<Fubenranking>>();

	// 城战排行
	private List<CharacterRanking> characterRankingChengZhan;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingChengZhanList = new HashMap<Integer, List<CharacterRanking>>();

	// 论剑排行
	private List<CharacterRanking> characterRankingLunJian;
	private Map<Integer, List<CharacterRanking>> mapCharacterRankingLunJianList = new HashMap<Integer, List<CharacterRanking>>();

	public List<CharacterRanking> getCharacterRankingLunJian() {
		return characterRankingLunJian;
	}

	public void setCharacterRankingLunJian(List<CharacterRanking> characterRankingLunJian) {
		this.characterRankingLunJian = characterRankingLunJian;
	}

	public Map<Integer, List<CharacterRanking>> getMapCharacterRankingLunJianList() {
		return mapCharacterRankingLunJianList;
	}

	public void setMapCharacterRankingLunJianList(Map<Integer, List<CharacterRanking>> mapCharacterRankingLunJianList) {
		this.mapCharacterRankingLunJianList = mapCharacterRankingLunJianList;
	}

	public List<CharacterRanking> getCharacterRankingChengZhan() {
		return characterRankingChengZhan;
	}

	public void setCharacterRankingChengZhan(List<CharacterRanking> characterRankingChengZhan) {
		this.characterRankingChengZhan = characterRankingChengZhan;
	}

	public Map<Integer, List<CharacterRanking>> getMapCharacterRankingChengZhanList() {
		return mapCharacterRankingChengZhanList;
	}

	public void setMapCharacterRankingChengZhanList(Map<Integer, List<CharacterRanking>> mapCharacterRankingChengZhanList) {
		this.mapCharacterRankingChengZhanList = mapCharacterRankingChengZhanList;
	}

	public Map<Integer, List<HiddenWeaponDataEntry>> getMapCharacterRankingAnQiList() {
		return mapCharacterRankingAnQiList;
	}

	public Map<Integer, List<Fubenranking>> getMapfubenRankingTongJi() {
		return mapfubenRankingTongJi;
	}

	public List<Fubenranking> getFubenrankingList() {
		return fubenrankingList;
	}

	public Map<Integer, List<Fubenranking>> getMapfubenRanking() {
		return mapfubenRanking;
	}

	public Map<Integer, List<CharacterRanking>> getMapCharacterRankingChengjiu() {
		return mapCharacterRankingChengjiu;
	}

	public List<HiddenWeaponDataEntry> getCharacterRankingAnQiList() {
		return characterRankingAnQiList;
	}

	public void setCharacterRankingAnQiList(List<HiddenWeaponDataEntry> characterRankingAnQiList) {
		this.characterRankingAnQiList = characterRankingAnQiList;
	}

	public List<CharacterRanking> getCharacterRankingChengjiuList() {
		return characterRankingChengjiuList;
	}

	public void setCharacterRankingChengjiuList(List<CharacterRanking> characterRankingChengjiuList) {
		this.characterRankingChengjiuList = characterRankingChengjiuList;
	}

	// gm
	private String charactersList_GM;
	// 人物10大头衔
	private Map<Integer, String> mapTopTenId = new HashMap<Integer, String>();
	// 发送过头衔的id
	private HashSet<Integer> oldCharacteridHashSet = new HashSet<Integer>();

	public Map<Integer, String> getMapTopTenId() {
		return mapTopTenId;
	}

	public String getCharactersList_GM() {
		if (StringUtil.isEmpty(charactersList_GM)) {
			charactersList_GM = "0";
		}
		return "0";
	}

	public void setCharactersList_GM(String charactersListGM) {
		charactersList_GM = charactersListGM;
	}

	private static RankingManager instance;

	private RankingManager() {
	}

	public static RankingManager getInstance() {
		if (instance == null) {
			instance = new RankingManager();
		}
		return instance;
	}

	public List<CharacterRanking> getCharacterRankingBossList() {
		return characterRankingBossList;
	}

	public void setCharacterRankingBossList(List<CharacterRanking> characterRankingBossList) {
		this.characterRankingBossList = characterRankingBossList;
	}

	public Map<Integer, List<CharacterRanking>> getMapcharacterRankingBossList() {
		return mapCharacterRankingBossList;
	}

	public void setMapcharacterRankingBossList(Map<Integer, List<CharacterRanking>> mapcharacterRankingBossList) {
		this.mapCharacterRankingBossList = mapcharacterRankingBossList;
	}

	public List<CharacterRanking> getLeveList() {
		return leveList;
	}

	public List<CharacterRanking> getCharacterRankingWuGongJingJieList() {
		return characterRankingWuGongJingJieList;
	}

	public void setCharacterRankingWuGongJingJieList(List<CharacterRanking> characterRankingWuGongJingJieList) {
		this.characterRankingWuGongJingJieList = characterRankingWuGongJingJieList;
	}

	public Map<Integer, List<CharacterRanking>> getMapcharacterRankingWuGongJingJieList() {
		return mapCharacterRankingWuGongJingJieList;
	}

	public void setMapcharacterRankingWuGongJingJieList(Map<Integer, List<CharacterRanking>> mapcharacterRankingWuGongJingJieList) {
		this.mapCharacterRankingWuGongJingJieList = mapcharacterRankingWuGongJingJieList;
	}

	public void setLeveList(List<CharacterRanking> leveList) {
		this.leveList = leveList;
	}

	public List<CharacterRanking> getCharacterRankingList() {
		return characterRankingList;
	}

	public void setCharacterRankingList(List<CharacterRanking> characterRankingList) {
		this.characterRankingList = characterRankingList;
	}

	public List<CharacterRanking> getCharacterRankingMoneyList() {
		return characterRankingMoneyList;
	}

	public void setCharacterRankingMoneyList(List<CharacterRanking> characterRankingMoneyList) {
		this.characterRankingMoneyList = characterRankingMoneyList;
	}

	public Map<Integer, List<CharacterRanking>> getMapcharacterRankingMoneyList() {
		return mapCharacterRankingMoneyList;
	}

	public void setMapcharacterRankingMoneyList(Map<Integer, List<CharacterRanking>> mapcharacterRankingMoneyList) {
		this.mapCharacterRankingMoneyList = mapcharacterRankingMoneyList;
	}

	public List<CharacterRanking> getCharacterRankingJingMaiList() {
		return characterRankingJingMaiList;
	}

	public void setCharacterRankingJingMaiList(List<CharacterRanking> characterRankingJingMaiList) {
		this.characterRankingJingMaiList = characterRankingJingMaiList;
	}

	public Map<Integer, List<CharacterRanking>> getMapcharacterRankingJingMaiList() {
		return mapCharacterRankingJingMaiList;
	}

	public void setMapcharacterRankingJingMaiList(Map<Integer, List<CharacterRanking>> mapcharacterRankingJingMaiList) {
		this.mapCharacterRankingJingMaiList = mapcharacterRankingJingMaiList;
	}

	public List<CharacterRanking> getCharacterRankingLianZhanList() {
		return characterRankingLianZhanList;
	}

	public void setCharacterRankingLianZhanList(List<CharacterRanking> characterRankingLianZhanList) {
		this.characterRankingLianZhanList = characterRankingLianZhanList;
	}

	public Map<Integer, List<CharacterRanking>> getMapcharacterRankingLianZhanList() {
		return mapCharacterRankingLianZhanList;
	}

	public void setMapcharacterRankingLianZhanList(Map<Integer, List<CharacterRanking>> mapcharacterRankingLianZhanList) {
		this.mapCharacterRankingLianZhanList = mapcharacterRankingLianZhanList;
	}

	public List<HorseCharacterView> getCharacterRankingMaList() {
		return characterRankingMaList;
	}

	public void setCharacterRankingMaList(List<HorseCharacterView> characterRankingMaList) {
		this.characterRankingMaList = characterRankingMaList;
	}

	public Map<Integer, List<HorseCharacterView>> getMapcharacterRankingMAList() {
		return mapCharacterRankingMAList;
	}

	public void setMapcharacterRankingMAList(Map<Integer, List<HorseCharacterView>> mapcharacterRankingMAList) {
		this.mapCharacterRankingMAList = mapcharacterRankingMAList;
	}

	public List<CharacterRanking> getCharacterRankingZhanChangList() {
		return characterRankingZhanChangList;
	}

	public void setCharacterRankingZhanChangList(List<CharacterRanking> characterRankingZhanChangList) {
		this.characterRankingZhanChangList = characterRankingZhanChangList;
	}

	public Map<Integer, List<CharacterRanking>> getMapcharacterRankingZhanChangList() {
		return mapCharacterRankingZhanChangList;
	}

	public void setMapcharacterRankingZhanChangList(Map<Integer, List<CharacterRanking>> mapcharacterRankingZhanChangList) {
		this.mapCharacterRankingZhanChangList = mapcharacterRankingZhanChangList;
	}

	public void resetBoss() {
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.setBossKill(0);
			}
		});
		// 更新帮会boss击杀数
		FactionManager.getInstance().clearAllFactionBossKill();
		CharacterManager.getInstance().updateCharacterBoss();
	}

	public void resetChengJianLunJian() {
		GameServer.vlineServerManager.runToOnlineCharacter(new CharacterRun() {
			@Override
			public void run(Hero character) {
				character.setLunjianShengWang(0);
				character.setChengzhanShengWang(0);
				character.getMyCharacterAcrossIncomeManager().getAi().setShengwang(0);
			}
		});
		AcrossIncomeManager.getInstance().resetAcrossIncomeShengWang();
		CharacterManager.getInstance().updateCharacterChengZhanAndLunJian();
	}

	public void init() {
		String ksdateString = Timer.getNowTime("yyyy-MM-dd HH:mm:ss");


		characterRankingList = CharacterManager.getInstance().rankingCharacters();
		mapTopTenId.put(rendengjichenghao, getTopRankingTenId(characterRankingList));

		characterRankingMoneyList = CharacterManager.getInstance().rankingsMoney2();
		mapTopTenId.put(renqianchenghao, getTopRankingTenId(characterRankingMoneyList));

		characterRankingJingMaiList = CharacterManager.getInstance().rankingsjingmai();
		mapTopTenId.put(renjingmaichenghao, getTopRankingTenId(characterRankingJingMaiList));

		characterRankingLianZhanList = CharacterManager.getInstance().rankingslianzhan();
		mapTopTenId.put(renlianzhanchenghao, getTopRankingTenId(characterRankingLianZhanList));

		characterRankingMaList = HorseCharacterViewDataProvider.getInstance().selectRanKing();
		mapTopTenId.put(renzuoqichenghao, getTopHorseCharacterViewTen(characterRankingMaList));

		// characterRankingZhanChangList =
		// CharacterManager.getInstance().rankingszhanchang();
		// mapTopTenId.put(renzhanchangchenghao,
		// getTopRankingTenId(characterRankingZhanChangList));

		characterRankingBossList = CharacterManager.getInstance().rankingsboss();
		mapTopTenId.put(renbooschenghao, getTopRankingTenId(characterRankingBossList));

		characterRankingWuGongJingJieList = CharacterManager.getInstance().rankingswuxuejingjie();
		mapTopTenId.put(renwugongjingjiechenghao, getTopRankingTenId(characterRankingWuGongJingJieList));
		characterRankingChengjiuList = CharacterManager.getInstance().rankingchengjiu();
		mapTopTenId.put(renchengjiuchenghao, getTopRankingTenId(characterRankingChengjiuList));

		characterRankingAnQiList = CharacterHiddenWeaponManager.getInstance().getRanKingCharacterHiddenWeaponsList();
		mapTopTenId.put(rencikechenghao, getTopRankingTenId_HiddenWeapon(characterRankingAnQiList));

		characterRankingChengZhan = CharacterManager.getInstance().rankingchengzhan();
		mapTopTenId.put(renchengzhan, getTopRankingTenId(characterRankingChengZhan));

		characterRankingLunJian = CharacterManager.getInstance().rankinglunjian();
		mapTopTenId.put(renlunjian, getTopRankingTenId(characterRankingLunJian));

		int tongjicount = 100;

		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjimoney(i);
			if (leveList.size() > 0) {
				mapCharacterRankingMoneyList.put(i, leveList);
			}
		}
		// 经脉同级
		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjijingmai(i);
			if (leveList.size() > 0) {
				mapCharacterRankingJingMaiList.put(i, leveList);
			}
		}
		// 连斩同级
		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjilianzhan(i);
			if (leveList.size() > 0) {
				mapCharacterRankingLianZhanList.put(i, leveList);
			}
		}
		// 马
		List<HorseModel> horselist = HorseModelDataProvider.getInstance().getHorseModels();
		for (HorseModel horseModel : horselist) {
			List<HorseCharacterView> characterHorselist = HorseCharacterViewDataProvider.getInstance().getRankingmatongji(horseModel.getId());
			if (characterHorselist.size() > 0) {
				mapCharacterRankingMAList.put(horseModel.getId(), characterHorselist);

			}
		}
		// 战场声望
		// for (int i = 1; i < tongjicount; i++) {
		// leveList =
		// CharacterManager.getInstance().getRankingtongjizhanchang(i);
		// if (leveList.size() > 0) {
		// mapcharacterRankingZhanChangList.put(i, leveList);
		// }
		// }
		// boss
		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjibossjisha(i);
			if (leveList.size() > 0) {
				mapCharacterRankingBossList.put(i, leveList);
			}
		}
		// 武功境界
		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjiwuxuejingjie(i);
			if (leveList.size() > 0) {
				mapCharacterRankingWuGongJingJieList.put(i, leveList);
			}
		}

		// 人物成就
		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjiwuxuejingjie(i);
			if (leveList.size() > 0) {
				mapCharacterRankingChengjiu.put(i, leveList);
			}
		}
		// 暗器
		for (int i = 1; i < tongjicount; i++) {
			List<HiddenWeaponDataEntry> characterRankingAnQiList = CharacterHiddenWeaponManager.getInstance().getRanKingCharacterHiddenWeaponsListTongJi(i);
			if (leveList.size() > 0) {
				mapCharacterRankingAnQiList.put(i, characterRankingAnQiList);
			}
		}

		// 城战同级
		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjichengzhan(i);
			if (leveList.size() > 0) {
				mapCharacterRankingChengZhanList.put(i, leveList);
			}
		}

		// 论剑同级
		for (int i = 1; i < tongjicount; i++) {
			leveList = CharacterManager.getInstance().getRankingtongjilunjian(i);
			if (leveList.size() > 0) {
				mapCharacterRankingLunJianList.put(i, leveList);
			}
		}

		rechargeTopTen();
		getOnlineCharacter();
		// 副本排行加载
		List<Integer> fubenrankingDistinctList = FubenrankingManager.getInstance().getFubenRankingDistinct();
		for (Integer fubenid : fubenrankingDistinctList) {
			List<Fubenranking> list = FubenrankingManager.getInstance().getFubenRanking(fubenid);
			mapfubenRanking.put(fubenid, list);
		}
		// 副本排行同级
		for (int i = 1; i < tongjicount; i++) {
			List<Fubenranking> list = FubenrankingManager.getInstance().getFubenRankingTongJi(i);
			mapfubenRankingTongJi.put(i, list);
		}

		try {
			// Timer.getNowTime("yyyy-MM-dd HH:mm:ss")));
			logger.debug("人物排行使用时间:"+Timer.timeJiSuan(ksdateString, Timer.getNowTime("yyyy-MM-dd HH:mm:ss")));
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void rechargeTopTen() {
		for (int id : oldCharacteridHashSet) {
			final Hero character = GameServer.vlineServerManager.getCharacterById(id);
			if (null != character) {
				character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
					@Override
					public void run() {
						character.changeRankingTitle(null);

					}
				});

			}
		}
	}

	public String getTopRankingTenId(List<CharacterRanking> list) {
		StringBuilder sb = new StringBuilder();
		if (list.size() == 0) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			CharacterRanking characterRanking = list.get(i);
			sb.append(characterRanking.getId());
			sb.append(",");
			if (i == 9) {
				break;
			}
		}

		return sb.toString();
	}

	public String getTopHorseCharacterViewTen(List<HorseCharacterView> list) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			HorseCharacterView horseCharacterView = list.get(i);
			sb.append(horseCharacterView.getCharacterId());
			sb.append(",");
			if (i == 9) {
				break;
			}
		}

		return sb.toString();
	}

	public String getTopRankingTenId_HiddenWeapon(List<HiddenWeaponDataEntry> list) {
		StringBuilder sb = new StringBuilder();
		if (list.size() == 0) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			HiddenWeaponDataEntry hiddenWeaponDataEntry = list.get(i);
			sb.append(hiddenWeaponDataEntry.getCharacterId());
			sb.append(",");
			if (i == 9) {
				break;
			}
		}
		return sb.toString();
	}

	public int[] getRankingTitle(int characterid) {

		int rankingTitle[] = null;

		StringBuilder sb = new StringBuilder();
		for (int a : mapTopTenId.keySet()) {
			String str = mapTopTenId.get(a);
			if (StringUtil.isEmpty(str)) {
				continue;
			}
			String idString = String.valueOf(characterid);
			List<String> list = Arrays.asList(str.split(","));
			if (list.contains(idString)) {
				sb.append(a);
				sb.append(",");
			}
		}
		String str = sb.toString();
		if (str.length() > 0) {
			rankingTitle = StringUtil.string_To_int(str.split(","));
			// for (int i : rankingTitle) {
			// }
		}
		return rankingTitle;
	}

	/**
	 * 直接发送排行榜头衔数据
	 * 
	 */
	public void getOnlineCharacter() {
		StringBuilder sb = new StringBuilder();
		// 跨服判断
		if (Options.IsCrossServ) {
			return;
		}

		for (int a : mapTopTenId.keySet()) {
			String str = mapTopTenId.get(a);
			if (StringUtil.isNotEmpty(str)) {
				sb.append(str);
			}
		}
		String str = sb.toString();
		if (StringUtil.isEmpty(str)) {
			return;
		}

		int shu[] = StringUtil.string_To_int(str.split(","));
		HashSet<Integer> result = findSame(shu);
		oldCharacteridHashSet = result;
		for (Integer id : result) {
			final Hero character = GameServer.vlineServerManager.getCharacterById(id);
			if (null != character) {

				character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
					@Override
					public void run() {
						int title[] = getRankingTitle(character.getId());
						character.changeRankingTitle(title);

					}
				});

			}
		}
	}

	public static HashSet<Integer> findSame(int array1[]) {

		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < array1.length; i++) {
			set.add(array1[i]);
		}
		return set;
	}

}
