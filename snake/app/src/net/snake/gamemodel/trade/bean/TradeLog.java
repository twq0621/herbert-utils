package net.snake.gamemodel.trade.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class TradeLog implements IbatisEntity {

	/**
	 * t_trade_log.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 游戏帐号id t_trade_log.f_account_id
	 * 
	 */
	private Integer accountId;
	/**
	 * 运营帐号ID t_trade_log.f_yunying_id
	 * 
	 */
	private String yunyingId;
	/**
	 * 角色ID t_trade_log.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 角色名 t_trade_log.f_character_name
	 * 
	 */
	private String characterName;
	/**
	 * 角色消费时的等级 t_trade_log.f_character_grade
	 * 
	 */
	private Integer characterGrade;
	/**
	 * 消费日期 t_trade_log.f_trade_time
	 * 
	 */
	private Date tradeTime;
	/**
	 * 消费类型 1人与人元宝交易，2摊位列表购买物品，3摊位列表购买马 t_trade_log.f_trade_type
	 * 
	 */
	private Integer tradeType;
	/**
	 * 消费前的元宝数 t_trade_log.f_original_yuanbao
	 * 
	 */
	private Integer originalYuanbao;
	/**
	 * 本次消费元宝数量 t_trade_log.f_trade_gain_yuanbao
	 * 
	 */
	private Integer tradeGainYuanbao;
	/**
	 * 物品ID 交易给的角色id t_trade_log.f_trade_character_id
	 * 
	 */
	private Integer tradeCharacterId;
	/**
	 * 摊位的物品数量 如果消费类型为1 t_trade_log.f_goods_count
	 * 
	 */
	private Integer goodsCount;
	/**
	 * 摊位的物品的价格 t_trade_log.f_goods_price
	 * 
	 */
	private Integer goodsPrice;
	/**
	 * 摊位列表售出的id t_trade_log.f_good_id
	 * 
	 */
	private Integer goodId;
	/**
	 * 交易中失去元宝 t_trade_log.f_trade_lost_yuanbao
	 * 
	 */
	private Integer tradeLostYuanbao;
	/**
	 * 游戏帐号id t_trade_log.f_trade_account_id
	 * 
	 */
	private Integer tradeAccountId;
	/**
	 * 运营帐号ID t_trade_log.f_trade_yunying_id
	 * 
	 */
	private String tradeYunyingId;
	/**
	 * serverid t_trade_log.f_server_id
	 * 
	 */
	private Integer serverId;

	/**
	 * t_trade_log.f_id
	 * @return  the value of t_trade_log.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_trade_log.f_id
	 * @param id  the value for t_trade_log.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 游戏帐号id t_trade_log.f_account_id
	 * @return  the value of t_trade_log.f_account_id
	 * 
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 游戏帐号id t_trade_log.f_account_id
	 * @param accountId  the value for t_trade_log.f_account_id
	 * 
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 运营帐号ID t_trade_log.f_yunying_id
	 * @return  the value of t_trade_log.f_yunying_id
	 * 
	 */
	public String getYunyingId() {
		return yunyingId;
	}

	/**
	 * 运营帐号ID t_trade_log.f_yunying_id
	 * @param yunyingId  the value for t_trade_log.f_yunying_id
	 * 
	 */
	public void setYunyingId(String yunyingId) {
		this.yunyingId = yunyingId;
	}

	/**
	 * 角色ID t_trade_log.f_character_id
	 * @return  the value of t_trade_log.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色ID t_trade_log.f_character_id
	 * @param characterId  the value for t_trade_log.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 角色名 t_trade_log.f_character_name
	 * @return  the value of t_trade_log.f_character_name
	 * 
	 */
	public String getCharacterName() {
		return characterName;
	}

	/**
	 * 角色名 t_trade_log.f_character_name
	 * @param characterName  the value for t_trade_log.f_character_name
	 * 
	 */
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	/**
	 * 角色消费时的等级 t_trade_log.f_character_grade
	 * @return  the value of t_trade_log.f_character_grade
	 * 
	 */
	public Integer getCharacterGrade() {
		return characterGrade;
	}

	/**
	 * 角色消费时的等级 t_trade_log.f_character_grade
	 * @param characterGrade  the value for t_trade_log.f_character_grade
	 * 
	 */
	public void setCharacterGrade(Integer characterGrade) {
		this.characterGrade = characterGrade;
	}

	/**
	 * 消费日期 t_trade_log.f_trade_time
	 * @return  the value of t_trade_log.f_trade_time
	 * 
	 */
	public Date getTradeTime() {
		return tradeTime;
	}

	/**
	 * 消费日期 t_trade_log.f_trade_time
	 * @param tradeTime  the value for t_trade_log.f_trade_time
	 * 
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	/**
	 * 消费类型 1人与人元宝交易，2摊位列表购买物品，3摊位列表购买马 t_trade_log.f_trade_type
	 * @return  the value of t_trade_log.f_trade_type
	 * 
	 */
	public Integer getTradeType() {
		return tradeType;
	}

	/**
	 * 消费类型 1人与人元宝交易，2摊位列表购买物品，3摊位列表购买马 t_trade_log.f_trade_type
	 * @param tradeType  the value for t_trade_log.f_trade_type
	 * 
	 */
	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	/**
	 * 消费前的元宝数 t_trade_log.f_original_yuanbao
	 * @return  the value of t_trade_log.f_original_yuanbao
	 * 
	 */
	public Integer getOriginalYuanbao() {
		return originalYuanbao;
	}

	/**
	 * 消费前的元宝数 t_trade_log.f_original_yuanbao
	 * @param originalYuanbao  the value for t_trade_log.f_original_yuanbao
	 * 
	 */
	public void setOriginalYuanbao(Integer originalYuanbao) {
		this.originalYuanbao = originalYuanbao;
	}

	/**
	 * 本次消费元宝数量 t_trade_log.f_trade_gain_yuanbao
	 * @return  the value of t_trade_log.f_trade_gain_yuanbao
	 * 
	 */
	public Integer getTradeGainYuanbao() {
		return tradeGainYuanbao;
	}

	/**
	 * 本次消费元宝数量 t_trade_log.f_trade_gain_yuanbao
	 * @param tradeGainYuanbao  the value for t_trade_log.f_trade_gain_yuanbao
	 * 
	 */
	public void setTradeGainYuanbao(Integer tradeGainYuanbao) {
		this.tradeGainYuanbao = tradeGainYuanbao;
	}

	/**
	 * 物品ID 交易给的角色id t_trade_log.f_trade_character_id
	 * @return  the value of t_trade_log.f_trade_character_id
	 * 
	 */
	public Integer getTradeCharacterId() {
		return tradeCharacterId;
	}

	/**
	 * 物品ID 交易给的角色id t_trade_log.f_trade_character_id
	 * @param tradeCharacterId  the value for t_trade_log.f_trade_character_id
	 * 
	 */
	public void setTradeCharacterId(Integer tradeCharacterId) {
		this.tradeCharacterId = tradeCharacterId;
	}

	/**
	 * 摊位的物品数量 如果消费类型为1 t_trade_log.f_goods_count
	 * @return  the value of t_trade_log.f_goods_count
	 * 
	 */
	public Integer getGoodsCount() {
		return goodsCount;
	}

	/**
	 * 摊位的物品数量 如果消费类型为1 t_trade_log.f_goods_count
	 * @param goodsCount  the value for t_trade_log.f_goods_count
	 * 
	 */
	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	/**
	 * 摊位的物品的价格 t_trade_log.f_goods_price
	 * @return  the value of t_trade_log.f_goods_price
	 * 
	 */
	public Integer getGoodsPrice() {
		return goodsPrice;
	}

	/**
	 * 摊位的物品的价格 t_trade_log.f_goods_price
	 * @param goodsPrice  the value for t_trade_log.f_goods_price
	 * 
	 */
	public void setGoodsPrice(Integer goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/**
	 * 摊位列表售出的id t_trade_log.f_good_id
	 * @return  the value of t_trade_log.f_good_id
	 * 
	 */
	public Integer getGoodId() {
		return goodId;
	}

	/**
	 * 摊位列表售出的id t_trade_log.f_good_id
	 * @param goodId  the value for t_trade_log.f_good_id
	 * 
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	/**
	 * 交易中失去元宝 t_trade_log.f_trade_lost_yuanbao
	 * @return  the value of t_trade_log.f_trade_lost_yuanbao
	 * 
	 */
	public Integer getTradeLostYuanbao() {
		return tradeLostYuanbao;
	}

	/**
	 * 交易中失去元宝 t_trade_log.f_trade_lost_yuanbao
	 * @param tradeLostYuanbao  the value for t_trade_log.f_trade_lost_yuanbao
	 * 
	 */
	public void setTradeLostYuanbao(Integer tradeLostYuanbao) {
		this.tradeLostYuanbao = tradeLostYuanbao;
	}

	/**
	 * 游戏帐号id t_trade_log.f_trade_account_id
	 * @return  the value of t_trade_log.f_trade_account_id
	 * 
	 */
	public Integer getTradeAccountId() {
		return tradeAccountId;
	}

	/**
	 * 游戏帐号id t_trade_log.f_trade_account_id
	 * @param tradeAccountId  the value for t_trade_log.f_trade_account_id
	 * 
	 */
	public void setTradeAccountId(Integer tradeAccountId) {
		this.tradeAccountId = tradeAccountId;
	}

	/**
	 * 运营帐号ID t_trade_log.f_trade_yunying_id
	 * @return  the value of t_trade_log.f_trade_yunying_id
	 * 
	 */
	public String getTradeYunyingId() {
		return tradeYunyingId;
	}

	/**
	 * 运营帐号ID t_trade_log.f_trade_yunying_id
	 * @param tradeYunyingId  the value for t_trade_log.f_trade_yunying_id
	 * 
	 */
	public void setTradeYunyingId(String tradeYunyingId) {
		this.tradeYunyingId = tradeYunyingId;
	}

	/**
	 * serverid t_trade_log.f_server_id
	 * @return  the value of t_trade_log.f_server_id
	 * 
	 */
	public Integer getServerId() {
		return serverId;
	}

	/**
	 * serverid t_trade_log.f_server_id
	 * @param serverId  the value for t_trade_log.f_server_id
	 * 
	 */
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
}
