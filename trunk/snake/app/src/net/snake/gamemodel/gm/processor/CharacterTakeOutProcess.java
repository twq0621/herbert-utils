package net.snake.gamemodel.gm.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.gm.response.MsgToBackAdmin904;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 201封号 踢玩家下线
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 201)
public class CharacterTakeOutProcess extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {

		int id = request.getInt();
		CharacterManager cm = CharacterManager.getInstance();
		Hero character = (Hero) cm.getCache(id);
		if (character != null) {
			character.getPlayer().logout();
			session.sendMsg(new MsgToBackAdmin904(CommonUseNumber.byte1));
		} else {
			session.sendMsg(new MsgToBackAdmin904(CommonUseNumber.byte0));
		}
	}
}
