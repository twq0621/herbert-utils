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

public class ActNettyServerLoginTest extends AbstractJavaSamplerClient {

	// Sock begin----------------------------------------------

	private Socket socket;

	private DataOutputStream out;

	private DataInputStream in;

	private String ip;

	private String port;

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

		return params;

	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		ip = arg0.getParameter("ip");

		port = arg0.getParameter("port");

		sr = new SampleResult();

		sr.setSampleLabel(label);

		try {
			sr.sampleStart(); // 记录程序执行时间，以及执行结果
			// 发送数据
			String retMsg = sendMsg(ip, Integer.parseInt(port));
			sr.setSuccessful(true);
			closeConnections();
			sr.setResponseMessage(retMsg);
		} catch (Throwable e) {
			sr.setResponseMessage(e.getMessage());
			e.printStackTrace();
			sr.setSuccessful(false);
		} finally {
			sr.sampleEnd();
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
		Amf3Request request = new Amf3Request("login", "yangwq", "yangwq");
		encodeAndSend(request);
		Object retObj = decode();
		// 处理返回结果
		ASObject retAsObj = (ASObject) retObj;
		// System.out.println("method:" + retAsObj.get("method"));
		// Object[] oList = (Object[]) retAsObj.get("args");
		// System.out.println("args:" + (ASObject) oList[0]);

		return retAsObj.get("method").toString();

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
