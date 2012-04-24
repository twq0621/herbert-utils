package net.snake.gamemodel.goods.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.EffectType;
import net.snake.consts.SkillId;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.goods.bean.DrugComparator;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.action.UserGoodActionManager;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 物品模型表管理
 * 
 * @author benchild
 */

public class GoodmodelManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(GoodmodelManager.class);
	// 单例实现=====================================
	private static GoodmodelManager instance;
	private GoodmodelDAO goodmodelDAO = new GoodmodelDAO(SystemFactory.getGamedataSqlMapClient());

	private List<Goodmodel> hpDrugList;// 从大到小排列药片恢复值
	private List<Goodmodel> mpDrugList;// 从大到小排列药片恢复值
	private List<Goodmodel> spDrugList;// 从大到小排列药片恢复值
	private List<Goodmodel> hpDrugBuffList;// 从大到小排列hpBuff恢复值
	private List<Goodmodel> mpDrugBuffList;// 从大到小排列mpBuff恢复值
	private Map<Integer, List<Goodmodel>> gradeEquite;;

	public static GoodmodelManager getInstance() {
		if (instance == null) {
			instance = new GoodmodelManager();
		}
		return instance;
	}

	private GoodmodelManager() {
	}

	// 单例实现========================================
	private Map<Integer, Goodmodel> cacheGoodModelMap = new HashMap<Integer, Goodmodel>();
	private Map<String, Goodmodel> cacheNameGoodModelMap = new HashMap<String, Goodmodel>(); // 物品名字是key值

	/**
	 * 根据物品名字获取物品模型
	 * 
	 * @param name
	 * @return
	 */
	public Goodmodel getCacheNameGoodModelMapByName(String name) {
		return this.cacheNameGoodModelMap.get(name);
	}

	public Goodmodel get(int id) {
		return this.cacheGoodModelMap.get(id);
	}

	@SuppressWarnings("unchecked")
	private void initAllGoodModel() {
		try {
			List<Goodmodel> goodmodellist = goodmodelDAO.select();
			BeanTool.addOrUpdate(cacheGoodModelMap, goodmodellist, "id");
			BeanTool.addOrUpdate(cacheNameGoodModelMap, goodmodellist, "name");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 初始化药物类道具
	 * 
	 * @param goodmodellist
	 */
	private void initDrug(Collection<Goodmodel> goodmodellist) {
		hpDrugList = new ArrayList<Goodmodel>();
		mpDrugList = new ArrayList<Goodmodel>();
		spDrugList = new ArrayList<Goodmodel>();
		hpDrugBuffList = new ArrayList<Goodmodel>();
		mpDrugBuffList = new ArrayList<Goodmodel>();
		for (Iterator<Goodmodel> iterator = goodmodellist.iterator(); iterator.hasNext();) {
			Goodmodel goodmodel = iterator.next();
			if (goodmodel.getKind() == 3) {
				if (goodmodel.getDrugBuffId() == SkillId.AUTO_HP_SKILL_BUFF_ID) {
					hpDrugBuffList.add(goodmodel);
				}
				if (goodmodel.getDrugBuffId() == SkillId.AUTO_MP_SKILL_BUFF_ID) {
					mpDrugBuffList.add(goodmodel);
				}
				if (goodmodel.getDrugType() == EffectType.hp) {
					hpDrugList.add(goodmodel);
				} else if (goodmodel.getDrugType() == EffectType.mp) {
					mpDrugList.add(goodmodel);
				} else if (goodmodel.getDrugType() == EffectType.sp) {
					spDrugList.add(goodmodel);
				}
			}
		}
	}

	private void initEquite(Collection<Goodmodel> goodmodellist) {
		Map<Integer, List<Goodmodel>> tempEquite = new HashMap<Integer, List<Goodmodel>>();
		for (Goodmodel goodmodel : goodmodellist) {
			if (goodmodel.getKind() == 2) {
				addGradeEquiteToMap(tempEquite, goodmodel);
			}

			if (goodmodel.isShelizi()) {
				goodmodel.initPosignerskillSheliziMap();
			}
		}
		this.gradeEquite = tempEquite;
	}

	private void addGradeEquiteToMap(Map<Integer, List<Goodmodel>> tempEquite, Goodmodel goodmodel) {
		List<Goodmodel> list = tempEquite.get(goodmodel.getLimitGrade());
		if (list == null) {
			list = new ArrayList<Goodmodel>();
			tempEquite.put(goodmodel.getLimitGrade(), list);
		}
		list.add(goodmodel);
	}

	/**
	 * 初始化物品相关数据
	 */
	public void initGoodDate() {
		GoodspackgeDateManager.getInstance();
		GiftPacksManager.getInstance().reload();
		WeddingRingManager.getInstance().reload();
		UserGoodActionManager.getInstance().reload();
		initEquite(cacheGoodModelMap.values());
		initDrug(cacheGoodModelMap.values());
		Collections.sort(hpDrugList, new DrugComparator());
		Collections.sort(mpDrugList, new DrugComparator());
		Collections.sort(spDrugList, new DrugComparator());
		Collections.sort(hpDrugBuffList, new DrugComparator());
		Collections.sort(mpDrugBuffList, new DrugComparator());
	}

	@Override
	public void reload() {
		try {
			initAllGoodModel();
			initGoodDate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "goodmodel";
	}

	public Collection<Goodmodel> getAlLGoodCollection() {
		return cacheGoodModelMap.values();
	}

	public List<Goodmodel> getHpDrugList() {
		return hpDrugList;
	}

	public List<Goodmodel> getMpDrugList() {
		return mpDrugList;
	}

	public List<Goodmodel> getSpDrugList() {
		return spDrugList;
	}

	public List<Goodmodel> getHpDrugBuffList() {
		return hpDrugBuffList;
	}

	public List<Goodmodel> getMpDrugBuffList() {
		return mpDrugBuffList;
	}

	public List<Integer> getEquiteCollectionByGradeAndMenpai(int grade, byte menpai) {
		List<Integer> returnlist = new ArrayList<Integer>();
		List<Goodmodel> list = gradeEquite.get(grade);
		if (list == null || list.size() == 0) {
			return returnlist;
		}
		for (Goodmodel gm : list) {
			if (gm.getPosition() <= 12 && gm.getPosition() > 2) {
				if (gm.getPopsinger() == 0 || gm.getPopsinger() == menpai) {
					returnlist.add(gm.getId());
				}
			}
		}
		return returnlist;
	}

	public List<Integer> getHorseEquiteCollectionByGradeAndMenpai(int grade) {
		List<Integer> returnlist = new ArrayList<Integer>();
		List<Goodmodel> list = gradeEquite.get(grade);
		if (list == null || list.size() == 0) {
			return returnlist;
		}
		for (Goodmodel gm : list) {
			if (gm.getPosition() >= 21 && gm.getPosition() <= 24) {
				returnlist.add(gm.getId());
			}
		}
		return returnlist;
	}

}
