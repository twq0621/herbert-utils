package test;

import gear.robot.ARobot;
import gear.robot.IRobotHandler;

public class ActHandler implements IRobotHandler {

	private int finishThreadCount = 0;

	private long chatTakeMsAll = 0;

	private int maxThreadCount;

	private int totalMsgCount;

	public ActHandler(int maxThreadCount, int totalMsgCount) {
		this.maxThreadCount = maxThreadCount;
		this.totalMsgCount = totalMsgCount;
	}

	@Override
	public void complete(ARobot robot) {
		if (robot instanceof EnterGameRobot) {
			EnterGameRobot enterGameRobot = (EnterGameRobot) robot;
			long chatTakeMs = enterGameRobot.getChatTakeMs();
			finishThreadCount++;
			chatTakeMsAll += chatTakeMs;
			System.out.println("finish Thread count:" + finishThreadCount);
			System.out.println(String.format("avg time:%s", chatTakeMsAll
					/ maxThreadCount));
			if (maxThreadCount == finishThreadCount) {
				System.out
						.println("-------------finish successfully!-----------------");
				System.out.println("thread total count:" + maxThreadCount);
				System.out.println("every thread send message count:"
						+ totalMsgCount);
				System.out.println(String.format("chat avg time:%sms",
						chatTakeMsAll / maxThreadCount));
				System.exit(0);
			}
		}
	}

}
