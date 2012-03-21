package test;

import gear.amf3.Amf3CP;
import gear.codec.ASObject;
import gear.io.Amf3Request;
import gear.robot.ARobot;
import gear.robot.IRobotHandler;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnterGameRobot extends ARobot {

	private static Logger logger = LoggerFactory
			.getLogger(EnterGameRobot.class);

	private int roleIndex;

	private int maxMsgCount;

	private int maxThreadCount;

	private int currentMsgCount = 0;

	private long chatTakeMs = 0;

	private long chatStartTime;

	public EnterGameRobot(IRobotHandler handler, Amf3CP cp, String username,
			int roleIndex) {
		super(handler, cp, username);
		this.roleIndex = roleIndex;
	}

	@Override
	protected void initCallPool() {
		_callPool.addCallback(this, "call_login");
		_callPool.addCallback(this, "call_createRole");
		_callPool.addCallback(this, "call_joinGame");
		_callPool.addCallback(this, "call_broadcast");
		_callPool.addCallback(this, "call_taskInit");
		_callPool.addCallback(this, "call_initSkill");
		_callPool.addCallback(this, "call_myEnterCombat");
		_callPool.addCallback(this, "call_updateGiftList");
		_callPool.addCallback(this, "addNotice");
	}

	@Override
	public void think() {
		connect();
		login();
	}

	public void login() {
		send(new Amf3Request("login", "xlm" + roleIndex, "xlm" + roleIndex));
	}

	public void call_createRole(ASObject retAsObj) {
		System.out.println("receive call_createRole!");
		Object subObj = ((Object[]) retAsObj.get("args"))[0];
		if (subObj instanceof Integer) {
			int retCode = (Integer) subObj;
			System.out.println("createRole retCode:" + retCode);
		} else {
			ASObject subAsObj = (ASObject) subObj;
			System.out.println("createRole " + subAsObj);
		}
	}

	public void call_joinGame(ASObject retAsObj) {
		logger.info(retAsObj.toString());
		sendChatMsg();
	}

	public void call_broadcast(String src, String msg, int channelId,
			int retCode) {
		currentMsgCount++;
		System.out.println(String.format("thread[%s] receiveMsgCount=%s",
				roleIndex, currentMsgCount));
		if (maxMsgCount * maxThreadCount == currentMsgCount) {
			chatTakeMs = new Date().getTime() - chatStartTime;
			System.out.println(String.format(
					"thread no[%s] receive msgCount[%s],take time:[%sms]",
					roleIndex, currentMsgCount, Math.abs(chatTakeMs)));
			_handler.complete(this);
			close();
		}
	}

	public void call_taskInit(ASObject retObj) {
	}

	public void call_initSkill(Object[] params) {
	}

	public void call_myEnterCombat(int lv, int plat, int hard, Object[] mons,
			Object[] boss, Object[] bars, int code) {
	}

	public void call_updateGiftList(Object[] ids) {
	}

	public void addNotice(ASObject retObj) {
	}

	public void call_login(ASObject retAsObj) {
		int code = (Integer) retAsObj.get("code");
		if (code == 0) {
			Object[] rolesObj = (Object[]) retAsObj.get("roles");
			int roleCount = rolesObj.length;
			// System.out.println("role count=" + roleCount);
			if (roleCount == 0) {
				createRole();
			} else {
				enterGame(0);
			}
		}
	}

	private void enterGame(int i) {
		Amf3Request request = new Amf3Request("joinGame", i);
		send(request);
	}

	private void createRole() {
		Amf3Request request = new Amf3Request("createRole", "xlmRole"
				+ roleIndex, 1);
		send(request);
	}

	private void sendChatMsg() {
		// System.out.println("chat start!");
		chatStartTime = new Date().getTime();
		for (int i = 1; i <= maxMsgCount; i++) {
			String msg = "test chat msg[" + i + "]";
			Amf3Request request = new Amf3Request("broadcast", msg, 1);
			send(request);
		}
	}

	public int getMaxMsgCount() {
		return maxMsgCount;
	}

	public void setMaxMsgCount(int maxMsgCount) {
		this.maxMsgCount = maxMsgCount;
	}

	public long getChatTakeMs() {
		return chatTakeMs;
	}

	public void setChatTakeMs(long chatTakeMs) {
		this.chatTakeMs = chatTakeMs;
	}

	public int getMaxThreadCount() {
		return maxThreadCount;
	}

	public void setMaxThreadCount(int maxThreadCount) {
		this.maxThreadCount = maxThreadCount;
	}

}
