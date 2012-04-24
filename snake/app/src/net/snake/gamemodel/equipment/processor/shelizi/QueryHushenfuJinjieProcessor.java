package net.snake.gamemodel.equipment.processor.shelizi;

import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.QueryHushenfuJinJieMsg52112;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;



/**
 * 护身符进阶查询 52111
 * 
 */
@MsgCodeAnn(msgcode = 52111, accessLimit = 200)
public class QueryHushenfuJinjieProcessor extends CharacterMsgProcessor implements IThreadProcessor {
	private static Logger logger = Logger.getLogger(QueryHushenfuJinjieProcessor.class);

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
		EquipmentPlayconfig equipmentPlayconfig = null;
		equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodModel().getId());
		if (equipmentPlayconfig == null) {
			character.sendMsg(new QueryHushenfuJinJieMsg52112(0, 0, 0, 0, 0, 0, 0));
			return;
		}

		final int nextGoodModelId = equipmentPlayconfig.getNextGoodmodelId();

		if ((nextGoodModelId == GoodItemId.SL_FUSHENFU)) {
			character.sendMsg(new QueryHushenfuJinJieMsg52112(0, 0, 0, 0, 0, 0, 0));
			return;
		}

//		character.sendMsg(new QueryHushenfuJinJieMsg52112(1, goodModelId, equipmentPlayconfig.getSheliziCopper(), equipmentPlayconfig.getShenjieshiNum(), equipmentPlayconfig
//				.getSheliziShowLv(), equipmentPlayconfig.getNextGoodmodelId(), Position.HUSHENFU_NEXT_TIPS));
	}
}
