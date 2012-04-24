package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.skill.bean.HiddenWeaponDataEntry;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;
public class CharacterHiddenWeaponManager {
	private static final Logger logger = Logger.getLogger(CharacterHiddenWeaponManager.class);

	private static HiddenWeaponDataEntryDAO dao = new HiddenWeaponDataEntryDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterHiddenWeaponManager instance;

	private CharacterHiddenWeaponManager() {
	}

	public static CharacterHiddenWeaponManager getInstance() {
		if (instance == null) {
			instance = new CharacterHiddenWeaponManager();
		}
		return instance;
	}

	public void delCharacterHiddenWeapon(int characterId) {
		try {
			dao.deleteByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 更具角色id 进行的新手引导
	 * 
	 * @param characterId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public CharacterHiddenWeapon queryCharacterHiddenWeaponByCharacterId(int characterId) {
		try {
			List list = dao.selectByCharacterId(characterId);
			int size = list.size();
			if (size > 0) {
				if (size == 1) {
					CharacterHiddenWeapon characterHiddenWeapon = new CharacterHiddenWeapon((HiddenWeaponDataEntry) list.get(0));
					return characterHiddenWeapon;
				} else {
					logger.error("queryCharacterHiddenWeaponByCharacterId(int) - db save with err, characterid:"+ characterId); //$NON-NLS-1$
					throw new SQLException();
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 插入CharacterNewguide
	 * 
	 * @param cf
	 */
	private void insert(HiddenWeaponDataEntry hwde) {
		try {
			dao.insertSelective(hwde);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 异步插入CharacterGiftpacks
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynInsertCharacterHiddenWeapon(Hero character, final HiddenWeaponDataEntry cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				insert(cf);
			}
		});
	}

	public void update(HiddenWeaponDataEntry hwde) {
		try {
			dao.updateByPrimaryKeySelective(hwde);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<HiddenWeaponDataEntry> getRanKingCharacterHiddenWeaponsList() {
		return dao.getCharacterHiddenWeaponDataEntriesRanking();
	}

	public List<HiddenWeaponDataEntry> getRanKingCharacterHiddenWeaponsListTongJi(int leve) {
		return dao.getCharacterHiddenWeaponDataEntriesTongJiRanking(leve);
	}

	/**
	 * CharacterGiftpacks异步更新
	 * 
	 * @param character
	 * @param characterhorse
	 */

	public void asynUpdateCharacterHiddenWeapon(Hero character, final CharacterHiddenWeapon cf) {
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				update(cf);
			}
		});
	}
}
