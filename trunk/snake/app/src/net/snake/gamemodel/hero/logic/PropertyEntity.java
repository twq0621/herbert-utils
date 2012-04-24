package net.snake.gamemodel.hero.logic;

import net.snake.consts.Popsinger;
import net.snake.consts.Property;
import net.snake.commons.script.SPropertyEntity;

public class PropertyEntity implements SPropertyEntity {

	private int maxHp;
	private int maxMp;
	private int maxSp;
	private int attack;
	private int defend;
	private int crt;
	private int dodge;
	private int moveSpeed;
	private int attackSpeed;
	private int hit;
	/**
	 * 攻击力增强
	 */
	private int gjl;
	/**
	 * 反弹伤害 百分比
	 */
	private int ftsh;
	/**
	 * 忽视防御
	 */
	private int hsfy;
	/**
	 * 伤害减免　百分比
	 */
	private int shjm;
	/**
	 * 暗器免伤
	 */
	private int aqms;
	/**
	 * 暗器几率 万分比 (1/1000)
	 */
	private int aqJv;
	/**
	 * 降低其他各门派玩家对您产生爆击伤害(1/1000)
	 */
	private int all_crt;

	/**
	 * 降低武当派玩家对您产生爆击伤害(1/1000)
	 */
	private int zhanshi_crt;

	/**
	 * 降低桃花岛玩家对您产生爆击伤害(1/1000)
	 */
	private int fashi_crt;

	/**
	 * 降低古墓派玩家对您产生爆击伤害(1/1000)
	 */
	private int yueshi_crt;

	/**
	 * 降低少林派玩家对您产生爆击伤害(1/1000)
	 */
	private int daoshi_crt;

	/**
	 * 降低您受到其他玩家暗器技能攻击几率(1/1000)
	 */
	private int reduce_aqJv;
	/**
	 * 降低您受到其他玩家暗器伤害几率(1/1000)
	 */
	private int reduce_aqHurt;
	/**
	 * 降低您受到其他玩家弓箭箭术技能攻击几率(1/1000)
	 */
	private int reduce_gong;

	/**
	 * 降低您受到其他玩家丹田武学的攻击几率(1/1000)
	 */
	private int reduce_dantian;

	public int getAll_crt() {
		return all_crt;
	}

	public int getReduceCrtMenPai(int menpai) {
		Popsinger p = Popsinger.getPopsinger(menpai);
		switch (p) {
		case guming:
			return daoshi_crt;
		case yunyue:
			return yueshi_crt;
		case futu:
			return zhanshi_crt;
		case miaoyu:
			return fashi_crt;
		default:
			break;
		}
		return 0;
	}

	public void setAll_crt(int all_crt) {
		this.all_crt = all_crt;
	}

	public int getWudan_crt() {
		return zhanshi_crt;
	}

	public void setWudan_crt(int wudan_crt) {
		this.zhanshi_crt = wudan_crt;
	}

	public int getTaohuadao_crt() {
		return fashi_crt;
	}

	public void setTaohuadao_crt(int taohuadao_crt) {
		this.fashi_crt = taohuadao_crt;
	}

	public int getGumu_crt() {
		return yueshi_crt;
	}

	public void setGumu_crt(int gumu_crt) {
		this.yueshi_crt = gumu_crt;
	}

	public int getShaolin_crt() {
		return daoshi_crt;
	}

	public void setShaolin_crt(int shaolin_crt) {
		this.daoshi_crt = shaolin_crt;
	}

	public int getReduce_aqJv() {
		return reduce_aqJv;
	}

	public void setReduce_aqJv(int reduce_aqJv) {
		this.reduce_aqJv = reduce_aqJv;
	}

	public int getReduce_aqHurt() {
		return reduce_aqHurt;
	}

	public void setReduce_aqHurt(int reduce_aqHurt) {
		this.reduce_aqHurt = reduce_aqHurt;
	}

	public int getReduce_gong() {
		return reduce_gong;
	}

	public void setReduce_gong(int reduce_gong) {
		this.reduce_gong = reduce_gong;
	}

	public int getReduce_dantian() {
		return reduce_dantian;
	}

	public void setReduce_dantian(int reduce_dantian) {
		this.reduce_dantian = reduce_dantian;
	}

	public int getAqJv() {
		return aqJv;
	}

