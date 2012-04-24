package net.snake.gamemodel.equipment.processor.strengthen;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GameConstant;
import net.snake.consts.Position;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.strengthen.EquipmentStrengThenPreviewResponse;
import net.snake.gamemodel.equipment.response.strengthen.StrengthenUpgradeMsg50104;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 51311 取预览装备 位置(short),模型id(int)
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 51311, accessLimit = 100)
public class EquipmentStrengThenPreviewProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(EquipmentStrengThenPreviewProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		// 位置(short),模型id(int)
		if (Options.IsCrossServ) {
			return;
		}
		short position = request.getShort();
		int modelId = request.getInt();
		if (position < Position.BagGoodsBeginPostion || position > Position.BagGoodsEndPostion) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20076));
			character.sendMsg(new StrengthenUpgradeMsg50104(0));
			return;
		}
		final CharacterGoods characterGoods = character.getCharacterGoodController().getGoodsByPositon(position);
		if (characterGoods == null) {
			character.sendMsg(new CombineFailMsg50150(0));
			return;
		}
		if (modelId != characterGoods.getGoodmodelId()) {
			logger.warn("data err goodmodelid:"+modelId+" position:"+position);
			return;
		}
		int nextGrade = characterGoods.getStrengthenCount() + 1;
		int newjie = nextGrade % GameConstant.StrengthenJie;
		int newpin = nextGrade / GameConstant.StrengthenJie;
		newpin++;
		if (newjie == 0) {
			newpin = newpin - 1;
		}
		int oldpinjie = characterGoods.getPin();
		CharacterGoods tmpCharacterGoods = (CharacterGoods) characterGoods.clone();

		if (newpin > oldpinjie) {
			final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(
					tmpCharacterGoods.getGoodModel().getId());
			if (equipmentPlayconfig != null) {
				modelId = equipmentPlayconfig.getNextGoodmodelId();
				tmpCharacterGoods.setGoodmodelId(modelId);
				tmpCharacterGoods.setGoodModel(GoodmodelManager.getInstance().get(modelId));
			}
			//99为特殊标示，表示该次强化会有机会产生特殊的附加属性
			tmpCharacterGoods.setAdditionDesc(tmpCharacterGoods.getAdditionDesc()==null?"":tmpCharacterGoods.getAdditionDesc()+ "99" + Symbol.DOUHAO + "0" + Symbol.FENHAO);
		}
		tmpCharacterGoods.setPosition((short) 800);
		tmpCharacterGoods.setStrengthenCount(nextGrade);
		tmpCharacterGoods.equipmentUpdate();
		character.getCombineController().setPreviewCharacterGoods(tmpCharacterGoods);
		EquipmentStrengThenPreviewResponse response = new EquipmentStrengThenPreviewResponse(modelId);
		character.sendMsg(response);
	}
}
