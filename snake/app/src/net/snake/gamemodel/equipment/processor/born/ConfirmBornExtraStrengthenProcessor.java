package net.snake.gamemodel.equipment.processor.born;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.born.BornExtraStrengthenMsg50172;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;
import org.apache.log4j.Logger;


/**
 * 天生属性强化，维持还是替换 51307
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 51307, accessLimit = 200)
public class ConfirmBornExtraStrengthenProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(ConfirmBornExtraStrengthenProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		short pos = request.getShort();
		int model = request.getInt();
		byte weichiTihuan = request.getByte();// 0维持，1替换
		final CharacterGoods equipment = character.getCharacterGoodController().getGoodsByPositon(pos);

		if (equipment == null) {
			logger.warn("no this equipment goodmodelid:"+model+" position:"+pos );
			character.sendMsg(new BornExtraStrengthenMsg50172());
			return;
		}

		if (model != equipment.getGoodmodelId()) {
			logger.warn("no this equipment goodmodelid:"+model+" position:{}"+pos);
			character.sendMsg(new BornExtraStrengthenMsg50172());
			return;
		}

		if (!equipment.getGoodModel().isEquipment()) {
			logger.warn("param err,the first param is not equipment goodmodelid:"+model+" position:"+pos);
			character.sendMsg(new BornExtraStrengthenMsg50172());
			return;
		}

		if (pos < Position.BagGoodsBeginPostion || pos > Position.BagGoodsEndPostion) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
			character.sendMsg(new BornExtraStrengthenMsg50172());
			return;
		}
		if (equipment.getStrengthenAfterBaseDesc() == null || "".equals(equipment.getStrengthenAfterBaseDesc())) {
			return;
		}
		if (weichiTihuan == 1) {
			equipment.setBaseDesc(equipment.getStrengthenAfterBaseDesc());
			character.getCharacterGoodController().updateCharacterGoods(equipment);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60042));
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60043));
		}
		equipment.setStrengthenAfterBaseDesc("");
		character.sendMsg(SimpleResponse.onlyMsgCodeMsg(51308));
	}
}
