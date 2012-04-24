package net.snake.gamemodel.trade.processor;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.trade.response.NpcTradeSaleMsg10884;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10883 卖东西给NPC NpcID(short),物品模板id(int),物品数量(int),玩家包裹索引(short)
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10883, accessLimit = 200)
public class NpcTradeSaleProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int npcId = request.getInt();
		int goodId = request.getInt();
		int count = request.getInt();
		short position = request.getShort();
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (count < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 919));
			return;
		}
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		int npcSceneId = npc.getSceneid();
		if (!npc.getFunctions().contains(Npc.NPC_SHOP)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 911));
			return;
		}
		if (character.getScene() != npcSceneId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 912));
			return;
		}
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getY(), character.getX(), character.getY()) > ClientConfig.NPC_TRADE_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 913));
			return;
		}

		CharacterGoods characterGood = character.getCharacterGoodController().getBagGoodsContiner().getGoodsByPostion(position);
		if (characterGood == null || characterGood.getGoodmodelId() != goodId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 920));
			return;
		}
		if (characterGood.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 920));
			return;
		}
		if (characterGood.getGoodModel().getIsSale() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1155));
			return;
		}
		if (characterGood.getCount() < count) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 921));
			return;
		}
		if (characterGood.getLastDate() != null && characterGood.getLastDate().getTime() < System.currentTimeMillis()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1154));
			return;
		}
		Goodmodel gm = characterGood.getGoodModel();
		if (gm.isGiftBag()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 922));
			return;
		}
		int salePrice = gm.getSalePrice();
		if (characterGood.getBind() == 1) {
			salePrice = salePrice / 2;
		}
		int monney = count * salePrice;
		if (character.getCopper() + monney > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 923));
			return;
		}
		boolean b = character.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods(position, count);
		if (!b) {
			return;
		}
		CharacterPropertyManager.changeCopper(character, monney, CopperAction.ADD_NPCSHELL);
		character.sendMsg(new NpcTradeSaleMsg10884(npcId, goodId, count, monney));
	}

}
