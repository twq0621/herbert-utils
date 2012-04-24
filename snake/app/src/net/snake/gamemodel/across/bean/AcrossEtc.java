package net.snake.gamemodel.across.bean;

import java.util.Date;

import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.gamemodel.wedding.persistence.WeddingRingManager;
import net.snake.ibatis.IbatisEntity;


public class AcrossEtc implements IbatisEntity{

	/**
	 * t_across_etc.f_id
	 */
	private Integer id;
	/**R
	 * 战场服务器ID t_across_etc.f_ascoss_server_id
	 */
	private Integer ascossServerId;
	/**
	 * 战场角色id t_across_etc.f_character_id
	 */
	private Integer characterId;
	/**
	 * 原始区ID t_across_etc.f_old_area_id
	 */
	private Integer oldAreaId;
	/**
	 * 原始角色ID t_across_etc.f_old_character_id
	 */
	private Integer oldCharacterId;
	/**
	 * 原服配偶ID t_across_etc.f_old_wedder_id
	 */
	private Integer oldWedderId;
	/**
	 * 原服配偶名字 t_across_etc.f_old_wedder_name
	 */
	private String oldWedderName;
	/**
	 * 原服帮会ID t_across_etc.f_old_gang_id
	 */
	private Integer oldGangId;
	/**
	 * 原服帮会名字 t_across_etc.f_old_gang_name
	 */
	private String oldGangName;
	/**
	 * 原服帮会区ID t_across_etc.f_old_gang_area_id
	 */
	private Integer oldGangAreaId;
	/**
	 * t_across_etc.f_old_wedder_area_id
	 */
	private Integer oldWedderAreaId;
	/**
	 * 新生成的帐号 t_across_etc.f_account_id
	 */
	private Integer accountId;
	/**
	 * 原始帐号 t_across_etc.f_old_account_id
	 */
	private Integer oldAccountId;
	/**
	 * t_across_etc.f_is_bangzhu
	 */
	private Integer isBangzhu;
	/**
	 * 是不是城主 t_across_etc.f_is_chengzhu
	 */
	private Integer isChengzhu;
	/**
	 * 帮会等级 t_across_etc.f_gang_grade
	 */
	private Integer gangGrade;
	/**
	 * 婚佩id t_across_etc.f_wedder_quanpei_id
	 */
	private Integer wedderQuanpeiId;
	/**
	 * 结婚时间 t_across_etc.f_wedder_time
	 */
	private Date wedderTime;
	/**
	 * 配偶门派 t_across_etc.f_old_wedder_menpai
	 */
	private Byte oldWedderMenpai;
	/**
	 * 配偶丹田 t_across_etc.f_old_wedder_dantian
	 */
	private Byte oldWedderDantian;
	/**
	 * 角色原始id（合服之前） t_across_etc.f_old_character_initially_id
	 */
	private Integer oldCharacterInitiallyId;
	/**
	 * 帐号和服前id t_across_etc.f_old_account_initially_id
	 */
	private Integer oldAccountInitiallyId;
	/**
	 * 是否过时标识 0没有/1过时不允许登入 t_across_etc.f_guoshi_flag
	 */
	private Byte guoshiFlag;

	/**
	 * t_across_etc.f_id
	 * @return  the value of t_across_etc.f_id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * t_across_etc.f_id
	 * @param id  the value for t_across_etc.f_id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 战场服务器ID t_across_etc.f_ascoss_server_id
	 * @return  the value of t_across_etc.f_ascoss_server_id
	 */
	public Integer getAscossServerId() {
		return ascossServerId;
	}

	/**
	 * 战场服务器ID t_across_etc.f_ascoss_server_id
	 * @param ascossServerId  the value for t_across_etc.f_ascoss_server_id
	 */
	public void setAscossServerId(Integer ascossServerId) {
		this.ascossServerId = ascossServerId;
	}

	/**
	 * 战场角色id t_across_etc.f_character_id
	 * @return  the value of t_across_etc.f_character_id
	 */
	public Integer getCharacterId() {
		return characterId;
	}

