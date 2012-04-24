package net.snake.gamemodel.chest.bean;

import net.snake.ibatis.IbatisEntity;

public class ChestGroup implements IbatisEntity {

	/**
	 * 宝箱组别id t_chest_group.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 宝箱组几率 t_chest_group.f_Probability
	 * 
	 */
	private Integer probability;
	/**
	 * 抽几个 t_chest_group.f_count
	 * 
	 */
	private Byte count;
	/**
	 * 宝箱类型id t_chest_group.f_type_id
	 * 
	 */
	private Integer typeId;
	/**
	 * 宝箱名字 t_chest_group.f_name
	 * 
	 */
	private String name;

	/**
	 * 宝箱组别id t_chest_group.f_id
	 * @return  the value of t_chest_group.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 宝箱组别id t_chest_group.f_id
	 * @param id  the value for t_chest_group.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 宝箱组几率 t_chest_group.f_Probability
	 * @return  the value of t_chest_group.f_Probability
	 * 
	 */
	public Integer getProbability() {
		return probability;
	}

	/**
	 * 宝箱组几率 t_chest_group.f_Probability
	 * @param probability  the value for t_chest_group.f_Probability
	 * 
	 */
	public void setProbability(Integer probability) {
		this.probability = probability;
	}

	/**
	 * 抽几个 t_chest_group.f_count
	 * @return  the value of t_chest_group.f_count
	 * 
	 */
	public Byte getCount() {
		return count;
	}

	/**
	 * 抽几个 t_chest_group.f_count
	 * @param count  the value for t_chest_group.f_count
	 * 
	 */
	public void setCount(Byte count) {
		this.count = count;
	}

	/**
	 * 宝箱类型id t_chest_group.f_type_id
	 * @return  the value of t_chest_group.f_type_id
	 * 
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * 宝箱类型id t_chest_group.f_type_id
	 * @param typeId  the value for t_chest_group.f_type_id
	 * 
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * 宝箱名字 t_chest_group.f_name
	 * @return  the value of t_chest_group.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 宝箱名字 t_chest_group.f_name
	 * @param name  the value for t_chest_group.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
}
