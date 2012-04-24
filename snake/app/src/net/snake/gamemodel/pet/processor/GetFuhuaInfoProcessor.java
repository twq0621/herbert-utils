package net.snake.gamemodel.pet.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.response.GetFuhuaInfoResponse;
import net.snake.netio.message.RequestMsg;

/**
 * 获得灵宠孵化信息 60023
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 60023)
public class GetFuhuaInfoProcessor extends CharacterMsgProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		processFuhuaInfo(character);
	}

	public static void processFuhuaInfo(final Hero character) {
		// 0没有正在孵化中的灵宠
		int id = character.getFuhuaNeidanId();
		if (id == -1) {
			character.sendMsg(new GetFuhuaInfoResponse());
			return;
		}

		long nowtime = System.currentTimeMillis();
		long time = character.getFuhuaCdtime() - (nowtime - character.getFuhuaStarttime());
		int yutime = 0;
		if (time > 0) {
			yutime = (int) (time / 1000);
		}
		Goodmodel goodmodel = GoodmodelManager.getInstance().get(id);
		if (goodmodel == null) {
			return;
		}
		int coolTime = goodmodel.getCoolingtime();
		// 3孵化冷却中{剩余时间(int),总时间(int)}
		if (character.getIsfuhua() == 0) {
			character.sendMsg(new GetFuhuaInfoResponse(3, yutime, coolTime / 2));
			return;
		}
		// 2有孵化完成的灵宠{内丹ID(int),孵化成品ID(int)}

		if (yutime == 0) {
			character.sendMsg(new GetFuhuaInfoResponse(2, id, goodmodel.getChangeModelId()));
		} else {
			// 1有正在孵化的灵宠{内丹ID(int),孵化成品ID(int),剩余时间(int),总时间(int)}

			// yutime = yutime - (coolTime-cdtime);
			character.sendMsg(new GetFuhuaInfoResponse(id, goodmodel.getChangeModelId(), yutime, coolTime));
		}
	}
}
