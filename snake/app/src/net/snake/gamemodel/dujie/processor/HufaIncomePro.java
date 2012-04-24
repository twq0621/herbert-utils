package net.snake.gamemodel.dujie.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.dujie.bean.HeroDujieData;
import net.snake.gamemodel.dujie.response.HufaIncomeResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

@MsgCodeAnn(msgcode = 60311)
public class HufaIncomePro extends MsgProcessor implements IThreadProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero = session.getCurrentCharacter(Hero.class);
		int op=request.getByte();
		switch (op) {
		case 1:
			HeroDujieData dto =hero.getDujieCtrlor().getHeroDujieData();
			int income = dto.getIncome();
			if (hero.getGrade() * 5000 < income) {
				HufaIncomeResp incomeResp = new HufaIncomeResp(hero.getGrade() * 5000,hero.getGrade() * 5000);
				session.sendMsg(incomeResp);
			}else {
				HufaIncomeResp incomeResp = new HufaIncomeResp(income,hero.getGrade() * 5000);
				session.sendMsg(incomeResp);
			}
			
			break;
		case 2:
			hero.getDujieCtrlor().receiveGuardIncome();
			break;
		}

	}
}
