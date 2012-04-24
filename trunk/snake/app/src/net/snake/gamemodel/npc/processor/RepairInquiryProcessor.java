package net.snake.gamemodel.npc.processor;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.equipment.logic.EquipmentController;
import net.snake.gamemodel.equipment.response.repair.RepairCostsResponse50222;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


/**
 *询价消息处理
 *@author serv_dev
 */
@MsgCodeAnn(msgcode = 50221,accessLimit=100)
public class RepairInquiryProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		int npcId = request.getInt();
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (npc == null) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,897));
			return;
		}
		int npcSceneId = npc.getSceneid();
		if (!npc.getFunctions().contains(Npc.NPC_REPAIR)) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,911));
			return;
		}
		if (character.getScene() != npcSceneId) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,912));
			return;
		}
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getX(),
				character.getX(), character.getX()) > ClientConfig.NPC_TRADE_LANG) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,913));
			return;
		}
		
		EquipmentController equipmentController = character.getEquipmentController();
		int generial = equipmentController.getRepairPrice(1);//身上的装备普通维修价
		int special = equipmentController.getRepairPrice(2);//身上的装备特殊维修价
		character.sendMsg(new RepairCostsResponse50222(generial, special));
	}
	
	
}
