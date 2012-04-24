package net.snake.gamemodel.gift.bean;

import net.snake.ibatis.IbatisEntity;

public class GrandPrixData  implements IbatisEntity{

	/**
	 * t_grandprix.id
	 *
	 */
	private Integer id;
	/**
	 * 达成条件描述 t_grandprix.f_limit
	 *
	 */
	private String fLimit;
	/**
	 * 图标编号 t_grandprix.f_icoid
	 *
	 */
	private Integer fIcoid;
	/**
	 * t_grandprix.f_desc
	 *
	 */
	private String fDesc;
	/**
	 * 礼包名称 t_grandprix.f_name
	 *
	 */
	private String fName;
	/**
	 * 关联礼包ID t_grandprix.f_giftpackid
	 *
	 */
	private Integer fGiftpackid;
	/**
	 * 礼包备注 t_grandprix.f_desc_i18n
	 *
	 */
	private String fDescI18n;
	/**
	 * 礼包名称 t_grandprix.f_name_i18n
	 *
	 */
	private String fNameI18n;

	/**
	 * t_grandprix.id
	 * @return  the value of t_grandprix.id
	 *
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_grandprix.id
	 * @param id  the value for t_grandprix.id
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 达成条件描述 t_grandprix.f_limit
	 * @return  the value of t_grandprix.f_limit
	 *
	 */
	public String getfLimit() {
		return fLimit;
	}

	/**
	 * 达成条件描述 t_grandprix.f_limit
	 * @param fLimit  the value for t_grandprix.f_limit
	 *
	 */
	public void setfLimit(String fLimit) {
		this.fLimit = fLimit;
	}

	/**
	 * 图标编号 t_grandprix.f_icoid
	 * @return  the value of t_grandprix.f_icoid
	 *
	 */
	public Integer getfIcoid() {
		return fIcoid;
	}

	/**
	 * 图标编号 t_grandprix.f_icoid
	 * @param fIcoid  the value for t_grandprix.f_icoid
	 *
	 */
	public void setfIcoid(Integer fIcoid) {
		this.fIcoid = fIcoid;
	}

	/**
	 * t_grandprix.f_desc
	 * @return  the value of t_grandprix.f_desc
	 *
	 */
	public String getfDesc() {
		return fDesc;
	}

	/**
	 * t_grandprix.f_desc
	 * @param fDesc  the value for t_grandprix.f_desc
	 *
	 */
	public void setfDesc(String fDesc) {
		this.fDesc = fDesc;
	}

	/**
	 * 礼包名称 t_grandprix.f_name
	 * @return  the value of t_grandprix.f_name
	 *
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * 礼包名称 t_grandprix.f_name
	 * @param fName  the value for t_grandprix.f_name
	 *
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * 关联礼包ID t_grandprix.f_giftpackid
	 * @return  the value of t_grandprix.f_giftpackid
	 *
	 */
	public Integer getfGiftpackid() {
		return fGiftpackid;
	}

	/**
	 * 关联礼包ID t_grandprix.f_giftpackid
	 * @param fGiftpackid  the value for t_grandprix.f_giftpackid
	 *
	 */
	public void setfGiftpackid(Integer fGiftpackid) {
		this.fGiftpackid = fGiftpackid;
	}

	/**
	 * 礼包备注 t_grandprix.f_desc_i18n
	 * @return  the value of t_grandprix.f_desc_i18n
	 *
	 */
	public String getfDescI18n() {
		return fDescI18n;
	}

	/**
	 * 礼包备注 t_grandprix.f_desc_i18n
	 * @param fDescI18n  the value for t_grandprix.f_desc_i18n
	 *
	 */
	public void setfDescI18n(String fDescI18n) {
		this.fDescI18n = fDescI18n;
	}

	/**
	 * 礼包名称 t_grandprix.f_name_i18n
	 * @return  the value of t_grandprix.f_name_i18n
	 *
	 */
	public String getfNameI18n() {
		return fNameI18n;
	}

	/**
	 * 礼包名称 t_grandprix.f_name_i18n
	 * @param fNameI18n  the value for t_grandprix.f_name_i18n
	 *
	 */
	public void setfNameI18n(String fNameI18n) {
		this.fNameI18n = fNameI18n;
	}
}
