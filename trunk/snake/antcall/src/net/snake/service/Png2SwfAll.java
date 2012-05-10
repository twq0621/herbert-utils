package net.snake.service;

import java.io.StringWriter;
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



public class Png2SwfAll {
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
		
		List<Resicon> listResicons = resIconProvider.getResicons();
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
		Png2SwfAll pngtoSwf = (Png2SwfAll) fs.getBean("Png2SwfAll");
		pngtoSwf.start();
	}

}
