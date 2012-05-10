package net.snake.dao.horsemodel;

import java.util.List;

import net.snake.dao.horsemodel.HorseModel;
import net.snake.dao.horsemodel.HorseModelExample;

public interface HorseModelDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	int countByExample(HorseModelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	int deleteByExample(HorseModelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	void insert(HorseModel record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	void insertSelective(HorseModel record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	List selectByExample(HorseModelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	HorseModel selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	int updateByExampleSelective(HorseModel record, HorseModelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	int updateByExample(HorseModel record, HorseModelExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	int updateByPrimaryKeySelective(HorseModel record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_horse_model
	 * @ibatorgenerated  Fri May 06 18:15:47 CST 2011
	 */
	int updateByPrimaryKey(HorseModel record);
}
