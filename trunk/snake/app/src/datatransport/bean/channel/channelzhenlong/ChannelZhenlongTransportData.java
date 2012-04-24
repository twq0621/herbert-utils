package datatransport.bean.channel.channelzhenlong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ChannelZhenlongTransportData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.characterChannelCount == null ? Integer.MIN_VALUE : this.characterChannelCount);
		out.writeInt(this.channelExp == null ? Integer.MIN_VALUE : this.channelExp);
		out.writeInt(this.channelCongxueTime == null ? Integer.MIN_VALUE : this.channelCongxueTime);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.characterChannelCount = in.readInt();
		this.characterChannelCount = this.characterChannelCount == Integer.MIN_VALUE ? null : this.characterChannelCount;
		this.channelExp = in.readInt();
		this.channelExp = this.channelExp == Integer.MIN_VALUE ? null : this.channelExp;
		this.channelCongxueTime = in.readInt();
		this.channelCongxueTime = this.channelCongxueTime == Integer.MIN_VALUE ? null : this.channelCongxueTime;
	}
    /**
     *  t_channel_zhenlong.f_character_id
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    private Integer characterId;

    /**
     * 人物充斤脉的的次数 t_channel_zhenlong.f_character_channel_count
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    private Integer characterChannelCount;

    /**
     * 个人得到的经验 t_channel_zhenlong.f_channel_exp
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    private Integer channelExp;

    /**
     * 最后一个穴位的时间 t_channel_zhenlong.f_channel_congxue_time
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    private Integer channelCongxueTime;

    /**
     *  t_channel_zhenlong.f_character_id
     *
     * @return the value of t_channel_zhenlong.f_character_id
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public Integer getCharacterId() {
        return characterId;
    }

    /**
     *  t_channel_zhenlong.f_character_id
     *
     * @param characterId the value for t_channel_zhenlong.f_character_id
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    /**
     * 人物充斤脉的的次数 t_channel_zhenlong.f_character_channel_count
     *
     * @return the value of t_channel_zhenlong.f_character_channel_count
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public Integer getCharacterChannelCount() {
        return characterChannelCount;
    }

    /**
     * 人物充斤脉的的次数 t_channel_zhenlong.f_character_channel_count
     *
     * @param characterChannelCount the value for t_channel_zhenlong.f_character_channel_count
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public void setCharacterChannelCount(Integer characterChannelCount) {
        this.characterChannelCount = characterChannelCount;
    }

    /**
     * 个人得到的经验 t_channel_zhenlong.f_channel_exp
     *
     * @return the value of t_channel_zhenlong.f_channel_exp
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public Integer getChannelExp() {
        return channelExp;
    }

    /**
     * 个人得到的经验 t_channel_zhenlong.f_channel_exp
     *
     * @param channelExp the value for t_channel_zhenlong.f_channel_exp
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public void setChannelExp(Integer channelExp) {
        this.channelExp = channelExp;
    }

    /**
     * 最后一个穴位的时间 t_channel_zhenlong.f_channel_congxue_time
     *
     * @return the value of t_channel_zhenlong.f_channel_congxue_time
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public Integer getChannelCongxueTime() {
        return channelCongxueTime;
    }

    /**
     * 最后一个穴位的时间 t_channel_zhenlong.f_channel_congxue_time
     *
     * @param channelCongxueTime the value for t_channel_zhenlong.f_channel_congxue_time
     *
     * @ibatorgenerated Sat Jun 25 04:39:16 CST 2011
     */
    public void setChannelCongxueTime(Integer channelCongxueTime) {
        this.channelCongxueTime = channelCongxueTime;
    }
}
