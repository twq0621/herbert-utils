package net.snake.gamemodel.faction.bean;

import java.util.Calendar;
import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class FactionCity  implements IbatisEntity{
     
	/**
	 * t_faction_city.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 当前占城帮会id t_faction_city.f_faction_id
	 * 
	 */
	private Integer factionId;
	/**
	 * 襄阳成税率 t_faction_city.f_tax_rate
	 * 
	 */
	private Integer taxRate;
	/**
	 * 昨天税金收入 t_faction_city.f_yd_taxes
	 * 
	 */
	private Integer ydTaxes;
	/**
	 * 今日税金收入 t_faction_city.f_td_taxes
	 * 
	 */
	private Integer tdTaxes;
	/**
	 * 税金总收入 t_faction_city.f_all_taxes
	 * 
	 */
	private Long allTaxes;
	/**
	 * 是否已经领取税金（今日） 0未领取 1领取 t_faction_city.f_isget_taxes
	 * 
	 */
	private Integer isgetTaxes;
	/**
	 * 帮主今日是否已经领取 0未领取 1领取 t_faction_city.f_isget_bangzhu
	 * 
	 */
	private Integer isgetBangzhu;
	/**
	 * 负帮主今日是否已经领取 0未领取 1领取 t_faction_city.f_isget_fubangzhu
	 * 
	 */
	private Integer isgetFubangzhu;
	/**
	 * 大长老是否领取 t_faction_city.f_isgetdazhanglao
	 * 
	 */
	private Integer isgetdazhanglao;
	/**
	 * 大师兄是否领取 t_faction_city.f_isgetdashixiong
	 * 
	 */
	private Integer isgetdashixiong;
	/**
	 * 大师姐是否领取 0没有 1领取了 t_faction_city.f_isgetdashijie
	 * 
	 */
	private Integer isgetdashijie;
	/**
	 * 可领取时间 每日更新 更换城主更新 t_faction_city.f_lingqu_time
	 * 
	 */
	private Date lingquTime;
	/**
	 * 更新数据更新时间 t_faction_city.f_update_time
	 * 
	 */
	private Date updateTime;
	/**
	 * 城主公告 t_faction_city.f_notice
	 * 
	 */
	private String notice;


	/**
	 * t_faction_city.f_id
	 * @return  the value of t_faction_city.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_faction_city.f_id
	 * @param id  the value for t_faction_city.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 当前占城帮会id t_faction_city.f_faction_id
	 * @return  the value of t_faction_city.f_faction_id
	 * 
	 */
	public Integer getFactionId() {
		return factionId;
	}

	/**
	 * 当前占城帮会id t_faction_city.f_faction_id
	 * @param factionId  the value for t_faction_city.f_faction_id
	 * 
	 */
	public void setFactionId(Integer factionId) {
		this.factionId = factionId;
	}

	/**
	 * 襄阳成税率 t_faction_city.f_tax_rate
	 * @return  the value of t_faction_city.f_tax_rate
	 * 
	 */
	public Integer getTaxRate() {
		return taxRate;
	}

	/**
	 * 襄阳成税率 t_faction_city.f_tax_rate
	 * @param taxRate  the value for t_faction_city.f_tax_rate
	 * 
	 */
	public void setTaxRate(Integer taxRate) {
		this.taxRate = taxRate;
	}

	/**
	 * 昨天税金收入 t_faction_city.f_yd_taxes
	 * @return  the value of t_faction_city.f_yd_taxes
	 * 
	 */
	public Integer getYdTaxes() {
		return ydTaxes;
	}

	/**
	 * 昨天税金收入 t_faction_city.f_yd_taxes
	 * @param ydTaxes  the value for t_faction_city.f_yd_taxes
	 * 
	 */
	public void setYdTaxes(Integer ydTaxes) {
		this.ydTaxes = ydTaxes;
	}

	/**
	 * 今日税金收入 t_faction_city.f_td_taxes
	 * @return  the value of t_faction_city.f_td_taxes
	 * 
	 */
	public Integer getTdTaxes() {
		return tdTaxes;
	}

	/**
	 * 今日税金收入 t_faction_city.f_td_taxes
	 * @param tdTaxes  the value for t_faction_city.f_td_taxes
	 * 
	 */
	public void setTdTaxes(Integer tdTaxes) {
		this.tdTaxes = tdTaxes;
	}

	/**
	 * 税金总收入 t_faction_city.f_all_taxes
	 * @return  the value of t_faction_city.f_all_taxes
	 * 
	 */
	public Long getAllTaxes() {
		return allTaxes;
	}

	/**
	 * 税金总收入 t_faction_city.f_all_taxes
	 * @param allTaxes  the value for t_faction_city.f_all_taxes
	 * 
	 */
	public void setAllTaxes(Long allTaxes) {
		this.allTaxes = allTaxes;
	}

	/**
	 * 是否已经领取税金（今日） 0未领取 1领取 t_faction_city.f_isget_taxes
	 * @return  the value of t_faction_city.f_isget_taxes
	 * 
	 */
	public Integer getIsgetTaxes() {
		return isgetTaxes;
	}

	/**
	 * 是否已经领取税金（今日） 0未领取 1领取 t_faction_city.f_isget_taxes
	 * @param isgetTaxes  the value for t_faction_city.f_isget_taxes
	 * 
	 */
	public void setIsgetTaxes(Integer isgetTaxes) {
		this.isgetTaxes = isgetTaxes;
	}

	/**
	 * 帮主今日是否已经领取 0未领取 1领取 t_faction_city.f_isget_bangzhu
	 * @return  the value of t_faction_city.f_isget_bangzhu
	 * 
	 */
	public Integer getIsgetBangzhu() {
		return isgetBangzhu;
	}

	/**
	 * 帮主今日是否已经领取 0未领取 1领取 t_faction_city.f_isget_bangzhu
	 * @param isgetBangzhu  the value for t_faction_city.f_isget_bangzhu
	 * 
	 */
	public void setIsgetBangzhu(Integer isgetBangzhu) {
		this.isgetBangzhu = isgetBangzhu;
	}

	/**
	 * 负帮主今日是否已经领取 0未领取 1领取 t_faction_city.f_isget_fubangzhu
	 * @return  the value of t_faction_city.f_isget_fubangzhu
	 * 
	 */
	public Integer getIsgetFubangzhu() {
		return isgetFubangzhu;
	}

	/**
	 * 负帮主今日是否已经领取 0未领取 1领取 t_faction_city.f_isget_fubangzhu
	 * @param isgetFubangzhu  the value for t_faction_city.f_isget_fubangzhu
	 * 
	 */
	public void setIsgetFubangzhu(Integer isgetFubangzhu) {
		this.isgetFubangzhu = isgetFubangzhu;
	}

	/**
	 * 大长老是否领取 t_faction_city.f_isgetdazhanglao
	 * @return  the value of t_faction_city.f_isgetdazhanglao
	 * 
	 */
	public Integer getIsgetdazhanglao() {
		return isgetdazhanglao;
	}

	/**
	 * 大长老是否领取 t_faction_city.f_isgetdazhanglao
	 * @param isgetdazhanglao  the value for t_faction_city.f_isgetdazhanglao
	 * 
	 */
	public void setIsgetdazhanglao(Integer isgetdazhanglao) {
		this.isgetdazhanglao = isgetdazhanglao;
	}

	/**
	 * 大师兄是否领取 t_faction_city.f_isgetdashixiong
	 * @return  the value of t_faction_city.f_isgetdashixiong
	 * 
	 */
	public Integer getIsgetdashixiong() {
		return isgetdashixiong;
	}

	/**
	 * 大师兄是否领取 t_faction_city.f_isgetdashixiong
	 * @param isgetdashixiong  the value for t_faction_city.f_isgetdashixiong
	 * 
	 */
	public void setIsgetdashixiong(Integer isgetdashixiong) {
		this.isgetdashixiong = isgetdashixiong;
	}

	/**
	 * 大师姐是否领取 0没有 1领取了 t_faction_city.f_isgetdashijie
	 * @return  the value of t_faction_city.f_isgetdashijie
	 * 
	 */
	public Integer getIsgetdashijie() {
		return isgetdashijie;
	}

	/**
	 * 大师姐是否领取 0没有 1领取了 t_faction_city.f_isgetdashijie
	 * @param isgetdashijie  the value for t_faction_city.f_isgetdashijie
	 * 
	 */
	public void setIsgetdashijie(Integer isgetdashijie) {
		this.isgetdashijie = isgetdashijie;
	}

	/**
	 * 可领取时间 每日更新 更换城主更新 t_faction_city.f_lingqu_time
	 * @return  the value of t_faction_city.f_lingqu_time
	 * 
	 */
	public Date getLingquTime() {
		return lingquTime;
	}

	/**
	 * 可领取时间 每日更新 更换城主更新 t_faction_city.f_lingqu_time
	 * @param lingquTime  the value for t_faction_city.f_lingqu_time
	 * 
	 */
	public void setLingquTime(Date lingquTime) {
		this.lingquTime = lingquTime;
	}

	/**
	 * 更新数据更新时间 t_faction_city.f_update_time
	 * @return  the value of t_faction_city.f_update_time
	 * 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新数据更新时间 t_faction_city.f_update_time
	 * @param updateTime  the value for t_faction_city.f_update_time
	 * 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 城主公告 t_faction_city.f_notice
	 * @return  the value of t_faction_city.f_notice
	 * 
	 */
	public String getNotice() {
		return notice;
	}

	/**
	 * 城主公告 t_faction_city.f_notice
	 * @param notice  the value for t_faction_city.f_notice
	 * 
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}

	public void initTodayInfo(){
		if(this.lingquTime!=null){
			Date date=new Date(onlyYMD());
			if(this.lingquTime.getTime()==date.getTime()){
				return;
			}
		}
		this.isgetBangzhu=0;
		this.isgetFubangzhu=0;
		this.isgetdazhanglao=0;
		this.isgetdashixiong=0;
		this.isgetdashijie=0;
		this.isgetTaxes=0;
		this.ydTaxes=this.tdTaxes;
		this.tdTaxes=0;
		this.lingquTime=new Date(onlyYMD());
	}
	private int ydFactionId=0;
	
	
	public int getYdFactionId() {
		return ydFactionId;
	}

	public void setYdFactionId(int ydFactionId) {
		this.ydFactionId = ydFactionId;
	}

	/**
	 * 获取今日
	 * 
	 * @return
	 */
	private static long onlyYMD() {
		Calendar t = Calendar.getInstance();
		t.setTimeInMillis(System.currentTimeMillis());
		t.set(Calendar.HOUR_OF_DAY, 0);
		t.set(Calendar.MINUTE, 0);
		t.set(Calendar.SECOND, 0);
		t.set(Calendar.MILLISECOND, 0);
		return t.getTimeInMillis();
	}
}
