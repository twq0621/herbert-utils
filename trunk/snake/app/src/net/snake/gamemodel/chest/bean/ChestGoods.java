package net.snake.gamemodel.chest.bean;

import net.snake.ibatis.IbatisEntity;

public class ChestGoods implements IbatisEntity {

	
	private int pingzhi = 0;// 品质附加条数
	
	public int getPingzhi() {
		return pingzhi;
	}

	public void setPingzhi(int pingzhi) {
		this.pingzhi = pingzhi;
	}

	/**
	 * 主键(真) t_chest_goods.f_chest_goods_id
	 * 
	 */
	private Integer chestGoodsId;
	
	private Integer strengthenCount;
	/**
	 * 宝箱内容表id t_chest_goods.f_chest_resources_id
	 * 
	 */
	private String chestResourcesId;
	/**
	 * 宝箱类别 t_chest_goods.f_goodmodel_type
	 * 
	 */
	private Integer goodmodelType;
	/**
	 * 主键 t_chest_goods.f_id
	 * 
	 */
	private String id;
	/**
	 * 人物角色表主键 t_chest_goods.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 道具表主键 t_chest_goods.f_goodmodel_id
	 * 
	 */
	private Integer goodmodelId;
	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599  //镶嵌位置:800-899 需要关联f_in_equip_id  //仓库里:1000-1999仓库里2000-2100摊位上 t_chest_goods.f_position
	 * 
	 */
	private Short position;
	/**
	 * 在那匹马上  0表示不在马身上 t_chest_goods.f_in_horse_id
	 * 
	 */
	private Integer inHorseId;
	/**
	 * 道具的数量 t_chest_goods.f_count
	 * 
	 */
	private Integer count;
	/**
	 * 是否绑定 1绑定 0不绑定 t_chest_goods.f_bind
	 * 
	 */
	private Byte bind;
	/**
	 * 快捷栏下标  快捷栏700-750 t_chest_goods.f_quickbarindex
	 * 
	 */
	private Integer quickbarindex;
	/**
	 * 当前持久 t_chest_goods.f_curr_durability
	 * 
	 */
	private Integer currDurability;
	/**
	 * 最大耐久 t_chest_goods.f_max_durability
	 * 
	 */
	private Integer maxDurability;
	/**
	 * 是否显示  t_chest_goods.f_is_show
	 * 
	 */
	private Byte isShow;
	/**
	 * 是否已经使用 t_chest_goods.f_is_use
	 * 
	 */
	private Byte isUse;
	/**
	 * 基础属性的说明 -- 装备使用(类型;类型;) t_chest_goods.f_base_desc
	 * 
	 */
	private String baseDesc;
	/**
	 * 天生属性增加率(1/10000) t_chest_goods.f_born_lv
	 * 
	 */
	private Integer bornLv;
	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_chest_goods.f_strengthen_desc
	 * 
	 */
	private String strengthenDesc;
	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_chest_goods.f_addition_desc
	 * 
	 */
	private String additionDesc;
	/**
	 * （宝石模型id;宝石模型id;） t_chest_goods.f_in_equip_id
	 * 
	 */
	private String inEquipId;
	/**
	 * 放到摊位上的铜币价格 t_chest_goods.f_stall_copper
	 * 
	 */
	private Integer stallCopper;
	/**
	 * 放到摊位上时的元宝价格 t_chest_goods.f_stall_ingot
	 * 
	 */
	private Integer stallIngot;

	/**
	 * 主键(真) t_chest_goods.f_chest_goods_id
	 * @return  the value of t_chest_goods.f_chest_goods_id
	 * 
	 */
	public Integer getChestGoodsId() {
		return chestGoodsId;
	}

	/**
	 * 主键(真) t_chest_goods.f_chest_goods_id
	 * @param chestGoodsId  the value for t_chest_goods.f_chest_goods_id
	 * 
	 */
	public void setChestGoodsId(Integer chestGoodsId) {
		this.chestGoodsId = chestGoodsId;
	}

	/**
	 * 宝箱内容表id t_chest_goods.f_chest_resources_id
	 * @return  the value of t_chest_goods.f_chest_resources_id
	 * 
	 */
	public String getChestResourcesId() {
		return chestResourcesId;
	}

	/**
	 * 宝箱内容表id t_chest_goods.f_chest_resources_id
	 * @param chestResourcesId  the value for t_chest_goods.f_chest_resources_id
	 * 
	 */
	public void setChestResourcesId(String chestResourcesId) {
		this.chestResourcesId = chestResourcesId;
	}

	/**
	 * 宝箱类别 t_chest_goods.f_goodmodel_type
	 * @return  the value of t_chest_goods.f_goodmodel_type
	 * 
	 */
	public Integer getGoodmodelType() {
		return goodmodelType;
	}

