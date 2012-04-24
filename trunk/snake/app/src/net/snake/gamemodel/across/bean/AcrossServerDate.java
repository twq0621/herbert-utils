package net.snake.gamemodel.across.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.snake.commons.DateUtil;
import net.snake.commons.TimeExpression;
import net.snake.gamemodel.map.logic.KuafuZhanTsMap;
import net.snake.ibatis.IbatisEntity;


public class AcrossServerDate implements IbatisEntity {
    /**
	 * 登陆服务器id t_across_server.f_server_id
	 * 
	 */
	private Integer serverId;
	/**
	 * 最大允许的玩家人数 t_across_server.f_maxPermitPlayerCount
	 * 
	 */
	private Integer maxpermitplayercount;
	/**
	 * 登陆服务器名称 t_across_server.f_loginServerName
	 * 
	 */
	private String loginservername;
	/**
	 * 外部IP或域名 t_across_server.f_login_server_ip
	 * 
	 */
	private String loginServerIp;
	/**
	 * 听监端口 t_across_server.f_login_server_port
	 * 
	 */
	private String loginServerPort;
	/**
	 * 聊天IP t_across_server.f_chat_server_ip
	 * 
	 */
	private String chatServerIp;
	/**
	 * 聊天端口 t_across_server.f_chat_server_port
	 * 
	 */
	private String chatServerPort;
	/**
	 * 服务器类型（标识真实物理服务器编号） t_across_server.f_server_process
	 * 
	 */
	private Integer serverProcess;
	/**
	 * 进入该服务器的key值 t_across_server.f_across_md5key
	 * 
	 */
	private String acrossMd5key;
	/**
	 * 跨服服务器进入时间限制 t_across_server.f_enter_limit_time
	 * 
	 */
	private String enterLimitTime;
	/**
	 * 跨服锁定时间 t_across_server.f_lock_time
	 * 
	 */
	private Integer lockTime;
	/**
	 * 0不可用/1可用 不要轻易配置不可用 t_across_server.f_enable
	 * 
	 */
	private Byte enable;

	/**
	 * 登陆服务器id t_across_server.f_server_id
	 * @return  the value of t_across_server.f_server_id
	 * 
	 */
	public Integer getServerId() {
		return serverId;
	}

	/**
	 * 登陆服务器id t_across_server.f_server_id
	 * @param serverId  the value for t_across_server.f_server_id
	 * 
	 */
	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	/**
	 * 最大允许的玩家人数 t_across_server.f_maxPermitPlayerCount
	 * @return  the value of t_across_server.f_maxPermitPlayerCount
	 * 
	 */
	public Integer getMaxpermitplayercount() {
		return maxpermitplayercount;
	}

	/**
	 * 最大允许的玩家人数 t_across_server.f_maxPermitPlayerCount
	 * @param maxpermitplayercount  the value for t_across_server.f_maxPermitPlayerCount
	 * 
	 */
	public void setMaxpermitplayercount(Integer maxpermitplayercount) {
		this.maxpermitplayercount = maxpermitplayercount;
	}

	/**
	 * 登陆服务器名称 t_across_server.f_loginServerName
	 * @return  the value of t_across_server.f_loginServerName
	 * 
	 */
	public String getLoginservername() {
		return loginservername;
	}

	/**
	 * 登陆服务器名称 t_across_server.f_loginServerName
	 * @param loginservername  the value for t_across_server.f_loginServerName
	 * 
	 */
	public void setLoginservername(String loginservername) {
		this.loginservername = loginservername;
	}

	/**
	 * 外部IP或域名 t_across_server.f_login_server_ip
	 * @return  the value of t_across_server.f_login_server_ip
	 * 
	 */
	public String getLoginServerIp() {
		return loginServerIp;
	}

	/**
	 * 外部IP或域名 t_across_server.f_login_server_ip
	 * @param loginServerIp  the value for t_across_server.f_login_server_ip
	 * 
	 */
	public void setLoginServerIp(String loginServerIp) {
		this.loginServerIp = loginServerIp;
	}

	/**
	 * 听监端口 t_across_server.f_login_server_port
	 * @return  the value of t_across_server.f_login_server_port
	 * 
	 */
	public String getLoginServerPort() {
		return loginServerPort;
	}

	/**
	 * 听监端口 t_across_server.f_login_server_port
	 * @param loginServerPort  the value for t_across_server.f_login_server_port
	 * 
	 */
	public void setLoginServerPort(String loginServerPort) {
		this.loginServerPort = loginServerPort;
	}

	/**
	 * 聊天IP t_across_server.f_chat_server_ip
	 * @return  the value of t_across_server.f_chat_server_ip
	 * 
	 */
	public String getChatServerIp() {
		return chatServerIp;
	}

	/**
	 * 聊天IP t_across_server.f_chat_server_ip
	 * @param chatServerIp  the value for t_across_server.f_chat_server_ip
	 * 
	 */
	public void setChatServerIp(String chatServerIp) {
		this.chatServerIp = chatServerIp;
	}

