package com.qq;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qq.open.OpenApiV3;
import com.qq.open.OpensnsException;

@SuppressWarnings("serial")
public class TestQQToken extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		// JSONObject json = new JSONObject();

		// json.put("ret", "0");
		// json.put("token", UUIDGenerater.generate());
		// json.put("url_params", "http://192.168.1.11:8080/recharge/qq_recharge.jsp");
		// resp.getOutputStream().print(json.toString());
		Enumeration<String> enumers = request.getParameterNames();
		while (enumers.hasMoreElements()) {
			String key = enumers.nextElement();
			System.out.println(key + "=" + request.getParameter(key));
		}
		String appkey = "5057eea7066e2a67741486a26dcc5c5c";// 平台密钥
		String serverName = "119.147.19.43";
		String scriptName = "/v3/user/is_setup";// "/v3/pay/buy_goods";
		String protocol = "http";
		HashMap<String, String> params = new HashMap<String, String>();
		// 公共参数
		String openid = request.getParameter("openid");
		String openkey = request.getParameter("openkey");
		params.put("openid", openid);
		params.put("openkey", openkey);
		params.put("appid", "100625401");
		params.put("pf", "pengyou");
		params.put("ts", String.valueOf(System.currentTimeMillis() / 1000));
		
		System.out.println("验证是否安装应用-----");
		try {
			String res = OpenApiV3.api(appkey, serverName, scriptName, params, protocol);
			System.out.println(res);
			resp.getOutputStream().write(res.getBytes("UTF-8"));
		} catch (OpensnsException e) {
			e.printStackTrace();
		}
		System.out.println("验证是否登录-----");
		try {
			String res = OpenApiV3.api(appkey,serverName,  "/v3/user/get_info", params, protocol);
			System.out.println(res);
			resp.getOutputStream().write(res.getBytes("UTF-8"));
		} catch (OpensnsException e) {
			e.printStackTrace();
		}
		System.out.println("得到用户的信息-----");
		try {
			String res = OpenApiV3.api(appkey,serverName,  "/v3/user/is_login", params, protocol);
			System.out.println(res);
			resp.getOutputStream().write(res.getBytes("UTF-8"));
		} catch (OpensnsException e) {
			e.printStackTrace();
		}
	}

}
