package net.snake.ai.fight.bean;

import java.util.List;

import net.snake.commons.script.SFighterVO;
import net.snake.gamemodel.hero.bean.VisibleObject;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.skill.bean.Skill;
import net.snake.gamemodel.skill.bean.SkillEffect;
import net.snake.gamemodel.skill.logic.CharacterSkill;

public class FighterVO implements SFighterVO {
	// private static Logger logger = Logger.getLogger(FighterVO.class);
	// private int constructType =0;

	private boolean forceAttack;

	private VisibleObject sponsor;// 发起者

	private VisibleObject fighter;// 攻击者(施加者)

	private VisibleObject recipient;// 承受者(效果对象)

	// private Skill skill;//技能

	private CharacterSkill characterSkill;// 身上的技能

	// private boolean isDaoqi = true;//true为刀起，false为刀落

	private int maxHurtNum;// 最大的伤害人数

	private int hurtValue;// 本次造成的伤害

	private int hiddenValue;// 暗器照成的伤害

	private int baoji;// 是否暴击

	private Skill skill;// 技能

	private short x;// 点技能释放位置

	private short y;// 点技能释放位置

	private CharacterHiddenWeapon characterHiddenWeapon;// 暗器

	List<VisibleObject> visibleObjects;// 作用在谁身上

	private boolean isHurt = true;// 是否产生伤害

	private boolean isDeBuff = false;// 是否产生debuff

	private float hurt_decay_coefficient = 0;// 伤害的系数衰减

	public void destroy() {
		// sponsor = null;
		// fighter = null;
		// recipient = null;
		// characterSkill = null;
		if (visibleObjects != null) {
			visibleObjects.clear();
			// visibleObjects = null;
		}
	}

	public float getHurt_decay_coefficient() {
		return hurt_decay_coefficient;
	}

	public void setHurt_decay_coefficient(float hurt_decay_coefficient) {
		this.hurt_decay_coefficient = hurt_decay_coefficient;
	}

	/**
	 * 是否产生debuff
	 * 
	 * @return
	 */
	public boolean isDeBuff() {
		return isDeBuff;
	}

	/**
	 * 设置是否产生dubuff
	 * 
	 * @param isDeBuff
	 */
	public void setDeBuff(boolean isDeBuff) {
		this.isDeBuff = isDeBuff;
	}

	/**
	 * 是否产生伤害
	 * 
	 * @return
	 */
	public boolean isHurt() {
		return isHurt;
	}

	/**
	 * 获得暗器带来的伤害值
	 * 
	 * @return
	 */
	public int getHiddenValue() {
		return hiddenValue;
	}

	public void setHiddenValue(int hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	/**
	 * 作用在谁身上
	 * 
	 * @return
	 */
	public List<VisibleObject> getVisibleObjects() {
		return visibleObjects;
	}

	public void setVisibleObjects(List<VisibleObject> visibleObjects) {
		this.visibleObjects = visibleObjects;
	}

	/**
	 * 获得暗器
	 */
	public CharacterHiddenWeapon getCharacterHiddenWeapon() {
		return characterHiddenWeapon;
	}

	public void setCharacterHiddenWeapon(CharacterHiddenWeapon characterHiddenWeapon) {
		this.characterHiddenWeapon = characterHiddenWeapon;
	}

	/**
	 * 创建一个战斗vo
	 * 
	 * @param sponsor
	 *            发起者
	 * @param fighter
	 *            起效者
	 * @param recipient
	 *            受用者
	 * @param characterSkill
	 *            角色技能
	 */
	public FighterVO(VisibleObject sponsor, VisibleObject fighter, VisibleObject recipient, CharacterSkill characterSkill) {
		this.sponsor = sponsor;
		this.fighter = fighter;
		this.recipient = recipient;
		this.characterSkill = characterSkill;

	}

	/**
	 * 创建一个战斗vo
	 * 
	 * @param sponsor
	 *            发起者
	 * @param fighter
	 *            起效者
	 * @param x
	 *            坐标x
	 * @param y
	 *            坐标y
	 * @param characterSkill
	 *            角色技能
	 */
	public FighterVO(VisibleObject sponsor, VisibleObject fighter, short x, short y, CharacterSkill characterSkill) {
		this.sponsor = sponsor;
		this.fighter = fighter;
		this.x = x;
		this.y = y;
		this.characterSkill = characterSkill;
	}

	/**
	 * 创建一个战斗vo
	 * 
	 * @param sponsor
	 *            发起者
	 * @param fighter
	 *            起效者
	 * @param recipient
	 *            受用者
	 * @param skill
	 *            技能
	 */
	public FighterVO(VisibleObject sponsor, VisibleObject fighter, VisibleObject recipient, Skill skill) {
		this.sponsor = sponsor;
		this.fighter = fighter;
		this.recipient = recipient;
		this.skill = skill;
	}

	public FighterVO(VisibleObject sponsor, VisibleObject fighter, VisibleObject recipient) {
		this.sponsor = sponsor;
		this.fighter = fighter;
		this.recipient = recipient;
	}

	public short getX() {
		return x;
	}

	public short getY() {
		return y;
	}

	/**
	 * 技能关联的效果
	 * 
	 * @return
	 */
	public SkillEffect getEffect() {
		if (getSkill() != null) {
			return getSkill().getEffect();
		}
		return null;
	}

	/**
	 * 技能带来的仇恨值
	 * 
	 * @return
	 */
	public int getEnmityValue() {
		if (getCharacterSkill() == null)
			return 0;
		return getCharacterSkill().getSkill().getEnmityValue();
	}

	public int getBaoji() {
		return baoji;
	}

	public void setBaoji(int baoji) {
		this.baoji = baoji;
	}

	public void setX(short x) {
		this.x = x;
	}

	public void setY(short y) {
		this.y = y;
	}

	public int getHurtValue() {
		return hurtValue;
	}

	public void setHurtValue(int hurtValue) {
		this.hurtValue = hurtValue;
	}

	public void setSponsor(VisibleObject sponsor) {
		this.sponsor = sponsor;
	}

	// public void setFighter(VisibleObject fighter) {
	// this.fighter = fighter;
	// }

	/**
	 * 承受者(效果对象)
	 * 
	 * @param recipient
	 */
	public void setRecipient(VisibleObject recipient) {
		this.recipient = recipient;
	}

	/**
	 * 发起者
	 */
	public VisibleObject getSponsor() {
		return sponsor;
	}

	/**
	 * 起效者
	 * 
	 * @return
	 */
	public VisibleObject getFighter() {
		return fighter;
	}

	/**
	 * 受用者、承受者(效果对象)
	 * 
	 * @return
	 */
	public VisibleObject getRecipient() {
		return recipient;
	}

	/**
	 * skill技能
	 * 
	 * @return
	 */
	public Skill getSkill() {
		if (skill != null)
			return skill;
		if (characterSkill == null)
			return null;
		if (skill == null) {
			skill = characterSkill.getSkill();
		}
		return skill;
	}

	public CharacterSkill getCharacterSkill() {
		return characterSkill;
	}

	/**
	 * 最大伤害数量限制
	 * 
	 * @return
	 */
	public int getMaxHurtNum() {
		return maxHurtNum;
	}

	public void setMaxHurtNum(int maxHurtNum) {
		this.maxHurtNum = maxHurtNum;
	}

	/**
	 * 是否是强制攻击
	 * 
	 * @return
	 */
	public boolean isForceAttack() {
		return forceAttack;
	}

	public void forceAttack(boolean force) {
		forceAttack = force;
	}
}
