package net.snake.events;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.commons.runtimeload.JRuntimeLoaderImp;
import net.snake.commons.script.IGlobalLanguage;
import net.snake.commons.script.IAppEventListeners;
import net.snake.commons.script.IEventListener;
import net.snake.gamemodel.language.persistence.LanguageScriptManager;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class EventManager implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(EventManager.class);

	private volatile JRuntimeLoaderImp jruntimeLoader;

	public EventManager() {
		jruntimeLoader = new JRuntimeLoaderImp();
	}

	public void initial(String workdir) {
		jruntimeLoader.setRuntimeDir(workdir + "runtimejar");
		jruntimeLoader.setScriptRefreshPath(workdir + "script/gamescript.jar");
		try {
			jruntimeLoader.afterPropertiesSet();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		this.jruntimeLoader = jruntimeLoader;
		initScript();
	}

	private void initScript() {
		try {
			// 初始化脚本中的静态字串
			LanguageScriptManager languageScriptManager = new LanguageScriptManager(SystemFactory.getGamedataSqlMapClient());
			IGlobalLanguage globalLanguage = (IGlobalLanguage) createScript("net.snake.resource.GlobalLanguage", null);
			globalLanguage.initData(languageScriptManager.getAllLanguageScriptMap());
			IAppEventListeners scriptdefine = (IAppEventListeners) createScript("net.snake.gameserver.event.AppEventListenDefine", null);
			String[] scripts = scriptdefine.getRuntimeListeners();
			String[] formulaStrings = scriptdefine.getRuntimeFormulas();
			if (scripts == null) {
				AppEventCtlor.getInstance().registEventListener(null);
			} else {
				IEventListener[] obj = new IEventListener[scripts.length];
				for (int i = 0; i < scripts.length; i++) {
					obj[i] = (IEventListener) createScript(scripts[i], null);
				}
				AppEventCtlor.getInstance().registEventListener(obj);
			}

			if (formulaStrings == null) {
				AppEventCtlor.getInstance().registFormula(null);
			} else {
				Object[] obj = new Object[formulaStrings.length];
				for (int i = 0; i < formulaStrings.length; i++) {
					obj[i] = createScript(formulaStrings[i], null);
				}
				AppEventCtlor.getInstance().registFormula(obj);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private Object createScript(String classname, String param) throws Exception {
		Object script = null;
		if (param == null || param.length() == 0) {
			script = jruntimeLoader.newObject(classname);
		} else {
			script = jruntimeLoader.newObject(classname, param);
		}
		return script;
	}

	@Override
	public void reload() {
		jruntimeLoader.reload();
		initScript();
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "gamescript";
	}
}
