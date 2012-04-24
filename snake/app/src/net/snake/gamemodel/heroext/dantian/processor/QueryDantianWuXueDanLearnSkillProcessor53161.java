package net.snake.gamemodel.heroext.dantian.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.gamemodel.heroext.dantian.bean.DantianModel;
import net.snake.netio.message.RequestMsg;

/**
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 53161, accessLimit = 200)
public class QueryDantianWuXueDanLearnSkillProcessor53161 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		DanTian cha = character.getDanTianController().getDanTian();
		if (cha == null) {
			return;
		}
		DantianModel req = cha.getModel();
		if (req == null) {
			return;
		}
		String canleanSkillitem = req.getCanleanSkillitem();
		String[] split = new String[] {};
		if (canleanSkillitem != null && !canleanSkillitem.trim().equals("")) {
			split = canleanSkillitem.split(Symbol.FENHAO);
		}
		character.sendMsg(new DanTianWuXueSkillInfoMsg53162(cha.getDantianid(), character.getSkillManager(), split));
	}
}
