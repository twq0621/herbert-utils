package game.chat.dto;

import lion.core.Amf3BaseDTO;

public class Chat_S2C extends Amf3BaseDTO {

	private String senderName;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

}
