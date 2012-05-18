package lion.core;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.LinkedList;

import lion.codec.MyRequestMsg;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.Channel;

/**
 * @author wutao
 */
final public class GamePlayer {
	private static Logger logger = Logger.getLogger(GamePlayer.class);

	public static final int Session_Type_Game = 1;
	public static final int Session_Type_Chat = 2;

	private int accountid;
	private boolean validate;
	private int sessionType;

	/** 上次心跳时间戳,上次收到客户端消息的时间戳 */
	private volatile long lastTime = System.currentTimeMillis();// 心跳检测使用
	private long initTime = System.currentTimeMillis();
	private LinkedList<MyRequestMsg> requests = null;
	private Channel channel = null;
	private String address = null;
	private volatile Object gameCharacter = null;
	private boolean islogout = false;

	private GamePlayer() {
		validate = false;
		sessionType = Session_Type_Game;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	/**
	 * 是否通过md5验证
	 * 
	 * @return
	 */
	public final boolean isValidate() {
		return validate;
	}

	/**
	 * 设置该会话已经通过验证
	 * 
	 * @param isvalidate
	 */
	public final void setValidate(boolean v) {
		this.validate = v;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public long getInitTime() {
		return initTime;
	}

	/**
	 * 为新的会话创建一个玩家对象。
	 * 
	 * @param session
	 */
	public static void newPlayer4Session(Channel channel) {
		GamePlayer player = new GamePlayer();
		player.requests = new LinkedList<MyRequestMsg>();
		player.channel = channel;
		player.setLastTime(System.currentTimeMillis());
		SocketAddress socketaddress = channel.getRemoteAddress();
		InetSocketAddress s = (InetSocketAddress) socketaddress;
		player.setAddress(s.getAddress().getHostAddress());
	}

	public void sendMsg(Object obj) {
		if (obj == null || !channel.isConnected()) {
			return;
		}
		channel.write(obj);
	}

	public void logout() {
		synchronized (this) {
			if (islogout) {
				return;
			}

			islogout = true;
			try {
				channel.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public int hashCode() {
		return this.getAccountid().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (!(object instanceof GamePlayer)) {
			return false;
		}
		if (object == this) {
			return true;
		}
		if (this.hashCode() == object.hashCode()) {
			return true;
		}
		return false;
	}

	public boolean addRequest(MyRequestMsg msg) {
		synchronized (requests) {
			requests.addLast(msg);
			return true;
		}
	}

	public MyRequestMsg executeRequest() {
		synchronized (requests) {
			return requests.poll();
		}

	}

	public <T> T getCurrentCharacter(Class<T> castType) {
		if (gameCharacter != null) {
			return castType.cast(gameCharacter);
		}
		return null;
	}

	public <T> void setCurrentCharacter(T character) {
		gameCharacter = character;
	}

	public void clearRequests() {
		synchronized (requests) {
			this.requests.clear();
		}

	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String ads) {
		this.address = ads;
	}

	public void close() {
		try {
			channel.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Channel getIoSession() {
		return channel;
	}

	public final int getSessionType() {
		return sessionType;
	}

	public final void setSessionType(int type) {
		sessionType = type;
	}

}