	/**
	 * 战场角色id t_across_etc.f_character_id
	 * @param characterId  the value for t_across_etc.f_character_id
	 */
	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	/**
	 * 原始区ID t_across_etc.f_old_area_id
	 * @return  the value of t_across_etc.f_old_area_id
	 */
	public Integer getOldAreaId() {
		return oldAreaId;
	}

	/**
	 * 原始区ID t_across_etc.f_old_area_id
	 * @param oldAreaId  the value for t_across_etc.f_old_area_id
	 */
	public void setOldAreaId(Integer oldAreaId) {
		this.oldAreaId = oldAreaId;
	}

	/**
	 * 原始角色ID t_across_etc.f_old_character_id
	 * @return  the value of t_across_etc.f_old_character_id
	 */
	public Integer getOldCharacterId() {
		return oldCharacterId;
	}

	/**
	 * 原始角色ID t_across_etc.f_old_character_id
	 * @param oldCharacterId  the value for t_across_etc.f_old_character_id
	 */
	public void setOldCharacterId(Integer oldCharacterId) {
		this.oldCharacterId = oldCharacterId;
	}

	/**
	 * 原服配偶ID t_across_etc.f_old_wedder_id
	 * @return  the value of t_across_etc.f_old_wedder_id
	 */
	public Integer getOldWedderId() {
		return oldWedderId;
	}

	/**
	 * 原服配偶ID t_across_etc.f_old_wedder_id
	 * @param oldWedderId  the value for t_across_etc.f_old_wedder_id
	 */
	public void setOldWedderId(Integer oldWedderId) {
		this.oldWedderId = oldWedderId;
	}

	/**
	 * 原服配偶名字 t_across_etc.f_old_wedder_name
	 * @return  the value of t_across_etc.f_old_wedder_name
	 */
	public String getOldWedderName() {
		return oldWedderName;
	}

	/**
	 * 原服配偶名字 t_across_etc.f_old_wedder_name
	 * @param oldWedderName  the value for t_across_etc.f_old_wedder_name
	 */
	public void setOldWedderName(String oldWedderName) {
		this.oldWedderName = oldWedderName;
	}

	/**
	 * 原服帮会ID t_across_etc.f_old_gang_id
	 * @return  the value of t_across_etc.f_old_gang_id
	 */
	public Integer getOldGangId() {
		return oldGangId;
	}

	/**
	 * 原服帮会ID t_across_etc.f_old_gang_id
	 * @param oldGangId  the value for t_across_etc.f_old_gang_id
	 */
	public void setOldGangId(Integer oldGangId) {
		this.oldGangId = oldGangId;
	}

	/**
	 * 原服帮会名字 t_across_etc.f_old_gang_name
	 * @return  the value of t_across_etc.f_old_gang_name
	 */
	public String getOldGangName() {
		return oldGangName;
	}

	/**
	 * 原服帮会名字 t_across_etc.f_old_gang_name
	 * @param oldGangName  the value for t_across_etc.f_old_gang_name
	 */
	public void setOldGangName(String oldGangName) {
		this.oldGangName = oldGangName;
	}

	/**
	 * 原服帮会区ID t_across_etc.f_old_gang_area_id
	 * @return  the value of t_across_etc.f_old_gang_area_id
	 */
	public Integer getOldGangAreaId() {
		return oldGangAreaId;
	}

	/**
	 * 原服帮会区ID t_across_etc.f_old_gang_area_id
	 * @param oldGangAreaId  the value for t_across_etc.f_old_gang_area_id
	 */
	public void setOldGangAreaId(Integer oldGangAreaId) {
		this.oldGangAreaId = oldGangAreaId;
	}

	/**
	 * t_across_etc.f_old_wedder_area_id
	 * @return  the value of t_across_etc.f_old_wedder_area_id
	 */
	public Integer getOldWedderAreaId() {
		return oldWedderAreaId;
	}

	/**
	 * t_across_etc.f_old_wedder_area_id
	 * @param oldWedderAreaId  the value for t_across_etc.f_old_wedder_area_id
	 */
	public void setOldWedderAreaId(Integer oldWedderAreaId) {
		this.oldWedderAreaId = oldWedderAreaId;
	}

