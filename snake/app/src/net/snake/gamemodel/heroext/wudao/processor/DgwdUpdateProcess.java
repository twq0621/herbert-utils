package net.snake.gamemodel.heroext.wudao.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.gm.response.BackadminToClientMsg40200;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.heroext.wudao.bean.DGWD;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

@MsgCodeAnn(msgcode = 215)
public class DgwdUpdateProcess extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		int characterid = request.getInt();
		byte type = request.getByte();// 类型1的锻造2是祝福
		int addCount = request.getInt();
		CharacterManager cm = CharacterManager.getInstance();
		Hero character = (Hero) cm.getCache(characterid);
		if (character == null) {
			session.sendMsg(new BackadminToClientMsg40200((byte) -1, 0)); // -1人不在线
			return;
		}
		DGWD dgwd = character.getDgwdController().getDGWD(); // 是问道实例如果为空则是没有开启问道
		if (dgwd == null) {
			session.sendMsg(new BackadminToClientMsg40200((byte) -3, 0));
			return;
		}
		if (type == 1) {
			dgwd.setFaildcount(dgwd.getFaildcount() + addCount);
			session.sendMsg(new BackadminToClientMsg40200(CommonUseNumber.byte1, 0));
			return;
		} else if (type == 2) {
			dgwd.setLuck(addCount);
			session.sendMsg(new BackadminToClientMsg40200((byte) 2, 0));
			return;
		}
		session.sendMsg(new BackadminToClientMsg40200((byte) -2, 0));
	}
}
