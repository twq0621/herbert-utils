package net.snake.gamemodel.monster.logic.lostgoods;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.bean.Goodspackge;
import net.snake.gamemodel.goods.bean.LostpackgeGood;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.persistence.GoodspackgeDateManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterLostgoodsDate;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;



/**
 * 怪物掉落数据初始化 数据存储 掉落规则管理器（与怪物模版绑定 服务启动后 初始化该管理器数据 为怪物掉落提供接口）
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class MonsterLostgoods {
	private static Logger logger = Logger.getLogger(MonsterLostgoods.class);

	private MonsterLostgoodsDate mlgd;
	private List<Lostgoods> lostGoodList = new ArrayList<Lostgoods>();
	private Lostmonsters lostMonster;

	public MonsterLostgoods(MonsterLostgoodsDate mlgd) {
		this.mlgd = mlgd;
		for (int i = 1; i <= 50; i++) {
			try {
				Object bean = BeanTool.getPropValue(mlgd, "lostGood" + i);
				if (bean != null) {
					String str = (String) bean;
					Lostgoods lg = StringToLostgoods(str);
					if (lg != null) {
						lostGoodList.add(lg);
					}
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		lostMonster = StringToLostMonsters(mlgd.getLostMonsterProbability(), mlgd.getLostMonster());
	}

	/**
	 * 怪物物品掉落调用此方法
	 * 
	 * @param loop
	 * @return
	 */
	public List<CharacterGoods> dropMonsterLostGoodsMap(Hero character, MonsterModel monster, int jiacheng) {
		List<CharacterGoods> list = new ArrayList<CharacterGoods>();
		for (Lostgoods lg : lostGoodList) {
			CharacterGoods cg = lg.dropLostGoods(character, monster, jiacheng);
			if (cg != null) {
				list.add(cg);
			}
		}
		return list;
	}

	/**
	 * 怪物掉出怪物调用此方法
	 * 
	 * @param loop
	 * @return
	 */
	public SceneMonster dropMonsterLostMonsterList(Hero character, MonsterModel monster) {
		if (lostMonster == null) {
			return null;
		}
		SceneMonster sm = lostMonster.dropLostMonsters(character, monster);
		return sm;
	}

	public MonsterLostgoodsDate getMlgd() {
		return mlgd;
	}

	public void setMlgd(MonsterLostgoodsDate mlgd) {
		this.mlgd = mlgd;
	}

	/**
	 * 解析数据库中字符串转换为Lostgoods对象
	 * 
	 * @param str
	 * @return
	 */
	private Lostgoods StringToLostgoods(String str) {
		String[] goodStr = str.split(" ");
		Lostgoods lg;
		if (goodStr.length != 2) {
			// 数据错误
			return null;
		}
		boolean gradeLimit = true;
		int bind = 0;
		boolean isOwner = true;
		if (goodStr[1].contains("~")) {
			gradeLimit = false;
			goodStr[1] = goodStr[1].replace("~", "");
		}
		if (goodStr[1].contains("$")) {
			goodStr[1] = goodStr[1].replace("$", "");
			bind = 1;
		}
		if (goodStr[1].contains("&")) {
			goodStr[1] = goodStr[1].replace("&", "");
			isOwner = false;
		}
		if (goodStr[1].contains("@")) {
			String name = goodStr[1].substring(1, goodStr[1].length());
			GoodspackgeDateManager gpm = GoodspackgeDateManager.getInstance();
			Goodspackge gpd = gpm.getGoodspackgeByNameInMap(name);
			if (gpd == null) {
				return null;
			}
			lg = new LostpackgeGood(gpd);
		} else if (goodStr[1].contains("#")) {
			GoodmodelManager gmm = GoodmodelManager.getInstance();
			String name = goodStr[1].substring(1, goodStr[1].length());
			Goodmodel gm = gmm.getCacheNameGoodModelMapByName(name);
			if (gm == null) {
				return null;
			}
			lg = new LostTaskGoods(gm);
		} else if (goodStr[1].contains("!")) {
			String name = goodStr[1].substring(1, goodStr[1].length());
			lg = new LostScripGoods(Integer.parseInt(name));
		} else if (goodStr[1].contains("COPPER")) {
			String[] couteTrs = goodStr[1].split("[*]");
			if (couteTrs.length == 2) {
				lg = new LostCopperGoods(Integer.parseInt(couteTrs[1]));
			} else {
				lg = new LostCopperGoods(5);
			}
		} else {
			GoodmodelManager gmm = GoodmodelManager.getInstance();
			Goodmodel gm = gmm.getCacheNameGoodModelMapByName(goodStr[1]);
			if (gm == null) {
				return null;
			}
			lg = new LostOnegoods(gm);

		}
		String[] jilvStr = goodStr[0].split("/");
		int probability = 1;
		if (jilvStr.length == 2) {
			int fenzi = Integer.parseInt(jilvStr[0]);
			int fenmu = Integer.parseInt(jilvStr[1]);
			float fenmuf = fenmu;
			float bizhi = Lostgoods.probabilityMax / fenmuf;
			probability = (int) (bizhi * fenzi);
			if (fenzi > fenmu) {
				probability = 0;
				lg.setMaxNum(fenzi / fenmu);
				lg.setNowNum(0);
			}
		}
		lg.setGradeLimit(gradeLimit);
		lg.setBind(bind);
		lg.setOwner(isOwner);
		lg.setProbability(probability);
		// logger.info(
		// "lg:{},{},{},{}",
		// new Object[] { str, lg.getMaxNum(), lg.getProbability(),
		// lg.isGradeLimit() });
		return lg;
	}

	/**
	 * 解析数据库中字符串转换为Lostmonsters对象
	 * 
	 * @param str
	 * @return
	 */
	private Lostmonsters StringToLostMonsters(String probabilityStr, String monster) {
		if (monster == null || monster.equals("") || probabilityStr == null || probabilityStr.equals("")) {
			return null;
		}
		String[] monsterStr = monster.split(";");
		List<MonsterModel> list = new ArrayList<MonsterModel>();
		MonsterModelManager mmm = MonsterModelManager.getInstance();
		for (int i = 0; i < monsterStr.length; i++) {
			MonsterModel mm = mmm.getFromCacheByName(monsterStr[i]);
			if (mm != null) {
				list.add(mm);
			}
		}
		if (list.size() == 0) {
			return null;
		}
		boolean gradeLimit = true;
		if (probabilityStr.contains("~")) {
			gradeLimit = false;
			probabilityStr = probabilityStr.substring(1, probabilityStr.length());
		}
		Lostmonsters lm = new LostOnemonsters(list);
		String[] jilvStr = probabilityStr.split("/");
		int probability = 1;
		if (jilvStr.length == 2) {
			int fenzi = Integer.parseInt(jilvStr[0]);
			int fenmu = Integer.parseInt(jilvStr[1]);
			probability = (10000 / fenmu) * fenzi;
			if (fenzi > fenmu) {
				probability = 0;
				lm.setMaxNum(fenzi / fenmu);
				lm.setNowNum(0);
			}
		}
		lm.setGradeLimit(gradeLimit);
		lm.setProbability(probability);
//		logger.info("lm:{},{},{},{}", new Object[] { monster, lm.getMaxNum(), lm.getProbability(), lm.isGradeLimit() });
		return lm;
	}
}
