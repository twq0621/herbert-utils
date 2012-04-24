package net.snake.gamemodel.hero.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.CertificationUtil;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.auth.persistence.AuthConfigManager;
import net.snake.gamemodel.gm.response.BackadminToClientMsg40200;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 铜币、礼金
 * 
 */
@MsgCodeAnn(msgcode = 203)
public class CharacterMoneyUpdateProcess extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		int id = request.getInt();
		byte tag = request.getByte();
		int money = request.getInt();
		String md5web = request.getString();
		CharacterManager cm = CharacterManager.getInstance();
		Hero character = (Hero) cm.getCache(id);
		String md5key = AuthConfigManager.getInstance().getMd5Key();
		String md5 = CertificationUtil.md5(md5key);
		if (!md5web.equals(md5)) {
			character.sendMsg(new BackadminToClientMsg40200((byte) 2, character.getCopper()));

			session.sendMsg(new BackadminToClientMsg40200((byte) -1, 0));
			return;
		}
		if (character != null) {
			if (tag == 1) {
				CharacterPropertyManager.changeCopper(character, money, CopperAction.ADD_OTHER);
				session.sendMsg(new BackadminToClientMsg40200(tag, character.getCopper()));
			} else if (tag == 2) {
				CharacterPropertyManager.changeLijin(character, money);
				session.sendMsg(new BackadminToClientMsg40200(tag, character.getJiaozi()));
			}
		}
		session.sendMsg(new BackadminToClientMsg40200((byte) -1, 0));

	}

}
