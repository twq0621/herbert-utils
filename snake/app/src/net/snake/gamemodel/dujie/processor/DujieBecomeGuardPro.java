package net.snake.gamemodel.dujie.processor;


import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.dujie.bean.Guards;
import net.snake.gamemodel.dujie.bean.HeroDujieData;
import net.snake.gamemodel.dujie.logic.DujieCtrlor;
import net.snake.gamemodel.dujie.response.DujieBecomeGuardResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 要不要称为护法候选人
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode=60305)
public class DujieBecomeGuardPro extends MsgProcessor {
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero=(Hero)session.getCurrentCharacter(Hero.class);
		if (hero==null) {
			return;
		}
		
		if (hero.getGrade() <20) {
			//TODO err msg
			DujieBecomeGuardResp resp = new DujieBecomeGuardResp(0);
			hero.sendMsg(resp);
			return ;
		}
		DujieCtrlor ctrlor = hero.getDujieCtrlor();
		int num =ctrlor.currentTianjieNum();
		if(num==1){
			//TODO err msg
			DujieBecomeGuardResp resp = new DujieBecomeGuardResp(0);
			hero.sendMsg(resp);
			return ;
		}
		int doit = request.getByte();
		HeroDujieData data = hero.getDujieCtrlor().getHeroDujieData();
		int currStat = data.getIsguard();
		if (currStat == doit) {
			DujieBecomeGuardResp resp = new DujieBecomeGuardResp(0);
			hero.sendMsg(resp);
			return ;
		}
		data.setIsguard(doit);
		DujieBecomeGuardResp resp = new DujieBecomeGuardResp(1);
		hero.sendMsg(resp);
		
		if (doit == 1) {
			Guards.becomeGuard(hero);
		}else {
			Guards.nolongerGuard(hero.getId());
		}
	}
}
