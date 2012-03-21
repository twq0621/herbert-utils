package cn.hxh.test;

import java.nio.ByteBuffer;

import cn.hxh.ActConstants;

public class ChatThread implements Runnable {

	private int i;

	private ActChatClientSingle chatServer;

	private ByteBuffer sendBuffer = ByteBuffer
			.allocate(ActConstants.BUFF_CAPACITY);

	public ChatThread(int i, ActChatClientSingle chatServer) {
		this.i = i;
		this.chatServer = chatServer;
	}

	@Override
	public void run() {
		chatServer.callLogin(i);
	}

	public ByteBuffer getSendBuffer() {
		return sendBuffer;
	}

	public void setSendBuffer(ByteBuffer sendBuffer) {
		this.sendBuffer = sendBuffer;
	}

}
