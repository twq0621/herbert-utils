package com.qq.open;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qq.open.SnsNetwork;
import com.qq.open.SnsSigCheck;

/**
 * Java SDK for OpenAPI V3 - 提供访问腾讯开放平台 OpenApiV3 的接口
 * 
 */
public class OpenApiV3 {
	private static final String protocol = "http";

	private static Logger logger = Logger.getLogger("openPFLog");
	private static Logger loggererror = Logger.getLogger(OpenApiV3.class);

	/**
	 * 执行API调用
	 * 
	 * @param scriptName
	 *            OpenApi CGI名字
	 * @param params
	 *            OpenApi的参数列表
	 * @param protocol
	 *            HTTP请求协议 "http" / "https"
	 * @return 返回服务器响应内容
	 */
	public static String api(String appkey, String serverName, String scriptName, HashMap<String, String> params, String protocol) throws OpensnsException {
		// 检查openid openkey等参数
		if (params.get("openid") == null) {
			throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "openid is empty");
		}
		if (params.get("openkey") == null) {
			throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "openkey is empty");
		}
		if (params.get("pf") == null) {
			throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "pf is empty");
		}
		if (!isOpenid(params.get("openid"))) {
			throw new OpensnsException(ErrorCode.PARAMETER_INVALID, "openid is invalid");
		}
		// 无需传sig,会自动生成
		params.remove("sig");
		// 请求方法
		String method = "post";
		// 签名密钥
		String secret = appkey + "&";
		// 计算签名
		String sig = SnsSigCheck.makeSig(method, scriptName, params, secret);
		params.put("sig", sig);
		StringBuilder sb = new StringBuilder(64);
		sb.append(protocol).append("://").append(serverName).append(scriptName);
		String url = sb.toString();
		// cookie
		HashMap<String, String> cookies = null;
		// 发送请求
		String resp = SnsNetwork.postRequest(url, params, cookies, protocol);
		return resp;
	}

	/**
	 * 执行API调用
	 * 
	 * @param scriptName
	 *            OpenApi CGI名字
	 * @param params
	 *            OpenApi的参数列表
	 * @param protocol
	 *            HTTP请求协议 "http" / "https"
	 * @return 返回服务器响应内容
	 */
	public static String checkSig(String appkey, String scriptName, HashMap<String, String> params, String protocol, String method) throws OpensnsException {
		// 检查openid openkey等参数
		if (params.get("openid") == null) {
			throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "openid is empty");
		}
		if (params.get("openkey") == null) {
			throw new OpensnsException(ErrorCode.PARAMETER_EMPTY, "openkey is empty");
		}
		if (!isOpenid(params.get("openid"))) {
			throw new OpensnsException(ErrorCode.PARAMETER_INVALID, "openid is invalid");
		}
		// 无需传sig,会自动生成
		params.remove("sig");
		// 签名密钥
		String secret = appkey + "&";
		// 计算签名
		String sig = SnsSigCheck.makeSig(method, scriptName, params, secret);
		return sig;
	}

	/**
	 * 验证openid是否合法
	 */
	private static boolean isOpenid(String openid) {
		return (openid.length() == 32) && openid.matches("^[0-9A-Fa-f]+$");
	}

	/**
	 * 获取登录用户的信息，包括昵称，头像等信息。
	 * 
	 * @param appkey
	 * @param serverName
	 * @param params
	 *            pf,openid,openkey,appid
	 * @return
	 * @throws OpensnsException
	 */
	public static void getUserinfo(String appkey, String serverName, HashMap<String, String> params) throws OpensnsException {
		String res = OpenApiV3.api(appkey, serverName, "/v3/user/get_info", params, protocol);
		JSONObject json = strToJson(res);
		if (json == null) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_is_realname】接口返回结果有误");
		}
		String ret = json.getString("ret");
		if (!"0".equals(ret)) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_is_realname】接口返回结果有误");
		}
		logger.info(json.toString());
	}

	/**
	 * 获取登录用户的信息，包括昵称，头像等信息。
	 * 
	 * @param appkey
	 * @param serverName
	 * @param params
	 *            pf,openid,openkey,appid
	 * @return
	 * @throws OpensnsException
	 */
	public static boolean isLogin(String appkey, String serverName, HashMap<String, String> params) throws OpensnsException {
		String res = OpenApiV3.api(appkey, serverName, "/v3/user/is_login", params, protocol);
		JSONObject json = strToJson(res);
		if (json == null) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_is_realname】接口返回结果有误");
		}
		String ret = json.getString("ret");
		if ("0".equals(ret)) {
			return true;
		}
		loggererror.info(res);
		return false;
	}

	/**
	 * 标志用户是否实名注册（1表示已实名注册；0表示未实名注册）
	 * 
	 * @param appkey
	 * @param serverName
	 * @param params
	 *            pf,openid,openkey,appid,appname,platform(1表示QQ空间，2表示腾讯朋友，3表示腾讯微博，4表示Q+平台，5表示财付通开放平台，10表示QQGame。)
	 * @return true 已实名注册,false 未实名注册
	 * @throws OpensnsException
	 */
	public static boolean isRealname(String appkey, String serverName, HashMap<String, String> params) throws OpensnsException {
		String userinfo = params.get("openid") + "," + params.get("openkey");
		params.put("userinfo", userinfo);
		params.put("user_num", "1");
		String res = OpenApiV3.api(appkey, serverName, "/csec/fcm_is_realname", params, protocol);
		JSONObject json = strToJson(res);
		if (json == null) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_is_realname】接口返回结果有误");
		}
		String ret = json.getString("ret");
		if (!"0".equals(ret)) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_is_realname】接口返回结果有误");
		}
		if ("1".equals(json.getString("flag"))) {
			return true;
		}
		return false;
	}

	/**
	 * -audit: 是否成年。0表示未成年；1表示成年；2表示未注册
	 * 
	 * @param appkey
	 * @param serverName
	 * @param params
	 *            pf,openid,openkey,appid
	 * @return true 受防沉迷限制，false不受防沉迷限制
	 * @throws OpensnsException
	 */
	public static boolean isAudit(String appkey, String serverName, HashMap<String, String> params) throws OpensnsException {
		String res = OpenApiV3.api(appkey, serverName, "/csec/fcm_gametime_get", params, protocol);
		JSONObject json = strToJson(res);
		if (json == null) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_gametime_get】接口返回结果有误");
		}
		String ret = json.getString("ret");
		if (!"0".equals(ret)) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_gametime_get】接口返回结果有误");
		}
		JSONArray jsonArr = json.getJSONArray("info");
		if (jsonArr == null || jsonArr.size() < 0) {
			throw new OpensnsException(ErrorCode.RESPONSE_DATA_INVALID, "【fcm_gametime_get】接口返回结果有误,info信息没有");
		}
		JSONObject info = jsonArr.getJSONObject(0);
		if ("1".equals(info.getString("audit"))) {
			return false;
		}
		return true;
	}

	private static JSONObject strToJson(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		JSONObject json = JSONObject.fromObject(str);
		return json;
	}

}
