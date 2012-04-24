package net.snake.gamemodel.vip.bean;

import java.util.Date;

import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.ibatis.IbatisEntity;


public class CharacterVip  implements IbatisEntity{

	/**
	 * character_vip.id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	private Integer id;
	/**
	 * 角色id character_vip.f_character_id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	private Integer characterId;
	/**
	 * 使用效果buffer id 展示效果用 character_vip.f_buffer_id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	private Integer bufferId;
	/**
	 * 开始使用时间 character_vip.f_start_time
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	private Date startTime;
	/**
	 * 结束使用时间 character_vip.f_end_time
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	private Date endTime;

	/**
	 * character_vip.id
	 * @return  the value of character_vip.id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * character_vip.id
	 * @param id  the value for character_vip.id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色id character_vip.f_character_id
	 * @return  the value of character_vip.f_character_id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id character_vip.f_character_id
	 * @param characterId  the value for character_vip.f_character_id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 使用效果buffer id 展示效果用 character_vip.f_buffer_id
	 * @return  the value of character_vip.f_buffer_id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public Integer getBufferId() {
		return bufferId;
	}

	/**
	 * 使用效果buffer id 展示效果用 character_vip.f_buffer_id
	 * @param bufferId  the value for character_vip.f_buffer_id
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public void setBufferId(Integer bufferId) {
		this.bufferId = bufferId;
	}

	/**
	 * 开始使用时间 character_vip.f_start_time
	 * @return  the value of character_vip.f_start_time
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 开始使用时间 character_vip.f_start_time
	 * @param startTime  the value for character_vip.f_start_time
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 结束使用时间 character_vip.f_end_time
	 * @return  the value of character_vip.f_end_time
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 结束使用时间 character_vip.f_end_time
	 * @param endTime  the value for character_vip.f_end_time
	 * @ibatorgenerated  Fri Feb 04 17:22:30 CST 2011
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	private EffectInfo effectInfo;
	public EffectInfo getEffectInfo(){
		if (effectInfo == null) {
			SkillEffect se = SkillEffectManager.getInstance()
					.getSkillEffectById(getBufferId());
			if (se == null) {
				return null;
			}
			this.effectInfo = new EffectInfo(se);
		}
		return effectInfo;
	}
}
