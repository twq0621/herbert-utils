package net.snake.gamemodel.quickbar.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.action.UseGoodAction;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.quickbar.logic.QuickBarGoods;
import net.snake.netio.message.RequestMsg;

/**
 * 
 * 快捷栏使用 道具
 * 
 * @author serv_dev
 * 
 */

@MsgCodeAnn(msgcode = 11203)
public class QuickBarUsedProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte quickBarIndex = request.getByte();
		int goodModelId = request.getInt();
		QuickBarGoods quickBarGoods = character.getQuickbarController().getQuickBarItem(quickBarIndex);
		if (!(quickBarGoods instanceof CharacterGoods)) {
			return;
		}
		CharacterGoods goods = (CharacterGoods) quickBarGoods;
		UseGoodAction uga = goods.getUseGoodAction();
		if (uga == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 878));
			return;
		}
		if (1214 == goods.getGoodmodelId()) {
			Horse ride = character.getCharacterHorseController().getShowHorse();
			if (ride == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 818));
				return;
			}
			if (ride.getHorseModel().getKind() < 9) {
				character.sendMsg(new TipMsg(TipMsg.MSGPOSITION_ERRORTIP, "超级悟性丹限豹子及以上坐骑才可使用"));
				return;
			}
		}
		uga.useGoodDoSomething(character, goodModelId, goods.getPosition(),character.getSceneRef().getUseItemListeners());
	}

}
