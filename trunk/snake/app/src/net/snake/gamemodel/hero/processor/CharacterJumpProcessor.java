package net.snake.gamemodel.hero.processor;

import net.snake.ai.fight.response.SkillShowResponse;
import net.snake.ai.formula.DistanceFormula;
import net.snake.ai.move.IMoveController;
import net.snake.ai.move.MoveInfo;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.commons.VisibleObjectState;
import net.snake.gamemodel.hero.response.UpdateCurrentPositionMsg30000;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.netio.message.RequestMsg;

import org.apache.log4j.Logger;

/**
 * 处理人跳跃升的消息类 消息号 10063
 * 
 * @author serv_dev 一定不要实现IThreadProcessor
 */
@MsgCodeAnn(msgcode = 10063, accessLimit = 0)
public class CharacterJumpProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(CharacterJumpProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int characterState = character.getObjectState();
		if (characterState == VisibleObjectState.Shock || characterState == VisibleObjectState.Die || characterState == VisibleObjectState.Jitui) {
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			return;
		}
		
		if (character.getEffectController().isSpOver()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 910));
			return;
		}
		short[] lines = new short[4];
		lines[0] = request.getShort();
		lines[1] = request.getShort();
		lines[2] = request.getShort();
		lines[3] = request.getShort();

		// 是否为二级跳
		boolean is2jitiao = false;
		is2jitiao = request.getByte() == 1 ? true : false;
		if (character.getEffectController().isImmb()) {
			return;
		}

		if (character.getEffectController().isSleep()) {
			return;
		}
		// 障碍物判断
		if (!isPassLine(character.getSceneRef(), lines, is2jitiao)) {
			// 路径不合法
			return;
		}

		// 距离太远的判断
		CharacterSkill charskill = null;
		int skillid = request.getInt();
		charskill = character.getSkillManager().getCharacterSkillById(skillid);
		if(charskill==null){
			return;
		}
		int maxJumpLogicTiles = charskill.getSkill().getEffect().getScopeRadius();
		int jumpColdTime = charskill.getCoolingtime();

		//增加距离的公式：（1+技能等级*0.01）*初始距离
		maxJumpLogicTiles = (int) (1 + charskill.getGrade() * 0.01) * maxJumpLogicTiles + 1;
		if ((int)DistanceFormula.getDistance2(lines[0], lines[1], lines[2], lines[3]) > charskill.getSkill().getDistance()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 1071));
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			character.getMoveController().stopMove();
			return;
		}
		boolean tf = CharacterMoveProcessor.isNeedUpdatePosition(character, lines);
		if (tf) {
			character.sendMsg(new UpdateCurrentPositionMsg30000(character));
			character.getMoveController().stopMove();
			return;
		}
		character.getCatchHorseActionController().breakCatch();
		character.getCatchYoulongActionController().breakCatch();
		character.getMyCharacterVehicleManager().breakSenderShells();// 打断发炮
		character.getCatchXuanyuanJianActionController().breakCatch();
		IMoveController mc = character.getMoveController();
		if (mc.getMovingType() == MoveInfo.TYPE_STAND || mc.getMovingType() == MoveInfo.TYPE_WALK) {
			if (System.currentTimeMillis() - mc.getPreviousJumpEndTime() > jumpColdTime) {
				if (charskill.able2Use(character)) {
					character.onJump();
					if (character.getSceneRef().getInstanceModelId()!=16) {
						charskill.xiaohaoValue(character, charskill);
					}
					SkillShowResponse response = new SkillShowResponse(skillid, 0, character.getSpriteType(), character.getId(), CommonUseNumber.byte0, 0);
					character.sendMsg(response);
					mc.setJumpLines(lines, is2jitiao, request.getReceiveTime());
					character.setObjectState(VisibleObjectState.Walk);
					character.getFightController().autoAttack(false);
				} else {
					character.sendMsg(new UpdateCurrentPositionMsg30000(character));
					character.getMoveController().stopMove();
				}
			} else {
				logger.info("位置不同步，更新玩家的坐标------------------------------------3");
				character.sendMsg(new UpdateCurrentPositionMsg30000(character));
				character.getMoveController().stopMove();
			}
		} else if (mc.getMovingType() == MoveInfo.TYPE_JUMP2) {
			// 连跳状态下发跳跃请求，不做处理
			return;

		} 
	}

	/**
	 * 线路是否合法
	 * 
	 * @param scene
	 * @param movepath
	 * @param is2jitiao
	 * @return
	 */
	private boolean isPassLine(Scene scene, short[] movepath, boolean is2jitiao) {
		if (movepath.length < 4) {
			return false;
		}
		if (is2jitiao) {
			return scene.isPermitted(movepath[2], movepath[3]);
		} else {
			return scene.isPermitted(movepath[0], movepath[1]) && scene.isPermitted(movepath[2], movepath[3]);
		}

	}

}
