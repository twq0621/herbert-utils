package net.snake.gamemodel.monster.logic.lostgoods;

import net.snake.commons.GenerateProbability;
import net.snake.consts.BindChangeType;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.bean.MonsterModel;

/**
 * 怪物死亡触发任务物品掉落
 * 
 * @author serv_dev
 * 
 */
public class LostTaskGoods extends Lostgoods {

	private Goodmodel gm;

	public LostTaskGoods(Goodmodel gm) {
		this.gm = gm;
	}

	@Override
	public CharacterGoods dropLostGoods(Hero character, MonsterModel monster, float dropJiacheng) {
		if (character == null) {
			return null;
		}
		if (jiacheng < 1) {
			jiacheng = 1;
		}
		if (dropJiacheng < 50) {
			dropJiacheng = 10000;
		}
		boolean isDrop = GenerateProbability.isGenerateToBoolean(getProbability() * jiacheng * dropJiacheng / 10000, probabilityMax);
		if (getMaxNum() > 1) {
			int nowNum = getNowNum();
			nowNum++;
			if (getMaxNum() == nowNum) {
				isDrop = true;
				nowNum = 0;
			}
			setNowNum(nowNum);
		}
		if (isDrop) {
			if (gm == null) {
				return null;
			}
			if (character.getTaskController().isNeedGood(gm.getId())) {
				CharacterGoods cg = CharacterGoods.createCharacterGoods(1, gm, monster.getLoopCount(), 0);
				if (gm.getBinding() == BindChangeType.BIND) {
					cg.setBind(CommonUseNumber.byte1);
				} else {
					cg.setBind(CommonUseNumber.byte0);
				}
				if (this.getBind() == 1) {
					cg.setBind(CommonUseNumber.byte1);
				}
				boolean b = character.getCharacterGoodController().addGoodsToBag(cg);
				if (!b) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 758));
				}
			}
		}
		return null;

	}

}
