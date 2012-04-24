package net.snake.gamemodel.hero.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.commons.GenerateProbability;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.RoleRandomName;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.cache.CharacterCacheManager;

/**
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class RoleRandomNameManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(RoleRandomNameManager.class);
	private List<RoleRandomName> xingList = new ArrayList<RoleRandomName>();
	private List<RoleRandomName> maleNameList = new ArrayList<RoleRandomName>();
	private List<RoleRandomName> femaleNameList = new ArrayList<RoleRandomName>();
	public boolean isOpen = true;
	private static RoleRandomNameDAO dao = new RoleRandomNameDAO(SystemFactory.getGamedataSqlMapClient());

	private static RoleRandomNameManager instance;

	public static RoleRandomNameManager getInstance() {
		if (instance == null) {
			instance = new RoleRandomNameManager();
		}
		return instance;
	}

	private RoleRandomNameManager() {
	}

	/**
	 * 获取组合名字
	 * 
	 * @param isMale
	 * @param count
	 * @return
	 */
	public String randomHefaRoleName(boolean isMale, int count) {
		// 最少生产两次
		if (count < 2) {
			count = 2;
		}

		String roleName = null;
		for (int i = 0; i < count; i++) {
			roleName = randomRoleName(isMale);
			if (roleName == null) {
				continue;
			}
			CharacterCacheEntry cce = CharacterCacheManager.getInstance().getCharacterCacheEntryByName(roleName);
			if (cce == null) {
				return roleName;
			}
			// 走到这里表示名字有重复，再来一次。
		}

		if (roleName == null) {
			return null;
		}
		// 但是还是重复了
		String zuheName = roleName;
		int rand = 6 - roleName.length();
		for (int i = 0; i < count; i++) {
			zuheName = roleName;
			// 添加数字后缀
			for (int n = 0; n < rand; n++) {
				int num = GenerateProbability.randomIntValue(0, 9);
				zuheName = zuheName + num;
			}
			CharacterCacheEntry cce = CharacterCacheManager.getInstance().getCharacterCacheEntryByName(zuheName);
			if (cce == null) {
				return zuheName;
			}
			// 走到这里表示名字有重复，再来一次。
		}
		// TODO 两部分的思路一样无法根本解决重复问题，但是这种几率太低了。
		return zuheName;
	}

	private String randomRoleName(boolean isMale) {
		StringBuilder sb = new StringBuilder();
		// 随机一个姓
		int xingIndex = GenerateProbability.randomIntValue(0, xingList.size() - 1);
		sb.append(xingList.get(xingIndex).getfValue());
		// 随机一个男名
		if (isMale) {
			int mingIndex = GenerateProbability.randomIntValue(0, maleNameList.size() - 1);
			sb.append(maleNameList.get(mingIndex).getfValue());
		} else {
			// 随机一个女名
			int mingIndex = GenerateProbability.randomIntValue(0, femaleNameList.size() - 1);
			sb.append(femaleNameList.get(mingIndex).getfValue());
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public void reload() {
		try {
			List<RoleRandomName> xingList1 = new ArrayList<RoleRandomName>();
			List<RoleRandomName> maleNameList1 = new ArrayList<RoleRandomName>();
			List<RoleRandomName> femaleNameList1 = new ArrayList<RoleRandomName>();
			List<RoleRandomName> list = dao.select();
			for (RoleRandomName randomName : list) {
				if (randomName.getfType() == RoleRandomName.xingType) {
					xingList1.add(randomName);
				} else if (randomName.getfType() == RoleRandomName.maleType) {
					maleNameList1.add(randomName);
				} else if (randomName.getfType() == RoleRandomName.femaleType) {
					femaleNameList1.add(randomName);
				}
			}
			xingList = xingList1;
			maleNameList = maleNameList1;
			femaleNameList = femaleNameList1;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "RoleRandomName";
	}

}
