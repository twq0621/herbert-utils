package net.snake.gamemodel.heroext.dantian.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.gm.response.BackadminToClientMsg40200;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.heroext.dantian.bean.DanTian;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

@MsgCodeAnn(msgcode = 213)
public class DanTianUpdateProcess extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

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

		DanTian danTian = character.getDanTianController().getDanTian(); // 是丹田实例如果为空则是没有弓箭
		if (danTian == null) {
			session.sendMsg(new BackadminToClientMsg40200((byte) -3, 0));
			return;
		}
		if (type == 1) {
			danTian.setFaildcount(danTian.getFaildcount() + addCount);
			session.sendMsg(new BackadminToClientMsg40200(CommonUseNumber.byte1, 0));
			return;
		} else if (type == 2) {
			danTian.setLuck(addCount);
			session.sendMsg(new BackadminToClientMsg40200((byte) 2, 0));
			return;
		}
		session.sendMsg(new BackadminToClientMsg40200((byte) -2, 0));
	}
}
