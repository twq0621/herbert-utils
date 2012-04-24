package net.snake.gamemodel.friend.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.BlackListComparator;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.friend.response.BlackListAddMsg10354;
import net.snake.gamemodel.friend.response.BlackListDelMsg10360;
import net.snake.gamemodel.friend.response.BlackListMsg10358;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 黑名单列表管理器
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleBlackListManager {
	public static final byte blackListRelation = 1;
	private MyFriendManager myFriendManager;
	private Map<Integer, CharacterFriend> blackList = new ConcurrentHashMap<Integer, CharacterFriend>(); // 好友列表

	public RoleBlackListManager(MyFriendManager myFriendManager) {
		this.myFriendManager = myFriendManager;
	}

	public void putCharacterFriend(CharacterFriend cf) {
		this.blackList.put(cf.getFriendId(), cf);
	}

	public CharacterFriend getCharacterFriend(int friendId) {
		return this.blackList.get(friendId);
	}

	public MyFriendManager getMyFriendManager() {
		return myFriendManager;
	}

	public Collection<CharacterFriend> getBalackListCollection() {
		return blackList.values();
	}

	/**
	 * 黑名单列表
	 */
	public void sendListToClient() {
		Collection<CharacterFriend> collection = blackList.values();
		myFriendManager.updateCharacterFriendCollection(collection);
		List<CharacterFriend> list = new ArrayList<CharacterFriend>();
		list.addAll(collection);
		Collections.sort(list, new BlackListComparator());
		myFriendManager.sendMsgToCharacter(new BlackListMsg10358(list));
	}

	/**
	 * 添加黑名单操作
	 * 
	 * @param friendId
	 */
	public void addBlackList(int blackId) {
		if (myFriendManager.getRoleWedingManager().isWeddingWith(blackId)) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17008));
			return;
		}
		if (blackId == myFriendManager.getCharacter().getId()) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 730));
			return;
		}
		if (myFriendManager.getRoleFriendManager().getCharacterFriend(blackId) != null) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 731));
			return;
		}
		if (blackList.get(blackId) != null) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 732));
			return;
		}
		if (blackList.size() >= 30) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 733));
			return;
		}
		CharacterFriend cf = createBlackList(myFriendManager.getCharacter(), blackId, blackListRelation);
		if (cf == null) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 734));
			return;
		}
		blackList.put(cf.getFriendId(), cf);
		CharacterFriendManager.getInstance().asynInsertCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new BlackListAddMsg10354(cf));
	}

	/**
	 * 创建黑名单好友
	 * 
	 * @param character
	 * @param friendId
	 * @param relationType
	 * @return
	 */
	private CharacterFriend createBlackList(Hero character, int friendId, byte relationType) {
		Hero his = GameServer.vlineServerManager.getCharacterById(friendId);
		if (his == null) {
			return null;
		}
		CharacterFriend cf = CharacterFriend.createCharacterFriend(character.getId(), friendId, his.getName(), relationType);
		myFriendManager.getRoleOnlineFriendManager().addCharacterFriend(his);
		cf.updateInfo(his);
		return cf;
	}

	/**
	 * 删除黑名单
	 * 
	 * @param deleteId
	 */
	public void deleteBlackList(int deleteId) {
		if (blackList.get(deleteId) == null) {
			return;
		}
		CharacterFriend cf = blackList.remove(deleteId);
		CharacterFriendManager.getInstance().asynDeleteCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new BlackListDelMsg10360(deleteId));
		deleteCharacterFriendUpdate(deleteId);
	}

	/**
	 * 删除黑名单更新在线关系
	 * 
	 * @param deleteId
	 */
	public void deleteCharacterFriendUpdate(int deleteId) {
		if (myFriendManager.getRoleBlackListManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		if (myFriendManager.getRoleLateLinkManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		myFriendManager.getRoleOnlineFriendManager().removeCharacterFriend(deleteId);
	}

	public void destroy() {
		blackList.clear();
	}
}
