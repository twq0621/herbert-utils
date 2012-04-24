package net.snake.gamemodel.activities.bean;

import java.util.Date;

import net.snake.ibatis.IbatisEntity;

public class CharacterActivationCard implements IbatisEntity{
    /**
     * 主键ID t_character_activation_card.f_id
     *
     *
     */
    private Integer id;

    /**
     * 卡片号码 t_character_activation_card.f_card_no
     *
     *
     */
    private String cardNo;

    /**
     * 激活码类型 t_character_activation_card.f_card_type
     *
     *
     */
    private Byte cardType;

    /**
     * 服务器ID t_character_activation_card.f_sid
     *
     *
     */
    private Integer sid;

    /**
     * 领取的账号ID t_character_activation_card.f_account_id
     *
     *
     */
    private Integer accountId;

    /**
     * 领取的角色ID t_character_activation_card.f_character_id
     *
     *
     */
    private Integer characterId;

    /**
     * 领取时间 t_character_activation_card.f_time
     *
     *
     */
    private Date time;

    /**
     * 领取IP t_character_activation_card.f_ip
     *
     *
     */
    private String ip;

    /**
     * 领取时的角色等级 t_character_activation_card.f_character_grade
     *
     *
     */
    private Integer characterGrade;

    /**
     * 主键ID t_character_activation_card.f_id
     *
     * @return the value of t_character_activation_card.f_id
     *
     *
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键ID t_character_activation_card.f_id
     *
     * @param id the value for t_character_activation_card.f_id
     *
     *
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 卡片号码 t_character_activation_card.f_card_no
     *
     * @return the value of t_character_activation_card.f_card_no
     *
     *
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡片号码 t_character_activation_card.f_card_no
     *
     * @param cardNo the value for t_character_activation_card.f_card_no
     *
     *
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * 激活码类型 t_character_activation_card.f_card_type
     *
     * @return the value of t_character_activation_card.f_card_type
     *
     *
     */
    public Byte getCardType() {
        return cardType;
    }

    /**
     * 激活码类型 t_character_activation_card.f_card_type
     *
     * @param cardType the value for t_character_activation_card.f_card_type
     *
     *
     */
    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    /**
     * 服务器ID t_character_activation_card.f_sid
     *
     * @return the value of t_character_activation_card.f_sid
     *
     *
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * 服务器ID t_character_activation_card.f_sid
     *
     * @param sid the value for t_character_activation_card.f_sid
     *
     *
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 领取的账号ID t_character_activation_card.f_account_id
     *
     * @return the value of t_character_activation_card.f_account_id
     *
     *
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * 领取的账号ID t_character_activation_card.f_account_id
     *
     * @param accountId the value for t_character_activation_card.f_account_id
     *
     *
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * 领取的角色ID t_character_activation_card.f_character_id
     *
     * @return the value of t_character_activation_card.f_character_id
     *
     *
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * 领取的角色ID t_character_activation_card.f_character_id
     *
     * @param characterId the value for t_character_activation_card.f_character_id
     *
     *
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * 领取时间 t_character_activation_card.f_time
     *
     * @return the value of t_character_activation_card.f_time
     *
     *
     */
    public Date getTime() {
        return time;
    }

    /**
     * 领取时间 t_character_activation_card.f_time
     *
     * @param time the value for t_character_activation_card.f_time
     *
     *
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * 领取IP t_character_activation_card.f_ip
     *
     * @return the value of t_character_activation_card.f_ip
     *
     *
     */
    public String getIp() {
        return ip;
    }

    /**
     * 领取IP t_character_activation_card.f_ip
     *
     * @param ip the value for t_character_activation_card.f_ip
     *
     *
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 领取时的角色等级 t_character_activation_card.f_character_grade
     *
     * @return the value of t_character_activation_card.f_character_grade
     *
     *
     */
    public Integer getCharacterGrade() {
        return characterGrade;
    }

    /**
     * 领取时的角色等级 t_character_activation_card.f_character_grade
     *
     * @param characterGrade the value for t_character_activation_card.f_character_grade
     *
     *
     */
    public void setCharacterGrade(Integer characterGrade) {
        this.characterGrade = characterGrade;
    }
}
