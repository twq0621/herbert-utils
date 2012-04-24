package net.snake.gamemodel.friend.logic;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.friend.response.CharacterDownMsg10314;
import net.snake.gamemodel.friend.response.CharacterOnlineMsg10312;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

/**
 * 我在其他在线角色里表中，其他在线角色在我列表中的在线管理 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleOnlineFriendManager {
	private MyFriendManager myFriendManager;
	private Map<Integer, Hero> addMeFriendList = new ConcurrentHashMap<Integer, Hero>(); // 加我为好友，仇人
	private Map<Integer, Hero> myFriendList = new ConcurrentHashMap<Integer, Hero>(); // 我的好友，仇人

	public RoleOnlineFriendManager(MyFriendManager myFriendManager) {
		this.myFriendManager = myFriendManager;
	}

	public Hero getFriendById(int friendId) {
		return myFriendList.get(friendId);
	}

	/**
	 * 添加添加我为好友 黑名单 仇人最贱联系人的玩家
	 * 
	 * @param num
	 * @param img
	 */
	public void addAddMeFriendList(Hero character) {
		addMeFriendList.put(character.getId(), character);
	}

	/**
	 * 删除接触了我与对方好友关系的玩家
	 * 
	 * @param num
	 * @param img
	 */
	public Hero removeAddMeFriendList(int addMeId) {
		return addMeFriendList.remove(addMeId);
	}

	/**
	 * 添加在线的与我有关系的玩家
	 * 
	 * @param num
	 * @param img
	 */
	public void addmyFriendList(Hero character) {
		myFriendList.put(character.getId(), character);
	}

	/**
	 * 删除不在线或与我没关系的玩家
	 * 
	 * @param num
	 * @param img
	 */
	public Hero removemyFriendList(int friendId) {
		return myFriendList.remove(friendId);
	}

	/**
	 * 添加某人为好友 初始化信息
	 * 
	 * @param friendId
	 */
	public void addCharacterFriend(Hero friend) {
		if (friend == null) {
			return;
		}
		addmyFriendList(friend);
		friend.getMyFriendManager().getRoleOnlineFriendManager().addAddMeFriendList(myFriendManager.getCharacter());
	}

	/**
	 * 删除不在线或与我没关系的玩家 同时清除对方对我的引用
	 * 
	 * @param friendId
	 */
	public void removeCharacterFriend(int friend) {
		Hero myfriend = removemyFriendList(friend);
		if (myfriend != null) {
			myfriend.getMyFriendManager().getRoleOnlineFriendManager().removeAddMeFriendList(myFriendManager.getCharacter().getId());
		}
	}

	/**
	 * 初始化各线在线的加我为好友的玩家新信息，并发送我上线通知
	 * 
	 * @param addmeFriendList
	 * @param gateway
	 */
	private void initAddMeFriendList(List<CharacterFriend> addmeFriendList) {
		if (addmeFriendList == null || addmeFriendList.size() == 0) {
			return;
		}
		Hero character = myFriendManager.getCharacter();
		for (CharacterFriend cf : addmeFriendList) {
			Hero other = GameServer.vlineServerManager.getCharacterById(cf.getCharacterId());
			if (other != null) {
				addAddMeFriendList(other);
				other.getMyFriendManager().getRoleOnlineFriendManager().addmyFriendList(character);
			}
		}
	}

	/**
	 * 初始化我的在线好友
	 * 
	 * @param addmeFriendList
	 * @param gateway
	 */
	private void initMyFriendList(List<CharacterFriend> myFriendList) {
		if (myFriendList == null || myFriendList.size() == 0) {
			return;
		}
		Hero character = myFriendManager.getCharacter();
		for (CharacterFriend cf : myFriendList) {
			Hero other = GameServer.vlineServerManager.getCharacterById(cf.getFriendId());
			if (other != null) {
				addmyFriendList(other);
				other.getMyFriendManager().getRoleOnlineFriendManager().addAddMeFriendList(character);
			}
		}
	}

	public void initFriendInfo(List<CharacterFriend> addmeFriendList, List<CharacterFriend> myFriendList) {
		initAddMeFriendList(addmeFriendList);
		initMyFriendList(myFriendList);
	}

	private void myFriendListClear() {
		Hero character = myFriendManager.getCharacter();
		for (Hero other : myFriendList.values()) {
			other.getMyFriendManager().getRoleOnlineFriendManager().removeAddMeFriendList(character.getId());
		}
	}

	/**
	 * 我的状态发生变化通知所有添加我在对方列表中的玩家
	 * 
	 * @param msg
	 */
	public void sendToAllAddmeFriendMsg(ServerResponse msg) {
		for (Hero other : addMeFriendList.values()) {
			other.sendMsg(msg);
		}
	}

	public void sendDownLineMsg() {
		Hero character = myFriendManager.getCharacter();
		CharacterDownMsg10314 downlineMsg = new CharacterDownMsg10314(character.getId());
		sendToAllAddmeFriendMsg(downlineMsg);
		for (Hero other : addMeFriendList.values()) {
			if (myFriendManager.getRoleFriendManager().getCharacterFriend(other.getId()) != null) {
				if (other.getMyFriendManager().getRoleFriendManager().getCharacterFriend(character.getId()) != null) {
					other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17007, character.getViewName()));
				}
			}
		}
	}

	public void downline() {
		sendDownLineMsg();
		myFriendListClear();
		myAddMeFriendClear();
	}

	/**
	 * 清除添加我为好友的角色对我的角色引用
	 */
	private void myAddMeFriendClear() {
		Hero character = myFriendManager.getCharacter();
		for (Hero other : addMeFriendList.values()) {
			other.getMyFriendManager().getRoleOnlineFriendManager().removemyFriendList(character.getId());
		}
	}

	public void online() {
		sendOnLineMsg();
	}

	public void sendOnLineMsg() {
		Hero character = myFriendManager.getCharacter();
		CharacterOnlineMsg10312 onlineMsg = new CharacterOnlineMsg10312(character);
		sendToAllAddmeFriendMsg(onlineMsg);
		for (Hero other : addMeFriendList.values()) {
			if (myFriendManager.getRoleFriendManager().getCharacterFriend(other.getId()) != null) {
				if (other.getMyFriendManager().getRoleFriendManager().getCharacterFriend(character.getId()) != null) {
					other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17006, character.getViewName()));
				}
			}
		}
	}

	public Collection<Hero> getAddMeFriendCollection() {
		return addMeFriendList.values();
	}

	public Collection<Hero> getMyFriendCollection() {
		return myFriendList.values();
	}

	public void destroy() {
		addMeFriendList.clear();
		myFriendList.clear();
	}
}
