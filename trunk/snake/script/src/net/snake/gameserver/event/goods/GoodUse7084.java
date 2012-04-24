package net.snake.gameserver.event.goods;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SRole;
import net.snake.script.util.DateUtile;
import net.snake.script.util.GenerateUtil;

public class GoodUse7084 implements IEventListener {
	public final int usegoodId = 7084;// 
    public final int openGoodId=1302;
    public final String lostTime = "2011-05-20 23:59:59";

	private void addGood(SGoods addgood, SApi api, SRole role ){
		List<SGoods> add = new ArrayList<SGoods>();
		add.add(addgood);
		List<SGoods> remove = new ArrayList<SGoods>();
		api.removeAndAddGoods(role, remove, add);
	}
	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_UseGoods;
	}
	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SRole role, SGoods goods
		SRole role = (SRole)args[0];
		SGoods good =(SGoods)args[1];
		if(usegoodId!=good.getGoodmodelId()){
			return;
		}
		Date date = DateUtile.stringToDate(lostTime);
		if (date == null || date.getTime() < System.currentTimeMillis()) {
			return;
		}
		int jilv=1000;
		if(role.getCharacterHiddenWeaponGrade()>=4){
			jilv=10000;
		}
		boolean b =GenerateUtil.isGenerateToBoolean(jilv,10000);
		if(!b){
			return;
		}
		SGoods addgood = api.createCharacterGood(openGoodId, 1, 0, 1,
				date);
		addGood(addgood, api, role);
	}

}
