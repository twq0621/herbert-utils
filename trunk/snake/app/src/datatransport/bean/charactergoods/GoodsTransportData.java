package datatransport.bean.charactergoods;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class GoodsTransportData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(this.id == null ? "NaN" : this.id);
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.goodmodelId == null ? Integer.MIN_VALUE : this.goodmodelId);
		out.writeShort(this.position);
		out.writeInt(this.inHorseId == null ? Integer.MIN_VALUE : this.inHorseId);
		out.writeInt(this.count == null ? Integer.MIN_VALUE : this.count);
		out.writeByte(this.bind);
		out.writeInt(this.quickbarindex == null ? Integer.MIN_VALUE : this.quickbarindex);
		out.writeInt(this.currDurability == null ? Integer.MIN_VALUE : this.currDurability);
		out.writeInt(this.maxDurability == null ? Integer.MIN_VALUE : this.maxDurability);
		out.writeByte(this.isShow);
		out.writeByte(this.isUse);
		out.writeUTF(this.baseDesc == null ? "NaN" : this.baseDesc);
		out.writeInt(this.bornLv == null ? Integer.MIN_VALUE : this.bornLv);
		out.writeUTF(this.strengthenDesc == null ? "NaN" : this.strengthenDesc);
		out.writeUTF(this.additionDesc == null ? "NaN" : this.additionDesc);
		out.writeUTF(this.inEquipId == null ? "NaN" : this.inEquipId);
		out.writeInt(this.stallCopper == null ? Integer.MIN_VALUE : this.stallCopper);
		out.writeInt(this.stallIngot == null ? Integer.MIN_VALUE : this.stallIngot);
		out.writeInt(this.chongqiOwnerId == null ? Integer.MIN_VALUE : this.chongqiOwnerId);
		out.writeUTF(this.chongqiOwnerName == null ? "NaN" : this.chongqiOwnerName);
		out.writeObject(this.lastDate);
		out.writeInt(this.wugongId == null ? Integer.MIN_VALUE : this.wugongId);
		out.writeUTF(this.shelizhiInEquipId == null ? "NaN" : this.shelizhiInEquipId);
		out.writeInt(this.tupoCnt == null ? Integer.MIN_VALUE : this.tupoCnt);
		out.writeUTF(this.totem == null ? "NaN" : this.totem);
		out.writeByte(this.shelizhiRongxue == null ? 2 : this.shelizhiRongxue== true ? 1 : 0);
		out.writeUTF(this.totemUsrname == null ? "NaN" : this.totemUsrname);
		out.writeUTF(this.maleName == null ? "NaN" : this.maleName);
		out.writeUTF(this.femaleName == null ? "NaN" : this.femaleName);
		out.writeObject(this.coupleDate);
		out.writeInt(this.bornStrengthenCnt == null ? Integer.MIN_VALUE : this.bornStrengthenCnt);
		out.writeUTF(this.stroneAddproperty == null ? "NaN" : this.stroneAddproperty);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.id = in.readUTF();
		this.id = this.id.equals("NaN") ? null : this.id;
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.goodmodelId = in.readInt();
		this.goodmodelId = this.goodmodelId == Integer.MIN_VALUE ? null : this.goodmodelId;
		this.position = in.readShort();
		this.inHorseId = in.readInt();
		this.inHorseId = this.inHorseId == Integer.MIN_VALUE ? null : this.inHorseId;
		this.count = in.readInt();
		this.count = this.count == Integer.MIN_VALUE ? null : this.count;
		this.bind = in.readByte();
		this.quickbarindex = in.readInt();
		this.quickbarindex = this.quickbarindex == Integer.MIN_VALUE ? null : this.quickbarindex;
		this.currDurability = in.readInt();
		this.currDurability = this.currDurability == Integer.MIN_VALUE ? null : this.currDurability;
		this.maxDurability = in.readInt();
		this.maxDurability = this.maxDurability == Integer.MIN_VALUE ? null : this.maxDurability;
		this.isShow = in.readByte();
		this.isUse = in.readByte();
		this.baseDesc = in.readUTF();
		this.baseDesc = this.baseDesc.equals("NaN") ? null : this.baseDesc;
		this.bornLv = in.readInt();
		this.bornLv = this.bornLv == Integer.MIN_VALUE ? null : this.bornLv;
		this.strengthenDesc = in.readUTF();
		this.strengthenDesc = this.strengthenDesc.equals("NaN") ? null : this.strengthenDesc;
		this.additionDesc = in.readUTF();
		this.additionDesc = this.additionDesc.equals("NaN") ? null : this.additionDesc;
		this.inEquipId = in.readUTF();
		this.inEquipId = this.inEquipId.equals("NaN") ? null : this.inEquipId;
		this.stallCopper = in.readInt();
		this.stallCopper = this.stallCopper == Integer.MIN_VALUE ? null : this.stallCopper;
		this.stallIngot = in.readInt();
		this.stallIngot = this.stallIngot == Integer.MIN_VALUE ? null : this.stallIngot;
		this.chongqiOwnerId = in.readInt();
		this.chongqiOwnerId = this.chongqiOwnerId == Integer.MIN_VALUE ? null : this.chongqiOwnerId;
		this.chongqiOwnerName = in.readUTF();
		this.chongqiOwnerName = this.chongqiOwnerName.equals("NaN") ? null : this.chongqiOwnerName;
		this.lastDate = (Date)in.readObject();
		this.wugongId = in.readInt();
		this.wugongId = this.wugongId == Integer.MIN_VALUE ? null : this.wugongId;
		this.shelizhiInEquipId = in.readUTF();
		this.shelizhiInEquipId = this.shelizhiInEquipId.equals("NaN") ? null : this.shelizhiInEquipId;
		this.tupoCnt = in.readInt();
		this.tupoCnt = this.tupoCnt == Integer.MIN_VALUE ? null : this.tupoCnt;
		this.totem = in.readUTF();
		this.totem = this.totem.equals("NaN") ? null : this.totem;
		this.shelizhiRongxue = cv(in.readByte());
		this.totemUsrname = in.readUTF();
		this.totemUsrname = this.totemUsrname.equals("NaN") ? null : this.totemUsrname;
		this.maleName = in.readUTF();
		this.maleName = this.maleName.equals("NaN") ? null : this.maleName;
		this.femaleName = in.readUTF();
		this.femaleName = this.femaleName.equals("NaN") ? null : this.femaleName;
		this.coupleDate = (Date)in.readObject();
		this.bornStrengthenCnt = in.readInt();
		this.bornStrengthenCnt = this.bornStrengthenCnt == Integer.MIN_VALUE ? null : this.bornStrengthenCnt;
		this.stroneAddproperty = in.readUTF();
		this.stroneAddproperty = this.stroneAddproperty.equals("NaN") ? null : this.stroneAddproperty;
	}
	private Boolean cv(byte b) {
		if(b == 0){
			return false;
		}else
		if(b == 1){
			return true;
		}
		return null;
	}
	/**
	 * 主键 t_character_goods.f_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String id;
	/**
	 * 人物角色表主键 t_character_goods.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer characterId;
	/**
	 * 道具表主键 t_character_goods.f_goodmodel_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer goodmodelId;
	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599  //镶嵌位置:800-899 需要关联f_in_equip_id  //仓库里:1000-1999仓库里2000-2100摊位上 t_character_goods.f_position
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Short position;
	/**
	 * 在那匹马上  0表示不在马身上 t_character_goods.f_in_horse_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer inHorseId;
	/**
	 * 道具的数量 t_character_goods.f_count
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer count;
	/**
	 * 是否绑定 1绑定 0不绑定 t_character_goods.f_bind
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Byte bind;
	/**
	 * 快捷栏下标  快捷栏700-750 t_character_goods.f_quickbarindex
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer quickbarindex;
	/**
	 * 当前持久 t_character_goods.f_curr_durability
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer currDurability;
	/**
	 * 最大耐久 t_character_goods.f_max_durability
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer maxDurability;
	/**
	 * 是否显示  t_character_goods.f_is_show
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Byte isShow;
	/**
	 * 是否已经使用 t_character_goods.f_is_use
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Byte isUse;
	/**
	 * 基础属性的说明 -- 装备使用(类型;类型;) t_character_goods.f_base_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String baseDesc;
	/**
	 * 天生属性增加率(1/10000) t_character_goods.f_born_lv
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer bornLv;
	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_character_goods.f_strengthen_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String strengthenDesc;
	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_character_goods.f_addition_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String additionDesc;
	/**
	 * （宝石模型id;宝石模型id;） t_character_goods.f_in_equip_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String inEquipId;
	/**
	 * 放到摊位上的铜币价格 t_character_goods.f_stall_copper
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer stallCopper;
	/**
	 * 放到摊位上时的元宝价格 t_character_goods.f_stall_ingot
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer stallIngot;
	/**
	 * 充气娃娃所属角色（是由哪个角色充满的） t_character_goods.f_chongqi_owner_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer chongqiOwnerId;
	/**
	 * (充气娃娃)所属角色名 t_character_goods.f_chongqi_owner_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String chongqiOwnerName;
	/**
	 * 过期时间 t_character_goods.f_last_date
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Date lastDate;
	/**
	 * 舍利子用(对应的武功id) t_character_goods.f_wugong_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer wugongId;
	/**
	 * (舍利子id,武功id,武功层数,是否溶血; 舍利子id,武功id,武功层数,是否溶血;) t_character_goods.f_shelizhi_in_equip_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String shelizhiInEquipId;
	/**
	 * 舍利子或附身符升阶次数累计 t_character_goods.f_tupo_cnt
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer tupoCnt;
	/**
	 * 附加的图腾属性(格式:  图腾刻画者;图腾id; (类型,区间的位置;类型,区间的位置;)*)（类型:20攻击力增强21反弹伤害22忽视防御23伤害减免24暗器免伤） t_character_goods.f_totem
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String totem;
	/**
	 * 舍利子是否融血 t_character_goods.f_shelizhi_rongxue
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Boolean shelizhiRongxue;
	/**
	 * 图腾所属角色名 t_character_goods.f_totem_usrname
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String totemUsrname;
	/**
	 * 离婚玩家男方名字 t_character_goods.f_male_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String maleName;
	/**
	 * 离婚玩家男方名字 t_character_goods.f_female_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String femaleName;
	/**
	 * 结婚日期 t_character_goods.f_couple_date
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Date coupleDate;
	/**
	 * 装备天生强化突破的次数 t_character_goods.f_born_strengthen_cnt
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private Integer bornStrengthenCnt;
	/**
	 * 宝石自身镶嵌过的附加属性(属性类型,)* t_character_goods.f_strone_addproperty
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	private String stroneAddproperty;

	/**
	 * 主键 t_character_goods.f_id
	 * @return  the value of t_character_goods.f_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键 t_character_goods.f_id
	 * @param id  the value for t_character_goods.f_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 人物角色表主键 t_character_goods.f_character_id
	 * @return  the value of t_character_goods.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 人物角色表主键 t_character_goods.f_character_id
	 * @param characterId  the value for t_character_goods.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 道具表主键 t_character_goods.f_goodmodel_id
	 * @return  the value of t_character_goods.f_goodmodel_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getGoodmodelId() {
		return goodmodelId;
	}

	/**
	 * 道具表主键 t_character_goods.f_goodmodel_id
	 * @param goodmodelId  the value for t_character_goods.f_goodmodel_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setGoodmodelId(Integer goodmodelId) {
		this.goodmodelId = goodmodelId;
	}

	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599  //镶嵌位置:800-899 需要关联f_in_equip_id  //仓库里:1000-1999仓库里2000-2100摊位上 t_character_goods.f_position
	 * @return  the value of t_character_goods.f_position
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Short getPosition() {
		return position;
	}

	/**
	 * //身上的:1-13 //坐骑上:21-24 需要关联f_in_horse_id //包裹里:100-599  //镶嵌位置:800-899 需要关联f_in_equip_id  //仓库里:1000-1999仓库里2000-2100摊位上 t_character_goods.f_position
	 * @param position  the value for t_character_goods.f_position
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setPosition(Short position) {
		this.position = position;
	}

	/**
	 * 在那匹马上  0表示不在马身上 t_character_goods.f_in_horse_id
	 * @return  the value of t_character_goods.f_in_horse_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getInHorseId() {
		return inHorseId;
	}

	/**
	 * 在那匹马上  0表示不在马身上 t_character_goods.f_in_horse_id
	 * @param inHorseId  the value for t_character_goods.f_in_horse_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setInHorseId(Integer inHorseId) {
		this.inHorseId = inHorseId;
	}

	/**
	 * 道具的数量 t_character_goods.f_count
	 * @return  the value of t_character_goods.f_count
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 道具的数量 t_character_goods.f_count
	 * @param count  the value for t_character_goods.f_count
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 是否绑定 1绑定 0不绑定 t_character_goods.f_bind
	 * @return  the value of t_character_goods.f_bind
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Byte getBind() {
		return bind;
	}

	/**
	 * 是否绑定 1绑定 0不绑定 t_character_goods.f_bind
	 * @param bind  the value for t_character_goods.f_bind
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setBind(Byte bind) {
		this.bind = bind;
	}

	/**
	 * 快捷栏下标  快捷栏700-750 t_character_goods.f_quickbarindex
	 * @return  the value of t_character_goods.f_quickbarindex
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getQuickbarindex() {
		return quickbarindex;
	}

	/**
	 * 快捷栏下标  快捷栏700-750 t_character_goods.f_quickbarindex
	 * @param quickbarindex  the value for t_character_goods.f_quickbarindex
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setQuickbarindex(Integer quickbarindex) {
		this.quickbarindex = quickbarindex;
	}

	/**
	 * 当前持久 t_character_goods.f_curr_durability
	 * @return  the value of t_character_goods.f_curr_durability
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getCurrDurability() {
		return currDurability;
	}

	/**
	 * 当前持久 t_character_goods.f_curr_durability
	 * @param currDurability  the value for t_character_goods.f_curr_durability
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setCurrDurability(Integer currDurability) {
		this.currDurability = currDurability;
	}

	/**
	 * 最大耐久 t_character_goods.f_max_durability
	 * @return  the value of t_character_goods.f_max_durability
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getMaxDurability() {
		return maxDurability;
	}

	/**
	 * 最大耐久 t_character_goods.f_max_durability
	 * @param maxDurability  the value for t_character_goods.f_max_durability
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setMaxDurability(Integer maxDurability) {
		this.maxDurability = maxDurability;
	}

	/**
	 * 是否显示  t_character_goods.f_is_show
	 * @return  the value of t_character_goods.f_is_show
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Byte getIsShow() {
		return isShow;
	}

	/**
	 * 是否显示  t_character_goods.f_is_show
	 * @param isShow  the value for t_character_goods.f_is_show
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setIsShow(Byte isShow) {
		this.isShow = isShow;
	}

	/**
	 * 是否已经使用 t_character_goods.f_is_use
	 * @return  the value of t_character_goods.f_is_use
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Byte getIsUse() {
		return isUse;
	}

	/**
	 * 是否已经使用 t_character_goods.f_is_use
	 * @param isUse  the value for t_character_goods.f_is_use
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setIsUse(Byte isUse) {
		this.isUse = isUse;
	}

	/**
	 * 基础属性的说明 -- 装备使用(类型;类型;) t_character_goods.f_base_desc
	 * @return  the value of t_character_goods.f_base_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getBaseDesc() {
		return baseDesc;
	}

	/**
	 * 基础属性的说明 -- 装备使用(类型;类型;) t_character_goods.f_base_desc
	 * @param baseDesc  the value for t_character_goods.f_base_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setBaseDesc(String baseDesc) {
		this.baseDesc = baseDesc;
	}

	/**
	 * 天生属性增加率(1/10000) t_character_goods.f_born_lv
	 * @return  the value of t_character_goods.f_born_lv
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getBornLv() {
		return bornLv;
	}

	/**
	 * 天生属性增加率(1/10000) t_character_goods.f_born_lv
	 * @param bornLv  the value for t_character_goods.f_born_lv
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setBornLv(Integer bornLv) {
		this.bornLv = bornLv;
	}

	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_character_goods.f_strengthen_desc
	 * @return  the value of t_character_goods.f_strengthen_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getStrengthenDesc() {
		return strengthenDesc;
	}

	/**
	 * 强化等级实心描述(1,0,0,1,0) (0表示空心1表示实心) （装备使用） t_character_goods.f_strengthen_desc
	 * @param strengthenDesc  the value for t_character_goods.f_strengthen_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setStrengthenDesc(String strengthenDesc) {
		this.strengthenDesc = strengthenDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_character_goods.f_addition_desc
	 * @return  the value of t_character_goods.f_addition_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getAdditionDesc() {
		return additionDesc;
	}

	/**
	 * 附加属性的说明 -- 装备使用(类型，百分比；) t_character_goods.f_addition_desc
	 * @param additionDesc  the value for t_character_goods.f_addition_desc
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setAdditionDesc(String additionDesc) {
		this.additionDesc = additionDesc;
	}

	/**
	 * （宝石模型id;宝石模型id;） t_character_goods.f_in_equip_id
	 * @return  the value of t_character_goods.f_in_equip_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getInEquipId() {
		return inEquipId;
	}

	/**
	 * （宝石模型id;宝石模型id;） t_character_goods.f_in_equip_id
	 * @param inEquipId  the value for t_character_goods.f_in_equip_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setInEquipId(String inEquipId) {
		this.inEquipId = inEquipId;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_goods.f_stall_copper
	 * @return  the value of t_character_goods.f_stall_copper
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getStallCopper() {
		return stallCopper;
	}

	/**
	 * 放到摊位上的铜币价格 t_character_goods.f_stall_copper
	 * @param stallCopper  the value for t_character_goods.f_stall_copper
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setStallCopper(Integer stallCopper) {
		this.stallCopper = stallCopper;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_goods.f_stall_ingot
	 * @return  the value of t_character_goods.f_stall_ingot
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getStallIngot() {
		return stallIngot;
	}

	/**
	 * 放到摊位上时的元宝价格 t_character_goods.f_stall_ingot
	 * @param stallIngot  the value for t_character_goods.f_stall_ingot
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setStallIngot(Integer stallIngot) {
		this.stallIngot = stallIngot;
	}

	/**
	 * 充气娃娃所属角色（是由哪个角色充满的） t_character_goods.f_chongqi_owner_id
	 * @return  the value of t_character_goods.f_chongqi_owner_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getChongqiOwnerId() {
		return chongqiOwnerId;
	}

	/**
	 * 充气娃娃所属角色（是由哪个角色充满的） t_character_goods.f_chongqi_owner_id
	 * @param chongqiOwnerId  the value for t_character_goods.f_chongqi_owner_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setChongqiOwnerId(Integer chongqiOwnerId) {
		this.chongqiOwnerId = chongqiOwnerId;
	}

	/**
	 * (充气娃娃)所属角色名 t_character_goods.f_chongqi_owner_name
	 * @return  the value of t_character_goods.f_chongqi_owner_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getChongqiOwnerName() {
		return chongqiOwnerName;
	}

	/**
	 * (充气娃娃)所属角色名 t_character_goods.f_chongqi_owner_name
	 * @param chongqiOwnerName  the value for t_character_goods.f_chongqi_owner_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setChongqiOwnerName(String chongqiOwnerName) {
		this.chongqiOwnerName = chongqiOwnerName;
	}

	/**
	 * 过期时间 t_character_goods.f_last_date
	 * @return  the value of t_character_goods.f_last_date
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Date getLastDate() {
		return lastDate;
	}

	/**
	 * 过期时间 t_character_goods.f_last_date
	 * @param lastDate  the value for t_character_goods.f_last_date
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	/**
	 * 舍利子用(对应的武功id) t_character_goods.f_wugong_id
	 * @return  the value of t_character_goods.f_wugong_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getWugongId() {
		return wugongId;
	}

	/**
	 * 舍利子用(对应的武功id) t_character_goods.f_wugong_id
	 * @param wugongId  the value for t_character_goods.f_wugong_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setWugongId(Integer wugongId) {
		this.wugongId = wugongId;
	}

	/**
	 * (舍利子id,武功id,武功层数,是否溶血; 舍利子id,武功id,武功层数,是否溶血;) t_character_goods.f_shelizhi_in_equip_id
	 * @return  the value of t_character_goods.f_shelizhi_in_equip_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getShelizhiInEquipId() {
		return shelizhiInEquipId;
	}

	/**
	 * (舍利子id,武功id,武功层数,是否溶血; 舍利子id,武功id,武功层数,是否溶血;) t_character_goods.f_shelizhi_in_equip_id
	 * @param shelizhiInEquipId  the value for t_character_goods.f_shelizhi_in_equip_id
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setShelizhiInEquipId(String shelizhiInEquipId) {
		this.shelizhiInEquipId = shelizhiInEquipId;
	}

	/**
	 * 舍利子或附身符升阶次数累计 t_character_goods.f_tupo_cnt
	 * @return  the value of t_character_goods.f_tupo_cnt
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getTupoCnt() {
		return tupoCnt;
	}

	/**
	 * 舍利子或附身符升阶次数累计 t_character_goods.f_tupo_cnt
	 * @param tupoCnt  the value for t_character_goods.f_tupo_cnt
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setTupoCnt(Integer tupoCnt) {
		this.tupoCnt = tupoCnt;
	}

	/**
	 * 附加的图腾属性(格式:  图腾刻画者;图腾id; (类型,区间的位置;类型,区间的位置;)*)（类型:20攻击力增强21反弹伤害22忽视防御23伤害减免24暗器免伤） t_character_goods.f_totem
	 * @return  the value of t_character_goods.f_totem
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getTotem() {
		return totem;
	}

	/**
	 * 附加的图腾属性(格式:  图腾刻画者;图腾id; (类型,区间的位置;类型,区间的位置;)*)（类型:20攻击力增强21反弹伤害22忽视防御23伤害减免24暗器免伤） t_character_goods.f_totem
	 * @param totem  the value for t_character_goods.f_totem
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setTotem(String totem) {
		this.totem = totem;
	}

	/**
	 * 舍利子是否融血 t_character_goods.f_shelizhi_rongxue
	 * @return  the value of t_character_goods.f_shelizhi_rongxue
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Boolean getShelizhiRongxue() {
		return shelizhiRongxue;
	}

	/**
	 * 舍利子是否融血 t_character_goods.f_shelizhi_rongxue
	 * @param shelizhiRongxue  the value for t_character_goods.f_shelizhi_rongxue
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setShelizhiRongxue(Boolean shelizhiRongxue) {
		this.shelizhiRongxue = shelizhiRongxue;
	}

	/**
	 * 图腾所属角色名 t_character_goods.f_totem_usrname
	 * @return  the value of t_character_goods.f_totem_usrname
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getTotemUsrname() {
		return totemUsrname;
	}

	/**
	 * 图腾所属角色名 t_character_goods.f_totem_usrname
	 * @param totemUsrname  the value for t_character_goods.f_totem_usrname
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setTotemUsrname(String totemUsrname) {
		this.totemUsrname = totemUsrname;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_male_name
	 * @return  the value of t_character_goods.f_male_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getMaleName() {
		return maleName;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_male_name
	 * @param maleName  the value for t_character_goods.f_male_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setMaleName(String maleName) {
		this.maleName = maleName;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_female_name
	 * @return  the value of t_character_goods.f_female_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getFemaleName() {
		return femaleName;
	}

	/**
	 * 离婚玩家男方名字 t_character_goods.f_female_name
	 * @param femaleName  the value for t_character_goods.f_female_name
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setFemaleName(String femaleName) {
		this.femaleName = femaleName;
	}

	/**
	 * 结婚日期 t_character_goods.f_couple_date
	 * @return  the value of t_character_goods.f_couple_date
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Date getCoupleDate() {
		return coupleDate;
	}

	/**
	 * 结婚日期 t_character_goods.f_couple_date
	 * @param coupleDate  the value for t_character_goods.f_couple_date
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setCoupleDate(Date coupleDate) {
		this.coupleDate = coupleDate;
	}

	/**
	 * 装备天生强化突破的次数 t_character_goods.f_born_strengthen_cnt
	 * @return  the value of t_character_goods.f_born_strengthen_cnt
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public Integer getBornStrengthenCnt() {
		return bornStrengthenCnt;
	}

	/**
	 * 装备天生强化突破的次数 t_character_goods.f_born_strengthen_cnt
	 * @param bornStrengthenCnt  the value for t_character_goods.f_born_strengthen_cnt
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setBornStrengthenCnt(Integer bornStrengthenCnt) {
		this.bornStrengthenCnt = bornStrengthenCnt;
	}

	/**
	 * 宝石自身镶嵌过的附加属性(属性类型,)* t_character_goods.f_strone_addproperty
	 * @return  the value of t_character_goods.f_strone_addproperty
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public String getStroneAddproperty() {
		return stroneAddproperty;
	}

	/**
	 * 宝石自身镶嵌过的附加属性(属性类型,)* t_character_goods.f_strone_addproperty
	 * @param stroneAddproperty  the value for t_character_goods.f_strone_addproperty
	 * @ibatorgenerated  Fri Jun 24 09:14:46 CST 2011
	 */
	public void setStroneAddproperty(String stroneAddproperty) {
		this.stroneAddproperty = stroneAddproperty;
	}
}
