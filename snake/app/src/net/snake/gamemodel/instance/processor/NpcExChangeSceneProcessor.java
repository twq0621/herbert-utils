package net.snake.gamemodel.instance.processor;

import java.util.List;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.MapTeleport;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.WorldSceneUtil;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;

/**
 * 10701 npc副本请求
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10701)
public class NpcExChangeSceneProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int npcId = request.getInt();
		int sceneId = request.getInt();

		if (sceneId < 100) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 898));
			return;
		}
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		if (!npc.getFunctions().contains(Npc.NPC_EXCHANGEMAP)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 899));
			return;
		}
		int npcSceneId = npc.getSceneid();
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
		Scene enterScene = character.getVlineserver().getSceneManager().getScene(sceneId);
		if (enterScene != null) {
			List<MapTeleport> list = WorldSceneUtil.searchTargetScenePath(character.getSceneRef(), sceneId, character.getVlineserver().getSceneManager());
			MapTeleport teleport = list.get(list.size() - 1);
			short[] point = enterScene.getRandomPoint(teleport.getTargetX(), teleport.getTargetY(), 3);
			ExchangeMapTrans.trans(enterScene, point[0], point[1], character);
			return;
		}

		enterScene = character.getVlineserver().getSceneManager().getInstanceScene(sceneId);
		character.getMyCharacterInstanceManager().openInstance(enterScene, true);

	}
}
