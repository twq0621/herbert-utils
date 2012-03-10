package cn.hxh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class BaseSocketSampler extends AbstractJavaSamplerClient {

	// Sock begin----------------------------------------------

	private Socket sid;

	private PrintWriter out;

	private BufferedReader in;

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

		System.out.println("setupTest");

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
			String msg = "welcome to kingnet.com";
			System.out.println("begin");
			sendMsg(ip, Integer.parseInt(port), msg);
			sr.setSuccessful(true);
			closeConnections();
			System.out.println("end");

		} catch (Throwable e) {

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
		System.out.println("tearDown test!");
	}

	public void closeConnections() {
		try {
			in.close();
			out.close();
			sid.close();
			System.out.println("close connections!");
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

	private void sendMsg(String ip, int port, String msg) throws Exception {

		sid = new Socket(ip, port);

		in = new BufferedReader(new InputStreamReader(sid.getInputStream()));

		out = new PrintWriter(sid.getOutputStream(), true);

		out.println(msg);

		System.out.println("success");

	}

}
