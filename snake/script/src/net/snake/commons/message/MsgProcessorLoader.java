package net.snake.commons.message;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.commons.runtimeload.JRuntimeLoaderImp;
import net.snake.netio.filter.MsgValidDateManager;
import net.snake.netio.message.MsgDispatcher;
import net.snake.netio.message.process.IServerDispatcher;
import net.snake.netio.message.process.MsgProcessor;


/**
 * 消息处理器加载
 * 
 * @author serv_dev
 * 
 */
public class MsgProcessorLoader implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(MsgProcessorLoader.class);
	private String workdir;
	private IServerDispatcher disp;
	private static final String refreshPath = "process/process.jar";
	private static final String runtimeDIR = "runtimejar";
	private volatile JRuntimeLoaderImp jruntimeLoader;

	public MsgProcessorLoader(IServerDispatcher disp) {
		this.workdir = disp.getWorkPath();
		this.disp = disp;
		try {
			File runtimejardir = new File(workdir, runtimeDIR);
			runtimejardir.mkdir();

			JRuntimeLoaderImp jruntimeLoader = new JRuntimeLoaderImp();
			jruntimeLoader.setRuntimeDir(workdir + runtimeDIR);
			jruntimeLoader.setScriptRefreshPath(workdir + refreshPath);
			jruntimeLoader.afterPropertiesSet();
			this.jruntimeLoader = jruntimeLoader;
			// loader = this.jruntimeLoader.getClassLoader();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		// reload();
		initProcessor();
	}

	private void parseProcessor(Properties properties, IServerDispatcher disp) {
		Set<Entry<Object, Object>> es = properties.entrySet();
		Iterator<Entry<Object, Object>> it = es.iterator();
		MsgDispatcher dispatcher = disp.getMsgDispatcher();

		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			String requestcode = (String) entry.getKey();
			String requestProcessor = (String) entry.getValue();
			Integer msgCode = Integer.valueOf(requestcode);
			Class<?> processorClass;

			try {
				String[] str = requestProcessor.split(",");
				processorClass = jruntimeLoader.getClassLoader().loadClass(str[0]);
				Object processorObj = processorClass.newInstance();
				MsgProcessor msgProcessor = MsgProcessor.class.cast(processorObj);
				dispatcher.addMsgProcessor(msgCode, msgProcessor);
			} catch (Exception e) {
				logger.error("construct " + requestProcessor + " instance fail", e);
			}
		}
		logger.info("process reload finish");
		MsgValidDateManager.getInstance().initDate(properties);
		return;
	}

	@Override
	public void reload() {
		jruntimeLoader.reload();
		initProcessor();
	}

	private void initProcessor() {
		InputStream propStream = null;
		Properties properties = new Properties();

		try {
			propStream = jruntimeLoader.getClassLoader().getResourceAsStream(disp.getProccessProp());

			if (propStream == null) {
				propStream = jruntimeLoader.getClassLoader().getResourceAsStream("/" + disp.getProccessProp());
				properties.load(propStream);
			} else {
				properties.load(propStream);
			}

			// 重新加载msgProcessor的jar文件
			parseProcessor(properties, disp);
		} catch (Exception e) {
			logger.warn("init msg manager with err", e);
		} finally {
			try {
				propStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "process";
	}
}
