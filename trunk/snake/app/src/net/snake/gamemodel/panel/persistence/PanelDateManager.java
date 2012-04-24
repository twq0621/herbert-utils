package net.snake.gamemodel.panel.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.GameServer;
import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.consts.GameConstant;
import net.snake.gamemodel.panel.bean.PanelDate;
import net.snake.gamemodel.panel.response.PanelListMsg52058;
import net.snake.ibatis.SystemFactory;

/**
 * 面板管理类。
 * 
 * @author serv_dev
 */
public class PanelDateManager implements CacheUpdateListener {
	private static final Logger logger = Logger.getLogger(PanelDateManager.class);
	private static PanelDateDAO dao = new PanelDateDAO(SystemFactory.getGamedataSqlMapClient());

	private static PanelDateManager instance;

	private List<PanelDate> panelDateList = new ArrayList<PanelDate>();
	public boolean isOpen = true;

	public static PanelDateManager getInstance() {
		if (instance == null) {
			instance = new PanelDateManager();
		}
		return instance;
	}

	private PanelDateManager() {
		panelDateList.clear();
	}
	@SuppressWarnings("unchecked")
	public void init() {
		Date d = new Date();
		try {
			List<PanelDate> list = dao.select();
			for (PanelDate p : list) {
				if (p.getfStopTime().after(d)) {
					panelDateList.add(p);
					if (d.before(p.getfStartTime())) {
						p.setfIsOpen(false);
					}
				}
			}

		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void reload() {
		panelDateList.clear();
		init();
		for (PanelDate panel : panelDateList) {
			GameServer.vlineServerManager.sendMsgToAllLineServer(new PanelListMsg52058(panel));
		}
	}

	@Override
	public String getAppname() {
		return GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "PanelDate";
	}

	public List<PanelDate> getDataByTime() {
		Date d = new Date();
		List<PanelDate> list = new ArrayList<PanelDate>();
		for (PanelDate p : panelDateList)
			if (d.after(p.getfStartTime()) && d.before(p.getfStopTime()))
				list.add(p);
		return list;
	}

	private int count = 0;

	public void update() {
		if (!isOpen) {
			return;
		}
		count++;
		if (count % 1000 != 0) {
			return;
		}
		if (panelDateList.size() == 0) {
			return;
		}
		for (int i = 0; i < panelDateList.size(); i++) {
			final PanelDate panel = panelDateList.get(i);
			if (panel.getfIsOpen() && panel.getfStopTime().before(new Date())) {
				panel.setfIsOpen(false);
				panelDateList.remove(i);
				i--;
				GameServer.executorService.execute(new Runnable() {
					@Override
					public void run() {
						GameServer.vlineServerManager.sendMsgToAllLineServer(new PanelListMsg52058(panel));
					}
				});
			} else {
				Date d = new Date();
				if (!panel.getfIsOpen() && d.after(panel.getfStartTime())) {
					panel.setfIsOpen(true);
					GameServer.executorService.execute(new Runnable() {
						@Override
						public void run() {
							GameServer.vlineServerManager.sendMsgToAllLineServer(new PanelListMsg52058(panel));
						}
					});
				}
			}

		}
	}
}
