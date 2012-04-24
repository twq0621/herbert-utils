package net.snake.gamemodel.heroext.wudao.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.wudao.bean.DGWD;
import net.snake.gamemodel.heroext.wudao.persistence.DGWDController;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 53265, accessLimit = 200)
public class WDUpgradeProcess53265 extends CharacterMsgProcessor {
	public int gradelimit = 62;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (character.getGrade() < gradelimit) {
			character.sendRightPrompMsg(50079, gradelimit + "");
			return;
		}

		byte reqgrade = request.getByte();
		if (reqgrade > 9) {
			return;
		}
		DGWDController dc = character.getDgwdController();
		DGWD nowwd = dc.getDGWD();
		if (reqgrade <= nowwd.getNowwd()) {
			// 以经学过 客户端做限制 正常情况下不会到此
			return;
		}

		if (reqgrade - nowwd.getNowwd() > 1) {
			// 很抱歉，该式的前一式您还没有领悟
			character.sendRightPrompMsg(50077);
			return;
		}
		dc.upgrade();
	}
}
