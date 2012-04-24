package net.snake.gamemodel.equipment.processor.shelizi;



import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.HushenfuJinJieMsg52124;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 护身符进阶
 * 
 */
@MsgCodeAnn(msgcode = 52123, accessLimit = 500)
public class ShenLong_HushenfuJinjieProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(ShenLong_HushenfuJinjieProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int goodModelId = request.getInt();
		short position = request.getShort();
		CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(position);
		if (goodModelId != characterGoods.getGoodmodelId()) {
			if (logger.isDebugEnabled()) {
				logger.debug("数据错误goodmodelid:{} position:{}");
			}

			return;
		}
		if (position < Position.BagGoodsBeginPostion || position > Position.BagGoodsEndPostion) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
			character.sendMsg(new HushenfuJinJieMsg52124(0, characterGoods.getPosition()));
			return;
		}

		EquipmentPlayconfig equipmentPlayconfig = null;
		equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());
		if (equipmentPlayconfig == null) {
			character.sendMsg(new HushenfuJinJieMsg52124(0, characterGoods.getPosition()));
			return;
		}

		character.getCombineController().createHushenfuNextCharacterGoods2(characterGoods);
	}
}