package net.snake.gamemodel.faction.processor.factionflag;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 51095)
public class FactionFlagEnterSceneProcessor51095 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int positionId = request.getInt();
		if (!isPkTime()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 540));
			return;
		}
		character.getMyFactionManager().bangqiEnterScene(positionId);
	}

	public boolean isPkTime() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		if (day == 2 || day == 4 || day == 6) {// 只有每周一，每周三，每周五晚上20：00—22：00可以砍旗
			int hours = calendar.get(Calendar.HOUR_OF_DAY);
			if (hours < 20 || hours >= 22) {
				return false;
			}
		} else {
			return false;
		}

		return true;
	}
}
