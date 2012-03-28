package cn.hxh;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.hxh.amf3.Amf3Client;
import cn.hxh.dto.GetOnlineNames_C2S;
import cn.hxh.sample.SampleClient;

public class MainClient {

	public static void main(String[] args) {
		ApplicationContext factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Amf3Client amf3Client = new Amf3Client();
		SampleClient sampleClient = new SampleClient(amf3Client);
		sampleClient.connect("192.168.83.94", 8653);
		// GetNewRole_C2S msg = new GetNewRole_C2S();
		// msg.setQueryDay("2012-03-14");
		// sampleClient.send(msg);
		GetOnlineNames_C2S msg = new GetOnlineNames_C2S();
		sampleClient.send(msg);
	}

}
