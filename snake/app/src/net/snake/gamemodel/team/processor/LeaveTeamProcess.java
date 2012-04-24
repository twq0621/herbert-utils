package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.script.IEventListener;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 离队操作 消息 10199
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10199)
public class LeaveTeamProcess extends MsgProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero character = session.getCurrentCharacter(Hero.class);
		character.getMyTeamManager().leaveTeam();
		AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_OutOfTeam, new Object[]{character});
	}

}