	/**
	 * 宝箱类别 t_chest_goods.f_goodmodel_type
	 * @param goodmodelType  the value for t_chest_goods.f_goodmodel_type
	 * 
	 */
	public void setGoodmodelType(Integer goodmodelType) {
		this.goodmodelType = goodmodelType;
	}

	/**
	 * 主键 t_chest_goods.f_id
	 * @return  the value of t_chest_goods.f_id
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键 t_chest_goods.f_id
	 * @param id  the value for t_chest_goods.f_id
	 * 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 人物角色表主键 t_chest_goods.f_character_id
	 * @return  the value of t_chest_goods.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 人物角色表主键 t_chest_goods.f_character_id
	 * @param characterId  the value for t_chest_goods.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 道具表主键 t_chest_goods.f_goodmodel_id
	 * @return  the value of t_chest_goods.f_goodmodel_id
	 * 
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 道具表主键 t_chest_goods.f_goodmodel_id
	 * @param goodmodelId  the value for t_chest_goods.f_goodmodel_id
	 * 
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599  //镶嵌位置:800-899 需要关联f_in_equip_id  //仓库里:1000-1999仓库里2000-2100摊位上 t_chest_goods.f_position
	 * @return  the value of t_chest_goods.f_position
	 * 
	 */
	public Short getPosition() {
		return position;
	}

	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599  //镶嵌位置:800-899 需要关联f_in_equip_id  //仓库里:1000-1999仓库里2000-2100摊位上 t_chest_goods.f_position
	 * @param position  the value for t_chest_goods.f_position
	 * 
	 */
	public void setPosition(Short position) {
		this.position = position;
	}

	/**
	 * 在那匹马上  0表示不在马身上 t_chest_goods.f_in_horse_id
	 * @return  the value of t_chest_goods.f_in_horse_id
	 * 
	 */
	public Integer getInHorseId() {
		return inHorseId;
	}

	/**
	 * 在那匹马上  0表示不在马身上 t_chest_goods.f_in_horse_id
	 * @param inHorseId  the value for t_chest_goods.f_in_horse_id
	 * 
	 */
	public void setInHorseId(Integer inHorseId) {
		this.inHorseId = inHorseId;
	}

	/**
	 * 道具的数量 t_chest_goods.f_count
	 * @return  the value of t_chest_goods.f_count
	 * 
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 道具的数量 t_chest_goods.f_count
	 * @param count  the value for t_chest_goods.f_count
	 * 
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 是否绑定 1绑定 0不绑定 t_chest_goods.f_bind
	 * @return  the value of t_chest_goods.f_bind
	 * 
	 */
	public Byte getBind() {
		return bind;
	}

	/**
	 * 是否绑定 1绑定 0不绑定 t_chest_goods.f_bind
	 * @param bind  the value for t_chest_goods.f_bind
	 * 
	 */
	public void setBind(Byte bind) {
		this.bind = bind;
	}

	/**
	 * 快捷栏下标  快捷栏700-750 t_chest_goods.f_quickbarindex
	 * @return  the value of t_chest_goods.f_quickbarindex
	 * 
	 */
	public Integer getQuickbarindex() {
		return quickbarindex;
	}

	/**
	 * 快捷栏下标  快捷栏700-750 t_chest_goods.f_quickbarindex
	 * @param quickbarindex  the value for t_chest_goods.f_quickbarindex
	 * 
	 */
	public void setQuickbarindex(Integer quickbarindex) {
		this.quickbarindex = quickbarindex;
	}

	/**
	 * 当前持久 t_chest_goods.f_curr_durability
	 * @return  the value of t_chest_goods.f_curr_durability
	 * 
	 */
	public Integer getCurrDurability() {
		return currDurability;
	}

	/**
	 * 当前持久 t_chest_goods.f_curr_durability
	 * @param currDurability  the value for t_chest_goods.f_curr_durability
	 * 
	 */
	public void setCurrDurability(Integer currDurability) {
		this.currDurability = currDurability;
	}

	/**
	 * 最大耐久 t_chest_goods.f_max_durability
	 * @return  the value of t_chest_goods.f_max_durability
	 * 
	 */
	public Integer getMaxDurability() {
		return maxDurability;
	}

	/**
	 * 最大耐久 t_chest_goods.f_max_durability
	 * @param maxDurability  the value for t_chest_goods.f_max_durability
	 * 
	 */
	public void setMaxDurability(Integer maxDurability) {
		this.maxDurability = maxDurability;
	}

	/**
	 * 是否显示  t_chest_goods.f_is_show
	 * @return  the value of t_chest_goods.f_is_show
	 * 
	 */
	public Byte getIsShow() {
		return isShow;
	}

