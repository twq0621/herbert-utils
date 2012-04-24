package net.snake.gameserver.event.goods;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;
import net.snake.script.util.GenerateUtil;

public class GoodUse3401 implements IEventListener {
	public final int usegoodId = 3401;
    public final int openGoodId=1216;

	private void addGood(SGoods addgood, SApi api, SRole role, SGoods goods) {
		List<SGoods> add = new ArrayList<SGoods>();
		add.add(addgood);
		List<SGoods> remove = new ArrayList<SGoods>();
		SGoods temp = api.getNewSGoods(goods, 1);
		remove.add(temp);
		boolean b = api.removeAndAddGoods(role, remove, add);
		if (b) {
			api.sendMsg(role, (byte) 6, GlobalLanguage.goodUse3401Str);
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
		int count = GenerateUtil.randomIntValue(1, 3);
		SGoods addgood = api.createCharacterGood(openGoodId, count, 0, 1,
				null);
		addGood(addgood, api, role, good);
	}

}
