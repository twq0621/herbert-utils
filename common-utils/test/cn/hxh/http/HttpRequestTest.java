package cn.hxh.http;

import junit.framework.TestCase;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestTest extends TestCase {

	private static final Logger logger = LoggerFactory.getLogger(HttpRequestTest.class);

	public void testInvokeSdo() {
		String ret = "";
		HttpClient httpClient = new DefaultHttpClient();
		try {
			String url = "http://cas.sdo.com/cas/Validate.Ex?service=http%3A%2F%2Fhjgd03.game.qidian.com%2Fgame_sdo3%2Frefresh.html&appId=629&appArea=3&ticket=ST-6b751dcc-057e-4906-91ce-cfdcb6d17659&service=http%3A%2F%2Fgd.ztgame.com%2F";
			HttpGet httpGet = new HttpGet(url);
			ResponseHandler<String> responsehandler = new BasicResponseHandler();
			ret = httpClient.execute(httpGet, responsehandler);
			logger.info("http response={}", ret);
		} catch (Exception e) {
			logger.error("error", e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

}
