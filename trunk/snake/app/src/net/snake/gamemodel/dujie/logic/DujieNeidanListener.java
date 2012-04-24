package net.snake.gamemodel.dujie.logic;

import net.snake.api.IUseItemListener;
import net.snake.consts.EffectType;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.bean.HeroDujieData;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.response.CoolingTimeMsg50142;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.response.CharacterOneAttributeMsg49990;
import net.snake.gamemodel.instance.logic.InstanceController;

/**
 * 在渡劫副本内试用内丹增加真元的监听。
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class DujieNeidanListener implements IUseItemListener {
	public void onUseItem(net.snake.gamemodel.hero.bean.Hero hero, Goodmodel m) {
//		int inc =m.getDrugValue();
//		DujieCtrlor ctrlor=hero.getDujieCtrlor();
//		int proc = ctrlor.currentTianjieProc();
//		
//		Dujie jie = DujieDataTbl.getInstance().getDujie(ctrlor.currentTianjieNum());
//		int need = jie.getZhenyuan();
//		HeroDujieData record = ctrlor.getHeroDujieData();
//		record.setProcess(proc + inc);
//		
//		if ((proc + inc) >= need) {
//			InstanceController controller=hero.getMyCharacterInstanceManager().getInstanceController();
//			controller.getPlugin().onCompleted(controller, hero);
//		}
	}

	@Override
	public boolean beforeUseItem(Hero hero, int goodId, int position,Goodmodel gm) {
		//在这里代替了正常逻辑的计算
		
		//最大真元值
		int mxZhenyuan = (Integer)hero.getSceneRef().getInstanceController().getAttribute("mxZhenyuan");
		
		if (hero.getZhenqi() >= mxZhenyuan) {
			hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 806));
			return false;
		}
		//冷却中
		boolean b = hero.getCharacterGoodController().getCoolingTimeManager().isUseGoodsAndUpdate(gm);
		if (!b) {
				hero.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 820));
				return false;
		}

		//扣除数量
		if (hero.getCharacterGoodController().getBagGoodsContiner().deleteSplitGoods((short) position, 1)) {
			int value = gm.getDrugValue();
			
			if (value == 0){
				return false;
			}
			if (hero.getZhenqi() + value > mxZhenyuan) {
				value = mxZhenyuan - hero.getZhenqi();
			}

			if (value > 0) {
				hero.getDayInCome().dealZhenqi(value);
			}
			hero.setZhenqi(hero.getZhenqi() + value);
			if (value != 0) {
				hero.sendMsg(new CharacterOneAttributeMsg49990(hero, EffectType.zhenqi, hero.getZhenqi()));
			}
			hero.sendMsg(new CoolingTimeMsg50142(goodId));
			
			//当前进度
			DujieCtrlor ctrlor=hero.getDujieCtrlor();
			int proc = ctrlor.currentTianjieProc();
			
			Dujie jie = DujieDataTbl.getInstance().getDujie(ctrlor.currentTianjieNum());
			int need = jie.getZhenyuan();
			HeroDujieData record = ctrlor.getHeroDujieData();
			record.setProcess(proc + value);
			
			if ((proc + value) >= need) {
				InstanceController controller=hero.getMyCharacterInstanceManager().getInstanceController();
				controller.getPlugin().onCompleted(controller, hero);
				controller.setAttribute("sucComp", Long.valueOf(0));
			}
		}
		
		return false;
	}
}