	/**
	 * 新生成的帐号 t_across_etc.f_account_id
	 * @return  the value of t_across_etc.f_account_id
	 */
	public Integer getAccountId() {
		return accountId;
	}

	/**
	 * 新生成的帐号 t_across_etc.f_account_id
	 * @param accountId  the value for t_across_etc.f_account_id
	 */
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	/**
	 * 原始帐号 t_across_etc.f_old_account_id
	 * @return  the value of t_across_etc.f_old_account_id
	 */
	public Integer getOldAccountId() {
		return oldAccountId;
	}

	/**
	 * 原始帐号 t_across_etc.f_old_account_id
	 * @param oldAccountId  the value for t_across_etc.f_old_account_id
	 */
	public void setOldAccountId(Integer oldAccountId) {
		this.oldAccountId = oldAccountId;
	}

	/**
	 * t_across_etc.f_is_bangzhu
	 * @return  the value of t_across_etc.f_is_bangzhu
	 */
	public Integer getIsBangzhu() {
		return isBangzhu;
	}

	/**
	 * t_across_etc.f_is_bangzhu
	 * @param isBangzhu  the value for t_across_etc.f_is_bangzhu
	 */
	public void setIsBangzhu(Integer isBangzhu) {
		this.isBangzhu = isBangzhu;
	}

	/**
	 * 是不是城主 t_across_etc.f_is_chengzhu
	 * @return  the value of t_across_etc.f_is_chengzhu
	 */
	public Integer getIsChengzhu() {
		return isChengzhu;
	}

	/**
	 * 是不是城主 t_across_etc.f_is_chengzhu
	 * @param isChengzhu  the value for t_across_etc.f_is_chengzhu
	 */
	public void setIsChengzhu(Integer isChengzhu) {
		this.isChengzhu = isChengzhu;
	}

	/**
	 * 帮会等级 t_across_etc.f_gang_grade
	 * @return  the value of t_across_etc.f_gang_grade
	 */
	public Integer getGangGrade() {
		return gangGrade;
	}

	/**
	 * 帮会等级 t_across_etc.f_gang_grade
	 * @param gangGrade  the value for t_across_etc.f_gang_grade
	 */
	public void setGangGrade(Integer gangGrade) {
		this.gangGrade = gangGrade;
	}

	/**
	 * 婚佩id t_across_etc.f_wedder_quanpei_id
	 * @return  the value of t_across_etc.f_wedder_quanpei_id
	 */
	public Integer getWedderQuanpeiId() {
		return wedderQuanpeiId;
	}

	/**
	 * 婚佩id t_across_etc.f_wedder_quanpei_id
	 * @param wedderQuanpeiId  the value for t_across_etc.f_wedder_quanpei_id
	 */
	public void setWedderQuanpeiId(Integer wedderQuanpeiId) {
		this.wedderQuanpeiId = wedderQuanpeiId;
	}

	/**
	 * 结婚时间 t_across_etc.f_wedder_time
	 * @return  the value of t_across_etc.f_wedder_time
	 */
	public Date getWedderTime() {
		return wedderTime;
	}

	/**
	 * 结婚时间 t_across_etc.f_wedder_time
	 * @param wedderTime  the value for t_across_etc.f_wedder_time
	 */
	public void setWedderTime(Date wedderTime) {
		this.wedderTime = wedderTime;
	}

	/**
	 * 配偶门派 t_across_etc.f_old_wedder_menpai
	 * @return  the value of t_across_etc.f_old_wedder_menpai
	 */
	public Byte getOldWedderMenpai() {
		return oldWedderMenpai;
	}

	/**
	 * 配偶门派 t_across_etc.f_old_wedder_menpai
	 * @param oldWedderMenpai  the value for t_across_etc.f_old_wedder_menpai
	 */
	public void setOldWedderMenpai(Byte oldWedderMenpai) {
		this.oldWedderMenpai = oldWedderMenpai;
	}

	/**
	 * 配偶丹田 t_across_etc.f_old_wedder_dantian
	 * @return  the value of t_across_etc.f_old_wedder_dantian
	 */
	public Byte getOldWedderDantian() {
		return oldWedderDantian;
	}