	/**
	 * 聊天端口 t_across_server.f_chat_server_port
	 * @return  the value of t_across_server.f_chat_server_port
	 * 
	 */
	public String getChatServerPort() {
		return chatServerPort;
	}

	/**
	 * 聊天端口 t_across_server.f_chat_server_port
	 * @param chatServerPort  the value for t_across_server.f_chat_server_port
	 * 
	 */
	public void setChatServerPort(String chatServerPort) {
		this.chatServerPort = chatServerPort;
	}

	/**
	 * 服务器类型（标识真实物理服务器编号） t_across_server.f_server_process
	 * @return  the value of t_across_server.f_server_process
	 * 
	 */
	public Integer getServerProcess() {
		return serverProcess;
	}

	/**
	 * 服务器类型（标识真实物理服务器编号） t_across_server.f_server_process
	 * @param serverProcess  the value for t_across_server.f_server_process
	 * 
	 */
	public void setServerProcess(Integer serverProcess) {
		this.serverProcess = serverProcess;
	}

	/**
	 * 进入该服务器的key值 t_across_server.f_across_md5key
	 * @return  the value of t_across_server.f_across_md5key
	 * 
	 */
	public String getAcrossMd5key() {
		return acrossMd5key;
	}

	/**
	 * 进入该服务器的key值 t_across_server.f_across_md5key
	 * @param acrossMd5key  the value for t_across_server.f_across_md5key
	 * 
	 */
	public void setAcrossMd5key(String acrossMd5key) {
		this.acrossMd5key = acrossMd5key;
	}

	/**
	 * 跨服服务器进入时间限制 t_across_server.f_enter_limit_time
	 * @return  the value of t_across_server.f_enter_limit_time
	 * 
	 */
	public String getEnterLimitTime() {
		return enterLimitTime;
	}

	/**
	 * 跨服服务器进入时间限制 t_across_server.f_enter_limit_time
	 * @param enterLimitTime  the value for t_across_server.f_enter_limit_time
	 * 
	 */
	public void setEnterLimitTime(String enterLimitTime) {
		this.enterLimitTime = enterLimitTime;
	}

	/**
	 * 跨服锁定时间 t_across_server.f_lock_time
	 * @return  the value of t_across_server.f_lock_time
	 * 
	 */
	public Integer getLockTime() {
		return lockTime;
	}

	/**
	 * 跨服锁定时间 t_across_server.f_lock_time
	 * @param lockTime  the value for t_across_server.f_lock_time
	 * 
	 */
	public void setLockTime(Integer lockTime) {
		this.lockTime = lockTime;
	}

	/**
	 * 0不可用/1可用 不要轻易配置不可用 t_across_server.f_enable
	 * @return  the value of t_across_server.f_enable
	 * 
	 */
	public Byte getEnable() {
		return enable;
	}

	/**
	 * 0不可用/1可用 不要轻易配置不可用 t_across_server.f_enable
	 * @param enable  the value for t_across_server.f_enable
	 * 
	 */
	public void setEnable(Byte enable) {
		this.enable = enable;
	}

	private int onlineNum=0;

	public int getOnlineNum() {
		return onlineNum;
	}

	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
		setUpdatetime(System.currentTimeMillis());
	}
	
	private long updatetime=0l;
	
	public long getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(long updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * 是开放时间？
	 * @return
	 */
	public boolean isTimeExpression() {
		if (this.enterLimitTime == null || this.enterLimitTime.length() == 0) {
			return true;
		}
		TimeExpression te = new TimeExpression(this.enterLimitTime);
		return te.isExpressionTime(System.currentTimeMillis());
	}
	public boolean isTimeExpression(long time) {
		if (this.enterLimitTime == null || this.enterLimitTime.length() == 0) {
			return true;
		}
		TimeExpression te = new TimeExpression(this.enterLimitTime);
		return te.isExpressionTime(time);
	}
   /**
    * 获取下下一次跨服战时间
    * @return
    */
	public Date getNextKuafuzhan() {
		if (this.enterLimitTime == null || this.enterLimitTime.length() == 0) {
			return null;
		}
		Date now=new Date();
		Calendar calendar= DateUtil.dateToCalendar(now);
		int hourse=calendar.get(Calendar.HOUR_OF_DAY);
		if(hourse<KuafuZhanTsMap.startHourse){
			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
		}
		long nowStart=calendar.getTimeInMillis();
		TimeExpression te = new TimeExpression(this.enterLimitTime);
		Date nextDay = TimeExpression.getNextDay(te, nowStart);
		Calendar recordCalendar =new GregorianCalendar();
		recordCalendar.setTime(nextDay);
		recordCalendar.set(Calendar.HOUR_OF_DAY,KuafuZhanTsMap.startHourse);
		recordCalendar.set(Calendar.MINUTE,0);
		recordCalendar.set(Calendar.SECOND,0);
		return recordCalendar.getTime();
	}
}
