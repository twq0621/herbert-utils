package net.snake.gamemodel.hero.bean;

import net.snake.ibatis.IbatisEntity;
import net.snake.serverenv.cache.CharacterCacheManager;

public class CharacterRanking implements IbatisEntity {

	private int metop = 0;// 临时统计出我排名第几

	public int getMetop() {
		return metop;
	}

	public void setMetop(int metop) {
		this.metop = metop;
	}

	private int id;
	private int copper;// 铜币
	private int ingot;// 元宝
	private int jiaozi;// 交子
	private int popsinger;// 职业 0 - 无,1 - 少林,2 - 武当,3 - 古墓,4 - 峨眉

	private String name;// 姓名
	private short grade;// 等级
	private long nowExperience;// 当前的经验值
	private Integer nowAppellationid;// 玩家当前显示称谓Id
	private int channelXuewei; // 人物穴位个数
	private int maxContinueKillcount;// 连斩数
	private int prestige;// 战场声望
	private int bossKill;// 击杀BOSS数
	private String stallName;// 摊位名
	private int wuxueJingjie;// 武学境界
	private int storageCopper;// 仓库里存的铜钱数量
	private Integer flowerCount;// 收到赠送的花的数量
	private String nowXingqing;
	private String nowBiaoqing;
	private Integer chengjiuPoint;
	private Integer chengzhanShengWang;
	private Integer lunjianShengWang;

	public Integer getChengzhanShengWang() {
		return chengzhanShengWang;
	}

	public void setChengzhanShengWang(Integer chengzhanShengWang) {
		this.chengzhanShengWang = chengzhanShengWang;
	}

	public Integer getLunjianShengWang() {
		return lunjianShengWang;
	}

	public void setLunjianShengWang(Integer lunjianShengWang) {
		this.lunjianShengWang = lunjianShengWang;
	}

	private CharacterCacheEntry cce;

	public CharacterCacheEntry getCce() {
		if (this.cce == null) {
			this.cce = CharacterCacheManager.getInstance().getCharacterCacheEntryById(id);
		}
		return cce;
	}

	public void setCce(CharacterCacheEntry cce) {
		this.cce = cce;
	}

	public Integer getChengjiuPoint() {
		return chengjiuPoint;
	}

	public void setChengjiuPoint(Integer chengjiuPoint) {
		this.chengjiuPoint = chengjiuPoint;
	}

	public String getNowXingqing() {
		return nowXingqing;
	}

	public void setNowXingqing(String nowXingqing) {
		this.nowXingqing = nowXingqing;
	}

	public String getNowBiaoqing() {
		return nowBiaoqing;
	}

	public void setNowBiaoqing(String nowBiaoqing) {
		this.nowBiaoqing = nowBiaoqing;
	}

	public int getIngot() {
		return ingot;
	}

	public void setIngot(int ingot) {
		this.ingot = ingot;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCopper() {
		return copper;
	}

	public void setCopper(int copper) {
		this.copper = copper;
	}

	public int getJiaozi() {
		return jiaozi;
	}

	public void setJiaozi(int jiaozi) {
		this.jiaozi = jiaozi;
	}

	public int getPopsinger() {
		return popsinger;
	}

	public void setPopsinger(int popsinger) {
		this.popsinger = popsinger;
	}

	public String getName() {
		return getCce() == null ? name : getCce().getViewName();
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getGrade() {
		return grade;
	}

	public void setGrade(short grade) {
		this.grade = grade;
	}

	public long getNowExperience() {
		return nowExperience;
	}

	public void setNowExperience(long nowExperience) {
		this.nowExperience = nowExperience;
	}

	public Integer getNowAppellationid() {
		return nowAppellationid;
	}

	public void setNowAppellationid(Integer nowAppellationid) {
		this.nowAppellationid = nowAppellationid;
	}

	public int getChannelXuewei() {
		return channelXuewei;
	}

	public void setChannelXuewei(int channelXuewei) {
		this.channelXuewei = channelXuewei;
	}

	public int getMaxContinueKillcount() {
		return maxContinueKillcount;
	}

	public void setMaxContinueKillcount(int maxContinueKillcount) {
		this.maxContinueKillcount = maxContinueKillcount;
	}

	public int getPrestige() {
		return prestige;
	}

	public void setPrestige(int prestige) {
		this.prestige = prestige;
	}

	public int getBossKill() {
		return bossKill;
	}

	public void setBossKill(int bossKill) {
		this.bossKill = bossKill;
	}

	public String getStallName() {
		return stallName;
	}

	public void setStallName(String stallName) {
		this.stallName = stallName;
	}

	public int getWuxueJingjie() {
		return wuxueJingjie;
	}

	public void setWuxueJingjie(int wuxueJingjie) {
		this.wuxueJingjie = wuxueJingjie;
	}

	public int getStorageCopper() {
		return storageCopper;
	}

	public void setStorageCopper(int storageCopper) {
		this.storageCopper = storageCopper;
	}

	public Integer getFlowerCount() {
		return flowerCount;
	}

	public void setFlowerCount(Integer flowerCount) {
		this.flowerCount = flowerCount;
	}

}
