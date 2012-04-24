package datatransport.bean.characterweddingring;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
public class CharacterWeddingringTransportData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(this.id == null ? Integer.MIN_VALUE : this.id);
		out.writeInt(this.characterId == null ? Integer.MIN_VALUE : this.characterId);
		out.writeInt(this.ringId == null ? Integer.MIN_VALUE : this.ringId);
		out.writeObject(this.weddingDate);
		out.writeInt(this.partnerId == null ? Integer.MIN_VALUE : this.partnerId);
	}
	
	private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
		this.id = in.readInt();
		this.id = this.id == Integer.MIN_VALUE ? null : this.id;
		this.characterId = in.readInt();
		this.characterId = this.characterId == Integer.MIN_VALUE ? null : this.characterId;
		this.ringId = in.readInt();
		this.ringId = this.ringId == Integer.MIN_VALUE ? null : this.ringId;
		this.weddingDate = (Date)in.readObject();
		this.partnerId = in.readInt();
		this.partnerId = this.partnerId == Integer.MIN_VALUE ? null : this.partnerId;
	}
	/**
	 * t_character_weddingring.f_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	private Integer id;
	/**
	 * 婚戒领取角色 t_character_weddingring.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	private Integer characterId;
	/**
	 * 领取婚戒物品id t_character_weddingring.f_ring_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	private Integer ringId;
	/**
	 * 结婚日期 t_character_weddingring.f_wedding_date
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	private Date weddingDate;
	/**
	 * 配偶一方名字 t_character_weddingring.f_partner_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	private Integer partnerId;

	/**
	 * t_character_weddingring.f_id
	 * @return  the value of t_character_weddingring.f_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_character_weddingring.f_id
	 * @param id  the value for t_character_weddingring.f_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 婚戒领取角色 t_character_weddingring.f_character_id
	 * @return  the value of t_character_weddingring.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 婚戒领取角色 t_character_weddingring.f_character_id
	 * @param characterId  the value for t_character_weddingring.f_character_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 领取婚戒物品id t_character_weddingring.f_ring_id
	 * @return  the value of t_character_weddingring.f_ring_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public Integer getRingId() {
		return ringId;
	}

	/**
	 * 领取婚戒物品id t_character_weddingring.f_ring_id
	 * @param ringId  the value for t_character_weddingring.f_ring_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public void setRingId(Integer ringId) {
		this.ringId = ringId;
	}

	/**
	 * 结婚日期 t_character_weddingring.f_wedding_date
	 * @return  the value of t_character_weddingring.f_wedding_date
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public Date getWeddingDate() {
		return weddingDate;
	}

	/**
	 * 结婚日期 t_character_weddingring.f_wedding_date
	 * @param weddingDate  the value for t_character_weddingring.f_wedding_date
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public void setWeddingDate(Date weddingDate) {
		this.weddingDate = weddingDate;
	}

	/**
	 * 配偶一方名字 t_character_weddingring.f_partner_id
	 * @return  the value of t_character_weddingring.f_partner_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public Integer getPartnerId() {
		return partnerId;
	}

	/**
	 * 配偶一方名字 t_character_weddingring.f_partner_id
	 * @param partnerId  the value for t_character_weddingring.f_partner_id
	 * @ibatorgenerated  Fri Jun 24 09:15:10 CST 2011
	 */
	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}
}
