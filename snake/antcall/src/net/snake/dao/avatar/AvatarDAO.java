package net.snake.dao.avatar;

import java.util.List;

import net.snake.dao.avatar.Avatar;
import net.snake.dao.avatar.AvatarExample;
import net.snake.dao.effect.Effect;

public interface AvatarDAO {
    /**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	int countByExample(AvatarExample example);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	int deleteByExample(AvatarExample example);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	void insert(Avatar record);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	void insertSelective(Avatar record);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	List selectByExample(AvatarExample example);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	Avatar selectByPrimaryKey(Integer id);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	int updateByExampleSelective(Avatar record, AvatarExample example);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	int updateByExample(Avatar record, AvatarExample example);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	int updateByPrimaryKeySelective(Avatar record);
	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table t_avatar
	 */
	int updateByPrimaryKey(Avatar record);
	List<Avatar> getAvatar_avatar();
    List<Avatar> getAvatar_avatarByid(Avatar avatar);
  
}