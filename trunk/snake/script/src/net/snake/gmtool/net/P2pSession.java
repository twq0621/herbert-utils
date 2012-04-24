package net.snake.gmtool.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.log4j.Logger;


/**
 * 服务器到服务器之间的通信。
 * 
 * @author serv_dev Copyright (c) 2011 Kingnet
 */
public class P2pSession {
	private static Logger logger = Logger.getLogger(P2pSession.class);
	private Socket socket;

	public P2pSession(String ip, int port) throws IOException {
		socket = new Socket();
		socket.setSoTimeout(3000);// 1秒的读超时
		socket.setKeepAlive(false);
		socket.setSoLinger(false, 0);
		socket.connect(new InetSocketAddress(ip, port));
	}

	public synchronized Msg sendAndReceive(Msg msg) throws IOException {
		writeMsg(msg, socket);
		return readMsg(socket);
	}

	public void close() {
		try {
			socket.getInputStream().close();
			socket.shutdownInput();
		} catch (IOException e) {
		}
		try {
			socket.getOutputStream().close();
			socket.shutdownOutput();
		} catch (IOException e) {
		}
		try {
			socket.close();
		} catch (IOException e) {
		}

	}

	private static void writeMsg(Msg msg, Socket socket) throws IOException {
		OutputStream output = socket.getOutputStream();
		ByteArrayWriter baw = new ByteArrayWriter();
		int length = 8 + msg.getLength();
		baw.writeShort(127);
		baw.writeShort(length);
		baw.writeInt(msg.getFunction());
		if (msg.getLength() > 0) {
			baw.write(msg.getContent());
		}
		output.write(baw.toByteArray());
		output.flush();
	}

	private static Msg readMsg(Socket socket) throws IOException {
		DataInputStream input = new DataInputStream(socket.getInputStream());
		short tag = input.readShort();
		if (tag != 127) {
			logger.error("error tag = " + tag);
		}
		short length = input.readShort();
		int code = input.readInt();
		byte[] bytes = new byte[length - 8];
		input.readFully(bytes);
		Msg msg = new Msg(code, bytes);
		return msg;
	}

	public static void main(String[] args) {
		try {
			P2pSession ajsocket = new P2pSession("127.0.0.1", 23);
			Msg msg = new Msg(30107);
			ajsocket.sendAndReceive(msg);
			ajsocket.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}
}
