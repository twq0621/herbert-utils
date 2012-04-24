package net.snake.gamemodel.equipment.logic;

import net.snake.commons.Language;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.DelayLoadingMsg50128;
import net.snake.gamemodel.equipment.response.extra.ResetExtraPropertyMsg50110;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import org.apache.log4j.Logger;


/**
 *  
 * @author jack
 *
 */
public class EquipmentResetExtraProPlay extends EquipmentPlay {
	private static final Logger logger = Logger.getLogger(EquipmentResetExtraProPlay.class);

	public void play(final Hero hero, final CharacterGoods characterGoods, final byte attrs[]) {
		hero.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				resetExtraPro(hero, characterGoods, attrs);
			}
		}, 5 * 1000);

		hero.sendMsg(new DelayLoadingMsg50128());
	}

	private void resetExtraPro(Hero hero, CharacterGoods characterGoods, byte attrs[]) {
		boolean tf = this.condition(hero, characterGoods,attrs);
		if (!tf) {
			hero.sendMsg(new ResetExtraPropertyMsg50110(0));
			return;
		}
		CharacterGoodController characterGoodController = hero.getCharacterGoodController();
		EquipmentPlayconfigManager epm = EquipmentPlayconfigManager.getInstance();
		int scheme = characterGoods.getGoodModel().getId();
		EquipmentPlayconfig ep = epm.getEPlayconfigByGoodsId(scheme);
		// 消耗真气以及物品
		int copper = ep.getResetAddCopper();
		int zhenqi = ep.getResetAddZhenqi();
		CharacterPropertyManager.changeCopper(hero, -copper, CopperAction.CONSUME);
		CharacterPropertyManager.changeZhenqi(hero, -zhenqi);

		int suodingNum = attrs.length;
		int cailiaoNum = ep.getJinglianNum();
		int cailiaoId = ep.getJinglian();
		if (!characterGoodController.combineDeleteCailiao(characterGoods, true, 2, GoodItemId.SuoDingCaiLiao, suodingNum, cailiaoId, cailiaoNum)) {
			logger.warn("bag manager calc with err");
			hero.sendMsg(new ResetExtraPropertyMsg50110(0));
			return;
		}
		characterGoods.randomGenerateExtraPropByPinjie(attrs);
		characterGoods.equipmentUpdate();
		characterGoodController.updateCharacterGoods(characterGoods);
		hero.sendMsg(new ResetExtraPropertyMsg50110(1));
		if (suodingNum > 0) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 974, cailiaoNum + "", Language.LUCKY_CRYSTAL + suodingNum + Language.ONE + ",", copper + "", zhenqi));
		} else {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 974, cailiaoNum + "", "", copper + "", zhenqi));
		}
		hero.getMyCharacterAchieveCountManger().getEquipmentCount().chongzhiEquipment(characterGoods);
	}

	public boolean condition(Hero hero, CharacterGoods characterGoods, byte attrs[]) {
		if (characterGoods.getPingzhi() == 0) {// 白色装备
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20091));
			return false;
		}
		EquipmentPlayconfigManager epm = EquipmentPlayconfigManager.getInstance();
		int scheme = characterGoods.getGoodModel().getId();
		EquipmentPlayconfig ep = epm.getEPlayconfigByGoodsId(scheme);
		if (ep == null) {
//			logger.error("没有该物品：{} 的重置附加属性的配置", new Object[] { characterGoods.getGoodmodelId() });
			hero.sendMsg(new CombineFailMsg50150(1));
			return false;
		}
		int copper = ep.getResetAddCopper();
		int zhenqi = ep.getResetAddZhenqi();
		if (!super.commonCondition(hero, copper, zhenqi)) {
			return false;
		}
		int suodingNum = attrs.length;
		if(suodingNum==characterGoods.getPin()){
			hero.sendMsg(new CombineFailMsg50150(0));
			return false;
		}
		if (suodingNum > 0) {
			int hasSuodingNum = hero.getCharacterGoodController().getBagGoodsCountByModelID(GoodItemId.SuoDingCaiLiao);
			if (suodingNum > hasSuodingNum) {
				hero.sendMsg(new CombineFailMsg50150(4));
				return false;
			}
			String additionDesc = characterGoods.getAdditionDesc();
			int size = 0;
			if (additionDesc != null && additionDesc.length() > 0) {
				String[] extraData = additionDesc.split(Symbol.FENHAO);
				size = extraData.length;
				if (size < suodingNum) {
					return false;
				}
				for (int i = 0; i < suodingNum; i++) {
					if (attrs[i] > size - 1) {
						return false;
					}
				}
			}
		}
		int needJinglianNum = ep.getJinglianNum();
		// 重置材料数量
		if (needJinglianNum > hero.getCharacterGoodController().getBagGoodsCountByModelID(ep.getJinglian())) {
			hero.sendMsg(new CombineFailMsg50150(9));
			return false;
		}
		return true;
	}

}
