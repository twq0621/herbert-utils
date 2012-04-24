package net.snake.gamemodel.heroext.dantian.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.heroext.dantian.bean.DantianModel;
import net.snake.gamemodel.heroext.dantian.response.UpGradeInfo53154;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 53153)
public class UpGradeQueryProcess53153 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		DanTian danTian = character.getDanTianController().getDanTian();
		if (danTian != null && danTian.getDantianid() < 8) {
			DantianModel model = danTian.getModel();
			character.sendMsg(new UpGradeInfo53154(model.getId(), danTian.getLuck(), model.getMaxZhufu(), model.getUpProbabilityShow(), model.getUpNeedgoodid(), model
					.getUpNeedgoodnum()));
		} else {
			if (danTian != null && danTian.getDantianid() >= 8) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50065));
			}
			if (danTian == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 50069));
			}

		}
	}
}
