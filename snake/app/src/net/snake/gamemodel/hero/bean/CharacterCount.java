package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterCount implements IbatisEntity {
    /**
     *  t_character_count.f_id
     *
     * 
     */
    private Integer id;

    /**
     * 色角ID t_character_count.f_character_id
     *
     * 
     */
    private Integer characterId;

    /**
     * 计数类型 t_character_count.f_count_type
     *
     * 
     */
    private Integer countType;

    /**
     * 计数 t_character_count.f_count
     *
     * 
     */
    private Integer count;

    /**
     *  t_character_count.f_describe
     *
     * 
     */
    private String describe;

    /**
     *  t_character_count.f_id
     *
     * @return the value of t_character_count.f_id
     *
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     *  t_character_count.f_id
     *
     * @param id the value for t_character_count.f_id
     *
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 色角ID t_character_count.f_character_id
     *
     * @return the value of t_character_count.f_character_id
     *
     * 
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * 色角ID t_character_count.f_character_id
     *
     * @param characterId the value for t_character_count.f_character_id
     *
     * 
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * 计数类型 t_character_count.f_count_type
     *
     * @return the value of t_character_count.f_count_type
     *
     * 
     */
    public Integer getCountType() {
        return countType;
    }

    /**
     * 计数类型 t_character_count.f_count_type
     *
     * @param countType the value for t_character_count.f_count_type
     *
     * 
     */
    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    /**
     * 计数 t_character_count.f_count
     *
     * @return the value of t_character_count.f_count
     *
     * 
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 计数 t_character_count.f_count
     *
     * @param count the value for t_character_count.f_count
     *
     * 
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     *  t_character_count.f_describe
     *
     * @return the value of t_character_count.f_describe
     *
     * 
     */
    public String getDescribe() {
        return describe;
    }

    /**
     *  t_character_count.f_describe
     *
     * @param describe the value for t_character_count.f_describe
     *
     * 
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
