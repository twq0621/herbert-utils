package net.snake.dao.achieve;

import java.util.List;

import net.snake.dao.achieve.Achieve;
import net.snake.dao.achieve.AchieveExample;
import net.snake.dao.goodsdc.Goodsdc;

public interface AchieveDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	int countByExample(AchieveExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	int deleteByExample(AchieveExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	void insert(Achieve record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	void insertSelective(Achieve record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	List selectByExample(AchieveExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	Achieve selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	int updateByExampleSelective(Achieve record, AchieveExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	int updateByExample(Achieve record, AchieveExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	int updateByPrimaryKeySelective(Achieve record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_achieve
	 */
	int updateByPrimaryKey(Achieve record);

	List<Object> getSelectTable(String Table);
}
