package net.snake.gamemodel.trade.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.goods.response.GoodToBadEffectMsg11170;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.gamemodel.trade.response.NpcTradeBuyMsg10882;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 从npc处买物品协议 NpcID(short),物品模型id(int),数量(int) 10881
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10881, accessLimit = 200)
public class NpcTradeBuyProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int npcId = request.getInt();
		int goodId = request.getInt();
		int count = request.getInt();
		if (count < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 914));
			return;
		}
		if (count > 2000) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16505, 2000 + ""));
			return;
		}
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
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
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getX(), character.getX(), character.getX()) > ClientConfig.NPC_TRADE_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 913));
			return;
		}
		Goodmodel gm = GoodmodelManager.getInstance().get(goodId);
		if (gm == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 915));
			return;
		}
		if (!npc.getFunctionShop().contains(goodId + "")) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 916));
			return;
		}
		if (gm.getPrestigeLimit() == null) {
			gm.setPrestigeLimit(0);
		}
		if (character.getPrestige() < gm.getPrestigeLimit()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 917));
			return;
		}
		int monney = count * gm.getBuyPrice();
		int tax = 0;
		// if (character.getScene() == ClientConfig.Scene_Xianjing) {
		// tax = (int) (monney * FactionCityManager.getInstance().getTaxRate());
		// }
		if (monney < 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16506));
			return;
		}
		if (character.getCopper() < monney + tax) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 828));
			return;
		}
		CharacterGoods cg = CharacterGoods.createCharacterGoods(count, gm, 0, 0);
		boolean b = character.getCharacterGoodController().addGoodsToBag(cg);
		if (!b) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 918));
			return;
		}
		CharacterPropertyManager.changeCopper(character, -(monney + tax), CopperAction.CONSUME);
		FactionCityManager.getInstance().addTaxs(tax);

		List<CharacterGoods> list = new ArrayList<CharacterGoods>();
		list.add(cg);
		character.sendMsg(new GoodToBadEffectMsg11170(list, npcId));
		character.sendMsg(new NpcTradeBuyMsg10882(npcId, goodId, count, monney + tax));
	}
}
