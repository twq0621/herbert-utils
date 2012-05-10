package net.snake.ant;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.snake.common.Constants;
import net.snake.common.Timer;
import net.snake.service.HttpRemote;
import net.snake.service.I18nSQL;
import net.snake.util.IOTool;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestUtils;

public class RunAntServlet extends HttpServlet {
	private static final long serialVersionUID = 1882621624849504710L;

	private static Logger logger = LoggerFactory.getLogger(RunAntServlet.class);

	private String ksdateString = "";
	private static volatile boolean runingstatus = false;
	private static volatile Object runingstatusLock = new Object();
	private static volatile boolean type = true;
	//private static Timer ttTimer = new Timer();
	
	private String getWebRootPath(ServletContext sc) {
		return sc.getRealPath("/");
	}

	private void writeResponse(HttpServletResponse resp, String content) throws IOException {
		OutputStream os = resp.getOutputStream();
		resp.setContentType("text/html");
		os.write(content.getBytes("gb2312"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {

		ksdateString = Timer.getNowTime("yyyy-MM-dd HH:mm:ss");
		String taskname = ServletRequestUtils.getStringParameter(req, "task", "").trim();
		String typeid = ServletRequestUtils.getStringParameter(req, "typeid", "").trim();
		
		String time = ServletRequestUtils.getStringParameter(req, "time", "").trim();
		String ftype = ServletRequestUtils.getStringParameter(req, "ftype", "").trim();
		if ("i18n".equals(taskname)) {
			I18nSQL.execI18nSQL();
			return ;
		}
		if ("".equals(typeid)) {
			typeid = "0";
		}
		
		if ("avatar".equals(taskname)) {
			VelocityContext context = new VelocityContext();

			try {
				if (!"".equals(ftype)) {
					List<String> list = new ArrayList<String>();
					list.add(ftype);
					list.add(time);
					
					context.put("list", list);
					taskname = taskname + "2";
					velocityFileWriter2("net/snake/service/template/avatar2.vm", context, Constants.root
							+ "antdir/avatar2.xml");
				} else {
					List<String> list = new ArrayList<String>();
					list.add(typeid);
					
					context.put("list", list);
					velocityFileWriter2("net/snake/service/template/avatar.vm", context, Constants.root
							+ "antdir/avatar.xml");
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		} else if ("avatar_effect".equals(taskname)) {
			VelocityContext context = new VelocityContext();
			try {
				if (!"".equals(ftype)) {
					List<String> list = new ArrayList<String>();
					list.add(ftype);
					list.add(time);
					context.put("list", list);
					taskname = taskname + "2";
					velocityFileWriter2("net/snake/service/template/avatar_effect2.vm", context,
							Constants.root + "antdir/avatar_effect2.xml");
				} else {
					List<String> list = new ArrayList<String>();
					list.add(typeid);
					context.put("list", list);
					velocityFileWriter2("net/snake/service/template/avatar_effect.vm", context,
							Constants.root + "antdir/avatar_effect.xml");
				}
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}

		} else if ("map2".equals(taskname)) {
			VelocityContext context = new VelocityContext();
			List<String> list = new ArrayList<String>();
			list.add(typeid);
			context.put("list", list);
			try {
				velocityFileWriter2("net/snake/service/template/map2.vm", context, Constants.root
						+ "antdir/map2.xml");

			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		if (taskname.length() < 1) {
			writeResponse(resp, "参数不正确");
			return;
		}

		if ("guanbi".equals(taskname)) {
			type = false;
		}
		if ("kaishi".equals(taskname)) {
			type = true;
		}

		if (type) {

			String antfilename = getWebRootPath(getServletContext()) + "antdir" + File.separator + taskname
					+ ".xml";

			File antfile = new File(antfilename);
			if (!antfile.exists()) {
				writeResponse(resp, "任务文件不存在");
				return;
			}

			synchronized (runingstatusLock) {
				if (runingstatus) {
					writeResponse(resp, "尚有任务正在执行,请稍后重试");
					return;
				}
				runingstatus = true;
			}
			excuteAntTask(antfile);
			writeResponse(resp, "任务正在执行");

			Constants.NAMEString = taskname;

			Constants.KSTimeString = Timer.getNowTime("yyyy-MM-dd HH:mm:ss");
			Constants.TYPE = 1;
		} else {
			Constants.NAMEString = "over......................";

			Constants.KSTimeString = Timer.getNowTime("yyyy-MM-dd HH:mm:ss");
			Constants.TYPE = 1;
		}
	}

	private void excuteAntTask(final File antfile) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Project p = new Project();
				try {
					p.fireBuildStarted();
					p.init();
					ProjectHelper helper = ProjectHelper.getProjectHelper();
					helper.parse(p, antfile);
					//System.out.println("开始执行ant脚本:"+antfile.getAbsolutePath()+"的任务："+p.getDefaultTarget());
					p.executeTarget(p.getDefaultTarget());
					p.fireBuildFinished(null);
					String nameString = antfile.getName();
					if(nameString.startsWith("newsvn")){
						HttpRemote.sendHttpRequest("http://192.168.1.110:8080/resTool/sysServ?service=svn_update");
					}
				} catch (Exception e) {
					logger.error(e.getMessage(),e);
					p.fireBuildFinished(e);
				}
				
				//FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext("");
//				ApplicationContext fs=new ClassPathXmlApplicationContext("applicationContext.xml");
//				PngToSwf pngtoSwf = (PngToSwf) fs.getBean("PngToSwf");
//				pngtoSwf.start();
				synchronized (runingstatusLock) {
					runingstatus = false;

					try {
						Constants.KSTimeString = Timer.timeJiSuan(ksdateString, Timer.getNowTime("yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e) {
						logger.error(e.getMessage(),e);
					}
					Constants.TYPE = 0;
				}
			}
		}).start();

	}
	
	private void velocityFileWriter2(String template, VelocityContext context, String fileName)
			throws Exception {
		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		p.setProperty(Velocity.RESOURCE_LOADER, "class");
		p.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
		ve.init(p);
		Template templateo = ve.getTemplate(template, "utf-8");
		StringWriter sw = new StringWriter();
		templateo.merge(context, sw);
		IOTool.writeBinaryToFile(fileName, sw.toString().getBytes("gbk"));

	}
	public static void main(String[] args) {
		HttpRemote.sendHttpRequest("http://192.168.1.110:8080/resTool/sysServ?service=svn_update");
	}
}
