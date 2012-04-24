package net.snake.consts;

import net.snake.commons.Language;

/**
 * 
 * 1增加攻击力,2增加防御力,3增加暴击,4增加闪避,5增加生命上限值,6增加体力上限值,7增加内力上限值,8增加攻击速度,9增加移动速度,10命中
 * 人物属性
 * 
 * @author serv_dev
 * 
 */
public enum Property {

	/**
	 * 攻击力
	 */
	attack(1),
	/**
	 * 防御
	 */
	defence(2),
	/**
	 * 暴击
	 */
	crt(3),
	/**
	 * 闪避
	 */
	dodge(4),
	/**
	 * 生命值上限
	 */
	maxHp(5),
	/**
	 * 体力值上限
	 */
	maxSp(6),
	/**
	 * 内力上限
	 */
	maxMp(7),
	/**
	 * 攻击速度
	 */
	attackspeed(8),
	/**
	 * 移动速度
	 */
	movespeed(9),
	/**
	 * 命中
	 */
	hit(10),
	/**
	 * 攻击、防御、生命上限、法力上限，暴击、闪避
	 */
	all(100),
	/**
	 * 攻击力增强
	 */
	GJL(20),
	/**
	 * 反弹伤害
	 */
	FTSH(21),
	/**
	 * 忽视防御
	 */
	HSFY(22),
	/**
	 * 伤害减免
	 */
	SHJM(23),
	/**
	 * 暗器几率
	 */
	AQJV(25),
	/**
	 * 暗器免伤
	 */
	AQMS(24),

	/**
	 * 降低其他各门派玩家对您产生爆击伤害(1/1000)
	 */
	all_crt(25),
	/**
	 * 降低武当派玩家对您产生爆击伤害(1/1000)
	 */
	wudan_crt(26),
	/**
	 * 降低白驼山玩家对您产生爆击伤害(1/1000)
	 */
	baituoshan_crt(27),
	/**
	 * 降低桃花岛玩家对您产生爆击伤害(1/1000)
	 */
	taohuadao_crt(28),
	/**
	 * 降低古墓派玩家对您产生爆击伤害(1/1000)
	 */
	gumu_crt(29),
	/**
	 * 降低少林派玩家对您产生爆击伤害(1/1000)
	 */
	shaolin_crt(30),
	/**
	 * 降低您受到其他玩家暗器技能攻击几率(1/1000)
	 */
	reduce_aqJv(31),
	/**
	 * 降低您受到其他玩家暗器伤害几率(1/1000)
	 */
	reduce_aqHurt(32),
	/**
	 * 降低您受到其他玩家弓箭箭术技能攻击几率(1/1000)
	 */
	reduce_gong(33),
	/**
	 * 降低您受到其他玩家丹田武学的攻击几率(1/1000)
	 */
	reduce_dantian(34);

	/**
	 * 中文描述
	 * 
	 * @param pro
	 * @return
	 */
	public static String getPropertyStr(Property pro) {
		switch (pro) {
		case attack:
			return Language.ATTACK;
		case defence:
			return Language.DEFENCE;
		case crt:
			return Language.CRT;
		case dodge:
			return Language.DODGE;
		case attackspeed:
			return Language.ATTACKSPEED;
		case movespeed:
			return Language.MOVESPEED;
		case maxHp:
			return Language.MAXHP;
		case maxMp:
			return Language.MAXMP;
		case maxSp:
			return Language.MAXSP;
		case GJL:
			return Language.GJL;
		case FTSH:
			return Language.FTSH;
		case HSFY:
			return Language.HSFY;
		case SHJM:
			return Language.SHJM;
		case AQMS:
			return Language.AQMS;
		case AQJV:
			return Language.AQJV;
		default:
			return "";
		}
	}

	private int num;// 在数据库的标记

	private Property(int num) {
		this.num = num;
	}

	public int getNum() {
		return this.num;
	}

	public static Property getpPropertyByEffectType(int type) {
		Property property = null;
		switch (type) {
		case EffectType.movespeed:
			property = Property.movespeed;
			break;
		case EffectType.attackspeed:
			property = Property.attackspeed;
			break;
		case EffectType.maxMp:
			property = Property.maxMp;
			break;
		case EffectType.maxHp:
			property = Property.maxHp;
			break;
		case EffectType.maxSp:
			property = Property.maxSp;
			break;
		case EffectType.crt:
			property = Property.crt;
			break;
		case EffectType.dodge:
			property = Property.dodge;
			break;
		case EffectType.attack:
			property = Property.attack;
			break;
		case EffectType.defence:
			property = Property.defence;
			break;
		case EffectType.appendAllPe:
			property = Property.all;
			break;
		case EffectType.hit:
			property = Property.hit;
			break;			
		default:
			break;
		}
		return property;
	}

	public static Property getProperty(int num) {
		switch (num) {
		case 1:
			return Property.attack;
		case 2:
			return Property.defence;
		case 3:
			return Property.crt;
		case 4:
			return Property.dodge;
		case 5:
			return Property.maxHp;
		case 6:
			return Property.maxSp;
		case 7:
			return Property.maxMp;
		case 8:
			return Property.attackspeed;
		case 9:
			return Property.movespeed;
		case 10:
			return Property.hit;
		case 20:
			return Property.GJL;
		case 21:
			return Property.FTSH;
		case 22:
			return Property.HSFY;
		case 23:
			return Property.SHJM;
		case 24:
			return Property.AQMS;
		case 25:
			return Property.AQJV;
		default:
			return null;
		}
	}
}
