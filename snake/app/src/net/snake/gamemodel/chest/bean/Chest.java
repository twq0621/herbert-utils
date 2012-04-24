package net.snake.gamemodel.chest.bean;

import net.snake.ibatis.IbatisEntity;

public class Chest implements IbatisEntity {

	/**
	 * 主键 t_chest.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 角色id t_chest.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 获奖时间 t_chest.f_time
	 * 
	 */
	private String time;
	/**
	 * 得到的奖品id t_chest.f_chest_resources_id
	 * 
	 */
	private String chestResourcesId;
	/**
	 * 物品是哪类 t_chest.f_goodmodel_type
	 * 
	 */
	private Integer goodmodelType;
	/**
	 * 0是没领奖1是领奖了 对应f_chest_list t_chest.f_type
	 * 
	 */
	private Byte type;
	/**
	 * t_chest.f_chest_list
	 * 
	 */
	private String chestList;
	/**
	 * 奖品对应索引位置 t_chest.f_position
	 * 
	 */
	private String position;
	/**
	 * 宝箱转盘状态 0 没转1转过 t_chest.f_turn
	 * 
	 */
	private Byte turn;
	/**
	 * 兑换礼金 t_chest.f_lijin_type
	 * 
	 */
	private Byte lijinType;
	/**
	 * 物品标识 t_chest.f_charactergood_uuid
	 * 
	 */
	private String charactergoodUuid;
	/**
	 * 物品的品质 t_chest.f_pinzhi
	 * 
	 */
	private String pinzhi;
	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_chest.f_binding
	 * 
	 */
	private Byte binding;
	/**
	 * 数量 t_chest.f_quantity
	 * 
	 */
	private Byte quantity;
	/**
	 * 失效时间 t_chest.f_fail_time
	 * 
	 */
	private Integer failTime;
	/**
	 * 是否全服广播1广播0不广播 t_chest.f_full_service_announcement
	 * 
	 */
	private Byte fullServiceAnnouncement;

	/**
	 * 主键 t_chest.f_id
	 * @return  the value of t_chest.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键 t_chest.f_id
	 * @param id  the value for t_chest.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色id t_chest.f_character_id
	 * @return  the value of t_chest.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_chest.f_character_id
	 * @param characterId  the value for t_chest.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 获奖时间 t_chest.f_time
	 * @return  the value of t_chest.f_time
	 * 
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 获奖时间 t_chest.f_time
	 * @param time  the value for t_chest.f_time
	 * 
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 得到的奖品id t_chest.f_chest_resources_id
	 * @return  the value of t_chest.f_chest_resources_id
	 * 
	 */
	public String getChestResourcesId() {
		return chestResourcesId;
	}

	/**
	 * 得到的奖品id t_chest.f_chest_resources_id
	 * @param chestResourcesId  the value for t_chest.f_chest_resources_id
	 * 
	 */
	public void setChestResourcesId(String chestResourcesId) {
		this.chestResourcesId = chestResourcesId;
	}

	/**
	 * 物品是哪类 t_chest.f_goodmodel_type
	 * @return  the value of t_chest.f_goodmodel_type
	 * 
	 */
	public Integer getGoodmodelType() {
		return goodmodelType;
	}

	/**
	 * 物品是哪类 t_chest.f_goodmodel_type
	 * @param goodmodelType  the value for t_chest.f_goodmodel_type
	 * 
	 */
	public void setGoodmodelType(Integer goodmodelType) {
		this.goodmodelType = goodmodelType;
	}

	/**
	 * 0是没领奖1是领奖了 对应f_chest_list t_chest.f_type
	 * @return  the value of t_chest.f_type
	 * 
	 */
	public Byte getType() {
		return type;
	}

	/**
	 * 0是没领奖1是领奖了 对应f_chest_list t_chest.f_type
	 * @param type  the value for t_chest.f_type
	 * 
	 */
	public void setType(Byte type) {
		this.type = type;
	}

	/**
	 * t_chest.f_chest_list
	 * @return  the value of t_chest.f_chest_list
	 * 
	 */
	public String getChestList() {
		return chestList;
	}

