package net.snake.gamemodel.hero.processor;

import net.snake.ai.fight.response.CharacterFuhuoDelayMsg20078;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.logic.CharacterResurrect;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

import org.apache.log4j.Logger;

/**
 * 处理消息号10111 玩家复活
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 20075, accessLimit = 1000)
public class CharacterResurrectProcess extends CharacterMsgProcessor {
	private static final Logger logger = Logger.getLogger(CharacterResurrectProcess.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		byte resurrectFlag = request.getByte();
		if (resurrectFlag == 1) {// 回城复活
			CharacterResurrect.huichengFuhuo(character);
		} else {// 原地复活
			character.getEyeShotManager().sendMsg(new CharacterFuhuoDelayMsg20078(character.getId()));
			final Hero _character = character;
			character.getFuhuotimer().shutdown();
			character.getUpdateObjManager().addFrameUpdateLaterTask(new Runnable() {
				@Override
				public void run() {
					try {
						CharacterResurrect.yuandiResurrectProcess(_character);
						_character.getEffectController().addUnWithstandBuff(3000);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}, (Options.Relive_Timeout + 1) * 1000);
		}
	}
}
