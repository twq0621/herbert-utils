package net.snake.gamemodel.goods.processor;

import java.util.ArrayList;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 兔子对话物品
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 52051)
public class RabbitGoodExChangeProcessor52051 extends CharacterMsgProcessor {
	private int[] RabbitIds = new int[] { 1104, 1105, 1106, 1107, 1108, 1109, 1110 };
	public int exChangeEquiteMaxCount = 50;
	public int exChangeGoodId = 3400;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte count = request.getByte();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		List<Short> list = new ArrayList<Short>();
		List<CharacterGoods> glist = new ArrayList<CharacterGoods>();
		if (count > 7) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 694));
			return;
		}
		for (int i = 0; i < count; i++) {
			Short position = request.getShort();
			boolean b = ckeckSamePositon(position, list);
			if (b) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 944));
				return;
			}
			list.add(position);
			CharacterGoods cg = character.getCharacterGoodController().getGoodsByPositon(position);
			if (cg == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 856));
				return;
			}
			if (cg.isInTrade()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 945));
				return;
			}
			if (!isRabbit(RabbitIds, cg.getGoodmodelId())) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 946));
				return;
			}
			glist.add(cg);
		}
		if (glist.size() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 947));
			return;
		}
		int fcount = 0;
		// List<CharacterGoods> deleteList = new ArrayList<CharacterGoods>();
		for (CharacterGoods cgs : glist) {
			if (cgs.getCount() > 10) {
				character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(cgs.getPosition(), cgs.getCount() - 10);
				cgs.setCount(10);
			}
			fcount = fcount + cgs.getCount();
			// deleteList.add(cgs);
			character.getCharacterGoodController().deleteCharacterGoods(cgs);
		}
		CharacterPropertyManager.changeLijin(character, fcount * 100);
		if (fcount >= 50) {
			CharacterGoods addCg = CharacterGoods.createCharacterGoods(1, exChangeGoodId, 0);
			addCg.setBind(CommonUseNumber.byte1);
			character.getCharacterGoodController().getBagGoodsContiner().addGoods(addCg);
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1036, addCg.getGoodModel().getNameI18n(), fcount * 100 + ""));
		} else {
			// "
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1037, fcount * 100 + ""));
		}
	}

	/**
	 * 返回true 是玫瑰花
	 * 
	 * @param meiguihuaid
	 * @param goodId
	 * @return
	 */
	private boolean isRabbit(int[] RabbitId, int goodId) {
		for (int id : RabbitId) {
			if (goodId == id) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查送花位置是否有重复
	 * 
	 * @param position
	 * @param list
	 * @return
	 */
	public boolean ckeckSamePositon(short position, List<Short> list) {
		for (Short p : list) {
			if (p == position) {
				return true;
			}
		}
		return false;
	}
}
