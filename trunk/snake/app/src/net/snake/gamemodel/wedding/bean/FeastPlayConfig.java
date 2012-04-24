package net.snake.gamemodel.wedding.bean;

import net.snake.ibatis.IbatisEntity;

public class FeastPlayConfig  implements IbatisEntity{

	/**
	 * 婚宴类型 t_feast_playconfig.f_id
	 * 
	 */
	private Integer id;
	/**
	 * t_feast_playconfig.f_name
	 * 
	 */
	private String name;
	/**
	 * 所需好感度 t_feast_playconfig.f_applyfavor
	 * 
	 */
	private Integer applyfavor;
	/**
	 * 申请花费 t_feast_playconfig.f_applycost
	 * 
	 */
	private Integer applycost;
	/**
	 * 与参所需红包花费 t_feast_playconfig.f_giftcost
	 * 
	 */
	private Integer giftcost;
	/**
	 * 宴婚每次扣减血量 t_feast_playconfig.f_feast_lower
	 * 
	 */
	private Integer feastLower;
	/**
	 * 宴婚怪模型 t_feast_playconfig.f_feast_model
	 * 
	 */
	private Integer feastModel;
	/**
	 * 婚宴每波数量 t_feast_playconfig.f_feast_amount
	 * 
	 */
	private Integer feastAmount;
	/**
	 * 助兴怪1模型 t_feast_playconfig.f_lively1_model
	 * 
	 */
	private Integer lively1Model;
	/**
	 * 助兴怪1数量 t_feast_playconfig.f_lively1_amount
	 * 
	 */
	private Integer lively1Amount;
	/**
	 * 助兴怪2模型 t_feast_playconfig.f_lively2_model
	 * 
	 */
	private Integer lively2Model;
	/**
	 * 助兴怪2数量 t_feast_playconfig.f_lively2_amount
	 * 
	 */
	private Integer lively2Amount;
	/**
	 * 婚宴名称国际化 t_feast_playconfig.f_name_i18n
	 * 
	 */
	private String nameI18n;

	/**
	 * 婚宴类型 t_feast_playconfig.f_id
	 * @return  the value of t_feast_playconfig.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 婚宴类型 t_feast_playconfig.f_id
	 * @param id  the value for t_feast_playconfig.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_feast_playconfig.f_name
	 * @return  the value of t_feast_playconfig.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * t_feast_playconfig.f_name
	 * @param name  the value for t_feast_playconfig.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 所需好感度 t_feast_playconfig.f_applyfavor
	 * @return  the value of t_feast_playconfig.f_applyfavor
	 * 
	 */
	public Integer getApplyfavor() {
		return applyfavor;
	}

	/**
	 * 所需好感度 t_feast_playconfig.f_applyfavor
	 * @param applyfavor  the value for t_feast_playconfig.f_applyfavor
	 * 
	 */
	public void setApplyfavor(Integer applyfavor) {
		this.applyfavor = applyfavor;
	}

	/**
	 * 申请花费 t_feast_playconfig.f_applycost
	 * @return  the value of t_feast_playconfig.f_applycost
	 * 
	 */
	public Integer getApplycost() {
		return applycost;
	}

	/**
	 * 申请花费 t_feast_playconfig.f_applycost
	 * @param applycost  the value for t_feast_playconfig.f_applycost
	 * 
	 */
	public void setApplycost(Integer applycost) {
		this.applycost = applycost;
	}

	/**
	 * 与参所需红包花费 t_feast_playconfig.f_giftcost
	 * @return  the value of t_feast_playconfig.f_giftcost
	 * 
	 */
	public Integer getGiftcost() {
		return giftcost;
	}

	/**
	 * 与参所需红包花费 t_feast_playconfig.f_giftcost
	 * @param giftcost  the value for t_feast_playconfig.f_giftcost
	 * 
	 */
	public void setGiftcost(Integer giftcost) {
		this.giftcost = giftcost;
	}

	/**
	 * 宴婚每次扣减血量 t_feast_playconfig.f_feast_lower
	 * @return  the value of t_feast_playconfig.f_feast_lower
	 * 
	 */
	public Integer getFeastLower() {
		return feastLower;
	}

