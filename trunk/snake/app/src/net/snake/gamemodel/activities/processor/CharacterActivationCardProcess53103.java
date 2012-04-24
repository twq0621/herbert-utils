package net.snake.gamemodel.activities.processor;

import java.util.List;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.activities.bean.ActivationCard;
import net.snake.gamemodel.activities.response.ActivationCardResponse53104;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 53103)
public class CharacterActivationCardProcess53103 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		String cardNo = request.getString();
		// 验证空字串
		if (cardNo == null || cardNo.length() <= 0) {
			// 提示卡号不合法
			character.sendMsg(new PrompMsg(1180));
			return;
		}
		// 验证卡是否存在
		ActivationCard activationCard = GameServer.activationCardManager.findActivationCardByCardNo(cardNo);
		if (activationCard == null) {
			// 提示卡号不合法
			character.sendMsg(new PrompMsg(1181));
			return;
		}
		// 验证是否被领取
		if (GameServer.characterActivationCardManager.isExchangeForCard(character.getOriginalSid(), activationCard)) {
			// 已经被领取
			character.sendMsg(new PrompMsg(1182));
			return;
		}
		// 验证是否被领取
		if (GameServer.characterActivationCardManager.isExchangeForAccount(character, activationCard)) {
			// 已经被领取
			character.sendMsg(new PrompMsg(1184));
			return;
		}
		// 验证包裹空位
		List<CharacterGoods> list = activationCard.getGoodlist(character.getPopsinger());
		if (list != null && !list.isEmpty()) {
			if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(list)) {
				// 空位不够
				character.sendMsg(new PrompMsg(1180));
				return;
			}
		}

		// 完成激活码兑换
		if (GameServer.characterActivationCardManager.doExchange(character, activationCard) == null) {
			character.sendMsg(new PrompMsg(1183));
		} else {
			// 返回正确消息，关闭窗口
			character.sendMsg(new ActivationCardResponse53104());
		}

	}

}
