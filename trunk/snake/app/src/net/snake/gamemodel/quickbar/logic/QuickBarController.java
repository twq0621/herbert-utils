package net.snake.gamemodel.quickbar.logic;

import java.util.HashMap;
import java.util.Map;

import net.snake.consts.Position;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.persistence.GoodsDataEntryManager;
import net.snake.gamemodel.goods.response.GoodsUpdata10176;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.logic.CharacterSkill;
import net.snake.gamemodel.skill.persistence.CharacterSkillDataProvider2;
import net.snake.gamemodel.skill.response.SkillQuickBarChange10278;

public class QuickBarController {
	private Hero character;
	private Map<Integer, QuickBarGoods> quickbarMap = new HashMap<Integer, QuickBarGoods>();

	public QuickBarController(Hero character) {
		this.character = character;
	}

	public void destory() {
		if (quickbarMap != null) {
			quickbarMap.clear();
			quickbarMap = null;
		}
	}

	// 仅初始化使用
	public void initQuickBarGoods(QuickBarGoods quickbaritem) {
		quickbarMap.put(quickbaritem.getQuickbarindex(), quickbaritem);
	}

	// 只移除,不发更新
	public void removeQuickBarGoods(int quickbarindex) {
		if (quickbarindex == 0) {
			return;
		}
		QuickBarGoods oldQuickbarItem = quickbarMap.remove(quickbarindex);
		if (oldQuickbarItem != null) {
			oldQuickbarItem.setQuickbarindex(0);
		}
	}

	/**
	 * 得到空闲的快捷栏位置
	 * 
	 * @return
	 */
	public int getIdleQuickIndex() {

		for (int i = Position.QuickBarBeginPostion; i < Position.QuickBarBeginPostion; i++) {
			if (quickbarMap.get(i) == null) {
				return i;
			}
		}

		return 0;
	}

	public void removeQuickBar(int quickbarindex) {
		if (quickbarindex == 0) {
			return;
		}
		QuickBarGoods oldQuickbarItem = quickbarMap.remove(quickbarindex);
		if (oldQuickbarItem != null) {
			oldQuickbarItem.setQuickbarindex(0);
			if (oldQuickbarItem instanceof CharacterGoods) {
				updategoods(oldQuickbarItem);
			} else if (oldQuickbarItem instanceof CharacterSkill) {
				updateskill(oldQuickbarItem);
			}
		}
	}

	public QuickBarGoods getQuickBarItem(int index) {
		return quickbarMap.get(index);
	}

	// 设置快捷栏，需要发消息通知
	public void setQuickBarGoods(int quickindex, QuickBarGoods quickbaritem) {
		if (quickbaritem == null) {
			return;
		}
		QuickBarGoods goods = quickbarMap.remove(quickbaritem.getQuickbarindex());
		if (goods != null) {
			if (goods != quickbaritem) {
				goods.setQuickbarindex(0);
				updategoods(goods);
			}
		}
		// 当前位置是否有老的物品
		QuickBarGoods olditem = quickbarMap.get(quickindex);
		if (olditem == null) {
			quickbaritem.setQuickbarindex(quickindex);
			quickbarMap.put(quickindex, quickbaritem);
			updateQuickBarItem(quickbaritem);
		} else {
			if (olditem == quickbaritem) {
				// 不执行操作
			} else {
				// 更新老的
				quickbarMap.remove(olditem.getQuickbarindex());
				olditem.setQuickbarindex(0);
				updateQuickBarItem(olditem);

				quickbaritem.setQuickbarindex(quickindex);
				quickbarMap.put(quickindex, quickbaritem);
				updateQuickBarItem(quickbaritem);
			}
		}

	}

	private void updateQuickBarItem(QuickBarGoods quickbaritem) {
		if (quickbaritem instanceof CharacterGoods) {
			updategoods(quickbaritem);
		} else if (quickbaritem instanceof CharacterSkill) {
			updateskill(quickbaritem);
		}
	}

	private void updateskill(QuickBarGoods quickbaritem) {
		CharacterSkill cskill = (CharacterSkill) quickbaritem;
		// 数据库
		CharacterSkillDataProvider2.getInstance().asynUpdataCharacterSkill(character, cskill);
		// 消息
		character.sendMsg(new SkillQuickBarChange10278(cskill.getSkillId(), quickbaritem.getQuickbarindex()));
	}

	private void updategoods(QuickBarGoods quickbaritem) {
		if (quickbaritem instanceof CharacterGoods) {
			CharacterGoods goods = (CharacterGoods) quickbaritem;
			if (goods.getCount() > 0) {
				// 数据库
				GoodsDataEntryManager.getInstance().asynUpdataCharacterGoods(character, goods);
				// 消息
				character.sendMsg(new GoodsUpdata10176(goods));
			}
		}
	}
}
