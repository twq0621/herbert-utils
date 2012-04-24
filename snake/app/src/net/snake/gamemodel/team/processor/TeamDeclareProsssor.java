package net.snake.gamemodel.team.processor;

import java.sql.SQLException;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.badwords.persistence.BadWordsFilter;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.team.response.TeamdeclareMsg10196;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 队长修改队伍宣言 10195
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10195, accessLimit = 500)
public class TeamDeclareProsssor extends MsgProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		String declare = request.getString();
		int msg = checkTeamDeclareName(declare);
		if (msg != 0) {
			session.sendMsg(new TeamdeclareMsg10196(msg));
			return;
		}
		Hero character = session.getCurrentCharacter(Hero.class);
		character.getMyTeamManager().chageTeamDeclare(declare);

	}

	/**
	 * 输入名字是否合法
	 * 
	 * @param factionName
	 * @return
	 * @throws SQLException
	 */
	public int checkTeamDeclareName(String name) throws SQLException {
		int msg = 0;

		int nameLen = name.toCharArray().length;
		if (nameLen > 9) {
			msg = 10004;
			return msg;
		}
		// Pattern pattern = Pattern.compile(wordFilterStr);
		// boolean tf = pattern.matcher(name).matches();
		// if (!tf) {
		// msg = " 对不起，您的宣言名字只能由数字、字母、中文、下划线构成，请重新输入";
		// return msg;
		// }
		if (BadWordsFilter.getInstance().hashBadWords(name)) {
			msg = 10005;
			return msg;
		}

		return msg;
	}

}
