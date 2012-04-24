package net.snake.gamemodel.equipment.logic;

import net.snake.commons.GenerateProbability;
import net.snake.commons.Language;
import net.snake.consts.CopperAction;
import net.snake.consts.Property;
import net.snake.consts.Symbol;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.DelayLoadingMsg50128;
import net.snake.gamemodel.equipment.response.born.BornExtraStrengthenMsg50172;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import org.apache.log4j.Logger;

public class EquipmentBornStrengthenPlay extends EquipmentPlay {
	private static final Logger logger = Logger.getLogger(EquipmentBornStrengthenPlay.class);

	public void play(final Hero hero, final CharacterGoods characterGoods) {
		hero.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				bornStrengthen(hero, characterGoods);
			}
		}, 5 * 1000);

		hero.sendMsg(new DelayLoadingMsg50128());
	}

	private void bornStrengthen(Hero hero, CharacterGoods characterGoods) {
		boolean tf = this.condition(hero, characterGoods);
		if (!tf) {
			hero.sendMsg(new BornExtraStrengthenMsg50172());
			return;
		}
		EquipmentPlayconfigManager epm = EquipmentPlayconfigManager.getInstance();
		int scheme = characterGoods.getGoodModel().getId();
		EquipmentPlayconfig ep = epm.getEPlayconfigByGoodsId(scheme);

		// 消耗真气以及物品
		int copper = ep.getBornStrengthenCopper();
		int zhenqi = ep.getBornStrengthenZhenqi();
		CharacterPropertyManager.changeCopper(hero, -copper, CopperAction.CONSUME);
		CharacterPropertyManager.changeZhenqi(hero, -zhenqi);
		int cailiaoId = ep.getBornStrengthenCailiaoModelid();
		int cailiaoNum = ep.getBornStrengthenCailiaoNum();
		if (!hero.getCharacterGoodController().deleteCailiao(cailiaoId, cailiaoNum)) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20120));
			logger.warn("bag manager calc with errs");
			hero.sendMsg(new BornExtraStrengthenMsg50172());
			return;
		}

		String msg = "";
		String baseDesc = characterGoods.getBaseDesc();
		StringBuilder afterBaseDesc = new StringBuilder();
		float pre = 1f;
		if (baseDesc != null && baseDesc.length() > 0) {
			String[] baseData = baseDesc.split(Symbol.FENHAO);
			boolean gp = true;
			for (int i = 0; i < baseData.length; i++) {
				String[] baseValueStr = baseData[i].split(Symbol.DOUHAO);
				int propType = Integer.parseInt(baseValueStr[0]);
				int bornLv = Integer.parseInt(baseValueStr[1]);
				Property property = Property.getProperty(propType);
				if (gp && property != Property.attackspeed && property != Property.movespeed && bornLv == 0) {
					gp = false;
					pre = 0.2f;
				}
				int newBornLV = 0;
				if (property == Property.attackspeed || property == Property.movespeed) {
					bornLv = 0;
					newBornLV = 0;
				} else {
					int val = characterGoods.getGoodModel().getBasePropertyValue(property);// 基础属性
					newBornLV = GenerateProbability.randomIntValue(1, val);
					if (gp == false) {
						newBornLV = (int) (newBornLV * pre) + 1;
					}
				}
				afterBaseDesc.append(baseValueStr[0]).append(Symbol.DOUHAO).append(newBornLV).append(Symbol.FENHAO);
				int born = newBornLV - bornLv;
				if (born != 0) {
					msg = msg + Property.getPropertyStr(property) + (born > 0 ? Language.BORN_ADD : Language.BORN_REDUCE);
					msg = msg + Math.abs(born);
				} else {
					msg = msg + Property.getPropertyStr(property) + Language.BORN_UNCHANGING;
				}
				if (i != baseData.length - 1) {
					msg = msg + ",";
				}
			}
		}
		characterGoods.setStrengthenAfterBaseDesc(afterBaseDesc.toString());
		hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20119, msg));
		hero.sendMsg(new BornExtraStrengthenMsg50172(characterGoods));
	}

	public boolean condition(Hero hero, CharacterGoods characterGoods) {
		if (characterGoods.getPingzhi() == 0) {// 白色装备
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20091));
			return false;
		}
		EquipmentPlayconfigManager epm = EquipmentPlayconfigManager.getInstance();
		int scheme = characterGoods.getGoodModel().getId();
		EquipmentPlayconfig ep = epm.getEPlayconfigByGoodsId(scheme);
		if (ep == null) {
			// logger.error("没有该物品：{} 的天生强化配置", new Object[] { characterGoods.getGoodmodelId() });
			hero.sendMsg(new CombineFailMsg50150(1));
			return false;
		}
		int copper = ep.getBornStrengthenCopper();
		int zhenqi = ep.getBornStrengthenZhenqi();
		if (!super.commonCondition(hero, copper, zhenqi)) {
			return false;
		}
		int cailiaoId = ep.getBornStrengthenCailiaoModelid();
		int cailiaoNum = ep.getBornStrengthenCailiaoNum();
		if (cailiaoNum > 0 && cailiaoNum > hero.getCharacterGoodController().getBagGoodsCountByModelID(cailiaoId)) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20092, GoodmodelManager.getInstance().get(cailiaoId).getNameI18n()));
			return false;
		}
		return true;
	}
}