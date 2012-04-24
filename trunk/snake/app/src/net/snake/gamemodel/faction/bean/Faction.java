package net.snake.gamemodel.faction.bean;

import java.util.Date;

import net.snake.GameServer;
import net.snake.commons.Language;
import net.snake.gamemodel.faction.persistence.FactionFlagManager;
import net.snake.ibatis.IbatisEntity;

public class Faction implements Cloneable,IbatisEntity{
    /**
	 * 帮会id t_faction.f_id
	 *
	 */
	private Integer id;
	/**
	 * 帮会名字 t_faction.f_name
	 *
	 */
	private String name;
	/**
	 * 帮会贡献值 t_faction.f_contribution
	 *
	 */
	private Integer contribution;
	/**
	 * 帮主id 关联角色表id t_faction.f_bangzhu_id
	 *
	 */
	private Integer bangzhuId;
	/**
	 * 帮会描述 t_faction.f_desc
	 *
	 */
	private String desc;
	/**
	 * 帮旗样式颜色 t_faction.f_ico_str
	 *
	 */
	private String icoStr;
	/**
	 * 对应帮会旗帜级别 t_faction.f_faction_flag_id
	 *
	 */
	private Integer factionFlagId;
	/**
	 * 0需要确认玩家加入帮会/1不需要确认直接加入 t_faction.f_access_in_faction
	 *
	 */
	private Integer accessInFaction;
	/**
	 * 帮会当前拥有铜币数 t_faction.f_copper
	 *
	 */
	private Long copper;
	/**
	 * 帮旗名字 t_faction.f_bangqi_name
	 *
	 */
	private String bangqiName;
	/**
	 * 青龙捐献个数 t_faction.f_qinglong_count
	 *
	 */
	private Integer qinglongCount;
	/**
	 * 白虎捐献个数 t_faction.f_baihu_count
	 *
	 */
	private Integer baihuCount;
	/**
	 * 玄武捐献个数 t_faction.f_xuanwu_count
	 *
	 */
	private Integer xuanwuCount;
	/**
	 * 朱雀捐献个数 t_faction.f_zhuqu_count
	 *
	 */
	private Integer zhuquCount;
	/**
	 * 帮会创建时间 t_faction.f_create_date
	 *
	 */
	private Date createDate;
	/**
	 * 帮会公告 t_faction.faction_notice
	 *
	 */
	private String factionNotice;
	/**
	 * 帮战公告 t_faction.bangzhan_notice
	 *
	 */
	private String bangzhanNotice;
	/**
	 * 攻城公告 t_faction.gongcheng_notice
	 *
	 */
	private String gongchengNotice;
	/**
	 * 帮旗样式id t_faction.f_ico_id
	 *
	 */
	private Integer icoId;
	/**
	 * 帮会分区id t_faction.f_server_id
	 *
	 */
	private Integer serverId;
	/**
	 * 帮主令个数 t_faction.f_bangzhuling_count
	 *
	 */
	private Integer bangzhulingCount;
	/**
	 * 帮会id t_faction.f_id
	 * @return  the value of t_faction.f_id
	 *
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 帮会id t_faction.f_id
	 * @param id  the value for t_faction.f_id
	 *
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 帮会名字 t_faction.f_name
	 * @return  the value of t_faction.f_name
	 *
	 */
	public String getName() {
		return name;
	}
	/**
	 * 帮会名字 t_faction.f_name
	 * @param name  the value for t_faction.f_name
	 *
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 帮会贡献值 t_faction.f_contribution
	 * @return  the value of t_faction.f_contribution
	 *
	 */
	public Integer getContribution() {
		if(this.contribution==null){
			this.contribution=0;
		}
		return contribution;
	}
	/**
	 * 帮会贡献值 t_faction.f_contribution
	 * @param contribution  the value for t_faction.f_contribution
	 *
	 */
	public void setContribution(Integer contribution) {
		this.contribution = contribution;
	}
	/**
	 * 帮主id 关联角色表id t_faction.f_bangzhu_id
	 * @return  the value of t_faction.f_bangzhu_id
	 *
	 */
	public Integer getBangzhuId() {
		return bangzhuId;
	}
	/**
	 * 帮主id 关联角色表id t_faction.f_bangzhu_id
	 * @param bangzhuId  the value for t_faction.f_bangzhu_id
	 *
	 */
	public void setBangzhuId(Integer bangzhuId) {
		this.bangzhuId = bangzhuId;
	}
	/**
	 * 帮会描述 t_faction.f_desc
	 * @return  the value of t_faction.f_desc
	 *
	 */
	public String getDesc() {
		if(this.desc==null){
			this.desc="";
		}
		return desc;
	}
	/**
	 * 帮会描述 t_faction.f_desc
	 * @param desc  the value for t_faction.f_desc
	 *
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 帮旗样式颜色 t_faction.f_ico_str
	 * @return  the value of t_faction.f_ico_str
	 *
	 */
	public String getIcoStr() {
		if(this.icoStr==null){
			this.icoStr="";
		}
		return icoStr;
	}
	/**
	 * 帮旗样式颜色 t_faction.f_ico_str
	 * @param icoStr  the value for t_faction.f_ico_str
	 *
	 */
	public void setIcoStr(String icoStr) {
		this.icoStr = icoStr;
	}
	/**
	 * 对应帮会旗帜级别 t_faction.f_faction_flag_id
	 * @return  the value of t_faction.f_faction_flag_id
	 *
	 */
	public Integer getFactionFlagId() {
		if(this.factionFlagId==null){
			this.factionFlagId=0;
		}
		return factionFlagId;
	}
	/**
	 * 对应帮会旗帜级别 t_faction.f_faction_flag_id
	 * @param factionFlagId  the value for t_faction.f_faction_flag_id
	 *
	 */
	public void setFactionFlagId(Integer factionFlagId) {
		this.factionFlagId = factionFlagId;
	}
	/**
	 * 0需要确认玩家加入帮会/1不需要确认直接加入 t_faction.f_access_in_faction
	 * @return  the value of t_faction.f_access_in_faction
	 *
	 */
	public Integer getAccessInFaction() {
		if(this.accessInFaction==null){
			this.accessInFaction=0;
		}
		return accessInFaction;
	}
	/**
	 * 0需要确认玩家加入帮会/1不需要确认直接加入 t_faction.f_access_in_faction
	 * @param accessInFaction  the value for t_faction.f_access_in_faction
	 *
	 */
	public void setAccessInFaction(Integer accessInFaction) {
		this.accessInFaction = accessInFaction;
	}
	/**
	 * 帮会当前拥有铜币数 t_faction.f_copper
	 * @return  the value of t_faction.f_copper
	 *
	 */
	public Long getCopper() {
		if(this.copper==null){
			this.copper=0l;
		}
		return copper;
	}
	/**
	 * 帮会当前拥有铜币数 t_faction.f_copper
	 * @param copper  the value for t_faction.f_copper
	 *
	 */
	public void setCopper(Long copper) {
		this.copper = copper;
	}
	/**
	 * 帮旗名字 t_faction.f_bangqi_name
	 * @return  the value of t_faction.f_bangqi_name
	 *
	 */
	public String getBangqiName() {
		if(this.bangqiName==null){
			this.bangqiName="";
		}
		return bangqiName;
	}
	/**
	 * 帮旗名字 t_faction.f_bangqi_name
	 * @param bangqiName  the value for t_faction.f_bangqi_name
	 *
	 */
	public void setBangqiName(String bangqiName) {
		this.bangqiName = bangqiName;
	}
	/**
	 * 青龙捐献个数 t_faction.f_qinglong_count
	 * @return  the value of t_faction.f_qinglong_count
	 *
	 */
	public Integer getQinglongCount() {
		return qinglongCount;
	}
	/**
	 * 青龙捐献个数 t_faction.f_qinglong_count
	 * @param qinglongCount  the value for t_faction.f_qinglong_count
	 *
	 */
	public void setQinglongCount(Integer qinglongCount) {
		this.qinglongCount = qinglongCount;
	}
	/**
	 * 白虎捐献个数 t_faction.f_baihu_count
	 * @return  the value of t_faction.f_baihu_count
	 *
	 */
	public Integer getBaihuCount() {
		return baihuCount;
	}
	/**
	 * 白虎捐献个数 t_faction.f_baihu_count
	 * @param baihuCount  the value for t_faction.f_baihu_count
	 *
	 */
	public void setBaihuCount(Integer baihuCount) {
		this.baihuCount = baihuCount;
	}
	/**
	 * 玄武捐献个数 t_faction.f_xuanwu_count
	 * @return  the value of t_faction.f_xuanwu_count
	 *
	 */
	public Integer getXuanwuCount() {
		return xuanwuCount;
	}
	/**
	 * 玄武捐献个数 t_faction.f_xuanwu_count
	 * @param xuanwuCount  the value for t_faction.f_xuanwu_count
	 *
	 */
	public void setXuanwuCount(Integer xuanwuCount) {
		this.xuanwuCount = xuanwuCount;
	}
	/**
	 * 朱雀捐献个数 t_faction.f_zhuqu_count
	 * @return  the value of t_faction.f_zhuqu_count
	 *
	 */
	public Integer getZhuquCount() {
		return zhuquCount;
	}
	/**
	 * 朱雀捐献个数 t_faction.f_zhuqu_count
	 * @param zhuquCount  the value for t_faction.f_zhuqu_count
	 *
	 */
	public void setZhuquCount(Integer zhuquCount) {
		this.zhuquCount = zhuquCount;
	}
	/**
	 * 帮会创建时间 t_faction.f_create_date
	 * @return  the value of t_faction.f_create_date
	 *
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 帮会创建时间 t_faction.f_create_date
	 * @param createDate  the value for t_faction.f_create_date
	 *
	 */
	public void setCreateDate(Date createDate) {
		if(this.createDate==null){
			this.createDate=new Date();
		}
		this.createDate = createDate;
	}
	/**
	 * 帮会公告 t_faction.faction_notice
	 * @return  the value of t_faction.faction_notice
	 *
	 */
	public String getFactionNotice() {
		if(this.factionNotice==null){
			this.factionNotice="";
		}
		return factionNotice;
	}
	/**
	 * 帮会公告 t_faction.faction_notice
	 * @param factionNotice  the value for t_faction.faction_notice
	 *
	 */
	public void setFactionNotice(String factionNotice) {
		this.factionNotice = factionNotice;
	}
	/**
	 * 帮战公告 t_faction.bangzhan_notice
	 * @return  the value of t_faction.bangzhan_notice
	 *
	 */
	public String getBangzhanNotice() {
		if(this.bangzhanNotice==null){
			this.bangzhanNotice="";
		}
		return bangzhanNotice;
	}
	/**
	 * 帮战公告 t_faction.bangzhan_notice
	 * @param bangzhanNotice  the value for t_faction.bangzhan_notice
	 *
	 */
	public void setBangzhanNotice(String bangzhanNotice) {
		this.bangzhanNotice = bangzhanNotice;
	}
	/**
	 * 攻城公告 t_faction.gongcheng_notice
	 * @return  the value of t_faction.gongcheng_notice
	 *
	 */
	public String getGongchengNotice() {
		if(this.gongchengNotice==null){
			this.gongchengNotice="";
		}
		return gongchengNotice;
	}
	/**
	 * 攻城公告 t_faction.gongcheng_notice
	 * @param gongchengNotice  the value for t_faction.gongcheng_notice
	 *
	 */
	public void setGongchengNotice(String gongchengNotice) {
		this.gongchengNotice = gongchengNotice;
	}
	/**
	 * 帮旗样式id t_faction.f_ico_id
	 * @return  the value of t_faction.f_ico_id
	 *
	 */
	public Integer getIcoId() {
		return icoId;
	}
	/**
	 * 帮旗样式id t_faction.f_ico_id
	 * @param icoId  the value for t_faction.f_ico_id
	 *
	 */
	public void setIcoId(Integer icoId) {
		this.icoId = icoId;
	}
	/**
	 * 帮会分区id t_faction.f_server_id
	 * @return  the value of t_faction.f_server_id
	 *
	 */
	public Integer getServerId() {
		return serverId;
	}
	/**
	 * 帮会分区id t_faction.f_server_id
	 * @param serverId  the value for t_faction.f_server_id
	 *
	 */
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}
	/**
	 * 帮主令个数 t_faction.f_bangzhuling_count
	 * @return  the value of t_faction.f_bangzhuling_count
	 *
	 */
	public Integer getBangzhulingCount() {
		return bangzhulingCount;
	}
	/**
	 * 帮主令个数 t_faction.f_bangzhuling_count
	 * @param bangzhulingCount  the value for t_faction.f_bangzhuling_count
	 *
	 */
	public void setBangzhulingCount(Integer bangzhulingCount) {
		this.bangzhulingCount = bangzhulingCount;
	}
	public FactionFlag getFactionFlag(){
    	return FactionFlagManager.getInstance().getFactionFlagById(this.factionFlagId);
    }
	public String getViewName() {
		if(GameServer.configParamManger.getConfigParam().isShowMyServerId()){
			return  "["+this.getServerId()+ Language.QU+"]"+this.name;
		}
		return  this.name;
	}
	public int getMonsterModelId(){
		if(icoId<10){
			icoId=icoId+10;
		}
		return icoId+20000;
	}
	public int getDropGoodMonsterModelId(){
	    FactionFlag flag=getFactionFlag();
		return 20000+flag.getfGrade()*10+1;
	}
	public Object clone() throws CloneNotSupportedException {
		Faction temp = (Faction)super.clone();
		return temp;
	}
}
