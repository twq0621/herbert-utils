package net.snake.gamemodel.goods.processor;

import java.util.Collection;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.map.response.goods.GoodsAutoPickUpMsg11166;
import net.snake.gamemodel.monster.logic.lostgoods.SceneDropGood;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 捡取物品 11163 任务捡取物品暂时根据怪物的仇恨度 来判断是否可捡取 。。
 * 
 * 
 */
@MsgCodeAnn(msgcode = 11163)
public class TakeGoodsProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int dropGoodId = request.getInt(); // 怪物id
		SceneDropGood cdg = isAblePickUp(dropGoodId, character);
		if (cdg != null) {
			if (cdg.getCg() != null && cdg.getCg().getGoodModel() != null && cdg.getCg().getGoodModel().isEquipment()) {
				character.getDayInCome().dealEquip(1);
			}
			if (cdg.getCg() != null && cdg.getCg().getGoodmodelId() != -1) {
				character.sendMsg(new GoodToBadEffectMsg11170(cdg));
			}
			character.getSceneRef().leaveScene(cdg);
			character.getCharacterOnHoorController().autoPickUpStart();
		}
	}

	/**
	 * 验证物品是否可捡
	 * 
	 * @param dropGoodId
	 * @param character
	 * @return 非空 可捡
	 */
	private SceneDropGood isAblePickUp(int dropGoodId, Hero character) {
		SceneDropGood cdg = null;
		if (dropGoodId == -1) {
			cdg = getEyeLatelyDropGood(character);
			if (cdg == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 882));
				return null;
			}
			character.sendMsg(new GoodsAutoPickUpMsg11166(cdg));
		} else {
			cdg = character.getSceneRef().getSceneDropGood(dropGoodId);
			if (cdg == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 883));
				return null;
			}
			int temp = DistanceFormula.getDistanceRound(character.getX(), character.getY(), cdg.getX(), cdg.getY());
			if (temp > 5) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 884));
				return null;
			}
			if (!cdg.isPickUpAndSendMsg(character)) {
				return null;
			}
			if (cdg.getCg().getGoodmodelId() == -1) {

				int coute = 0;
				if (Options.IsCrossServ) {
					character.getMyCharacterAcrossIncomeManager().addCopper(cdg.getCg().getCount());
					return cdg;
				} else {
					coute = CharacterPropertyManager.changeCopper(character, cdg.getCg().getCount(), CopperAction.ADD_PICKUP);

				}
				if (coute == 0) {
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 885));
					return null;
				}
			} else {
				if (!character.getCharacterGoodController().addGoodsToBag(cdg.getCg())) {
					if (Options.IsCrossServ) {
						character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1325));
						return null;
					}
					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 886));
					return null;
				}
			}
			return cdg;
		}

		return null;
	}

	/**
	 * 视野内获取距离玩家最近的可以拾取的包裹
	 * 
	 * @param character
	 * @return
	 */
	private SceneDropGood getEyeLatelyDropGood(Hero character) {
		Collection<SceneDropGood> collection = character.getEyeShotManager().getEyeShortObjs(SceneObj.SceneObjType_DropedGood);
		int juli = 1000000;
		SceneDropGood goods = null;
		for (SceneDropGood sdg : collection) {
			if (sdg.isPickUp(character)) {
				int temp = DistanceFormula.getDistanceRound(character.getX(), character.getY(), sdg.getX(), sdg.getY());
				if (temp < juli) {
					juli = temp;
					goods = sdg;
				}
			}
		}
		return goods;
	}

}
