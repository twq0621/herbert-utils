package net.snake.service;

import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import net.snake.dao.resicon.ResIconProvider;
import net.snake.dao.resicon.Resicon;
import net.snake.util.IOTool;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.support.FileSystemXmlApplicationContext;



public class PngToSwf {
	private static final Logger logger = Logger.getLogger(PngToSwf.class);
	private ResIconProvider resIconProvider;
	private String pngToSwfJsflTempatePath;
	private String pngToSWfJsflPath;

	public void setResIconProvider(ResIconProvider resIconProvider) {
		this.resIconProvider = resIconProvider;
	}

	public void setPngToSWfJsflPath(String pngToSWfJsflPath) {
		this.pngToSWfJsflPath = pngToSWfJsflPath;
	}

	public void setPngToSwfJsflTempatePath(String pngToSwfJsflTempatePath) {
		this.pngToSwfJsflTempatePath = pngToSwfJsflTempatePath;
	}

	public void start() {
		Calendar today=Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int mon = today.get(Calendar.MONTH)+1;
		int day = today.get(Calendar.DAY_OF_MONTH);
		
		List<Resicon> listResicons = resIconProvider.getResicons();
		for (Iterator<Resicon> iterator = listResicons.iterator(); iterator.hasNext();) {
			Resicon resicon = iterator.next();
			Date date=resicon.getDate();
//			long timestamp=date.getTime();
			Calendar calendar= Calendar.getInstance();
			calendar.setTime(date);
			
			int _year = calendar.get(Calendar.YEAR);
			int _mon = calendar.get(Calendar.MONTH)+1;
			int _day = calendar.get(Calendar.DAY_OF_MONTH);
			if (year !=_year) {
				iterator.remove();
				continue;
			}
			if (mon != _mon) {
				iterator.remove();
				continue;
			}
			if (day != _day) {
				iterator.remove();
				continue;
			}
		}
		VelocityContext context = new VelocityContext();
		context.put("list", listResicons);
		try {
			velocityFileWriter(pngToSwfJsflTempatePath, context);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	private void velocityFileWriter(String template, VelocityContext context)
			throws Exception {
		VelocityEngine ve = new VelocityEngine();
		Properties p = new Properties();
		ve.init(p);
		// template="com/heishi/httpservice/service/template/monster-model.vm"
		Template templateo = ve.getTemplate(template, "utf-8");
		StringWriter sw = new StringWriter();
		templateo.merge(context, sw);
		IOTool.writeBinaryToFile(pngToSWfJsflPath,
				sw.toString().getBytes("gbk"));

	}

	public static void main(String[] args) {
		FileSystemXmlApplicationContext fs = new FileSystemXmlApplicationContext(
				args[0]);
		PngToSwf pngtoSwf = (PngToSwf) fs.getBean("PngToSwf");
		pngtoSwf.start();
	}

}
