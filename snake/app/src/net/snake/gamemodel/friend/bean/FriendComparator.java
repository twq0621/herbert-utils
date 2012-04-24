package net.snake.gamemodel.friend.bean;

import java.util.Comparator;


public class FriendComparator implements Comparator<CharacterFriend>{

	@Override
	public int compare(CharacterFriend o1, CharacterFriend o2) {
		int value=o1.getFavor();
		int ovalue=o2.getFavor();
		if ( o1.getIsOnline()>o2.getIsOnline() ) {
			return -1;
		} else if(o1.getIsOnline()<o2.getIsOnline()){
			return 1;
		}else{
			if(value>ovalue){
			return -1;
			}else if(value<ovalue){
				return 1;
			}else{
				if(o1.getFriendId()>o2.getFriendId()){
					return -1;
				}else{
					return 1;
				}
			}
		}
	}

}
