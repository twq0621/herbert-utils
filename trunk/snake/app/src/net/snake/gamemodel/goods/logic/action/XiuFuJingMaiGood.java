package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;

public class XiuFuJingMaiGood implements UseGoodAction {

	public XiuFuJingMaiGood(Goodmodel gm) {
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {

		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		String failChannelIds = character.getMyChannelManager().getFailChannelId();
		if (failChannelIds != null && !"".equals(failChannelIds)) {
			int mixChannel = 0;// 找编号最小的受伤经脉
			String[] failChannelIdsArr = failChannelIds.split(",");
			for (int i = 0; i < failChannelIdsArr.length; i++) {
				String tmpchannelId = failChannelIdsArr[i];
				if (!"".equals(tmpchannelId)) {
					int channelId = Integer.parseInt(tmpchannelId);
					if (mixChannel == 0) {
						mixChannel = channelId;
					}
					if (channelId < mixChannel) {
						mixChannel = channelId;
					}
				}
			}

			if (mixChannel == 0) {// 没有受损的经脉
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20154));
				return false;
			} else {
				int debuffId = character.getMyChannelManager().getChannelBebuffId(mixChannel + "");
				boolean hasremove = character.getEffectController().removeBuffById(debuffId);
				if (hasremove) {
					character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) positon, 1);
					return true;
				}
				return false;
			}
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20154));
			return false;
		}
	}

}