	/**
	 * 宴婚每次扣减血量 t_feast_playconfig.f_feast_lower
	 * @param feastLower  the value for t_feast_playconfig.f_feast_lower
	 * 
	 */
	public void setFeastLower(Integer feastLower) {
		this.feastLower = feastLower;
	}

	/**
	 * 宴婚怪模型 t_feast_playconfig.f_feast_model
	 * @return  the value of t_feast_playconfig.f_feast_model
	 * 
	 */
	public Integer getFeastModel() {
		return feastModel;
	}

	/**
	 * 宴婚怪模型 t_feast_playconfig.f_feast_model
	 * @param feastModel  the value for t_feast_playconfig.f_feast_model
	 * 
	 */
	public void setFeastModel(Integer feastModel) {
		this.feastModel = feastModel;
	}

	/**
	 * 婚宴每波数量 t_feast_playconfig.f_feast_amount
	 * @return  the value of t_feast_playconfig.f_feast_amount
	 * 
	 */
	public Integer getFeastAmount() {
		return feastAmount;
	}

	/**
	 * 婚宴每波数量 t_feast_playconfig.f_feast_amount
	 * @param feastAmount  the value for t_feast_playconfig.f_feast_amount
	 * 
	 */
	public void setFeastAmount(Integer feastAmount) {
		this.feastAmount = feastAmount;
	}

	/**
	 * 助兴怪1模型 t_feast_playconfig.f_lively1_model
	 * @return  the value of t_feast_playconfig.f_lively1_model
	 * 
	 */
	public Integer getLively1Model() {
		return lively1Model;
	}

	/**
	 * 助兴怪1模型 t_feast_playconfig.f_lively1_model
	 * @param lively1Model  the value for t_feast_playconfig.f_lively1_model
	 * 
	 */
	public void setLively1Model(Integer lively1Model) {
		this.lively1Model = lively1Model;
	}

	/**
	 * 助兴怪1数量 t_feast_playconfig.f_lively1_amount
	 * @return  the value of t_feast_playconfig.f_lively1_amount
	 * 
	 */
	public Integer getLively1Amount() {
		return lively1Amount;
	}

	/**
	 * 助兴怪1数量 t_feast_playconfig.f_lively1_amount
	 * @param lively1Amount  the value for t_feast_playconfig.f_lively1_amount
	 * 
	 */
	public void setLively1Amount(Integer lively1Amount) {
		this.lively1Amount = lively1Amount;
	}

	/**
	 * 助兴怪2模型 t_feast_playconfig.f_lively2_model
	 * @return  the value of t_feast_playconfig.f_lively2_model
	 * 
	 */
	public Integer getLively2Model() {
		return lively2Model;
	}

	/**
	 * 助兴怪2模型 t_feast_playconfig.f_lively2_model
	 * @param lively2Model  the value for t_feast_playconfig.f_lively2_model
	 * 
	 */
	public void setLively2Model(Integer lively2Model) {
		this.lively2Model = lively2Model;
	}

	/**
	 * 助兴怪2数量 t_feast_playconfig.f_lively2_amount
	 * @return  the value of t_feast_playconfig.f_lively2_amount
	 * 
	 */
	public Integer getLively2Amount() {
		return lively2Amount;
	}

	/**
	 * 助兴怪2数量 t_feast_playconfig.f_lively2_amount
	 * @param lively2Amount  the value for t_feast_playconfig.f_lively2_amount
	 * 
	 */
	public void setLively2Amount(Integer lively2Amount) {
		this.lively2Amount = lively2Amount;
	}

	/**
	 * 婚宴名称国际化 t_feast_playconfig.f_name_i18n
	 * @return  the value of t_feast_playconfig.f_name_i18n
	 * 
	 */
	public String getNameI18n() {
		return nameI18n;
	}

	/**
	 * 婚宴名称国际化 t_feast_playconfig.f_name_i18n
	 * @param nameI18n  the value for t_feast_playconfig.f_name_i18n
	 * 
	 */
	public void setNameI18n(String nameI18n) {
		this.nameI18n = nameI18n;
	}
}
