package net.snake.gamemodel.chest.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.commons.BeanTool;
import net.snake.commons.Language;
import net.snake.commons.StringUtil;
import net.snake.commons.GenerateProbability;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.bean.ChestCount;
import net.snake.gamemodel.chest.bean.ChestGoods;
import net.snake.gamemodel.chest.bean.ChestResources;
import net.snake.gamemodel.chest.persistence.ChestCountManager;
import net.snake.gamemodel.chest.persistence.ChestGoodsManager;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.chest.persistence.ChestManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.shell.Options;


public class MyChestManager {
	private static Logger logger = Logger.getLogger(MyChestManager.class);
	private List<Chest> chestlist;
	private List<ChestGoods> chestGoodslist;
	private Map<String, ChestGoods> mapChestGoods = new HashMap<String, ChestGoods>();
	private Map<Integer, Map<String, ChestGoods>> mapChestTypeMap = new HashMap<Integer, Map<String, ChestGoods>>();
	private ChestCount chestCount;

	public void destroy() {
		if (chestlist != null) {
			chestlist.clear();
		}
		if (chestGoodslist != null) {
			chestGoodslist.clear();
		}
		if (mapChestGoods != null) {
			mapChestGoods.clear();
		}
	}

	public int getCount3011() {
		return chestCount.getChesttypeReceive3011().intValue() - chestCount.getChesttypeReceiveTrue3011().intValue();
	}

	public int getCount3012() {
		return chestCount.getChesttypeReceive3012().intValue() - chestCount.getChesttypeReceiveTrue3012().intValue();
	}

	public int getCount3013() {
		return chestCount.getChesttypeReceive3013().intValue() - chestCount.getChesttypeReceiveTrue3013().intValue();
	}

	public int getCount3014() {
		return chestCount.getChesttypeReceive3014().intValue() - chestCount.getChesttypeReceiveTrue3014().intValue();
	}

	public int getCount3015() {
		return chestCount.getChesttypeReceive3015().intValue() - chestCount.getChesttypeReceiveTrue3015().intValue();
	}

	public int getCountJinSongGuo() {
		int jinsongguo3011 = chestCount.getChesttypeReceive3011().intValue() - chestCount.getChesttypeReceiveTrue3011().intValue();
		int jinsongguo3012 = chestCount.getChesttypeReceive3012().intValue() - chestCount.getChesttypeReceiveTrue3012().intValue();
		int jinsongguo3013 = chestCount.getChesttypeReceive3013().intValue() - chestCount.getChesttypeReceiveTrue3013().intValue();
		int jinsongguo3014 = chestCount.getChesttypeReceive3014().intValue() - chestCount.getChesttypeReceiveTrue3014().intValue();
		int jinsongguo3015 = chestCount.getChesttypeReceive3015().intValue() - chestCount.getChesttypeReceiveTrue3015().intValue();
		int count = jinsongguo3011 + jinsongguo3012 + jinsongguo3013 + jinsongguo3014 + jinsongguo3015;
		return count;
	}