	public void setAqJv(int aqJv) {
		this.aqJv = aqJv;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getMaxMp() {
		return maxMp;
	}

	public void setMaxMp(int maxMp) {
		this.maxMp = maxMp;
	}

	public int getMaxSp() {
		return maxSp;
	}

	public void setMaxSp(int maxSp) {
		this.maxSp = maxSp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefend() {
		return defend;
	}

	public void setDefend(int defend) {
		this.defend = defend;
	}

	public int getCrt() {
		return crt;
	}

	public void setCrt(int crt) {
		this.crt = crt;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getGjl() {
		return gjl;
	}

	public void setGjl(int gjl) {
		this.gjl = gjl;
	}

	public int getFtsh() {
		return ftsh;
	}

	public void setFtsh(int ftsh) {
		this.ftsh = ftsh;
	}

	public int getHsfy() {
		return hsfy;
	}

	public void setHsfy(int hsfy) {
		this.hsfy = hsfy;
	}

	public int getShjm() {
		return shjm;
	}

	public void setShjm(int shjm) {
		this.shjm = shjm;
	}

	public int getAqms() {
		return aqms;
	}

	public void setAqms(int aqms) {
		this.aqms = aqms;
	}

	protected void clearData() {
		maxHp = 0;
		maxMp = 0;
		maxSp = 0;
		attack = 0;
		defend = 0;
		crt = 0;
		dodge = 0;
		moveSpeed = 0;
		attackSpeed = 0;
		hit = 0;
		gjl = 0;
		ftsh = 0;
		hsfy = 0;
		shjm = 0;
		aqms = 0;
		aqJv = 0;
		all_crt = 0;
		zhanshi_crt = 0;
		fashi_crt = 0;
		yueshi_crt = 0;
		daoshi_crt = 0;
		reduce_aqJv = 0;
		reduce_aqHurt = 0;
		reduce_gong = 0;
		reduce_dantian = 0;
	}

	public void addPropertyEntity(PropertyEntity propertyentity) {
		if (propertyentity == null) {
			return;
		}
		maxHp = maxHp + propertyentity.maxHp;
		maxMp = maxMp + propertyentity.maxMp;
		maxSp = maxSp + propertyentity.maxSp;
		attack = attack + propertyentity.attack;
		defend = defend + propertyentity.defend;
		crt = crt + propertyentity.crt;
		dodge = dodge + propertyentity.dodge;
		moveSpeed = moveSpeed + propertyentity.moveSpeed;
		attackSpeed = attackSpeed + propertyentity.attackSpeed;
		hit = hit + propertyentity.hit;
		gjl = gjl + propertyentity.gjl;
		ftsh = ftsh + propertyentity.ftsh;
		shjm = shjm + propertyentity.shjm;
		hsfy = hsfy + propertyentity.hsfy;
		aqms = aqms + propertyentity.aqms;
		aqJv = aqJv + propertyentity.aqJv;
		all_crt = all_crt + propertyentity.all_crt;
		zhanshi_crt = zhanshi_crt + propertyentity.zhanshi_crt;
		fashi_crt = fashi_crt + propertyentity.fashi_crt;
		yueshi_crt = yueshi_crt + propertyentity.yueshi_crt;
		daoshi_crt = daoshi_crt + propertyentity.daoshi_crt;
		reduce_aqJv = reduce_aqJv + propertyentity.reduce_aqJv;
		reduce_aqHurt = reduce_aqHurt + propertyentity.reduce_aqHurt;
		reduce_gong = reduce_gong + propertyentity.reduce_gong;
		reduce_dantian = reduce_dantian + propertyentity.reduce_dantian;
	}

	protected void minusPropertyEntity(PropertyEntity propertyentity) {
		if (propertyentity == null) {
			return;
		}
		maxHp = maxHp - propertyentity.maxHp;
		maxMp = maxMp - propertyentity.maxMp;
		maxSp = maxSp - propertyentity.maxSp;
		attack = attack - propertyentity.attack;
		defend = defend - propertyentity.defend;
		crt = crt - propertyentity.crt;
		dodge = dodge - propertyentity.dodge;
		moveSpeed = moveSpeed - propertyentity.moveSpeed;
		attackSpeed = attackSpeed - propertyentity.attackSpeed;
		hit = hit - propertyentity.hit;
		gjl = gjl - propertyentity.gjl;
		ftsh = ftsh - propertyentity.ftsh;
		shjm = shjm - propertyentity.shjm;
		hsfy = hsfy - propertyentity.hsfy;
		aqms = aqms - propertyentity.aqms;
		aqJv = aqJv - propertyentity.aqJv;
		all_crt = all_crt - propertyentity.all_crt;
		zhanshi_crt = zhanshi_crt - propertyentity.zhanshi_crt;
		fashi_crt = fashi_crt - propertyentity.fashi_crt;
		yueshi_crt = yueshi_crt - propertyentity.yueshi_crt;
		daoshi_crt = daoshi_crt - propertyentity.daoshi_crt;
		reduce_aqJv = reduce_aqJv - propertyentity.reduce_aqJv;
		reduce_aqHurt = reduce_aqHurt - propertyentity.reduce_aqHurt;
		reduce_gong = reduce_gong - propertyentity.reduce_gong;
		reduce_dantian = reduce_dantian - propertyentity.reduce_dantian;
	}

	public void addExtraProperty(Property property, int value) {

		switch (property) {
		case attack:
			setAttack(getAttack() + value);
			break;
		case defence:
			setDefend(getDefend() + value);
			break;
		case crt:
			setCrt(getCrt() + value);
			break;
		case dodge:
			setDodge(getDodge() + value);
			break;
		case maxHp:
			setMaxHp(getMaxHp() + value);
			break;
		case maxSp:
			setMaxSp(getMaxSp() + value);
			break;
		case movespeed:
			setMoveSpeed(getMoveSpeed() + value);
			break;
		case attackspeed:
			setAttackSpeed(getAttackSpeed() + value);
			break;
		case maxMp:
			setMaxMp(getMaxMp() + value);
			break;
		case hit:
			setHit(getHit() + value);
			break;
		case AQMS:
			setAqms(getAqms() + value);
			break;
		case FTSH:
			setFtsh(getFtsh() + value);
			break;
		case GJL:
			setGjl(getGjl() + value);
			break;
		case HSFY:
			setHsfy(getHsfy() + value);
			break;
		case SHJM:
			setShjm(getShjm() + value);
			break;
		case AQJV:
			setAqJv(getAqJv() + value);
			break;
		case all_crt:
			setAll_crt(getAll_crt() + value);
			break;
		case wudan_crt:
			setWudan_crt(getWudan_crt() + value);
			break;
		case taohuadao_crt:
			setTaohuadao_crt(getTaohuadao_crt() + value);
			break;
		case gumu_crt:
			setGumu_crt(getGumu_crt() + value);
			break;
		case shaolin_crt:
			setShaolin_crt(getShaolin_crt() + value);
			break;
		case reduce_aqJv:
			setReduce_aqJv(getReduce_aqJv() + value);
			break;
		case reduce_aqHurt:
			setReduce_aqHurt(getReduce_aqHurt() + value);
			break;
		case reduce_gong:
			setReduce_gong(getReduce_gong() + value);
			break;
		case reduce_dantian:
			setReduce_dantian(getReduce_dantian() + value);
			break;
		default:
			break;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("攻击力:").append(attack).append("  ,防御力:").append(defend);
		sb.append("  ,最大血量:").append(maxHp).append("  ,最大魔法量:").append(maxMp);
		sb.append("  ,最大体力:").append(maxSp).append("  ,最大暴击值:").append(crt);
		sb.append("  ,最大闪避值:").append(dodge).append("  ,攻击速度:").append(attackSpeed);
		sb.append("  ,移动速度:").append(moveSpeed).append("  ,攻击增强:").append(gjl);
		sb.append("  %,反弹伤害:").append(ftsh).append("  %,忽视防御:").append(hsfy);
		sb.append("  %,伤害减免:").append(shjm).append(" %,暗器几率:").append(aqJv);
		sb.append(" /万,暗器免伤:").append(aqms).append(" %,降低其他各门派玩家对您产生爆击伤害:").append(all_crt);
		sb.append(" /万,降低桃花岛玩家对您产生爆击伤害:").append(fashi_crt).append(" /万,降低古墓派玩家对您产生爆击伤害:").append(yueshi_crt);
		sb.append(" /万,降低少林派玩家对您产生爆击伤害:").append(daoshi_crt).append(" /万,降低您受到其他玩家暗器技能攻击几率:").append(reduce_aqJv);
		sb.append(" /万,降低您受到其他玩家暗器伤害几率:").append(reduce_aqHurt);
		sb.append(" /万,降低您受到其他玩家弓箭箭术技能攻击几率:").append(reduce_gong);
		sb.append(" /万,降低您受到其他玩家丹田武学的攻击几率:").append(reduce_dantian).append(" /万");
		return sb.toString();
	}
}
