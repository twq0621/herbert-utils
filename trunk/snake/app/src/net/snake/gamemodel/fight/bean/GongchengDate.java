package net.snake.gamemodel.fight.bean;

import java.util.Calendar;
import java.util.Date;

import net.snake.gamemodel.faction.persistence.FactionCityManager;
import net.snake.ibatis.IbatisEntity;


public class GongchengDate  implements IbatisEntity{
    /**
     *  t_gongcheng_date.f_id
     *
     * 
     */
    private Integer id;

    /**
     * 申请帮会 t_gongcheng_date.f_faction_id
     *
     * 
     */
    private Integer factionId;

    /**
     * 攻城日期 t_gongcheng_date.f_gongcheng_date
     *
     * 
     */
    private Date gongchengDate;

    /**
     * 申请日期 t_gongcheng_date.f_apply_date
     *
     * 
     */
    private Date applyDate;

    /**
     *  t_gongcheng_date.f_id
     *
     * @return the value of t_gongcheng_date.f_id
     *
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     *  t_gongcheng_date.f_id
     *
     * @param id the value for t_gongcheng_date.f_id
     *
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 申请帮会 t_gongcheng_date.f_faction_id
     *
     * @return the value of t_gongcheng_date.f_faction_id
     *
     * 
     */
    public Integer getFactionId() {
        return factionId;
    }

    /**
     * 申请帮会 t_gongcheng_date.f_faction_id
     *
     * @param factionId the value for t_gongcheng_date.f_faction_id
     *
     * 
     */
    public void setFactionId(Integer factionId) {
        this.factionId = factionId;
    }

    /**
     * 攻城日期 t_gongcheng_date.f_gongcheng_date
     *
     * @return the value of t_gongcheng_date.f_gongcheng_date
     *
     * 
     */
    public Date getGongchengDate() {
        return gongchengDate;
    }

    /**
     * 攻城日期 t_gongcheng_date.f_gongcheng_date
     *
     * @param gongchengDate the value for t_gongcheng_date.f_gongcheng_date
     *
     * 
     */
    public void setGongchengDate(Date gongchengDate) {
        this.gongchengDate = gongchengDate;
    }

    /**
     * 申请日期 t_gongcheng_date.f_apply_date
     *
     * @return the value of t_gongcheng_date.f_apply_date
     *
     * 
     */
    public Date getApplyDate() {
        return applyDate;
    }

    /**
     * 申请日期 t_gongcheng_date.f_apply_date
     *
     * @param applyDate the value for t_gongcheng_date.f_apply_date
     *
     * 
     */
    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    public GongchengDate(){
    	
    }
   public GongchengDate(int factionId){
    	this.factionId=factionId;
    	this.applyDate=new Date();
    	this.gongchengDate=applyDateToGongchengDate(this.applyDate);
    }
	public static Date applyDateToGongchengDate(Date applyDate) {
		Date date=new Date();
		Calendar t = Calendar.getInstance();
		t.setTime(date);
		int week=t.get(Calendar.DAY_OF_WEEK);
		if(week<=2){
			t.set(Calendar.DAY_OF_MONTH,t.get(Calendar.DAY_OF_MONTH)+4-week);
		}else if(week>2&&week<6){
			t.set(Calendar.DAY_OF_MONTH,t.get(Calendar.DAY_OF_MONTH)+7-week);
		}else{
			t.set(Calendar.DAY_OF_MONTH,t.get(Calendar.DAY_OF_MONTH)+7+4-week);
		}
		t.set(Calendar.HOUR_OF_DAY, FactionCityManager.startHorse);
		t.set(Calendar.MINUTE, 0);
		t.set(Calendar.SECOND, 0);
		t.set(Calendar.MILLISECOND, 0);
//		Calendar t = Calendar.getInstance();
//		t.setTime(applyDate);
//		t.set(Calendar.DAY_OF_MONTH,t.get(Calendar.DAY_OF_MONTH)+3);
//		t.set(Calendar.HOUR_OF_DAY, FactionCityManager.startHorse);
//		t.set(Calendar.MINUTE, 0);
//		t.set(Calendar.SECOND, 0);
//		t.set(Calendar.MILLISECOND, 0);
		return t.getTime();
	}
	
}
