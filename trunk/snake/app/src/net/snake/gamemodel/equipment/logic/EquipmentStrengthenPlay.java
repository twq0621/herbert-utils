package net.snake.gamemodel.equipment.logic;

import net.snake.commons.GenerateProbability;
import net.snake.commons.Language;
import net.snake.consts.CopperAction;
import net.snake.consts.GameConstant;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.bean.EquipmentStrengthen;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.persistence.EquipmentStrengthenManager;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.DelayLoadingMsg50128;
import net.snake.gamemodel.equipment.response.strengthen.StrengthenUpgradeMsg50104;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

import org.apache.log4j.Logger;

public class EquipmentStrengthenPlay extends EquipmentPlay {
	private static final Logger logger = Logger.getLogger(EquipmentStrengthenPlay.class);

	public void play(final Hero hero, final CharacterGoods characterGoods, final int xyjNum) {
		// 消耗的真气
		hero.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				strengthen(hero, characterGoods, xyjNum);
			}
		}, 5 * 1000);
		hero.sendMsg(new DelayLoadingMsg50128());
	}

	private void strengthen(final Hero hero, final CharacterGoods characterGoods, final int xyjNum) {
		boolean tf = this.condition(hero, characterGoods);
		if (!tf) {
			hero.sendMsg(new StrengthenUpgradeMsg50104(0));
			return;
		}
		CharacterGoodController characterGoodController = hero.getCharacterGoodController();
		EquipmentStrengthenManager equipmentStrengthenManager = EquipmentStrengthenManager.getInstance();
		int schema = characterGoods.getGoodModel().getId();
		final EquipmentStrengthen euipmentStrengthen = equipmentStrengthenManager.getEuipmentStrengthen(schema, characterGoods.getStrengthenCount());
		int needJingangshi = euipmentStrengthen.getJingangshi();
		int needJingangshiNum = euipmentStrengthen.getJingangshiNum();
		int probability = 0;
		if (characterGoods.getBornStrengthenCnt() >= euipmentStrengthen.getMinCount()) {
			probability = euipmentStrengthen.getProbability();
		}
		if (characterGoods.getBornStrengthenCnt() >= euipmentStrengthen.getMaxCount()) {
			probability = GenerateProbability.gailv;
		}
		probability = probability + xyjNum * GameConstant.XingYunJinglv;
		if (probability > GenerateProbability.gailv) {
			probability = GenerateProbability.gailv;
		}
		characterGoods.setBornStrengthenCnt(characterGoods.getBornStrengthenCnt() + 1);
		int tipMsgKey = 970;
		boolean isBindEquipment = false;
		// probability = GenerateProbability.gailv;//TODO
		if (GenerateProbability.defaultIsGenerate(probability)) {// 强化成功
			tipMsgKey = 969;
			isBindEquipment = true;
		}
		// 消耗真气以及物品
		int copper = euipmentStrengthen.getCopper();
		int zhenqi = euipmentStrengthen.getZhenqi();
		CharacterPropertyManager.changeCopper(hero, -copper, CopperAction.CONSUME);
		CharacterPropertyManager.changeZhenqi(hero, -zhenqi);
		if (!characterGoodController.combineDeleteCailiao(characterGoods, isBindEquipment, 2, GoodItemId.XINGYUNJING_ID, xyjNum, needJingangshi, needJingangshiNum)) {
			logger.warn("bag manager calc with err");
			hero.sendMsg(new StrengthenUpgradeMsg50104(0));
			return;
		}
		if (isBindEquipment) {
			characterGoods.setBornStrengthenCnt(0);
			int nextGrade = characterGoods.getStrengthenCount() + 1;
			int newjie = nextGrade % GameConstant.StrengthenJie;
			int newpin = nextGrade / GameConstant.StrengthenJie;
			newpin++;
			if (newjie == 0) {
				newpin = newpin - 1;
			}
			characterGoods.generateExtraPropByPinjieUpdate(newpin);
			characterGoods.setStrengthenCount(nextGrade);
			characterGoodController.updateCharacterGoods(characterGoods);
			hero.getMyCharacterAchieveCountManger().getEquipmentCount().qianghuaEquipment(characterGoods);
		}
		hero.sendMsg(new StrengthenUpgradeMsg50104(isBindEquipment ? 1 : 0));
		characterGoodController.updateCharacterGoods(characterGoods);
		if (xyjNum > 0) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, tipMsgKey, needJingangshiNum, Language.LUCKY_CRYSTAL + xyjNum + Language.ONE + ",", copper, zhenqi));
		} else {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, tipMsgKey, needJingangshiNum, "", copper, zhenqi));
		}
	}

	public boolean condition(Hero hero, CharacterGoods characterGoods) {
		int nextGrade = characterGoods.getStrengthenCount() + 1;
		if (nextGrade > GameConstant.StrengthenCountLimit) {
			// 装备已最高等级不能继续强化
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 600));
			return false;
		}
		// 消耗的真气
		EquipmentStrengthenManager equipmentStrengthenManager = EquipmentStrengthenManager.getInstance();
		int schema = characterGoods.getGoodModel().getId();
		final EquipmentStrengthen euipmentStrengthen = equipmentStrengthenManager.getEuipmentStrengthen(schema, characterGoods.getStrengthenCount());
		if (euipmentStrengthen == null) {
//			logger.error("没有该物品：{} 等级：{}的强化配置", new Object[] { characterGoods.getGoodmodelId(), nextGrade });
			hero.sendMsg(new CombineFailMsg50150(1));
			return false;
		}
		int newjie = nextGrade % GameConstant.StrengthenJie;
		int newpin = nextGrade / GameConstant.StrengthenJie;
		newpin++;
		if (newjie == 0) {
			newpin = newpin - 1;
		}
		int oldpinjie = characterGoods.getPin();
		if (newpin > oldpinjie) {
			final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(characterGoods.getGoodmodelId());
			if (equipmentPlayconfig != null) {
				int nextGoodModelId = equipmentPlayconfig.getNextGoodmodelId();
				Goodmodel goodmodel = GoodmodelManager.getInstance().get(nextGoodModelId);
				if(goodmodel.getLimitGrade()>hero.getGrade()){
					hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 60059));
					return false;
				}
			}
		}
		
		int copper = euipmentStrengthen.getCopper();
		int zhenqi = euipmentStrengthen.getZhenqi();
		if (!super.commonCondition(hero, copper, zhenqi)) {
			return false;
		}
		final CharacterGoodController characterGoodController = hero.getCharacterGoodController();
		// 金刚石数量
		final int needJingangshiNum = euipmentStrengthen.getJingangshiNum();
		if (needJingangshiNum > characterGoodController.getBagGoodsCountByModelID(euipmentStrengthen.getJingangshi())) {
			// 发送消息
			hero.sendMsg(new CombineFailMsg50150(5));
			return false;
		}
		return true;
	}

}
