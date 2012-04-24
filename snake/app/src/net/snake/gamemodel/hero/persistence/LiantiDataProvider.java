package net.snake.gamemodel.hero.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.heroext.lianti.bean.Lianti;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class LiantiDataProvider implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(LiantiDataProvider.class);
	LiantiDAO liantidao = new LiantiDAO(SystemFactory.getGamedataSqlMapClient());
	private static LiantiDataProvider instance;
	private Map<Integer, Lianti> liantimap = new HashMap<Integer, Lianti>();
	private volatile int maxliantijingjie = 8;
	private Lianti max = null;

	private LiantiDataProvider() {
	}

	public int getMaxLiantiJingjie() {
		return maxliantijingjie;
	}

	public static LiantiDataProvider getInstance() {
		if (instance == null) {
			instance = new LiantiDataProvider();
		}
		return instance;
	}

	public Lianti getLianTiByJingjie(int jingjie) {
		return liantimap.get(jingjie);
	}

	public Lianti getLianTiByFoodId(int foodid) {
		for (Lianti lianti : liantimap.values()) {
			if (foodid == lianti.getFoodGoodsid().intValue()) {
				return lianti;
			}
		}
		return null;
	}

	@Override
	public void reload() {
		try {
			BeanTool.addOrUpdate(liantimap, liantidao.select(), "id");
			ArrayList<Lianti> t = new ArrayList<Lianti>(liantimap.values());
			Collections.sort(t, new Comparator<Lianti>() {
				@Override
				public int compare(Lianti o1, Lianti o2) {
					return o2.getId().compareTo(o1.getId());
				}
			});
			maxliantijingjie = t.get(0).getId();
			max = liantimap.get(maxliantijingjie);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public Lianti getMax() {
		return max;
	};

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "lianti";
	}

}
