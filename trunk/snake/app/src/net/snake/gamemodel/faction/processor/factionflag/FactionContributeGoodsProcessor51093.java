package net.snake.gamemodel.faction.processor.factionflag;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 51093)
public class FactionContributeGoodsProcessor51093 extends CharacterMsgProcessor {
	private int[] lingapiIds = new int[] { GoodItemId.baihuilingId, GoodItemId.qinglonglingId, GoodItemId.zhuqulingId, GoodItemId.xuanwulingId, GoodItemId.BANGZHULING_ID };

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte count = request.getByte();
		if (!character.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 854));
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
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 855));
				return;
			}
			list.add(position);
			CharacterGoods cg = character.getCharacterGoodController().getGoodsByPositon(position);
			if (cg == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 856));
				return;
			}
			if (cg.isInTrade()) {
				//
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1024, cg.getGoodModel().getNameI18n()));
				return;
			}
			if (!isLingpai(lingapiIds, cg.getGoodmodelId())) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 857));
				return;
			}
			int goodId = request.getInt();
			int goodCount = request.getInt();
			if (goodId != cg.getGoodmodelId() || goodCount != cg.getCount()) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 858));
				return;
			}
			glist.add(cg);
		}
		if (glist.size() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 727));
			return;
		}
		character.getMyFactionManager().changeContributeGoods(glist);

	}

	/**
	 * 返回true 是玫瑰花
	 * 
	 * @param meiguihuaid
	 * @param goodId
	 * @return
	 */
	private boolean isLingpai(int[] lingpaiId, int goodId) {
		for (int id : lingpaiId) {
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
