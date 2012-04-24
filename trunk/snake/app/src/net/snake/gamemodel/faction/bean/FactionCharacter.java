package net.snake.gamemodel.faction.bean;

import java.util.Date;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.GongchengTsMap;
import net.snake.ibatis.IbatisEntity;
import net.snake.netio.ServerResponse;
import net.snake.serverenv.cache.CharacterCacheManager;

public class FactionCharacter  implements IbatisEntity{
	/**
	 * t_faction_character.f_id
	 * 
	 */
	private Integer id;
	/**
	 * 玩家角色id 与玩家角色表关联 t_faction_character.f_character_id
	 * 
	 */
	private Integer characterId;
	/**
	 * 帮会id与帮会关联 t_faction_character.f_faction_id
	 * 
	 */
	private Integer factionId;
	/**
	 * 职位 （0帮众 ,1帮主，2副帮主，3大长老，4大师兄，5大师姐） t_faction_character.f_position
	 * 
	 */
	private Byte position;
	/**
	 * 玩家本帮帮会帮会贡献度 t_faction_character.f_contribution
	 * 
	 */
	private Integer contribution;
	/**
	 * 玩家铜币捐献数 t_faction_character.f_copper
	 * 
	 */
	private Integer copper;
	/**
	 * 青龙捐献个数 t_faction_character.f_qinglong_count
	 * 
	 */
	private Integer qinglongCount;
	/**
	 * 白虎捐献个数 t_faction_character.f_baihu_count
	 * 
	 */
	private Integer baihuCount;
	/**
	 * 玄武捐献个数 t_faction_character.f_xuanwu_count
	 * 
	 */
	private Integer xuanwuCount;
	/**
	 * 朱雀捐献个数 t_faction_character.f_zhuqu_count
	 * 
	 */
	private Integer zhuquCount;
	/**
	 * 帮众称号 t_faction_character.f_name
	 * 
	 */
	private String name;
	/**
	 * t_faction_character.f_bangzhuling_count
	 * 
	 */
	private Integer bangzhulingCount;
	/**
	 * 帮会帮助提示是否弹出 0弹出/1不弹出 t_faction_character.f_tishi_config
	 * 
	 */
	private Integer tishiConfig;
	/**
	 * 入帮时间 t_faction_character.f_date
	 * 
	 */
	private Date date;

	/**
	 * t_faction_character.f_id
	 * @return  the value of t_faction_character.f_id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_faction_character.f_id
	 * @param id  the value for t_faction_character.f_id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 玩家角色id 与玩家角色表关联 t_faction_character.f_character_id
	 * @return  the value of t_faction_character.f_character_id
	 * 
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 玩家角色id 与玩家角色表关联 t_faction_character.f_character_id
	 * @param characterId  the value for t_faction_character.f_character_id
	 * 
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 帮会id与帮会关联 t_faction_character.f_faction_id
	 * @return  the value of t_faction_character.f_faction_id
	 * 
	 */
	public Integer getFactionId() {
		return factionId;
	}

	/**
	 * 帮会id与帮会关联 t_faction_character.f_faction_id
	 * @param factionId  the value for t_faction_character.f_faction_id
	 * 
	 */
	public void setFactionId(Integer factionId) {
		this.factionId = factionId;
	}

	/**
	 * 职位 （0帮众 ,1帮主，2副帮主，3大长老，4大师兄，5大师姐） t_faction_character.f_position
	 * @return  the value of t_faction_character.f_position
	 * 
	 */
	public Byte getPosition() {
		return position;
	}

	/**
	 * 职位 （0帮众 ,1帮主，2副帮主，3大长老，4大师兄，5大师姐） t_faction_character.f_position
	 * @param position  the value for t_faction_character.f_position
	 * 
	 */
	public void setPosition(Byte position) {
		this.position = position;
	}

	/**
	 * 玩家本帮帮会帮会贡献度 t_faction_character.f_contribution
	 * @return  the value of t_faction_character.f_contribution
	 * 
	 */
	public Integer getContribution() {
		return contribution;
	}

