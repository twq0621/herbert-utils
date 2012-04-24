package net.snake.gamemodel.equipment.processor.strengthen;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.strengthen.NextEquipMsg50124;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 50123 装备下一阶级查询
 * 
 */
@MsgCodeAnn(msgcode = 50123, accessLimit = 20)
public class QueryEquipmentCombiningProcessor extends CharacterMsgProcessor implements IThreadProcessor {
	/**
	 * Logger for this class
	 */
	// private static final Logger logger = Logger.getLogger(QueryEquipmentCombiningProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		// short position = request.getShort();
		int goodModelId = request.getInt();
		// CharacterGoods characterGoods =
		// character.getCharacterGoodController().getGoodsByPositon(position);
		// if (goodModelId != characterGoods.getGoodmodelId().intValue()) {
		// logger.debug("数据错误goodmodelid:{} position:{}");
		// return;
		// }

		Goodmodel goodmodel = GoodmodelManager.getInstance().get(goodModelId);
		EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(goodmodel.getId());
		if (equipmentPlayconfig == null) {
			//			logger.warn("proccesse(GamePlayer, RequestMsg) - 找不到该物品{}的玩法配置", goodModelId); //$NON-NLS-1$

			return;
		}
		int nextGoodModelId = equipmentPlayconfig.getNextGoodmodelId();
		character.sendMsg(new NextEquipMsg50124(goodModelId, nextGoodModelId));
		// characterGoods.createNextCharacterGoods(nextGoodModelId, 0, 0, 0,
		// false);
	}
}
