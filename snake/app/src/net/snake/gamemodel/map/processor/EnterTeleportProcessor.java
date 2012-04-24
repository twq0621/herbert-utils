package net.snake.gamemodel.map.processor;

import org.apache.log4j.Logger;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.UpdateCurrentPositionMsg30000;
import net.snake.gamemodel.map.logic.ExchangeMapTrans;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.map.logic.Teleport;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;



/**
 * 切换地图场景 一定不要实现 IThreadProcessor
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10051, accessLimit = 2000)
public class EnterTeleportProcessor extends CharacterMsgProcessor {

	private static Logger logger = Logger.getLogger(EnterTeleportProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("ExchangeMapProcessor 处理");
		}

		short x = request.getShort();// 得到传送的坐标
		short y = request.getShort();
		if (!ClientConfig.PERMIT_CHANGE_FRAME_RATE && DistanceFormula.getDistance2(character.getX(), character.getY(), x, y) > ClientConfig.PERMIT_LOCATION_ERROR_RANGE) {
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			if (logger.isDebugEnabled()) {
				logger.debug("您当前的位置与服务器不同步,无法传送");
			}

			return;
		}
		Scene currentScene = character.getSceneRef();// 玩家当前所在的地图
		if (currentScene == null) {
			logger.warn("currentScene == null");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(character.getViewName() + "当前所处场景" + currentScene + "　请求切换场景,请求的传送点位置:(" + x + "," + y + ")");
		}

		final Teleport teleport = currentScene.getTransprot(x, y);
		if (teleport == null) {// 找不到传送点
			character.sendMsg(SimpleResponse.failReasonMsg(10020, 40023));
			return;
		}
		if (Options.IsCrossServ) {// 跨服服务器切回源服务器
			if (teleport.getTargetSceneID() == ClientConfig.Scene_Xianjing) {
				ExchangeMapTrans.transAcrossToYuanfuXiangyangcheng(character);
			}
			return;
		}
		// 由副本截获隐藏传送点传送事件
		if (teleport.isHideTelePort()) {// 如果是没有开放的传送点
			// 交由副本判断
			character.getMyCharacterInstanceManager().instanceExChangeScene(teleport, false);
			return;
		}
		Scene enterScene = character.getVlineserver().getSceneManager().getScene(teleport.getTargetSceneID());
		if (enterScene == null) {
			toInstance(teleport.getTargetSceneID(), character);
			// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,905));
			return;
		}
		if (character.getGrade() < enterScene.getEnterLevelLimit()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 906));
			return;
		}
		// 查找要切换的场景
		ExchangeMapTrans.trans(teleport, character);
	}

	private void toInstance(int sceneId, Hero hero) {
		Scene enterScene = hero.getVlineserver().getSceneManager().getInstanceScene(sceneId);
		if (enterScene == null) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 905));
			return;
		}
		hero.getMyCharacterInstanceManager().openInstance(enterScene, true);

	}

}
