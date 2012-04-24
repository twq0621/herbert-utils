package net.snake.api.log;

import java.io.UnsupportedEncodingException;

public class MaintainStatBody {

	private String uid;

	private short tableType;

	private String msgContent;

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getSize() throws UnsupportedEncodingException {
		int bodySize = msgContent.getBytes("UTF-8").length + 9;
		return bodySize;
	}

	public short getTableType() {
		return tableType;
	}

	public void setTableType(short tableType) {
		this.tableType = tableType;
	}

}
