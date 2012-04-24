package net.snake.gamemodel.goods.logic.action;

import java.util.List;

import net.snake.ai.fight.controller.EffectController;
import net.snake.ai.fight.handler.FightMsgSender;
import net.snake.api.IUseItemListener;
import net.snake.consts.EffectType;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.goods.logic.container.IBag;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;

/**
 * 使用buffer 产生物品 对角色产生临时buffer 效果
 * 
 * @author serv_dev
 * 
 */
public class CharacterUseBufferGood implements UseGoodAction {
	public SkillEffect se;

	public CharacterUseBufferGood(SkillEffect se) {
		this.se = se;
	}

	@Override
	public boolean useGoodDoSomething(Hero character, int goodId, int positon, List<IUseItemListener> listeners) {
		CharacterGoodController cgc = character.getCharacterGoodController();
		IBag bag = cgc.getBagGoodsContiner();

		CharacterGoods cg = bag.getGoodsByPostion((short) positon);
		if (cg.isInTrade()) {// 交易中
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 682));
			return false;
		}

		if (cg.getLastDate() != null) {// 过期了
			if (System.currentTimeMillis() > cg.getLastDate().getTime()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
				return false;
			}
		}

		if (cg.getGoodmodelId() == GoodItemId.duantijindan) {
			if (character.getLiantiController().getLiantiJingjieId() < 4) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 20141));
				return false;
			}
		}

		if (se.getBuffFlag() == 3) {
			boolean b = character.getMyCharacterVipManger().useVip(se);
			if (b) {
				bag.deleteSplitGoods((short) positon, 1);
				return true;
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 795));
				return false;
			}
		}
		if (se.getType() == EffectType.lianzhanyanshiDan) {
			EffectController effectController = character.getEffectController();
			EffectInfo continuumKillBuff = effectController.getContinuumKillBuff();
			if (continuumKillBuff == null) {
				character.sendMsg(new PrompMsg(797));
				return false;
			}
			if (continuumKillBuff.getDuration2() > 0) {
				character.sendMsg(new PrompMsg(796));
				return false;
			}
			continuumKillBuff.setDuration2(se.getDuration());
			continuumKillBuff.setDuration(continuumKillBuff.getDuration() + continuumKillBuff.getDuration2());
			FightMsgSender.broastSustainEffect(continuumKillBuff, continuumKillBuff.getDuration(), character);
			bag.deleteSplitGoods((short) positon, 1);
			return true;
		} else {
			EffectInfo effectInfo = new EffectInfo(se);
			effectInfo.setAffecter(character);
			if (character.getEffectController().addEffect(effectInfo)) {
				if (cg.getGoodmodelId() == GoodItemId.chaozhimenhuanputika) {
					character.getLiantiController().setPuticardUseCount(20);
				}
				bag.deleteSplitGoods((short) positon, 1);
			} else {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17020));
			}
		}

		return true;
	}

}
