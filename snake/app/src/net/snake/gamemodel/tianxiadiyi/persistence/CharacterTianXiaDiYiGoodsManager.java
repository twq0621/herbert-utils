package net.snake.gamemodel.tianxiadiyi.persistence;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.BeanUtils;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYiGoods;
import net.snake.ibatis.SystemFactory;


public class CharacterTianXiaDiYiGoodsManager {
	private static Logger logger = Logger.getLogger(CharacterTianXiaDiYiGoodsManager.class);
	private CharacterTianXiaDiYiGoodsDAO characterTianXiaDiYiGoodsDAO = new CharacterTianXiaDiYiGoodsDAO(SystemFactory.getCharacterSqlMapClient());
	private List<CharacterTianXiaDiYiGoods> listCharacterTianXiaDiYiGoods;
	private Map<String, CharacterTianXiaDiYiGoods> mapCharacterTianXiaDiYiGoods = new HashMap<String, CharacterTianXiaDiYiGoods>();
	// 单例实现=====================================
	private static CharacterTianXiaDiYiGoodsManager instance;

	private CharacterTianXiaDiYiGoodsManager() {
		init();
	}

	public static CharacterTianXiaDiYiGoodsManager getInstance() {
		if (instance == null) {
			instance = new CharacterTianXiaDiYiGoodsManager();
		}
		return instance;
	}

	// 单例实现=====================================
	@SuppressWarnings("unchecked")
	public void init() {
		try {
			listCharacterTianXiaDiYiGoods = characterTianXiaDiYiGoodsDAO.select();

			for (CharacterTianXiaDiYiGoods obj : listCharacterTianXiaDiYiGoods) {
				mapCharacterTianXiaDiYiGoods.put(obj.getId(), obj);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void updateCharacterTianXiaDiYiTopOneSave(Hero character) {

		Collection<CharacterGoods> listGoods = character.getCharacterGoodController().getBodyGoodsList();
		CharacterTianXiaDiYiGoods characterTianXiaDiYiGoods = new CharacterTianXiaDiYiGoods();
		for (CharacterGoods characterGoods : listGoods) {
			try {
				BeanUtils.copyProperties(characterGoods, characterTianXiaDiYiGoods);
				characterTianXiaDiYiGoods.setCharacterId(character.getCharacterInitiallyId());
				characterTianXiaDiYiGoods.setServerId(character.getOriginalSid());
				characterTianXiaDiYiGoodsDAO.insertSelective(characterTianXiaDiYiGoods);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	/**
	 * 清空本地数据 <br/>
	 * 循环物理机<br/>
	 * 添加天下第一数据
	 * 
	 * @throws SQLException
	 */
	public void updateData() {
		try {

			characterTianXiaDiYiGoodsDAO.deleteAll();
			listCharacterTianXiaDiYiGoods.clear();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void insertCharacterTianXiaDiYiGoods(CharacterTianXiaDiYiGoods characterTianXiaDiYiGoods) {
		if (mapCharacterTianXiaDiYiGoods.containsKey(characterTianXiaDiYiGoods.getId())) {
			return;
		}
		try {
			characterTianXiaDiYiGoods.setIdType(null);
			mapCharacterTianXiaDiYiGoods.put(characterTianXiaDiYiGoods.getId(), characterTianXiaDiYiGoods);
			listCharacterTianXiaDiYiGoods.add(characterTianXiaDiYiGoods);
			characterTianXiaDiYiGoodsDAO.insertSelective(characterTianXiaDiYiGoods);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<CharacterTianXiaDiYiGoods> getListCharacterTianXiaDiYiGoods() {
		if (listCharacterTianXiaDiYiGoods == null || listCharacterTianXiaDiYiGoods.isEmpty()) {

			try {
				listCharacterTianXiaDiYiGoods = characterTianXiaDiYiGoodsDAO.select();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return listCharacterTianXiaDiYiGoods;
	}

}
