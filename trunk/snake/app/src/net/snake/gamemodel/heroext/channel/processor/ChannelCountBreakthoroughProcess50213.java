package net.snake.gamemodel.heroext.channel.processor;

/**
 * 返回经脉加成数据
 * 
 * @author serv_dev
 */
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.response.ChannelResponse50214;
import net.snake.netio.message.RequestMsg;


@MsgCodeAnn(msgcode = 50213,accessLimit=100)
public class ChannelCountBreakthoroughProcess50213 extends CharacterMsgProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		character.sendMsg(new ChannelResponse50214(character));
	}


}
