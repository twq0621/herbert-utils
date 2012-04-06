package game.chat.service;

import game.chat.common.ChatConstants;
import game.chat.common.ErrorCode;
import game.chat.dao.UserDao;
import game.chat.dto.Chat_C2S;
import game.chat.dto.Chat_S2C;
import game.chat.dto.ConnectChat_C2S;
import game.chat.dto.ConnectChat_S2C;
import game.entity.ChatingRole;

import java.util.Map;
import java.util.Map.Entry;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.util.internal.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.protobuf.InvalidProtocolBufferException;

@Component
public class ChatManager {

	private static Logger logger = LoggerFactory.getLogger(ChatManager.class);

	private Map<Integer, ChatingRole> chatingRoles = new ConcurrentHashMap<Integer, ChatingRole>();

	private Map<String, ChatingRole> chatingRolesNameKey = new ConcurrentHashMap<String, ChatingRole>();

	@Autowired
	private UserDao userDao;

	public void connectChat(Channel channel, ConnectChat_C2S reqMsg) throws InvalidProtocolBufferException {
		logger.info("connect to chat server!roleName={}", reqMsg.getRoleName());
		ConnectChat_S2C retMsg = new ConnectChat_S2C();
		boolean loginValid = userDao.checkLoginValid(reqMsg.getUserName(), reqMsg.getSid());
		//聊天session无效
		//		if (!loginValid) {
		//			retMsg.setCode(ErrorCode.CHAT_SESSION_INVALID);
		//			channel.write(retMsg);
		//			return;
		//		}
		//判断是否为当前角色的聊天
		//		boolean roleValid = userDao.checkRoleValid(reqMsg.getUserName(), reqMsg.getRoleName());
		//		if (!roleValid) {
		//			retMsg.setCode(ErrorCode.CHAT_ROLE_INVALID);
		//			channel.write(retMsg);
		//			return;
		//		}
		//存入玩家信息
		ChatingRole chatingRole = chatingRoles.get(channel.getId());
		if (chatingRole == null) {
			chatingRole = new ChatingRole(reqMsg.getUserName(), reqMsg.getRoleName(), channel);
			chatingRoles.put(channel.getId(), chatingRole);
			chatingRolesNameKey.put(reqMsg.getRoleName(), chatingRole);
		}
		//返回成功信息
		retMsg.setCode(ErrorCode.SUCCESS);
		channel.write(retMsg);
	}

	/**
	 * 发送给所有玩家
	 * @param sendAllMsg
	 */
	public void sendAll(Chat_S2C sendAllMsg) {
		for (Entry<Integer, ChatingRole> entry : chatingRoles.entrySet()) {
			entry.getValue().send(sendAllMsg);
		}
	}

	/**
	 * 移除玩家的缓存
	 * @param channel
	 */
	public void removeRole(Channel channel) {
		logger.info("channel close, remove role!");
		ChatingRole chatingRole = chatingRoles.get(channel.getId());
		if (chatingRole != null) {
			chatingRolesNameKey.remove(chatingRole.getrName());
			chatingRoles.remove(channel.getId());
		}
	}

	/**
	 * 进行聊天
	 * @param channel
	 * @param reqMsg
	 */
	public void chat(Channel channel, Chat_C2S reqMsg) {
		logger.info("role chat!");
		Chat_S2C retMsg = new Chat_S2C();
		ChatingRole chatingRole = chatingRoles.get(channel.getId());
		if (chatingRole == null) {
			retMsg.setCode(ErrorCode.CHAT_ROLE_INVALID);
			channel.write(retMsg);
			return;
		}
		retMsg.setSenderName(chatingRole.getrName());
		retMsg.setContent(reqMsg.getContent());
		switch (reqMsg.getType()) {
		case ChatConstants.CHAT_TYPE_ALL:
			sendAll(retMsg);
			break;
		case ChatConstants.CHAT_TYPE_PRIVATE:
			chatPrivate(chatingRole, reqMsg.getContent(), reqMsg.getTargetRole());
			break;
		case ChatConstants.CHAT_TYPE_FRIEND:
			chatFriend(chatingRole, reqMsg.getContent());
			break;
		default:
			break;
		}
	}

	/**
	 * 好友聊天
	 * @param chatingRole
	 * @param content
	 */
	private void chatFriend(ChatingRole chatingRole, String content) {
		//获取好友列表
		//发送聊天消息
	}

	private void chatPrivate(ChatingRole chatingRole, String content, String targetRole) {
		//判断目标角色是否存在
		Chat_S2C retMsg = new Chat_S2C();
		ChatingRole targetChatingRole = chatingRolesNameKey.get(targetRole);
		if (targetChatingRole == null) {
			retMsg.setCode(ErrorCode.CHAT_TARGET_OFFLINE);
			chatingRole.send(retMsg);
			return;
		}
		//TODO 是否在对方的黑名单中
		//发送聊天消息
		retMsg.setSenderName(chatingRole.getrName());
		retMsg.setContent(content);
		targetChatingRole.send(retMsg);
	}

}
