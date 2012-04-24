package net.snake.gamemodel.shop.bean;

import net.snake.ibatis.IbatisEntity;

public class TaskShoppingGoods implements IbatisEntity{

	/**
	 * t_character_task_shopping.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 角色id t_character_task_shopping.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 购买商城道具时与任务相关的goodmodelid t_character_task_shopping.f_good
	 * 
	 */
	private Integer good;
	/**
	 * 购买商城道具时与任务相关的good数量 t_character_task_shopping.f_num
	 * 
	 */
	private Integer num;

	/**
	 * t_character_task_shopping.f_id
	 * @return  the value of t_character_task_shopping.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_task_shopping.f_id
	 * @param id  the value for t_character_task_shopping.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色id t_character_task_shopping.f_character_id
	 * @return  the value of t_character_task_shopping.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_task_shopping.f_character_id
	 * @param characterId  the value for t_character_task_shopping.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 购买商城道具时与任务相关的goodmodelid t_character_task_shopping.f_good
	 * @return  the value of t_character_task_shopping.f_good
	 * 
	 */
	public Integer getGood() {
		return good;
	}

	/**
	 * 购买商城道具时与任务相关的goodmodelid t_character_task_shopping.f_good
	 * @param good  the value for t_character_task_shopping.f_good
	 * 
	 */
	public void setGood(Integer good) {
		this.good = good;
	}

	/**
	 * 购买商城道具时与任务相关的good数量 t_character_task_shopping.f_num
	 * @return  the value of t_character_task_shopping.f_num
	 * 
	 */
	public Integer getNum() {
		return num;
	}

	/**
	 * 购买商城道具时与任务相关的good数量 t_character_task_shopping.f_num
	 * @param num  the value for t_character_task_shopping.f_num
	 * 
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public static TaskShoppingGoods createTaskShoppingGoods(int characterid,int good,int num){
		TaskShoppingGoods taskShoppingGoods = new TaskShoppingGoods();
		taskShoppingGoods.setCharacterId(characterid);
		taskShoppingGoods.setGood(good);
		taskShoppingGoods.setNum(num);
		return taskShoppingGoods;
	}
}
