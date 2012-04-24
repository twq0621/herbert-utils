package net.snake.gamemodel.skill.processor.hiddenweapon;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.gm.response.BackadminToClientMsg40200;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 209)
public class CharacterHiddenWeaponUpdateProcess extends MsgProcessor implements INotNeedAuthProcessor, IThreadProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		int characterid = request.getInt();
		byte type = request.getByte();// 类型2是祝福1是锻造
		int addCount = request.getInt();
		CharacterManager cm = CharacterManager.getInstance();
		Hero character = (Hero) cm.getCache(characterid);
		if (character == null) {
			session.sendMsg(new BackadminToClientMsg40200((byte) -1, 0)); // -1人不在线
			return;
		}
		CharacterHiddenWeapon characterHiddenWeapon = character.getCharacterHiddenWeaponController().getCharacterHiddenWeapon();
		if (type == 1) {
			characterHiddenWeapon.setTupoCnt(characterHiddenWeapon.getTupoCnt() + addCount);
			session.sendMsg(new BackadminToClientMsg40200(CommonUseNumber.byte1, 0));
			return;
		}
		if (type == 2) {
			characterHiddenWeapon.setLuckValue(addCount);
			character.getCharacterHiddenWeaponController().sendInitMsg();
			session.sendMsg(new BackadminToClientMsg40200((byte) 2, 0));
			return;
		}
		session.sendMsg(new BackadminToClientMsg40200((byte) -2, 0));
	}

}
