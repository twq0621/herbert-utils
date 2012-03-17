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
 * 玩家持续创建角色，删除角色
 * 
 * @author hexuhui
 * 
 */
public class ActServerRoleTest extends AbstractJavaSamplerClient {

	// Sock begin----------------------------------------------

	private Socket socket;

	private DataOutputStream out;

	private DataInputStream in;

	private String ip;

	private String port;

	private String userName;

	private String password;

	private String roleName;

	// Sock end------------------------------------------------

	private static String label = "kingnet.com";

	// 测试结果

	private SampleResult sr;

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

		return params;

	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		ip = arg0.getParameter("ip");

		port = arg0.getParameter("port");

		userName = arg0.getParameter("userName");
		password = arg0.getParameter("password");
		roleName = arg0.getParameter("roleName");

		sr = new SampleResult();

		sr.setSampleLabel(label);

		try {
			sr.sampleStart(); // 记录程序执行时间，以及执行结果
			// 发送数据
			String retMsg = sendMsg(ip, Integer.parseInt(port));
			sr.setSuccessful(true);
			sr.setResponseMessage(retMsg);
		} catch (Throwable e) {
			sr.setResponseMessage(e.getMessage());
			e.printStackTrace();
			sr.setSuccessful(false);
		} finally {
			sr.sampleEnd();
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

	private String sendMsg(String ip, int port) throws Exception {

		socket = new Socket();

		socket.setReuseAddress(true);

		socket.setSoLinger(true, 0);

		socket.connect(new InetSocketAddress(ip, port));

		in = new DataInputStream(socket.getInputStream());

		out = new DataOutputStream(new BufferedOutputStream(
				socket.getOutputStream()));
		// 发送请求
		Amf3Request request = new Amf3Request("login", userName, password);
		encodeAndSend(request);
		Object retObj = decode();
		// 处理返回结果
		ASObject retAsObj = (ASObject) retObj;
		ASObject subAsObj = (ASObject) ((Object[]) retAsObj.get("args"))[0];
		Object[] rolesObj = (Object[]) subAsObj.get("roles");
		int roleCount = rolesObj.length;
		System.out.println("role count=" + roleCount);
		if (roleCount == 0) {
			createRole();
		} else {
			removeRole(0);
		}

		return retAsObj.get("method").toString();

	}

	private void removeRole(int roleIndex) throws IOException {
		Amf3Request request = new Amf3Request("removeRole", roleIndex);
		encodeAndSend(request);
		Object retObj = decode();
		ASObject retAsObj = (ASObject) retObj;
		Object subObj = ((Object[]) retAsObj.get("args"))[0];
		if (subObj instanceof Integer) {
			int retCode = (Integer) subObj;
			System.out.println("removeRole retCode:" + retCode);
		} else {
			ASObject subAsObj = (ASObject) subObj;
			System.out.println("removeRole " + subAsObj);
		}
	}

	private void createRole() throws IOException {
		Amf3Request request = new Amf3Request("createRole", roleName, 1);
		encodeAndSend(request);
		Object retObj = decode();
		ASObject retAsObj = (ASObject) retObj;
		Object subObj = ((Object[]) retAsObj.get("args"))[0];
		if (subObj instanceof Integer) {
			int retCode = (Integer) subObj;
			System.out.println("createRole retCode:" + retCode);
		} else {
			ASObject subAsObj = (ASObject) subObj;
			System.out.println("createRole " + subAsObj);
		}
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
