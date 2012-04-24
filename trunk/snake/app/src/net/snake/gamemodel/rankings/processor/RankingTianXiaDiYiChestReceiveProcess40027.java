package net.snake.gamemodel.rankings.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.rankings.response.RankingResponse40026;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 40027)
public class RankingTianXiaDiYiChestReceiveProcess40027 extends CharacterMsgProcessor implements IThreadProcessor {

	/**
	 * @param character
	 * @param request
	 * @throws Exception
	 */
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		CharacterTianXiaDiYi characterTianXiaDiYi = CharacterTianXiaDiYiManager.getInstance().getHaracterTianXiaDiYiMap()
				.get(character.getCharacterInitiallyId().toString() + character.getOriginalSid());

		if (null == characterTianXiaDiYi) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 667));
			return;
		}
		if (characterTianXiaDiYi.getChestCount() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 668));
			return;
		}
		// 取得人物背包地方
		Short beibaoindex = character.getCharacterGoodController().getBagGoodsContiner().findFirstIdleGirdPosition();
		// 人物物品管理者
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		if (beibaoindex == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return;
		}
		characterTianXiaDiYi.setChestCount(2);
		CharacterGoods characterGoods = CharacterGoods.createCharacterGood(3300, 2, CommonUseNumber.byte1);
		characterGoodController.addGoodsToBag(characterGoods);
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 19012));
		CharacterTianXiaDiYiManager.getInstance().updateCharacterTianXiaDiYi(characterTianXiaDiYi);
		RankingResponse40026 response40026 = new RankingResponse40026(CommonUseNumber.byte1, CommonUseNumber.byte0);
		character.sendMsg(response40026);

	}

}
