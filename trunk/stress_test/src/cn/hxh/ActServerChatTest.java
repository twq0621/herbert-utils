package cn.hxh;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import cn.hxh.codec.ASObject;
import cn.hxh.codec.Amf3Reader;
import cn.hxh.codec.Amf3Writer;
import cn.hxh.io.Amf3Request;

/**
 * 玩家不断登陆游戏世界
 * 
 * @author hexuhui
 * 
 */
public class ActServerChatTest extends AbstractJavaSamplerClient {

	// Sock begin----------------------------------------------

	private Socket socket;

	private DataOutputStream out;

	private DataInputStream in;

	private String ip;

	private String port;

	private String userName;

	private String password;

	private String roleName;

	private int chatMsgCount;

	// Sock end------------------------------------------------

	private static String label = "kingnet.com";

	// 测试结果

	private SampleResult sr;

	private int receiveChatMsgCount = 0;

	private String receiveMsgContent = "";

	/**
	 * 
	 * 初始化
	 */

	public void setupTest(JavaSamplerContext arg0) {
	}

	/**
	 * 
	 * 设置请求的参数
	 */

	public Arguments getDefaultParameters() {

		Arguments params = new Arguments();

		params.addArgument("ip", "192.168.83.102");

		params.addArgument("port", "1863");

		params.addArgument("userName", "ddt");

		params.addArgument("password", "ddt");

		params.addArgument("roleName", "ddtRole");

		params.addArgument("chatMsgCount", "10");

		return params;

	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		ip = arg0.getParameter("ip");

		port = arg0.getParameter("port");

		userName = arg0.getParameter("userName");
		password = arg0.getParameter("password");
		roleName = arg0.getParameter("roleName");
		chatMsgCount = Integer.valueOf(arg0.getParameter("chatMsgCount"));

		sr = new SampleResult();

		sr.setSampleLabel(label);
		System.out.println("run test!");
		try {
			sr.sampleStart(); // 记录程序执行时间，以及执行结果
			// 发送数据
			boolean retMsg = sendMsg(ip, Integer.parseInt(port));
			sr.setSuccessful(retMsg);
			// 等待服务器在客户端端关闭socket连接之后踢出玩家
		} catch (Exception e) {
			sr.setResponseMessage(e.getMessage());
			e.printStackTrace();
			sr.setSuccessful(false);
		} finally {
			sr.sampleEnd();
			sr.setResponseMessage(sr.getResponseMessage()
					+ "     \n <br /> msg content:" + receiveMsgContent
					+ " \n chat msg count:" + receiveChatMsgCount);
			closeConnections();
		}
		return sr;
	}

	/**
	 * 
	 * 结束
	 */

	public void teardownTest(JavaSamplerContext arg0) {
		// System.out.println("tearDown test!");
	}

	public void closeConnections() {
		try {
			in.close();
			out.close();
			socket.close();
			socket = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 发送消息
	 * 
	 * @param ip
	 * 
	 * @param port
	 * 
	 * @param msg
	 * 
	 * @throws Exception
	 */

	private boolean sendMsg(String ip, int port) throws Exception {
		boolean ret = true;
		System.out.println("create socket!");

		socket = new Socket();

		socket.setReuseAddress(true);

		// 关闭socket之后马上中断连接
		socket.setSoLinger(true, 0);

		// 设置超时
		socket.setSoTimeout(500);

		socket.connect(new InetSocketAddress(ip, port));

		in = new DataInputStream(socket.getInputStream());

		out = new DataOutputStream(new BufferedOutputStream(
				socket.getOutputStream()));
		// 发送请求
		System.out.println("call login!");
		Amf3Request request = new Amf3Request("login", userName, password);
		encodeAndSend(request);
		System.out.println("get response!");
		Object retObj = decode();
		System.out.println("login return!");
		// 处理返回结果
		ASObject retAsObj = (ASObject) retObj;
		ASObject subAsObj = (ASObject) ((Object[]) retAsObj.get("args"))[0];
		Object[] rolesObj = (Object[]) subAsObj.get("roles");
		int roleCount = rolesObj.length;
		System.out.println("role count=" + roleCount);
		int retCode = 0;
		if (roleCount == 0) {
			retCode = createRole();
		}
		if (retCode == 0) {
			enterGame(0);
			Thread.sleep(3000);// 3s后启动聊天
			sendChatMsg();
		} else {
			ret = false;
			sr.setResponseCode(String.valueOf(retCode));
		}
		return ret;
	}

	private void sendChatMsg() throws IOException {
		for (int i = 1; i <= chatMsgCount; i++) {
			Amf3Request request = new Amf3Request("broadcast", "test chat msg["
					+ i + "]", 1);
			encodeAndSend(request);
		}
		// 会接收到5个返回
		while (true) {
			Object retObj = decode();
			ASObject retAsObj = (ASObject) retObj;
			Object[] oList = (Object[]) retAsObj.get("args");
			for (Object chatMsg : oList) {
				receiveMsgContent += "; " + chatMsg;
			}
			receiveMsgContent += "\n";
			receiveChatMsgCount++;
		}
	}

	private void enterGame(int roleIndex) throws IOException {
		Amf3Request request = new Amf3Request("joinGame", roleIndex);
		encodeAndSend(request);
		// 会接收到6个返回
		decode();
		decode();
		decode();
		decode();
		decode();
		decode();
	}

	private int createRole() throws IOException {
		int ret = 0;
		Amf3Request request = new Amf3Request("createRole", roleName, 1);
		encodeAndSend(request);
		Object retObj = decode();
		ASObject retAsObj = (ASObject) retObj;
		Object subObj = ((Object[]) retAsObj.get("args"))[0];
		if (subObj instanceof Integer) {
			int retCode = (Integer) subObj;
			System.out.println("createRole retCode:" + retCode);
			ret = retCode;
		} else {
			ASObject subAsObj = (ASObject) subObj;
			System.out.println("createRole " + subAsObj);
		}
		return ret;
	}

	private Object decode() throws IOException {
		int dataLen = in.readInt();
		byte[] data = new byte[dataLen];
		in.read(data);
		return Amf3Reader.read(data);
	}

	private void encodeAndSend(Amf3Request request) throws IOException {
		Map<String, Object> msg = request.toMap();
		byte[] data = Amf3Writer.write(msg);
		int length = data.length;
		out.writeInt(length);
		out.write(data);
		out.flush();
	}
}
