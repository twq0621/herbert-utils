package net.snake.gamemodel.gm.bean;

import net.snake.ibatis.IbatisEntity;

public class GmInfo  implements IbatisEntity{

	/**
	 * 键主ID t_gm_info.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 运营ID t_gm_info.f_yunying_id
	 * 
	 */
	private String yunyingId;
	/**
	 * 是否过期0为不可用，1为可用 t_gm_info.f_state
	 * 
	 */
	private Integer state;

	/**
	 * 键主ID t_gm_info.f_id
	 * @return  the value of t_gm_info.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 键主ID t_gm_info.f_id
	 * @param id  the value for t_gm_info.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 运营ID t_gm_info.f_yunying_id
	 * @return  the value of t_gm_info.f_yunying_id
	 * 
	 */
	public String getYunyingId() {
		return yunyingId;
	}

	/**
	 * 运营ID t_gm_info.f_yunying_id
	 * @param yunyingId  the value for t_gm_info.f_yunying_id
	 * 
	 */
	public void setYunyingId(String yunyingId) {
		this.yunyingId = yunyingId;
	}

	/**
	 * 是否过期0为不可用，1为可用 t_gm_info.f_state
	 * @return  the value of t_gm_info.f_state
	 * 
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 是否过期0为不可用，1为可用 t_gm_info.f_state
	 * @param state  the value for t_gm_info.f_state
	 * 
	 */
	public void setState(Integer state) {
		this.state = state;
	}
}
