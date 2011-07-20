package cn.gdland;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;

import net.oauth.OAuth;
import net.oauth.OAuthAccessor;
import net.oauth.OAuthConsumer;
import net.oauth.OAuthException;
import net.oauth.OAuthMessage;
import net.oauth.OAuthServiceProvider;
import net.oauth.client.OAuthClient;
import net.oauth.client.httpclient3.HttpClient3;

public class GdTestMain {

	private Properties props;
	private File propFile;

	public GdTestMain(String fileName) throws IOException {
		props = new Properties();
		propFile = new File(fileName);
		props.load(new FileInputStream(propFile));
	}

	public static void main(String[] argv) throws Exception {
		new GdTestMain(argv[0]).execute("balance");
	}

	public OAuthMessage sendRequest(Map map, String url) throws IOException, OAuthException, URISyntaxException {
		List<Map.Entry> params = new ArrayList<Map.Entry>();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry p = (Map.Entry) it.next();
			params.add(new OAuth.Parameter((String) p.getKey(), (String) p.getValue()));
		}
		OAuthAccessor accessor = createOAuthAccessor();
		accessor.accessToken = props.getProperty("oauth_token");
		accessor.tokenSecret = props.getProperty("tokenSecret");
		OAuthClient client = new OAuthClient(new HttpClient3());
		return client.invoke(accessor, "POST", url, params);
	}

	private OAuthAccessor createOAuthAccessor() {
		String consumerKey = props.getProperty("consumerKey");
		String callbackUrl = null;
		String consumerSecret = props.getProperty("consumerSecret");

		String reqUrl = props.getProperty("requestUrl");
		String authzUrl = props.getProperty("authorizationUrl");
		String accessUrl = props.getProperty("accessUrl");

		OAuthServiceProvider provider = new OAuthServiceProvider(reqUrl, authzUrl, accessUrl);
		OAuthConsumer consumer = new OAuthConsumer(callbackUrl, consumerKey, consumerSecret, provider);
		return new OAuthAccessor(consumer);
	}

	public void execute(String operation) throws IOException, OAuthException, URISyntaxException {
		Properties paramProps = new Properties();
		//		paramProps.setProperty("oauth_token", props.getProperty("oauth_token"));
		String url = "";
		if (operation.equals("test")) {
			url = "http://api.test.aeriagames.com/services/v1/test/uid.json";
		} else if (operation.equals("balance")) {
			url = "http://api.test.aeriagames.com/services/v1/billing/balance.json";
		} else if (operation.equals("purchase")) {
			url = "http://api.test.aeriagames.com/services/v1/billing/purchase.json";
			paramProps.setProperty("txn_id", String.valueOf(129));
			paramProps.setProperty("price", String.valueOf(10));
			paramProps.setProperty("item_id", String.valueOf(1012));
			paramProps.setProperty("item_name", "my item!");
			paramProps.setProperty("user_ip", "12.1.49.120");
		}
		OAuthMessage response = sendRequest(paramProps, url);
		System.out.println(response.readBodyAsString());
	}

}
