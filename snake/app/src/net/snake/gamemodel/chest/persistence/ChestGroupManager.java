package net.snake.gamemodel.chest.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.GenerateProbability;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.chest.bean.ChestGroup;
import net.snake.gamemodel.chest.bean.ChestResources;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.ibatis.SystemFactory;
import org.apache.log4j.Logger;

/**
 * 宝箱组别
 * 
 * 
 */

public class ChestGroupManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(ChestGroupManager.class);
	private static ChestGroupDAO dao = new ChestGroupDAO(SystemFactory.getGamedataSqlMapClient());
	private List<ChestGroup> listChestGroup = null;
	private List<ChestResources> listChestResource = null;
	private Map<String, List<ChestResources>> mapChestGroup_to_ChestResources = new HashMap<String, List<ChestResources>>();
	private Map<String, ChestResources> mapChestResources_string = new HashMap<String, ChestResources>();
	private Map<Integer, ChestResources> mapChestResources_int = new HashMap<Integer, ChestResources>();
	private Map<Integer, List<ChestGroup>> mapChestGroup = new HashMap<Integer, List<ChestGroup>>();
	private Map<Integer, Integer> mapChestGroup_type_count = new HashMap<Integer, Integer>();
	private static ChestGroupManager instance;

	private ChestGroupManager() {
	}

	public static ChestGroupManager getInstance() {
		if (instance == null) {
			instance = new ChestGroupManager();
		}
		return instance;
	}

	@Override
	public void reload() {
		Map<String, List<ChestResources>> mapChestGroup_to_ChestResources2 = new HashMap<String, List<ChestResources>>();
		Map<String, ChestResources> mapChestResources_string2 = new HashMap<String, ChestResources>();
		Map<Integer, List<ChestGroup>> mapChestGroup2 = new HashMap<Integer, List<ChestGroup>>();
		Map<Integer, Integer> mapChestGroup_type_count2 = new HashMap<Integer, Integer>();
		reloadMap(mapChestGroup_to_ChestResources2, mapChestResources_string2, mapChestGroup2, mapChestGroup_type_count2);
		mapChestGroup_to_ChestResources = mapChestGroup_to_ChestResources2;
		mapChestResources_string = mapChestResources_string2;
		mapChestGroup = mapChestGroup2;
		mapChestGroup_type_count = mapChestGroup_type_count2;
		logger.info("宝箱数据更新------------------");
	}

	/**
	 * 加载宝箱数据
	 * 
	 * @param mapChestGroup_to_ChestResources2
	 * @param mapChestResources_string2
	 * @param mapChestGroup2
	 * @param mapChestGroup_type_count2
	 */
	@SuppressWarnings("unchecked")
	public void reloadMap(Map<String, List<ChestResources>> mapChestGroup_to_ChestResources2, Map<String, ChestResources> mapChestResources_string2,
			Map<Integer, List<ChestGroup>> mapChestGroup2, Map<Integer, Integer> mapChestGroup_type_count2) {
		// 获得宝箱内容的数据
		Map<Integer, ChestResources> mapChestResources_int2 = new HashMap<Integer, ChestResources>();
		listChestResource = ChestResourcesManager.getInstance().getChestResources();
		Map<Integer, List<ChestResources>> chestressMap = new HashMap<Integer, List<ChestResources>>();
		for (ChestResources chestResources : listChestResource) {
			String id_type = chestResources.getId() + "_" + chestResources.getChesGroupId() + "_" + chestResources.getGoodmodelId();
			mapChestResources_string2.put(id_type, chestResources);
			// 按宝箱组进行分类
			if (chestressMap.containsKey(chestResources.getChesGroupId())) {
				chestressMap.get(chestResources.getChesGroupId()).add(chestResources);
			} else {
				List<ChestResources> listChestResourcetmp = new ArrayList<ChestResources>();
				listChestResourcetmp.add(chestResources);
				chestressMap.put(chestResources.getChesGroupId(), listChestResourcetmp);
			}
		}
		mapChestResources_int2 = BeanTool.listToMap(listChestResource, "id");
		// 获得宝箱组的初始化信息，以便于摇奖
		try {
			listChestGroup = dao.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		for (ChestGroup chestGroup : listChestGroup) {
			String id_type = chestGroup.getId().toString() + "_" + chestGroup.getTypeId().toString() + "_" + chestGroup.getName() + "_" + chestGroup.getCount().toString();
			List<ChestResources> listChestResources = chestressMap.get(chestGroup.getId());
			List<ChestResources> listChestResources_new = new ArrayList<ChestResources>(listChestResources.size());
			for (ChestResources chestResources : listChestResources) {
				Goodmodel goodmodel = GoodmodelManager.getInstance().get(chestResources.getGoodmodelId());
				if (goodmodel == null) {
					logger.info("goomodel数据和宝箱关联错误!!!!!!!:" + chestResources.getGoodmodelId());
					continue;
				}
				chestResources.setGrade(goodmodel.getGrade());
				chestResources.setPopsinger(goodmodel.getPopsinger());
				chestResources.setMa(goodmodel.getKind());
				listChestResources_new.add(chestResources);
			}
			if (mapChestGroup2.containsKey(chestGroup.getTypeId())) {
				mapChestGroup2.get(chestGroup.getTypeId()).add(chestGroup);
			} else {
				List<ChestGroup> listChestGroup = new ArrayList<ChestGroup>();
				listChestGroup.add(chestGroup);
				mapChestGroup2.put(chestGroup.getTypeId(), listChestGroup);
			}
			mapChestGroup_to_ChestResources2.put(id_type, listChestResources_new);
		}
		Iterator<Integer> typeiter = mapChestGroup2.keySet().iterator();
		while (typeiter.hasNext()) {
			int type = typeiter.next();
			mapChestGroup_type_count2.put(type, mapChestGroup2.get(type).size());
		}
		mapChestResources_int = mapChestResources_int2;
	}

	public Map<Integer, Integer> getMapChestGroup_type_count() {
		return mapChestGroup_type_count;
	}

	public Map<Integer, List<ChestGroup>> getMapChestGroup() {
		return mapChestGroup;
	}

	public List<ChestResources> getListChestResource() {
		return listChestResource;
	}

	public Map<String, ChestResources> getMapChestResources_string() {
		return mapChestResources_string;
	}

	public Map<Integer, ChestResources> getMapChestResources_int() {
		return mapChestResources_int;
	}

	public Map<String, List<ChestResources>> getMapChestGroup_to_ChestResources() {
		return mapChestGroup_to_ChestResources;
	}

	public List<ChestGroup> getListChestGroup() {
		return listChestGroup;
	}

	public void initdate() {
		// 获得宝箱内容的数据
		reloadMap(mapChestGroup_to_ChestResources, mapChestResources_string, mapChestGroup, mapChestGroup_type_count);
	}

	/**
	 * 根据宝箱id返回索引
	 * 
	 * @param chestResourceid
	 * @param position
	 * @return
	 */
	public int getPosition_To_ChestResourceid(String chestResourceid, String position) {
		int a = 0;
		String str[] = position.split(",");
		for (String string : str) {
			if (chestResourceid.equals(string.substring(0, string.indexOf("#")))) {
				String str2 = string.substring(string.indexOf("#") + 1);
				a = Integer.valueOf(str2);
				break;
			}
		}
		return a;
	}

	/**
	 * 根据索引返回宝箱id
	 * 
	 * @param chestResourceid
	 * @param position
	 * @return
	 */
	public String getChestResourceid_To_Position(Integer positionid, String position) {
		String idString = "";
		String str[] = position.split(",");
		for (String string : str) {
			if (positionid.toString().equals(string.substring(string.indexOf("#") + 1))) {
				idString = string.substring(0, string.indexOf("#"));
				break;

			}

		}
		return idString;
	}

	/**
	 * 根据索引返回宝箱类别
	 * 
	 * @param position
	 * @return
	 */
	public int getChest_type(int position) {
		int a = 0;
		if (position >= 4000 && position <= 4019) {
			a = GoodItemId.tianjisuo;
		}
		if (position >= 4020 && position <= 4039) {
			a = GoodItemId.kongmingsuo;
		}
		if (position >= 4040 && position <= 4059) {
			a = GoodItemId.chengjiulihe;
		}
		if (position >= 4060 && position <= 4079) {
			a = GoodItemId.putongbaozang;
		}
		if (position >= 4080 && position <= 4099) {
			a = GoodItemId.zhenqibaozhang;
		}
		if (position >= 4100 && position <= 4119) {
			a = GoodItemId.lansongguo;
		}
		if (position >= 4120 && position <= 4139) {
			a = GoodItemId.lvsongguo;
		}
		if (position >= 4140 && position <= 4159) {
			a = GoodItemId.zisongguo;
		}
		if (position >= 4160 && position <= 4179) {
			a = GoodItemId.jinsongguo3011;
		}
		if (position >= 4180 && position <= 4199) {
			a = GoodItemId.jinsongguo3012;
		}
		if (position >= 4200 && position <= 4219) {
			a = GoodItemId.jinsongguo3013;
		}
		if (position >= 4220 && position <= 4239) {
			a = GoodItemId.jinsongguo3014;
		}
		if (position >= 4240 && position <= 4259) {
			a = GoodItemId.jinsongguo3015;
		}
		return a;
	}

	public StringBuilder getChestPostitionStartToStop(String start, String stop, StringBuilder sb) {
		sb.append(start);
		sb.append("#");
		sb.append(stop);
		sb.append(",");
		return sb;
	}

	/**
	 * @param chestGroupTypeId
	 * @param chestList
	 * @return 返回宝相id对应的索引位置
	 */
	public String getChestListToPosition(int chestGroupTypeId, String chestList) {
		StringBuilder sb2 = new StringBuilder();
		String pString[] = chestList.toString().split(",");

		// 3001,3002,7004,9001,9002,3100,3200,3300
		// 3011,3012,3013,3014,3015 金松果
		int[] typecount = { 4000, 4020, 4040, 4060, 4080, 4100, 4120, 4140, 4160, 4180, 4200, 4220, 4240 };

		for (int a = 0; a < pString.length; a++) {
			// 宝箱奖品开始与结束位置

			// 4000--4019 3002
			if (chestGroupTypeId == GoodItemId.tianjisuo) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[0] + a), sb2);
				// 4020--4039 3001
			} else if (chestGroupTypeId == GoodItemId.kongmingsuo) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[1] + a), sb2);
				// 4040--4059 7004
			} else if (chestGroupTypeId == GoodItemId.chengjiulihe) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[2] + a), sb2);
				// 4060--4079 9001
			} else if (chestGroupTypeId == GoodItemId.putongbaozang) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[3] + a), sb2);
				// 4080--4099 9002
			} else if (chestGroupTypeId == GoodItemId.zhenqibaozhang) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[4] + a), sb2);
				// 4100--4119 3100
			} else if (chestGroupTypeId == GoodItemId.lansongguo) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[5] + a), sb2);
				// 4120--4139 3200
			} else if (chestGroupTypeId == GoodItemId.lvsongguo) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[6] + a), sb2);
				// 4140--4159 3300
			} else if (chestGroupTypeId == GoodItemId.zisongguo) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[7] + a), sb2);
				// 4160--4179 3011
			} else if (chestGroupTypeId == GoodItemId.jinsongguo3011) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[8] + a), sb2);
				// 4180--4199 3012
			} else if (chestGroupTypeId == GoodItemId.jinsongguo3012) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[9] + a), sb2);
				// 4200--4219 3013
			} else if (chestGroupTypeId == GoodItemId.jinsongguo3013) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[10] + a), sb2);
				// 4220--4239 3014
			} else if (chestGroupTypeId == GoodItemId.jinsongguo3014) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[11] + a), sb2);
				// 4240--4259 3015
			} else if (chestGroupTypeId == GoodItemId.jinsongguo3015) {
				getChestPostitionStartToStop(pString[a], String.valueOf(typecount[12] + a), sb2);
			}
		}
		return sb2.toString();
	}

	/**
	 * 过滤重复的
	 * 
	 * @param chestResources
	 * @param sb
	 * @return
	 */
	public boolean RepeatFiltering(ChestResources chestResources, StringBuilder sb) {
		boolean type = true;
		if (sb.indexOf(chestResources.getId() + "_" + chestResources.getChesGroupId() + "_" + chestResources.getGoodmodelId()) == -1) {
			sb.append(chestResources.getId() + "_" + chestResources.getChesGroupId() + "_" + chestResources.getGoodmodelId());
			sb.append(",");
		}
		return type;

	}

	/**
	 * @param typeId
	 * @return 返回秘籍奖池
	 */
	public String getChestListMiJi(int typeId) {
		StringBuilder sb = new StringBuilder();
		List<ChestGroup> listChestGroup = new ArrayList<ChestGroup>(mapChestGroup.get(typeId));
		int count = mapChestGroup_type_count.get(typeId);
		if (count == 0) {
			return null;
		}
		ChestGroup chestGroup_beiyong = null;
		if (listChestGroup.size() > 1) {
			chestGroup_beiyong = listChestGroup.get(count - 1);
			listChestGroup.remove(count - 1);
		}

		int count2 = 0;
		for (ChestGroup chestGroup : listChestGroup) {
			String id_type = chestGroup.getId().toString() + "_" + chestGroup.getTypeId().toString() + "_" + chestGroup.getName() + "_" + chestGroup.getCount().toString();
			if (GenerateProbability.isGenerateToBoolean(chestGroup.getProbability(), 10000)) {
				List<ChestResources> listchChestResource = new ArrayList<ChestResources>(mapChestGroup_to_ChestResources.get(id_type));
				for (int i = 0; i < chestGroup.getCount(); i++) {
					if (listchChestResource.size() == 0) {
						break;
					}
					if (listchChestResource.size() == 1) {
						ChestResources chestResources = listchChestResource.get(0);
						RepeatFiltering(chestResources, sb);
						listchChestResource.remove(0);
					} else {
						int b = GenerateProbability.randomIntValue(0, listchChestResource.size() - 1);
						ChestResources chestResources = listchChestResource.get(b);
						RepeatFiltering(chestResources, sb);
						listchChestResource.remove(b);
					}
					count2 = count2 + 1;
					if (count2 == 16) {
						break;
					}
				}
			}

			if (count2 == 16) {
				break;
			}

		}

		String chest_list[] = sb.toString().split(",");
		if (chest_list.length < 16) {
			int a = 16 - chest_list.length;
			String id_type = chestGroup_beiyong.getId().toString() + "_" + chestGroup_beiyong.getTypeId().toString() + "_" + chestGroup_beiyong.getName() + "_"
					+ chestGroup_beiyong.getCount().toString();
			List<ChestResources> listchChestResource = new ArrayList<ChestResources>(mapChestGroup_to_ChestResources.get(id_type));

			for (int i = 0; i < a; i++) {
				if (listchChestResource.size() == 1) {
					ChestResources chestResources = listchChestResource.get(0);
					RepeatFiltering(chestResources, sb);
					listchChestResource.remove(0);
				} else {
					int b = GenerateProbability.randomIntValue(0, listchChestResource.size() - 1);
					ChestResources chestResources = listchChestResource.get(b);
					RepeatFiltering(chestResources, sb);
					listchChestResource.remove(b);
				}
				String str = sb.toString();
				if (str.length() < 16) {
					a = a + 1;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 根据对应物品，等级，门派
	 * 
	 * @param typeId
	 * @param limitGrade
	 * @param popsinger
	 * @return 返回奖池数据
	 */
	public String getChestList(int typeId, int limitGrade, int popsinger) {
		StringBuilder sb = new StringBuilder();
		List<ChestGroup> listChestGroup = new ArrayList<ChestGroup>(mapChestGroup.get(typeId));
		int count = mapChestGroup_type_count.get(typeId);
		if (count == 0) {
			return null;
		}
		ChestGroup chestGroup_beiyong = null;
		if (listChestGroup.size() > 1) {
			chestGroup_beiyong = listChestGroup.get(count - 1);
			listChestGroup.remove(count - 1);
		}

		int count2 = 0;
		for (ChestGroup chestGroup : listChestGroup) {
			String id_type = chestGroup.getId().toString() + "_" + chestGroup.getTypeId().toString() + "_" + chestGroup.getName() + "_" + chestGroup.getCount().toString();
			if (GenerateProbability.isGenerateToBoolean(chestGroup.getProbability(), 10000)) {
				List<ChestResources> listchChestResource = new ArrayList<ChestResources>(mapChestGroup_to_ChestResources.get(id_type));
				listchChestResource = getLimitGrade(listchChestResource, limitGrade);
				listchChestResource = getPopsinger(listchChestResource, popsinger);

				for (int i = 0; i < chestGroup.getCount(); i++) {
					if (listchChestResource.size() == 0) {
						break;
					}
					if (listchChestResource.size() == 1) {
						ChestResources chestResources = listchChestResource.get(0);
						RepeatFiltering(chestResources, sb);
						listchChestResource.remove(0);
					} else {
						int b = GenerateProbability.randomIntValue(0, listchChestResource.size() - 1);
						ChestResources chestResources = listchChestResource.get(b);
						RepeatFiltering(chestResources, sb);
						listchChestResource.remove(b);

					}
					count2 = count2 + 1;
					if (count2 == 16) {
						break;
					}
				}
			}

			if (count2 == 16) {
				break;
			}

		}

		String chest_list[] = sb.toString().split(",");
		if (chest_list.length < 16) {
			int a = 16 - chest_list.length;
			String id_type = chestGroup_beiyong.getId().toString() + "_" + chestGroup_beiyong.getTypeId().toString() + "_" + chestGroup_beiyong.getName() + "_"
					+ chestGroup_beiyong.getCount().toString();
			List<ChestResources> listchChestResource = new ArrayList<ChestResources>(mapChestGroup_to_ChestResources.get(id_type));

			listchChestResource = getLimitGrade(listchChestResource, limitGrade);
			listchChestResource = getPopsinger(listchChestResource, popsinger);

			for (int i = 0; i < a; i++) {
				if (listchChestResource.size() == 1) {
					ChestResources chestResources = listchChestResource.get(0);
					RepeatFiltering(chestResources, sb);
					listchChestResource.remove(0);
				} else {
					int b = GenerateProbability.randomIntValue(0, listchChestResource.size() - 1);
					ChestResources chestResources = listchChestResource.get(b);
					RepeatFiltering(chestResources, sb);
					listchChestResource.remove(b);
				}
				String str = sb.toString();
				if (str.length() < 16) {
					a = a + 1;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 过滤门派
	 * 
	 * @param listchChestResource
	 * @param f_popsinger
	 * @return
	 */
	public List<ChestResources> getPopsinger(List<ChestResources> listchChestResource, int f_popsinger) {

		for (int a = listchChestResource.size(); a > 0; a--) {
			ChestResources chestResources = listchChestResource.get(a - 1);
			if (chestResources.getPopsinger() == 0 || chestResources.getPopsinger() == f_popsinger) {
				continue;
			}
			listchChestResource.remove(a - 1);
		}
		return listchChestResource;
	}

	/**
	 * 等级过滤
	 * 
	 * @param listchChestResource
	 * @param limitGrade
	 * @return
	 */
	public List<ChestResources> getLimitGrade(List<ChestResources> listchChestResource, int limitGrade) {
		int grade = jiSuanBinJie(limitGrade);
		for (int a = listchChestResource.size(); a > 0; a--) {
			ChestResources chestResources = listchChestResource.get(a - 1);
			// 12表示的是马不用等级验证
			// logger.debug("过滤条件grade(品级)="+chestResources.getGrade()+"是不是马或者宝石（马是12 宝石6）"+chestResources.getModel());
			if (chestResources.getGrade() != 0 && chestResources.getModel() != 12 && chestResources.getModel() != 6) {
				switch (grade) {
				case 1:
					if (chestResources.getGrade() != 1) {
						listchChestResource.remove(a - 1);
					}
					break;
				case 2:
					if (chestResources.getGrade() != 2) {
						listchChestResource.remove(a - 1);
					}
					break;
				case 3:
					if (chestResources.getGrade() != 3) {
						listchChestResource.remove(a - 1);
					}
					break;
				case 4:
					if (chestResources.getGrade() != 4) {
						listchChestResource.remove(a - 1);
					}
					break;
				case 5:
					if (chestResources.getGrade() != 5) {
						listchChestResource.remove(a - 1);
					}
					break;
				case 6:
					if (chestResources.getGrade() != 6) {
						listchChestResource.remove(a - 1);
					}
					break;

				default:
					break;
				}
			}
		}
		return listchChestResource;
	}

	public int jiSuanBinJie(int limitGrade) {
		// 人物等级处于1-15级之间则开出“1级佩戴限制的装备” 1
		// 人物等级处于15-30级之间则开出“20级佩戴限制的装备”2
		// 人物等级处于31-54级之间则开出“40级佩戴限制的装备”3
		// 人物等级处于55-77级之间则开出“60级佩戴限制的装备”4
		// 人物等级处于78-98级之间则开出“80级佩戴限制的装备”5
		// 人物等级大于99级则开出“100级佩戴限制的装备”6
		int a = 0;
		if (limitGrade <= 15) {
			a = 1;
		}
		if (limitGrade <= 30 && limitGrade > 15) {
			a = 2;
		}
		if (limitGrade <= 54 && limitGrade > 30) {
			a = 3;
		}
		if (limitGrade <= 77 && limitGrade > 54) {
			a = 4;
		}
		if (limitGrade <= 98 && limitGrade > 77) {
			a = 5;
		}
		if (limitGrade >= 99) {
			a = 6;
		}
		return a;
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "chest";
	}
}