	/**
	 * 玩家本帮帮会帮会贡献度 t_faction_character.f_contribution
	 * @param contribution  the value for t_faction_character.f_contribution
	 * 
	 */
	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}

	/**
	 * 玩家铜币捐献数 t_faction_character.f_copper
	 * @return  the value of t_faction_character.f_copper
	 * 
	 */
	public Integer getCopper() {
		return copper;
	}

	/**
	 * 玩家铜币捐献数 t_faction_character.f_copper
	 * @param copper  the value for t_faction_character.f_copper
	 * 
	 */
	public void setCopper(Integer copper) {
		this.copper = copper;
	}

	/**
	 * 青龙捐献个数 t_faction_character.f_qinglong_count
	 * @return  the value of t_faction_character.f_qinglong_count
	 * 
	 */
	public Integer getQinglongCount() {
		return qinglongCount;
	}

	/**
	 * 青龙捐献个数 t_faction_character.f_qinglong_count
	 * @param qinglongCount  the value for t_faction_character.f_qinglong_count
	 * 
	 */
	public void setQinglongCount(Integer qinglongCount) {
		this.qinglongCount = qinglongCount;
	}

	/**
	 * 白虎捐献个数 t_faction_character.f_baihu_count
	 * @return  the value of t_faction_character.f_baihu_count
	 * 
	 */
	public Integer getBaihuCount() {
		return baihuCount;
	}

	/**
	 * 白虎捐献个数 t_faction_character.f_baihu_count
	 * @param baihuCount  the value for t_faction_character.f_baihu_count
	 * 
	 */
	public void setBaihuCount(Integer baihuCount) {
		this.baihuCount = baihuCount;
	}

	/**
	 * 玄武捐献个数 t_faction_character.f_xuanwu_count
	 * @return  the value of t_faction_character.f_xuanwu_count
	 * 
	 */
	public Integer getXuanwuCount() {
		return xuanwuCount;
	}

	/**
	 * 玄武捐献个数 t_faction_character.f_xuanwu_count
	 * @param xuanwuCount  the value for t_faction_character.f_xuanwu_count
	 * 
	 */
	public void setXuanwuCount(Integer xuanwuCount) {
		this.xuanwuCount = xuanwuCount;
	}

	/**
	 * 朱雀捐献个数 t_faction_character.f_zhuqu_count
	 * @return  the value of t_faction_character.f_zhuqu_count
	 * 
	 */
	public Integer getZhuquCount() {
		return zhuquCount;
	}

	/**
	 * 朱雀捐献个数 t_faction_character.f_zhuqu_count
	 * @param zhuquCount  the value for t_faction_character.f_zhuqu_count
	 * 
	 */
	public void setZhuquCount(Integer zhuquCount) {
		this.zhuquCount = zhuquCount;
	}

	/**
	 * 帮众称号 t_faction_character.f_name
	 * @return  the value of t_faction_character.f_name
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * 帮众称号 t_faction_character.f_name
	 * @param name  the value for t_faction_character.f_name
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * t_faction_character.f_bangzhuling_count
	 * @return  the value of t_faction_character.f_bangzhuling_count
	 * 
	 */
	public Integer getBangzhulingCount() {
		if(this.bangzhulingCount==null){
			this.bangzhulingCount=0;
		}
		return bangzhulingCount;
	}

	/**
	 * t_faction_character.f_bangzhuling_count
	 * @param bangzhulingCount  the value for t_faction_character.f_bangzhuling_count
	 * 
	 */
	public void setBangzhulingCount(Integer bangzhulingCount) {
		this.bangzhulingCount = bangzhulingCount;
	}

	/**
	 * 帮会帮助提示是否弹出 0弹出/1不弹出 t_faction_character.f_tishi_config
	 * @return  the value of t_faction_character.f_tishi_config
	 * 
	 */
	public Integer getTishiConfig() {
		if(this.tishiConfig==null){
			this.tishiConfig=0;
		}
		return tishiConfig;
	}

	/**
	 * 帮会帮助提示是否弹出 0弹出/1不弹出 t_faction_character.f_tishi_config
	 * @param tishiConfig  the value for t_faction_character.f_tishi_config
	 * 
	 */
	public void setTishiConfig(Integer tishiConfig) {
		this.tishiConfig = tishiConfig;
	}

	/**
	 * 入帮时间 t_faction_character.f_date
	 * @return  the value of t_faction_character.f_date
	 * 
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * 入帮时间 t_faction_character.f_date
	 * @param date  the value for t_faction_character.f_date
	 * 
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	private CharacterCacheEntry cce;

	public CharacterCacheEntry getCce() {
		if (this.cce == null) {
			this.cce = CharacterCacheManager.getInstance()
					.getCharacterCacheEntryById(this.characterId);
			if (this.cce == null) {
				//TODO return new CharacterCacheEntry();
			}
		}
		return cce;
	}
	public String getViewName(){
		CharacterCacheEntry cce=getCce();
		if(cce==null){
			return "";
		}
		return getCce().getViewName();
	}
	public void setCce(CharacterCacheEntry cce) {
		this.cce = cce;
	}
	public void updatebossKillCount(Hero character) {
		CharacterCacheEntry cee = getCce();
		if (cee != null) {
			cee.setBossKill(character.getBossKill());
		}
	}

	public void updateOnlineState(byte state) {
		CharacterCacheEntry cee = getCce();
		if (cee != null) {
			cee.setIsOnline(state);
		}
	}

	public static FactionCharacter createFactionCharacter(Faction faction,
			Hero character, byte position, int copper) {
		FactionCharacter fc = new FactionCharacter();
		fc.setFactionId(faction.getId());
		fc.setCharacterId(character.getId());
		fc.setPosition(position);
		fc.setCopper(copper);
		fc.setName("");
		fc.setQinglongCount(0);
		fc.setXuanwuCount(0);
		fc.setZhuquCount(0);
		fc.setBaihuCount(0);
		fc.setContribution(0);
		fc.setBangzhulingCount(0);
		fc.setTishiConfig(0);
		fc.setDate(new Date());
		return fc;
	}

	public void sendMsg(ServerResponse msg) {
		if (getCce().getIsOnline() == 0) {
			return;
		}
		Hero character = GameServer.vlineServerManager
				.getCharacterById(this.characterId);
		if (character != null) {
			character.sendMsg(msg);
		}
	}
	private long fanctionCityTime=0;
	private long jiruTime=0;
	public void saveFactionCityTime(long time) {
		this.fanctionCityTime=time;
		this.jiruTime=System.currentTimeMillis();
	}
	public long getFactionCityTime(){
		if(System.currentTimeMillis()-this.jiruTime>60*60*1000){
			this.fanctionCityTime=0;
		}
		if(!GongchengTsMap.isGongchenging){
			if(System.currentTimeMillis()-this.jiruTime>5*60*1000){
				this.fanctionCityTime=0;
			}
		}
		return this.fanctionCityTime;
	}
}
