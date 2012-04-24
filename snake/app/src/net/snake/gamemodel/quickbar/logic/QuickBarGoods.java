package net.snake.gamemodel.quickbar.logic;

/***
 * 快捷栏物品，可找到对应的技能或者道具
 * @author serv_dev
 *
 */
public interface QuickBarGoods {
	/**
	 * 获得快捷栏位置索引
	 * @return
	 */
	public Integer getQuickbarindex();
	/**
	 * 设置快捷栏的位置索引，只是设置数据
	 * @param quickbarindex
	 */
	public void setQuickbarindex(Integer quickbarindex) ;
	
}
