package net.snake.gamemodel.faction.processor.factionflag;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 51087)
public class FactionFlagIcoChangeProcessor51087 extends CharacterMsgProcessor {
	public byte[] icoIds = { 1, 2, 3, 4, 11, 12, 13, 14, 21, 22, 23, 24, 31, 32, 33, 34 };

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte icoId = request.getByte();
		String icoStr = request.getString();
		if (!isValid(icoId)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14713));
			return;
		}
		character.getMyFactionManager().changBangqiIco(icoId, icoStr);
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
