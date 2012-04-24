package net.snake.gamemodel.friend.logic;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import net.snake.GameServer;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.friend.response.LateLinkAddMsg10336;
import net.snake.gamemodel.friend.response.LatelinkMsg10332;
import net.snake.gamemodel.friend.response.LatelyLinkDelMsg10334;
import net.snake.gamemodel.friend.response.LatelyLinkUpdateMsg10320;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 最近联系人列表管理器
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleLateLinkManager {
	public static final byte latelyLinkRelation = 3;
	private MyFriendManager myFriendManager;
	private List<CharacterFriend> lataLyLink = new ArrayList<CharacterFriend>(); // 最近联系人
																					// 好友关系==7

	public RoleLateLinkManager(MyFriendManager myFriendManager) {
		this.myFriendManager = myFriendManager;
	}

	public List<CharacterFriend> getLataLyLink() {
		return lataLyLink;
	}

	public void setLataLyLink(List<CharacterFriend> lataLyLink) {
		this.lataLyLink = lataLyLink;
	}

	public void putCharacterFriend(CharacterFriend cf) {
		this.lataLyLink.add(cf);
	}

	public CharacterFriend getCharacterFriend(int friendId) {
		for (int i = 0; i < lataLyLink.size(); i++) {
			CharacterFriend cf = lataLyLink.get(i);
			if (cf.getFriendId() == friendId) {
				return cf;
			}
		}
		return null;
	}

	public CharacterFriend removeCharacterFriend(int friendId) {
		for (int i = 0; i < lataLyLink.size(); i++) {
			CharacterFriend cf = lataLyLink.get(i);
			if (cf.getFriendId() == friendId) {
				lataLyLink.remove(i);
				return cf;
			}
		}
		return null;
	}

	public MyFriendManager getMyFriendManager() {
		return myFriendManager;
	}

	public void sendListToClient() {
		myFriendManager.updateCharacterFriendCollection(lataLyLink);
		myFriendManager.sendMsgToCharacter(new LatelinkMsg10332(lataLyLink));
	}

	/**
	 * 添加更新仇人单操作
	 * 
	 * @param friendId
	 */
	public synchronized void addOrUpdateLateLink(int lateLinkId) {
		if (lateLinkId == myFriendManager.getCharacter().getId()) {
			return;
		}
		CharacterFriend cf = getCharacterFriend(lateLinkId);
		if (cf == null) {
			cf = createLinkLate(myFriendManager.getCharacter(), lateLinkId, latelyLinkRelation);
			if (cf == null) {
				return;
			}
			if (lataLyLink.size() >= 30) {
				CharacterFriend delcf = lataLyLink.remove(0);
				CharacterFriendManager.getInstance().asynDeleteCharacterFriend(myFriendManager.getCharacter(), delcf);
			}
			lataLyLink.add(cf);
			CharacterFriendManager.getInstance().asynInsertCharacterFriend(myFriendManager.getCharacter(), cf);
			myFriendManager.sendMsgToCharacter(new LateLinkAddMsg10336(cf));
			return;
		}
		cf.setAddDate(new Date());
		lataLyLink.remove(cf);
		lataLyLink.add(cf);
		CharacterFriendManager.getInstance().asynUpdateCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new LatelyLinkUpdateMsg10320(cf));

	}

	private CharacterFriend createLinkLate(Hero character, int friendId, byte relationType) {
		Hero his = GameServer.vlineServerManager.getCharacterById(friendId);
		if (his == null) {
			return null;
		}
		CharacterFriend cf = CharacterFriend.createCharacterFriend(character.getId(), friendId, his.getName(), relationType);
		myFriendManager.getRoleOnlineFriendManager().addCharacterFriend(his);
		cf.updateInfo(his);
		return cf;
	}

	public void deleteLatelyLink(int lateLinkId) {
		CharacterFriend cf = removeCharacterFriend(lateLinkId);
		if (cf == null) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 744));
			return;
		}
		CharacterFriendManager.getInstance().asynDeleteCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new LatelyLinkDelMsg10334(lateLinkId));
		deleteCharacterFriendUpdate(lateLinkId);
	}

	public void deleteCharacterFriendUpdate(int deleteId) {
		if (myFriendManager.getRoleEnemyManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		if (myFriendManager.getRoleFriendManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		if (myFriendManager.getRoleBlackListManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		myFriendManager.getRoleOnlineFriendManager().removeCharacterFriend(deleteId);
	}

	public void destroy() {
		lataLyLink.clear();
	}
}
