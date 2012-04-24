package net.snake.gamemodel.goods.logic.action;
import java.util.List;

import net.snake.api.IUseItemListener;
import net.snake.gamemodel.hero.bean.Hero;

/**
 * 物品使用接口
 * @author serv_dev
 *
 */
public interface UseGoodAction {
	/**
	 * 物品使用  如果位置positon>1按位置处理物品 如果位置<1按物品模型id
	 */
     public boolean useGoodDoSomething(Hero character,int goodId,int positon,List<IUseItemListener> listeners);
}