	/**
	 * t_chest.f_chest_list
	 * @param chestList  the value for t_chest.f_chest_list
	 * 
	 */
	public void setChestList(String chestList) {
		this.chestList = chestList;
	}

	/**
	 * 奖品对应索引位置 t_chest.f_position
	 * @return  the value of t_chest.f_position
	 * 
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 奖品对应索引位置 t_chest.f_position
	 * @param position  the value for t_chest.f_position
	 * 
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 宝箱转盘状态 0 没转1转过 t_chest.f_turn
	 * @return  the value of t_chest.f_turn
	 * 
	 */
	public Byte getTurn() {
		return turn;
	}

	/**
	 * 宝箱转盘状态 0 没转1转过 t_chest.f_turn
	 * @param turn  the value for t_chest.f_turn
	 * 
	 */
	public void setTurn(Byte turn) {
		this.turn = turn;
	}

	/**
	 * 兑换礼金 t_chest.f_lijin_type
	 * @return  the value of t_chest.f_lijin_type
	 * 
	 */
	public Byte getLijinType() {
		return lijinType;
	}

	/**
	 * 兑换礼金 t_chest.f_lijin_type
	 * @param lijinType  the value for t_chest.f_lijin_type
	 * 
	 */
	public void setLijinType(Byte lijinType) {
		this.lijinType = lijinType;
	}

	/**
	 * 物品标识 t_chest.f_charactergood_uuid
	 * @return  the value of t_chest.f_charactergood_uuid
	 * 
	 */
	public String getCharactergoodUuid() {
		return charactergoodUuid;
	}

	/**
	 * 物品标识 t_chest.f_charactergood_uuid
	 * @param charactergoodUuid  the value for t_chest.f_charactergood_uuid
	 * 
	 */
	public void setCharactergoodUuid(String charactergoodUuid) {
		this.charactergoodUuid = charactergoodUuid;
	}

	/**
	 * 物品的品质 t_chest.f_pinzhi
	 * @return  the value of t_chest.f_pinzhi
	 * 
	 */
	public String getPinzhi() {
		return pinzhi;
	}

	/**
	 * 物品的品质 t_chest.f_pinzhi
	 * @param pinzhi  the value for t_chest.f_pinzhi
	 * 
	 */
	public void setPinzhi(String pinzhi) {
		this.pinzhi = pinzhi;
	}

	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_chest.f_binding
	 * @return  the value of t_chest.f_binding
	 * 
	 */
	public Byte getBinding() {
		return binding;
	}

	/**
	 * 1不绑定,2拾取绑定,3佩戴绑定 t_chest.f_binding
	 * @param binding  the value for t_chest.f_binding
	 * 
	 */
	public void setBinding(Byte binding) {
		this.binding = binding;
	}

	/**
	 * 数量 t_chest.f_quantity
	 * @return  the value of t_chest.f_quantity
	 * 
	 */
	public Byte getQuantity() {
		return quantity;
	}

	/**
	 * 数量 t_chest.f_quantity
	 * @param quantity  the value for t_chest.f_quantity
	 * 
	 */
	public void setQuantity(Byte quantity) {
		this.quantity = quantity;
	}

	/**
	 * 失效时间 t_chest.f_fail_time
	 * @return  the value of t_chest.f_fail_time
	 * 
	 */
	public Integer getFailTime() {
		return failTime;
	}

	/**
	 * 失效时间 t_chest.f_fail_time
	 * @param failTime  the value for t_chest.f_fail_time
	 * 
	 */
	public void setFailTime(Integer failTime) {
		this.failTime = failTime;
	}

	/**
	 * 是否全服广播1广播0不广播 t_chest.f_full_service_announcement
	 * @return  the value of t_chest.f_full_service_announcement
	 * 
	 */
	public Byte getFullServiceAnnouncement() {
		return fullServiceAnnouncement;
	}

	/**
	 * 是否全服广播1广播0不广播 t_chest.f_full_service_announcement
	 * @param fullServiceAnnouncement  the value for t_chest.f_full_service_announcement
	 * 
	 */
	public void setFullServiceAnnouncement(Byte fullServiceAnnouncement) {
		this.fullServiceAnnouncement = fullServiceAnnouncement;
	}
}
