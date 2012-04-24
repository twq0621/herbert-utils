package net.snake.gamemodel.monster.logic.lostgoods;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.snake.commons.BeanTool;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.bean.Goodspackge;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.persistence.GoodspackgeDateManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.BossLostGoodDate;
import net.snake.gamemodel.monster.bean.MonsterModel;

import org.apache.log4j.Logger;

/**
 * boss站立击杀获物品掉落
 * 
 * @author serv_dev
 * 
 */
public class BossLostGood extends Lostgoods {
	private static Logger logger = Logger.getLogger(BossLostGood.class);

	private List<BossGood> bossGoodList = new ArrayList<BossGood>();
	private int randMax;

	public BossLostGood(BossLostGoodDate blgd) {
		for (int i = 1; i < 21; i++) {
			Object bean;
			try {
				bean = BeanTool.getPropValue(blgd, "good" + i);
				if (bean != null && !bean.equals("")) {
					String name = (String) bean;
					BossGood bossGood = stringToBossGood(name);
					addBossGoodList(bossGood);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public BossLostGood(List<BossGood> goods) {
		for (Iterator<BossGood> iterator = goods.iterator(); iterator.hasNext();) {
			BossGood good = iterator.next();
			addBossGoodList(good);
		}
	}

	public void addBossGoodList(BossGood bossGood) {
		if (bossGood == null) {
			return;
		}
		if (bossGoodList.size() == 0) {
			bossGoodList.add(bossGood);
			this.randMax = bossGood.getRandNum();
			return;
		}
		bossGood.setRandNum(this.randMax + bossGood.getRandNum());
		bossGoodList.add(bossGood);
		this.randMax = bossGood.getRandNum();
	}

	private BossGood stringToBossGood(String str) {
		String[] goodStr = str.split(" ");
		BossGood bossGood;
		if (goodStr.length != 2) {
			// 数据错误
			return null;
		}
		if (goodStr[1].contains("@")) {
			String name = goodStr[1].substring(1, goodStr[1].length());
			GoodspackgeDateManager gpm = GoodspackgeDateManager.getInstance();
			Goodspackge gpd = gpm.getGoodspackgeByNameInMap(name);
			if (gpd == null) {
				return null;
			}
			bossGood = new BossPacksGood(gpd);
		} else if (goodStr[1].contains("COPPER")) {
			String[] couteTrs = goodStr[1].split("[*]");
			if (couteTrs.length == 2) {
				bossGood = new BossCopperGood(Integer.parseInt(couteTrs[1]));
			} else {
				bossGood = new BossCopperGood(5);
			}
		} else {
			GoodmodelManager gmm = GoodmodelManager.getInstance();
			Goodmodel gm = gmm.getCacheNameGoodModelMapByName(goodStr[1]);
			if (gm == null) {
				return null;
			}
			bossGood = new BossOneGood(gm);
		}
		int rand = Integer.parseInt(goodStr[0]);
		if (rand < 1) {
			return null;
		}
		bossGood.setRandNum(rand);
		return bossGood;
	}

	@Override
	public CharacterGoods dropLostGoods(Hero character, MonsterModel monster, float dropJiacheng) {
		if (bossGoodList.size() == 0) {
			return null;
		}
		logger.info(randMax);
		int rand = GenerateProbability.randomIntValue(1, randMax);
		for (BossGood bg : bossGoodList) {
			logger.info("rand=" + rand + ",randnum=" + bg.getRandNum());
			if (rand <= bg.getRandNum()) {
				return bg.dropLostGoods(character, monster);
			}
		}
		return null;
	}
	
	public void resetItemIndex(){
		if (bossGoodList.size() == 0) {
			return ;
		}
		for (BossGood bg : bossGoodList) {
				bg.goodIndexReset();
		}
	}
}
