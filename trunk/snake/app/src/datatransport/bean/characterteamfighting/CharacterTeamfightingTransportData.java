package datatransport.bean.characterteamfighting;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class CharacterTeamfightingTransportData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.id == null ? Integer.MIN_VALUE : this.id);
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.teamfightingId == null ? Integer.MIN_VALUE : this.teamfightingId);
		out.writeObject(this.learnTime);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.id = in.readInt();
		this.id = this.id == Integer.MIN_VALUE ? null : this.id;
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.teamfightingId = in.readInt();
		this.teamfightingId = this.teamfightingId == Integer.MIN_VALUE ? null : this.teamfightingId;
		this.learnTime = (Date) in.readObject();
	}

	/**
	 * t_character_teamfighting.id
	 * 
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	private Integer id;
	/**
	 * 角色id t_character_teamfighting.character_id
	 * 
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	private Integer characterId;
	/**
	 * 阵法id t_character_teamfighting.teamfighting_id
	 * 
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	private Integer teamfightingId;
	/**
	 * 学习事件 t_character_teamfighting.learn_time
	 * 
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	private Date learnTime;

	/**
	 * t_character_teamfighting.id
	 * 
	 * @return the value of t_character_teamfighting.id
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_teamfighting.id
	 * 
	 * @param id
	 *            the value for t_character_teamfighting.id
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色id t_character_teamfighting.character_id
	 * 
	 * @return the value of t_character_teamfighting.character_id
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 角色id t_character_teamfighting.character_id
	 * 
	 * @param characterId
	 *            the value for t_character_teamfighting.character_id
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 阵法id t_character_teamfighting.teamfighting_id
	 * 
	 * @return the value of t_character_teamfighting.teamfighting_id
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public Integer getTeamfightingId() {
		return teamfightingId;
	}

	/**
	 * 阵法id t_character_teamfighting.teamfighting_id
	 * 
	 * @param teamfightingId
	 *            the value for t_character_teamfighting.teamfighting_id
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public void setTeamfightingId(Integer teamfightingId) {
		this.teamfightingId = teamfightingId;
	}

	/**
	 * 学习事件 t_character_teamfighting.learn_time
	 * 
	 * @return the value of t_character_teamfighting.learn_time
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public Date getLearnTime() {
		return learnTime;
	}

	/**
	 * 学习事件 t_character_teamfighting.learn_time
	 * 
	 * @param learnTime
	 *            the value for t_character_teamfighting.learn_time
	 * @ibatorgenerated Sat Jun 25 05:47:43 CST 2011
	 */
	public void setLearnTime(Date learnTime) {
		this.learnTime = learnTime;
	}
}
