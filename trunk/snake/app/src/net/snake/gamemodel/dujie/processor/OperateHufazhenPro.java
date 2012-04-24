package net.snake.gamemodel.dujie.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.dujie.bean.Tianshen;
import net.snake.gamemodel.dujie.logic.GsCalculator;
import net.snake.gamemodel.dujie.persistence.TianshenDataTbl;
import net.snake.gamemodel.dujie.response.ExistingHufas;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.gamemodel.instance.logic.InstancePlugin;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 操作护法和护法阵关系。
 * @author serv_dev<br>
 * Copyright (c) 2011 Kingnet
 */
@MsgCodeAnn(msgcode = 60325)
public class OperateHufazhenPro extends MsgProcessor implements IThreadProcessor{
	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		Hero hero=session.getCurrentCharacter(Hero.class);
		InstanceController controller=hero.getMyCharacterInstanceManager().getInstanceController();
		
		InstancePlugin plugin=controller.getPlugin();
		if (plugin == null) {
			return ;
		}
		if (controller.getAttribute("flushed")!=null) {
			return ;
		}
		
		int guardId = request.getInt();
		int type = request.getByte();
		int op = request.getByte();
		
		switch (type) {
		case GuardImg.Guards_Advanced:
			if (op==1) {
				List<GuardImg> imgs = plugin.advanceGuards();
				if (imgs==null) {
					return ;
				}
				int size = imgs.size();
				GuardImg add=null;
				for (int i = 0; i < size; i++) {
					GuardImg img = imgs.get(i);
					if (img.id == guardId) {
						add=img;
						break;
					}
				}
				if (add == null) {
					return ;
				}
				
				int fee = GsCalculator.calcHeroFee(add.gs, hero.getDujieCtrlor().currentTianjieNum());
//				Account account = hero.getAccount();
//				boolean flag = account.getAccountMonneyManager().consumYuanbao(hero, fee);
//				if (flag) {
					plugin.addGuard(type, add,fee);
//				}
			}else {
				plugin.delGuard(type, guardId);
			}
			break;
		case GuardImg.Guards_Normal:
			if (op==1) {
				List<GuardImg> imgs = plugin.normalGuards();
				if (imgs==null) {
					return ;
				}
				int size = imgs.size();
				GuardImg add=null;
				for (int i = 0; i < size; i++) {
					GuardImg img = imgs.get(i);
					if (img.id == guardId) {
						add=img;
						break;
					}
				}
				if (add == null) {
					return ;
				}
				
				int fee = GsCalculator.calcHeroFee(add.gs, hero.getDujieCtrlor().currentTianjieNum());
//				Account account = hero.getAccount();
//				boolean flag = account.getAccountMonneyManager().consumYuanbao(hero, fee);
//				if (flag) {
					plugin.addGuard(type, add,fee);
//				}
			}else {
				plugin.delGuard(type, guardId);
			}
			break;
		case GuardImg.Guards_Super:
			if (op == 1) {
				Tianshen shen = getTianshenById(guardId);
				if (shen == null) {
					break;
				}

//				int fee = shen.getFee();
//				Account account = hero.getAccount();
//				boolean flag = account.getAccountMonneyManager().consumYuanbao(hero, fee);
//				if (flag) {
					plugin.addGuard(type, shen);
//				}
			} else {
				plugin.delGuard(type, guardId);
			}
			break;
		}
		
		
		ExistingHufas existing=new ExistingHufas();
		existing.setData(1, plugin.getAllGuardImgs());
		hero.sendMsg(existing);
	}
	
	private Tianshen getTianshenById(int id){
		Tianshen shen =TianshenDataTbl.getInstance().getTianshen(id);
		return shen;
	}
	
}
