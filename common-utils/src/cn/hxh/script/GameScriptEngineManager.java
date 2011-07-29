package cn.hxh.script;

import java.io.Reader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
@Service
public class GameScriptEngineManager implements ApplicationContextAware {

	private static final Logger logger = LoggerFactory.getLogger(GameScriptEngineManager.class);

	private ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
	private ScriptEngine scriptEngine = scriptEngineManager.getEngineByExtension("js");

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			scriptEngine.put(beanName, applicationContext.getBean(beanName));
		}
	}

	public String execute(Reader reader) {
		String output = StringUtils.EMPTY;
		try {
			scriptEngine.eval("var output = '';");
			scriptEngine.eval(reader);
			Object outputObj = scriptEngine.get("output");
			if (outputObj instanceof String) {
				output = (String) outputObj;
			} else if (outputObj instanceof Number) {
				Number outputNumber = (Number) outputObj;
				output = outputNumber.toString();
			}
		} catch (ScriptException e) {
			logger.error("", e);
		}
		return output;
	}

}
