package net.snake.gamemodel.heroext.zhenqi.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.zhenqi.response.QueryGuShuJingHunMsg50706;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;


/**
 * 提取真气需要多少个古树精魂
 * @author serv_dev
 *
 */
@MsgCodeAnn(msgcode = 50705,accessLimit=0)
public class QueryGuShuJingHunPros50705 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (LinshiActivityManager.getInstance().isTimeByLinshiActivityID(9)) {
				character.sendMsg(new QueryGuShuJingHunMsg50706(1,5));
		}else {
			Goodmodel gm = GoodmodelManager.getInstance().get(GoodItemId.chongqiwawa_full);
			character.sendMsg(new QueryGuShuJingHunMsg50706(1,gm.getChongqiMax()));
		}
	}
}
