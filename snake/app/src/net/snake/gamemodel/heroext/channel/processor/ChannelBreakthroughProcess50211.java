package net.snake.gamemodel.heroext.channel.processor;

/**
 * 返回突破瓶颈
 * 
 */
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50210;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50212;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 50211,accessLimit=100)
public class ChannelBreakthroughProcess50211 extends CharacterMsgProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		
		//跨服判断
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		// 验证经脉能不能突破瓶颈
		int datong=character.getMyChannelManager().getDatongjinmai();
		if(datong<8){
			character.sendMsg(new ChannelResponse50210(CommonUseNumber.byte0));
			  character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,60003));
			return;
		}
		
		character.sendMsg(new ChannelResponse50212(character));
	}


}
