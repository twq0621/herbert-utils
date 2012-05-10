package net.snake.dao.lianti;

import java.util.List;

import net.snake.dao.lianti.Lianti;
import net.snake.dao.lianti.LiantiExample;

public interface LiantiDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	int countByExample(LiantiExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	int deleteByExample(LiantiExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	void insert(Lianti record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	void insertSelective(Lianti record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	List selectByExample(LiantiExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	Lianti selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	int updateByExampleSelective(Lianti record, LiantiExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	int updateByExample(Lianti record, LiantiExample example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	int updateByPrimaryKeySelective(Lianti record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_lianti
	 * @ibatorgenerated  Sat May 07 12:35:40 CST 2011
	 */
	int updateByPrimaryKey(Lianti record);
}