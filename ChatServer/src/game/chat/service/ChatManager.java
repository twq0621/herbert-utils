package game.chat.service;

import game.chat.common.ErrorCode;
import game.chat.dao.UserDao;
import game.chat.dto.ConnectChat_C2S;
import game.chat.dto.ConnectChat_S2C;
import game.chat.dto.SendAll_S2C;
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

	@Autowired
	private UserDao userDao;

	public void connectChat(Channel channel, ConnectChat_C2S reqMsg) throws InvalidProtocolBufferException {
		logger.info("connect to chat server!roleName={}", reqMsg.getRoleName());
		ConnectChat_S2C retMsg = new ConnectChat_S2C();
		boolean loginValid = userDao.checkLoginValid(reqMsg.getUserName(), reqMsg.getSid());
		//聊天session无效
		if (!loginValid) {
			retMsg.setCode(ErrorCode.CHAT_SESSION_INVALID);
			channel.write(retMsg);
			return;
		}
		//判断是否为当前角色的聊天
		boolean roleValid = userDao.checkRoleValid(reqMsg.getUserName(), reqMsg.getRoleName());
		if (!roleValid) {
			retMsg.setCode(ErrorCode.CHAT_ROLE_INVALID);
			channel.write(retMsg);
			return;
		}
		//存入玩家信息
		ChatingRole chatingRole = chatingRoles.get(channel.getId());
		if (chatingRole == null) {
			chatingRole = new ChatingRole(reqMsg.getUserName(), reqMsg.getRoleName(), channel);
			chatingRoles.put(channel.getId(), chatingRole);
		}
		//返回成功信息
		retMsg.setCode(ErrorCode.SUCCESS);
		channel.write(retMsg);
	}

	/**
	 * 发送给所有玩家
	 * @param sendAllMsg
	 */
	public void sendAll(SendAll_S2C sendAllMsg) {
		for (Entry<Integer, ChatingRole> entry : chatingRoles.entrySet()) {
			entry.getValue().send(sendAllMsg);
		}
	}
}
