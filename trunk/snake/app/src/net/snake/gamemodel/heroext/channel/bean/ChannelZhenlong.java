package net.snake.gamemodel.heroext.channel.bean;

import net.snake.ibatis.IbatisEntity;

public class ChannelZhenlong implements IbatisEntity{

	/**
	 * t_channel_zhenlong.f_character_id
	 *
	 */
	private Integer characterId;
	/**
	 * 人物充斤脉的的次数 t_channel_zhenlong.f_character_channel_count
	 *
	 */
	private Integer characterChannelCount;
	/**
	 * 个人得到的经验 t_channel_zhenlong.f_channel_exp
	 *
	 */
	private Integer channelExp;
	/**
	 * 最后一个穴位的时间 t_channel_zhenlong.f_channel_congxue_time
	 *
	 */
	private Integer channelCongxueTime;

	/**
	 * t_channel_zhenlong.f_character_id
	 * @return  the value of t_channel_zhenlong.f_character_id
	 *
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * t_channel_zhenlong.f_character_id
	 * @param characterId  the value for t_channel_zhenlong.f_character_id
	 *
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 人物充斤脉的的次数 t_channel_zhenlong.f_character_channel_count
	 * @return  the value of t_channel_zhenlong.f_character_channel_count
	 *
	 */
	public Integer getCharacterChannelCount() {
		return characterChannelCount;
	}

	/**
	 * 人物充斤脉的的次数 t_channel_zhenlong.f_character_channel_count
	 * @param characterChannelCount  the value for t_channel_zhenlong.f_character_channel_count
	 *
	 */
	public void setCharacterChannelCount(Integer characterChannelCount) {
		this.characterChannelCount = characterChannelCount;
	}

	/**
	 * 个人得到的经验 t_channel_zhenlong.f_channel_exp
	 * @return  the value of t_channel_zhenlong.f_channel_exp
	 *
	 */
	public Integer getChannelExp() {
		return channelExp;
	}

	/**
	 * 个人得到的经验 t_channel_zhenlong.f_channel_exp
	 * @param channelExp  the value for t_channel_zhenlong.f_channel_exp
	 *
	 */
	public void setChannelExp(Integer channelExp) {
		this.channelExp = channelExp;
	}

	/**
	 * 最后一个穴位的时间 t_channel_zhenlong.f_channel_congxue_time
	 * @return  the value of t_channel_zhenlong.f_channel_congxue_time
	 *
	 */
	public Integer getChannelCongxueTime() {
		return channelCongxueTime;
	}

	/**
	 * 最后一个穴位的时间 t_channel_zhenlong.f_channel_congxue_time
	 * @param channelCongxueTime  the value for t_channel_zhenlong.f_channel_congxue_time
	 *
	 */
	public void setChannelCongxueTime(Integer channelCongxueTime) {
		this.channelCongxueTime = channelCongxueTime;
	}
}
