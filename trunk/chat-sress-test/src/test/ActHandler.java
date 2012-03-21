package test;

import gear.robot.ARobot;
import gear.robot.IRobotHandler;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class ActHandler implements IRobotHandler, IChatHandler {

	private AtomicInteger finishThreadCount = new AtomicInteger(0);

	private AtomicInteger chatTakeMsAll = new AtomicInteger(0);

	private int maxThreadCount;

	private int totalMsgCount;

	private Map<Integer, Integer> threadMaxMessages = new ConcurrentHashMap<Integer, Integer>();

	private Timer timer;

	public ActHandler(int maxThreadCount, int totalMsgCount) {
		this.maxThreadCount = maxThreadCount;
		this.totalMsgCount = totalMsgCount;
		timer = new Timer("onlineNumCounter", true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println(threadMaxMessages);
			}
		}, 2000, 20000);
	}

	@Override
	public void complete(ARobot robot) {
		if (robot instanceof ChatRobot) {
			ChatRobot enterGameRobot = (ChatRobot) robot;
			long chatTakeMs = enterGameRobot.getChatTakeMs();
			finishThreadCount.incrementAndGet();
			chatTakeMsAll.addAndGet((int) chatTakeMs);
			System.out.println("finish Thread count:" + finishThreadCount);
			System.out.println(String.format("avg time:%s", chatTakeMsAll.get()
					/ maxThreadCount));
			if (maxThreadCount == finishThreadCount.get()) {
				System.out
						.println("-------------finish successfully!-----------------");
				System.out.println("thread total count:" + maxThreadCount);
				System.out.println("every thread send message count:"
						+ totalMsgCount);
				System.out.println(String.format("chat avg time:%sms",
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
