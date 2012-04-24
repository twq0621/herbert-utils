package net.snake.gamemodel.skill.persistence;

import java.sql.SQLException;
import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.CharacterSkillData;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 人物技能关系管理
 * 
 */

public class CharacterSkillDataProvider2 {
	private static final Logger logger = Logger.getLogger(CharacterSkillDataProvider2.class);
	private static CharacterSkillDataDAO dao = new CharacterSkillDataDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterSkillDataProvider2 instance;

	private CharacterSkillDataProvider2() {
	}

	public static CharacterSkillDataProvider2 getInstance() {
		if (instance == null) {
			instance = new CharacterSkillDataProvider2();
		}
		return instance;
	}

	// 单例实现========================================

	@SuppressWarnings("unchecked")
	public List<CharacterSkillData> getCharacterSkillDataList(Integer characterId) throws SQLException {
		return dao.selectByCharacterId(characterId);
	}

	@SuppressWarnings("unchecked")
	public List<CharacterSkillData> getHorseSkillDataList(Integer horseId) throws SQLException {
		return dao.selectByHorseId(horseId);
	}

	public void deleteByHorseId(int horseId) {
		try {
			if (Options.IsCrossServ)
				return;
			dao.deleteByHorseId(horseId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void deleteByCharacterId(int characterId) {
		try {
			if (Options.IsCrossServ)
				return;
			dao.deleteByCharacterId(characterId);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynUpdataCharacterSkill(Hero character, final CharacterSkillData skilldata) {
		if (Options.IsCrossServ)
			return;
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				try {
					dao.updateByPrimaryKey(skilldata);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}
	
	public void insert(CharacterSkillData skilldata){
		try {
			dao.insert(skilldata);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void asynInsertCharacterSkill(Hero character, final CharacterSkillData skilldata) {
		if (Options.IsCrossServ)
			return;
		character.addToBatchUpdateTask(new Runnable() {

			@Override
			public void run() {
				try {
					dao.insert(skilldata);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

	public void asynDelCharacterSkill(Hero character, final int id) {
		if (Options.IsCrossServ)
			return;
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				try {
					dao.deleteByPrimaryKey(id);
				} catch (SQLException e) {
					logger.error(e.getMessage(), e);
				}
			}
		});
	}

}
