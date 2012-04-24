package net.snake.gamemodel.guide.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterWeekTime implements IbatisEntity {
    /**
     *  t_character_week.f_id
     *
     * 
     */
    private Integer fId;

    /**
     * 角色id 关联角色表 t_character_week.f_character_id
     *
     * 
     */
    private Integer fCharacterId;

    /**
     * 年 t_character_week.f_year
     *
     * 
     */
    private Integer fYear;

    /**
     * 统计周 t_character_week.f_week
     *
     * 
     */
    private Integer fWeek;

    /**
     * 在线总时间 t_character_week.f_online_time
     *
     * 
     */
    private Integer fOnlineTime;

    /**
     *  t_character_week.f_id
     *
     * @return the value of t_character_week.f_id
     *
     * 
     */
    public Integer getfId() {
        return fId;
    }

    /**
     *  t_character_week.f_id
     *
     * @param fId the value for t_character_week.f_id
     *
     * 
     */
    public void setfId(Integer fId) {
        this.fId = fId;
    }

    /**
     * 角色id 关联角色表 t_character_week.f_character_id
     *
     * @return the value of t_character_week.f_character_id
     *
     * 
     */
    public Integer getfCharacterId() {
        return fCharacterId;
    }

    /**
     * 角色id 关联角色表 t_character_week.f_character_id
     *
     * @param fCharacterId the value for t_character_week.f_character_id
     *
     * 
     */
    public void setfCharacterId(Integer fCharacterId) {
        this.fCharacterId = fCharacterId;
    }

    /**
     * 年 t_character_week.f_year
     *
     * @return the value of t_character_week.f_year
     *
     * 
     */
    public Integer getfYear() {
        return fYear;
    }

    /**
     * 年 t_character_week.f_year
     *
     * @param fYear the value for t_character_week.f_year
     *
     * 
     */
    public void setfYear(Integer fYear) {
        this.fYear = fYear;
    }

    /**
     * 统计周 t_character_week.f_week
     *
     * @return the value of t_character_week.f_week
     *
     * 
     */
    public Integer getfWeek() {
        return fWeek;
    }

    /**
     * 统计周 t_character_week.f_week
     *
     * @param fWeek the value for t_character_week.f_week
     *
     * 
     */
    public void setfWeek(Integer fWeek) {
        this.fWeek = fWeek;
    }

    /**
     * 在线总时间 t_character_week.f_online_time
     *
     * @return the value of t_character_week.f_online_time
     *
     * 
     */
    public Integer getfOnlineTime() {
        return fOnlineTime;
    }

    /**
     * 在线总时间 t_character_week.f_online_time
     *
     * @param fOnlineTime the value for t_character_week.f_online_time
     *
     * 
     */
    public void setfOnlineTime(Integer fOnlineTime) {
        this.fOnlineTime = fOnlineTime;
    }
}
