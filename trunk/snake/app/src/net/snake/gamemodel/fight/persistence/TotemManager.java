package net.snake.gamemodel.fight.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.commons.GenerateProbability;
import net.snake.consts.Property;
import net.snake.gamemodel.fight.bean.Totem;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.ibatis.SystemFactory;

public class TotemManager implements CacheUpdateListener {

	private static final Logger logger = Logger.getLogger(TotemManager.class);
	private static TotemDAO dao = new TotemDAO(SystemFactory.getGamedataSqlMapClient());

	// 单例实现=====================================
	private static TotemManager instance;
	private int maxProbility = 0;
	private int minProbility = 0;

	public static TotemManager getInstance() {
		if (instance == null) {
			instance = new TotemManager();
		}
		return instance;
	}

	private TotemManager() {
		reload();
	}

	// 单例实现========================================
	private Map<Integer, Totem> cacheTotemMap = new HashMap<Integer, Totem>();

	public void reload() {
		try {
			getTotemMap();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Totem getTotemById(int id) {
		return cacheTotemMap.get(id);
	}
	@SuppressWarnings("unchecked")
	private void getTotemMap() {
		try {
			List<Totem> totemlist = dao.select();
			BeanTool.addOrUpdate(cacheTotemMap, totemlist, "id");
			maxProbility = 0;
			minProbility = 0;
			for (Iterator<Totem> iterator = cacheTotemMap.values().iterator(); iterator.hasNext();) {
				Totem totem = iterator.next();
				if (totem.getMaxProbility() > maxProbility) {
					maxProbility = totem.getMaxProbility();
				}

				if (totem.getMinProbility() < minProbility) {
					minProbility = totem.getMinProbility();
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public String totemToEquipmment(CharacterGoods characterGoods, Totem totem, String characterName) {
		StringBuilder sb = new StringBuilder();
//		characterGoods.setTotemUsrname(characterName);
		sb.append(totem.getId()).append(";");
		String attackEnhance = totem.getAttackEnhance();
		if (attackEnhance != null && !"".equals(attackEnhance)) {
			String[] attackEnhanceArr = attackEnhance.split(";");
			if (attackEnhanceArr.length > 0 && !"".equals(attackEnhanceArr[0])) {
				int ranV = GenerateProbability.randomIntValue(0, attackEnhanceArr.length - 1);
				sb.append(Property.GJL.getNum()).append(",").append(ranV).append(";");
			}
		}

		// String hiddenDerate = totem.getHiddenDerate();
		// if (hiddenDerate != null && !"".equals(hiddenDerate)) {
		// String[] hiddenDerateArr = hiddenDerate.split(";");
		// if (hiddenDerateArr.length > 0 && !"".equals(hiddenDerateArr[0])) {
		// int ranV = GenerateProbability.randomIntValue(0,
		// hiddenDerateArr.length -1);
		// sb.append(Property.AQMS.getNum()).append(",").append(ranV).append(";");
		// }
		// }

		String hurtDerate = totem.getHurtDerate();
		if (hurtDerate != null && !"".equals(hurtDerate)) {
			String[] hurtDerateArr = hurtDerate.split(";");
			if (hurtDerateArr.length > 0 && !"".equals(hurtDerateArr[0])) {
				int ranV = GenerateProbability.randomIntValue(0, hurtDerateArr.length - 1);
				sb.append(Property.SHJM.getNum()).append(",").append(ranV).append(";");
			}
		}

		String ignoreDefence = totem.getIgnoreDefence();
		if (ignoreDefence != null && !"".equals(ignoreDefence)) {
			String[] ignoreDefenceArr = ignoreDefence.split(";");
			if (ignoreDefenceArr.length > 0 && !"".equals(ignoreDefenceArr[0])) {
				int ranV = GenerateProbability.randomIntValue(0, ignoreDefenceArr.length - 1);
				sb.append(Property.HSFY.getNum()).append(",").append(ranV).append(";");
			}
		}

		String reboundHurt = totem.getReboundHurt();
		if (reboundHurt != null && !"".equals(reboundHurt)) {
			String[] reboundHurtArr = reboundHurt.split(";");
			if (reboundHurtArr.length > 0 && !"".equals(reboundHurtArr[0])) {
				int ranV = GenerateProbability.randomIntValue(0, reboundHurtArr.length - 1);
				sb.append(Property.FTSH.getNum()).append(",").append(ranV).append(";");
			}
		}

		return sb.toString();
	}

	public String getTotemShowStr(String totemStr) {
		StringBuilder sb = new StringBuilder();
		if (totemStr != null && !"".equals(totemStr)) {
			String[] totemArr = totemStr.split(";");
			if (totemArr.length > 1 && !"".equals(totemArr[0])) {
				int totemId = Integer.parseInt(totemArr[0]);
				// sb.append(characterGoods.getTotemUsrname()).append(";");
				for (int i = 1; i < totemArr.length; i++) {
					String[] _tmpTotemPropertyArr = totemArr[i].split(",");
					int property = Integer.parseInt(_tmpTotemPropertyArr[0]);
					int pos = Integer.parseInt(_tmpTotemPropertyArr[1]);
					Property p = Property.getProperty(property);
					Totem totem = TotemManager.getInstance().getTotemById(totemId);
					sb.append(totemId).append(",");
					sb.append(property).append(",");
					int nowV = totem.getTotemValue(p, pos);
					int maxV = totem.getTotemMaxValue(p);
					sb.append(nowV).append(",");
					sb.append(maxV).append(",");
					sb.append(maxV == nowV ? 1 : 0).append(";");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 获得一个随机的图腾
	 * 
	 * @return
	 */
	public Totem getRandomTotem() {
		int ranV = GenerateProbability.randomIntValue(minProbility, maxProbility);
		for (Iterator<Totem> iterator = cacheTotemMap.values().iterator(); iterator.hasNext();) {
			Totem totem = iterator.next();
			if (totem.getMinProbility() <= ranV && ranV <= totem.getMaxProbility()) {
				return totem;
			}
		}
		return null;
	}

	@Override
	public String getAppname() {
		return "totem";
	}

	@Override
	public String getCachename() {
		return "totem";
	}
}
