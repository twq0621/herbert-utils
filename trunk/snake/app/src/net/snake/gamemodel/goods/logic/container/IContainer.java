package net.snake.gamemodel.goods.logic.container;

import java.util.Collection;

import net.snake.gamemodel.goods.bean.CharacterGoods;


public interface IContainer {
	/**
	 * 获得开始的位置索引
	 * @return
	 */
	public short getBeginPosition();
	/**
	 * 获得总容量
	 * @return
	 */
	public int getTotalCapacity();
	/**
	 * 根据物品位置获得物品
	 * @param postion
	 * @return
	 */
	public CharacterGoods getGoodsByPostion(short postion);
	/**
	 * 对于子容器,这是不是一个合法的位置
	 * @param postion
	 * @return
	 */
	public boolean isValidatePostion(short postion);
	/**
	 * 获得物品列表
	 * @return
	 */
	public Collection<CharacterGoods> getGoodsList();
	/**
	 * 检查该物品是否适合放到容器里
	 * @param goods
	 * @return
	 */
	public boolean checksuit(CharacterGoods goods);
	/**
	 * 容器间的相互移动
	 * @param fromposition
	 * @param target
	 * @param topostion
	 */
	public void moveTo(short fromposition, IContainer target, short topostion);
	/**
	 * 更新物品属性,只是数据库调用而已
	 * @param charactergoods
	 */
	public void updateGoods(CharacterGoods charactergoods);
	/**
	 * 当把物品移到
	 * @param fromGoods
	 * @param fromContainer
	 * @return
	 */
	public short getSuitPostionForGoods(CharacterGoods fromGoods, IContainer fromContainer);
	public void destory();


}
