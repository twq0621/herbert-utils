package net.snake.serverenv;

import java.util.HashMap;

/**
 * 由于登陆过程需要加载的数据比较多，需要一定的时间，
 * 如果在这段时间内玩家再次登陆，会造成一系列的安全问题
 * 维护正处于登陆状态的服务器防止反复请求
 *
 * @author serv_dev
 */
public class LoginstatusManager {
	private volatile HashMap<Integer, AccountLoginEntry> mapentry=new HashMap<Integer, LoginstatusManager.AccountLoginEntry>();
	/**未正常remove的认为是超时造成的*/
	private final static int TIME_OUT=1*60*1000;
	/**
	 * 添加处于登陆状态的帐号ID，如果已经存在，且不是因为各种异常未移除，则返回false,表示已经在登陆中了，不能再添加登陆请求
	 * 返回TRUE表示可以接受登陆处理
	 * @param accountid
	 * @return
	 */
	public synchronized boolean addAccountID(int accountid) {
		AccountLoginEntry loginentry= mapentry.get(accountid);
		//此账号不在管理中
		if(loginentry==null){
			loginentry=new AccountLoginEntry();
			loginentry.starttime=System.currentTimeMillis();
			mapentry.put(accountid, loginentry);
			return true;
		}
		//此账号登录游戏服务器超时
		if(System.currentTimeMillis()-loginentry.starttime>TIME_OUT){
			loginentry.starttime=System.currentTimeMillis();
			return true;
		}else{
			return false;
		}

	}

	public synchronized void removeAccountID(int accountid) {
		mapentry.remove(accountid);
	}

	private class AccountLoginEntry {
		public long starttime;
//		public int accountid;
	}

}
