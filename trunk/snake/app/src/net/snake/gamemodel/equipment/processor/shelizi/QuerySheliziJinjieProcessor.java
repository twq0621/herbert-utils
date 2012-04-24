package net.snake.gamemodel.equipment.processor.shelizi;

import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.shelizi.QuerySheliziJinJieMsg52108;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;



/**
 * 舍利子合成查询 52107
 * 
 */
@MsgCodeAnn(msgcode = 52107, accessLimit = 200)
public class QuerySheliziJinjieProcessor extends CharacterMsgProcessor implements IThreadProcessor {
	private static Logger logger = Logger.getLogger(QuerySheliziJinjieProcessor.class);

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
			character.sendMsg(new QuerySheliziJinJieMsg52108(0, 0, 0, 0, 0, 0, 0));
			return;
		}

//		character.sendMsg(new QuerySheliziJinJieMsg52108(1, goodModelId, equipmentPlayconfig.getSheliziCopper(), equipmentPlayconfig.getShenjieshiNum(), equipmentPlayconfig
//				.getSheliziShowLv(), equipmentPlayconfig.getNextGoodmodelId(), Position.SHENLIZI_NEXT_TIPS));
	}
}
