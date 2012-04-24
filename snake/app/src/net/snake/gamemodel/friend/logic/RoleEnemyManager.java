package net.snake.gamemodel.friend.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.friend.bean.EnemyComparator;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.friend.response.EnemyAddMsg10346;
import net.snake.gamemodel.friend.response.EnemyDelMsg10344;
import net.snake.gamemodel.friend.response.EnemyHateValueUpdateMsg10318;
import net.snake.gamemodel.friend.response.EnemyInfoMsg10382;
import net.snake.gamemodel.friend.response.EnemyListMsg10342;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 仇人列表管理器
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleEnemyManager {
	public static final byte enemyRelation = 2;
	private MyFriendManager myFriendManager;
	public static int hateMax = 9999;
	public static int hateMin = -9999;
	private Map<Integer, CharacterFriend> enemy = new ConcurrentHashMap<Integer, CharacterFriend>(); // 好友列表

	public RoleEnemyManager(MyFriendManager myFriendManager) {
		this.myFriendManager = myFriendManager;
	}

	public void putCharacterFriend(CharacterFriend cf) {
		this.enemy.put(cf.getFriendId(), cf);
	}

	public CharacterFriend getCharacterFriend(int friendId) {
		return this.enemy.get(friendId);
	}

	public MyFriendManager getMyFriendManager() {
		return myFriendManager;
	}

	public void sendListToClient() {
		myFriendManager.sendMsgToCharacter(new EnemyListMsg10342(getEnemtyList()));
	}

	/**
	 * 获取仇恨列表
	 * 
	 * @return
	 */
	public List<CharacterFriend> getEnemtyList() {
		Collection<CharacterFriend> collection = enemy.values();
		myFriendManager.updateCharacterFriendCollection(collection);
		List<CharacterFriend> list = new ArrayList<CharacterFriend>();
		list.addAll(collection);
		Collections.sort(list, new EnemyComparator());
		return list;
	}

	/**
	 * 添加更新仇人单操作
	 * 
	 * @param friendId
	 */
	public void addOrUpdateEneny(int enemyId) {
		if (enemyId == myFriendManager.getCharacter().getId()) {
			return;
		}
		CharacterFriend cf = enemy.get(enemyId);
		if (cf == null) {
			cf = createEnemy(myFriendManager.getCharacter(), enemyId, enemyRelation);
			if (cf == null) {
				return;
			}
			if (enemy.size() >= 30) {
				myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 736));
				return;
			}
			cf.setHateValue(1);
			enemy.put(cf.getFriendId(), cf);
			CharacterFriendManager.getInstance().asynInsertCharacterFriend(myFriendManager.getCharacter(), cf);
			myFriendManager.sendMsgToCharacter(new EnemyAddMsg10346(cf));
			return;
		}
		if (cf.getHateValue() >= hateMax) {
			return;
		}
		cf.setHateValue(cf.getHateValue() + 1);
		CharacterFriendManager.getInstance().asynUpdateCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new EnemyHateValueUpdateMsg10318(cf));
	}

	/**
	 * 当我杀死对方时 减少我对对方的仇恨度
	 */
	public void updateEnenyByMeKillOther(Hero other) {
		if(other.getId()==myFriendManager.getCharacter().getId()){
			return;
		}
		CharacterFriend cf = enemy.get(other.getId());
		if (cf == null) {
			cf = createEnemy(myFriendManager.getCharacter(), other.getId(), enemyRelation);
			if (cf == null) {
				return;
			}
			if (enemy.size() >= 30) {
				myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 736));
				return;
			}
			cf.setHateValue(-1);
			enemy.put(cf.getFriendId(), cf);
			CharacterFriendManager.getInstance().asynInsertCharacterFriend(myFriendManager.getCharacter(), cf);
			myFriendManager.sendMsgToCharacter(new EnemyAddMsg10346(cf));
			return;
		}
		if (cf.getHateValue() <= hateMin) {
			return;
		}
		cf.setHateValue(cf.getHateValue() - 1);
		CharacterFriendManager.getInstance().asynUpdateCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new EnemyHateValueUpdateMsg10318(cf));
	}

	private CharacterFriend createEnemy(Hero character, int friendId, byte relationType) {
		Hero his = GameServer.vlineServerManager.getCharacterById(friendId);
		if (his == null) {
			return null;
		}
		CharacterFriend cf = CharacterFriend.createCharacterFriend(character.getId(), friendId, his.getName(), relationType);
		cf.setHateValue(1);
		myFriendManager.getRoleOnlineFriendManager().addCharacterFriend(his);
		cf.updateInfo(his);
		return cf;
	}

	public void deleteEmeny(int enemyId) {
		if (enemy.get(enemyId) == null) {
			return;
		}
		CharacterFriend cf = enemy.remove(enemyId);
		CharacterFriendManager.getInstance().asynDeleteCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new EnemyDelMsg10344(enemyId));
		deleteCharacterFriendUpdate(enemyId);
	}

	public void deleteCharacterFriendUpdate(int deleteId) {
		if (myFriendManager.getRoleFriendManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		if (myFriendManager.getRoleBlackListManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		if (myFriendManager.getRoleLateLinkManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		myFriendManager.getRoleOnlineFriendManager().removeCharacterFriend(deleteId);
	}

	public void getEnemyInfoById(int enemyId) {
		Hero hisRole = myFriendManager.getCharacter().getVlineserver().getOnlineManager().getByID(enemyId);
		if (hisRole != null) {
			boolean b = myFriendManager.getCharacter().getCharacterGoodController().deleteGoodsFromBag(GoodItemId.SHANGSHANFAE_ID, 1);
			if (!b) {
				myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(enemyId, 12002));
				return;
			}
			myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(hisRole));
			return;
		}
		Hero roledate = GameServer.vlineServerManager.getCharacterById(enemyId);
		if (roledate != null) {
			myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(enemyId, 12003, roledate.getVlineserver().getLineid() + "", roledate.getVlineserver().getLineid() + ""));
			return;
		}
		myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(enemyId, 12001));
	}

	public void destroy() {
		enemy.clear();
	}
}
