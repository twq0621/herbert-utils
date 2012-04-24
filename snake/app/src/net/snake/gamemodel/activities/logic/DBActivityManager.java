package net.snake.gamemodel.activities.logic;

import net.snake.GameServer;
//import net.snake.api.context.ISubline;
import net.snake.commons.program.SafeTimer;
import net.snake.commons.program.Updatable;
import net.snake.gamemodel.activities.persistence.DoubActivityManager;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.serverenv.vline.CharacterRun;
import net.snake.serverenv.vline.VLineServer;


/**
 * 双倍活动管理
 * @author serv_dev
 */
public class DBActivityManager implements Updatable {
	private boolean issend1 = false;
	private boolean issend2 = false;
	private boolean issend3 = false;
	private VLineServer vlserver;
	//private ISubline subline;
	private SafeTimer timeclick;

	public DBActivityManager(VLineServer vlServer) {
		vlserver = vlServer;
		timeclick = new SafeTimer(10000);
	}
	//public DBActivityManager(ISubline line){
	//	subline = line;
	//	timeclick = new SafeTimer(10000);
	//}

	public boolean isIssend1() {
		return issend1;
	}

	public void setIssend1(boolean issend1) {
		this.issend1 = issend1;
	}

	public boolean isIssend2() {
		return issend2;
	}

	public void setIssend2(boolean issend2) {
		this.issend2 = issend2;
	}

	@Override
	public void update(long now) {
		if (!timeclick.isIntervalOK(now)) {
			return;
		}
		DoubActivityManager dm = GameServer.doubActivityManager;
		if (dm.isReload()) {
			issend1 = false;
			issend2 = false;
			issend3 = false;
			dm.setReload(false);
		}
		if (dm.checkStartTime(now)) {
			if (!issend3) {
				dm.swichDBDoubactivity();
				send(40032);
				sendMsg();
				issend3 = true;
			}
			return;
		}
		if (issend3) {
			dm.swichDefaultDoubActivity();
			send(40033);
			issend3 = false;
			return;
		}

		if (dm.checkSendTime2(now)) {
			if (!issend2) {
				String next = dm.getNext();
				if (!next.equals("unrefresh")) {
					send(40034, dm.getNext());
				}
				issend2 = true;
			}
			return;
		}
		issend2 = false;
		if (dm.checkSendTime1(now)) {
			if (!issend1) {
				String next = dm.getNext();
				if (!next.equals("unrefresh")) {
					send(40034, dm.getNext());
				}
				issend1 = true;
			}
			return;
		}
		issend1 = false;
	}

	private void send(final int msgkey,final String... msg) {
		vlserver.runToOnlineCharacter(new CharacterRun() {
			public void run(Hero character) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_SYSBROADCAST,msgkey,msg));
			}
		});
	}
	
	private void sendMsg(){
		vlserver.runToOnlineCharacter(new CharacterRun() {
			public void run(Hero character) {
				character.getMyNewcomeManager().sendDoubleExpZhenqiActivityMsg();
			}
		});
		
	}
}
