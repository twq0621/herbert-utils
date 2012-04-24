package net.snake.gamemodel.friend.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.snake.GameServer;
import net.snake.ai.formula.DistanceFormula;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.friend.bean.FriendComparator;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.friend.response.EnemyInfoMsg10382;
import net.snake.gamemodel.friend.response.FriendAddMsg10304;
import net.snake.gamemodel.friend.response.FriendDelMsg10310;
import net.snake.gamemodel.friend.response.FriendListMsg10308;
import net.snake.gamemodel.friend.response.FriendaddMsg10306;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.team.logic.Team;
import net.snake.serverenv.cache.CharacterCacheManager;

/**
 * 好友列表管理器
 * 
 * Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class RoleFriendManager {
	public static final byte FriendRelation = 0;
	private MyFriendManager myFriendManager;
	private Map<Integer, CharacterFriend> friend = new ConcurrentHashMap<Integer, CharacterFriend>(); // 好友列表

	public RoleFriendManager(MyFriendManager myFriendManager) {
		this.myFriendManager = myFriendManager;
	}

	public void putCharacterFriend(CharacterFriend cf) {
		this.friend.put(cf.getFriendId(), cf);
	}

	public CharacterFriend getCharacterFriend(int friendId) {
		return this.friend.get(friendId);
	}

	public MyFriendManager getMyFriendManager() {
		return myFriendManager;
	}

	public void sendListToClient() {
		Collection<CharacterFriend> collection = friend.values();
		myFriendManager.updateCharacterFriendCollection(collection);
		List<CharacterFriend> list = new ArrayList<CharacterFriend>();
		list.addAll(collection);
		Collections.sort(list, new FriendComparator());
		myFriendManager.sendMsgToCharacter(new FriendListMsg10308(list));
	}

	/**
	 * 添加好友操作
	 * 
	 * @param friendId
	 */
	public void addFriend(int friendId) {
		Hero character = myFriendManager.getCharacter();
		if (myFriendManager.getRoleWedingManager().isWeddingWith(friendId)) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17009));
			return;
		}
		if (friendId == character.getId()) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 738));
			return;
		}
		Hero hisRole = GameServer.vlineServerManager.getCharacterById(friendId);
		if (hisRole != null && !hisRole.getCharacterLimitConfig().isOtherAbleJoinMyFriend()) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 739));
			return;
		}
		if (myFriendManager.getRoleBlackListManager().getCharacterFriend(friendId) != null) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 740));
			return;
		}
		if (friend.get(friendId) != null) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 741));
			return;
		}
		if (friend.size() >= 30) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 742));
			return;
		}
		CharacterFriend cf = createFriend(myFriendManager.getCharacter(), friendId, FriendRelation);
		if (cf == null) {
			myFriendManager.sendMsgToCharacter(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 734));
			return;
		}
		friend.put(cf.getFriendId(), cf);
		CharacterFriendManager.getInstance().asynInsertCharacterFriend(myFriendManager.getCharacter(), cf);
		myFriendManager.sendMsgToCharacter(new FriendAddMsg10304(cf));
		character.getMyCharacterAchieveCountManger().getFriendCount().friendCount(friend.size());
	}

	/**
	 * 接受被添加为好友的消息 参数 为添加者id
	 */
	public void accessAddFriendMsg(Hero other) {
		if (other == null) {
			return;
		}
		byte isTag = 0;
		if (friend.get(other.getId()) != null) {
			isTag = 1;
		}
		myFriendManager.sendMsgToCharacter(new FriendaddMsg10306(other, isTag));
	}

	private CharacterFriend createFriend(Hero character, int friendId, byte relationType) {
		Hero hisRole = GameServer.vlineServerManager.getCharacterById(friendId);
		if (hisRole != null) {
			CharacterFriend cf = CharacterFriend.createCharacterFriend(character.getId(), friendId, hisRole.getName(), relationType);
			// 更新 好友信息
			myFriendManager.getRoleOnlineFriendManager().addCharacterFriend(hisRole);
			hisRole.getMyFriendManager().getRoleFriendManager().accessAddFriendMsg(character);
			cf.updateInfo(hisRole);
			return cf;
		}
		CharacterCacheEntry his = CharacterCacheManager.getInstance().getCharacterCacheEntryById(friendId);
		if (his == null) {
			return null;
		}
		CharacterFriend cf = CharacterFriend.createCharacterFriend(character.getId(), friendId, his.getName(), relationType);
		return cf;
	}

	/**
	 * s删除好友
	 * 
	 * @param deleteId
	 */
	public CharacterFriend deleteFriend(int deleteId) {
		if (friend.get(deleteId) == null) {
			// myFriendManager.sendMsgToCharacter(new PrompMsg(
			// TipMsg.MSGPOSITION_ERRORTIP, 743));
			return null;
		}
		CharacterFriend cf = friend.remove(deleteId);
		Hero character = myFriendManager.getCharacter();
		CharacterFriendManager.getInstance().asynDeleteCharacterFriend(character, cf);
		myFriendManager.sendMsgToCharacter(new FriendDelMsg10310(deleteId));
		deleteCharacterFriendUpdate(deleteId);
		character.getMyCharacterAchieveCountManger().getFriendCount().friendCount(friend.size());
		return cf;
	}

	public void deleteCharacterFriendUpdate(int deleteId) {
		if (myFriendManager.getRoleEnemyManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		if (myFriendManager.getRoleLateLinkManager().getCharacterFriend(deleteId) != null) {
			return;
		}
		myFriendManager.getRoleOnlineFriendManager().removeCharacterFriend(deleteId);
	}

	/**
	 * 组队战斗增加好感度
	 */
	public void updateFavorWhenTeam() {
		Hero character = myFriendManager.getCharacter();
		if (!character.getMyTeamManager().isTeam()) {
			return;
		}
		int myId = character.getId();
		Team t = character.getMyTeamManager().getMyTeam();
		List<Hero> list = t.getCharacterPlayers();
		int scenId = character.getScene();
		for (Hero teamer : list) {
			if (teamer.getId() != myId) {
				CharacterFriend cf = character.getMyFriendManager().getRoleFriendManager().getCharacterFriend(teamer.getId());
				if (cf != null) {
					if (teamer.getScene() == scenId && DistanceFormula.getDistanceRound(character.getX(), character.getY(), teamer.getX(), teamer.getY()) < 20) {
						cf.updateTeamFavor(character, cf.getTeamFavor() + 1);
					}
				}
			}
		}
	}

	/**
	 * 通知我好友我 杀死某人
	 * 
	 * @param other
	 */
	public void sendMyFriendMeKill(Hero other) {
		Collection<Hero> collection = myFriendManager.getRoleOnlineFriendManager().getAddMeFriendCollection();
		Hero character = myFriendManager.getCharacter();
		Scene scene = character.getSceneRef();
		int num = character.getVlineserver().getLineid();
		//
		PrompMsg msg = new PrompMsg(TipMsg.QICHU_MSG, 1004, character.getViewName(), num + "", scene.getShowName(), other.getViewName());
		for (Hero fc : collection) {
			if (this.getCharacterFriend(fc.getId()) == null) {
				continue;
			}
			if (fc != null && fc.getId() != other.getId()) {
				CharacterFriend myfc = fc.getMyFriendManager().getRoleFriendManager().getCharacterFriend(character.getId());
				if (myfc != null) {
					fc.sendMsg(msg);
				}
			}
		}
	}

	/**
	 * 通知好友我被某人杀死
	 * 
	 * @param other
	 */
	public void sendMyFriendKillMeBy(Hero other) {
		Collection<Hero> collection = myFriendManager.getRoleOnlineFriendManager().getAddMeFriendCollection();
		Hero character = myFriendManager.getCharacter();
		Scene scene = character.getSceneRef();
		int num = character.getVlineserver().getLineid();
		PrompMsg msg = new PrompMsg(TipMsg.QICHU_MSG, 1005, character.getViewName(), other.getViewName(), num + "", scene.getShowName());
		for (Hero fc : collection) {
			if (this.getCharacterFriend(fc.getId()) == null) {
				continue;
			}
			if (fc != null && fc.getId() != other.getId()) {
				CharacterFriend myfc = fc.getMyFriendManager().getRoleFriendManager().getCharacterFriend(character.getId());
				if (myfc != null) {
					fc.sendMsg(msg);
				}
			}
		}
	}

	/**
	 * 好友总数量
	 * 
	 * @return
	 */
	public int getFriendCount() {
		return friend.size();
	}

	public Map<Integer, CharacterFriend> getFriend() {
		return friend;
	}

	public void setFriend(Map<Integer, CharacterFriend> friend) {
		this.friend = friend;
	}

	public void destroy() {
		friend.clear();
	}

	/**
	 * 打探好友
	 * 
	 * @param enemyId
	 */
	public void getOtherInfoById(int friendId) {
		Hero hisRole = myFriendManager.getCharacter().getVlineserver().getOnlineManager().getByID(friendId);
		if (hisRole != null) {
			boolean b = myFriendManager.getCharacter().getCharacterGoodController().deleteGoodsFromBag(GoodItemId.SHANGSHANFAE_ID, 1);
			if (!b) {
				myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(friendId, 12002));
				return;
			}
			myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(hisRole));
			return;
		}
		Hero roledate = GameServer.vlineServerManager.getCharacterById(friendId);
		if (roledate != null) {
			myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(friendId, 12003, roledate.getVlineserver().getLineid() + "", roledate.getVlineserver().getLineid() + ""));
			return;
		}
		myFriendManager.sendMsgToCharacter(new EnemyInfoMsg10382(friendId, 12001));
	}
}
