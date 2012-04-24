package net.snake.gamemodel.instance.processor;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;

/**
 * 10721请求NPC使用副本令进入副本
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10721)
public class NpcExChangeSceneTakeGoodProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int npcId = request.getInt();
		int sceneId = request.getInt();
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		if (sceneId < 100) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 898));
			return;
		}
		int npcSceneId = npc.getSceneid();
		if (!npc.getFunctions().contains(Npc.NPC_EXCHANGEMAP)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 899));
			return;
		}
		if (character.getScene() != npcSceneId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 900));
			return;
		}
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getX(), character.getX(), character.getX()) > ClientConfig.NPC_TRADE_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 901));
			return;
		}
		if (!npc.getFunctionTransfer().contains(sceneId + "")) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 902));
			return;
		}
		if (character.getMyTeamManager().isTeam()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 903));
			return;
		}
		Scene enterScene = character.getVlineserver().getSceneManager().getInstanceScene(sceneId);
		enterScene.getInstanceController();
		character.getMyCharacterInstanceManager().openInstance(enterScene, false);
	}
}
