package cn.hxh.test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EchoNioClient {

	private static Log log = LogFactory.getLog(EchoNioClient.class);

	private SocketChannel socketChannel = null;
	private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);
	private ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);
	private Charset charset = Charset.forName("SJIS");
	private Selector selector;

	public EchoNioClient() throws IOException {
		socketChannel = SocketChannel.open();
		InetAddress ia = InetAddress.getLocalHost();
		InetSocketAddress isa = new InetSocketAddress(ia, 31000);
		socketChannel.connect(isa);
		socketChannel.configureBlocking(false);
		log.info("与服务器的连接建立成功");
		selector = Selector.open();
	}

	public static void main(String args[]) throws IOException {
		final EchoNioClient client = new EchoNioClient();
		Thread receiver = new Thread() {
			public void run() {
				client.keepConnection();
			}
		};
		client.sendBuffer.put(client.encode("C:Socket 18750 8 "));
		client.sendBuffer.put(client.encode("C:Start 0 "));

		receiver.start();
		client.talk();
	}

	public void keepConnection() {
		String msg = "C:HB";

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (sendBuffer) {
				sendBuffer.put(encode(msg + " "));
			}
			log.info("time: " + System.currentTimeMillis() + " <== C:HB");
			if (msg.equals("bye"))
				break;
		}
	}

	@SuppressWarnings("rawtypes")
	public void talk() throws IOException {
		socketChannel.register(selector, SelectionKey.OP_READ
				| SelectionKey.OP_WRITE);
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
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}// #while
	}

	public void send(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		synchronized (sendBuffer) {
			sendBuffer.flip(); // 把极限设为位置
			socketChannel.write(sendBuffer);
			sendBuffer.compact();
		}
	}

	public void receive(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		socketChannel.read(receiveBuffer);
		receiveBuffer.flip();
		String receiveData = decode(receiveBuffer);

		if (receiveData.indexOf(" ") == -1)
			return;

		String outputData = receiveData.substring(0,
				receiveData.indexOf(" ") + 1);
		log.info("time:" + System.currentTimeMillis() + " ==> " + outputData);
		if (outputData.equals("C:End ")) {
			key.cancel();
			socketChannel.close();
			log.info("关闭与服务器的连接");
			selector.close();
			System.exit(0);
		}

		ByteBuffer temp = encode(outputData);
		receiveBuffer.position(temp.limit());
		receiveBuffer.compact();
	}

	public String decode(ByteBuffer buffer) { // 解码
		CharBuffer charBuffer = charset.decode(buffer);
		return charBuffer.toString();
	}

	public ByteBuffer encode(String str) { // 编码
		return charset.encode(str);
	}
}
