package net.snake.gamemodel.equipment.logic;

import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GoodItemId;
import net.snake.consts.Position;
import net.snake.consts.Symbol;
import net.snake.consts.TakeMethod;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.DelayLoadingMsg50128;
import net.snake.gamemodel.equipment.response.gem.GemTakeoutMsg50198;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

import org.apache.log4j.Logger;

/**
 * 宝石拔除
 * 
 * @author jack
 * 
 */
public class EquipmentGemTakeoutPlay extends EquipmentPlay {
	private static final Logger logger = Logger.getLogger(EquipmentGemTakeoutPlay.class);

	public void play(final Hero hero, final CharacterGoods equipment, final byte[] baoshiPos) {
		int time = 5 * 1000;
		hero.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				gemTakeout(hero, equipment, baoshiPos);
			}
		}, time);
		hero.sendMsg(new DelayLoadingMsg50128());
	}

	private void gemTakeout(final Hero hero, final CharacterGoods equipment, byte[] baoshiPos) {
		if (!hero.getCharacterGoodController().deleteCailiao(GoodItemId.ZHAICHU, baoshiPos.length)) {
			logger.warn("bag manager calc with err");
			return;
		}
		// 使用了玉凿和没使用玉凿但没有破碎的情况
		// 删除真气/铜币

		EquipmentPlayconfig equipmentPlayconfig = null;
		int bachuZhenqi = 0;
		int bachuCcopper = 0;
		String[] inequipids = equipment.getInEquipId().split(Symbol.FENHAO);
		for (int i = 0; i < baoshiPos.length; i++) {
			int gemsId =Integer.parseInt(inequipids[baoshiPos[i]]);
			equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(gemsId);
			bachuZhenqi += equipmentPlayconfig.getBachuZhenqi();
			bachuCcopper += equipmentPlayconfig.getBachuCopper();
		}
		CharacterPropertyManager.changeZhenqi(hero, -bachuZhenqi);
		CharacterPropertyManager.changeCopper(hero, -bachuCcopper, CopperAction.CONSUME);
		// 拨除成功并创建宝石
		String[] takeoutStrone = takeoutGembyEquipment(hero, equipment, baoshiPos);
		if (takeoutStrone == null) {
			logger.warn("remove gem with err,because no this gem");
			hero.sendMsg(new GemTakeoutMsg50198(0));
			return;
		}
		for (int i = 0; i < takeoutStrone.length; i++) {
			CharacterGoods baoshi = CharacterGoods.createCharacterGoods(1, Integer.parseInt(takeoutStrone[i]), 0);
			baoshi.setBind(CommonUseNumber.byte1);// 只要是拨出的就是绑定的
			hero.getCharacterGoodController().addGoodsToBag(baoshi);
		}
		hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 985, bachuZhenqi,bachuCcopper));
		hero.sendMsg(new GemTakeoutMsg50198(1));
	}

	/**
	 * 拨除装备上的宝石
	 */
	private String[] takeoutGembyEquipment(Hero hero, CharacterGoods equipment, byte[] baoshi) {
		String[] inequipids = equipment.getInEquipId().split(Symbol.FENHAO);
		String[] takeoutStrone = new String[baoshi.length];
		StringBuilder newStrones = new StringBuilder();
		int num = 0;
		for (int i = 0; i < inequipids.length; i++) {
			String stroneDetail = inequipids[i];
			boolean tf = false;
			for (int j = 0; j < baoshi.length; j++) {
				if (i == baoshi[j]) {
					tf = true;
					takeoutStrone[num] = stroneDetail;
					num++;
					break;
				}
			}
			if (tf) {
				continue;
			}
			newStrones.append(stroneDetail).append(Symbol.FENHAO);
		}

		String newinequipid = newStrones.toString();
		if (newinequipid.endsWith(Symbol.FENHAO)) {
			newinequipid.substring(0, newinequipid.length() - 1);
		}
		equipment.setInEquipId(newinequipid);

		hero.getCharacterGoodController().updateCharacterGoods(equipment);
		if (equipment.getPosition() >= Position.BodyGoodsBeginPostion && equipment.getPosition() <= Position.BodyGoodsEndPostion) {
			hero.getEquipmentController().changeProperty(hero, equipment, TakeMethod.on);
		}
		return takeoutStrone;
	}

	public boolean condition(Hero hero, CharacterGoods equiment, byte[] baoshi) {
		if (equiment.getInEquipId() == null || equiment.getInEquipId().length() < 1) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 622));
			return false;
		}
		CharacterGoodController characterGoodController = hero.getCharacterGoodController();
		final int hasZhaichuNum = characterGoodController.getBagGoodsCountByModelID(GoodItemId.ZHAICHU);
		if (baoshi.length > hasZhaichuNum) {// 检查摘除道具数量个数
			hero.sendMsg(new CombineFailMsg50150(4));
			return false;
		}
		String[] split = equiment.getInEquipId().split(Symbol.FENHAO);
		if (split == null || split.length < baoshi.length) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 622));
			return false;
		}

		short findIdleGirdCount = hero.getCharacterGoodController().getBagGoodsContiner().findIdleGirdCount();
		if (findIdleGirdCount < baoshi.length) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 624));
			return false;
		}
		EquipmentPlayconfig equipmentPlayconfig = null;
		int bachuZhenqi = 0;
		int bachuCcopper = 0;
		for (int i = 0; i < baoshi.length; i++) {
			int gemsId =Integer.parseInt(split[ baoshi[i]]);
			equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(gemsId);
			if (equipmentPlayconfig == null) {
				return false;
			}
			bachuZhenqi += equipmentPlayconfig.getBachuZhenqi();
			bachuCcopper += equipmentPlayconfig.getBachuCopper();
		}
		if (!super.commonCondition(hero, bachuCcopper, bachuZhenqi)) {
			return false;
		}
		return true;
	}

}
