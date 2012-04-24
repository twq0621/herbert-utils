package net.snake.gamemodel.equipment.logic;

import net.snake.commons.GenerateProbability;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GameConstant;
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
import net.snake.gamemodel.equipment.response.gem.ReplaceGemstoneMsg50112;
import net.snake.gamemodel.equipment.response.strengthen.StrengthenUpgradeMsg50104;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import org.apache.log4j.Logger;

/**
 * 装备镶嵌宝石
 * 
 * @author jack
 * 
 */
public class EquipmentGemStonePlay extends EquipmentPlay {
	private static final Logger logger = Logger.getLogger(EquipmentGemStonePlay.class);

	public void play(final Hero hero, final CharacterGoods equipment, final CharacterGoods gemstone, final int xyjNum) {
		hero.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				gemstone(hero, equipment, gemstone, xyjNum);
			}
		}, 5 * 1000);
		hero.sendMsg(new DelayLoadingMsg50128());
	}

	private void gemstone(Hero hero, CharacterGoods equipment, CharacterGoods gemstone, int xyjNum) {
		boolean isnew = false;
		byte type = 10;
		boolean tf = this.condition(hero, equipment, gemstone);
		if (!tf) {
			hero.sendMsg(new ReplaceGemstoneMsg50112(0, isnew, type));
			return;
		}
		EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(gemstone.getGoodModel().getId());

		int zhenqi = equipmentPlayconfig.getRongheZhenqi();
		CharacterPropertyManager.changeZhenqi(hero, -zhenqi);
		int copper = equipmentPlayconfig.getRongheCopper();
		CharacterPropertyManager.changeCopper(hero, -copper, CopperAction.CONSUME);

		CharacterGoodController characterGoodController = hero.getCharacterGoodController();

		if (!characterGoodController.combineDeleteCailiao(equipment, false, 1, GoodItemId.XINGYUNJING_ID, xyjNum)) {
			hero.sendMsg(new CombineFailMsg50150());
			logger.warn("bag manager calc with err");
			return;
		}

		int xinyunProbability = equipmentPlayconfig.getRongheProbability() + xyjNum * GameConstant.XingYunJinglv;
		if (!GenerateProbability.defaultIsGenerate(xinyunProbability)) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 978, gemstone.getGoodModel().getNameI18n(), xyjNum, zhenqi, copper));
			hero.sendMsg(new ReplaceGemstoneMsg50112(0, isnew, type));
			return;
		}
		if (!characterGoodController.getBagGoodsContiner().deleteSplitGoods(gemstone.getPosition(), 1)) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 609));
			hero.sendMsg(new CombineFailMsg50150());
			hero.sendMsg(new StrengthenUpgradeMsg50104(0));
			return;
		}
		equipment.setInEquipId(equipment.getInEquipId().concat(gemstone.getGoodmodelId() + "").concat(Symbol.FENHAO));
		if (gemstone.isBinding()) {
			equipment.setBind(gemstone.getBind());
		}
		if (isnew) {
			equipment.setBind(CommonUseNumber.byte1);
		}
		characterGoodController.updateCharacterGoods(equipment);
		hero.sendMsg(new ReplaceGemstoneMsg50112(1, isnew, type));
		hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 977, gemstone.getGoodModel().getNameI18n(), xyjNum, zhenqi, copper));
		hero.getMyCharacterAchieveCountManger().getEquipmentCount().baoshiRonghe(equipment);
		if (equipment.getPosition() >= Position.BodyGoodsBeginPostion && equipment.getPosition() <= Position.BodyGoodsEndPostion) {
			hero.getEquipmentController().changeProperty(hero, equipment, TakeMethod.on);
		}
	}

	public boolean condition(Hero hero, CharacterGoods equipment, CharacterGoods gemstone) {
		EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(equipment.getGoodModel().getId());
		if (equipment.getGoodModel().getPosition() >= Position.POSTION_HOURSE_ANJU && equipment.getGoodModel().getPosition() <= Position.POSTION_HOURSE_TITIE) {
			hero.sendMsg(new CombineFailMsg50150(15));
			return false;
		}
		int maxStoneNum = equipmentPlayconfig.getMaxStoneNum();
		String[] gemsArr = equipment.getInEquipId().split(Symbol.FENHAO);
		if (gemsArr.length + 1 > maxStoneNum) {
			hero.sendMsg(new CombineFailMsg50150(15));
			return false;
		}

		equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(gemstone.getGoodModel().getId());
		int copper = equipmentPlayconfig.getRongheCopper();
		int zhenqi = equipmentPlayconfig.getRongheZhenqi();
		if (!super.commonCondition(hero, copper, zhenqi)) {
			return false;
		}

		return true;
	}

}
