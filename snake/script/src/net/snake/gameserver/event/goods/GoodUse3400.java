package net.snake.gameserver.event.goods;

import java.util.ArrayList;

import java.util.List;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SRole;
import net.snake.resource.GlobalLanguage;

public class GoodUse3400 implements IEventListener {
	public final int usegoodId = 3400;

	/**
	 * 返回在0-maxcout之间产生的随机数时候小于num
	 * 
	 * @param num
	 * @return
	 */
	private boolean isGenerateToBoolean(int num, int maxcout) {
		double count = Math.random() * maxcout;
		if (count < num) {
			return true;
		}
		return false;
	}

	/**
	 * 随机产生min到max之间的整数值 包括min max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomIntValue(int min, int max) {
		return (int) (Math.random() * (double) (max - min + 1)) + min;
	}

	private void addEquite(List<Integer> list, SApi api, SRole role, SGoods goods) {
		int index = randomIntValue(0, list.size() - 1);
		int goodId = list.get(index);
		SGoods addgood = api.createCharacterGood(goodId, 1, goods.getBind());
		List<SGoods> add = new ArrayList<SGoods>();
		add.add(addgood);
		List<SGoods> remove = new ArrayList<SGoods>();
		SGoods temp = api.getNewSGoods(goods, 1);
		remove.add(temp);
		boolean b = api.removeAndAddGoods(role, remove, add);
		if (b) {
			api.sendMsg(role, (byte) 6, GlobalLanguage.goodUse3400Str);
		}
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_UseGoods;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		// SRole role, SGoods goods
		SRole role = (SRole) args[0];
		SGoods goods = (SGoods) args[1];
		if (usegoodId != goods.getGoodmodelId()) {
			return;
		}
		boolean zuoqi40 = isGenerateToBoolean(500, 10000);
		if (zuoqi40) {
			List<Integer> list = api.getHorseEquiteCollectionByGradeAndMenpai(40);
			addEquite(list, api, role, goods);
			return;
		}
		boolean zuoqi60 = isGenerateToBoolean(1000, 10000);
		if (zuoqi60) {
			List<Integer> list = api.getHorseEquiteCollectionByGradeAndMenpai(60);
			addEquite(list, api, role, goods);
			return;
		}
		boolean zuoqi80 = isGenerateToBoolean(500, 10000);
		if (zuoqi80) {
			List<Integer> list = api.getHorseEquiteCollectionByGradeAndMenpai(80);
			addEquite(list, api, role, goods);
			return;
		}
		int menpai = role.getPopsinger();
		boolean renwu40 = isGenerateToBoolean(1000, 10000);
		if (renwu40) {
			List<Integer> list = api.getEquiteCollectionByGradeAndMenpai(40, (byte) menpai);
			addEquite(list, api, role, goods);
			return;
		}
		boolean renwu60 = isGenerateToBoolean(2500, 10000);
		if (renwu60) {
			List<Integer> list = api.getEquiteCollectionByGradeAndMenpai(60, (byte) menpai);
			addEquite(list, api, role, goods);
			return;
		}
		boolean renwu80 = isGenerateToBoolean(10000, 10000);
		if (renwu80) {
			List<Integer> list = api.getEquiteCollectionByGradeAndMenpai(80, (byte) menpai);
			addEquite(list, api, role, goods);
			return;
		}
	}
}
