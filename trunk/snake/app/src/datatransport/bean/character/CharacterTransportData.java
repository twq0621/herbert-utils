package datatransport.bean.character;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class CharacterTransportData implements Serializable {
	private static final long serialVersionUID = 1L;

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(this.name == null ? "NaN" : this.name);
		out.writeInt(this.id == null ? Integer.MIN_VALUE : this.id);
		out.writeByte(this.state);
		out.writeInt(this.accountInitiallyId == null ? Integer.MIN_VALUE : this.accountInitiallyId);
		out.writeInt(this.copper == null ? Integer.MIN_VALUE : this.copper);
		out.writeInt(this.characterInitiallyId == null ? Integer.MIN_VALUE : this.characterInitiallyId);
		out.writeInt(this.originalSid == null ? Integer.MIN_VALUE : this.originalSid);
		out.writeInt(this.accountId == null ? Integer.MIN_VALUE : this.accountId);
		out.writeInt(this.popsinger == null ? Integer.MIN_VALUE : this.popsinger);
		out.writeByte(this.sex);
		out.writeByte(this.headimg);
		out.writeUTF(this.desc == null ? "NaN" : this.desc);
		out.writeShort(this.grade);
		out.writeInt(this.nowHp == null ? Integer.MIN_VALUE : this.nowHp);
		out.writeInt(this.maxHp == null ? Integer.MIN_VALUE : this.maxHp);
		out.writeInt(this.nowMp == null ? Integer.MIN_VALUE : this.nowMp);
		out.writeInt(this.maxMp == null ? Integer.MIN_VALUE : this.maxMp);
		out.writeInt(this.nowSp == null ? Integer.MIN_VALUE : this.nowSp);
		out.writeInt(this.maxSp == null ? Integer.MIN_VALUE : this.maxSp);
		out.writeLong(this.nowExperience);
		out.writeLong(this.nextExperience);
		out.writeInt(this.attack == null ? Integer.MIN_VALUE : this.attack);
		out.writeInt(this.defence == null ? Integer.MIN_VALUE : this.defence);
		out.writeInt(this.hit == null ? Integer.MIN_VALUE : this.hit);
		out.writeInt(this.crt == null ? Integer.MIN_VALUE : this.crt);
		out.writeInt(this.dodge == null ? Integer.MIN_VALUE : this.dodge);
		out.writeInt(this.atkColdtime == null ? Integer.MIN_VALUE : this.atkColdtime);
		out.writeShort(this.moveSpeed);
		out.writeInt(this.zhenqi == null ? Integer.MIN_VALUE : this.zhenqi);
		out.writeShort(this.attackAddpoint);
		out.writeShort(this.defenceAddpoint);
		out.writeShort(this.lightAddpoint);
		out.writeShort(this.strongAddpoint);
		out.writeShort(this.useablepoint);
		out.writeShort(this.potential);
		out.writeInt(this.jiaozi == null ? Integer.MIN_VALUE : this.jiaozi);
		out.writeInt(this.ingot == null ? Integer.MIN_VALUE : this.ingot);
		out.writeInt(this.integral == null ? Integer.MIN_VALUE : this.integral);
		out.writeInt(this.prestige == null ? Integer.MIN_VALUE : this.prestige);
		out.writeInt(this.nowAppellationid == null ? Integer.MIN_VALUE : this.nowAppellationid);
		out.writeInt(this.consortId == null ? Integer.MIN_VALUE : this.consortId);
		out.writeUTF(this.consortName == null ? "NaN" : this.consortName);
		out.writeShort(this.scene);
		out.writeShort(this.x);
		out.writeShort(this.y);
		out.writeInt(this.pkModel == null ? Integer.MIN_VALUE : this.pkModel);
		out.writeByte(this.isallowChat);
		out.writeObject(this.allowchatStarttime);
		out.writeObject(this.createtime);
		out.writeUTF(this.createip == null ? "NaN" : this.createip);
		out.writeObject(this.lastLogindate);
		out.writeObject(this.lastdate);
		out.writeUTF(this.lastip == null ? "NaN" : this.lastip);
		out.writeLong(this.onlinedate);
		out.writeInt(this.esotericaExp == null ? Integer.MIN_VALUE : this.esotericaExp);
		out.writeInt(this.skillid == null ? Integer.MIN_VALUE : this.skillid);
		out.writeUTF(this.instanceId == null ? "NaN" : this.instanceId);
		out.writeInt(this.lineId == null ? Integer.MIN_VALUE : this.lineId);
		out.writeUTF(this.newcomeleaderStr == null ? "NaN" : this.newcomeleaderStr);
		out.writeByte(this.iscloseCharacter);
		out.writeByte(this.isonline);
		out.writeInt(this.doubleExp == null ? Integer.MIN_VALUE : this.doubleExp);
		out.writeInt(this.prizeCishu == null ? Integer.MIN_VALUE : this.prizeCishu);
		out.writeInt(this.maxHorseAmount == null ? Integer.MIN_VALUE : this.maxHorseAmount);
		out.writeInt(this.maxStorageHorseAmount == null ? Integer.MIN_VALUE : this.maxStorageHorseAmount);
		out.writeInt(this.channelXuewei == null ? Integer.MIN_VALUE : this.channelXuewei);
		out.writeUTF(this.channelJingmai == null ? "NaN" : this.channelJingmai);
		out.writeUTF(this.channelRealdragon == null ? "NaN" : this.channelRealdragon);
		out.writeInt(this.channelBeidongExp == null ? Integer.MIN_VALUE : this.channelBeidongExp);
		out.writeShort(this.maxBagAmount);
		out.writeShort(this.maxStorageAmount);
		out.writeInt(this.storageCopper == null ? Integer.MIN_VALUE : this.storageCopper);
		out.writeInt(this.contemptCount == null ? Integer.MIN_VALUE : this.contemptCount);
		out.writeInt(this.worshipCount == null ? Integer.MIN_VALUE : this.worshipCount);
		out.writeInt(this.maxContinueKillcount == null ? Integer.MIN_VALUE : this.maxContinueKillcount);
		out.writeUTF(this.stallName == null ? "NaN" : this.stallName);
		out.writeInt(this.wuxueJingjie == null ? Integer.MIN_VALUE : this.wuxueJingjie);
		out.writeInt(this.bossKill == null ? Integer.MIN_VALUE : this.bossKill);
		out.writeUTF(this.nowXingqing == null ? "NaN" : this.nowXingqing);
		out.writeUTF(this.nowBiaoqing == null ? "NaN" : this.nowBiaoqing);
		out.writeInt(this.flowerCount == null ? Integer.MIN_VALUE : this.flowerCount);
		out.writeObject(this.biguanDate);
		out.writeInt(this.stallStatus == null ? Integer.MIN_VALUE : this.stallStatus);
		out.writeInt(this.lastRidehorse == null ? Integer.MIN_VALUE : this.lastRidehorse);
		out.writeInt(this.weekLoginCount == null ? Integer.MIN_VALUE : this.weekLoginCount);
		out.writeInt(this.dropGood == null ? Integer.MIN_VALUE : this.dropGood);
		out.writeInt(this.maxJumpCount == null ? Integer.MIN_VALUE : this.maxJumpCount);
		out.writeInt(this.dayOnline == null ? Integer.MIN_VALUE : this.dayOnline);
		out.writeInt(this.hpPercent == null ? Integer.MIN_VALUE : this.hpPercent);
		out.writeInt(this.mpPercent == null ? Integer.MIN_VALUE : this.mpPercent);
		out.writeInt(this.spPercent == null ? Integer.MIN_VALUE : this.spPercent);
		out.writeInt(this.dazuoSkill == null ? Integer.MIN_VALUE : this.dazuoSkill);
		out.writeInt(this.chongqiRecord == null ? Integer.MIN_VALUE : this.chongqiRecord);
		out.writeInt(this.deleteMark == null ? Integer.MIN_VALUE : this.deleteMark);
		out.writeInt(this.chengjiuPoint == null ? Integer.MIN_VALUE : this.chengjiuPoint);
		out.writeInt(this.contribution == null ? Integer.MIN_VALUE : this.contribution);
		out.writeObject(this.skillPoBeginTime);
		out.writeInt(this.skillPoTime == null ? Integer.MIN_VALUE : this.skillPoTime);
		out.writeUTF(this.skipNoviceNavigation == null ? "NaN" : this.skipNoviceNavigation);
		out.writeInt(this.devilcnt == null ? Integer.MIN_VALUE : this.devilcnt);
		out.writeInt(this.leijiGainLijin == null ? Integer.MIN_VALUE : this.leijiGainLijin);
		out.writeInt(this.limitOnlineTime == null ? Integer.MIN_VALUE : this.limitOnlineTime);
		out.writeInt(this.chengzhanShengwang == null ? Integer.MIN_VALUE : this.chengzhanShengwang);
		out.writeInt(this.lunjianShengwang == null ? Integer.MIN_VALUE : this.lunjianShengwang);
		out.writeInt(this.todayChengzhanShengwang == null ? Integer.MIN_VALUE : this.todayChengzhanShengwang);
		out.writeInt(this.tDantianGrade == null ? Integer.MIN_VALUE : this.tDantianGrade);
	}

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		this.name = in.readUTF();
		this.name = this.name.equals("NaN") ? null : this.name;
		this.id = in.readInt();
		this.id = this.id == Integer.MIN_VALUE ? null : this.id;
		this.state = in.readByte();
		this.accountInitiallyId = in.readInt();
		this.accountInitiallyId = this.accountInitiallyId == Integer.MIN_VALUE ? null : this.accountInitiallyId;
		this.copper = in.readInt();
		this.copper = this.copper == Integer.MIN_VALUE ? null : this.copper;
		this.characterInitiallyId = in.readInt();
		this.characterInitiallyId = this.characterInitiallyId == Integer.MIN_VALUE ? null : this.characterInitiallyId;
		this.originalSid = in.readInt();
		this.originalSid = this.originalSid == Integer.MIN_VALUE ? null : this.originalSid;
		this.accountId = in.readInt();
		this.accountId = this.accountId == Integer.MIN_VALUE ? null : this.accountId;
		this.popsinger = in.readInt();
		this.popsinger = this.popsinger == Integer.MIN_VALUE ? null : this.popsinger;
		this.sex = in.readByte();
		this.headimg = in.readByte();
		this.desc = in.readUTF();
		this.desc = this.desc.equals("NaN") ? null : this.desc;
		this.grade = in.readShort();
		this.nowHp = in.readInt();
		this.nowHp = this.nowHp == Integer.MIN_VALUE ? null : this.nowHp;
		this.maxHp = in.readInt();
		this.maxHp = this.maxHp == Integer.MIN_VALUE ? null : this.maxHp;
		this.nowMp = in.readInt();
		this.nowMp = this.nowMp == Integer.MIN_VALUE ? null : this.nowMp;
		this.maxMp = in.readInt();
		this.maxMp = this.maxMp == Integer.MIN_VALUE ? null : this.maxMp;
		this.nowSp = in.readInt();
		this.nowSp = this.nowSp == Integer.MIN_VALUE ? null : this.nowSp;
		this.maxSp = in.readInt();
		this.maxSp = this.maxSp == Integer.MIN_VALUE ? null : this.maxSp;
		this.nowExperience = in.readLong();
		this.nextExperience = in.readLong();
		this.attack = in.readInt();
		this.attack = this.attack == Integer.MIN_VALUE ? null : this.attack;
		this.defence = in.readInt();
		this.defence = this.defence == Integer.MIN_VALUE ? null : this.defence;
		this.hit = in.readInt();
		this.hit = this.hit == Integer.MIN_VALUE ? null : this.hit;
		this.crt = in.readInt();
		this.crt = this.crt == Integer.MIN_VALUE ? null : this.crt;
		this.dodge = in.readInt();
		this.dodge = this.dodge == Integer.MIN_VALUE ? null : this.dodge;
		this.atkColdtime = in.readInt();
		this.atkColdtime = this.atkColdtime == Integer.MIN_VALUE ? null : this.atkColdtime;
		this.moveSpeed = in.readShort();
		this.zhenqi = in.readInt();
		this.zhenqi = this.zhenqi == Integer.MIN_VALUE ? null : this.zhenqi;
		this.attackAddpoint = in.readShort();
		this.defenceAddpoint = in.readShort();
		this.lightAddpoint = in.readShort();
		this.strongAddpoint = in.readShort();
		this.useablepoint = in.readShort();
		this.potential = in.readShort();
		this.jiaozi = in.readInt();
		this.jiaozi = this.jiaozi == Integer.MIN_VALUE ? null : this.jiaozi;
		this.ingot = in.readInt();
		this.ingot = this.ingot == Integer.MIN_VALUE ? null : this.ingot;
		this.integral = in.readInt();
		this.integral = this.integral == Integer.MIN_VALUE ? null : this.integral;
		this.prestige = in.readInt();
		this.prestige = this.prestige == Integer.MIN_VALUE ? null : this.prestige;
		this.nowAppellationid = in.readInt();
		this.nowAppellationid = this.nowAppellationid == Integer.MIN_VALUE ? null : this.nowAppellationid;
		this.consortId = in.readInt();
		this.consortId = this.consortId == Integer.MIN_VALUE ? null : this.consortId;
		this.consortName = in.readUTF();
		this.consortName = this.consortName.equals("NaN") ? null : this.consortName;
		this.scene = in.readShort();
		this.x = in.readShort();
		this.y = in.readShort();
		this.pkModel = in.readInt();
		this.pkModel = this.pkModel == Integer.MIN_VALUE ? null : this.pkModel;
		this.isallowChat = in.readByte();
		this.allowchatStarttime = (Date) in.readObject();
		this.createtime = (Date) in.readObject();
		this.createip = in.readUTF();
		this.createip = this.createip.equals("NaN") ? null : this.createip;
		this.lastLogindate = (Date) in.readObject();
		this.lastdate = (Date) in.readObject();
		this.lastip = in.readUTF();
		this.lastip = this.lastip.equals("NaN") ? null : this.lastip;
		this.onlinedate = in.readLong();
		this.esotericaExp = in.readInt();
		this.esotericaExp = this.esotericaExp == Integer.MIN_VALUE ? null : this.esotericaExp;
		this.skillid = in.readInt();
		this.skillid = this.skillid == Integer.MIN_VALUE ? null : this.skillid;
		this.instanceId = in.readUTF();
		this.instanceId = this.instanceId.equals("NaN") ? null : this.instanceId;
		this.lineId = in.readInt();
		this.lineId = this.lineId == Integer.MIN_VALUE ? null : this.lineId;
		this.newcomeleaderStr = in.readUTF();
		this.newcomeleaderStr = this.newcomeleaderStr.equals("NaN") ? null : this.newcomeleaderStr;
		this.iscloseCharacter = in.readByte();
		this.isonline = in.readByte();
		this.doubleExp = in.readInt();
		this.doubleExp = this.doubleExp == Integer.MIN_VALUE ? null : this.doubleExp;
		this.prizeCishu = in.readInt();
		this.prizeCishu = this.prizeCishu == Integer.MIN_VALUE ? null : this.prizeCishu;
		this.maxHorseAmount = in.readInt();
		this.maxHorseAmount = this.maxHorseAmount == Integer.MIN_VALUE ? null : this.maxHorseAmount;
		this.maxStorageHorseAmount = in.readInt();
		this.maxStorageHorseAmount = this.maxStorageHorseAmount == Integer.MIN_VALUE ? null : this.maxStorageHorseAmount;
		this.channelXuewei = in.readInt();
		this.channelXuewei = this.channelXuewei == Integer.MIN_VALUE ? null : this.channelXuewei;
		this.channelJingmai = in.readUTF();
		this.channelJingmai = this.channelJingmai.equals("NaN") ? null : this.channelJingmai;
		this.channelRealdragon = in.readUTF();
		this.channelRealdragon = this.channelRealdragon.equals("NaN") ? null : this.channelRealdragon;
		this.channelBeidongExp = in.readInt();
		this.channelBeidongExp = this.channelBeidongExp == Integer.MIN_VALUE ? null : this.channelBeidongExp;
		this.maxBagAmount = in.readShort();
		this.maxStorageAmount = in.readShort();
		this.storageCopper = in.readInt();
		this.storageCopper = this.storageCopper == Integer.MIN_VALUE ? null : this.storageCopper;
		this.contemptCount = in.readInt();
		this.contemptCount = this.contemptCount == Integer.MIN_VALUE ? null : this.contemptCount;
		this.worshipCount = in.readInt();
		this.worshipCount = this.worshipCount == Integer.MIN_VALUE ? null : this.worshipCount;
		this.maxContinueKillcount = in.readInt();
		this.maxContinueKillcount = this.maxContinueKillcount == Integer.MIN_VALUE ? null : this.maxContinueKillcount;
		this.stallName = in.readUTF();
		this.stallName = this.stallName.equals("NaN") ? null : this.stallName;
		this.wuxueJingjie = in.readInt();
		this.wuxueJingjie = this.wuxueJingjie == Integer.MIN_VALUE ? null : this.wuxueJingjie;
		this.bossKill = in.readInt();
		this.bossKill = this.bossKill == Integer.MIN_VALUE ? null : this.bossKill;
		this.nowXingqing = in.readUTF();
		this.nowXingqing = this.nowXingqing.equals("NaN") ? null : this.nowXingqing;
		this.nowBiaoqing = in.readUTF();
		this.nowBiaoqing = this.nowBiaoqing.equals("NaN") ? null : this.nowBiaoqing;
		this.flowerCount = in.readInt();
		this.flowerCount = this.flowerCount == Integer.MIN_VALUE ? null : this.flowerCount;
		this.biguanDate = (Date) in.readObject();
		this.stallStatus = in.readInt();
		this.stallStatus = this.stallStatus == Integer.MIN_VALUE ? null : this.stallStatus;
		this.lastRidehorse = in.readInt();
		this.lastRidehorse = this.lastRidehorse == Integer.MIN_VALUE ? null : this.lastRidehorse;
		this.weekLoginCount = in.readInt();
		this.weekLoginCount = this.weekLoginCount == Integer.MIN_VALUE ? null : this.weekLoginCount;
		this.dropGood = in.readInt();
		this.dropGood = this.dropGood == Integer.MIN_VALUE ? null : this.dropGood;
		this.maxJumpCount = in.readInt();
		this.maxJumpCount = this.maxJumpCount == Integer.MIN_VALUE ? null : this.maxJumpCount;
		this.dayOnline = in.readInt();
		this.dayOnline = this.dayOnline == Integer.MIN_VALUE ? null : this.dayOnline;
		this.hpPercent = in.readInt();
		this.hpPercent = this.hpPercent == Integer.MIN_VALUE ? null : this.hpPercent;
		this.mpPercent = in.readInt();
		this.mpPercent = this.mpPercent == Integer.MIN_VALUE ? null : this.mpPercent;
		this.spPercent = in.readInt();
		this.spPercent = this.spPercent == Integer.MIN_VALUE ? null : this.spPercent;
		this.dazuoSkill = in.readInt();
		this.dazuoSkill = this.dazuoSkill == Integer.MIN_VALUE ? null : this.dazuoSkill;
		this.chongqiRecord = in.readInt();
		this.chongqiRecord = this.chongqiRecord == Integer.MIN_VALUE ? null : this.chongqiRecord;
		this.deleteMark = in.readInt();
		this.deleteMark = this.deleteMark == Integer.MIN_VALUE ? null : this.deleteMark;
		this.chengjiuPoint = in.readInt();
		this.chengjiuPoint = this.chengjiuPoint == Integer.MIN_VALUE ? null : this.chengjiuPoint;
		this.contribution = in.readInt();
		this.contribution = this.contribution == Integer.MIN_VALUE ? null : this.contribution;
		this.skillPoBeginTime = (Date) in.readObject();
		this.skillPoTime = in.readInt();
		this.skillPoTime = this.skillPoTime == Integer.MIN_VALUE ? null : this.skillPoTime;
		this.skipNoviceNavigation = in.readUTF();
		this.skipNoviceNavigation = this.skipNoviceNavigation.equals("NaN") ? null : this.skipNoviceNavigation;
		this.devilcnt = in.readInt();
		this.devilcnt = this.devilcnt == Integer.MIN_VALUE ? null : this.devilcnt;
		this.leijiGainLijin = in.readInt();
		this.leijiGainLijin = this.leijiGainLijin == Integer.MIN_VALUE ? null : this.leijiGainLijin;
		this.limitOnlineTime = in.readInt();
		this.limitOnlineTime = this.limitOnlineTime == Integer.MIN_VALUE ? null : this.limitOnlineTime;
		this.chengzhanShengwang = in.readInt();
		this.chengzhanShengwang = this.chengzhanShengwang == Integer.MIN_VALUE ? null : this.chengzhanShengwang;
		this.lunjianShengwang = in.readInt();
		this.lunjianShengwang = this.lunjianShengwang == Integer.MIN_VALUE ? null : this.lunjianShengwang;
		this.todayChengzhanShengwang = in.readInt();
		this.todayChengzhanShengwang = this.todayChengzhanShengwang == Integer.MIN_VALUE ? null : this.todayChengzhanShengwang;
		this.tDantianGrade = in.readInt();
		this.tDantianGrade = this.tDantianGrade == Integer.MIN_VALUE ? null : this.tDantianGrade;
	}

	/**
	 * 主键 t_character.f_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer id;
	/**
	 * 角色的原始ID t_character.f_character_initially_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer characterInitiallyId;
	/**
	 * 原始分区id 合服用 t_character.f_original_sid
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer originalSid;
	/**
	 * 账户id t_character.f_account_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer accountId;
	/**
	 * 用户的原始ID t_character.f_account_initially_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer accountInitiallyId;
	/**
	 * 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉 t_character.f_popsinger
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer popsinger;
	/**
	 * 性别 t_character.f_sex
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Byte sex;
	/**
	 * 人物头像 t_character.f_headimg
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Byte headimg;
	/**
	 * 角色名 t_character.f_name
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String name;
	/**
	 * 角色说明 t_character.f_desc
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String desc;
	/**
	 * 等级 t_character.f_grade
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short grade;
	/**
	 * 当前剩余HP生命值 t_character.f_now_hp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer nowHp;
	/**
	 * HP上限 t_character.f_max_hp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer maxHp;
	/**
	 * 当前剩余MP内力值 t_character.f_now_mp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer nowMp;
	/**
	 * MP上限 t_character.f_max_mp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer maxMp;
	/**
	 * 当前体力 t_character.f_now_sp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer nowSp;
	/**
	 * 体力上限 t_character.f_max_sp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer maxSp;
	/**
	 * 当前的经验值 t_character.f_now_experience
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Long nowExperience;
	/**
	 * 下次升级所需要的经验值 t_character.f_next_experience
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Long nextExperience;
	/**
	 * 攻击力 t_character.f_attack
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer attack;
	/**
	 * 防御力 t_character.f_defence
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer defence;
	/**
	 * 命中 单位 1/10000 t_character.f_hit
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer hit;
	/**
	 * 暴击 1/10000 t_character.f_crt
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer crt;
	/**
	 * 闪避 单位1/10000 t_character.f_dodge
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer dodge;
	/**
	 * 攻击速度 用攻击冷却时间计(ms) t_character.f_atk_coldtime
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer atkColdtime;
	/**
	 * 移动速度 以像素计 t_character.f_move_speed
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short moveSpeed;
	/**
	 * 真气 t_character.f_zhenqi
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer zhenqi;
	/**
	 * 进攻加点 t_character.f_attack_addpoint
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short attackAddpoint;
	/**
	 * 防御加点 t_character.f_defence_addpoint
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short defenceAddpoint;
	/**
	 * 轻身加点 t_character.f_light_addpoint
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short lightAddpoint;
	/**
	 * 健体 加点 t_character.f_strong_addpoint
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short strongAddpoint;
	/**
	 * 可以用的属性点 t_character.f_useablepoint
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short useablepoint;
	/**
	 * 潜能(玩家升级时会增加，需玩家手动分配) t_character.f_potential
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short potential;
	/**
	 * 铜币 t_character.f_copper
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer copper;
	/**
	 * 交子 t_character.f_jiaozi
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer jiaozi;
	/**
	 * 元宝 t_character.f_ingot
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer ingot;
	/**
	 * 积分 t_character.f_integral
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer integral;
	/**
	 * 战场声望 t_character.f_prestige
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer prestige;
	/**
	 * 玩家当前显示称谓Id t_character.f_now_appellationid
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer nowAppellationid;
	/**
	 * 偶配角色ID t_character.f_consort_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer consortId;
	/**
	 * t_character.f_consort_name
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String consortName;
	/**
	 * 场景 t_character.f_scene
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short scene;
	/**
	 * x坐标 t_character.f_x
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short x;
	/**
	 * y坐标 t_character.f_y
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short y;
	/**
	 * pk模式 0.和平 1.组队 2.帮派 3.全体) t_character.f_pk_model
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer pkModel;
	/**
	 * 是否禁止聊天 0不禁止聊天，1禁止 t_character.f_isallow_chat
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Byte isallowChat;
	/**
	 * 可以开始说话的时间 t_character.f_allowchat_starttime
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Date allowchatStarttime;
	/**
	 * 角色创建时间 t_character.f_createtime
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Date createtime;
	/**
	 * 角色创建时的IP t_character.f_createip
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String createip;
	/**
	 * 最后登入时间 t_character.f_last_logindate
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Date lastLogindate;
	/**
	 * 最后退出时间 t_character.f_lastdate
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Date lastdate;
	/**
	 * t_character.f_lastip
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String lastip;
	/**
	 * 在线时间，指该角色的总在线时间(毫秒) t_character.f_onlinedate
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Long onlinedate;
	/**
	 * 玩家秘籍经验剩余点数 t_character.f_esoterica_exp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer esotericaExp;
	/**
	 * 人物普通攻击的技能id t_character.f_skillid
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer skillid;
	/**
	 * 当前所在的副本实例的id 如没有副本实例，则置空 t_character.f_instance_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String instanceId;
	/**
	 * 玩家当前分线id，如果已注销，则置为-1 ，可用于判断是否已经注销 t_character.f_line_id
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer lineId;
	/**
	 * 新手引导记入标识 t_character.f_newcomeleader_str
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String newcomeleaderStr;
	/**
	 * 是否封号0不封号 1标识封号 禁止登入 t_character.f_isclose_character
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Byte iscloseCharacter;
	/**
	 * 是否在线，0不在线，1在线 t_character.f_isonline
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Byte isonline;
	/**
	 * 离线经验奖励 t_character.f_double_exp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer doubleExp;
	/**
	 * 领奖次数 t_character.f_prize_cishu
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer prizeCishu;
	/**
	 * 最大允许的携带坐骑的数量 t_character.f_max_horse_amount
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer maxHorseAmount;
	/**
	 * 仓库里马的最大数量 t_character.f_max_storage_horse_amount
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer maxStorageHorseAmount;
	/**
	 * 打通穴位个数 t_character.f_channel_xuewei
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer channelXuewei;
	/**
	 * 打通斤脉（斤脉id，多个用，分个） t_character.f_channel_jingmai
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String channelJingmai;
	/**
	 * 打通真龙斤脉（斤脉id，多个用，分个）如果突破普通经脉这里边用000，表示 t_character.f_channel_realdragon
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String channelRealdragon;
	/**
	 * 人物被动经验加成次数 t_character.f_channel_beidong_exp
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer channelBeidongExp;
	/**
	 * 最大的包裹数 t_character.f_max_bag_amount
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short maxBagAmount;
	/**
	 * 最大的仓库数量 t_character.f_max_storage_amount
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Short maxStorageAmount;
	/**
	 * 仓库里存的铜钱数量 t_character.f_storage_copper
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer storageCopper;
	/**
	 * 鄙视次数 t_character.f_contempt_count
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer contemptCount;
	/**
	 * 崇拜次数 t_character.f_worship_count
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer worshipCount;
	/**
	 * 最大连斩数 t_character.f_max_continue_killcount
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer maxContinueKillcount;
	/**
	 * 摊位名称 t_character.f_stall_name
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String stallName;
	/**
	 * 武学境界 t_character.f_wuxue_jingjie
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer wuxueJingjie;
	/**
	 * 击杀boss次数 t_character.f_boss_kill
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer bossKill;
	/**
	 * 角色当前心情 t_character.f_now_xingqing
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String nowXingqing;
	/**
	 * 今日表情 t_character.f_now_biaoqing
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String nowBiaoqing;
	/**
	 * 收到赠送的花的数量 t_character.f_flower_count
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer flowerCount;
	/**
	 * 0正常状态/1打坐状态 t_character.f_state
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Byte state;
	/**
	 * 闭关开始时间 t_character.f_biguan_date
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Date biguanDate;
	/**
	 * 1摆摊 0不摆摊 t_character.f_stall_status
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer stallStatus;
	/**
	 * t_character.f_last_ridehorse
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer lastRidehorse;
	/**
	 * 一周登入次数统计 t_character.f_week_login_count
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer weekLoginCount;
	/**
	 * 是否第一次掉落物品 0是 1不是 t_character.f_drop_good
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer dropGood;
	/**
	 * 跳跃最大数 t_character.f_max_jump_count
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer maxJumpCount;
	/**
	 * 今天在线时间 单位毫秒 t_character.f_day_online
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer dayOnline;
	/**
	 * 血量百分比 t_character.f_hp_percent
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer hpPercent;
	/**
	 * 魔法百分比 t_character.f_mp_percent
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer mpPercent;
	/**
	 * 体力百分比 t_character.f_sp_percent
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer spPercent;
	/**
	 * 打坐心法提升id t_character.f_dazuo_skill
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer dazuoSkill;
	/**
	 * 当时使用（充气娃娃满）充气次数 t_character.f_chongqi_record
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer chongqiRecord;
	/**
	 * 删除标记 1为已删除 0为未删除 t_character.f_delete_mark
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer deleteMark;
	/**
	 * 成就进度 t_character.f_chengjiu_point
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer chengjiuPoint;
	/**
	 * 帮会贡献度 t_character.f_contribution
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer contribution;
	/**
	 * 技能突破的开始时间 t_character.f_skill_po_begin_time
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Date skillPoBeginTime;
	/**
	 * 技能突破的冷却持续时间(单位小时) t_character.f_skill_po_time
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer skillPoTime;
	/**
	 * 跳过新手导航面板 t_character.f_skip_novice_navigation
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private String skipNoviceNavigation;
	/**
	 * 杀死挂机中玩家的次数 t_character.f_devilcnt
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer devilcnt;
	/**
	 * 累计获得的礼金数 t_character.f_leiji_gain_lijin
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer leijiGainLijin;
	/**
	 * 防沉迷累计在线计时 t_character.f_limit_online_time
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer limitOnlineTime;
	/**
	 * 城战声望 t_character.f_chengzhan_shengwang
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer chengzhanShengwang;
	/**
	 * 论剑声望 t_character.f_lunjian_shengwang
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer lunjianShengwang;
	/**
	 * 今日城战已获得声望(零点清零) t_character.f_today_chengzhan_shengwang
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer todayChengzhanShengwang;
	/**
	 * 丹田等级 t_character.t_dantian_grade
	 * 
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	private Integer tDantianGrade;

	/**
	 * 主键 t_character.f_id
	 * 
	 * @return the value of t_character.f_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键 t_character.f_id
	 * 
	 * @param id
	 *            the value for t_character.f_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 角色的原始ID t_character.f_character_initially_id
	 * 
	 * @return the value of t_character.f_character_initially_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getCharacterInitiallyId() {
		return characterInitiallyId;
	}

	/**
	 * 角色的原始ID t_character.f_character_initially_id
	 * 
	 * @param characterInitiallyId
	 *            the value for t_character.f_character_initially_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setCharacterInitiallyId(Integer characterInitiallyId) {
		this.characterInitiallyId = characterInitiallyId;
	}

	/**
	 * 原始分区id 合服用 t_character.f_original_sid
	 * 
	 * @return the value of t_character.f_original_sid
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getOriginalSid() {
		return originalSid;
	}

	/**
	 * 原始分区id 合服用 t_character.f_original_sid
	 * 
	 * @param originalSid
	 *            the value for t_character.f_original_sid
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setOriginalSid(Integer originalSid) {
		this.originalSid = originalSid;
	}

	/**
	 * 账户id t_character.f_account_id
	 * 
	 * @return the value of t_character.f_account_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 账户id t_character.f_account_id
	 * 
	 * @param accountId
	 *            the value for t_character.f_account_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 用户的原始ID t_character.f_account_initially_id
	 * 
	 * @return the value of t_character.f_account_initially_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getAccountInitiallyId() {
		return accountInitiallyId;
	}

	/**
	 * 用户的原始ID t_character.f_account_initially_id
	 * 
	 * @param accountInitiallyId
	 *            the value for t_character.f_account_initially_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setAccountInitiallyId(Integer accountInitiallyId) {
		this.accountInitiallyId = accountInitiallyId;
	}

	/**
	 * 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉 t_character.f_popsinger
	 * 
	 * @return the value of t_character.f_popsinger
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getPopsinger() {
		return popsinger;
	}

	/**
	 * 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉 t_character.f_popsinger
	 * 
	 * @param popsinger
	 *            the value for t_character.f_popsinger
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setPopsinger(Integer popsinger) {
		this.popsinger = popsinger;
	}

	/**
	 * 性别 t_character.f_sex
	 * 
	 * @return the value of t_character.f_sex
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Byte getSex() {
		return sex;
	}

	/**
	 * 性别 t_character.f_sex
	 * 
	 * @param sex
	 *            the value for t_character.f_sex
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setSex(Byte sex) {
		this.sex = sex;
	}

	/**
	 * 人物头像 t_character.f_headimg
	 * 
	 * @return the value of t_character.f_headimg
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Byte getHeadimg() {
		return headimg;
	}

	/**
	 * 人物头像 t_character.f_headimg
	 * 
	 * @param headimg
	 *            the value for t_character.f_headimg
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setHeadimg(Byte headimg) {
		this.headimg = headimg;
	}

	/**
	 * 角色名 t_character.f_name
	 * 
	 * @return the value of t_character.f_name
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getName() {
		return name;
	}

	/**
	 * 角色名 t_character.f_name
	 * 
	 * @param name
	 *            the value for t_character.f_name
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 角色说明 t_character.f_desc
	 * 
	 * @return the value of t_character.f_desc
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 角色说明 t_character.f_desc
	 * 
	 * @param desc
	 *            the value for t_character.f_desc
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 等级 t_character.f_grade
	 * 
	 * @return the value of t_character.f_grade
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getGrade() {
		return grade;
	}

	/**
	 * 等级 t_character.f_grade
	 * 
	 * @param grade
	 *            the value for t_character.f_grade
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setGrade(Short grade) {
		this.grade = grade;
	}

	/**
	 * 当前剩余HP生命值 t_character.f_now_hp
	 * 
	 * @return the value of t_character.f_now_hp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getNowHp() {
		return nowHp;
	}

	/**
	 * 当前剩余HP生命值 t_character.f_now_hp
	 * 
	 * @param nowHp
	 *            the value for t_character.f_now_hp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNowHp(Integer nowHp) {
		this.nowHp = nowHp;
	}

	/**
	 * HP上限 t_character.f_max_hp
	 * 
	 * @return the value of t_character.f_max_hp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMaxHp() {
		return maxHp;
	}

	/**
	 * HP上限 t_character.f_max_hp
	 * 
	 * @param maxHp
	 *            the value for t_character.f_max_hp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxHp(Integer maxHp) {
		this.maxHp = maxHp;
	}

	/**
	 * 当前剩余MP内力值 t_character.f_now_mp
	 * 
	 * @return the value of t_character.f_now_mp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getNowMp() {
		return nowMp;
	}

	/**
	 * 当前剩余MP内力值 t_character.f_now_mp
	 * 
	 * @param nowMp
	 *            the value for t_character.f_now_mp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNowMp(Integer nowMp) {
		this.nowMp = nowMp;
	}

	/**
	 * MP上限 t_character.f_max_mp
	 * 
	 * @return the value of t_character.f_max_mp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMaxMp() {
		return maxMp;
	}

	/**
	 * MP上限 t_character.f_max_mp
	 * 
	 * @param maxMp
	 *            the value for t_character.f_max_mp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxMp(Integer maxMp) {
		this.maxMp = maxMp;
	}

	/**
	 * 当前体力 t_character.f_now_sp
	 * 
	 * @return the value of t_character.f_now_sp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getNowSp() {
		return nowSp;
	}

	/**
	 * 当前体力 t_character.f_now_sp
	 * 
	 * @param nowSp
	 *            the value for t_character.f_now_sp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNowSp(Integer nowSp) {
		this.nowSp = nowSp;
	}

	/**
	 * 体力上限 t_character.f_max_sp
	 * 
	 * @return the value of t_character.f_max_sp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMaxSp() {
		return maxSp;
	}

	/**
	 * 体力上限 t_character.f_max_sp
	 * 
	 * @param maxSp
	 *            the value for t_character.f_max_sp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxSp(Integer maxSp) {
		this.maxSp = maxSp;
	}

	/**
	 * 当前的经验值 t_character.f_now_experience
	 * 
	 * @return the value of t_character.f_now_experience
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Long getNowExperience() {
		return nowExperience;
	}

	/**
	 * 当前的经验值 t_character.f_now_experience
	 * 
	 * @param nowExperience
	 *            the value for t_character.f_now_experience
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNowExperience(Long nowExperience) {
		this.nowExperience = nowExperience;
	}

	/**
	 * 下次升级所需要的经验值 t_character.f_next_experience
	 * 
	 * @return the value of t_character.f_next_experience
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Long getNextExperience() {
		return nextExperience;
	}

	/**
	 * 下次升级所需要的经验值 t_character.f_next_experience
	 * 
	 * @param nextExperience
	 *            the value for t_character.f_next_experience
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNextExperience(Long nextExperience) {
		this.nextExperience = nextExperience;
	}

	/**
	 * 攻击力 t_character.f_attack
	 * 
	 * @return the value of t_character.f_attack
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getAttack() {
		return attack;
	}

	/**
	 * 攻击力 t_character.f_attack
	 * 
	 * @param attack
	 *            the value for t_character.f_attack
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setAttack(Integer attack) {
		this.attack = attack;
	}

	/**
	 * 防御力 t_character.f_defence
	 * 
	 * @return the value of t_character.f_defence
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDefence() {
		return defence;
	}

	/**
	 * 防御力 t_character.f_defence
	 * 
	 * @param defence
	 *            the value for t_character.f_defence
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDefence(Integer defence) {
		this.defence = defence;
	}

	/**
	 * 命中 单位 1/10000 t_character.f_hit
	 * 
	 * @return the value of t_character.f_hit
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getHit() {
		return hit;
	}

	/**
	 * 命中 单位 1/10000 t_character.f_hit
	 * 
	 * @param hit
	 *            the value for t_character.f_hit
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setHit(Integer hit) {
		this.hit = hit;
	}

	/**
	 * 暴击 1/10000 t_character.f_crt
	 * 
	 * @return the value of t_character.f_crt
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getCrt() {
		return crt;
	}

	/**
	 * 暴击 1/10000 t_character.f_crt
	 * 
	 * @param crt
	 *            the value for t_character.f_crt
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setCrt(Integer crt) {
		this.crt = crt;
	}

	/**
	 * 闪避 单位1/10000 t_character.f_dodge
	 * 
	 * @return the value of t_character.f_dodge
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDodge() {
		return dodge;
	}

	/**
	 * 闪避 单位1/10000 t_character.f_dodge
	 * 
	 * @param dodge
	 *            the value for t_character.f_dodge
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDodge(Integer dodge) {
		this.dodge = dodge;
	}

	/**
	 * 攻击速度 用攻击冷却时间计(ms) t_character.f_atk_coldtime
	 * 
	 * @return the value of t_character.f_atk_coldtime
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getAtkColdtime() {
		return atkColdtime;
	}

	/**
	 * 攻击速度 用攻击冷却时间计(ms) t_character.f_atk_coldtime
	 * 
	 * @param atkColdtime
	 *            the value for t_character.f_atk_coldtime
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setAtkColdtime(Integer atkColdtime) {
		this.atkColdtime = atkColdtime;
	}

	/**
	 * 移动速度 以像素计 t_character.f_move_speed
	 * 
	 * @return the value of t_character.f_move_speed
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * 移动速度 以像素计 t_character.f_move_speed
	 * 
	 * @param moveSpeed
	 *            the value for t_character.f_move_speed
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMoveSpeed(Short moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	/**
	 * 真气 t_character.f_zhenqi
	 * 
	 * @return the value of t_character.f_zhenqi
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getZhenqi() {
		return zhenqi;
	}

	/**
	 * 真气 t_character.f_zhenqi
	 * 
	 * @param zhenqi
	 *            the value for t_character.f_zhenqi
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setZhenqi(Integer zhenqi) {
		this.zhenqi = zhenqi;
	}

	/**
	 * 进攻加点 t_character.f_attack_addpoint
	 * 
	 * @return the value of t_character.f_attack_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getAttackAddpoint() {
		return attackAddpoint;
	}

	/**
	 * 进攻加点 t_character.f_attack_addpoint
	 * 
	 * @param attackAddpoint
	 *            the value for t_character.f_attack_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setAttackAddpoint(Short attackAddpoint) {
		this.attackAddpoint = attackAddpoint;
	}

	/**
	 * 防御加点 t_character.f_defence_addpoint
	 * 
	 * @return the value of t_character.f_defence_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getDefenceAddpoint() {
		return defenceAddpoint;
	}

	/**
	 * 防御加点 t_character.f_defence_addpoint
	 * 
	 * @param defenceAddpoint
	 *            the value for t_character.f_defence_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDefenceAddpoint(Short defenceAddpoint) {
		this.defenceAddpoint = defenceAddpoint;
	}

	/**
	 * 轻身加点 t_character.f_light_addpoint
	 * 
	 * @return the value of t_character.f_light_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getLightAddpoint() {
		return lightAddpoint;
	}

	/**
	 * 轻身加点 t_character.f_light_addpoint
	 * 
	 * @param lightAddpoint
	 *            the value for t_character.f_light_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLightAddpoint(Short lightAddpoint) {
		this.lightAddpoint = lightAddpoint;
	}

	/**
	 * 健体 加点 t_character.f_strong_addpoint
	 * 
	 * @return the value of t_character.f_strong_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getStrongAddpoint() {
		return strongAddpoint;
	}

	/**
	 * 健体 加点 t_character.f_strong_addpoint
	 * 
	 * @param strongAddpoint
	 *            the value for t_character.f_strong_addpoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setStrongAddpoint(Short strongAddpoint) {
		this.strongAddpoint = strongAddpoint;
	}

	/**
	 * 可以用的属性点 t_character.f_useablepoint
	 * 
	 * @return the value of t_character.f_useablepoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getUseablepoint() {
		return useablepoint;
	}

	/**
	 * 可以用的属性点 t_character.f_useablepoint
	 * 
	 * @param useablepoint
	 *            the value for t_character.f_useablepoint
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setUseablepoint(Short useablepoint) {
		this.useablepoint = useablepoint;
	}

	/**
	 * 潜能(玩家升级时会增加，需玩家手动分配) t_character.f_potential
	 * 
	 * @return the value of t_character.f_potential
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getPotential() {
		return potential;
	}

	/**
	 * 潜能(玩家升级时会增加，需玩家手动分配) t_character.f_potential
	 * 
	 * @param potential
	 *            the value for t_character.f_potential
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setPotential(Short potential) {
		this.potential = potential;
	}

	/**
	 * 铜币 t_character.f_copper
	 * 
	 * @return the value of t_character.f_copper
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 铜币 t_character.f_copper
	 * 
	 * @param copper
	 *            the value for t_character.f_copper
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 交子 t_character.f_jiaozi
	 * 
	 * @return the value of t_character.f_jiaozi
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getJiaozi() {
		return jiaozi;
	}

	/**
	 * 交子 t_character.f_jiaozi
	 * 
	 * @param jiaozi
	 *            the value for t_character.f_jiaozi
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setJiaozi(Integer jiaozi) {
		this.jiaozi = jiaozi;
	}

	/**
	 * 元宝 t_character.f_ingot
	 * 
	 * @return the value of t_character.f_ingot
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getIngot() {
		return ingot;
	}

	/**
	 * 元宝 t_character.f_ingot
	 * 
	 * @param ingot
	 *            the value for t_character.f_ingot
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setIngot(Integer ingot) {
		this.ingot = ingot;
	}

	/**
	 * 积分 t_character.f_integral
	 * 
	 * @return the value of t_character.f_integral
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getIntegral() {
		return integral;
	}

	/**
	 * 积分 t_character.f_integral
	 * 
	 * @param integral
	 *            the value for t_character.f_integral
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	/**
	 * 战场声望 t_character.f_prestige
	 * 
	 * @return the value of t_character.f_prestige
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getPrestige() {
		return prestige;
	}

	/**
	 * 战场声望 t_character.f_prestige
	 * 
	 * @param prestige
	 *            the value for t_character.f_prestige
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setPrestige(Integer prestige) {
		this.prestige = prestige;
	}

	/**
	 * 玩家当前显示称谓Id t_character.f_now_appellationid
	 * 
	 * @return the value of t_character.f_now_appellationid
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getNowAppellationid() {
		return nowAppellationid;
	}

	/**
	 * 玩家当前显示称谓Id t_character.f_now_appellationid
	 * 
	 * @param nowAppellationid
	 *            the value for t_character.f_now_appellationid
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNowAppellationid(Integer nowAppellationid) {
		this.nowAppellationid = nowAppellationid;
	}

	/**
	 * 偶配角色ID t_character.f_consort_id
	 * 
	 * @return the value of t_character.f_consort_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getConsortId() {
		return consortId;
	}

	/**
	 * 偶配角色ID t_character.f_consort_id
	 * 
	 * @param consortId
	 *            the value for t_character.f_consort_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setConsortId(Integer consortId) {
		this.consortId = consortId;
	}

	/**
	 * t_character.f_consort_name
	 * 
	 * @return the value of t_character.f_consort_name
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getConsortName() {
		return consortName;
	}

	/**
	 * t_character.f_consort_name
	 * 
	 * @param consortName
	 *            the value for t_character.f_consort_name
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setConsortName(String consortName) {
		this.consortName = consortName;
	}

	/**
	 * 场景 t_character.f_scene
	 * 
	 * @return the value of t_character.f_scene
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getScene() {
		return scene;
	}

	/**
	 * 场景 t_character.f_scene
	 * 
	 * @param scene
	 *            the value for t_character.f_scene
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setScene(Short scene) {
		this.scene = scene;
	}

	/**
	 * x坐标 t_character.f_x
	 * 
	 * @return the value of t_character.f_x
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getX() {
		return x;
	}

	/**
	 * x坐标 t_character.f_x
	 * 
	 * @param x
	 *            the value for t_character.f_x
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setX(Short x) {
		this.x = x;
	}

	/**
	 * y坐标 t_character.f_y
	 * 
	 * @return the value of t_character.f_y
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getY() {
		return y;
	}

	/**
	 * y坐标 t_character.f_y
	 * 
	 * @param y
	 *            the value for t_character.f_y
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setY(Short y) {
		this.y = y;
	}

	/**
	 * pk模式 0.和平 1.组队 2.帮派 3.全体) t_character.f_pk_model
	 * 
	 * @return the value of t_character.f_pk_model
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getPkModel() {
		return pkModel;
	}

	/**
	 * pk模式 0.和平 1.组队 2.帮派 3.全体) t_character.f_pk_model
	 * 
	 * @param pkModel
	 *            the value for t_character.f_pk_model
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setPkModel(Integer pkModel) {
		this.pkModel = pkModel;
	}

	/**
	 * 是否禁止聊天 0不禁止聊天，1禁止 t_character.f_isallow_chat
	 * 
	 * @return the value of t_character.f_isallow_chat
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Byte getIsallowChat() {
		return isallowChat;
	}

	/**
	 * 是否禁止聊天 0不禁止聊天，1禁止 t_character.f_isallow_chat
	 * 
	 * @param isallowChat
	 *            the value for t_character.f_isallow_chat
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setIsallowChat(Byte isallowChat) {
		this.isallowChat = isallowChat;
	}

	/**
	 * 可以开始说话的时间 t_character.f_allowchat_starttime
	 * 
	 * @return the value of t_character.f_allowchat_starttime
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Date getAllowchatStarttime() {
		return allowchatStarttime;
	}

	/**
	 * 可以开始说话的时间 t_character.f_allowchat_starttime
	 * 
	 * @param allowchatStarttime
	 *            the value for t_character.f_allowchat_starttime
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setAllowchatStarttime(Date allowchatStarttime) {
		this.allowchatStarttime = allowchatStarttime;
	}

	/**
	 * 角色创建时间 t_character.f_createtime
	 * 
	 * @return the value of t_character.f_createtime
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * 角色创建时间 t_character.f_createtime
	 * 
	 * @param createtime
	 *            the value for t_character.f_createtime
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * 角色创建时的IP t_character.f_createip
	 * 
	 * @return the value of t_character.f_createip
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getCreateip() {
		return createip;
	}

	/**
	 * 角色创建时的IP t_character.f_createip
	 * 
	 * @param createip
	 *            the value for t_character.f_createip
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setCreateip(String createip) {
		this.createip = createip;
	}

	/**
	 * 最后登入时间 t_character.f_last_logindate
	 * 
	 * @return the value of t_character.f_last_logindate
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Date getLastLogindate() {
		return lastLogindate;
	}

	/**
	 * 最后登入时间 t_character.f_last_logindate
	 * 
	 * @param lastLogindate
	 *            the value for t_character.f_last_logindate
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLastLogindate(Date lastLogindate) {
		this.lastLogindate = lastLogindate;
	}

	/**
	 * 最后退出时间 t_character.f_lastdate
	 * 
	 * @return the value of t_character.f_lastdate
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Date getLastdate() {
		return lastdate;
	}

	/**
	 * 最后退出时间 t_character.f_lastdate
	 * 
	 * @param lastdate
	 *            the value for t_character.f_lastdate
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}

	/**
	 * t_character.f_lastip
	 * 
	 * @return the value of t_character.f_lastip
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getLastip() {
		return lastip;
	}

	/**
	 * t_character.f_lastip
	 * 
	 * @param lastip
	 *            the value for t_character.f_lastip
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLastip(String lastip) {
		this.lastip = lastip;
	}

	/**
	 * 在线时间，指该角色的总在线时间(毫秒) t_character.f_onlinedate
	 * 
	 * @return the value of t_character.f_onlinedate
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Long getOnlinedate() {
		return onlinedate;
	}

	/**
	 * 在线时间，指该角色的总在线时间(毫秒) t_character.f_onlinedate
	 * 
	 * @param onlinedate
	 *            the value for t_character.f_onlinedate
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setOnlinedate(Long onlinedate) {
		this.onlinedate = onlinedate;
	}

	/**
	 * 玩家秘籍经验剩余点数 t_character.f_esoterica_exp
	 * 
	 * @return the value of t_character.f_esoterica_exp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getEsotericaExp() {
		return esotericaExp;
	}

	/**
	 * 玩家秘籍经验剩余点数 t_character.f_esoterica_exp
	 * 
	 * @param esotericaExp
	 *            the value for t_character.f_esoterica_exp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setEsotericaExp(Integer esotericaExp) {
		this.esotericaExp = esotericaExp;
	}

	/**
	 * 人物普通攻击的技能id t_character.f_skillid
	 * 
	 * @return the value of t_character.f_skillid
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getSkillid() {
		return skillid;
	}

	/**
	 * 人物普通攻击的技能id t_character.f_skillid
	 * 
	 * @param skillid
	 *            the value for t_character.f_skillid
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setSkillid(Integer skillid) {
		this.skillid = skillid;
	}

	/**
	 * 当前所在的副本实例的id 如没有副本实例，则置空 t_character.f_instance_id
	 * 
	 * @return the value of t_character.f_instance_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getInstanceId() {
		return instanceId;
	}

	/**
	 * 当前所在的副本实例的id 如没有副本实例，则置空 t_character.f_instance_id
	 * 
	 * @param instanceId
	 *            the value for t_character.f_instance_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * 玩家当前分线id，如果已注销，则置为-1 ，可用于判断是否已经注销 t_character.f_line_id
	 * 
	 * @return the value of t_character.f_line_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getLineId() {
		return lineId;
	}

	/**
	 * 玩家当前分线id，如果已注销，则置为-1 ，可用于判断是否已经注销 t_character.f_line_id
	 * 
	 * @param lineId
	 *            the value for t_character.f_line_id
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	/**
	 * 新手引导记入标识 t_character.f_newcomeleader_str
	 * 
	 * @return the value of t_character.f_newcomeleader_str
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getNewcomeleaderStr() {
		return newcomeleaderStr;
	}

	/**
	 * 新手引导记入标识 t_character.f_newcomeleader_str
	 * 
	 * @param newcomeleaderStr
	 *            the value for t_character.f_newcomeleader_str
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNewcomeleaderStr(String newcomeleaderStr) {
		this.newcomeleaderStr = newcomeleaderStr;
	}

	/**
	 * 是否封号0不封号 1标识封号 禁止登入 t_character.f_isclose_character
	 * 
	 * @return the value of t_character.f_isclose_character
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Byte getIscloseCharacter() {
		return iscloseCharacter;
	}

	/**
	 * 是否封号0不封号 1标识封号 禁止登入 t_character.f_isclose_character
	 * 
	 * @param iscloseCharacter
	 *            the value for t_character.f_isclose_character
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setIscloseCharacter(Byte iscloseCharacter) {
		this.iscloseCharacter = iscloseCharacter;
	}

	/**
	 * 是否在线，0不在线，1在线 t_character.f_isonline
	 * 
	 * @return the value of t_character.f_isonline
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Byte getIsonline() {
		return isonline;
	}

	/**
	 * 是否在线，0不在线，1在线 t_character.f_isonline
	 * 
	 * @param isonline
	 *            the value for t_character.f_isonline
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setIsonline(Byte isonline) {
		this.isonline = isonline;
	}

	/**
	 * 离线经验奖励 t_character.f_double_exp
	 * 
	 * @return the value of t_character.f_double_exp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDoubleExp() {
		return doubleExp;
	}

	/**
	 * 离线经验奖励 t_character.f_double_exp
	 * 
	 * @param doubleExp
	 *            the value for t_character.f_double_exp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDoubleExp(Integer doubleExp) {
		this.doubleExp = doubleExp;
	}

	/**
	 * 领奖次数 t_character.f_prize_cishu
	 * 
	 * @return the value of t_character.f_prize_cishu
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getPrizeCishu() {
		return prizeCishu;
	}

	/**
	 * 领奖次数 t_character.f_prize_cishu
	 * 
	 * @param prizeCishu
	 *            the value for t_character.f_prize_cishu
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setPrizeCishu(Integer prizeCishu) {
		this.prizeCishu = prizeCishu;
	}

	/**
	 * 最大允许的携带坐骑的数量 t_character.f_max_horse_amount
	 * 
	 * @return the value of t_character.f_max_horse_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMaxHorseAmount() {
		return maxHorseAmount;
	}

	/**
	 * 最大允许的携带坐骑的数量 t_character.f_max_horse_amount
	 * 
	 * @param maxHorseAmount
	 *            the value for t_character.f_max_horse_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxHorseAmount(Integer maxHorseAmount) {
		this.maxHorseAmount = maxHorseAmount;
	}

	/**
	 * 仓库里马的最大数量 t_character.f_max_storage_horse_amount
	 * 
	 * @return the value of t_character.f_max_storage_horse_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMaxStorageHorseAmount() {
		return maxStorageHorseAmount;
	}

	/**
	 * 仓库里马的最大数量 t_character.f_max_storage_horse_amount
	 * 
	 * @param maxStorageHorseAmount
	 *            the value for t_character.f_max_storage_horse_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxStorageHorseAmount(Integer maxStorageHorseAmount) {
		this.maxStorageHorseAmount = maxStorageHorseAmount;
	}

	/**
	 * 打通穴位个数 t_character.f_channel_xuewei
	 * 
	 * @return the value of t_character.f_channel_xuewei
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getChannelXuewei() {
		return channelXuewei;
	}

	/**
	 * 打通穴位个数 t_character.f_channel_xuewei
	 * 
	 * @param channelXuewei
	 *            the value for t_character.f_channel_xuewei
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setChannelXuewei(Integer channelXuewei) {
		this.channelXuewei = channelXuewei;
	}

	/**
	 * 打通斤脉（斤脉id，多个用，分个） t_character.f_channel_jingmai
	 * 
	 * @return the value of t_character.f_channel_jingmai
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getChannelJingmai() {
		return channelJingmai;
	}

	/**
	 * 打通斤脉（斤脉id，多个用，分个） t_character.f_channel_jingmai
	 * 
	 * @param channelJingmai
	 *            the value for t_character.f_channel_jingmai
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setChannelJingmai(String channelJingmai) {
		this.channelJingmai = channelJingmai;
	}

	/**
	 * 打通真龙斤脉（斤脉id，多个用，分个）如果突破普通经脉这里边用000，表示 t_character.f_channel_realdragon
	 * 
	 * @return the value of t_character.f_channel_realdragon
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getChannelRealdragon() {
		return channelRealdragon;
	}

	/**
	 * 打通真龙斤脉（斤脉id，多个用，分个）如果突破普通经脉这里边用000，表示 t_character.f_channel_realdragon
	 * 
	 * @param channelRealdragon
	 *            the value for t_character.f_channel_realdragon
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setChannelRealdragon(String channelRealdragon) {
		this.channelRealdragon = channelRealdragon;
	}

	/**
	 * 人物被动经验加成次数 t_character.f_channel_beidong_exp
	 * 
	 * @return the value of t_character.f_channel_beidong_exp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getChannelBeidongExp() {
		return channelBeidongExp;
	}

	/**
	 * 人物被动经验加成次数 t_character.f_channel_beidong_exp
	 * 
	 * @param channelBeidongExp
	 *            the value for t_character.f_channel_beidong_exp
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setChannelBeidongExp(Integer channelBeidongExp) {
		this.channelBeidongExp = channelBeidongExp;
	}

	/**
	 * 最大的包裹数 t_character.f_max_bag_amount
	 * 
	 * @return the value of t_character.f_max_bag_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getMaxBagAmount() {
		return maxBagAmount;
	}

	/**
	 * 最大的包裹数 t_character.f_max_bag_amount
	 * 
	 * @param maxBagAmount
	 *            the value for t_character.f_max_bag_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxBagAmount(Short maxBagAmount) {
		this.maxBagAmount = maxBagAmount;
	}

	/**
	 * 最大的仓库数量 t_character.f_max_storage_amount
	 * 
	 * @return the value of t_character.f_max_storage_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Short getMaxStorageAmount() {
		return maxStorageAmount;
	}

	/**
	 * 最大的仓库数量 t_character.f_max_storage_amount
	 * 
	 * @param maxStorageAmount
	 *            the value for t_character.f_max_storage_amount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxStorageAmount(Short maxStorageAmount) {
		this.maxStorageAmount = maxStorageAmount;
	}

	/**
	 * 仓库里存的铜钱数量 t_character.f_storage_copper
	 * 
	 * @return the value of t_character.f_storage_copper
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getStorageCopper() {
		return storageCopper;
	}

	/**
	 * 仓库里存的铜钱数量 t_character.f_storage_copper
	 * 
	 * @param storageCopper
	 *            the value for t_character.f_storage_copper
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setStorageCopper(Integer storageCopper) {
		this.storageCopper = storageCopper;
	}

	/**
	 * 鄙视次数 t_character.f_contempt_count
	 * 
	 * @return the value of t_character.f_contempt_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getContemptCount() {
		return contemptCount;
	}

	/**
	 * 鄙视次数 t_character.f_contempt_count
	 * 
	 * @param contemptCount
	 *            the value for t_character.f_contempt_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setContemptCount(Integer contemptCount) {
		this.contemptCount = contemptCount;
	}

	/**
	 * 崇拜次数 t_character.f_worship_count
	 * 
	 * @return the value of t_character.f_worship_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getWorshipCount() {
		return worshipCount;
	}

	/**
	 * 崇拜次数 t_character.f_worship_count
	 * 
	 * @param worshipCount
	 *            the value for t_character.f_worship_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setWorshipCount(Integer worshipCount) {
		this.worshipCount = worshipCount;
	}

	/**
	 * 最大连斩数 t_character.f_max_continue_killcount
	 * 
	 * @return the value of t_character.f_max_continue_killcount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMaxContinueKillcount() {
		return maxContinueKillcount;
	}

	/**
	 * 最大连斩数 t_character.f_max_continue_killcount
	 * 
	 * @param maxContinueKillcount
	 *            the value for t_character.f_max_continue_killcount
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxContinueKillcount(Integer maxContinueKillcount) {
		this.maxContinueKillcount = maxContinueKillcount;
	}

	/**
	 * 摊位名称 t_character.f_stall_name
	 * 
	 * @return the value of t_character.f_stall_name
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getStallName() {
		return stallName;
	}

	/**
	 * 摊位名称 t_character.f_stall_name
	 * 
	 * @param stallName
	 *            the value for t_character.f_stall_name
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	/**
	 * 武学境界 t_character.f_wuxue_jingjie
	 * 
	 * @return the value of t_character.f_wuxue_jingjie
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getWuxueJingjie() {
		return wuxueJingjie;
	}

	/**
	 * 武学境界 t_character.f_wuxue_jingjie
	 * 
	 * @param wuxueJingjie
	 *            the value for t_character.f_wuxue_jingjie
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setWuxueJingjie(Integer wuxueJingjie) {
		this.wuxueJingjie = wuxueJingjie;
	}

	/**
	 * 击杀boss次数 t_character.f_boss_kill
	 * 
	 * @return the value of t_character.f_boss_kill
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getBossKill() {
		return bossKill;
	}

	/**
	 * 击杀boss次数 t_character.f_boss_kill
	 * 
	 * @param bossKill
	 *            the value for t_character.f_boss_kill
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setBossKill(Integer bossKill) {
		this.bossKill = bossKill;
	}

	/**
	 * 角色当前心情 t_character.f_now_xingqing
	 * 
	 * @return the value of t_character.f_now_xingqing
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getNowXingqing() {
		return nowXingqing;
	}

	/**
	 * 角色当前心情 t_character.f_now_xingqing
	 * 
	 * @param nowXingqing
	 *            the value for t_character.f_now_xingqing
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNowXingqing(String nowXingqing) {
		this.nowXingqing = nowXingqing;
	}

	/**
	 * 今日表情 t_character.f_now_biaoqing
	 * 
	 * @return the value of t_character.f_now_biaoqing
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getNowBiaoqing() {
		return nowBiaoqing;
	}

	/**
	 * 今日表情 t_character.f_now_biaoqing
	 * 
	 * @param nowBiaoqing
	 *            the value for t_character.f_now_biaoqing
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setNowBiaoqing(String nowBiaoqing) {
		this.nowBiaoqing = nowBiaoqing;
	}

	/**
	 * 收到赠送的花的数量 t_character.f_flower_count
	 * 
	 * @return the value of t_character.f_flower_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getFlowerCount() {
		return flowerCount;
	}

	/**
	 * 收到赠送的花的数量 t_character.f_flower_count
	 * 
	 * @param flowerCount
	 *            the value for t_character.f_flower_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setFlowerCount(Integer flowerCount) {
		this.flowerCount = flowerCount;
	}

	/**
	 * 0正常状态/1打坐状态 t_character.f_state
	 * 
	 * @return the value of t_character.f_state
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Byte getState() {
		return state;
	}

	/**
	 * 0正常状态/1打坐状态 t_character.f_state
	 * 
	 * @param state
	 *            the value for t_character.f_state
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setState(Byte state) {
		this.state = state;
	}

	/**
	 * 闭关开始时间 t_character.f_biguan_date
	 * 
	 * @return the value of t_character.f_biguan_date
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Date getBiguanDate() {
		return biguanDate;
	}

	/**
	 * 闭关开始时间 t_character.f_biguan_date
	 * 
	 * @param biguanDate
	 *            the value for t_character.f_biguan_date
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setBiguanDate(Date biguanDate) {
		this.biguanDate = biguanDate;
	}

	/**
	 * 1摆摊 0不摆摊 t_character.f_stall_status
	 * 
	 * @return the value of t_character.f_stall_status
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getStallStatus() {
		return stallStatus;
	}

	/**
	 * 1摆摊 0不摆摊 t_character.f_stall_status
	 * 
	 * @param stallStatus
	 *            the value for t_character.f_stall_status
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setStallStatus(Integer stallStatus) {
		this.stallStatus = stallStatus;
	}

	/**
	 * t_character.f_last_ridehorse
	 * 
	 * @return the value of t_character.f_last_ridehorse
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getLastRidehorse() {
		return lastRidehorse;
	}

	/**
	 * t_character.f_last_ridehorse
	 * 
	 * @param lastRidehorse
	 *            the value for t_character.f_last_ridehorse
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLastRidehorse(Integer lastRidehorse) {
		this.lastRidehorse = lastRidehorse;
	}

	/**
	 * 一周登入次数统计 t_character.f_week_login_count
	 * 
	 * @return the value of t_character.f_week_login_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getWeekLoginCount() {
		return weekLoginCount;
	}

	/**
	 * 一周登入次数统计 t_character.f_week_login_count
	 * 
	 * @param weekLoginCount
	 *            the value for t_character.f_week_login_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setWeekLoginCount(Integer weekLoginCount) {
		this.weekLoginCount = weekLoginCount;
	}

	/**
	 * 是否第一次掉落物品 0是 1不是 t_character.f_drop_good
	 * 
	 * @return the value of t_character.f_drop_good
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDropGood() {
		return dropGood;
	}

	/**
	 * 是否第一次掉落物品 0是 1不是 t_character.f_drop_good
	 * 
	 * @param dropGood
	 *            the value for t_character.f_drop_good
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDropGood(Integer dropGood) {
		this.dropGood = dropGood;
	}

	/**
	 * 跳跃最大数 t_character.f_max_jump_count
	 * 
	 * @return the value of t_character.f_max_jump_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMaxJumpCount() {
		return maxJumpCount;
	}

	/**
	 * 跳跃最大数 t_character.f_max_jump_count
	 * 
	 * @param maxJumpCount
	 *            the value for t_character.f_max_jump_count
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMaxJumpCount(Integer maxJumpCount) {
		this.maxJumpCount = maxJumpCount;
	}

	/**
	 * 今天在线时间 单位毫秒 t_character.f_day_online
	 * 
	 * @return the value of t_character.f_day_online
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDayOnline() {
		return dayOnline;
	}

	/**
	 * 今天在线时间 单位毫秒 t_character.f_day_online
	 * 
	 * @param dayOnline
	 *            the value for t_character.f_day_online
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDayOnline(Integer dayOnline) {
		this.dayOnline = dayOnline;
	}

	/**
	 * 血量百分比 t_character.f_hp_percent
	 * 
	 * @return the value of t_character.f_hp_percent
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getHpPercent() {
		return hpPercent;
	}

	/**
	 * 血量百分比 t_character.f_hp_percent
	 * 
	 * @param hpPercent
	 *            the value for t_character.f_hp_percent
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setHpPercent(Integer hpPercent) {
		this.hpPercent = hpPercent;
	}

	/**
	 * 魔法百分比 t_character.f_mp_percent
	 * 
	 * @return the value of t_character.f_mp_percent
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getMpPercent() {
		return mpPercent;
	}

	/**
	 * 魔法百分比 t_character.f_mp_percent
	 * 
	 * @param mpPercent
	 *            the value for t_character.f_mp_percent
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setMpPercent(Integer mpPercent) {
		this.mpPercent = mpPercent;
	}

	/**
	 * 体力百分比 t_character.f_sp_percent
	 * 
	 * @return the value of t_character.f_sp_percent
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getSpPercent() {
		return spPercent;
	}

	/**
	 * 体力百分比 t_character.f_sp_percent
	 * 
	 * @param spPercent
	 *            the value for t_character.f_sp_percent
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setSpPercent(Integer spPercent) {
		this.spPercent = spPercent;
	}

	/**
	 * 打坐心法提升id t_character.f_dazuo_skill
	 * 
	 * @return the value of t_character.f_dazuo_skill
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDazuoSkill() {
		return dazuoSkill;
	}

	/**
	 * 打坐心法提升id t_character.f_dazuo_skill
	 * 
	 * @param dazuoSkill
	 *            the value for t_character.f_dazuo_skill
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDazuoSkill(Integer dazuoSkill) {
		this.dazuoSkill = dazuoSkill;
	}

	/**
	 * 当时使用（充气娃娃满）充气次数 t_character.f_chongqi_record
	 * 
	 * @return the value of t_character.f_chongqi_record
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getChongqiRecord() {
		return chongqiRecord;
	}

	/**
	 * 当时使用（充气娃娃满）充气次数 t_character.f_chongqi_record
	 * 
	 * @param chongqiRecord
	 *            the value for t_character.f_chongqi_record
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setChongqiRecord(Integer chongqiRecord) {
		this.chongqiRecord = chongqiRecord;
	}

	/**
	 * 删除标记 1为已删除 0为未删除 t_character.f_delete_mark
	 * 
	 * @return the value of t_character.f_delete_mark
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDeleteMark() {
		return deleteMark;
	}

	/**
	 * 删除标记 1为已删除 0为未删除 t_character.f_delete_mark
	 * 
	 * @param deleteMark
	 *            the value for t_character.f_delete_mark
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}

	/**
	 * 成就进度 t_character.f_chengjiu_point
	 * 
	 * @return the value of t_character.f_chengjiu_point
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getChengjiuPoint() {
		return chengjiuPoint;
	}

	/**
	 * 成就进度 t_character.f_chengjiu_point
	 * 
	 * @param chengjiuPoint
	 *            the value for t_character.f_chengjiu_point
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setChengjiuPoint(Integer chengjiuPoint) {
		this.chengjiuPoint = chengjiuPoint;
	}

	/**
	 * 帮会贡献度 t_character.f_contribution
	 * 
	 * @return the value of t_character.f_contribution
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getContribution() {
		return contribution;
	}

	/**
	 * 帮会贡献度 t_character.f_contribution
	 * 
	 * @param contribution
	 *            the value for t_character.f_contribution
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}

	/**
	 * 技能突破的开始时间 t_character.f_skill_po_begin_time
	 * 
	 * @return the value of t_character.f_skill_po_begin_time
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Date getSkillPoBeginTime() {
		return skillPoBeginTime;
	}

	/**
	 * 技能突破的开始时间 t_character.f_skill_po_begin_time
	 * 
	 * @param skillPoBeginTime
	 *            the value for t_character.f_skill_po_begin_time
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setSkillPoBeginTime(Date skillPoBeginTime) {
		this.skillPoBeginTime = skillPoBeginTime;
	}

	/**
	 * 技能突破的冷却持续时间(单位小时) t_character.f_skill_po_time
	 * 
	 * @return the value of t_character.f_skill_po_time
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getSkillPoTime() {
		return skillPoTime;
	}

	/**
	 * 技能突破的冷却持续时间(单位小时) t_character.f_skill_po_time
	 * 
	 * @param skillPoTime
	 *            the value for t_character.f_skill_po_time
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setSkillPoTime(Integer skillPoTime) {
		this.skillPoTime = skillPoTime;
	}

	/**
	 * 跳过新手导航面板 t_character.f_skip_novice_navigation
	 * 
	 * @return the value of t_character.f_skip_novice_navigation
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public String getSkipNoviceNavigation() {
		return skipNoviceNavigation;
	}

	/**
	 * 跳过新手导航面板 t_character.f_skip_novice_navigation
	 * 
	 * @param skipNoviceNavigation
	 *            the value for t_character.f_skip_novice_navigation
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setSkipNoviceNavigation(String skipNoviceNavigation) {
		this.skipNoviceNavigation = skipNoviceNavigation;
	}

	/**
	 * 杀死挂机中玩家的次数 t_character.f_devilcnt
	 * 
	 * @return the value of t_character.f_devilcnt
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getDevilcnt() {
		return devilcnt;
	}

	/**
	 * 杀死挂机中玩家的次数 t_character.f_devilcnt
	 * 
	 * @param devilcnt
	 *            the value for t_character.f_devilcnt
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setDevilcnt(Integer devilcnt) {
		this.devilcnt = devilcnt;
	}

	/**
	 * 累计获得的礼金数 t_character.f_leiji_gain_lijin
	 * 
	 * @return the value of t_character.f_leiji_gain_lijin
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getLeijiGainLijin() {
		return leijiGainLijin;
	}

	/**
	 * 累计获得的礼金数 t_character.f_leiji_gain_lijin
	 * 
	 * @param leijiGainLijin
	 *            the value for t_character.f_leiji_gain_lijin
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLeijiGainLijin(Integer leijiGainLijin) {
		this.leijiGainLijin = leijiGainLijin;
	}

	/**
	 * 防沉迷累计在线计时 t_character.f_limit_online_time
	 * 
	 * @return the value of t_character.f_limit_online_time
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getLimitOnlineTime() {
		return limitOnlineTime;
	}

	/**
	 * 防沉迷累计在线计时 t_character.f_limit_online_time
	 * 
	 * @param limitOnlineTime
	 *            the value for t_character.f_limit_online_time
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLimitOnlineTime(Integer limitOnlineTime) {
		this.limitOnlineTime = limitOnlineTime;
	}

	/**
	 * 城战声望 t_character.f_chengzhan_shengwang
	 * 
	 * @return the value of t_character.f_chengzhan_shengwang
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getChengzhanShengwang() {
		return chengzhanShengwang;
	}

	/**
	 * 城战声望 t_character.f_chengzhan_shengwang
	 * 
	 * @param chengzhanShengwang
	 *            the value for t_character.f_chengzhan_shengwang
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setChengzhanShengwang(Integer chengzhanShengwang) {
		this.chengzhanShengwang = chengzhanShengwang;
	}

	/**
	 * 论剑声望 t_character.f_lunjian_shengwang
	 * 
	 * @return the value of t_character.f_lunjian_shengwang
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getLunjianShengwang() {
		return lunjianShengwang;
	}

	/**
	 * 论剑声望 t_character.f_lunjian_shengwang
	 * 
	 * @param lunjianShengwang
	 *            the value for t_character.f_lunjian_shengwang
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setLunjianShengwang(Integer lunjianShengwang) {
		this.lunjianShengwang = lunjianShengwang;
	}

	/**
	 * 今日城战已获得声望(零点清零) t_character.f_today_chengzhan_shengwang
	 * 
	 * @return the value of t_character.f_today_chengzhan_shengwang
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer getTodayChengzhanShengwang() {
		return todayChengzhanShengwang;
	}

	/**
	 * 今日城战已获得声望(零点清零) t_character.f_today_chengzhan_shengwang
	 * 
	 * @param todayChengzhanShengwang
	 *            the value for t_character.f_today_chengzhan_shengwang
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void setTodayChengzhanShengwang(Integer todayChengzhanShengwang) {
		this.todayChengzhanShengwang = todayChengzhanShengwang;
	}

	/**
	 * 丹田等级 t_character.t_dantian_grade
	 * 
	 * @return the value of t_character.t_dantian_grade
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public Integer gettDantianGrade() {
		return tDantianGrade;
	}

	/**
	 * 丹田等级 t_character.t_dantian_grade
	 * 
	 * @param tDantianGrade
	 *            the value for t_character.t_dantian_grade
	 * @ibatorgenerated Fri Jul 01 21:57:24 CST 2011
	 */
	public void settDantianGrade(Integer tDantianGrade) {
		this.tDantianGrade = tDantianGrade;
	}
}
