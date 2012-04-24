package net.snake.gamemodel.goods.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class GoodsDataEntry implements IbatisEntity {

	/**
	 * 主键 t_character_goods.f_id
	 * 
	 * 
	 */
	private String id;
	/**
	 * 人物角色表主键 t_character_goods.f_character_id
	 * 
	 * 
	 */
	private Integer characterId;
	/**
	 * 道具表主键 t_character_goods.f_goodmodel_id
	 * 
	 * 
	 */
	private Integer goodmodelId;
	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599 //镶嵌位置:800-899 需要关联f_in_equip_id //仓库里:1000-1999仓库里2000-2100摊位上 t_character_goods.f_position
	 * 
	 * 
	 */
	private Short position;
	/**
	 * 在那匹马上 0表示不在马身上 t_character_goods.f_in_horse_id
	 * 
	 * 
	 */
	private Integer inHorseId;
	/**
	 * 道具的数量 t_character_goods.f_count
	 * 
	 * 
	 */
	private Integer count;
	/**
	 * 是否绑定 1绑定 0不绑定 t_character_goods.f_bind
	 * 
	 * 
	 */
	private Byte bind;
	/**
	 * 快捷栏下标 快捷栏700-750 t_character_goods.f_quickbarindex
	 * 
	 * 
	 */
	private Integer quickbarindex;
	/**
	 * 当前持久 t_character_goods.f_curr_durability
	 * 
	 * 
	 */
	private Integer currDurability;
	/**
	 * 最大耐久 t_character_goods.f_max_durability
	 * 
	 * 
	 */
	private Integer maxDurability;
	/**
	 * 是否显示 t_character_goods.f_is_show
	 * 
	 * 
	 */
	private Byte isShow;
	/**
	 * 是否已经使用 t_character_goods.f_is_use
	 * 
	 * 
	 */
	private Byte isUse;
	/**
	 * 基础属性的说明 -- 装备使用(类型,天生加成;类型,天生加成;) t_character_goods.f_base_desc
	 * 
	 * 
	 */
	private String baseDesc;
	/**
	 * 天生属性增加率(1/10000) t_character_goods.f_born_lv
	 * 
	 * 
	 */
	private Integer bornLv;
	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_character_goods.f_strengthen_desc
	 * 
	 * 
	 */
	private String strengthenDesc;

	/**
	 * 强化成功的次数，9阶一品，一共5品，所以该数值最多为45
	 */
	private Integer strengthenCount;
	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_character_goods.f_addition_desc
	 * 
	 * 
	 */
	private String additionDesc;
	/**
	 * （宝石模型id;宝石模型id;） t_character_goods.f_in_equip_id
	 * 
	 * 
	 */
	private String inEquipId;
	/**
	 * 放到摊位上的铜币价格 t_character_goods.f_stall_copper
	 * 
	 * 
	 */
	private Integer stallCopper;
	/**
	 * 放到摊位上时的元宝价格 t_character_goods.f_stall_ingot
	 * 
	 * 
	 */
	private Integer stallIngot;
	/**
	 * 充气娃娃所属角色（是由哪个角色充满的） t_character_goods.f_chongqi_owner_id
	 * 
	 * 
	 */
	private Integer chongqiOwnerId;
	/**
	 * (充气娃娃)所属角色名 t_character_goods.f_chongqi_owner_name
	 * 
	 * 
	 */
	private String chongqiOwnerName;
	/**
	 * 过期时间 t_character_goods.f_last_date
	 * 
	 * 
	 */
	private Date lastDate;
	/**
	 * 舍利子用(对应的武功id) t_character_goods.f_wugong_id
	 * 
	 * 
	 */
	private Integer wugongId;
	/**
	 * (舍利子id,武功id,武功层数,是否溶血; 舍利子id,武功id,武功层数,是否溶血;) t_character_goods.f_shelizhi_in_equip_id
	 * 
	 * 
	 */
	private String shelizhiInEquipId;

	/**
	 * 附加的图腾属性(格式: 图腾刻画者;图腾id; (类型,区间的位置;类型,区间的位置;)*)（类型:20攻击力增强21反弹伤害22忽视防御23伤害减免24暗器免伤） t_character_goods.f_totem
	 * 
	 * 
	 */
	private String totem;

	/**
	 * 离婚玩家男方名字 t_character_goods.f_male_name
	 * 
	 * 
	 */
	private String maleName;
	/**
	 * 离婚玩家男方名字 t_character_goods.f_female_name
	 * 
	 * 
	 */
	private String femaleName;
	/**
	 * 结婚日期 t_character_goods.f_couple_date
	 * 
	 * 
	 */
	private Date coupleDate;
	/**
	 * 装备天生强化突破的次数 t_character_goods.f_born_strengthen_cnt
	 * 
	 * 
	 */
	private Integer bornStrengthenCnt;
	/**
	 * 宝石自身镶嵌过的附加属性(属性类型;)* t_character_goods.f_strone_addproperty
	 * 
	 * 
	 */
	private String stroneAddproperty;

	/**
	 * 是否为玩家自制道具，0否，1是
	 */
	private byte isHomemade;

	/**
	 * 购买的类型，0普通，1商城游戏币
	 */
	private byte buyType = 0;

	/**
	 * 从商城购买这个道具的价格
	 */
	private int buyPrice = 0;
	/**
	 * 主键 t_character_goods.f_id
	 * 
	 * @return the value of t_character_goods.f_id
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键 t_character_goods.f_id
	 * 
	 * @param id
	 *            the value for t_character_goods.f_id
	 * 
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 人物角色表主键 t_character_goods.f_character_id
	 * 
	 * @return the value of t_character_goods.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 人物角色表主键 t_character_goods.f_character_id
	 * 
	 * @param characterId
	 *            the value for t_character_goods.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 道具表主键 t_character_goods.f_goodmodel_id
	 * 
	 * @return the value of t_character_goods.f_goodmodel_id
	 * 
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 道具表主键 t_character_goods.f_goodmodel_id
	 * 
	 * @param goodmodelId
	 *            the value for t_character_goods.f_goodmodel_id
	 * 
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599 //镶嵌位置:800-899 需要关联f_in_equip_id //仓库里:1000-1999仓库里2000-2100摊位上 t_character_goods.f_position
	 * 
	 * @return the value of t_character_goods.f_position
	 * 
	 */
	public Short getPosition() {
		return position;
	}

	/**
	 * //身上的:1-13 <br>
	 * //坐骑上:21-24 需要关联f_in_horse_id <br>
	 * //包裹里:100-599 <br>
	 * //镶嵌位置:800-899 需要关联f_in_equip_id <br>
	 * //仓库里:1000-1999仓库里 <br>
	 * 2000-2100摊位上 t_character_goods.f_position <br>
	 * 
	 * @param position
	 *            the value for t_character_goods.f_position
	 */
	public void setPosition(Short position) {
		this.position = position;
	}

	/**
	 * 在那匹马上 0表示不在马身上 t_character_goods.f_in_horse_id
	 * 
	 * @return the value of t_character_goods.f_in_horse_id
	 * 
	 */
	public Integer getInHorseId() {
		return inHorseId;
	}

	/**
	 * 在那匹马上 0表示不在马身上 t_character_goods.f_in_horse_id
	 * 
	 * @param inHorseId
	 *            the value for t_character_goods.f_in_horse_id
	 * 
	 */
	public void setInHorseId(Integer inHorseId) {
		this.inHorseId = inHorseId;
	}

	/**
	 * 道具的数量 t_character_goods.f_count
	 * 
	 * @return the value of t_character_goods.f_count
	 * 
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 道具的数量 t_character_goods.f_count
	 * 
	 * @param count
	 *            the value for t_character_goods.f_count
	 * 
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 是否绑定 1绑定 0不绑定 t_character_goods.f_bind
	 * 
	 * @return the value of t_character_goods.f_bind
	 * 
	 */
	public Byte getBind() {
		return bind;
	}

	/**
	 * 是否绑定 1绑定 0不绑定 t_character_goods.f_bind
	 * 
	 * @param bind
	 *            the value for t_character_goods.f_bind
	 * 
	 */
	public void setBind(Byte bind) {
		this.bind = bind;
	}

	/**
	 * 快捷栏下标 快捷栏700-750 t_character_goods.f_quickbarindex
	 * 
	 * @return the value of t_character_goods.f_quickbarindex
	 * 
	 */
	public Integer getQuickbarindex() {
		return quickbarindex;
	}

	/**
	 * 快捷栏下标 快捷栏700-750 t_character_goods.f_quickbarindex
	 * 
	 * @param quickbarindex
	 *            the value for t_character_goods.f_quickbarindex
	 * 
	 */
	public void setQuickbarindex(Integer quickbarindex) {
		this.quickbarindex = quickbarindex;
	}

	/**
	 * 当前持久 t_character_goods.f_curr_durability
	 * 
	 * @return the value of t_character_goods.f_curr_durability
	 * 
	 */
	public Integer getCurrDurability() {
		return currDurability;
	}

	/**
	 * 当前持久 t_character_goods.f_curr_durability
	 * 
	 * @param currDurability
	 *            the value for t_character_goods.f_curr_durability
	 * 
	 */
	public void setCurrDurability(Integer currDurability) {
		this.currDurability = currDurability;
	}

	/**
	 * 最大耐久 t_character_goods.f_max_durability
	 * 
	 * @return the value of t_character_goods.f_max_durability
	 * 
	 */
	public Integer getMaxDurability() {
		return maxDurability;
	}

	/**
	 * 最大耐久 t_character_goods.f_max_durability
	 * 
	 * @param maxDurability
	 *            the value for t_character_goods.f_max_durability
	 * 
	 */
	public void setMaxDurability(Integer maxDurability) {
		this.maxDurability = maxDurability;
	}

	/**
	 * 是否显示 t_character_goods.f_is_show
	 * 
	 * @return the value of t_character_goods.f_is_show
	 * 
	 */
	public Byte getIsShow() {
		return isShow;
	}

	/**
	 * 是否显示 t_character_goods.f_is_show
	 * 
	 * @param isShow
	 *            the value for t_character_goods.f_is_show
	 * 
	 */
	public void setIsShow(Byte isShow) {
		this.isShow = isShow;
	}

	/**
	 * 是否已经使用 t_character_goods.f_is_use
	 * 
	 * @return the value of t_character_goods.f_is_use
	 * 
	 */
	public Byte getIsUse() {
		return isUse;
	}

	/**
	 * 是否已经使用 t_character_goods.f_is_use
	 * 
	 * @param isUse
	 *            the value for t_character_goods.f_is_use
	 * 
	 */
	public void setIsUse(Byte isUse) {
		this.isUse = isUse;
	}

	/**
	 * 基础属性的说明 -- 装备使用(类型,天生加成;类型,天生加成;) t_character_goods.f_base_desc
	 * 
	 * @return the value of t_character_goods.f_base_desc
	 * 
	 */
	public String getBaseDesc() {
		return baseDesc;
	}

	/**
	 * 基础属性的说明 -- 装备使用(类型,天生加成;类型,天生加成;)t_character_goods.f_base_desc
	 * 
	 * @param baseDesc
	 *            the value for t_character_goods.f_base_desc
	 * 
	 */
	public void setBaseDesc(String baseDesc) {
		this.baseDesc = baseDesc;
	}

	/**
	 * 天生属性增加率(1/10000) t_character_goods.f_born_lv
	 * 
	 * @return the value of t_character_goods.f_born_lv
	 * 
	 */
	public Integer getBornLv() {
		return bornLv;
	}

	/**
	 * 天生属性增加率(1/10000) t_character_goods.f_born_lv
	 * 
	 * @param bornLv
	 *            the value for t_character_goods.f_born_lv
	 * 
	 */
	public void setBornLv(Integer bornLv) {
		this.bornLv = bornLv;
	}

	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_character_goods.f_strengthen_desc
	 * 
	 * @return the value of t_character_goods.f_strengthen_desc
	 * 
	 */
	public String getStrengthenDesc() {
		return strengthenDesc;
	}

	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_character_goods.f_strengthen_desc
	 * 
	 * @param strengthenDesc
	 *            the value for t_character_goods.f_strengthen_desc
	 * 
	 */
	public void setStrengthenDesc(String strengthenDesc) {
		this.strengthenDesc = strengthenDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_character_goods.f_addition_desc
	 * 
	 * @return the value of t_character_goods.f_addition_desc
	 * 
	 */
	public String getAdditionDesc() {
		return additionDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_character_goods.f_addition_desc
	 * 
	 * @param additionDesc
	 *            the value for t_character_goods.f_addition_desc
	 * 
	 */
	public void setAdditionDesc(String additionDesc) {
		this.additionDesc = additionDesc;
	}

	/**
	 * （宝石模型id;宝石模型id;） t_character_goods.f_in_equip_id
	 * 
	 * @return the value of t_character_goods.f_in_equip_id
	 * 
	 */
	public String getInEquipId() {
		return inEquipId;
	}

	/**
	 * （宝石模型id;宝石模型id;） t_character_goods.f_in_equip_id
	 * 
	 * @param inEquipId
	 *            the value for t_character_goods.f_in_equip_id
	 * 
	 */
	public void setInEquipId(String inEquipId) {
		this.inEquipId = inEquipId;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_goods.f_stall_copper
	 * 
	 * @return the value of t_character_goods.f_stall_copper
	 * 
	 */
	public Integer getStallCopper() {
		return stallCopper;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_goods.f_stall_copper
	 * 
	 * @param stallCopper
	 *            the value for t_character_goods.f_stall_copper
	 * 
	 */
	public void setStallCopper(Integer stallCopper) {
		this.stallCopper = stallCopper;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_goods.f_stall_ingot
	 * 
	 * @return the value of t_character_goods.f_stall_ingot
	 * 
	 */
	public Integer getStallIngot() {
		return stallIngot;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_goods.f_stall_ingot
	 * 
	 * @param stallIngot
	 *            the value for t_character_goods.f_stall_ingot
	 * 
	 */
	public void setStallIngot(Integer stallIngot) {
		this.stallIngot = stallIngot;
	}

	/**
	 * 充气娃娃所属角色（是由哪个角色充满的） t_character_goods.f_chongqi_owner_id
	 * 
	 * @return the value of t_character_goods.f_chongqi_owner_id
	 * 
	 */
	public Integer getChongqiOwnerId() {
		return chongqiOwnerId;
	}

	/**
	 * 充气娃娃所属角色（是由哪个角色充满的） t_character_goods.f_chongqi_owner_id
	 * 
	 * @param chongqiOwnerId
	 *            the value for t_character_goods.f_chongqi_owner_id
	 * 
	 */
	public void setChongqiOwnerId(Integer chongqiOwnerId) {
		this.chongqiOwnerId = chongqiOwnerId;
	}

	/**
	 * (充气娃娃)所属角色名 t_character_goods.f_chongqi_owner_name
	 * 
	 * @return the value of t_character_goods.f_chongqi_owner_name
	 * 
	 */
	public String getChongqiOwnerName() {
		return chongqiOwnerName;
	}

	/**
	 * (充气娃娃)所属角色名 t_character_goods.f_chongqi_owner_name
	 * 
	 * @param chongqiOwnerName
	 *            the value for t_character_goods.f_chongqi_owner_name
	 * 
	 */
	public void setChongqiOwnerName(String chongqiOwnerName) {
		this.chongqiOwnerName = chongqiOwnerName;
	}

	/**
	 * 过期时间 t_character_goods.f_last_date
	 * 
	 * @return the value of t_character_goods.f_last_date
	 * 
	 */
	public Date getLastDate() {
		return lastDate;
	}

	/**
	 * 过期时间 t_character_goods.f_last_date
	 * 
	 * @param lastDate
	 *            the value for t_character_goods.f_last_date
	 * 
	 */
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	/**
	 * 舍利子用(对应的武功id) t_character_goods.f_wugong_id
	 * 
	 * @return the value of t_character_goods.f_wugong_id
	 * 
	 */
	public Integer getWugongId() {
		return wugongId;
	}

	/**
	 * 舍利子用(对应的武功id) t_character_goods.f_wugong_id
	 * 
	 * @param wugongId
	 *            the value for t_character_goods.f_wugong_id
	 * 
	 */
	public void setWugongId(Integer wugongId) {
		this.wugongId = wugongId;
	}

	/**
	 * (舍利子id,武功id,武功层数,是否溶血; 舍利子id,武功id,武功层数,是否溶血;) t_character_goods.f_shelizhi_in_equip_id
	 * 
	 * @return the value of t_character_goods.f_shelizhi_in_equip_id
	 * 
	 */
	public String getShelizhiInEquipId() {
		return shelizhiInEquipId;
	}

	/**
	 * (舍利子id,武功id,武功层数,是否溶血; 舍利子id,武功id,武功层数,是否溶血;) t_character_goods.f_shelizhi_in_equip_id
	 * 
	 * @param shelizhiInEquipId
	 *            the value for t_character_goods.f_shelizhi_in_equip_id
	 * 
	 */
	public void setShelizhiInEquipId(String shelizhiInEquipId) {
		this.shelizhiInEquipId = shelizhiInEquipId;
	}

	/**
	 * 附加的图腾属性(格式: 图腾刻画者;图腾id; (类型,区间的位置;类型,区间的位置;)*)（类型:20攻击力增强21反弹伤害22忽视防御23伤害减免24暗器免伤） t_character_goods.f_totem
	 * 
	 * @return the value of t_character_goods.f_totem
	 * 
	 */
	public String getTotem() {
		return totem;
	}

	/**
	 * 附加的图腾属性(格式: 图腾刻画者;图腾id; (类型,区间的位置;类型,区间的位置;)*)（类型:20攻击力增强21反弹伤害22忽视防御23伤害减免24暗器免伤） t_character_goods.f_totem
	 * 
	 * @param totem
	 *            the value for t_character_goods.f_totem
	 * 
	 */
	public void setTotem(String totem) {
		this.totem = totem;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_male_name
	 * 
	 * @return the value of t_character_goods.f_male_name
	 * 
	 */
	public String getMaleName() {
		return maleName;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_male_name
	 * 
	 * @param maleName
	 *            the value for t_character_goods.f_male_name
	 * 
	 */
	public void setMaleName(String maleName) {
		this.maleName = maleName;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_female_name
	 * 
	 * @return the value of t_character_goods.f_female_name
	 * 
	 */
	public String getFemaleName() {
		return femaleName;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_female_name
	 * 
	 * @param femaleName
	 *            the value for t_character_goods.f_female_name
	 * 
	 */
	public void setFemaleName(String femaleName) {
		this.femaleName = femaleName;
	}

	/**
	 * 结婚日期 t_character_goods.f_couple_date
	 * 
	 * @return the value of t_character_goods.f_couple_date
	 * 
	 */
	public Date getCoupleDate() {
		return coupleDate;
	}

	/**
	 * 结婚日期 t_character_goods.f_couple_date
	 * 
	 * @param coupleDate
	 *            the value for t_character_goods.f_couple_date
	 * 
	 */
	public void setCoupleDate(Date coupleDate) {
		this.coupleDate = coupleDate;
	}

	/**
	 * 装备天生强化突破的次数 t_character_goods.f_born_strengthen_cnt
	 * 
	 * @return the value of t_character_goods.f_born_strengthen_cnt
	 * 
	 */
	public Integer getBornStrengthenCnt() {
		return bornStrengthenCnt;
	}

	/**
	 * 装备天生强化突破的次数 t_character_goods.f_born_strengthen_cnt
	 * 
	 * @param bornStrengthenCnt
	 *            the value for t_character_goods.f_born_strengthen_cnt
	 * 
	 */
	public void setBornStrengthenCnt(Integer bornStrengthenCnt) {
		this.bornStrengthenCnt = bornStrengthenCnt;
	}

	/**
	 * 宝石自身镶嵌过的附加属性(属性类型;)* t_character_goods.f_strone_addproperty
	 * 
	 * @return the value of t_character_goods.f_strone_addproperty
	 * 
	 */
	public String getStroneAddproperty() {
		return stroneAddproperty;
	}

	/**
	 * 宝石自身镶嵌过的附加属性(属性类型;)* t_character_goods.f_strone_addproperty
	 * 
	 * @param stroneAddproperty
	 *            the value for t_character_goods.f_strone_addproperty
	 * 
	 */
	public void setStroneAddproperty(String stroneAddproperty) {
		this.stroneAddproperty = stroneAddproperty;
	}

	/**
	 * 强化成功的次数，9阶一品，一共5品，所以该数值最多为45
	 * 
	 * @return
	 */
	public Integer getStrengthenCount() {
		return strengthenCount == null ? 1 : strengthenCount;
	}

	/**
	 * 强化成功的次数，9阶一品，一共5品，所以该数值最多为45
	 * 
	 * @param strengthenCount
	 */
	public void setStrengthenCount(Integer strengthenCount) {
		this.strengthenCount = strengthenCount;
	}

	/**
	 * 是否为玩家自制道具，0否，1是
	 * 
	 * @return
	 */
	public byte getIsHomemade() {
		return isHomemade;
	}

	/**
	 * 是否为玩家自制道具，0否，1是
	 * 
	 * @param isHomemade
	 */
	public void setIsHomemade(byte isHomemade) {
		this.isHomemade = isHomemade;
	}

	/**
	 * 购买的类型，0普通，1商城游戏币
	 * 
	 * @return
	 */
	public byte getBuyType() {
		return buyType;
	}

	/**
	 * 购买的类型，0普通，1商城游戏币
	 * 
	 * @param buyType
	 */
	public void setBuyType(byte buyType) {
		this.buyType = buyType;
	}

	/**
	 *  从商城购买这个道具的价格
	 * @return
	 */
	public int getBuyPrice() {
		return buyPrice;
	}

	/**
	 *  从商城购买这个道具的价格
	 * @param buyPrice
	 */
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}

}
