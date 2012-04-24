/**
 * 
 */
package net.snake.gamemodel.hero.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.hero.persistence.RoleRandomNameManager;
import net.snake.gamemodel.hero.response.CharacterRandomNameMsg10010;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 产生随机名字
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 10009, accessLimit = 300)
public class RandomCharacterNameProcessor10009 extends MsgProcessor implements IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		// 判定门派，决定男女
		byte menpai = request.getByte();
		boolean isMale = false;
		if (menpai < 3) {
			isMale = true;
		}
		// 发送系统生产的名字
		String name = RoleRandomNameManager.getInstance().randomHefaRoleName(isMale, 6);
		if (name == null) {
			name = "";
		}
		session.sendMsg(new CharacterRandomNameMsg10010(name));
	}

}
