package net.snake.gamemodel.account.bean;


public class AuthData {

	private String openid;
	private String hash;
	private long uid;
	private String sid;
	private String ip;
	private long time;
	private boolean indulge;

	public AuthData(String sid, String openid, String hash, long uid) {
		this.sid = sid;
		this.openid = openid;
		this.hash = hash;
		this.uid = uid;
	}

	public String getIp() {
		return ip;
	}

	public String getSid() {
		return sid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setIndulge(boolean indulge) {
		this.indulge = indulge;
	}

	public long getTime() {
		return time;
	}

	public boolean isIndulge() {
		return indulge;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

}
