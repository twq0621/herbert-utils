package net.snake.gamemodel.faction.processor.factionflag;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.FactionFlag;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 51089)
public class FactionFlagUpGradeProcessor51089 extends CharacterMsgProcessor {
	public byte[] icoIds = { 1, 2, 3, 4, 11, 12, 13, 14, 21, 22, 23, 24, 31,
			32, 33, 34 };

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		byte icoId = request.getByte();
		String icoStr = request.getString();
		if (!isValid(icoId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14713));
			return;
		}
		if (!character.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 713));
			return;
		}
		FactionFlag flag = character.getMyFactionManager()
				.getFactionController().getFaction().getFactionFlag();
		if (flag.getfGrade() >= 3) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14712));
			return;
		}
		character.getMyFactionManager().changBangqiUpGrade(icoId, icoStr);
	}

	public boolean isValid(byte icoId) {
		for (int i = 0; i < icoIds.length; i++) {
			if (icoId == icoIds[i]) {
				return true;
			}
		}
		return false;
	}
}