	public boolean getYanZhengJinSongGuo() {
		boolean type = false;
		int count = getCountJinSongGuo();
		if (count > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60013));
		} else {
			reSetChestCount();
			type = true;
		}

		return type;
	}

	public void reSetChestCount() {
		chestCount.setChesttypeExchangeCount(0);
		chestCount.setChesttypeReceive3011(0);
		chestCount.setChesttypeReceiveTrue3011(0);
		chestCount.setChesttypeReceive3012(0);
		chestCount.setChesttypeReceiveTrue3012(0);
		chestCount.setChesttypeReceive3013(0);
		chestCount.setChesttypeReceiveTrue3013(0);
		chestCount.setChesttypeReceive3014(0);
		chestCount.setChesttypeReceiveTrue3014(0);
		chestCount.setChesttypeReceive3015(0);
		chestCount.setChesttypeReceiveTrue3015(0);
	}

	/**
	 * 宝箱的统计
	 * 
	 * @return
	 */
	public ChestCount getChestCount() {
		return chestCount;
	}

	public void setChestCount(ChestCount chestCount) {
		this.chestCount = chestCount;
	}

	/**
	 * 返回的是中奖的哪类的宝箱数据
	 * 
	 * @return
	 */
	public Map<Integer, Map<String, ChestGoods>> getMapChestTypeMap() {
		return mapChestTypeMap;
	}

	public void setMapChestTypeMap(Map<Integer, Map<String, ChestGoods>> mapChestTypeMap) {
		this.mapChestTypeMap = mapChestTypeMap;
	}

	/**
	 * 返回当前人物上次有没有领的宝箱数据
	 * 
	 * @return
	 */
	public List<ChestGoods> getChestGoodslist() {
		return chestGoodslist;
	}

	public void setChestGoodslist(List<ChestGoods> chestGoodslist) {
		this.chestGoodslist = chestGoodslist;
	}

	/**
	 * 返回保存角色所开出的宝箱数据
	 * 
	 * @return
	 */
	public Map<String, ChestGoods> getMapChestGoods() {
		return mapChestGoods;
	}

	public void setMapChestGoods(Map<String, ChestGoods> mapChestGoods) {
		this.mapChestGoods = mapChestGoods;
	}

	public List<Chest> getChestlist() {
		return chestlist;
	}

	public void setChestlist(List<Chest> chestlist) {
		this.chestlist = chestlist;
	}

	private Hero character;

	public MyChestManager(Hero character) {
		this.character = character;
	}

	public void initChestCount() {

		// 跨服判断
		if (Options.IsCrossServ) {
			return;
		}

		// 加载人物果子统计

		try {
			ChestCount chestCount = ChestCountManager.getInstance().selectChsetCount(character.getId());
			if (null == chestCount) {
				chestCount = new ChestCount();
				chestCount.setCharacterId(character.getId());
				chestCount.setChesttype3100(0);
				chestCount.setChesttype3200(0);
				chestCount.setChesttype3300(0);
				chestCount.setChesttypeExchangeCount(0);
				chestCount.setChesttypeReceive3011(0);
				chestCount.setChesttypeReceive3012(0);
				chestCount.setChesttypeReceive3014(0);
				chestCount.setChesttypeReceive3013(0);
				chestCount.setChesttypeReceive3015(0);
				chestCount.setChesttypeReceiveTrue3011(0);
				chestCount.setChesttypeReceiveTrue3013(0);
				chestCount.setChesttypeReceiveTrue3012(0);
				chestCount.setChesttypeReceiveTrue3014(0);
				chestCount.setChesttypeReceiveTrue3015(0);
				chestCount.setChestUseCount(0);
				ChestCountManager.getInstance().addChsetCount(chestCount);
				this.chestCount = chestCount;
			} else {
				this.chestCount = chestCount;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public void initData() {
		try {
			if (Options.IsCrossServ) {
				return;
			}
			chestlist = ChestManager.getInstance().getChestType_0(character.getId());
			chestGoodslist = ChestGoodsManager.getInstance().getChestGoods(character.getId());
			if (chestGoodslist.size() > 8) {
				int a = 0;
				// 清理因数据变动为不能领取的数据，不影响角色宝箱
				Integer[] jiangchi = { 3001, 3002, 7004, 9001, 9002, 3100, 3200, 3300, 7005, 3011, 3012, 3013, 3014, 3015 };
				for (Integer chestid : jiangchi) {
					for (int i = chestGoodslist.size(); i > 0; i--) {
						ChestGoods chestGoods = chestGoodslist.get(i - 1);
						if (chestGoods.getGoodmodelType().intValue() == chestid) {
							a = a + 1;
							if (a > 1) {
								ChestGoodsManager.getInstance().delChsetGoods(chestGoods);
								chestGoodslist.remove(chestGoods);
								break;
							}
						}
					}
				}
			}
			// 上次未领取的装备计算品级
			for (ChestGoods chestGoods : chestGoodslist) {
				String goos[] = chestGoods.getAdditionDesc().split(";");
				chestGoods.setPingzhi(getPingzhiColor(Integer.valueOf(goos.length)));

			}

			mapChestGoods = BeanTool.listToMap(chestGoodslist, "chestResourcesId");
			for (Chest chest : chestlist) {
				for (String string : chest.getChestList().split(",")) {
					ChestGoods chestGoods = mapChestGoods.get(string);
					if (chestGoods == null) {
						CharacterGoods characterGoods = CharacterGoods.createCharacterGoods_ChestResourcesById(string);
						if (null == characterGoods) {
							continue;
						}
						chestGoods = ChestGoodsManager.getInstance().characterGoodsTOChestGoods(characterGoods);
						mapChestGoods.put(string, chestGoods);
					}
				}
				mapChestTypeMap.put(chest.getGoodmodelType(), mapChestGoods);
			}
			List<Chest> list = ChestManager.getInstance().getChestType_1(character.getId());
			for (Chest chest : list) {
				// chest.setChestList("");
				chest.setPosition("");
				ChestManager.getInstance().updateChest(chest);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void save() {

		if (Options.IsCrossServ) {
			return;
		}

		for (Chest chest : chestlist) {
			if (chest.getType() == 0) {
				Map<String, ChestGoods> mapChestGoods = mapChestTypeMap.get(chest.getGoodmodelType());
				ChestGoods chestGoods = mapChestGoods.get(chest.getChestResourcesId());
				if (null == chestGoods) {
					continue;
				}

				chestGoods.setCharacterId(character.getId());
				chestGoods.setChestResourcesId(chest.getChestResourcesId());
				chestGoods.setGoodmodelType(chest.getGoodmodelType());
				if (chestGoodslist.size() == 0) {
					ChestGoodsManager.getInstance().addChsetGoods(chestGoods);
				} else {
					for (ChestGoods chestGoods2 : chestGoodslist) {
						if (chestGoods2.getChestResourcesId().equals(chestGoods.getChestResourcesId())
								&& chestGoods2.getGoodmodelType().intValue() == chestGoods.getGoodmodelType().intValue()) {
							// 已经记录数据
							break;
						} else {
							// 没有记录数据
							if (StringUtil.isEmpty(chestGoods.getChestGoodsId())) {
								ChestGoodsManager.getInstance().addChsetGoods(chestGoods);
							}
							break;
						}
					}
				}

			}
		}
		for (Chest chest : chestlist) {
			if (chest.getId() == null) {
				ChestManager.getInstance().addChest(chest);
			}
		}

		ChestCountManager.getInstance().UpdateChsetCount(chestCount);
	}

	/**
	 * 通过宝箱别类返回宝箱数据
	 * 
	 * @param goodmodelType
	 * @return
	 */
	public Chest getChest(int goodmodelType) {
		Chest chest2 = null;
		List<Chest> list = getChestlist();
		for (int a = list.size(); a > 0; a--) {
			Chest chest = list.get(a - 1);
			if (chest.getGoodmodelType() == goodmodelType && chest.getType() == 0) {
				chest2 = chest;
				break;
			}
		}
		return chest2;
	}

	/**
	 * @param ChestGroup_type_id
	 *            宝箱组别
	 * @param f_limit_grade
	 *            等级
	 * @param f_popsinger
	 *            门派
	 * @param goodmodelType
	 *            宝箱类别
	 * @return
	 */
	public Chest getRepeatChest_list_MiJi(int ChestGroup_type_id, int goodmodelType) {
		String cheast_list = ChestGroupManager.getInstance().getChestListMiJi(ChestGroup_type_id);

		Chest chest = new Chest();
		chest.setChestList(cheast_list);
		chest.setCharacterId(character.getId());
		chest.setGoodmodelType(goodmodelType);
		// 摇奖---------------------------------------开始
		String chestlist2[] = cheast_list.split(",");
		// int a = GenerateProbability.randomIntValue(0, 15);
		List<ChestResources> jiangpingChestResources = new ArrayList<ChestResources>(chestlist2.length);
		for (String chestresourceid : chestlist2) {
			ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_int().get(Integer.valueOf(chestresourceid.split("_")[0]));
			chestResources.setZhongjiangidString(chestresourceid);
			jiangpingChestResources.add(chestResources);
		}
		Collections.shuffle(jiangpingChestResources);
		for (ChestResources chestResources : jiangpingChestResources) {
			if (GenerateProbability.isGenerateToBoolean(chestResources.getProbabilityType(), 10000)) {
				chest.setChestResourcesId(chestResources.getZhongjiangidString());
				break;
			}
		}
		if (StringUtil.isEmpty(chest.getChestResourcesId())) {
			String idString = "";
			int a = 0;
			for (ChestResources chestResources : jiangpingChestResources) {
				if (chestResources.getProbabilityType().intValue() > a) {
					a = chestResources.getProbabilityType().intValue();
					idString = chestResources.getZhongjiangidString();
				}
			}
			chest.setChestResourcesId(idString);
		}
		// --------------------------------------结束

		return chest;
	}

	/**
	 * @param chestGroupTypeId
	 *            宝箱组别
	 * @param limitGrade
	 *            等级
	 * @param popsinger
	 *            门派
	 * @param goodmodelType
	 *            宝箱类别
	 * @return
	 */
	public Chest getRepeatChestList(int chestGroupTypeId, int limitGrade, int popsinger, int goodmodelType) {
		String cheast_list = ChestGroupManager.getInstance().getChestList(chestGroupTypeId, limitGrade, popsinger);

		String chest_list_Position = ChestGroupManager.getInstance().getChestListToPosition(goodmodelType, cheast_list);
		Chest chest = new Chest();
		chest.setChestList(cheast_list);
		chest.setCharacterId(character.getId());
		chest.setGoodmodelType(goodmodelType);
		chest.setType(CommonUseNumber.byte0);
		chest.setTurn(CommonUseNumber.byte0);
		chest.setPosition(chest_list_Position);
		// 摇奖---------------------------------------开始
		String chestlist2[] = cheast_list.split(",");
		// int a = GenerateProbability.randomIntValue(0, 15);
		List<ChestResources> jiangpingChestResources = new ArrayList<ChestResources>(chestlist2.length);
		for (String chestresourceid : chestlist2) {
			ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_int().get(Integer.valueOf(chestresourceid.split("_")[0]));
			chestResources.setZhongjiangidString(chestresourceid);
			jiangpingChestResources.add(chestResources);
		}
		Collections.shuffle(jiangpingChestResources);
		for (ChestResources chestResources : jiangpingChestResources) {
			if (GenerateProbability.isGenerateToBoolean(chestResources.getProbabilityType(), 10000)) {
				chest.setChestResourcesId(chestResources.getZhongjiangidString());
				break;
			}
		}
		if (StringUtil.isEmpty(chest.getChestResourcesId())) {
			String idString = "";
			int a = 0;
			for (ChestResources chestResources : jiangpingChestResources) {
				if (chestResources.getProbabilityType().intValue() > a) {
					a = chestResources.getProbabilityType().intValue();
					idString = chestResources.getZhongjiangidString();
				}
			}
			chest.setChestResourcesId(idString);
		}
		ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_string().get(chest.getChestResourcesId());

		chest.setBinding(chestResources.getBinding());
		chest.setFailTime(chestResources.getFailTime());
		chest.setFullServiceAnnouncement(chestResources.getFullServiceAnnouncement());
		chest.setQuantity(chestResources.getQuantity());
		// --------------------------------------结束
		chestlist.add(chest);
		Map<String, ChestGoods> mapChestGoods2 = new HashMap<String, ChestGoods>();
		for (Chest chest2 : chestlist) {
			if (chest2.getType() == 1) {
				continue;
			}
			for (String string : chest2.getChestList().split(",")) {

				ChestGoods chestGoods = mapChestGoods.get(string);
				if (chestGoods == null) {
					CharacterGoods characterGoods = CharacterGoods.createCharacterGoods_ChestResourcesById(string);
					if (null == characterGoods) {
						continue;
					}
					chestGoods = ChestGoodsManager.getInstance().characterGoodsTOChestGoods(characterGoods);
					mapChestGoods2.put(string, chestGoods);
				} else {
					mapChestGoods2.put(string, chestGoods);
				}
			}
			mapChestTypeMap.put(chest2.getGoodmodelType(), mapChestGoods2);
			setMapChestTypeMap(mapChestTypeMap);
		}
		setMapChestGoods(mapChestGoods2);

		return chest;
	}

	public void updateChest(final Chest chest) {
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				ChestManager.getInstance().updateChest(chest);

			}
		});

	}

	/**
	 * @param chestResources2
	 *            奖品数据
	 * @param typegoods
	 *            那类的goods
	 * @param jiangpinggoods
	 *            奖品goods
	 */
	public void gongGaoMiJi(ChestResources chestResources2, CharacterGoods typegoods, CharacterGoods jiangpinggoods) {
		// Goodmodel goodmodel =
		// GoodmodelManager.getInstance().get(chestResources2.getGoodmodelId());

		// 恭喜【XXX】玩家通过充值有礼活动，打开了【秘籍福袋】获得了完整秘籍【XXX】，他的运气真不错
		//
		GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST, 992, character.getViewName(), jiangpinggoods.getGoodModel()
				.getNameI18n()));
	}

	/**
	 * @param goodid
	 *            goodmodel id
	 * @param typegoods
	 *            那类的goods
	 * @param jiangpinggoods
	 *            奖品goods
	 * 
	 */
	public void gongGao(int goodid, CharacterGoods typegoods, CharacterGoods jiangpinggoods, Chest chest) {
		Goodmodel goodmodel = GoodmodelManager.getInstance().get(goodid);

		if (goodmodel.getKind() != 2) {
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.QICHU_MSG, 990, character.getViewName(), goodmodel.getNameI18n()));
		}
		if (goodmodel.getKind() == 2) {
			int xingxingshu = jiangpinggoods.getStrengthenNum();
			jiangpinggoods.equipmentUpdate();
			String pinZhiString = getYanSe(jiangpinggoods.getPingzhiColor());
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PrompMsg(TipMsg.QICHU_MSG, 991, character.getViewName(), typegoods.getGoodModel().getNameI18n(), xingxingshu
					+ "", pinZhiString + "", goodmodel.getNameI18n()));
			if (logger.isDebugEnabled()) {
				logger.debug("广播当前装备品质" + jiangpinggoods.getPingzhi() + "颜色" + pinZhiString);
			}

		}
	}

	public void setChestLog(int goodid, CharacterGoods typegoods, CharacterGoods jiangpinggoods, Chest chest) {
		Goodmodel goodmodel = GoodmodelManager.getInstance().get(goodid);
		if (goodmodel.getKind() == 2) {
			int xingxingshu = jiangpinggoods.getStrengthenNum();
			jiangpinggoods.equipmentUpdate();
			String pinZhiString = getYanSe(jiangpinggoods.getPingzhiColor());
			chest.setPinzhi("星数" + xingxingshu + "颜色" + pinZhiString);
			chest.setCharactergoodUuid(jiangpinggoods.getId());
		}
	}

	public int getPingzhiColor(int pingzhi) {
		switch (pingzhi) {
		case 0:
		case 1:
			return 1;
		case 2:
		case 3:
			return 2;
		case 4:
		case 5:
			return 3;
		case 6:
			return 4;
		default:
			break;
		}

		return 0;
	}

	public String getYanSe(int type) {
		// 1白色，2蓝色，3绿色，4紫色
		String str = "";
		switch (type) {
		case 1:
			str = Language.WHITE_EQUIP;
			break;
		case 2:
			str = Language.BLUE_EQUIP;
			break;
		case 3:
			str = Language.GREEN_EQUIP;
			break;
		case 4:
			str = Language.PUTPLE_EQUIP;
			break;
		default:
			str = Language.WHITE_EQUIP;
			break;
		}
		return str;
	}

}
