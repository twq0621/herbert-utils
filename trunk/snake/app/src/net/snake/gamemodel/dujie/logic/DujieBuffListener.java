package net.snake.gamemodel.dujie.logic;

import net.snake.api.IBuffListneer;
import net.snake.consts.EffectType;
import net.snake.gamemodel.dujie.bean.Dujie;
import net.snake.gamemodel.dujie.bean.HeroDujieData;
import net.snake.gamemodel.dujie.persistence.DujieDataTbl;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.instance.logic.InstanceController;

public class DujieBuffListener implements IBuffListneer {
	@Override
	public void onBuff(VisibleObject object, short property, int value) {
		if(property!=EffectType.zhenqi){
			return ;
		}
		if(object.getSceneRef().getInstanceModelId()!=9){
			return ;
		}
		
		Hero hero=(Hero)object;
		//当前进度
		DujieCtrlor ctrlor=hero.getDujieCtrlor();
		int proc = ctrlor.currentTianjieProc();
		
		Dujie jie = DujieDataTbl.getInstance().getDujie(ctrlor.currentTianjieNum());
		int need = jie.getZhenyuan();
		HeroDujieData record = ctrlor.getHeroDujieData();
		int proc2 = record.getProcess();
		if(proc2==need){
			return ;
		}
		if ((proc + value) >= need) {
			record.setProcess(need);
		}else {
			record.setProcess(proc + value);
		}
		
		
		if ((proc + value) >= need) {
			InstanceController controller=hero.getMyCharacterInstanceManager().getInstanceController();
			controller.getPlugin().onCompleted(controller, hero);
			controller.setAttribute("sucComp", Long.valueOf(0));
		}
	}
}
