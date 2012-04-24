package net.snake.gamemodel.goods.bean;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.BeanTool;
import net.snake.commons.GenerateProbability;
import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.monster.bean.MonsterModel;

import org.apache.log4j.Logger;

public class Goodspackge {
	private static Logger logger = Logger.getLogger(Goodspackge.class);

	private GoodspackgeDate gpd;
	public int id = 0;
	private List<Goodmodel> goodList = new ArrayList<Goodmodel>();
	private int nowlistIndex = 0;

	public Goodspackge() {
	}

	/**
	 * 该组包掉落物品
	 * 
	 * @param monster
	 * @return
	 */
	public CharacterGoods dropLostGoods(MonsterModel monster) {

		if (nowlistIndex >= goodList.size()) {
			nowlistIndex = 0;
		}
		Goodmodel gm = goodList.get(nowlistIndex);
		// logger.info("nowlistindex="+nowlistIndex+","+gm.getName());
		nowlistIndex++;
		CharacterGoods cg = CharacterGoods.createCharacterGoods(1, gm, monster.getLoopCount(), 0);
		if (gm.getBinding() == BindChangeType.BIND) {
			cg.setBind(CommonUseNumber.byte1);
		} else {
			cg.setBind(CommonUseNumber.byte0);
		}
		return cg;
	}

	public List<CharacterGoods> dropTaskRewardGoods(int count, int loop) {
		if (count < 1) {
			return null;
		}
		if (loop > 100) {
			loop = 100;
		}
		List<CharacterGoods> list = new ArrayList<CharacterGoods>();
		for (int i = 0; list.size() < count && i < goodList.size() + 5; i++) {
			int index = GenerateProbability.randomIntValue(0, goodList.size() - 1);
			Goodmodel gm = goodList.get(index);
			boolean b = isAbleTaskReward(list, gm.getId());
			if (b) {
				CharacterGoods cg = CharacterGoods.createCharacterGoods(1, gm, loop, 0);
				list.add(cg);
			}
		}
		return list;
	}

	private boolean isAbleTaskReward(List<CharacterGoods> list, int goodId) {
		if (list.size() == 0) {
			return true;
		}
		for (CharacterGoods cg : list) {
			if (cg.getGoodmodelId() == goodId) {
				return false;
			}
		}
		return true;
	}

	public GoodspackgeDate getGpd() {
		return gpd;
	}

	public void setGpd(GoodspackgeDate gpd) {
		this.gpd = gpd;
	}

	public List<Goodmodel> getGoodList() {
		return goodList;
	}

	public void setGoodList(List<Goodmodel> goodList) {
		this.goodList = goodList;
	}

	public int getNowlistIndex() {
		return nowlistIndex;
	}

	public void setNowlistIndex(int nowlistIndex) {
		this.nowlistIndex = nowlistIndex;
	}

	public void initGoodPackge(GoodspackgeDate gpd) {
		this.gpd = gpd;
		this.id = gpd.getId();
		for (int i = 1; i < 21; i++) {
			Object bean;
			try {
				bean = BeanTool.getPropValue(gpd, "good" + i);
				if (bean != null) {
					String name = (String) bean;
					GoodmodelManager gmm = GoodmodelManager.getInstance();
					Goodmodel gm = gmm.getCacheNameGoodModelMapByName(name);
					if (gm != null) {
						goodList.add(gm);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		if (goodList.size() < 1) {
			logger.error("the goods packge id is " + gpd.getId() + ",not goodN data ");
		}
	}
}
