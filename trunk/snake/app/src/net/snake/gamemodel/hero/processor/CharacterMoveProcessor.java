package net.snake.gamemodel.hero.processor;

import net.snake.ai.astar.PathConvert;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.response.UpdateCurrentPositionMsg30000;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;

/**
 * 处理人走路的消息类 消息号 10061 一定不要实现 IThreadProcessor
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10061,accessLimit=0)
public class CharacterMoveProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(CharacterMoveProcessor.class);

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		if (character.isZeroHp()) {
			return;
		}
		final long currenttime = request.getReceiveTime();
		
		Scene scene = character.getSceneRef();
		if (scene == null) {
			logger.error("this hero is not in any scene, pre scene is "+character.worldPos[0]);
			return ;
		}
		final short[] movePath = PathConvert.readToPathArray(request);
		if (movePath ==null) {
			logger.error("move path is null ,map is "+ scene.getId());
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			return;
		}
		
		boolean b = isPassLine(scene, movePath);
		if (!b) {
			logger.error("move path can not be passed ,map is "+ scene.getId() );
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			return;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("move path is ："+ movePath);
		}
		//脱离战斗清除仇恨
		character.getEnmityManager().clearEnmityList();
		character.getCatchHorseActionController().breakCatch();
		character.getCatchYoulongActionController().breakCatch();// 拔剑进度条被打断接口
		character.getMyCharacterVehicleManager().breakSenderShells();// 打断发炮
		character.getCatchXuanyuanJianActionController().breakCatch();
		// character.getRideHorseActionController().breakRideAction();

		if (isNeedUpdatePosition(character, movePath)) {
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			character.getMoveController().stopMove();
			return;
		}

		if (character.getMoveController().isMoving()) {
			character.getMoveController().addWalkLines(movePath);
		} else {
			character.getMoveController().setWalkLines(movePath, currenttime);
		}
		character.setObjectState(VisibleObjectState.Walk);
		character.getFightController().autoAttack(false);
	}

	public static boolean isNeedUpdatePosition(Hero character, short[] movePath) {
		return !ClientConfig.PERMIT_CHANGE_FRAME_RATE && character.getGridMaxDistance(movePath[0], movePath[1]) > ClientConfig.PERMIT_LOCATION_ERROR_RANGE;

	}

	/**
	 * 线路是否合法
	 * 
	 * @param scene
	 * @param movepath
	 * @return
	 */
	private static boolean isPassLine(Scene scene, short[] movepath) {
//		if (movepath.length < 4) {
//			return false;
//		}
		short x = 0;
		short y = 0;
		for (int i = 0; i < movepath.length; i += 2) {
			x = movepath[i];
			y = movepath[i + 1];
			if (!scene.isPermitted(x, y)) {
				return false;
			}
		}
		return true;

	}

}
