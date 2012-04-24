package net.snake.gamemodel.heroext.channel.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Breakthrough;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.BreakthroughManager;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50210;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 50209)
public class ChannelRealdragonProcess50209 extends CharacterMsgProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {

		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		// 验证经脉能不能突破瓶颈
		int datong = character.getMyChannelManager().getDatongjinmai();
		if (datong < 8) {
			character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60003));
			return;
		}
		// 突破瓶颈
		Breakthrough breakthrough = BreakthroughManager.getInstance().getBreakthroughMap().get(1);// 1经脉突破瓶颈需求,是用type区分的

		if (character.getZhenqi() < breakthrough.getNeedZhenqi().intValue()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60002));
			character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
			return;
		}

		if (character.getChannelRealdragon().length() > 3) {
			character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60007));
			return;
		}
		character.getMyChannelManager().addPingJing(breakthrough);

	}

}
