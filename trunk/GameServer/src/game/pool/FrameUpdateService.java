package game.pool;

import game.GameServer;
import game.common.KernelConstants;
import game.common.SafeTimer;
import game.entity.Hero;
import game.serverenv.GameServerOnlineManager;

import java.util.concurrent.ConcurrentLinkedQueue;

import lion.core.GamePlayer;
import lion.core.SpringContextHolder;
import lion.message.MyRequestMsg;
import lion.processor.MsgProcessor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameUpdateService implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(FrameUpdateService.class);

	private Thread frameupdateThread = null;

	/**
	 * 调用线程是否是刷帧线程
	 * 
	 * @return
	 */
	public boolean isFrameUpdateThread() {
		return Thread.currentThread() == frameupdateThread;
	}

	public Thread getFrameUpdateThread() {
		return frameupdateThread;
	}

	private volatile boolean polling = false;
	private volatile ConcurrentLinkedQueue<Runnable> taskList = new ConcurrentLinkedQueue<Runnable>();

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

	@Override
	public void run() {
		int interval = 41;// 标准刷桢时间间隔
		long sleeptime = 0;// 每次应该休息的时间
		SafeTimer commontimer = new SafeTimer(100);// 普通刷桢 100毫秒一次
		while (polling) {
			long starttime = System.currentTimeMillis();
			try {
				processPlayerMsg(starttime);
			} catch (Exception e) {
				logger.error("handle hero's msg with err", e);
			}
			if (commontimer.isIntervalOK(starttime)) {
				// TODO 其他服务器中需要不断刷新的job，例如事件，公告等
				// 必须要刷楨循环里执行的任务
				for (Runnable run = taskList.poll(); run != null; run = taskList.poll()) {
					try {
						run.run();
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
		}
	}

	private void processPlayerMsg(long now) {
		GameServerOnlineManager onlineManager = SpringContextHolder.getBean(GameServerOnlineManager.class);
		for (Hero character : onlineManager.getCharacterList()) {
			try {
				// if (ClientConfig.HEART_BEAT_CHECK) {
				// // 超过60秒的玩家直接剔除,目前客户端每30秒发一次心跳
				// if ((now - character.lastHeatBeat) > ClientConfig.HeatBeatInterval1) {
				// if (character.isEnableHeatBeat()) {
				// character.getDownLineManager().downLine(null);
				// continue;
				// }
				// }
				// }
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

		MyRequestMsg request = session.executeRequest();
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

		if (KernelConstants.isOpenProcessCountInfo) {
			starttime = System.currentTimeMillis();
			try {
				processor.process(session, request);
			} catch (Exception e) {
				logger.error("process msg with err ", e);
			}
			long usetime = System.currentTimeMillis() - starttime;
		} else {
			try {
				processor.process(session, request);
			} catch (Exception e) {
				logger.error("process msg with err ", e);
			}
		}
	}

	public void startFrameupdate() {
		logger.warn("人物角色请求、怪物定时攻击等操作，定时轮询刷帧开始");
		polling = true;
		frameupdateThread = new Thread(this, FrameUpdateService.class.getName());
		frameupdateThread.start();
	}

	public void stopFrameupdate() {
		polling = false;
	}

}
