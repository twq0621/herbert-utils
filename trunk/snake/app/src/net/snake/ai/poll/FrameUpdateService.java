package net.snake.ai.poll;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.snake.GameServer;
//import net.snake.api.context.ISubline;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.script.IEventListener;
import net.snake.consts.ClientConfig;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.bulletin.persistence.ScrollBulletinManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.panel.persistence.PanelDateManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 刷帧处理 包括 场景刷帧、副本刷帧、 人物下线、上线、切换场景、 角色已在服务器上继续使用、 进入副本 等任务刷帧
 * 
 * @author serv_dev
 */
public class FrameUpdateService implements Runnable {

	private static final Logger logger = Logger.getLogger(FrameUpdateService.class);

	private Thread frameupdateThread = null;

	private final FRAME_RATE_STAT frameratestat = new FRAME_RATE_STAT();
	private final PROCESS_COUNT_INFO processcountinfo = new PROCESS_COUNT_INFO();

	/**
	 * 调用线程是否是刷帧线程
	 * 
	 * @return
	 */
	public boolean isFrameUpdateThread() {
		return Thread.currentThread() == frameupdateThread;
	}

	private volatile boolean polling = false;
	private volatile ConcurrentLinkedQueue<Runnable> taskList = new ConcurrentLinkedQueue<Runnable>();
	private volatile VLineServer vlineserver;

	// private volatile ISubline subline;

	public void setVlineserver(VLineServer vlineserver) {
		this.vlineserver = vlineserver;
	}

	// public void setSubline(ISubline line){
	// subline=line;
	// }

	/**
	 * 获得这个刷帧循环属于那个分线
	 * 
	 * @return
	 */
	public VLineServer getVlineserver() {
		return vlineserver;
	}

	// public ISubline getSubline(){
	// return subline;
	// }

	public FrameUpdateService() {
	}

	/**
	 * 为了避免同步的产生的问题，有些事情必须放到桢循环 比如玩家下线，如果允许其他线程将Character的场景置空，则游戏循环可能有异常抛出 包括下线、上线、切换场景、角色已在服务器上继续使用、进入副本
	 * 
	 * @param run
	 */
	public void addTask(Runnable run) {
		if (run == null) {
			return;
		}
		taskList.offer(run);
	}

	/**
	 * 获得帧率
	 * 
	 * @return
	 */
	public int getFrameRate() {
		return frameratestat.rate;
	}

	/**
	 * 获得消息处理性能
	 * 
	 * @return
	 */
	public Map<Integer, long[]> getProcessInfo() {
		return processcountinfo.countmaps;
	}

