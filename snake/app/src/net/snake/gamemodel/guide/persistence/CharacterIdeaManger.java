package net.snake.gamemodel.guide.persistence;

import java.sql.SQLException;

import net.snake.gamemodel.guide.bean.CharacterIdea;
import net.snake.ibatis.SystemFactory;

public class CharacterIdeaManger {

	private CharacterIdeaDAO characterHorseDAO = new CharacterIdeaDAO(SystemFactory.getCharacterSqlMapClient());
	// 单例实现=====================================
	private static CharacterIdeaManger instance;

	private CharacterIdeaManger() {
	}

	public static CharacterIdeaManger getInstance() {
		if (instance == null) {
			instance = new CharacterIdeaManger();
		}
		return instance;
	}

	// 单例实现========================================

	public void insert(CharacterIdea ci) throws SQLException {
		characterHorseDAO.insert(ci);
	}
}
