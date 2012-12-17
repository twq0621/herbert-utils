package cn.hxh.http;

import junit.framework.TestCase;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hxh.HttpRequestUtil;

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
	
	public void testSendJson() {
		String ret = "";
		try {
			String url = "http://192.168.1.250:8080/gameApp/cs_json_api";
			byte[] respByte = HttpRequestUtil.sendRequestV2(url,
					"{\"head\":{\"cmd\":\"stateOnline\",\"para\":\"userNums\",\"length\":\"\",\" timestamp \":\"\",\"len\":\"\"},\"body\":{\"sid\":[1,2], \"sig\":\"加密验证\" }}",
					null, "POST", "application/json");
			ret = new String(respByte, "UTF-8");
			logger.info("http response={}", ret);
		} catch (Exception e) {
			logger.error("error", e);
		}
	}
	
	public void testPlayerQuery() {
		String ret = "";
		try {
			String url = "http://192.168.1.250:8080/gameApp/cs_json_api";
			byte[] respByte = HttpRequestUtil.sendRequestV2(url,
					"{\"head\":{\"cmd\":\"playerQueries\",\"para\":\"player\",\"length\":\"\",\" timestamp \":\"\",\"len\":\"\"},\"body\":{\"area\":\"\",\"sig\":\"加密验证\" ,\"player\":\"角色名\",\"openid\":\"外部id\",\"innerid\":\"内部id\", \"playerid\":\"14631\"}}",
					null, "POST", "application/json");
			ret = new String(respByte, "UTF-8");
			logger.info("http response={}", ret);
		} catch (Exception e) {
			logger.error("error", e);
		}
	}
	
	public void testPropsQueries() {
		String ret = "";
		try {
			String url = "http://192.168.1.250:8080/gameApp/cs_json_api";
			byte[] respByte = HttpRequestUtil.sendRequestV2(url,
					"{\"head\":{\"cmd\":\"propsQueries\",\"para\":\"props\",\"length\":\"\",\" timestamp \":\"\",\"len\":\"\"},\"body\":{\"area\":\"\",\"sig\":\"加密验证\" ,\"player\":\"角色名\",\"openid\":\"外部id\",\"innerid\":\"内部id\", \"playerid\":\"14631\"}}",
					null, "POST", "application/json");
			ret = new String(respByte, "UTF-8");
			logger.info("http response={}", ret);
		} catch (Exception e) {
			logger.error("error", e);
		}
	}

	public void testTasksQueries() {
		String ret = "";
		try {
			String url = "http://192.168.1.250:8080/gameApp/cs_json_api";
			byte[] respByte = HttpRequestUtil.sendRequestV2(url,
					"{\"head\":{\"cmd\":\"tasksQueries\",\"para\":\"tasks\",\"length\":\"\",\" timestamp \":\"\",\"len\":\"\"},\"body\":{\"area\":\"\",\"sig\":\"加密验证\" ,\"player\":\"角色名\",\"openid\":\"外部id\",\"innerid\":\"内部id\", \"playerid\":\"14631\"}}",
					null, "POST", "application/json");
			ret = new String(respByte, "UTF-8");
			logger.info("http response={}", ret);
		} catch (Exception e) {
			logger.error("error", e);
		}
	}

}
