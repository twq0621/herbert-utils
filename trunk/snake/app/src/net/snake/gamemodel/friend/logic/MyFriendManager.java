package net.snake.gamemodel.friend.logic;

import net.snake.GameServer;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.friend.response.BiaoqingUpdateMsg10372;
import net.snake.gamemodel.friend.response.FriendBaitanUpdateMsg10322;
import net.snake.gamemodel.friend.response.XinBiaoFuqiMsg10366;
import net.snake.gamemodel.friend.response.XinqingUpdateMsg10374;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.logic.RoleCouplesSpeakManager;
import net.snake.netio.ServerResponse;
import net.snake.shell.Options;


import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 角色与其他角色关系管理器 好友 仇人 黑名单 最近联系人,夫妻关系,以及在线兵器角色在某人的关系列表中的管理 Copyright (c) 2011 by
 * 
 * @author serv_dev
 */
public class MyFriendManager {
	private static Logger logger = Logger.getLogger(MyFriendManager.class);

	private Hero character;
	private final RoleOnlineFriendManager roleOnlineFriendManager = new RoleOnlineFriendManager(this);
	private final RoleLateLinkManager roleLateLinkManager = new RoleLateLinkManager(this);
	private final RoleFriendManager roleFriendManager = new RoleFriendManager(this);
	private final RoleEnemyManager roleEnemyManager = new RoleEnemyManager(this);
	private final RoleBlackListManager roleBlackListManager = new RoleBlackListManager(this);
	private final RoleWedingManager roleWedingManager = new RoleWedingManager(this);
	private final RoleCouplesSpeakManager roleCouplesSpeakManager = new RoleCouplesSpeakManager(this);

	public int getAllObjInHeap() throws Exception {
		return roleOnlineFriendManager.getAddMeFriendCollection().size() + roleOnlineFriendManager.getMyFriendCollection().size() + roleLateLinkManager.getLataLyLink().size()
				+ roleFriendManager.getFriendCount() + roleEnemyManager.getEnemtyList().size() + roleBlackListManager.getBalackListCollection().size();
	}

	public MyFriendManager(Hero character) {
		this.character = character;
	}

	public Hero getCharacter() {
		return character;
	}

	/**
	 * 发送消息给玩家
	 * 
	 * @param msg
	 */
	public void sendMsgToCharacter(ServerResponse msg) {
		if (character == null) {
			return;
		}
		character.sendMsg(msg);
	}

	public RoleLateLinkManager getRoleLateLinkManager() {
		return roleLateLinkManager;
	}

	public RoleCouplesSpeakManager getRoleCouplesSpeakManager() {
		return roleCouplesSpeakManager;
	}

	public RoleWedingManager getRoleWedingManager() {
		return roleWedingManager;
	}

	public RoleFriendManager getRoleFriendManager() {
		return roleFriendManager;
	}

	public RoleEnemyManager getRoleEnemyManager() {
		return roleEnemyManager;
	}

	public RoleBlackListManager getRoleBlackListManager() {
		return roleBlackListManager;
	}

	public RoleOnlineFriendManager getRoleOnlineFriendManager() {
		return roleOnlineFriendManager;
	}

	/**
	 * 将存放玩家关系list，转化到相应需要Map中
	 * 
	 * @param characterFriendList
	 */
	public void init() {
		try {
			List<CharacterFriend> characterFriendList = CharacterFriendManager.getInstance().selecteListByCharacterId(character.getId());
			List<CharacterFriend> addMeFriendList = CharacterFriendManager.getInstance().selecteListByFriendId(character.getId());
			roleOnlineFriendManager.initFriendInfo(addMeFriendList, characterFriendList);
			if (characterFriendList == null || characterFriendList.size() == 0) {
				return;
			}
			for (CharacterFriend cf : characterFriendList) {
				if (cf.getRelationType() == 0) {
					this.roleFriendManager.putCharacterFriend(cf);
				} else if (cf.getRelationType() == 1) {
					this.roleBlackListManager.putCharacterFriend(cf);
				} else if (cf.getRelationType() == 2) {
					this.roleEnemyManager.putCharacterFriend(cf);
				} else if (cf.getRelationType() == 3) {
					this.roleLateLinkManager.putCharacterFriend(cf);
				}
			}
			roleWedingManager.init();
			character.getMyCharacterAchieveCountManger().getFriendCount().friendCount(roleFriendManager.getFriendCount());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	/**
	 * 更新好友信息
	 * 
	 * @param collection
	 */
	public void updateCharacterFriendCollection(Collection<CharacterFriend> collection) {
		for (CharacterFriend cf : collection) {
			Hero friend = roleOnlineFriendManager.getFriendById(cf.getFriendId());
			if (friend != null) {
				cf.setIsOnline(CommonUseNumber.byte1);
				cf.setLineNum((byte) friend.getVlineserver().getLineid());
				cf.setIsBaitan((byte) friend.getCharacterStatus());
			} else {
				cf.setIsOnline(CommonUseNumber.byte0);
				cf.setLineNum(CommonUseNumber.byte0);
				cf.setIsBaitan(CommonUseNumber.byte0);
			}
		}
	}

	/**
	 * 角色表情修改
	 * 
	 * @param biaoqing
	 */
	public void changeBiaoqing(String biaoqing) {
		// 表情修改
		character.setNowBiaoqing(biaoqing);
		character.sendMsg(new BiaoqingUpdateMsg10372(character));
	}

	/**
	 * 角色心情修改
	 * 
	 * @param xinqing
	 */
	public void changerXinqing(String xinqing) {
		if (BadWordsFilter.getInstance().hashBadWords(xinqing)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 729));
		}else{
			// 心情修改
			character.setNowXingqing(xinqing);
		}
		character.sendMsg(new XinqingUpdateMsg10374(character));
	}

