package test;

import gear.robot.ARobot;
import gear.robot.IRobotHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class ActHandler implements IRobotHandler, IChatHandler {

	private AtomicInteger finishThreadCount = new AtomicInteger(0);

	private AtomicInteger chatTakeMsAll = new AtomicInteger(0);

	private final int maxThreadCount;

	private final int totalMsgCount;

	private Map<Integer, Integer> threadMaxMessages = new ConcurrentHashMap<Integer, Integer>();

	private Timer timer;

	public ActHandler(final int maxThreadCount, final int totalMsgCount) {
		this.maxThreadCount = maxThreadCount;
		this.totalMsgCount = totalMsgCount;
		timer = new Timer("onlineNumCounter", true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println(String.format(
						"finishThread/totalThread: %s/%s",
						finishThreadCount.get(), maxThreadCount));
				Map<String, Integer> notFinishMap = new HashMap<String, Integer>();
				for (Map.Entry<Integer, Integer> mapEntry : threadMaxMessages
						.entrySet()) {
					if (mapEntry.getValue() < maxThreadCount * totalMsgCount) {
						notFinishMap.put("thread[" + mapEntry.getKey() + "]",
								mapEntry.getValue());
					}
				}
				System.out.println("not finish thread receive message info:");
				System.out.println(notFinishMap);
			}
		}, 2000, 10000);
	}

	@Override
	public void complete(ARobot robot) {
		if (robot instanceof ChatRobot) {
			ChatRobot enterGameRobot = (ChatRobot) robot;
			String threadInfo = "thread-" + enterGameRobot.getRoleIndex() + ": ";
			long chatTakeMs = enterGameRobot.getChatTakeMs();
			finishThreadCount.incrementAndGet();
			chatTakeMsAll.addAndGet((int) chatTakeMs);
			System.out.println(threadInfo + "finish Thread count:"
					+ finishThreadCount);
			System.out.println(threadInfo
					+ String.format("avg time:%s", chatTakeMsAll.get()
							/ maxThreadCount));
			if (maxThreadCount == finishThreadCount.get()) {
				System.out.println(threadInfo
						+ "-------------finish successfully!-----------------");
				System.out.println(threadInfo + "thread total count:"
						+ maxThreadCount);
				System.out.println(threadInfo
						+ "every thread send message count:" + totalMsgCount);
				System.out.println(threadInfo
						+ String.format("chat avg time:%sms",
								chatTakeMsAll.get() / maxThreadCount));
				System.exit(0);
			}
		}
	}

	@Override
	public void logReceiveMessage(ChatRobot robot) {
		int threadNum = robot.getRoleIndex();
		int maxMsgCount = robot.getCurrentMsgCount();
		threadMaxMessages.put(threadNum, maxMsgCount);
	}

}
