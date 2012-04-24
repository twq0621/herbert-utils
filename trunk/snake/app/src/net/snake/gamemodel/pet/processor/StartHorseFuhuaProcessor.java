package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.GenerateProbability;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.consts.GameConstant;
import net.snake.consts.GoodItemId;
import net.snake.consts.GoodModelKind;
import net.snake.consts.HorseStateConsts;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CombineFailMsg50150;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.pet.bean.CharacterHorse;
import net.snake.gamemodel.pet.logic.HorseCreator;
import net.snake.gamemodel.pet.response.GetFuhuaInfoResponse;
import net.snake.netio.message.RequestMsg;

/**
 * 开始孵化灵宠
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60011)
public class StartHorseFuhuaProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero hero, RequestMsg request) throws Exception {
		short pos = request.getShort();
		int modelid = request.getInt();
		final byte xingyunNum = request.getByte();
		if (hero.getIsfuhua() == 0 || hero.getIsfuhua() == 1) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60047));
			return;
		}
		long nowtime = System.currentTimeMillis();
		if (xingyunNum > GameConstant.xingyunMaxNum) {
			return;
		}

		final CharacterGoodController characterGoodController = hero.getCharacterGoodController();
		int hasXingyunNum = characterGoodController.getBagGoodsCountByModelID(GoodItemId.XINGYUNJING_ID);
		if (xingyunNum > hasXingyunNum) {// 检查幸运晶数量个数
			hero.sendMsg(new CombineFailMsg50150(4));
			return;
		}
		CharacterGoods goods = hero.getCharacterGoodController().getGoodsByPositon(pos);
		if (goods == null || goods.getGoodmodelId() != modelid) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return;
		}
		if (goods.getGoodModel().getKind() != GoodModelKind.neidan.getKind() || goods.getGoodModel().getChangeModelId()==0) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60062));
			return;
		}
		if (goods.getIsHomemade() == 1 ) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60044));
			return;
		}
		// 没空间的情况下不能开始孵化
		if (hero.getCharacterHorseController().getBagHorseContainer().getLeaveSpace() < 1) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 893));
			return;
		}

		Goodmodel goodModel = goods.getGoodModel();
		if (hero.getZhenqi() < goodModel.getZhenqi()) {
			// 真气不足
			hero.sendMsg(new CombineFailMsg50150(3));
			return;
		}
		if (hero.getCopper() < goodModel.getCopper()) {
			// 铜币不足
			hero.sendMsg(new CombineFailMsg50150(14));
			return;
		}

		hero.setFuhuaStarttime(nowtime);
		hero.setFuhuaCdtime(goodModel.getCoolingtime() * 1000);
		hero.setFuhuaNeidanId(goodModel.getId());
		hero.setIsfuhua(CommonUseNumber.byte1);
		int probability = 0;
		int cnt = hero.getFuhuaCount(modelid);
		if (cnt >= goodModel.getMinCount()) {
			probability = goodModel.getProbability();
		}
		if (cnt >= goodModel.getMaxCount()) {
			probability = GenerateProbability.gailv;
		}
		probability = probability + xingyunNum * GameConstant.XingYunJinglv + 10000;
		if (probability > GenerateProbability.gailv) {
			probability = GenerateProbability.gailv;
		}

		if (GenerateProbability.defaultIsGenerate(probability)) {// 如果孵化成功
			CharacterHorse characterHorse = HorseCreator.createCharacterHorse(hero, goodModel.getChangeModelId(), HorseStateConsts.FUHUA);
			hero.setFuhuaCharacterHorse(characterHorse);
			hero.setFuhuaCount(modelid, 0);
		} else {
			hero.setFuhuaCount(modelid, cnt + 1);
		}
		CharacterPropertyManager.changeCopper(hero, -goodModel.getCopper(), CopperAction.CONSUME);
		CharacterPropertyManager.changeZhenqi(hero, -goodModel.getZhenqi());
		hero.getCharacterGoodController().deleteCailiaoByPos(goods, 1);
		hero.getCharacterGoodController().deleteCailiao(GoodItemId.XINGYUNJING_ID, xingyunNum);
		hero.sendMsg(new GetFuhuaInfoResponse(modelid, goodModel.getChangeModelId(), goodModel.getCoolingtime(), goodModel.getCoolingtime()));
	}

}
