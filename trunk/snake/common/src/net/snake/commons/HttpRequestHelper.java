package net.snake.commons;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.log4j.Logger;

/**
 * 
 * @author jack
 * 
 */
public class HttpRequestHelper {
	static Logger logger = Logger.getLogger(HttpRequestHelper.class);

	/**
	 * 通过GET请求方式，UTF-8编码进行数据请求
	 * 
	 * @param requestUrl
	 * @param params
	 * @return
	 */
	public static String getHttpReponse(String requestUrl, NameValuePair[] params) {
		return getHttpReponse(requestUrl, params, "GET", "UTF-8");
	}

	public static String getHttpReponse(String requestUrl, NameValuePair[] params, String reqMethod, String charSet) {
		return getHttpReponse(requestUrl, params, reqMethod, charSet, "");
	}

	public static String getHttpReponse(String requestUrl, NameValuePair[] params, String reqMethod, String charSet, String body) {
		return getHttpReponse(requestUrl, params, reqMethod, charSet, body, null);
	}

	/**
	 * 获取url返回结果,对参数需进行编码
	 * 
	 * @param requestUrl
	 *            请求的url主体
	 * @param params
	 *            请求的url参数
	 * @param reqMethod
	 *            请求方法
	 * @param charSet
	 *            参数和结果的编码
	 * @param body
	 *            请求body
	 * @param header
	 *            请求头信息
	 * @return
	 * 
	 */
	public static String getHttpReponse(String requestUrl, NameValuePair[] params, String reqMethod, String charSet, String body, Map<String, String> header) {
		String fullRequestUrl = requestUrl;
		try {
			fullRequestUrl = generateRequestUrl(requestUrl, params, charSet);
			return getHttpResponseWithFullUrl(fullRequestUrl, reqMethod, charSet, body, header);
		} catch (Exception e) {
			logger.error(e.getMessage() + ", error at url: \r\n" + fullRequestUrl, e);
			return null;
		}
	}

	/**
	 * 据url、参数、及header等获取url结果
	 * 
	 * @param fullRequestUrl
	 *            带参数的全路径url
	 * @param reqMethod
	 *            请求的方法
	 * @param charSet
	 *            结果的编码格式
	 * @param body
	 *            请求body体
	 * @param header
	 *            请求头信息
	 * @return url请求返回结果
	 * 
	 */
	private static String getHttpResponseWithFullUrl(String fullRequestUrl, String reqMethod, String charSet, String body, Map<String, String> header) {
		try {
			URL url = new URL(fullRequestUrl);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			int timeout = 5;
			timeout *= 1000;
			httpConnection.setRequestMethod(reqMethod);
			httpConnection.setReadTimeout(timeout);
			httpConnection.setConnectTimeout(timeout);
			httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;");
			if (header != null) {
				Iterator<String> iterator = header.keySet().iterator();
				while (iterator.hasNext()) {
					String key = iterator.next();
					String value = header.get(key);
					if (key != null && value != null && !"".equals(key) && !"".equals(value)) {
						httpConnection.addRequestProperty(key, value);
					}
				}
			}
			if (!"".equals(body)) {
				httpConnection.setDoOutput(true);
				OutputStream out = httpConnection.getOutputStream();
				out.write(body.getBytes());
				out.flush();
				out.close();
			}

			logger.warn("out request URL: " + fullRequestUrl + " \r\n post body: \r\n" + body);

			httpConnection.connect();
			if (httpConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				logger.error("out url: " + fullRequestUrl + ",  post body: " + body + ", http status" + httpConnection.getResponseCode());
				return null;
			}
			StringBuilder responseData = new StringBuilder();
			char[] buffer = new char[1024];
			InputStream ins = httpConnection.getInputStream();
			InputStreamReader insr = new InputStreamReader(ins, charSet);
			int readLength = 0;
			while ((readLength = insr.read(buffer)) > 0) {
				responseData.append(new String(buffer, 0, readLength));
			}
			ins.close();
			logger.warn("out request Response: \r\n" + responseData.toString());
			return responseData.toString();
		} catch (IOException e) {
			logger.error(e.getMessage() + ", out url: " + fullRequestUrl, e);
			return null;
		}
	}

	public static String generateRequestUrl(String requestUrl, NameValuePair[] params, String charSet) throws UnsupportedEncodingException {
		StringBuilder urlSB = new StringBuilder();
		urlSB.append(requestUrl);
		if (requestUrl.indexOf("?") < 0)
			urlSB.append("?");
		else
			urlSB.append("&");
		for (NameValuePair nvp : params) {
			urlSB.append(nvp.getName());
			urlSB.append("=");
			urlSB.append(URLEncoder.encode(nvp.getValue(), charSet));
			urlSB.append("&");
		}
		urlSB.deleteCharAt(urlSB.length() - 1);
		return urlSB.toString();
	}

	public static String getRequestUrl(String baseUrl, NameValuePair[] param) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl);
		sb.append("?");
		for (NameValuePair nvp : param) {
			sb.append(nvp.getName());
			sb.append("=");
			sb.append(nvp.getValue());
			sb.append("&");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
