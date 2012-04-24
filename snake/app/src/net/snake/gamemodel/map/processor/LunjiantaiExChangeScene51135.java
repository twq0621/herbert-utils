package net.snake.gamemodel.map.processor;

import net.snake.GameServer;
import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.activities.persistence.LinshiActivityManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.LunjiantaiTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

/**
 * 进入帮战地图
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 51135, accessLimit = 500)
public class LunjiantaiExChangeScene51135 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int npcId = request.getInt();
		if (!LinshiActivityManager.getInstance().isTimeByLinshiActivityID(LunjiantaiTsMap.activityType)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1085));
			return;
		}
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		int npcSceneId = npc.getSceneid();
		if (!npc.getFunctions().contains(Npc.NPC_EXCHANGEFACTIONCT)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1160));
			return;
		}
		if (character.getGrade() <= 60) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14570, 60 + ""));
			return;
		}
		if (character.getScene() != npcSceneId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1162));
			return;
		}
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getY(), character.getX(), character.getY()) > ClientConfig.NPC_TRADE_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1163));
			return;
		}
		if (!LunjiantaiTsMap.isOpentTime) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1164, "18:00", "19:00"));
			return;
		}
		int lineid = LunjiantaiTsMap.LunjianTai_line;
		VLineServer line = GameServer.vlineServerManager.getVLineServerByLineID(lineid);
		int sceneId = LunjiantaiTsMap.lunjiantaiSceneId;
		Scene scene = line.getSceneManager().getScene(sceneId);
		ExchangeMapTrans.transWithChangeLine(character, line, scene, scene.getEnterX(), scene.getEnterY());
	}

}
