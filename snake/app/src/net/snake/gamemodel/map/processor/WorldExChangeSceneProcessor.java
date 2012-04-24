package net.snake.gamemodel.map.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.MapTeleport;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.WorldSceneUtil;
import net.snake.gamemodel.map.response.WorldMsg10134;
import net.snake.gamemodel.onhoor.logic.CharacterOnHoorController.OnHoorState;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10133 世界地图场景切换
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10133)
public class WorldExChangeSceneProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int sceneId = request.getInt();
		int mapId = character.getSceneRef().getId();
		if (character.getSceneRef().getInstanceModelId() != 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 907));
			return;
		}
		if (mapId == sceneId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 792));
			return;
		}
		Scene enterScene = character.getVlineserver().getSceneManager().getScene(sceneId);
		if (character.getGrade() < enterScene.getEnterLevelLimit()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 906));
			return;
		}

		Account account = character.getAccount();
		List<MapTeleport> list = WorldSceneUtil.searchTargetScenePath(character.getSceneRef(), sceneId, character.getVlineserver().getSceneManager());
		if (list == null || list.size() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 908));
			return;
		}
		MapTeleport teleport = list.get(list.size() - 1);
		short[] point = enterScene.getRandomPoint(teleport.getTargetX(), teleport.getTargetY(), 7);
		if (character.getMyCharacterVipManger().isVipYueka()) {
			character.getMyFactionCityZhengDuoManager().dropYoulongToScene(null);
			character.getMycharacterAcrossZhengzuoManager().dropXuanyuanToScene(null);
			character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
			ExchangeMapTrans.trans(enterScene, point[0], point[1], character);
			character.sendMsg(new WorldMsg10134());
			return;
		}
		// int oldYuanbao = account.getYuanbao();
		if (account.getYuanbao() < list.size()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 909));
			return;
		}
		boolean b = account.getAccountMonneyManager().consumYuanbao(character, list.size());
		if (b) {
			// String logMsg = character.getSceneRef().getShowName() + "->" + enterScene.getShowName();
			character.getMyFactionCityZhengDuoManager().dropYoulongToScene(null);
			character.getMycharacterAcrossZhengzuoManager().dropXuanyuanToScene(null);
			character.getCharacterOnHoorController().setAfkState(OnHoorState.off);
			ExchangeMapTrans.trans(enterScene, point[0], point[1], character);
			// account.getAccountMonneyLogManager().logWolrdExchangeMapLog(character, logMsg, list.size(), oldYuanbao);
			character.sendMsg(new WorldMsg10134());
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 909));
		}

	}

}
