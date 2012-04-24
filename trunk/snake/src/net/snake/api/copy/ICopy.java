package net.snake.api.copy;

import java.util.List;

import net.snake.api.IReuseable;
import net.snake.api.scene.IHero;



/**
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public interface ICopy extends IReuseable {
	
	public void buildCopy(Object copyConfig);
	/**
	 * 副本的唯一标识
	 * @return
	 */
	public Object getUnique();
	/**
	 * 副本编号，一个副本可能在运行时有多个实例
	 * @return
	 */
	public int getCopyNum() ;
	/**
	 * 副本刷新
	 * @return
	 */
	public void flush(long now);
	/**
	 * 进入副本
	 * @return
	 */
	public void enterCopy(IHero hero);
	/**
	 * 退出副本
	 * @return
	 */
	public List<IHero> closeCopy();
	
	public ICopy newAnother();
	
	public ICondition getCondition();
}
