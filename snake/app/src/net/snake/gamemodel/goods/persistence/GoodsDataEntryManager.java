package net.snake.gamemodel.goods.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.GoodsDataEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

/**
 * 角色物品表管理
 * 
 * @author benchild
 */

public class GoodsDataEntryManager {
	private static GoodsDataEntryDAO dao;
	// 单例实现=====================================
	private static GoodsDataEntryManager instance;
	private static final Logger logger = Logger.getLogger(GoodsDataEntryManager.class);

	private GoodsDataEntryManager() {
		dao = new GoodsDataEntryDAO(SystemFactory.getCharacterSqlMapClient());
	}

	public static GoodsDataEntryManager getInstance() {
		if (instance == null) {
			instance = new GoodsDataEntryManager();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List<GoodsDataEntry> getCharacterGoodsList(int characterid) throws SQLException {
		return dao.getCharacterGoodsList(characterid);
	}

	@SuppressWarnings("unchecked")
	public List<GoodsDataEntry> getCharacterGoodsList(int characterid, int from, int to) throws SQLException {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("characterid", characterid);
		map.put("from", from);
		map.put("to", to);
		return dao.getCharacterGoodsList(map);
	}

	@SuppressWarnings("unchecked")
	// 获得当前向上的装备
	public List<GoodsDataEntry> getCharacterAvatarGoodsList(int characterid) throws SQLException {
		return dao.getCharacterAvatarGoodsList(characterid);
	}

	public void asynUpdataCharacterGoods(Hero character, final CharacterGoods charactergoods) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updateByPrimaryKeySelective(charactergoods);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});

	}

	public void insert(CharacterGoods charactergoods) {
		try {
			dao.insertSelective(charactergoods);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynInsertCharacterGoods(Hero character, final CharacterGoods charactergoods) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.insertSelective(charactergoods);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});

	}

	// 仅设置物品位置字段进行更新，用于包裹整理
	public void asynUpdateGoodsPositonCount(Hero character, final CharacterGoods goods) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.updatePosition(goods);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});
	}

	public void asynDeleteCharacterGoods(Hero character, final String id) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.deleteById(id);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}

			}
		});

	}

	public void asynDeleteCharacterGoods(final Hero owner, final int beginPosition, final int capacity) {
		owner.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					Map<String, Integer> map = new HashMap<String, Integer>();
					map.put("characterid", owner.getId());
					map.put("from", beginPosition);
					map.put("to", beginPosition + capacity);
					dao.deleteByPosition(map);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void updateXingfu() {
		try {
			dao.updateXingfu();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
