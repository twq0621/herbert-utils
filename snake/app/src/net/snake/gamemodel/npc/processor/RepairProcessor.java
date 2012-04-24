package net.snake.gamemodel.npc.processor;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.response.CharacterPropertyResponse10108;
import net.snake.gamemodel.equipment.response.repair.RepairResultResponse50224;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 50223, accessLimit = 100)
public class RepairProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte type = request.getByte();
		int npcid = request.getInt();
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcid);
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		int npcSceneId = npc.getSceneid();
		if (!npc.getFunctions().contains(Npc.NPC_REPAIR)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 911));
			return;
		}
		if (character.getScene() != npcSceneId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 912));
			return;
		}
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getX(), character.getX(), character.getX()) > 70) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 913));
			return;
		}
		int result = character.getEquipmentController().repairAll(type);
		if (result == 0) {
			character.sendMsg(new RepairResultResponse50224(1, 40029));
			character.sendMsg(new CharacterPropertyResponse10108(character));
		} else {
			character.sendMsg(new RepairResultResponse50224(0, result));
		}
	}

}
