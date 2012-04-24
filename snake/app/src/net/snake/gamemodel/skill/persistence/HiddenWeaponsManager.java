package net.snake.gamemodel.skill.persistence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.snake.commons.BeanTool;
import net.snake.commons.BeanUtils;
import net.snake.commons.program.IReload;
import net.snake.gamemodel.skill.bean.HiddenWeapons;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;
/**
 * @author serv_dev
 */
public class HiddenWeaponsManager implements IReload {
	private static Logger logger = Logger.getLogger(HiddenWeaponsManager.class);
	private static HiddenWeaponsManager instance;
	private HiddenWeaponsDAO dao = new HiddenWeaponsDAO(SystemFactory.getGamedataSqlMapClient());
	private final Map<String, HiddenWeapons> cacheMap = new HashMap<String, HiddenWeapons>();
	private final Map<Integer, HiddenWeapons> cacheMap3 = new HashMap<Integer, HiddenWeapons>();// 暗器id，暗器
	private final Map<Integer, Integer> cacheMap2 = new HashMap<Integer, Integer>();// Grade品级,限制等级XiuGrade
	private int maxGrade = 0;

	public static HiddenWeaponsManager getInstance() {
		if (instance == null) {
			instance = new HiddenWeaponsManager();
		}
		return instance;
	}

	private HiddenWeaponsManager() {
		reload();
	}

	public int getMaxGrade(int hwgrade) {
		Integer maxGrade = cacheMap2.get(hwgrade);
		return maxGrade == null ? 0 : maxGrade;
	}
	@SuppressWarnings("unchecked")
	@Override
	public void reload() {
		try {
			List<HiddenWeapons> allHW = dao.select();
			BeanTool.addOrUpdate(cacheMap3, allHW, "id");
			if (!allHW.isEmpty()) {
				for (Iterator<HiddenWeapons> iterator = allHW.iterator(); iterator.hasNext();) {
					HiddenWeapons hiddenWeapons = iterator.next();
					String key = hiddenWeapons.getGrade() + "_" + hiddenWeapons.getXiuGrade();
					HiddenWeapons oldHiddenWeapons = cacheMap.get(key);
					if (oldHiddenWeapons != null) {
						BeanUtils.copyProperties(hiddenWeapons, oldHiddenWeapons);
					} else {
						cacheMap.put(key, hiddenWeapons);
					}
				}
			}

			cacheMap2.clear();
			maxGrade = 0;
			for (HiddenWeapons hw : cacheMap.values()) {
				if (hw.getGrade() > maxGrade) {
					maxGrade = hw.getGrade();
				}
				Integer grade = cacheMap2.get(hw.getGrade().intValue());
				if (grade != null) {
					if (grade < hw.getXiuGrade()) {
						cacheMap2.put(hw.getGrade(), hw.getXiuGrade());
					}
				} else {
					cacheMap2.put(hw.getGrade(), hw.getXiuGrade());
				}
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}
	}

	public int getMaxGrade() {
		return maxGrade;
	}

	public HiddenWeapons getHiddenWeaponsByGrade(int grade, int xiugrade) {
		return cacheMap.get(grade + "_" + xiugrade);
	}

	public HiddenWeapons getHiddenWeaponsById(int hwid) {
		return cacheMap3.get(hwid);
	}
}
