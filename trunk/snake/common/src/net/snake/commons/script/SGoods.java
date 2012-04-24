package net.snake.commons.script;
/**
 * 表示一个物品
 * @author serv_dev
 *
 */
public interface SGoods {
	public Integer getGoodmodelId();
	public Integer getCurrDurability();
	
	public Integer getMaxDurability();
	
	public int getRepairMoney(); 
	
	public int getGrade();
	public Byte getBind();
	public Integer getCount();
}
