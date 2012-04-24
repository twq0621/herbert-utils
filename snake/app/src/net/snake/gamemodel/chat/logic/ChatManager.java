package net.snake.gamemodel.chat.logic;

import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterController;
import net.snake.netio.ServerResponse;
import net.snake.netio.player.GamePlayer;
import org.apache.log4j.Logger;

/**
 * 角色聊天服务
 * 
 */
public class ChatManager extends CharacterController {
	/**
	 * 组队消息
	 */
	public final static int MSG_CHAT_TEAM = 1;
	/**
	 * 场景消息
	 */
	public final static int MSG_CHAT_SCENE = 2;
	/**
	 * 帮会消息
	 */
	public final static int MSG_CHAT_FACTION = 3;
	/**
	 * 世界消息
	 */
	public final static int MSG_CHAT_WORLD = 4;

	/**
	 * 喇叭消息
	 */
	public final static int MSG_CHAT_LABA = 5;

	private static Logger logger = Logger.getLogger(ChatManager.class);

	private byte ischat; // 0未禁言 1禁言*V
	private long speakTime = 0; // *V
	// public byte isAccessPriMsg = 0; // 1禁止私聊，0不禁止私聊 *V
	private volatile GamePlayer chatSession = null;
	private PrivateChatManager myPrivateChatManager;
	private RoleTeamManager myRoleTeamManager;
	private RoleSceneManager myRoleSceneManager;
	private RoleFactionManager myRoleFactionManager;
	private RoleAllChatManager myRoleAllChatManager;
	private ChatLabaSpeakManager myRoleLabaManager;
	/**
	 * 聊天服务初始化标记
	 */
	private boolean isInitial = false;

	/**
	 * 聊天服务己初始化标记
	 * 
	 * @return
	 */
	public boolean isInitial() {
		return isInitial;
	}

	/**
	 * 初始化聊天服务
	 * 
	 * @param session
	 */
	public void Initail(GamePlayer session) {
		// 私聊频道
		myPrivateChatManager = new PrivateChatManager(this);
		// 队伍频道
		myRoleTeamManager = new RoleTeamManager(this);
		// 普通聊天频道
		myRoleSceneManager = new RoleSceneManager(this);
		// 帮会频道
		myRoleFactionManager = new RoleFactionManager(this);
		// 世界频道
		myRoleAllChatManager = new RoleAllChatManager(this);
		myRoleLabaManager = new ChatLabaSpeakManager(this);
		// 建立聊天会话
		chatSession = session;
		isInitial = true;
		ischat = character.getIsallowChat();
		speakTime = character.getAllowchatStarttime().getTime();
	}

	/**
	 * 是否禁言 0:未禁 , 非0:禁言
	 * 
	 * @return
	 */
	public byte getIschat() {
		return ischat;
	}

	public void setIschat(byte ischat) {
		this.ischat = ischat;
	}

	public void setSpeakTime(long speakTime) {
		this.speakTime = speakTime;
	}

	boolean getIsAccessPriMsg() {
		return getCharacter().getCharacterLimitConfig().isOtherAbleSendMsgToMe();
	}

	public GamePlayer getChatSession() {
		return chatSession;
	}

	public ChatManager(Hero character) {
		super(character);
	}

	/**
	 * 到开口说话的时间啦吗？
	 * 
	 * @return
	 */
	boolean isSpeak() {
		long time = speakTime - System.currentTimeMillis();
		if (time > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 发送给多人的消息
	 * 
	 * @param content
	 * @param type
	 *            组队消息 ChatManager.MSG_CHAT_TEAM=1; 场景消息MSG_CHAT_SCENE=2; 帮会消息
	 *            MSG_CHAT_FACTION=3;世界消息 MSG_CHAT_WORLD=4;喇叭消息MSG_CHAT_LABA=5;
	 */
	public void sendChatMsg(String content, int type) {
		switch (type) {
		case MSG_CHAT_TEAM:
			myRoleTeamManager.sendTeamMsg(content);
			break;
		case MSG_CHAT_SCENE:
			myRoleSceneManager.sendScenemMsg(content);
			break;
		case MSG_CHAT_FACTION:
			myRoleFactionManager.sendFactionMsg(content);
			break;
		case MSG_CHAT_WORLD:
			myRoleAllChatManager.sendWorldMsg(content);
			break;
		case MSG_CHAT_LABA:
			myRoleLabaManager.sendLabaMsg(content);
			break;
		default:
			logger.error("err chat msg Type " + type + " right type is teammsg==ChatManager.MSG_CHAT_TEAM=1; scenemsg==MSG_CHAT_SCENE=2; bangmsg==MSG_CHAT_FACTION=3;worldmsg==MSG_CHAT_WORLD=4;labamsg==MSG_CHAT_LABA=5");
			break;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("[type=" + type + "]" + " msg " + content);
		}

	}

	/**
	 * 发送私聊消息
	 * 
	 * @param roleName
	 * @param content
	 */
	public void sendPrivateMsg(String roleName, String content) {
		myPrivateChatManager.sendPrivateMsgToOther(roleName, content);
	}

	/**
	 * 发送到当前角色的聊天消息
	 * 
	 * @param msg
	 */
	void sendMsg(ServerResponse msg) {
		if (chatSession == null || !isInitial()) {
			logger.warn(getCharacter().getId() + "'s chat session has been initialed,msg " + msg.getMsgCode() + ",can not be sent");
			return;
		}
		chatSession.sendMsg(msg);
	}

	/**
	 * 发送给其它角色的聊天消息
	 * 
	 * @param target
	 * @param msg
	 */
	void sendMsgToOther(Hero target, ServerResponse msg) {
		if (target == null) {
			return;
		}
		// 如果发送者在接收者的黑名单中则不发送
		CharacterFriend characterFriend = target.getMyFriendManager().getRoleBlackListManager().getCharacterFriend(character.getId());
		if (characterFriend != null) {
			return;
		}
		target.getChatManager().sendMsg(msg);
	}

	/**
	 * 
	 */
	public void downLine() {
		if (chatSession == null) {
			return;
		}
		chatSession.removeIOSessionMap();
		getChatSession().close();
	}

	@Override
	public int getAllObjInHeap() throws Exception {
		return 0;
	}

}
