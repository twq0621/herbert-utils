package net.snake.gamemodel.dujie.processor;


import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.dujie.bean.Guards;
import net.snake.gamemodel.dujie.bean.Hufazhen;
import net.snake.gamemodel.dujie.logic.HufazhenBuff;
import net.snake.gamemodel.dujie.response.FightingHufazhenResp;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.instance.logic.InstancePlugin;

/**
 * 确定要开始享受雷劈。
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode=60307)
public class DujieStartSkyfire extends MsgProcessor implements IThreadProcessor{

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		
		Hero hero=session.getCurrentCharacter(Hero.class);
		InstanceController controller = hero.getMyCharacterInstanceManager().getInstanceController();
		if (controller == null) {
			return ;
		}
		Object flushObject = controller.getAttribute("flushed");
		if (flushObject != null) {//准备完毕，开始天劫
			return ;
		}
		
		InstancePlugin plugin = controller.getPlugin();
		GuardImg[] imgs = plugin.getAllGuardImgs();
		//扣钱
		int goldsum =0;
		int coppersum =0;
		for (int i = 0; i < imgs.length; i++) {
			if (imgs[i].id == -1) {
				continue;
			}
			if (imgs[i].type == GuardImg.Guards_Super) {
				goldsum += imgs[i].fee;
			}else if (imgs[i].type == GuardImg.Guards_Normal){
				coppersum += imgs[i].fee;
			}else {
				coppersum += imgs[i].fee;
			}
		}
		if (coppersum > hero.getCopper()) {
			for (int i = 0; i < imgs.length; i++) {
				imgs[i].id=-1;
			}
			return ;
		}
		if (goldsum > hero.getAccount().getYuanbao()) {
			for (int i = 0; i < imgs.length; i++) {
				imgs[i].id=-1;
			}
			return;
		}
		hero.getAccount().getAccountMonneyManager().consumYuanbao(hero, goldsum);
		CharacterPropertyManager.changeCopper(hero, -coppersum, CopperAction.CONSUME);
		Guards.increaseGuardIncome(imgs);
		//end 扣钱
		
		
		
		controller.getPlugin().flushMonster(controller);
		controller.setAttribute("flushed", Boolean.TRUE);
		
		
		Hufazhen zhen =plugin.getHufazhen();
		
		
		//告诉客户端此次的护法和阵法详情
		FightingHufazhenResp fighting = new FightingHufazhenResp();
		int zhenfaId = 0;
		if (zhen !=null) {
			zhenfaId=zhen.getId();
		}
		fighting.setData(plugin.getAllGuardImgs(),zhenfaId);
		hero.sendMsg(fighting);
		
		
		//阵法加成buff
		if (zhen ==null) {
			return ;
		}
		HufazhenBuff buff=new HufazhenBuff(zhen);
		hero.getPropertyAdditionController().addChangeListener(buff);
		controller.setAttribute("zhenBuff", buff);

		
		int shenTong = zhen.getShentong_buff();
		switch (shenTong) {
		case 51060003:
			controller.setAttribute("shentong", 1);
			break;
		case 51060004:
			hero.getShenTongSkillManager().speedUpSkillCool();
			break;
		case 51060005:
			controller.setAttribute("shentong", 3);
			break;
		}
		
	}

}
