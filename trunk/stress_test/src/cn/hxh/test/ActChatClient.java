package cn.hxh.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import cn.hxh.ActConstants;
import cn.hxh.codec.ASObject;
import cn.hxh.codec.Amf3Reader;
import cn.hxh.codec.Amf3Writer;
import cn.hxh.io.Amf3Request;

public class ActChatClient {

	// private static Log logger = LogFactory.getLog(EchoNioClient.class);
	private SocketChannel socketChannel = null;
	private ByteBuffer sendBuffer = ByteBuffer
			.allocate(ActConstants.BUFF_CAPACITY);
	private ByteBuffer receiveBuffer = ByteBuffer
			.allocate(ActConstants.BUFF_CAPACITY);
	private Selector selector;

	private JavaSamplerContext params;

	private SampleResult sr;

	public ActChatClient(String ip, int port, JavaSamplerContext params,
			SampleResult sr) throws IOException {
		socketChannel = SocketChannel.open();
		System.out.println("connecting to " + ip + ":" + port);
		InetSocketAddress isa = new InetSocketAddress(ip, port);
		socketChannel.connect(isa);
		socketChannel.configureBlocking(false);
		System.out.println("connect to server success!");
		selector = Selector.open();
		this.params = params;
		this.sr = sr;
	}

	public static void main(String args[]) throws IOException {
		// final ActChatClient client = new ActChatClient("192.168.83.102",
		// 1863,
		// new JavaSamplerContext());
		// client.callLogin();
		// client.talk();
	}

	@SuppressWarnings("rawtypes")
	public void talk() throws IOException {
		socketChannel.register(selector, SelectionKey.OP_READ
				| SelectionKey.OP_WRITE);
		try {
			while (selector.select() > 0) {
				Set readyKeys = selector.selectedKeys();
				Iterator it = readyKeys.iterator();
				while (it.hasNext()) {
					SelectionKey key = null;
					try {
						key = (SelectionKey) it.next();
						it.remove();

						if (key.isReadable()) {
							receive(key);
						}
						if (key.isWritable()) {
							send(key);
						}
					} catch (IOException e) {
						e.printStackTrace();
						try {
							if (key != null) {
								key.cancel();
								key.channel().close();
							}
						} catch (Exception ex) {
							e.printStackTrace();
						}
					}
				}// #while
			}// #while
		} catch (Exception e) {
			e.printStackTrace();
			socketChannel.close();
			System.out.println("close connections!");
			selector.close();
		}
		socketChannel.close();
		System.out.println("close connections!");
		selector.close();
	}

	public void send(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		synchronized (sendBuffer) {
			if (sendBuffer.position() != 0) {
				sendBuffer.flip(); // 把极限设为位置
				socketChannel.write(sendBuffer);
				sendBuffer.compact();
			}
		}
	}

	public void receive(SelectionKey key) throws Exception {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		socketChannel.read(receiveBuffer);
		receiveBuffer.flip();
		Object retObj = decodeAmf3(receiveBuffer);
		ASObject retAsObj = (ASObject) retObj;
		Object callMethod = retAsObj.get("method");
		if (callMethod.equals("call_login")) {
			ASObject subAsObj = (ASObject) ((Object[]) retAsObj.get("args"))[0];
			Object[] rolesObj = (Object[]) subAsObj.get("roles");
			int roleCount = rolesObj.length;
			System.out.println("role count=" + roleCount);
			if (roleCount == 0) {
				createRole();
			} else {
				enterGame(0);
			}
		} else if (callMethod.equals("call_createRole")) {
			Object subObj = ((Object[]) retAsObj.get("args"))[0];
			if (subObj instanceof Integer) {
				int retCode = (Integer) subObj;
				System.out.println("createRole retCode:" + retCode);
			} else {
				ASObject subAsObj = (ASObject) subObj;
				System.out.println("createRole " + subAsObj);
			}
		} else if (callMethod.equals("call_joinGame")) {
			System.out.println("receive joinGame!");
			Object subObj = ((Object[]) retAsObj.get("args"))[0];
			if (subObj instanceof Integer) {
				int retCode = (Integer) subObj;
				System.out.println("joinGame retCode:" + retCode);
			} else {
				ASObject subAsObj = (ASObject) subObj;
				System.out.println("joinGame " + subAsObj);
			}
			// 发送聊天消息
			sendChatMsg();
		} else if (callMethod.equals("call_broadcast")) {
			System.out.println("receive broadcast message!");
			Object[] oList = (Object[]) retAsObj.get("args");
			String receiveMsgContent = "";
			for (Object chatMsg : oList) {
				receiveMsgContent += "; " + chatMsg;
			}
			System.out.println(receiveMsgContent);
		}
		receiveBuffer.position(receiveBuffer.limit());
		receiveBuffer.compact();
	}

	private void sendChatMsg() {
		int msgCount = Integer.valueOf(params
				.getParameter(ActConstants.CHAT_MESSAGE_COUNT));
		for (int i = 1; i <= msgCount; i++) {
			String msg = "test chat msg[" + i + "]";
			Amf3Request request = new Amf3Request("broadcast", msg, 1);
			sendBuffer.put(encodeAmf3(request));
			System.out.println("send msg:" + msg);
		}
	}

	/**
	 * 登陆界面
	 */
	public void callLogin() {
		System.out.println("call login!");
		Amf3Request request = new Amf3Request("login",
				params.getParameter(ActConstants.USER_NAME),
				params.getParameter(ActConstants.PASSWORD));
		sendBuffer.put(encodeAmf3(request));
	}

	/**
	 * 登陆游戏世界
	 * 
	 * @param i
	 */
	private void enterGame(int roleIndex) {
		System.out.println("call join game!");
		Amf3Request request = new Amf3Request("joinGame", roleIndex);
		sendBuffer.put(encodeAmf3(request));
	}

	/**
	 * 如果角色不存在，则创建角色
	 * 
	 * @return
	 */
	private void createRole() {
		Amf3Request request = new Amf3Request("createRole",
				params.getParameter(ActConstants.ROLE_NAME), 1);
		sendBuffer.put(encodeAmf3(request));
	}

	public Object decodeAmf3(ByteBuffer buffer) throws IOException {
		int dataLen = buffer.getInt();
		byte[] content = new byte[dataLen];
		buffer.get(content);
		return Amf3Reader.read(content);
	}

	public ByteBuffer encodeAmf3(Amf3Request request) {
		Map<String, Object> msg = request.toMap();
		byte[] data = Amf3Writer.write(msg);
		int length = data.length;
		ByteBuffer bb = ByteBuffer.allocate(data.length + 4);
		bb.putInt(length);
		bb.put(data);
		bb.flip();
		return bb;
	}

}
