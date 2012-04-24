package net.snake.gamemodel.goods.logic.container;


public interface IGirdContainer extends IContainer {
	/**
	 * 整理对物品进行排序 
	 */
	public void cleanGoods();
	/**
	 * 设置容器总大小
	 * @param capacity
	 */
	public abstract void setTotalCapacity(int capacity);

	/**
	 * 返回指定物品的数量（不区分绑定）
	 * @param goodModelid
	 * @return
	 */
	public abstract int getGoodsCountByModelID(int goodModelid);
	
	/**
	 * 返回指定物品的数量，指定是否绑定
	 * @param goodModelid
	 * @param bind 是否是绑定的物品
	 * @return 绑定的指定物品或者是非绑定的指定物品
	 */
	public abstract int getGoodsCountByModelID(int goodModelid,boolean bind);

	/**
	 * 查看容器里还有几个空格子
	 * @return
	 */
	public abstract short findIdleGirdCount();


	/**
	 * 获得物品数量
	 * 
	 * @return
	 */
	public int getGoodsCount();

	/**
	 * 分解物品 产生消息通知
	 * 
	 * @param oldpostion
	 * @param splitcount
	 */
	public void splitGoods(short oldpostion, int splitcount);

	
	/**
	 * 容器内部移动物品，产生消息通知
	 * 
	 * @param oldposition
	 * @param newposition
	 */
	public void movePostion(short oldposition, short newposition);
	
	/**
	 * 查找第一个空闲网格数量
	 * @return
	 */
	public short findFirstIdleGirdPosition();
	/**
	 * 删除特定位置的物品
	 * @param postion
	 */
	public void deleteGoodsByPostion(short postion);
}
