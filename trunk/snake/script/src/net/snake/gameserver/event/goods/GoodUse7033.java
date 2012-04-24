package net.snake.gameserver.event.goods;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;
import net.snake.script.util.GenerateUtil;

public class GoodUse7033 implements IEventListener {
	public int usegoodId = 7033;
	public int[] getgoodIds = new int[] { 52001, 52002, 52003, 52004, 52005,
			53008, 53009, 53010, 53011, 53012, 53013, 53014, 53015, 53016,
			53017, 53018,53019, 53020, 53021, 53022 };// 这几个帮会武学暂时不开出

	private void addGood(SGoods addgood, SApi api, SRole role, SGoods goods) {
		List<SGoods> add = new ArrayList<SGoods>();
		add.add(addgood);
		List<SGoods> remove = new ArrayList<SGoods>();
		SGoods temp = api.getNewSGoods(goods, 1);
		remove.add(temp);
		boolean b = api.removeAndAddGoods(role, remove, add);
		if (b) {
			api.sendMsg(role, (byte) 6, GlobalLanguage.goodUse7033Str);
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
		SGoods goods =(SGoods)args[1];
		if (usegoodId != goods.getGoodmodelId()) {
			return;
		}
		int index = GenerateUtil.randomIntValue(0, getgoodIds.length - 1);
		byte bind = 0;
		if (index > getgoodIds.length - 5) {
			bind = 1;
		}
		SGoods addgood = api.createCharacterGood(getgoodIds[index], 1, 0, bind,
				null);
		addGood(addgood, api, role, goods);
	}
}
