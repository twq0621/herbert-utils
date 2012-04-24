package net.snake.gamemodel.heroext.wudao.processor;

import java.io.IOException;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.persistence.DGWDManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.wudao.bean.DGWD;
import net.snake.gamemodel.heroext.wudao.bean.DgwdModel;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 53263, accessLimit = 200)
public class WDQueryProcess53263 extends CharacterMsgProcessor {
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
		byte grade = request.getByte();
		if (grade > 9) {
			character.sendRightPrompMsg(50078);
			return;
		}
		if (grade < 0) {
			// 非法参数
			return;
		}
		character.sendMsg(new WDQueryResult53264(grade, character));
		// if(grade<=nowwd.getNowwd()||grade>=9){
		// //以经学过 客户端做限制 正常情况下不会到此
		// return;
		// }
		// if(grade-nowwd.getNowwd()>1){
		// //很抱歉，该式的前一式您还没有领悟
		// character.sendRightPrompMsg(50077);
		// return ;
		// }
		// dc.upgrade();
		// 诀数ID(byte)
	}
	// 当前祝福值(int),最大祝福值(int),领悟前提诀ID(byte),领悟消耗物品id(int),消耗物品数量(int)，消耗真气(int),成功几率(int)

}

class WDQueryResult53264 extends ServerResponse {
	public WDQueryResult53264(int querygrade, Hero c) throws IOException {
		setMsgCode(53264);
		ServerResponse out = this;
		DgwdModel model = DGWDManager.getInstance().getModel(querygrade);
		out.writeByte(querygrade);
		if (model == null) {
			return;
		}
		DGWD dgwd = c.getDgwdController().getDGWD();
		if (model.equals(dgwd.getNextModel())) {
			out.writeInt(dgwd.getLuck());
		} else {
			out.writeInt(0);
		}
		out.writeInt(model.getZhufuMax());
		out.writeByte(querygrade - 1);
		out.writeInt(model.getConsumeGoodid());
		out.writeInt(model.getConsumeNum());
		out.writeInt(model.getConsumeZq());
		out.writeInt(model.getUpprobShow());
	}
}
