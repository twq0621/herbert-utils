package net.snake.gamemodel.bulletin.persistence;

import static net.snake.consts.GameConstant.GAME_SERVER_NAME;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.snake.GameServer;
import net.snake.commons.BeanTool;
import net.snake.commons.TimeExpression;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.consts.Symbol;
import net.snake.gamemodel.bulletin.bean.ScrollBulletin;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.ibatis.SystemFactory;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.serverenv.vline.VLineServer;

import org.apache.log4j.Logger;

/**
 * XXX 等待转为quartz管理 定时滚动公告管理
 * 
 * @author serv_dev
 */
public class ScrollBulletinManager implements Updatable, CacheUpdateListener {

	private static final Logger logger = Logger.getLogger(ScrollBulletinManager.class);

	Map<Integer, ScrollBulletin> map = new HashMap<Integer, ScrollBulletin>();
	private ScrollBulletinDAO dao = new ScrollBulletinDAO(SystemFactory.getGamedataSqlMapClient());
	private static ScrollBulletinManager instance;
	SafeTimer time = new SafeTimer(1000);
	/** 外部公告信息 */
	public Map<Integer, ScrollBulletin> extraMsgList = new HashMap<Integer, ScrollBulletin>();

	public static ScrollBulletinManager getInstance() {
		if (instance == null) {
			instance = new ScrollBulletinManager();
		}
		return instance;
	}

	private ScrollBulletinManager() {
	}

	@Override
	public void update(long now) {
		if (!time.isIntervalOK(now)) {
			return;
		}
		synchronized (this) {
			Set<Entry<Integer, ScrollBulletin>> elements = map.entrySet();
			if (elements != null)
				for (Entry<Integer, ScrollBulletin> entry : elements) {
					ScrollBulletin element = entry.getValue();
					if (!element.isEnable()) {
						continue;
					}
					String timeexp = element.getTimeexp();
					TimeExpression e = new TimeExpression(timeexp);
					if (e.isExpressionTime(now)) {
						if (!element.isSend()) {
							// 发送全服公告
							dealMessage(element);
						}
					} else {
						if (element.isSend()) {
							element.setSend(false);
						}
					}
				}
			processExtraScrollBulletin(now);
		}
	}

	/**
	 * 外部定时滚动公告
	 */
	private void processExtraScrollBulletin(long now) {
		if (extraMsgList.size() > 0) {
			for (Map.Entry<Integer,ScrollBulletin> mapEntry : extraMsgList.entrySet()) {
				ScrollBulletin scrollBulletin = mapEntry.getValue();
				if (!scrollBulletin.isEnable()) {
					continue;
				}
				String timeexp = scrollBulletin.getTimeexp();
				TimeExpression e = new TimeExpression(timeexp);
				if (e.isExpressionTime(now)) {
					if (!scrollBulletin.isSend()) {
						// 发送全服公告
						dealMessage(scrollBulletin);
					}
				} else {
					if (scrollBulletin.isSend()) {
						scrollBulletin.setSend(false);
					}
				}
			}
		}
	}

	private void dealMessage(ScrollBulletin element) {
		element.setSend(true);
		try {
			String typeString = element.getType();
			String[] split = typeString.split(Symbol.DOUHAO);
			for (String typeentry : split) {
				int type = Integer.parseInt(typeentry);
				final TipMsg msg = new TipMsg(type, element.getContentI18n());
				Collection<VLineServer> lineServersList = GameServer.vlineServerManager.getLineServersList();
				for (VLineServer vLineServer : lineServersList) {
					vLineServer.runToOnlineCharacter(new CharacterRun() {
						@Override
						public void run(Hero character) {
							character.sendMsg(msg);
						}
					});
				}
			}
		} catch (Exception e) {
			// logger.warn("公告消息配置错误");
			logger.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void reload() {
		try {
			List selectByExample = dao.select();
			BeanTool.addOrUpdate(map, selectByExample, "id");
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public String getAppname() {
		return GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "scroolbulletin";
	}

}