	/**
	 * 是否显示  t_chest_goods.f_is_show
	 * @param isShow  the value for t_chest_goods.f_is_show
	 * 
	 */
	public void setIsShow(Byte isShow) {
		this.isShow = isShow;
	}

	/**
	 * 是否已经使用 t_chest_goods.f_is_use
	 * @return  the value of t_chest_goods.f_is_use
	 * 
	 */
	public Byte getIsUse() {
		return isUse;
	}

	/**
	 * 是否已经使用 t_chest_goods.f_is_use
	 * @param isUse  the value for t_chest_goods.f_is_use
	 * 
	 */
	public void setIsUse(Byte isUse) {
		this.isUse = isUse;
	}

	/**
	 * 基础属性的说明 -- 装备使用(类型;类型;) t_chest_goods.f_base_desc
	 * @return  the value of t_chest_goods.f_base_desc
	 * 
	 */
	public String getBaseDesc() {
		return baseDesc;
	}

	/**
	 * 基础属性的说明 -- 装备使用(类型;类型;) t_chest_goods.f_base_desc
	 * @param baseDesc  the value for t_chest_goods.f_base_desc
	 * 
	 */
	public void setBaseDesc(String baseDesc) {
		this.baseDesc = baseDesc;
	}

	/**
	 * 天生属性增加率(1/10000) t_chest_goods.f_born_lv
	 * @return  the value of t_chest_goods.f_born_lv
	 * 
	 */
	public Integer getBornLv() {
		return bornLv;
	}

	/**
	 * 天生属性增加率(1/10000) t_chest_goods.f_born_lv
	 * @param bornLv  the value for t_chest_goods.f_born_lv
	 * 
	 */
	public void setBornLv(Integer bornLv) {
		this.bornLv = bornLv;
	}

	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_chest_goods.f_strengthen_desc
	 * @return  the value of t_chest_goods.f_strengthen_desc
	 * 
	 */
	public String getStrengthenDesc() {
		return strengthenDesc;
	}

	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_chest_goods.f_strengthen_desc
	 * @param strengthenDesc  the value for t_chest_goods.f_strengthen_desc
	 * 
	 */
	public void setStrengthenDesc(String strengthenDesc) {
		this.strengthenDesc = strengthenDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_chest_goods.f_addition_desc
	 * @return  the value of t_chest_goods.f_addition_desc
	 * 
	 */
	public String getAdditionDesc() {
		return additionDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_chest_goods.f_addition_desc
	 * @param additionDesc  the value for t_chest_goods.f_addition_desc
	 * 
	 */
	public void setAdditionDesc(String additionDesc) {
		this.additionDesc = additionDesc;
	}

	/**
	 * （宝石模型id;宝石模型id;） t_chest_goods.f_in_equip_id
	 * @return  the value of t_chest_goods.f_in_equip_id
	 * 
	 */
	public String getInEquipId() {
		return inEquipId;
	}

	/**
	 * （宝石模型id;宝石模型id;） t_chest_goods.f_in_equip_id
	 * @param inEquipId  the value for t_chest_goods.f_in_equip_id
	 * 
	 */
	public void setInEquipId(String inEquipId) {
		this.inEquipId = inEquipId;
	}

	/**
	 * 放到摊位上的铜币价格 t_chest_goods.f_stall_copper
	 * @return  the value of t_chest_goods.f_stall_copper
	 * 
	 */
	public Integer getStallCopper() {
		return stallCopper;
	}

	/**
	 * 放到摊位上的铜币价格 t_chest_goods.f_stall_copper
	 * @param stallCopper  the value for t_chest_goods.f_stall_copper
	 * 
	 */
	public void setStallCopper(Integer stallCopper) {
		this.stallCopper = stallCopper;
	}

	/**
	 * 放到摊位上时的元宝价格 t_chest_goods.f_stall_ingot
	 * @return  the value of t_chest_goods.f_stall_ingot
	 * 
	 */
	public Integer getStallIngot() {
		return stallIngot;
	}

	/**
	 * 放到摊位上时的元宝价格 t_chest_goods.f_stall_ingot
	 * @param stallIngot  the value for t_chest_goods.f_stall_ingot
	 * 
	 */
	public void setStallIngot(Integer stallIngot) {
		this.stallIngot = stallIngot;
	}
	
	/**
	 * 强化成功的次数，9阶一品，一共5品，所以该数值最多为45
	 * 
	 * @return
	 */
	public Integer getStrengthenCount() {
		return strengthenCount==null?0:strengthenCount;
	}

	/**
	 * 强化成功的次数，9阶一品，一共5品，所以该数值最多为45
	 * 
	 * @param strengthenCount
	 */
	public void setStrengthenCount(Integer strengthenCount) {
		this.strengthenCount = strengthenCount;
	}
}
