package net.snake.gamemodel.equipment.processor.shelizi;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.response.ZhanWenKeHuaResultMsg50180;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 是否替换图腾属性
 */
@MsgCodeAnn(msgcode = 50189, accessLimit = 200)
public class ReplaceTotemProcessor50189 extends CharacterMsgProcessor {
	// private static Logger logger = Logger.getLogger(ReplaceTotemProcessor50189.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		short pos = request.getShort();
		byte tihuan = request.getByte();

		final CharacterGoods equipment = character.getCharacterGoodController().getGoodsByPositon(pos);

		if (equipment == null) {
			// logger.warn("装备部存在goodmodelid:{} position:{}", new Object[] { 0, pos });
			return;
		}

		if (!equipment.getGoodModel().isEquipment()) {
			// logger.warn("参数错误，第一个物品不是装备goodmodelid:{} position:{}", new Object[] { model, pos });
			return;
		}

		if (tihuan == 1) {
			if (equipment.getNextTotem() != null) {
				// TotemManager totemManager = TotemManager.getInstance();
				// equipment.setTotem(equipment.getNextTotem());
				equipment.setBind(CommonUseNumber.byte1);// 必定绑定
				equipment.setNextTotem(null);
				character.getCharacterGoodController().updateCharacterGoods(equipment);
				// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20108, totemManager.getTotemStr(equipment)));
				character.sendMsg(new ZhanWenKeHuaResultMsg50180(1));
			}
		} else {
			equipment.setNextTotem(null);
		}

	}

}
