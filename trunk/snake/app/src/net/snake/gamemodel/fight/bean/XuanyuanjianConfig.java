package net.snake.gamemodel.fight.bean;

import net.snake.gamemodel.fight.persistence.XuanyuanBufferJiachengManager;
import net.snake.ibatis.IbatisEntity;

public class XuanyuanjianConfig implements IbatisEntity {

	/**
	 * t_xuanyuanjian_config.f_id
	 */
	private Integer id;
	/**
	 * t_xuanyuanjian_config.f_xuanyuanjian_name
	 */
	private String xuanyuanjianName;
	/**
	 * 关联怪物模型id（场景中怪物造型显示） t_xuanyuanjian_config.f_monster_id
	 */
	private Integer monsterId;
	/**
	 * 轩辕剑类别 1神剑，2天剑，3地剑，4人剑 t_xuanyuanjian_config.f_type
	 */
	private Integer type;
	/**
	 * 帮主经验加成倍数 t_xuanyuanjian_config.f_bangzhu_exp
	 */
	private Integer bangzhuExp;
	/**
	 * 帮会成员经验加成倍数 t_xuanyuanjian_config.f_chengyuan_exp
	 */
	private Integer chengyuanExp;
	/**
	 * 轩辕剑持剑帮主加成bufer（源服务器轩辕神剑也加成此buffer） t_xuanyuanjian_config.f_bangzhu_buffer
	 */
	private Integer bangzhuBuffer;
	/**
	 * 帮会加成buffer t_xuanyuanjian_config.f_faction_buffer
	 */
	private Integer factionBuffer;
	/**
	 * 轩辕抗主buffer（减弱持剑者防御属性） t_xuanyuanjian_config.f_kangzhu_buffer
	 */
	private Integer kangzhuBuffer;
	/**
	 * 换装物品id t_xuanyuanjian_config.f_good_id
	 */
	private Integer goodId;
	/**
	 * 刷怪点x坐标 t_xuanyuanjian_config.f_x
	 */
	private Short x;
	/**
	 * 刷怪点y坐标 t_xuanyuanjian_config.f_y
	 */
	private Short y;

	/**
	 * t_xuanyuanjian_config.f_id
	 * 
	 * @return the value of t_xuanyuanjian_config.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_xuanyuanjian_config.f_id
	 * 
	 * @param id
	 *            the value for t_xuanyuanjian_config.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * t_xuanyuanjian_config.f_xuanyuanjian_name
	 * 
	 * @return the value of t_xuanyuanjian_config.f_xuanyuanjian_name
	 */
	public String getXuanyuanjianName() {
		return xuanyuanjianName;
	}

	/**
	 * t_xuanyuanjian_config.f_xuanyuanjian_name
	 * 
	 * @param xuanyuanjianName
	 *            the value for t_xuanyuanjian_config.f_xuanyuanjian_name
	 */
	public void setXuanyuanjianName(String xuanyuanjianName) {
		this.xuanyuanjianName = xuanyuanjianName;
	}

	/**
	 * 关联怪物模型id（场景中怪物造型显示） t_xuanyuanjian_config.f_monster_id
	 * 
	 * @return the value of t_xuanyuanjian_config.f_monster_id
	 */
	public Integer getMonsterId() {
		return monsterId;
	}

	/**
	 * 关联怪物模型id（场景中怪物造型显示） t_xuanyuanjian_config.f_monster_id
	 * 
	 * @param monsterId
	 *            the value for t_xuanyuanjian_config.f_monster_id
	 */
	public void setMonsterId(Integer monsterId) {
		this.monsterId = monsterId;
	}

	/**
	 * 轩辕剑类别 1神剑，2天剑，3地剑，4人剑 t_xuanyuanjian_config.f_type
	 * 
	 * @return the value of t_xuanyuanjian_config.f_type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 轩辕剑类别 1神剑，2天剑，3地剑，4人剑 t_xuanyuanjian_config.f_type
	 * 
	 * @param type
	 *            the value for t_xuanyuanjian_config.f_type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 帮主经验加成倍数 t_xuanyuanjian_config.f_bangzhu_exp
	 * 
	 * @return the value of t_xuanyuanjian_config.f_bangzhu_exp
	 */
	public Integer getBangzhuExp() {
		return bangzhuExp;
	}

	/**
	 * 帮主经验加成倍数 t_xuanyuanjian_config.f_bangzhu_exp
	 * 
	 * @param bangzhuExp
	 *            the value for t_xuanyuanjian_config.f_bangzhu_exp
	 */
	public void setBangzhuExp(Integer bangzhuExp) {
		this.bangzhuExp = bangzhuExp;
	}

	/**
	 * 帮会成员经验加成倍数 t_xuanyuanjian_config.f_chengyuan_exp
	 * 
	 * @return the value of t_xuanyuanjian_config.f_chengyuan_exp
	 */
	public Integer getChengyuanExp() {
		return chengyuanExp;
	}

	/**
	 * 帮会成员经验加成倍数 t_xuanyuanjian_config.f_chengyuan_exp
	 * 
	 * @param chengyuanExp
	 *            the value for t_xuanyuanjian_config.f_chengyuan_exp
	 */
	public void setChengyuanExp(Integer chengyuanExp) {
		this.chengyuanExp = chengyuanExp;
	}

