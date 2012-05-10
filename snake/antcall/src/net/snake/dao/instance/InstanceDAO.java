package net.snake.dao.instance;

import java.util.List;

import net.snake.dao.instance.Instance;
import net.snake.dao.instance.InstanceExample;

public interface InstanceDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	int countByExample(InstanceExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	int deleteByExample(InstanceExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	int deleteByPrimaryKey(Integer instanceModelId);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	void insert(Instance record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	void insertSelective(Instance record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	List selectByExample(InstanceExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	Instance selectByPrimaryKey(Integer instanceModelId);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	int updateByExampleSelective(Instance record, InstanceExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	int updateByExample(Instance record, InstanceExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	int updateByPrimaryKeySelective(Instance record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_instance
	 * @ibatorgenerated  Sat May 07 12:35:27 CST 2011
	 */
	int updateByPrimaryKey(Instance record);
}