	/**
	 * 配偶丹田 t_across_etc.f_old_wedder_dantian
	 * @param oldWedderDantian  the value for t_across_etc.f_old_wedder_dantian
	 */
	public void setOldWedderDantian(Byte oldWedderDantian) {
		this.oldWedderDantian = oldWedderDantian;
	}

	/**
	 * 角色原始id（合服之前） t_across_etc.f_old_character_initially_id
	 * @return  the value of t_across_etc.f_old_character_initially_id
	 */
	public Integer getOldCharacterInitiallyId() {
		return oldCharacterInitiallyId;
	}

	/**
	 * 角色原始id（合服之前） t_across_etc.f_old_character_initially_id
	 * @param oldCharacterInitiallyId  the value for t_across_etc.f_old_character_initially_id
	 */
	public void setOldCharacterInitiallyId(Integer oldCharacterInitiallyId) {
		this.oldCharacterInitiallyId = oldCharacterInitiallyId;
	}

	/**
	 * 帐号和服前id t_across_etc.f_old_account_initially_id
	 * @return  the value of t_across_etc.f_old_account_initially_id
	 */
	public Integer getOldAccountInitiallyId() {
		return oldAccountInitiallyId;
	}

	/**
	 * 帐号和服前id t_across_etc.f_old_account_initially_id
	 * @param oldAccountInitiallyId  the value for t_across_etc.f_old_account_initially_id
	 */
	public void setOldAccountInitiallyId(Integer oldAccountInitiallyId) {
		this.oldAccountInitiallyId = oldAccountInitiallyId;
	}

	/**
	 * 是否过时标识 0没有/1过时不允许登入 t_across_etc.f_guoshi_flag
	 * @return  the value of t_across_etc.f_guoshi_flag
	 */
	public Byte getGuoshiFlag() {
		return guoshiFlag;
	}

	/**
	 * 是否过时标识 0没有/1过时不允许登入 t_across_etc.f_guoshi_flag
	 * @param guoshiFlag  the value for t_across_etc.f_guoshi_flag
	 */
	public void setGuoshiFlag(Byte guoshiFlag) {
		this.guoshiFlag = guoshiFlag;
	}
	private WeddingRing wedderRing;

	public WeddingRing getWedderRing() {
		if(this.oldWedderId==0){
			return null;
		}
		if(wedderRing==null){
			wedderRing=WeddingRingManager.getInstance().getWeddingRingById(this.wedderQuanpeiId);
		}
		return wedderRing;
	}

	public void setWedderRing(WeddingRing wedderRing) {
		this.wedderRing = wedderRing;
	}
	public String getWedderViewName(){
		return "["+this.oldWedderAreaId+"]"+this.oldWedderName;
	}
     private int characterLineId=1;
     private String characterLineName="一线";
     private String loginServerIp="";
     private String loginport="";
     private String chatServerIp="";
     private String chatport="";
	/**
	 * @param lineID
	 */
	public void setCharacterLineId(int lineID) {
		characterLineId=lineID;
	}

	public int getCharacterLineId() {
		return characterLineId;
	}

	public String getCharacterLineName() {
		return characterLineName;
	}

	public void setCharacterLineName(String characterLineName) {
		this.characterLineName = characterLineName;
	}

	public String getLoginServerIp() {
		return loginServerIp;
	}

	public void setLoginServerIp(String loginServerIp) {
		this.loginServerIp = loginServerIp;
	}

	public String getLoginport() {
		return loginport;
	}

	public void setLoginport(String loginport) {
		this.loginport = loginport;
	}

	public String getChatServerIp() {
		return chatServerIp;
	}

	public void setChatServerIp(String chatServerIp) {
		this.chatServerIp = chatServerIp;
	}

	public String getChatport() {
		return chatport;
	}

	public void setChatport(String chatport) {
		this.chatport = chatport;
	}
	public boolean isGongshiFlag() {
		if(this.guoshiFlag==null||this.guoshiFlag==0){
			return false;
		}
		return true;
	}
	public String getViewFactionName() {
		return oldGangName+"["+getOldGangAreaId()+"]";
	}
}
