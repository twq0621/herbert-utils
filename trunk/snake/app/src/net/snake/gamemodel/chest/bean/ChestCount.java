package net.snake.gamemodel.chest.bean;

import net.snake.ibatis.IbatisEntity;

public class ChestCount implements IbatisEntity {

	/**
	 * 主键 t_chest_count.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 祝福 t_chest_count.f_chesttype_3300
	 * 
	 */
	private Integer chesttype3300;
	/**
	 * 祝福 t_chest_count.f_chesttype_3200
	 * 
	 */
	private Integer chesttype3200;
	/**
	 * 祝福 t_chest_count.f_chesttype_3100
	 * 
	 */
	private Integer chesttype3100;
	/**
	 * 经验松果显示 t_chest_count.f_chesttype_receive_3011
	 * 
	 */
	private Integer chesttypeReceive3011;
	/**
	 * 真气松果显示 t_chest_count.f_chesttype_receive_3012
	 * 
	 */
	private Integer chesttypeReceive3012;
	/**
	 * 秘籍松果显示 t_chest_count.f_chesttype_receive_3013
	 * 
	 */
	private Integer chesttypeReceive3013;
	/**
	 * 宝石松果显示 t_chest_count.f_chesttype_receive_3014
	 * 
	 */
	private Integer chesttypeReceive3014;
	/**
	 * 10星紫装显示 t_chest_count.f_chesttype_receive_3015
	 * 
	 */
	private Integer chesttypeReceive3015;
	/**
	 * 经验松果领取 t_chest_count.f_chesttype_receive_true_3011
	 * 
	 */
	private Integer chesttypeReceiveTrue3011;
	/**
	 * 真气松果领取 t_chest_count.f_chesttype_receive_true_3012
	 * 
	 */
	private Integer chesttypeReceiveTrue3012;
	/**
	 * 秘籍松果领取 t_chest_count.f_chesttype_receive_true_3013
	 * 
	 */
	private Integer chesttypeReceiveTrue3013;
	/**
	 * 宝石松果领取 t_chest_count.f_chesttype_receive_true_3014
	 * 
	 */
	private Integer chesttypeReceiveTrue3014;
	/**
	 * 10星紫装领取 t_chest_count.f_chesttype_receive_true_3015
	 * 
	 */
	private Integer chesttypeReceiveTrue3015;
	/**
	 * 友好度兑换3011--3015使用 t_chest_count.f_chesttype_exchange_count
	 * 
	 */
	private Integer chesttypeExchangeCount;
	/**
	 * 松果使用限制 t_chest_count.f_chest_use_count
	 * 
	 */
	private Integer chestUseCount;

	/**
	 * 主键 t_chest_count.f_character_id
	 * @return  the value of t_chest_count.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 主键 t_chest_count.f_character_id
	 * @param characterId  the value for t_chest_count.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 祝福 t_chest_count.f_chesttype_3300
	 * @return  the value of t_chest_count.f_chesttype_3300
	 * 
	 */
	public Integer getChesttype3300() {
		return chesttype3300;
	}

	/**
	 * 祝福 t_chest_count.f_chesttype_3300
	 * @param chesttype3300  the value for t_chest_count.f_chesttype_3300
	 * 
	 */
	public void setChesttype3300(Integer chesttype3300) {
		this.chesttype3300 = chesttype3300;
	}

	/**
	 * 祝福 t_chest_count.f_chesttype_3200
	 * @return  the value of t_chest_count.f_chesttype_3200
	 * 
	 */
	public Integer getChesttype3200() {
		return chesttype3200;
	}

	/**
	 * 祝福 t_chest_count.f_chesttype_3200
	 * @param chesttype3200  the value for t_chest_count.f_chesttype_3200
	 * 
	 */
	public void setChesttype3200(Integer chesttype3200) {
		this.chesttype3200 = chesttype3200;
	}

	/**
	 * 祝福 t_chest_count.f_chesttype_3100
	 * @return  the value of t_chest_count.f_chesttype_3100
	 * 
	 */
	public Integer getChesttype3100() {
		return chesttype3100;
	}

