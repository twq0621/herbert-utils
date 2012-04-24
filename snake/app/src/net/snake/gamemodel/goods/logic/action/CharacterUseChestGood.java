package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.chest.bean.Chest;
import net.snake.gamemodel.chest.bean.ChestResources;
import net.snake.gamemodel.chest.persistence.ChestGroupManager;
import net.snake.gamemodel.chest.response.ChestResponse60102;
import net.snake.gamemodel.chest.response.ChestResponse60104;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 包厢打开处理
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseChestGood implements UseGoodAction {
	private Goodmodel gm;

	public CharacterUseChestGood(Goodmodel gm) {
		this.gm = gm;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {
		CharacterGoods cg = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}
		// 技能书判断
		if (gm.getId() == GoodItemId.mijilihe) {
			miJiLiHe(character);
			return false;
		}

		CharacterGoods cGoods = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(gm.getId().intValue());
		if (character.getGrade() < cGoods.getGoodModel().getLimitGrade().intValue()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1021, cGoods.getGoodModel().getLimitGrade().intValue() + ""));
			return false;
		}
		Short beibaoindex = character.getCharacterGoodController().getBagGoodsContiner().findFirstIdleGirdPosition();
		if (beibaoindex == 0) {
			character.sendMsg(new ChestResponse60104(CommonUseNumber.byte0, gm.getId()));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return false;
		}

		character.sendMsg(new ChestResponse60102(character, gm));
		return false;
	}

	/**
	 * 秘籍的摇奖
	 * 
	 * @param character
	 * @return
	 */
	public boolean miJiLiHe(Hero character) {
		boolean type = true;
		Short beibaoindex = character.getCharacterGoodController().getBagGoodsContiner().findFirstIdleGirdPosition();
		if (beibaoindex == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return false;
		}
		CharacterGoods cGoodsMiJi = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(gm.getId());
		if (cGoodsMiJi == null) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1017, GoodmodelManager.getInstance().get(gm.getId()).getNameI18n()));
			return false;
		}
		if (cGoodsMiJi.isInTrade()) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1018, cGoodsMiJi.getGoodModel().getNameI18n()));
			return false;
		}
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		// 扣秘籍
		character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cGoodsMiJi.getPosition(), 1);
		Chest miji = character.getMyChestManager().getRepeatChest_list_MiJi(cGoodsMiJi.getGoodmodelId(), gm.getId());

		ChestResources chestResources = ChestGroupManager.getInstance().getMapChestResources_string().get(miji.getChestResourcesId());
		CharacterGoods characterGoods = CharacterGoods.createCharacterGoods((int) chestResources.getQuantity(), chestResources.getGoodmodelId(), 0);
		if (gm.getBinding() == BindChangeType.BIND) {
			characterGoods.setBind(CommonUseNumber.byte1);
		} else if (chestResources.getBinding() == BindChangeType.BIND) {
			characterGoods.setBind(CommonUseNumber.byte1);
		} else {
			characterGoods.setBind(chestResources.getBinding());
		}
		characterGoodController.addGoodsToBag(characterGoods);
		if (chestResources.getFullServiceAnnouncement() == 1) {
			character.getMyChestManager().gongGaoMiJi(chestResources, cGoodsMiJi, characterGoods);
		}
		return type;
	}

}
