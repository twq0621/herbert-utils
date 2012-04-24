package net.snake.gamemodel.hero.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class CharacterGradeComparaer {

	private static CharacterGradeComparaer comparaer;

	private CharacterGradeComparaer() {
	}

	public CharacterGradeComparaer getInstance() {
		synchronized (comparaer) {
			if (comparaer == null) {
				comparaer = new CharacterGradeComparaer();
			}
		}
		return comparaer;
	}

	public static int comparacterCharacterGrade(Collection<Hero> col, Hero self) {
		int upgrade = 0;
		Collection<Hero> characterList = new ArrayList<Hero>(col);
		for (Iterator<Hero> iterator = characterList.iterator(); iterator.hasNext();) {
			Hero other = iterator.next();
			if (other.getGrade() > self.getGrade()) {
				upgrade++;
			}
		}
		return upgrade + 1;
	}
}