	@Override
	public void run() {
		Scene[] scenes = vlineserver.getSceneManager().getWorldSceneList().toArray(new Scene[0]);
		int interval = 41;// 标准刷桢时间间隔
		long sleeptime = 0;// 每次应该休息的时间
		SafeTimer commontimer = new SafeTimer(100);// 普通刷桢 100毫秒一次
		while (polling) {
			long starttime = System.currentTimeMillis();
			frameratestat.addCount(starttime);

			// =================================================================
			try {
				processPlayerMsg(starttime);
			} catch (Exception e) {
				logger.error("handle hero's msg with err", e);
			}
			// =================================================================
			if (commontimer.isIntervalOK(starttime)) {
				// 世界场景刷新
				for (Scene scene : scenes) {
					try {
						scene.update(starttime);
					} catch (Exception e) {
						logger.error("poll scene with err", e);
					}
				}

				try {
					AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_WorldUpdate, new Object[] { scenes, vlineserver.getLineid() });
				} catch (Exception e) {
					logger.error("invok script with err", e);
				}

				try {
					// 跨服禁用
					// if (!Options.IsCrossServ) {
					vlineserver.getRuningInstanceManager().update(starttime);
					// }
				} catch (Exception e) {
					logger.error("flush instance with err", e);
				}

				try {
					// 跨服禁用
					if (!Options.IsCrossServ) {
						vlineserver.getDBActivityManager().update(starttime);
					}
				} catch (Exception ex) {
					logger.error("schedule activity with err", ex);
				}
				try {
					// 跨服禁用
					if (!Options.IsCrossServ) {
						vlineserver.getWedFeastManagerVline().update(starttime);
					}
				} catch (Exception e) {
					logger.error("schedule wedding with err", e);
				}
				try {
					if (!Options.IsCrossServ) {
						ScrollBulletinManager.getInstance().update(starttime);
					}
				} catch (Exception e) {
					logger.error("scroll msg with err", e);
				}
				try {
					if (!Options.IsCrossServ) {
						// 面板定时更新检查
						PanelDateManager.getInstance().update();
					}
				} catch (Exception e) {
					logger.error("update panel with err", e);
				}

				// 必须要刷楨循环里执行的任务
				for (Runnable run = taskList.poll(); run != null; run = taskList.poll()) {
					try {
						// long st = System.currentTimeMillis();
						// logger.info("process run task:" + run.toString());
						run.run();
						// logger.info("the task run time is " + (System.currentTimeMillis() - st));
					} catch (Exception ex) {
						logger.error("loop frame-task with err", ex);
					}
				}
			}

			// 补帧计算========================================================
			long usetime = System.currentTimeMillis() - starttime;
			// 1如果花费在运行的时间多了，则sleeptime就有可能小于0 这样会立即运行，而不需歇息
			// 2如果花费在运行的时间少，则sleeptime可能大于0说明运行的快了，需要歇息一段时间
			sleeptime = sleeptime + (interval - usetime);
			if (sleeptime > 10) {
				try {
					Thread.sleep(sleeptime); // 客户端30桢
				} catch (InterruptedException e) {
					break;
				}
				sleeptime = 0;
			} else {
				try {
					Thread.sleep(10); // 客户端30桢 至少休息10毫秒
				} catch (InterruptedException e) {
					break;
				}
				// 如果sleeptime<0,则继续累积负值
				// LOGGER.warn("检查一下刷帧性能");
			}
			// GameServer.tmpDateTime.isOutTime(starttime);
		}

	}

	private void processPlayerMsg(long now) {
		for (Hero character : vlineserver.getOnlineManager().getCharacterList()) {
			try {
				if (ClientConfig.HEART_BEAT_CHECK) {
					// 超过60秒的玩家直接剔除,目前客户端每30秒发一次心跳
					if ((now - character.lastHeatBeat) > ClientConfig.HeatBeatInterval1) {
						if (character.isEnableHeatBeat()) {
							character.getDownLineManager().downLine(null);
							continue;
						}
					}
				}

				GamePlayer gameplayer = character.getPlayer();
				dealSyncMsg(gameplayer);
			} catch (Exception e) {
				logger.error("sync handle hero's msg with err", e);
			}
		}
	}

	private void dealSyncMsg(GamePlayer session) {
		if (null == session) {
			return;
		}

		RequestMsg request = session.executeRequest();
		if (null == request) {
			return;
		}

		int msgCode = request.getMsgCode();
		MsgProcessor processor = GameServer.getInstance().getMsgDispatcher().getMsgProcessor(msgCode);

		if (processor == null) {
			return;
		}
		if (!processor.isEnable()) {
			logger.warn("msg processor has been closed : " + msgCode);
			return;
		}

		long starttime = 0;
		if (GameServer.isOpenProcessCountInfo) {
			starttime = System.currentTimeMillis();
		}
		try {
			processor.process(session, request);
		} catch (Exception e) {
			logger.error("process msg with err ", e);
		}
		if (GameServer.isOpenProcessCountInfo) {
			long usetime = System.currentTimeMillis() - starttime;
			processcountinfo.count(msgCode, usetime);
		}
	}

	public void startFrameupdate() {
		if (logger.isDebugEnabled()) {
			logger.debug("人物角色请求、怪物定时攻击等操作，定时轮询开始");
		}

		polling = true;
		frameupdateThread = new Thread(this, FrameUpdateService.class.getName());
		frameupdateThread.start();
	}

	public void stopFrameupdate() {
		polling = false;
	}

	// ---------------------------------------内部类FRAME_RATE_STAT
	/**
	 * 帧率统计类，用于监测服务器性能
	 */
	private static final class FRAME_RATE_STAT {
		// 得到帧率属性
		public int rate = 0;// 次/秒
		private int framecount = 0;
		private long starttime = System.currentTimeMillis();

		public void addCount(long now) {
			framecount++;
			int time = (int) (now - starttime);
			if (time > 5000) {// 每5秒统计一次（在这5秒内刷了多少次）
				rate = Math.round(framecount / (time / 1000f));
				framecount = 0;
				starttime = now;
			}
		}

	}

	// ---------------------------------------内部类PROCESS_COUNT_INFO
	/**
	 * process统计类，用于监测process性能
	 * 
	 */
	private static final class PROCESS_COUNT_INFO {
		// [0]=消息号;[1]=累计处理时间;[2]=累计处理次数;[3]=最长处理时间;[4]=最短处理时间
		public Map<Integer, long[]> countmaps = new HashMap<Integer, long[]>();

		public void count(int msgCode, long usetime) {
			long[] processInfo = countmaps.get(msgCode);
			if (processInfo == null) {
				processInfo = new long[5];
				countmaps.put(msgCode, processInfo);

				processInfo[0] = msgCode;
				processInfo[1] = 0;
				processInfo[2] = 0;
				processInfo[3] = usetime;
				processInfo[4] = usetime;
			}
			// processInfo[0] = msgCode;
			processInfo[1] += usetime;
			processInfo[2]++;
			if (processInfo[3] < usetime) {
				processInfo[3] = usetime;
			}
			if (processInfo[4] > usetime) {
				processInfo[4] = usetime;
			}
		}
	}
}
