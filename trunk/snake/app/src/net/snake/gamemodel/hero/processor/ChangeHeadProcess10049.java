package net.snake.gamemodel.hero.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.ChangeHeadMsg10050;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.response.TeamInfoMsg10208;
import net.snake.netio.message.RequestMsg;
import net.snake.serverenv.cache.CharacterCacheManager;
import net.snake.shell.Options;

/**
 * 处理消息号10111 玩家复活
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 10049, accessLimit = 100)
public class ChangeHeadProcess10049 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ)
			return;
		byte headimg = request.getByte();
		long now = System.currentTimeMillis();
		if (now - character.getChangeHeadTime() > 5000) {
			character.setChangeHeadTime(now);
		} else {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT, 20130));
			return;
		}
		character.setHeadimg(headimg);
		CharacterCacheManager.getInstance().updateCharacterCacheEntry(character);
		character.sendMsg(new ChangeHeadMsg10050(character.getHeadimg()));
		int wedderId = character.getMyFriendManager().getRoleWedingManager().getWedderId();
		Hero wedder = GameServer.vlineServerManager.getCharacterById(wedderId);
		if (wedder != null) {
			character.getMyFriendManager().sendMyInfoAndFuqi();
			wedder.getMyFriendManager().sendMyInfoAndFuqi();
		}
		Team team = character.getMyTeamManager().getMyTeam();
		if (team != null) {
			team.sendTeamMsg(new TeamInfoMsg10208(team), null);
		}
	}
}
