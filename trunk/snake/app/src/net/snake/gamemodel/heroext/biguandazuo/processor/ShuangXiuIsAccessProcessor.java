package net.snake.gamemodel.heroext.biguandazuo.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 50511, accessLimit = 1000)
public class ShuangXiuIsAccessProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
		int characterId = request.getInt();
		byte tag = request.getByte();
		if (type == 1) {
			Hero other = character.getVlineserver().getOnlineManager().getByID(characterId);
			if (tag == 1) {
				character.getMyDazuoManager().accessShuangXiuWith(other);
			} else {
				if (other != null) {
					other.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 833));
					character.getMyDazuoManager().removeIniviteShuangxiuTime(characterId);
				}
			}
		} else {
			Hero other = character.getVlineserver().getOnlineManager().getByID(characterId);
			if (tag == 1) {
				character.getMyDazuoManager().accessLiaoshangWith(other);
				character.getMyDazuoManager().removeIniviteLiaoshangTime(characterId);
			} else {
				if (other != null) {
					other.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP, "对方拒绝了你的疗伤请求"));
					character.getMyDazuoManager().removeIniviteLiaoshangTime(characterId);
				}
			}
		}

	}

}
