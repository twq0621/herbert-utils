package net.snake.gamemodel.wedding.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import net.snake.ai.astar.Point;
//import net.snake.api.context.ISubline;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.consts.ClientConfig;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.monster.bean.MonsterModel;
import net.snake.gamemodel.monster.bean.SceneMonster;
import net.snake.gamemodel.monster.logic.SceneFeastMonster;
import net.snake.gamemodel.monster.persistence.MonsterModelManager;
import net.snake.gamemodel.wedding.bean.FeastPlayConfig;
import net.snake.gamemodel.wedding.persistence.WedFeast;
import net.snake.gamemodel.wedding.persistence.WedFeastManager;
import net.snake.serverenv.vline.VLineServer;


/**
 * 分线婚宴管理器
 * 
 * @author serv_dev
 */
public class WedFeastManagerVline implements Updatable {
	private static Logger logger = Logger.getLogger(WedFeastManagerVline.class);

	private VLineServer lineServer;
	// private ISubline subline;
	private List<SceneFeastMonster> feastMonster = new ArrayList<SceneFeastMonster>();
	private List<SceneMonster> livelyMonster = new ArrayList<SceneMonster>();
	private WedFeastManager manager = WedFeastManager.getInstance();
	private int refreshcount = 0;// 波数
	private long lastRefresh = 0l;// 最后刷新时间
	private final static int refreshTime = 5 * 60 * 1000;// 每五分钟刷新一次
	private final SafeTimer timetick = new SafeTimer(1000);
	private boolean isstart = false;
	private FeastMonsterPoint pointManager = new FeastMonsterPoint(this);

	public WedFeastManagerVline(VLineServer line) {
		lineServer = line;
	}

	@Override
	public void update(long now) {
		if (timetick.isIntervalOK(now)) {
			manager.tick(now);
			if (manager.isStart()) {
				if (!isstart) {
					isstart = true;
					refreshcount = 0;
				}
				List<WedFeast> beingFeast = manager.getBeingFeast(lineServer.getLineid());
				if (beingFeast != null && beingFeast.size() > 0 && (now - lastRefresh) > refreshTime && refreshcount <= 12) {
					clearFeastMonster();
					if (logger.isDebugEnabled()) {
						logger.debug("重置共享数据");
					}

					refreshMonster();
					if (logger.isDebugEnabled()) {
						logger.debug("第" + refreshcount + "波刷怪");
					}

				}
			} else {
				if (isstart) {
					isstart = false;
					lastRefresh = 0l;
					refreshcount = 0;
					clearFeastMonster();
				}

			}
		}
	}

	public List<SceneFeastMonster> getFeastMonster() {
		return feastMonster;
	}

	public List<SceneMonster> getLivelyMonster() {
		return livelyMonster;
	}

	public SceneFeastMonster getFeastMonsterById(int id) {
		for (SceneFeastMonster sceneFeastMonster : feastMonster) {
			if (sceneFeastMonster.getId().equals(id)) {
				return sceneFeastMonster;
			}
		}
		return null;
	}

	/**
	 * 往该分线的场景中添加婚宴怪
	 * 
	 * @param monster
	 */
	private void addFeastMonsterToScene(WedFeast feast) {
		Scene scene = lineServer.getSceneManager().getScene(ClientConfig.Scene_Xianjing);
		FeastPlayConfig config = feast.getConfig();
		for (int i = 0; i < config.getFeastAmount(); i++) {
			SceneFeastMonster monster = new SceneFeastMonster(feast, pointManager.getAndLockPoint(), refreshcount);
			feastMonster.add(monster);
			scene.enterScene(monster);
		}
	}

	/**
	 * 往该分线的场景中添加助兴怪
	 * 
	 * @param monster
	 */
	private void addLivelMonsterToScene(int model, int count) {
		Scene scene = lineServer.getSceneManager().getScene(ClientConfig.Scene_Xianjing);
		MonsterModel cache = MonsterModelManager.getInstance().getFromCache(model);
		if (cache == null) {
			logger.warn("jixing monster config with err");
			return;
		}
		for (int i = 0; i < count; i++) {
			Point point = scene.getRandomPoint();
			if (point == null) {
				if (logger.isDebugEnabled()) {
					logger.debug("婚宴助兴怪没地方放了");
				}

			}
			SceneMonster monster = new SceneMonster();
			monster.setScene(scene.getId());
			monster.setId(SceneMonster.getNewID());
			monster.setX((short) point.getX());
			monster.setY((short) point.getY());
			monster.setOriginalX((short) point.getX());
			monster.setOriginalY((short) point.getY());
			monster.setRelive(0);
			monster.setReplaceName(cache.getNameI18n());
			monster.setMonsterModel(cache);
			monster.init();
			scene.enterScene(monster);
			this.livelyMonster.add(monster);
		}
	}

	/**
	 * 清除该场景中的婚宴怪和助兴怪
	 */
	private void clearFeastMonster() {
		try {
			// Scene scene =
			// lineServer.getSceneManager().getScene(ClientConfig.xiaoyangcheng);
			List<SceneFeastMonster> monsters = getFeastMonster();
			// 清除婚宴怪
			if (monsters != null && monsters.size() > 0) {
				for (SceneFeastMonster sceneFeastMonster : monsters) {
					if (sceneFeastMonster.getObjectState() != VisibleObjectState.Dispose) {
						try {
							sceneFeastMonster.setObjectState(VisibleObjectState.Dispose);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
					// scene.leaveScene(sceneFeastMonster);
				}
				monsters.clear();
			}
			// 清除助兴怪
			List<SceneMonster> livelyMonsters = getLivelyMonster();
			if (livelyMonsters != null && livelyMonsters.size() > 0) {
				for (SceneMonster sceneMonster : livelyMonsters) {
					if (sceneMonster.getObjectState() != VisibleObjectState.Dispose) {
						try {
							sceneMonster.setObjectState(VisibleObjectState.Dispose);
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
					// scene.leaveScene(sceneMonster);
				}
				livelyMonsters.clear();
			}
			pointManager.resize();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void refreshMonster() {
		try {
			lastRefresh = System.currentTimeMillis();
			refreshcount++;
			List<WedFeast> beingFeast = manager.getBeingFeast(lineServer.getLineid());
			for (WedFeast wedFeast : beingFeast) {
				FeastPlayConfig config = wedFeast.getConfig();
				addFeastMonsterToScene(wedFeast);
				addLivelMonsterToScene(config.getLively1Model(), config.getLively1Amount());
				addLivelMonsterToScene(config.getLively2Model(), config.getLively2Amount());
				// 刷助兴怪
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
