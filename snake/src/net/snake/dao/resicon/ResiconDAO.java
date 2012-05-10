package net.snake.dao.resicon;


import java.util.List;

import net.snake.dao.resicon.Resicon;
import net.snake.dao.resicon.ResiconExample;

public interface ResiconDAO {
    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	int countByExample(ResiconExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	int deleteByExample(ResiconExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	void insert(Resicon record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	void insertSelective(Resicon record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	List selectByExample(ResiconExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	Resicon selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	int updateByExampleSelective(Resicon record, ResiconExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	int updateByExample(Resicon record, ResiconExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	int updateByPrimaryKeySelective(Resicon record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_res_icon
	 * @ibatorgenerated  Thu Oct 28 02:06:27 GMT 2010
	 */
	int updateByPrimaryKey(Resicon record);

	List<Resicon> getLianHeChaXun(int type);
}
