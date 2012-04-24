package net.snake.serverenv.stall;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;


public interface OnlineStallManager {
	//一旦发现自己的摊位列表里有东西,应使用这个方法
	public void addOrUpdateStallList(Hero character);
	//一旦发现自己的摊位列表里为空时,应使用这个方法
	public void removeFromStallList(Hero character);
	
	public void checkCharacterStall(Hero character);
	//获得总的摊位列表
	public List<Hero> getStallList();
	
	public List<Hero> getStallListByCharacterName(String name);
	public List<Hero> getStallListByGoodsName(String name);
	public List<Hero> getStallListByHorseName(String name);
	/**
	 * 更新角色摆摊信息
	 * @param character
	 */
    public void updateStallInfo(Hero character);
}
