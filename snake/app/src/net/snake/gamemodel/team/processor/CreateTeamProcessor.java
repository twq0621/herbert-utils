package net.snake.gamemodel.team.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.logic.GameTeam;
import net.snake.gamemodel.team.logic.Team;
import net.snake.gamemodel.team.response.CreateTeamMsg10198;
import net.snake.gamemodel.team.response.TeamInfoMsg10208;
import net.snake.gamemodel.team.response.TeamMapInfoMsg10246;
import net.snake.netio.message.RequestMsg;

/**
 * 玩家创建队伍10197
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10197)
public class CreateTeamProcessor extends CharacterMsgProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		Team t = character.getMyTeamManager().getMyTeam();
		if (t != null && t.getStatu() == GameTeam.CREATED) {
			character.sendMsg(new CreateTeamMsg10198(1114));
			return;
		}
		if (t == null) {
			t = new GameTeam(character.getVlineserver().getTeamManager());
		}
		t.setStatu(GameTeam.CREATED);
		t.addCharacter(character);
		character.getMyTeamManager().setTeamLeader(CommonUseNumber.byte1);
		character.getVlineserver().getTeamManager().addTeam(t);
		character.sendMsg(new CreateTeamMsg10198());
		character.sendMsg(new TeamInfoMsg10208(t));
		if (character.getMyTeamManager().isTeam()) {
			character.sendMsg(new TeamMapInfoMsg10246(character.getMyTeamManager().getMyTeam(), character));
		}
	}

}
