package net.snake.gamemodel.fight.bean;

import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.persistence.SkillEffectManager;
import net.snake.ibatis.IbatisEntity;

public class XuanyuanBufferJiacheng implements IbatisEntity {

	/**
	 * buffer id t_xuanyuan_buffer_jiacheng.f_buffer_id
	 * 
	 */
	private Integer bufferId;
	/**
	 * 生命上限最大加成 t_xuanyuan_buffer_jiacheng.f_hp
	 * 
	 */
	private Integer hp;
	/**
	 * 攻击最大加成 t_xuanyuan_buffer_jiacheng.f_atk
	 * 
	 */
	private Integer atk;
	/**
	 * 防御最大加成 t_xuanyuan_buffer_jiacheng.f_def
	 * 
	 */
	private Integer def;
	/**
	 * 内力上限最大加成 t_xuanyuan_buffer_jiacheng.f_mp
	 * 
	 */
	private Integer mp;
	/**
	 * 体力上限最大加成 t_xuanyuan_buffer_jiacheng.f_sp
	 * 
	 */
	private Integer sp;
	/**
	 * 攻速最大加成（千分比） t_xuanyuan_buffer_jiacheng.f_atk_speed
	 * 
	 */
	private Integer atkSpeed;
	/**
	 * 移速最大加成 t_xuanyuan_buffer_jiacheng.f_move_speed
	 * 
	 */
	private Integer moveSpeed;
	/**
	 * 暴击最大加成 t_xuanyuan_buffer_jiacheng.f_crt
	 * 
	 */
	private Integer crt;
	/**
	 * 闪避加成 t_xuanyuan_buffer_jiacheng.f_dodge
	 * 
	 */
	private Integer dodge;

	/**
	 * buffer id t_xuanyuan_buffer_jiacheng.f_buffer_id
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_buffer_id
	 */
	public Integer getBufferId() {
		return bufferId;
	}

	/**
	 * buffer id t_xuanyuan_buffer_jiacheng.f_buffer_id
	 * 
	 * @param bufferId
	 *            the value for t_xuanyuan_buffer_jiacheng.f_buffer_id
	 */
	public void setBufferId(Integer bufferId) {
		this.bufferId = bufferId;
	}

	/**
	 * 生命上限最大加成 t_xuanyuan_buffer_jiacheng.f_hp
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_hp
	 */
	public Integer getHp() {
		return hp;
	}

	/**
	 * 生命上限最大加成 t_xuanyuan_buffer_jiacheng.f_hp
	 * 
	 * @param hp
	 *            the value for t_xuanyuan_buffer_jiacheng.f_hp
	 */
	public void setHp(Integer hp) {
		this.hp = hp;
	}

	/**
	 * 攻击最大加成 t_xuanyuan_buffer_jiacheng.f_atk
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_atk
	 */
	public Integer getAtk() {
		return atk;
	}

	/**
	 * 攻击最大加成 t_xuanyuan_buffer_jiacheng.f_atk
	 * 
	 * @param atk
	 *            the value for t_xuanyuan_buffer_jiacheng.f_atk
	 */
	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	/**
	 * 防御最大加成 t_xuanyuan_buffer_jiacheng.f_def
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_def
	 */
	public Integer getDef() {
		return def;
	}

	/**
	 * 防御最大加成 t_xuanyuan_buffer_jiacheng.f_def
	 * 
	 * @param def
	 *            the value for t_xuanyuan_buffer_jiacheng.f_def
	 */
	public void setDef(Integer def) {
		this.def = def;
	}

	/**
	 * 内力上限最大加成 t_xuanyuan_buffer_jiacheng.f_mp
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_mp
	 */
	public Integer getMp() {
		return mp;
	}

	/**
	 * 内力上限最大加成 t_xuanyuan_buffer_jiacheng.f_mp
	 * 
	 * @param mp
	 *            the value for t_xuanyuan_buffer_jiacheng.f_mp
	 */
	public void setMp(Integer mp) {
		this.mp = mp;
	}

	/**
	 * 体力上限最大加成 t_xuanyuan_buffer_jiacheng.f_sp
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_sp
	 */
	public Integer getSp() {
		return sp;
	}

	/**
	 * 体力上限最大加成 t_xuanyuan_buffer_jiacheng.f_sp
	 * 
	 * @param sp
	 *            the value for t_xuanyuan_buffer_jiacheng.f_sp
	 */
	public void setSp(Integer sp) {
		this.sp = sp;
	}

	/**
	 * 攻速最大加成（千分比） t_xuanyuan_buffer_jiacheng.f_atk_speed
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_atk_speed
	 */
	public Integer getAtkSpeed() {
		return atkSpeed;
	}

	/**
	 * 攻速最大加成（千分比） t_xuanyuan_buffer_jiacheng.f_atk_speed
	 * 
	 * @param atkSpeed
	 *            the value for t_xuanyuan_buffer_jiacheng.f_atk_speed
	 */
	public void setAtkSpeed(Integer atkSpeed) {
		this.atkSpeed = atkSpeed;
	}

	/**
	 * 移速最大加成 t_xuanyuan_buffer_jiacheng.f_move_speed
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_move_speed
	 */
	public Integer getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * 移速最大加成 t_xuanyuan_buffer_jiacheng.f_move_speed
	 * 
	 * @param moveSpeed
	 *            the value for t_xuanyuan_buffer_jiacheng.f_move_speed
	 */
	public void setMoveSpeed(Integer moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * 暴击最大加成 t_xuanyuan_buffer_jiacheng.f_crt
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_crt
	 */
	public Integer getCrt() {
		return crt;
	}

	/**
	 * 暴击最大加成 t_xuanyuan_buffer_jiacheng.f_crt
	 * 
	 * @param crt
	 *            the value for t_xuanyuan_buffer_jiacheng.f_crt
	 */
	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	/**
	 * 闪避加成 t_xuanyuan_buffer_jiacheng.f_dodge
	 * 
	 * @return the value of t_xuanyuan_buffer_jiacheng.f_dodge
	 */
	public Integer getDodge() {
		return dodge;
	}

	/**
	 * 闪避加成 t_xuanyuan_buffer_jiacheng.f_dodge
	 * 
	 * @param dodge
	 *            the value for t_xuanyuan_buffer_jiacheng.f_dodge
	 */
	public void setDodge(Integer dodge) {
		this.dodge = dodge;
	}

	private EffectInfo buffer;

	public EffectInfo getBuffer() {
		if (buffer == null) {
			SkillEffect se = SkillEffectManager.getInstance().getSkillEffectById(this.bufferId);
			buffer = new EffectInfo(se);
		}
		return buffer;
	}
}
