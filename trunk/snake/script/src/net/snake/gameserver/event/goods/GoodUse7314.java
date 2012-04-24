package net.snake.gameserver.event.goods;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SRole;
import net.snake.script.util.DateUtile;
import net.snake.script.util.GenerateUtil;

public class GoodUse7314 implements IEventListener {
	public final int usegoodId = 7314;
    public final int openGoodId=1229;
    public final String lostTime = "2011-07-29 23:59:59";

	private void addGood(SGoods addgood, SApi api, SRole role, SGoods goods) {
		List<SGoods> add = new ArrayList<SGoods>();
		add.add(addgood);
		List<SGoods> remove = new ArrayList<SGoods>();
		SGoods temp = api.getNewSGoods(goods, 1);
		remove.add(temp);
		boolean b = api.removeAndAddGoods(role, remove, add);
		if (b) {
			//api.sendMsg(role, (byte) 6, GlobalLanguage.goodUse3401Str);
		}
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
		int grade=role.getDantianGrade();
		int count =0;
		if(grade<4){
			 count = GenerateUtil.randomIntValue(20, 70);
		}else{
			 count = GenerateUtil.randomIntValue(50, 200);
		}
		SGoods addgood = api.createCharacterGood(openGoodId, count, 0, 1,
				DateUtile.stringToDate(lostTime));
		addGood(addgood, api, role, good);
		
	}

}
