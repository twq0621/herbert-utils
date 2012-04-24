package net.snake.gamemodel.hero.bean;

import net.snake.GameServer;
import net.snake.commons.Language;
import net.snake.ibatis.IbatisEntity;

public class CharacterCacheEntry implements IbatisEntity {

	private Integer id; // id会经常做key值,所以不作基本类型
	private short grade;// 等级
	private String name;// 姓名
	private byte sex;// 性别
	private byte headimg;// 人物头像
	private int popsinger;// 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉
	private String nowXingqing;// 角色当前心情
	private String nowBiaoqing;// 今日表情
	private Integer flowerCount;// 收到赠送的花的数量
	public byte isBaitan = 0;
	public int bossKill = 0;
	public byte isOnline = 0;
	private int chengjiuPoint;
	private int channelXuewei;
	private int wuxueJingjie;
	private int chenzhanshengwang;// 城战声望
	private int originalSid;
	private int dantiangrade;
	private String factionName;// 帮派名字

	public CharacterCacheEntry() {
	}

	public CharacterCacheEntry(Hero c) {
		id = c.getId();
		grade = c.getGrade();
		name = c.getName();
		sex = c.getSex();
		this.nowBiaoqing = c.getNowBiaoqing();
		this.nowXingqing = c.getNowXingqing();
		headimg = c.getHeadimg();
		popsinger = c.getPopsinger();
		this.flowerCount = c.getFlowerCount();
		this.bossKill = c.getBossKill();
		this.chenzhanshengwang = c.getChengzhanShengWang();
		this.channelXuewei = c.getChannelXuewei();
		this.chengjiuPoint = c.getChengjiuPoint();
		this.wuxueJingjie = c.getWuxueJingjie();
		this.originalSid = c.getOriginalSid();
		this.dantiangrade = c.getDantiangrade();
		if (c.getMyFactionManager() != null) {
			this.factionName = c.getMyFactionManager().getFactionName();
		}
	}

	public int getOriginalSid() {
		return originalSid;
	}

	public void setOriginalSid(int originalSid) {
		this.originalSid = originalSid;
	}

	public byte getHeadimg() {
		return headimg;
	}

	public void setHeadimg(byte headimg) {
		this.headimg = headimg;
	}

	public Integer getId() {
		return id;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public byte getSex() {
		return sex;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public short getGrade() {
		return grade;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPopsinger() {
		return popsinger;
	}

	public void setPopsinger(int popsinger) {
		this.popsinger = popsinger;
	}

	public int getDantiangrade() {
		return dantiangrade;
	}

	public void setDantiangrade(int dantiangrade) {
		this.dantiangrade = dantiangrade;
	}

	public String getNowXingqing() {
		if (nowXingqing == null) {
			return "";
		}
		return nowXingqing;
	}

	public void setNowXingqing(String nowXingqing) {
		this.nowXingqing = nowXingqing;
	}

	public String getNowBiaoqing() {
		if (nowBiaoqing == null) {
			return "";
		}
		return nowBiaoqing;
	}

	public void setNowBiaoqing(String nowBiaoqing) {
		this.nowBiaoqing = nowBiaoqing;
	}

	public byte getIsBaitan() {
		return isBaitan;
	}

	public void setIsBaitan(byte isBaitan) {
		this.isBaitan = isBaitan;
	}

	public Integer getFlowerCount() {
		return flowerCount;
	}

	public void setFlowerCount(Integer flowerCount) {
		this.flowerCount = flowerCount;
	}

	public byte getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(byte isOnline) {
		this.isOnline = isOnline;
	}

	public int getBossKill() {
		return bossKill;
	}

	public void setBossKill(int bossKill) {
		this.bossKill = bossKill;
	}

	public int getChengjiuPoint() {
		return chengjiuPoint;
	}

	public void setChengjiuPoint(int chengjiuPoint) {
		this.chengjiuPoint = chengjiuPoint;
	}

	public int getChannelXuewei() {
		return channelXuewei;
	}

	public void setChannelXuewei(int channelXuewei) {
		this.channelXuewei = channelXuewei;
	}

	public int getWuxueJingjie() {
		return wuxueJingjie;
	}

	public void setWuxueJingjie(int wuxueJingjie) {
		this.wuxueJingjie = wuxueJingjie;
	}

	/**
	 * 是否是男性玩家
	 * 
	 * @return
	 */
	public boolean isMale() {
		if (this.getPopsinger() < 3) {
			return true;
		}
		return false;
	}

	public int getChenzhanshengwang() {
		return chenzhanshengwang;
	}

	public void setChenzhanshengwang(int chenzhanshengwang) {
		this.chenzhanshengwang = chenzhanshengwang;
	}

	public void update(Hero character, byte isOnline) {
		this.setBossKill(character.getBossKill());
		this.setIsOnline(isOnline);
		this.setChannelXuewei(character.getChannelXuewei());
		this.setChengjiuPoint(character.getChengjiuPoint());
		this.setChenzhanshengwang(character.getChengzhanShengWang());
		this.setWuxueJingjie(character.getWuxueJingjie());
	}

	public String getViewName() {
		if (GameServer.configParamManger.getConfigParam().isShowMyServerId()) {
			return "[" + this.getOriginalSid() + Language.QU + "]" + this.name;
		}
		return this.name;
	}

	public String getFactionName() {
		return factionName == null ? "" : factionName;
	}

	public void setFactionName(String factionName) {
		this.factionName = factionName;
	}
}