	/**
	 * 祝福 t_chest_count.f_chesttype_3100
	 * @param chesttype3100  the value for t_chest_count.f_chesttype_3100
	 * 
	 */
	public void setChesttype3100(Integer chesttype3100) {
		this.chesttype3100 = chesttype3100;
	}

	/**
	 * 经验松果显示 t_chest_count.f_chesttype_receive_3011
	 * @return  the value of t_chest_count.f_chesttype_receive_3011
	 * 
	 */
	public Integer getChesttypeReceive3011() {
		return chesttypeReceive3011;
	}

	/**
	 * 经验松果显示 t_chest_count.f_chesttype_receive_3011
	 * @param chesttypeReceive3011  the value for t_chest_count.f_chesttype_receive_3011
	 * 
	 */
	public void setChesttypeReceive3011(Integer chesttypeReceive3011) {
		this.chesttypeReceive3011 = chesttypeReceive3011;
	}

	/**
	 * 真气松果显示 t_chest_count.f_chesttype_receive_3012
	 * @return  the value of t_chest_count.f_chesttype_receive_3012
	 * 
	 */
	public Integer getChesttypeReceive3012() {
		return chesttypeReceive3012;
	}

	/**
	 * 真气松果显示 t_chest_count.f_chesttype_receive_3012
	 * @param chesttypeReceive3012  the value for t_chest_count.f_chesttype_receive_3012
	 * 
	 */
	public void setChesttypeReceive3012(Integer chesttypeReceive3012) {
		this.chesttypeReceive3012 = chesttypeReceive3012;
	}

	/**
	 * 秘籍松果显示 t_chest_count.f_chesttype_receive_3013
	 * @return  the value of t_chest_count.f_chesttype_receive_3013
	 * 
	 */
	public Integer getChesttypeReceive3013() {
		return chesttypeReceive3013;
	}

	/**
	 * 秘籍松果显示 t_chest_count.f_chesttype_receive_3013
	 * @param chesttypeReceive3013  the value for t_chest_count.f_chesttype_receive_3013
	 * 
	 */
	public void setChesttypeReceive3013(Integer chesttypeReceive3013) {
		this.chesttypeReceive3013 = chesttypeReceive3013;
	}

	/**
	 * 宝石松果显示 t_chest_count.f_chesttype_receive_3014
	 * @return  the value of t_chest_count.f_chesttype_receive_3014
	 * 
	 */
	public Integer getChesttypeReceive3014() {
		return chesttypeReceive3014;
	}

	/**
	 * 宝石松果显示 t_chest_count.f_chesttype_receive_3014
	 * @param chesttypeReceive3014  the value for t_chest_count.f_chesttype_receive_3014
	 * 
	 */
	public void setChesttypeReceive3014(Integer chesttypeReceive3014) {
		this.chesttypeReceive3014 = chesttypeReceive3014;
	}

	/**
	 * 10星紫装显示 t_chest_count.f_chesttype_receive_3015
	 * @return  the value of t_chest_count.f_chesttype_receive_3015
	 * 
	 */
	public Integer getChesttypeReceive3015() {
		return chesttypeReceive3015;
	}

	/**
	 * 10星紫装显示 t_chest_count.f_chesttype_receive_3015
	 * @param chesttypeReceive3015  the value for t_chest_count.f_chesttype_receive_3015
	 * 
	 */
	public void setChesttypeReceive3015(Integer chesttypeReceive3015) {
		this.chesttypeReceive3015 = chesttypeReceive3015;
	}

	/**
	 * 经验松果领取 t_chest_count.f_chesttype_receive_true_3011
	 * @return  the value of t_chest_count.f_chesttype_receive_true_3011
	 * 
	 */
	public Integer getChesttypeReceiveTrue3011() {
		return chesttypeReceiveTrue3011;
	}

	/**
	 * 经验松果领取 t_chest_count.f_chesttype_receive_true_3011
	 * @param chesttypeReceiveTrue3011  the value for t_chest_count.f_chesttype_receive_true_3011
	 * 
	 */
	public void setChesttypeReceiveTrue3011(Integer chesttypeReceiveTrue3011) {
		this.chesttypeReceiveTrue3011 = chesttypeReceiveTrue3011;
	}

