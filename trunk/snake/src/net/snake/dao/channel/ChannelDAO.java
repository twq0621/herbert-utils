package net.snake.dao.channel;

import java.util.List;

import net.snake.dao.channel.Channel;
import net.snake.dao.channel.ChannelExample;

public interface ChannelDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	int countByExample(ChannelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	int deleteByExample(ChannelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	void insert(Channel record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	void insertSelective(Channel record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	List selectByExample(ChannelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	Channel selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	int updateByExampleSelective(Channel record, ChannelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	int updateByExample(Channel record, ChannelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	int updateByPrimaryKeySelective(Channel record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_channel
	 */
	int updateByPrimaryKey(Channel record);
}
