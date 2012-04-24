package net.snake.commons.httplog;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import net.snake.commons.CertificationUtil;
import net.snake.commons.URLUtil;
import org.apache.log4j.Logger;
public class HttpLogThread {
	private static Logger logger = Logger.getLogger(HttpLogThread.class);
	int threadsize = 30;
	ExecutorService es;
	volatile int runstatusthread = 0;

	public void setThreadsize(int threadsize) {
		this.threadsize = threadsize;
	}

	public void init() {
		es = Executors.newFixedThreadPool(threadsize);
	}

	public void destroy() {
		es.shutdown();
	}

	/**
	 * 外部日志输出调用
	 * httpExternalGetRequest("http://www.baidu.com","username","henry",
	 * "password", "123456"); 等同于url
	 * http://www.baidu.com?username=henry&password=123456
	 * 
	 * @param url
	 * @param params
	 * @throws Exception
	 */
	public void httpExternalGetRequest(String url, String key, String gid, String sid, String user, String roleid, String time, String account, String dept, String user_yx,
			String... params) {
		if (es != null && runstatusthread < threadsize) {
			es.execute(new ExternalGetTask(url, key, gid, sid, user, roleid, time, account, dept, user_yx, params));
		} else {
			logger.warn("out of number of threads,can not log");
		}

	}

	/**
	 * @description 内部日志发送方法，只有公司内部http日志输出调用
	 * @param url
	 *            远程日志地址
	 * @param logType
	 *            日志类型
	 * @param context
	 *            日志内容
	 * @param key
	 *            加密key
	 */
	public void httpInteriorGetRequest(String url, int logType, String context, String key) {
		if (es != null && runstatusthread < threadsize) {
			String base64Context = CertificationUtil.encodeBase64(context);
			if (base64Context == null || base64Context.length() == 0) {
				return;
			}
			es.execute(new InteriorGetTask(url, logType, base64Context, key));
		} else {
			logger.warn("out of number of threads,can not log");
		}
	}

	/**
	 * @description 外部日志输出
	 * @author dev
	 */
	class ExternalGetTask implements Runnable {
		String url;
		String key;
		String gid;
		String sid;
		String user;
		String user_yx;
		String roleid;
		String time;
		String dept;
		String account;
		Object[] params;

		public ExternalGetTask(String url, String key, String gid, String sid, String user, String roleid, String time, String account, String dept, String user_yx,
				String... params) {
			this.key = key;
			this.url = url;
			this.gid = gid;
			this.sid = sid;
			this.user = user;
			this.roleid = roleid;
			this.time = time;
			this.dept = dept;
			this.params = params;
			this.account = account;
			this.user_yx = user_yx;
		}

		@Override
		public void run() {
			synchronized (HttpLogThread.this) {
				runstatusthread++;
			}
			try {
				String md5 = CertificationUtil.md5(key);
				String head = "gid=" + gid + "&sid=" + sid + "&user=" + user + "&roleid=" + roleid + "&time=" + time + "&user_yx=" + user_yx;
				String urls = url + "?" + head;
				String createUrlParam = URLUtil.createUrlParam(params);
				if (createUrlParam != null && createUrlParam.contains("=")) {
					urls += "&" + createUrlParam;
				}
				urls += "&dept=" + dept + "&account=" + account + "&sign=" + md5;
				wget(urls);
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug(e.getMessage(), e);
				}

			}
			synchronized (HttpLogThread.this) {
				runstatusthread--;
			}
		}
	}

	/**
	 * @description 内部日志输出
	 * @author dev
	 */
	class InteriorGetTask implements Runnable {
		String url;
		int logType;
		String key;
		String context;

		public InteriorGetTask(String url, int logType, String context, String key) {
			this.url = url;
			this.logType = logType;
			this.key = key;
			this.context = context;
		}

		@Override
		public void run() {
			synchronized (HttpLogThread.this) {
				runstatusthread++;
			}
			try {

				long nowTime = System.currentTimeMillis();
				String md5 = CertificationUtil.md5(context + nowTime + key);
				String head = "log_type=" + logType + "&time=" + nowTime + "&context=" + context;
				String urls = url + "?" + head;
				urls += "&sign=" + md5;
				wget(urls);
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug(e.getMessage(), e);
				}

			}

			synchronized (HttpLogThread.this) {
				runstatusthread--;
			}
		}

	}

	private static void wget(String urladdress) throws Exception {
		int i = 0;
		if (i == 0) {
			return;
		}
		HttpURLConnection uc = null;
		try {
			URL url = new URL(urladdress);
			uc = (HttpURLConnection) url.openConnection();
			uc.setConnectTimeout(5000); // 10秒超时
			uc.setReadTimeout(5000); // 10秒超时
			uc.setRequestMethod("GET");
			uc.setInstanceFollowRedirects(false); // 不允许重定向
			if (logger.isDebugEnabled()) {
				logger.debug("发送日志" + urladdress);
			}

			int t = uc.getResponseCode();
			String responseMessage = uc.getResponseMessage();
			BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String lines = "";
			while (reader.ready()) {
				lines += reader.readLine();
			}
			reader.close();
			if (logger.isDebugEnabled()) {
				logger.debug("返回信息" + t + " " + responseMessage + " \n" + lines);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error(e.getMessage(), e);
		} finally {
			if (uc != null && uc.getInputStream() != null) {
				// uc.disconnect();//释放资源，并有可能影响到持久连接
				uc.getInputStream().close();// 只释放实例资源，不会影响持久连接
			}
		}
	}

	public int getThreadsize() {
		return threadsize;
	}

	public int getRunstatusthread() {
		return runstatusthread;
	}

}
