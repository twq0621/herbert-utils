package net.snake.gamemodel.hero.logic;

import org.apache.log4j.Logger;

import net.snake.gamemodel.friend.persistence.PersonalsManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.Personals;



/**
 * 交友管理器
 * 
 * @author serv_dev
 * 
 */
public class MyPersonalsManager {
	private static Logger logger = Logger.getLogger(MyPersonalsManager.class);

	private Hero character;
	private Personals personals;

	public void destory() {
		personals = null;
	}

	public MyPersonalsManager(Hero character) {
		this.character = character;
	}

	public Personals getPersonals() {
		return personals;
	}

	public void setPersonals(Personals personals) {
		this.personals = personals;
	}

	public void initAddPersonals() {
		try {
			personals = new Personals();
			personals = PersonalsManager.getInstance().selectPersonalById(character.getId());
			if (null == personals.getCharacterId()) {
				personals.setCharacterId(character.getId());
				PersonalsManager.getInstance().addPersonals(personals);
			}
			personals.setBiaoqingtubiao(character.getNowBiaoqing());
			personals.setXingqing(character.getNowXingqing());
			personals.setHuxcount(character.getFlowerCount());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void updatePersonals(final Personals personals) {
		this.personals = personals;
		character.addToBatchUpdateTask(new Runnable() {
			@Override
			public void run() {
				PersonalsManager.getInstance().updatePersonals(personals);
			}
		});
	}

}
