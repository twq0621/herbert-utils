package net.snake.gamemodel.across.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterAcross implements IbatisEntity{

	/**
	 * t_character_across.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 角色id t_character_across.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 跨服服务器id t_character_across.f_across_server_id
	 * 
	 */
	private Integer acrossServerId;
	/**
	 * 0跨服服务器无收益，1跨服服务器有收益 t_character_across.f_shouyi_state
	 * 
	 */
	private Integer shouyiState;
	/**
	 * 收益领取时间 t_character_across.f_shouyi_lingqu_time
	 * 
	 */
	private Date shouyiLingquTime;
	/**
	 * 玩家跨服时间 t_character_across.f_kuafu_date
	 * 
	 */
	private Date kuafuDate;
	/**
	 * 帐号id t_character_across.f_account_id
	 * 
	 */
	private Integer accountId;
	/**
	 * t_character_across.f_character_initially_id
	 * 
	 */
	private Integer characterInitiallyId;
	/**
	 * 角色原始帐号 t_character_across.f_account_initially_id
	 * 
	 */
	private Integer accountInitiallyId;
	/**
	 * 角色原始区服 t_character_across.f_server_id
	 * 
	 */
	private Integer serverId;

	/**
	 * t_character_across.f_id
	 * @return  the value of t_character_across.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_across.f_id
	 * @param id  the value for t_character_across.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色id t_character_across.f_character_id
	 * @return  the value of t_character_across.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_across.f_character_id
	 * @param characterId  the value for t_character_across.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 跨服服务器id t_character_across.f_across_server_id
	 * @return  the value of t_character_across.f_across_server_id
	 * 
	 */
	public Integer getAcrossServerId() {
		return acrossServerId;
	}

	/**
	 * 跨服服务器id t_character_across.f_across_server_id
	 * @param acrossServerId  the value for t_character_across.f_across_server_id
	 * 
	 */
	public void setAcrossServerId(Integer acrossServerId) {
		this.acrossServerId = acrossServerId;
	}

	/**
	 * 0跨服服务器无收益，1跨服服务器有收益 t_character_across.f_shouyi_state
	 * @return  the value of t_character_across.f_shouyi_state
	 * 
	 */
	public Integer getShouyiState() {
		return shouyiState;
	}

	/**
	 * 0跨服服务器无收益，1跨服服务器有收益 t_character_across.f_shouyi_state
	 * @param shouyiState  the value for t_character_across.f_shouyi_state
	 * 
	 */
	public void setShouyiState(Integer shouyiState) {
		this.shouyiState = shouyiState;
	}

	/**
	 * 收益领取时间 t_character_across.f_shouyi_lingqu_time
	 * @return  the value of t_character_across.f_shouyi_lingqu_time
	 * 
	 */
	public Date getShouyiLingquTime() {
		return shouyiLingquTime;
	}

	/**
	 * 收益领取时间 t_character_across.f_shouyi_lingqu_time
	 * @param shouyiLingquTime  the value for t_character_across.f_shouyi_lingqu_time
	 * 
	 */
	public void setShouyiLingquTime(Date shouyiLingquTime) {
		this.shouyiLingquTime = shouyiLingquTime;
	}

	/**
	 * 玩家跨服时间 t_character_across.f_kuafu_date
	 * @return  the value of t_character_across.f_kuafu_date
	 * 
	 */
	public Date getKuafuDate() {
		return kuafuDate;
	}

	/**
	 * 玩家跨服时间 t_character_across.f_kuafu_date
	 * @param kuafuDate  the value for t_character_across.f_kuafu_date
	 * 
	 */
	public void setKuafuDate(Date kuafuDate) {
		this.kuafuDate = kuafuDate;
	}

	/**
	 * 帐号id t_character_across.f_account_id
	 * @return  the value of t_character_across.f_account_id
	 * 
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 帐号id t_character_across.f_account_id
	 * @param accountId  the value for t_character_across.f_account_id
	 * 
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * t_character_across.f_character_initially_id
	 * @return  the value of t_character_across.f_character_initially_id
	 * 
	 */
	public Integer getCharacterInitiallyId() {
		return characterInitiallyId;
	}

	/**
	 * t_character_across.f_character_initially_id
	 * @param characterInitiallyId  the value for t_character_across.f_character_initially_id
	 * 
	 */
	public void setCharacterInitiallyId(Integer characterInitiallyId) {
		this.characterInitiallyId = characterInitiallyId;
	}

	/**
	 * 角色原始帐号 t_character_across.f_account_initially_id
	 * @return  the value of t_character_across.f_account_initially_id
	 * 
	 */
	public Integer getAccountInitiallyId() {
		return accountInitiallyId;
	}

	/**
	 * 角色原始帐号 t_character_across.f_account_initially_id
	 * @param accountInitiallyId  the value for t_character_across.f_account_initially_id
	 * 
	 */
	public void setAccountInitiallyId(Integer accountInitiallyId) {
		this.accountInitiallyId = accountInitiallyId;
	}

	/**
	 * 角色原始区服 t_character_across.f_server_id
	 * @return  the value of t_character_across.f_server_id
	 * 
	 */
	public Integer getServerId() {
		return serverId;
	}

	/**
	 * 角色原始区服 t_character_across.f_server_id
	 * @param serverId  the value for t_character_across.f_server_id
	 * 
	 */
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
}
