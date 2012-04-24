package net.snake.gamemodel.chat.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.gamemodel.chat.bean.CharacterLimitConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 类型8byte(禁止他人向我发私聊),byte(1-选中,0-不选中), 类型9byte(禁止他人加我好友),byte(1-选中,0-不选中), 类型10byte(禁止他人向我发起交易),byte(1-选中,0-不选中), 类型11byte(禁止他人邀请我加入帮派),byte(1-选中,0-不选中)
 * 类型12byte(禁止他人邀请我加入队伍),byte(1-选中,0-不选中)
 * 
 * @author serv_dev 27001
 * 
 */
@MsgCodeAnn(msgcode = 27001)
public class SysConfigChangeProcs extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte type = request.getByte();
		byte chose = request.getByte();
		CharacterLimitConfig characterLimitConfig = character.getCharacterLimitConfig();
		switch (type) {
		case 8:
			characterLimitConfig.setOtherAbleSendMsgToMe(chose == 1 ? false : true);
			break;
		case 9:
			characterLimitConfig.setOtherAbleJoinMyFriend(chose == 1 ? false : true);
			break;
		case 10:
			characterLimitConfig.setOtherAbleTradeMe(chose == 1 ? false : true);
			break;
		case 11:
			characterLimitConfig.setOtherAbleInviteFaction(chose == 1 ? false : true);
			break;
		case 12:
			characterLimitConfig.setOtherAbleInviteTeam(chose == 1 ? false : true);
			break;
		default:
			return;
		}
		character.sendMsg(SimpleResponse.onlyMsgCodeMsg(27002));
	}
}