	/**
	 * 发送夫妻关系人
	 */
	public void sendMyInfoAndFuqi() {
		character.sendMsg(new XinBiaoFuqiMsg10366(character, roleWedingManager.getFuqi()));
	}

	/**
	 * 玩家pk 被某玩家杀死 处理pk玩家两者的关系记入
	 * 
	 * @param other
	 */
	public void characterPkDieBy(Hero other) {
		// 在跨服论剑台战斗胜负，不会记录仇人名单。
		if (Options.IsCrossServ)
			return;
		getRoleEnemyManager().addOrUpdateEneny(other.getId());
		if (roleWedingManager.isWedding()) {
			if (roleWedingManager.isWeddingWith(other.getId())) {
				return;
			}
			Hero wedder = GameServer.vlineServerManager.getCharacterById(roleWedingManager.getWedderId());
			if (wedder != null) {
				wedder.getMyFriendManager().getRoleEnemyManager().addOrUpdateEneny(other.getId());
			}
		}
		other.getMyFriendManager().mekillOther(character);
		getRoleFriendManager().sendMyFriendKillMeBy(other);
		other.getMyFriendManager().getRoleFriendManager().sendMyFriendMeKill(character);
	}

	public void mekillOther(Hero other) {
		getRoleEnemyManager().updateEnenyByMeKillOther(other);
		if (roleWedingManager.isWedding()) {
			if (roleWedingManager.isWeddingWith(other.getId())) {
				return;
			}
			Hero wedder = GameServer.vlineServerManager.getCharacterById(roleWedingManager.getWedderId());
			if (wedder != null) {
				wedder.getMyFriendManager().getRoleEnemyManager().updateEnenyByMeKillOther(other);
			}
		}

	}

	/**
	 * 角色下线
	 */
	public void downLine() {
		roleOnlineFriendManager.downline();
		roleCouplesSpeakManager.downline();
		roleWedingManager.downline();
	}

	/**
	 * 角色上线 初始化角色信息
	 */
	public void onLine() {
		sendMyInfoAndFuqi();
		roleFriendManager.sendListToClient();
		roleEnemyManager.sendListToClient();
		roleLateLinkManager.sendListToClient();
		roleBlackListManager.sendListToClient();
		roleOnlineFriendManager.online();
		roleCouplesSpeakManager.online();
		roleWedingManager.online();

	}

	public void destroy() {
		roleOnlineFriendManager.destroy();
		this.roleLateLinkManager.destroy();
		this.roleBlackListManager.destroy();
		this.roleFriendManager.destroy();
		this.roleEnemyManager.destroy();
	}

	/**
	 * 
	 */
	public void updateFavorWhenTeam() {
		roleFriendManager.updateFavorWhenTeam();
		roleWedingManager.updateFavorWhenTeam();
	}

	/**
	 * 更新关系列表状态
	 */
	public void updateCharacterState() {
		getRoleOnlineFriendManager().sendToAllAddmeFriendMsg(new FriendBaitanUpdateMsg10322(character));
		if (roleWedingManager.isWedding()) {
			character.getMyFriendManager().getRoleWedingManager().getFuqi().onlineUpdate(character);
		}

	}
}
