package net.snake.gamemodel.gift.processor;

import java.util.Date;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;


/**
 * 50717 请求领取升级礼包
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50757,accessLimit=200)
public class GiftDayQiandaoInfoProcessor50757 extends CharacterMsgProcessor implements IThreadProcessor{

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (!character.getMyCharacterGiftpacksManger().getRoleDayQiandaoGiftPacksManager().isSameDate(new Date())) {
			character.getMyCharacterGiftpacksManger().getRoleDayQiandaoGiftPacksManager().getNowGift().setIsOwner(false);
		}
		character.getMyCharacterGiftpacksManger().getRoleDayQiandaoGiftPacksManager().sendQiandaoInfo();
	}

}
