package net.snake.gamemodel.friend.bean;

import java.util.Comparator;


public class BlackListComparator implements Comparator<CharacterFriend> {
	@Override
	public int compare(CharacterFriend o1, CharacterFriend o2) {
		return o1.getAddDate().compareTo(o2.getAddDate());
	}
}
