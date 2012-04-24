package net.snake.gamemodel.equipment.logic;

import java.util.ArrayList;

import net.snake.commons.GenerateProbability;
import net.snake.consts.CopperAction;
import net.snake.consts.GameConstant;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.bean.EquipmentPlayconfig;
import net.snake.gamemodel.equipment.persistence.EquipmentPlayconfigManager;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.equipment.response.DelayLoadingMsg50128;
import net.snake.gamemodel.equipment.response.gem.GemCombiningMsg50194;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

import org.apache.log4j.Logger;

/**
 * 宝石合成
 * 
 * @author jack
 * 
 */
public class GemcombiningPlay extends EquipmentPlay {
	private static final Logger logger = Logger.getLogger(GemcombiningPlay.class);

	public void play(final Hero hero, final CharacterGoods gems, final int gemcount, final int xingyunNum) {
		hero.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
			@Override
			public void run() {
				if (!condition(hero, gems, gemcount)) {
					hero.sendMsg(new CombineFailMsg50150());
					return;
				}
				final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(gems.getGoodModel().getId());
				final int nextGoodmodelId = equipmentPlayconfig.getNextGoodmodelId();
				final IBag bag = hero.getCharacterGoodController().getBagGoodsContiner();
				int innerOtherCount = 0;
				innerOtherCount = bag.getGoodsCountByModelID(gems.getGoodmodelId());
				int count = equipmentPlayconfig.getMergeSum() * gemcount;
				ArrayList<CharacterGoods> deleteGoods = new ArrayList<CharacterGoods>();
				ArrayList<CharacterGoods> addGoods = new ArrayList<CharacterGoods>();
				CharacterGoods good = new CharacterGoods();
				good.setGoodModel(gems.getGoodModel());
				good.setCount(count);
				deleteGoods.add(good);
				// 剩余数量
				final int surpluscount = innerOtherCount - count;
				int successcount = 0;
				// 失败数量
				int faildcount = 0;
				// 合成次数
				for (int i = 0; i < gemcount; i++) {
					int pro = equipmentPlayconfig.getCailiaoCombineProbability() + xingyunNum * GameConstant.XingYunJinglv;
					if (GenerateProbability.defaultIsGenerate(pro)) {
						successcount++;
					} else {
						faildcount++;
					}
				}
				if (!hero.getCharacterGoodController().deleteCailiao(GoodItemId.XINGYUNJING_ID, xingyunNum * gemcount)) {
					logger.warn("bag manager calc with err");
					return;
				}
				int zhenqi = gemcount * equipmentPlayconfig.getCailiaoCombineZhenqi();
				int copper = gemcount * equipmentPlayconfig.getCailiaoCombineCopper();
				CharacterPropertyManager.changeZhenqi(hero, -zhenqi);
				CharacterPropertyManager.changeCopper(hero, -copper, CopperAction.CONSUME);

				if (faildcount > 0) {
					CharacterGoods befor = CharacterGoods.createCharacterGoods(faildcount, equipmentPlayconfig.getGoodmodelId(), 0);
					addGoods.add(befor);
				}
				// 合成成功的宝石
				CharacterGoods succgoods = CharacterGoods.createCharacterGoods(successcount, nextGoodmodelId, 0);
				succgoods.setStroneAddproperty(gems.getStroneAddproperty());
				addGoods.add(succgoods);
				hero.getMyCharacterAchieveCountManger().getEquipmentCount().baoshiHechengEquipment(succgoods);
				if (hero.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForBindFirstRemoveAndAddGoods(deleteGoods, addGoods)) {
					addGoods.remove(succgoods);
					hero.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(deleteGoods, addGoods);
					succgoods = hero.getCharacterGoodController().getBagGoodsContiner().addAndReturnLast(succgoods);
					hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1042, count, successcount * equipmentPlayconfig.getMergeSum(), faildcount
							* equipmentPlayconfig.getMergeSum(), surpluscount, successcount, succgoods.getGoodModel().getNameI18n(), faildcount, gems.getGoodModel().getNameI18n(),
							surpluscount + faildcount, gems.getGoodModel().getNameI18n(), zhenqi, copper));
					hero.sendMsg(new GemCombiningMsg50194(nextGoodmodelId, successcount, gems.getGoodmodelId(), faildcount, gems.getGoodmodelId(), surpluscount, succgoods
							.getPosition()));
				} else {
					hero.sendMsg(new PrompMsg(684));
					hero.sendMsg(new CombineFailMsg50150());
				}
			}
		}, 5 * 1000);
		hero.sendMsg(new DelayLoadingMsg50128());
	}

	public boolean condition(Hero hero, CharacterGoods gems, int gemcount) {
		final EquipmentPlayconfig equipmentPlayconfig = EquipmentPlayconfigManager.getInstance().getEPlayconfigByGoodsId(gems.getGoodModel().getId());
		if (equipmentPlayconfig == null) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 614));
			return false;
		}
		if (equipmentPlayconfig.getNextGoodmodelId() == 0) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 615));
			return false;
		}
		final IBag bag = hero.getCharacterGoodController().getBagGoodsContiner();
		int count = bag.getGoodsCountByModelID(gems.getGoodmodelId());
		if (count < equipmentPlayconfig.getMergeSum()) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 983, equipmentPlayconfig.getMergeSum() + ""));
			return false;
		}

		int item = count / equipmentPlayconfig.getMergeSum();
		if (item < gemcount) {
			return false;
		}
		int zhenqi = equipmentPlayconfig.getCailiaoCombineZhenqi() * gemcount;
		int copper = equipmentPlayconfig.getCailiaoCombineCopper() * gemcount;
		// 合成所需真气
		if (!super.commonCondition(hero, copper, zhenqi)) {
			return false;
		}
		return true;
	}

}
