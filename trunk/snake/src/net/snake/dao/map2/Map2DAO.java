package net.snake.dao.map2;

import java.util.List;

import net.snake.dao.map2.Map2;
import net.snake.dao.map2.Map2Example;

public interface Map2DAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	int countByExample(Map2Example example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	int deleteByExample(Map2Example example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	int deleteByPrimaryKey(Integer mapId);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	void insert(Map2 record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	void insertSelective(Map2 record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	List selectByExample(Map2Example example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	Map2 selectByPrimaryKey(Integer mapId);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	int updateByExampleSelective(Map2 record, Map2Example example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	int updateByExample(Map2 record, Map2Example example);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	int updateByPrimaryKeySelective(Map2 record);

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_map_1
	 * @ibatorgenerated  Fri May 06 16:50:49 CST 2011
	 */
	int updateByPrimaryKey(Map2 record);
}
