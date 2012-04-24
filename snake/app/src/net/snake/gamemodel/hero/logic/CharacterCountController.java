package net.snake.gamemodel.hero.logic;

import net.snake.gamemodel.hero.bean.CharacterCount;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterCountManager;

import org.apache.log4j.Logger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @author serv_dev
 */
public class CharacterCountController extends CharacterController {
	private static Logger logger = Logger.getLogger(CharacterCountController.class);

	int characterid;

	public CharacterCountController(Hero character) {
		super(character);
	}

	private CharacterCountManager manager = CharacterCountManager.getInstance();
	private HashMap<Integer, CharacterCount> countMap = new HashMap<Integer, CharacterCount>();

	public void initData() {
		characterid = character.getId();
		try {
			List<CharacterCount> loadByCharacter = manager.loadByCharacter(character);
			countMap.clear();
			if (loadByCharacter != null && loadByCharacter.size() > 0) {
				for (CharacterCount characterCount : loadByCharacter) {
					countMap.put(characterCount.getCountType(), characterCount);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void count(int type, int amount) {
		CharacterCount characterCount = getCharacterCount(type);
		if (characterCount == null) {
			characterCount = new CharacterCount();
			characterCount.setCount(amount);
			characterCount.setCountType(type);
			characterCount.setCharacterId(characterid);
			countMap.put(type, characterCount);
			manager.asyncInsert(characterCount);

		} else {
			characterCount.setCount(characterCount.getCount() + amount);
			manager.asyncUpdate(characterCount);
		}
	}

	// /**
	// * 加入新的统计项
	// * @param count
	// */
	// private void addCharacterCount(CharacterCount count){
	// countMap.put(count.getCountType(),count);
	// manager.asyncInsert(count);
	// }
	//
	// private boolean updateCharacterCount(CharacterCount count){
	// CharacterCount characterCount = countMap.get(count.getCountType());
	// if(characterCount!=null){
	// countMap.put(count.getCountType(),count);
	// manager.asyncUpdate(count);
	// return true;
	// }
	// return false;
	// }

	public CharacterCount getCharacterCount(int type) {
		return countMap.get(type);
	}

	public void destroy() {
		if (countMap != null) {
			countMap.clear();
			countMap = null;
		}
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

}
