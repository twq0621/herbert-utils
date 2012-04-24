package net.snake.gamemodel.heroext.wudao.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterDGWD implements IbatisEntity {
    /**
     *  t_character_dgwd.f_characterid
     *
     * 
     */
    private Integer characterid;

    /**
     * 前当悟道等级 t_character_dgwd.f_nowwd
     *
     * 
     */
    private Integer nowwd;

    /**
     * 当前幸运值 t_character_dgwd.f_luck
     *
     * 
     */
    private Integer luck;

    /**
     * 败失次数 t_character_dgwd.f_faildcount
     *
     * 
     */
    private Integer faildcount;

    /**
     * 上次幸运值重置时间 t_character_dgwd.f_luck_lastclear
     *
     * 
     */
    private Date luckLastclear;

    /**
     * 上次幸运值 t_character_dgwd.f_before_luck
     *
     * 
     */
    private Integer beforeLuck;

    /**
     *  t_character_dgwd.f_characterid
     *
     * @return the value of t_character_dgwd.f_characterid
     *
     * 
     */
    public Integer getCharacterid() {
        return characterid;
    }

    /**
     *  t_character_dgwd.f_characterid
     *
     * @param characterid the value for t_character_dgwd.f_characterid
     *
     * 
     */
    public void setCharacterid(Integer characterid) {
        this.characterid = characterid;
    }

    /**
     * 前当悟道等级 t_character_dgwd.f_nowwd
     *
     * @return the value of t_character_dgwd.f_nowwd
     *
     * 
     */
    public Integer getNowwd() {
        return nowwd;
    }

    /**
     * 前当悟道等级 t_character_dgwd.f_nowwd
     *
     * @param nowwd the value for t_character_dgwd.f_nowwd
     *
     * 
     */
    public void setNowwd(Integer nowwd) {
        this.nowwd = nowwd;
    }

    /**
     * 当前幸运值 t_character_dgwd.f_luck
     *
     * @return the value of t_character_dgwd.f_luck
     *
     * 
     */
    public Integer getLuck() {
        return luck;
    }

    /**
     * 当前幸运值 t_character_dgwd.f_luck
     *
     * @param luck the value for t_character_dgwd.f_luck
     *
     * 
     */
    public void setLuck(Integer luck) {
        this.luck = luck;
    }

    /**
     * 败失次数 t_character_dgwd.f_faildcount
     *
     * @return the value of t_character_dgwd.f_faildcount
     *
     * 
     */
    public Integer getFaildcount() {
        return faildcount;
    }

    /**
     * 败失次数 t_character_dgwd.f_faildcount
     *
     * @param faildcount the value for t_character_dgwd.f_faildcount
     *
     * 
     */
    public void setFaildcount(Integer faildcount) {
        this.faildcount = faildcount;
    }

    /**
     * 上次幸运值重置时间 t_character_dgwd.f_luck_lastclear
     *
     * @return the value of t_character_dgwd.f_luck_lastclear
     *
     * 
     */
    public Date getLuckLastclear() {
        return luckLastclear;
    }

    /**
     * 上次幸运值重置时间 t_character_dgwd.f_luck_lastclear
     *
     * @param luckLastclear the value for t_character_dgwd.f_luck_lastclear
     *
     * 
     */
    public void setLuckLastclear(Date luckLastclear) {
        this.luckLastclear = luckLastclear;
    }

    /**
     * 上次幸运值 t_character_dgwd.f_before_luck
     *
     * @return the value of t_character_dgwd.f_before_luck
     *
     * 
     */
    public Integer getBeforeLuck() {
        return beforeLuck;
    }

    /**
     * 上次幸运值 t_character_dgwd.f_before_luck
     *
     * @param beforeLuck the value for t_character_dgwd.f_before_luck
     *
     * 
     */
    public void setBeforeLuck(Integer beforeLuck) {
        this.beforeLuck = beforeLuck;
    }
}
