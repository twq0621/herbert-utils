package net.snake.gamemodel.map.processor;

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

import org.apache.log4j.Logger;


/**
 * 确认付出代价进入某副本地图地图场景
 * 10053
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10053)
public class ExChangeInstanceSureProcessor extends CharacterMsgProcessor//一定不要实现IThreadProcessor
{

	private static Logger logger = Logger
			.getLogger(ExChangeInstanceSureProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("ExchangeMapProcessor 处理");
		}
		
		short x = request.getShort();// 得到传送的坐标
		short y = request.getShort();
		if (!ClientConfig.PERMIT_CHANGE_FRAME_RATE
				&& DistanceFormula.getDistance2(character.getX(),
						character.getY(), x, y) > ClientConfig.PERMIT_LOCATION_ERROR_RANGE) {
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			if (logger.isDebugEnabled()) {
				logger.debug("您当前的位置与服务器不同步,无法传送");
			}
			
			return;
		}
		Scene currentScene = character.getSceneRef();// 玩家当前所在的地图
		if (currentScene == null) {//TODO 走投无路还要向哪里走？
			logger.warn("currentScene == null");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(character.getViewName() + "当前所处场景" + currentScene
				+ "　请求切换场景,请求的传送点位置:(" + x + "," + y + ")");
		}
		

		final Teleport teleport = currentScene.getTransprot(x, y);
		if (teleport == null) {// 找不到传送点
			character.sendMsg(SimpleResponse.failReasonMsg(10020, 40023));
			return;
		}
		// 由副本截获隐藏传送点传送事件
		if (teleport.isHideTelePort()) {// 如果是没有开放的传送点
			// 交由副本判断
			character.getMyCharacterInstanceManager().instanceExChangeScene(
					teleport,true);
			return;
		}
		Scene enterScene = character.getVlineserver().getSceneManager()
				.getScene(teleport.getTargetSceneID());
		if (enterScene == null) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,905));
			return;
		}
		if (character.getGrade() < enterScene.getEnterLevelLimit()) {
character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,906));
			return;
		}
		// 查找要切换的场景
		ExchangeMapTrans.trans(teleport, character);
	}

}
