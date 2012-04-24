package net.snake.gamemodel.gift.processor;


import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;


/**
 * 50755 请求当月在线时间
 * 
 * 
 */
@MsgCodeAnn(msgcode = 50755,accessLimit=500)
public class GiftOnlineTimeProcessor extends CharacterMsgProcessor implements IThreadProcessor{

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		byte flag = request.getByte();
		if(flag==0){//月在线时间  
			character.getMyCharacterGiftpacksManger().getRoleOnlineGiftPacksManger().sendOnlineTime();
		}else if(flag==1){
			//本次在线时间 还是当日累计 在线
			character.getMyCharacterGiftpacksManger().getRoleDayLineGiftPacks().sendOnlineTime();
		}
		
	}

}