	/**
	 * 真气松果领取 t_chest_count.f_chesttype_receive_true_3012
	 * @return  the value of t_chest_count.f_chesttype_receive_true_3012
	 * 
	 */
	public Integer getChesttypeReceiveTrue3012() {
		return chesttypeReceiveTrue3012;
	}

	/**
	 * 真气松果领取 t_chest_count.f_chesttype_receive_true_3012
	 * @param chesttypeReceiveTrue3012  the value for t_chest_count.f_chesttype_receive_true_3012
	 * 
	 */
	public void setChesttypeReceiveTrue3012(Integer chesttypeReceiveTrue3012) {
		this.chesttypeReceiveTrue3012 = chesttypeReceiveTrue3012;
	}

	/**
	 * 秘籍松果领取 t_chest_count.f_chesttype_receive_true_3013
	 * @return  the value of t_chest_count.f_chesttype_receive_true_3013
	 * 
	 */
	public Integer getChesttypeReceiveTrue3013() {
		return chesttypeReceiveTrue3013;
	}

	/**
	 * 秘籍松果领取 t_chest_count.f_chesttype_receive_true_3013
	 * @param chesttypeReceiveTrue3013  the value for t_chest_count.f_chesttype_receive_true_3013
	 * 
	 */
	public void setChesttypeReceiveTrue3013(Integer chesttypeReceiveTrue3013) {
		this.chesttypeReceiveTrue3013 = chesttypeReceiveTrue3013;
	}

	/**
	 * 宝石松果领取 t_chest_count.f_chesttype_receive_true_3014
	 * @return  the value of t_chest_count.f_chesttype_receive_true_3014
	 * 
	 */
	public Integer getChesttypeReceiveTrue3014() {
		return chesttypeReceiveTrue3014;
	}

	/**
	 * 宝石松果领取 t_chest_count.f_chesttype_receive_true_3014
	 * @param chesttypeReceiveTrue3014  the value for t_chest_count.f_chesttype_receive_true_3014
	 * 
	 */
	public void setChesttypeReceiveTrue3014(Integer chesttypeReceiveTrue3014) {
		this.chesttypeReceiveTrue3014 = chesttypeReceiveTrue3014;
	}

	/**
	 * 10星紫装领取 t_chest_count.f_chesttype_receive_true_3015
	 * @return  the value of t_chest_count.f_chesttype_receive_true_3015
	 * 
	 */
	public Integer getChesttypeReceiveTrue3015() {
		return chesttypeReceiveTrue3015;
	}

	/**
	 * 10星紫装领取 t_chest_count.f_chesttype_receive_true_3015
	 * @param chesttypeReceiveTrue3015  the value for t_chest_count.f_chesttype_receive_true_3015
	 * 
	 */
	public void setChesttypeReceiveTrue3015(Integer chesttypeReceiveTrue3015) {
		this.chesttypeReceiveTrue3015 = chesttypeReceiveTrue3015;
	}

	/**
	 * 友好度兑换3011--3015使用 t_chest_count.f_chesttype_exchange_count
	 * @return  the value of t_chest_count.f_chesttype_exchange_count
	 * 
	 */
	public Integer getChesttypeExchangeCount() {
		return chesttypeExchangeCount;
	}

	/**
	 * 友好度兑换3011--3015使用 t_chest_count.f_chesttype_exchange_count
	 * @param chesttypeExchangeCount  the value for t_chest_count.f_chesttype_exchange_count
	 * 
	 */
	public void setChesttypeExchangeCount(Integer chesttypeExchangeCount) {
		this.chesttypeExchangeCount = chesttypeExchangeCount;
	}

	/**
	 * 松果使用限制 t_chest_count.f_chest_use_count
	 * @return  the value of t_chest_count.f_chest_use_count
	 * 
	 */
	public Integer getChestUseCount() {
		return chestUseCount;
	}

	/**
	 * 松果使用限制 t_chest_count.f_chest_use_count
	 * @param chestUseCount  the value for t_chest_count.f_chest_use_count
	 * 
	 */
	public void setChestUseCount(Integer chestUseCount) {
		this.chestUseCount = chestUseCount;
	}
}
