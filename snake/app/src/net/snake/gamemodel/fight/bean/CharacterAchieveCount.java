package net.snake.gamemodel.fight.bean;

import net.snake.ibatis.IbatisEntity;

public class CharacterAchieveCount implements IbatisEntity {
    /**
     *  t_character_achieve_count.f_id
     *
     * 
     */
    private Integer id;

    /**
     * 统计子类别 t_character_achieve_count.f_child_kind
     *
     * 
     */
    private Integer childKind;

    /**
     * 统计成就进度数量 t_character_achieve_count.f_achieve_count
     *
     * 
     */
    private Integer achieveCount;

    /**
     * 角色id t_character_achieve_count.f_character_id
     *
     * 
     */
    private Integer characterId;

    /**
     *  t_character_achieve_count.f_id
     *
     * @return the value of t_character_achieve_count.f_id
     *
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     *  t_character_achieve_count.f_id
     *
     * @param id the value for t_character_achieve_count.f_id
     *
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 统计子类别 t_character_achieve_count.f_child_kind
     *
     * @return the value of t_character_achieve_count.f_child_kind
     *
     * 
     */
    public Integer getChildKind() {
        return childKind;
    }

    /**
     * 统计子类别 t_character_achieve_count.f_child_kind
     *
     * @param childKind the value for t_character_achieve_count.f_child_kind
     *
     * 
     */
    public void setChildKind(Integer childKind) {
        this.childKind = childKind;
    }

    /**
     * 统计成就进度数量 t_character_achieve_count.f_achieve_count
     *
     * @return the value of t_character_achieve_count.f_achieve_count
     *
     * 
     */
    public Integer getAchieveCount() {
        return achieveCount;
    }

    /**
     * 统计成就进度数量 t_character_achieve_count.f_achieve_count
     *
     * @param achieveCount the value for t_character_achieve_count.f_achieve_count
     *
     * 
     */
    public void setAchieveCount(Integer achieveCount) {
        this.achieveCount = achieveCount;
    }

    /**
     * 角色id t_character_achieve_count.f_character_id
     *
     * @return the value of t_character_achieve_count.f_character_id
     *
     * 
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     * 角色id t_character_achieve_count.f_character_id
     *
     * @param characterId the value for t_character_achieve_count.f_character_id
     *
     * 
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }
}
