package net.snake.gamemodel.skill.processor.hiddenweapon;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.gamemodel.skill.response.hiddenweapon.QueryOtherCharacterHiddenWeaponInfoMsg52018;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;


/**
 * 查看其他玩家暗器请求
 * @author serv_dev
 *
 */
@MsgCodeAnn(msgcode = 52017,accessLimit=500)
public class QueryOtherCharacterHiddenWeaponProc52017 extends
		CharacterMsgProcessor  implements IThreadProcessor{

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		int othercharacterid = request.getInt();
		Hero othercharacter = CharacterManager.getInstance().getCache(othercharacterid);
		if (othercharacter == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,887));
			return;
		}
		character.sendMsg(new QueryOtherCharacterHiddenWeaponInfoMsg52018(othercharacter.getCharacterHiddenWeaponController().getCharacterHiddenWeapon(),othercharacter));
	}

}
