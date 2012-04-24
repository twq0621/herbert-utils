package net.snake.gamemodel.activities.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.commons.BeanTool;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.activities.bean.ActivationCard;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * @description 兑换激活码静态管理类
 * @author serv_dev
 */
public class ActivationCardManager implements CacheUpdateListener {

	private static Logger logger = Logger.getLogger(ActivationCardManager.class);

	private static ActivationCardManager instance;

	public static ActivationCardManager getInstance() {
		if (instance == null) {
			instance = new ActivationCardManager();
		}
		return instance;
	}

	private ActivationCardDAO activationCardDAO = new ActivationCardDAO(SystemFactory.getGamedataSqlMapClient());

	private Map<String, ActivationCard> activationCardMap = new ConcurrentHashMap<String, ActivationCard>();

	private ActivationCardManager() {
	}

	/**
	 * @description 初始化静态数据
	 */
	@SuppressWarnings("unchecked")
	private void initActivationCardManager() {
		// 加载全部卡信息
		List<ActivationCard> activationCardList = this.selectAllActivationCard();
		if (activationCardList == null || activationCardList.isEmpty()) {
			logger.error("activationCardList init is null ...  ");
			return;
		}
		// 初始化物品信息
		initGoods(activationCardList);

		// 包装MAP
		Map<String, ActivationCard> map = BeanTool.listToMap(activationCardList, "cardNo", ConcurrentHashMap.class);
		if (map == null || map.isEmpty()) {
			logger.error("activationCardList listToMap is null ...  ");
			return;
		}
		// 新信息赋值
		activationCardMap = map;

	}

	/**
	 * @description 初始化物品信息
	 * @param activationCardList
	 */
	private void initGoods(List<ActivationCard> activationCardList) {
		for (ActivationCard activationCard : activationCardList) {
			activationCard.initGoodsInfo();
		}
	}

	/**
	 * @description 获得所有激活码信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private List<ActivationCard> selectAllActivationCard() {
		try {
			return activationCardDAO.select();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * @description 判断卡号是否存在
	 * @param cardNo
	 *            卡号
	 * @return true为存在，false为不存在
	 */
	public boolean isExist(String cardNo) {
		return activationCardMap.containsKey(cardNo);
	}

	/**
	 * @description 根据卡号获得卡信息
	 * @param cardNo
	 *            卡号
	 * @return
	 */
	public ActivationCard findActivationCardByCardNo(String cardNo) {
		if (cardNo == null || cardNo.length() <= 0) {
			return null;
		}
		return activationCardMap.get(cardNo);
	}

	@Override
	public void reload() {
		initActivationCardManager();
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "activationCard";
	}

}
