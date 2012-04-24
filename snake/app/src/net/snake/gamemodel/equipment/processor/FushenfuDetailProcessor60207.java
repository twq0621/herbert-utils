package net.snake.gamemodel.equipment.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.EquipmentDetailMsg50102;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 在护身符中的舍利子提示
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60207, accessLimit = 200)
public class FushenfuDetailProcessor60207 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int pos = request.getInt();
		int index = request.getInt();
		CharacterGoods charactergood = character.getCharacterGoodController().getGoodsByPositon((short) pos);
		if (index == Position.HUSHENFU_NEXT_TIPS) {
			if (!charactergood.getGoodModel().isFuShenfu()) {
				return;
			}

			EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(charactergood.getGoodModel().getId());
			if (equipmentPlayconfig == null)
				return;
			CharacterGoods _characterGoods = charactergood.createNextHushenfuCharacterGoods(equipmentPlayconfig.getNextGoodmodelId(), false);
			character.sendMsg(new EquipmentDetailMsg50102(_characterGoods));
			return;
		} else if (index == Position.SHENLIZI_NEXT_TIPS) {
			if (!charactergood.getGoodModel().isShelizi()) {
				return;
			}
			EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(charactergood.getGoodModel().getId());
			if (equipmentPlayconfig == null)
				return;
			CharacterGoods _characterGoods = charactergood.createNextSheliziCharacterGoods(equipmentPlayconfig.getNextGoodmodelId(), false);
			character.sendMsg(new EquipmentDetailMsg50102(_characterGoods));
		} else {

			if (!charactergood.getGoodModel().isFuShenfu()) {
				return;
			}

			// 4600, 4650 4600第一个位置
			String slz = charactergood.getShelizhiInEquipId();
			String[] slzstr = slz.split(";");
			int cnt = index - Position.HUSHENFU_SHENLIZI_BEGIN;
			if (0 <= cnt && cnt <= slzstr.length - 1) {
				String sheliziStr = slzstr[cnt];
				if (!"".equals(sheliziStr)) {
					String[] _sheliziStr = sheliziStr.split(",");
					int sheliziId = Integer.parseInt(_sheliziStr[0]);
					int skillId = Integer.parseInt(_sheliziStr[1]);
					Goodmodel goodmodel = GoodmodelManager.getInstance().get(sheliziId);
					if (goodmodel.isShelizi()) {
						CharacterGoods _characterGoods = CharacterGoods.createCharacterGoods(1, sheliziId, 0);
						_characterGoods.setWugongId(skillId);
						_characterGoods.setPosition((short) index);
						character.sendMsg(new EquipmentDetailMsg50102(_characterGoods));
					}
				}
			}
		}
	}
}