	/**
	 * 轩辕剑持剑帮主加成bufer（源服务器轩辕神剑也加成此buffer） t_xuanyuanjian_config.f_bangzhu_buffer
	 * 
	 * @return the value of t_xuanyuanjian_config.f_bangzhu_buffer
	 */
	public Integer getBangzhuBuffer() {
		return bangzhuBuffer;
	}

	/**
	 * 轩辕剑持剑帮主加成bufer（源服务器轩辕神剑也加成此buffer） t_xuanyuanjian_config.f_bangzhu_buffer
	 * 
	 * @param bangzhuBuffer
	 *            the value for t_xuanyuanjian_config.f_bangzhu_buffer
	 */
	public void setBangzhuBuffer(Integer bangzhuBuffer) {
		this.bangzhuBuffer = bangzhuBuffer;
	}

	/**
	 * 帮会加成buffer t_xuanyuanjian_config.f_faction_buffer
	 * 
	 * @return the value of t_xuanyuanjian_config.f_faction_buffer
	 */
	public Integer getFactionBuffer() {
		return factionBuffer;
	}

	/**
	 * 帮会加成buffer t_xuanyuanjian_config.f_faction_buffer
	 * 
	 * @param factionBuffer
	 *            the value for t_xuanyuanjian_config.f_faction_buffer
	 */
	public void setFactionBuffer(Integer factionBuffer) {
		this.factionBuffer = factionBuffer;
	}

	/**
	 * 轩辕抗主buffer（减弱持剑者防御属性） t_xuanyuanjian_config.f_kangzhu_buffer
	 * 
	 * @return the value of t_xuanyuanjian_config.f_kangzhu_buffer
	 */
	public Integer getKangzhuBuffer() {
		return kangzhuBuffer;
	}

	/**
	 * 轩辕抗主buffer（减弱持剑者防御属性） t_xuanyuanjian_config.f_kangzhu_buffer
	 * 
	 * @param kangzhuBuffer
	 *            the value for t_xuanyuanjian_config.f_kangzhu_buffer
	 */
	public void setKangzhuBuffer(Integer kangzhuBuffer) {
		this.kangzhuBuffer = kangzhuBuffer;
	}

	/**
	 * 换装物品id t_xuanyuanjian_config.f_good_id
	 * 
	 * @return the value of t_xuanyuanjian_config.f_good_id
	 */
	public Integer getGoodId() {
		return goodId;
	}

	/**
	 * 换装物品id t_xuanyuanjian_config.f_good_id
	 * 
	 * @param goodId
	 *            the value for t_xuanyuanjian_config.f_good_id
	 */
	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	/**
	 * 刷怪点x坐标 t_xuanyuanjian_config.f_x
	 * 
	 * @return the value of t_xuanyuanjian_config.f_x
	 */
	public Short getX() {
		return x;
	}

	/**
	 * 刷怪点x坐标 t_xuanyuanjian_config.f_x
	 * 
	 * @param x
	 *            the value for t_xuanyuanjian_config.f_x
	 */
	public void setX(Short x) {
		this.x = x;
	}

	/**
	 * 刷怪点y坐标 t_xuanyuanjian_config.f_y
	 * 
	 * @return the value of t_xuanyuanjian_config.f_y
	 */
	public Short getY() {
		return y;
	}

	/**
	 * 刷怪点y坐标 t_xuanyuanjian_config.f_y
	 * 
	 * @param y
	 *            the value for t_xuanyuanjian_config.f_y
	 */
	public void setY(Short y) {
		this.y = y;
	}

	private XuanyuanBufferJiacheng xuanyuanJianBangzhuBuffer;
	private XuanyuanBufferJiacheng xuanyuanJianFactionBuffer;
	private XuanyuanBufferJiacheng xuanyuanJiankangzhuBuffer;

	public XuanyuanBufferJiacheng getXuanyuanJianBangzhuBuffer() {
		if (xuanyuanJianBangzhuBuffer == null) {
			xuanyuanJianBangzhuBuffer = XuanyuanBufferJiachengManager.getInstance().getXuanyuanBufferByBufferID(this.bangzhuBuffer);
		}
		return xuanyuanJianBangzhuBuffer;
	}

	public XuanyuanBufferJiacheng getXuanyuanJianFactionBuffer() {
		if (xuanyuanJianFactionBuffer == null) {
			xuanyuanJianFactionBuffer = XuanyuanBufferJiachengManager.getInstance().getXuanyuanBufferByBufferID(this.factionBuffer);
		}
		return xuanyuanJianFactionBuffer;
	}

	public XuanyuanBufferJiacheng getXuanyuanJianKangzhuBuffer() {
		if (xuanyuanJiankangzhuBuffer == null) {
			xuanyuanJiankangzhuBuffer = XuanyuanBufferJiachengManager.getInstance().getXuanyuanBufferByBufferID(this.kangzhuBuffer);
		}
		return xuanyuanJiankangzhuBuffer;
	}

}
