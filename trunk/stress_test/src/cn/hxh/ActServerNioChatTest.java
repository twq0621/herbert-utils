package cn.hxh;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import cn.hxh.test.ActChatClient;

public class ActServerNioChatTest extends AbstractJavaSamplerClient {

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
	public SampleResult runTest(JavaSamplerContext params) {

		String ip = params.getParameter("ip");
		String port = params.getParameter("port");

		SampleResult sr = new SampleResult();
		sr.setSampleLabel("kingnet.com");

		try {
			sr.sampleStart(); // 记录程序执行时间，以及执行结果
			// 发送数据
			ActChatClient client = new ActChatClient(ip, Integer.valueOf(port),
					params, sr);
			client.callLogin();
			client.talk();
			sr.setSuccessful(true);
		} catch (Exception e) {
			sr.setResponseMessage(e.getMessage());
			e.printStackTrace();
			sr.setSuccessful(false);
		} finally {
			sr.sampleEnd();
		}
		return sr;
	}

}
