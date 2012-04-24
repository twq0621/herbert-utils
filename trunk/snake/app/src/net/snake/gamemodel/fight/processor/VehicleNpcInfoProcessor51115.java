package net.snake.gamemodel.fight.processor;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 请求火炮军团界面
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 51115)
public class VehicleNpcInfoProcessor51115 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int npcId = request.getInt();
		int vehicleId = request.getInt();
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (vehicleId < 500 || vehicleId > 600) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		int npcSceneId = npc.getSceneid();
		if (!npc.getFunctions().contains(vehicleId + "")) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14514));
			return;
		}
		if (character.getScene() != npcSceneId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14515));
			return;
		}
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getY(), character.getX(), character.getY()) > ClientConfig.VEHICLE_SEBDER_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14516));
			return;
		}

		character.getMyCharacterVehicleManager().requestGongVechicleInfo(vehicleId, npcId);
	}

}
