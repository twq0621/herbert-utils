package net.snake.gamemodel.team.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 组队创建或邀请请求处理 处理10181
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10181, accessLimit = 300)
public class InviteTeamProcess extends CharacterMsgProcessor {

	@Override
	public void process(final Hero character, RequestMsg request) throws Exception {
		// 创建组队
		byte type = request.getByte();
		Hero hisCharacter = null;
		if (type == 0) {
			int hisId = request.getInt();
			hisCharacter = GameServer.vlineServerManager.getCharacterById(hisId);

		} else if (type == 1) {
			String name = request.getString();
			hisCharacter = GameServer.vlineServerManager.getCharacterByName(name);
		}

		if (hisCharacter == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 966));
			return;
		}
		if (hisCharacter.getVlineserver() != character.getVlineserver()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 968, hisCharacter.getVlineserver().getLineid() + ""));
			return;
		}
		final Hero his = hisCharacter;
		if (!his.getCharacterLimitConfig().isOtherAbleInviteTeam()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 967));
			return;
		}
		character.getVlineserver().addTaskInFrameUpdateThread(new Runnable() {
			@Override
			public void run() {
				character.getMyTeamManager().inviteOrApplyTeam(his);
			}
		});
	}

}
