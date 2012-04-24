/**
 * 
 */
package net.snake.gamemodel.line.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.gamemodel.map.logic.LunjiantaiTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

/**
 * 新改的角色切线 Copyright (c) 2011 by Kingnet
 * 
 */
@MsgCodeAnn(msgcode = 10111, accessLimit = 200)
public class ExChangeLineProcess10111 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int lineid = request.getByte();
		VLineServer vlineserver = GameServer.vlineServerManager.getVLineServerByLineID(lineid);
		// 分线相同，直接返回
		if (vlineserver == character.getVlineserver()) {
			return;
		}

		// 攻城战地图不许切线
		if (character.getSceneRef() instanceof GongchengTsMap) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14534));
			return;
		}

		// 论剑台地图不许切线
		if (character.getSceneRef() instanceof LunjiantaiTsMap) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1170));
			return;
		}

		Scene scene = character.getSceneRef();
		if (scene == null) {
			return;
		}

		// 如果角色在副本中
		if (scene.isInstanceScene()) {
			character.sendMsg(SimpleResponse.byteStatusMsg(10110, 0));// (10110,
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 829));
			return;
		}

		long now = System.currentTimeMillis();
		// 如果在战斗中
		if (character.inFighting(now)) {
			// 脱离战斗状态后方可换线，剩余：XX秒
			character.sendMsg(SimpleResponse.byteStatusMsg(10110, 0));// (10110,
			long shenyuTime = (ClientConfig.FightingTime - (now - character.getFightController().getAttackModelBeginTime())) / 1000;
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 830, shenyuTime == 0 ? 1 : shenyuTime));
		} else {
			VLineServer line = GameServer.vlineServerManager.getVLineServerByLineID(lineid);
			Scene enterScene = line.getSceneManager().getScene(scene.getId());
			ExchangeMapTrans.transWithChangeLine(character, line, enterScene, character.getX(), character.getY());
		}
	}

}
