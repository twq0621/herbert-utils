package net.snake.gameserver.event.goods;

import java.text.SimpleDateFormat;
import java.util.Date;


import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SRole;

import org.apache.log4j.Logger;

public class MonsterGoodDrop1002 implements IEventListener {
	private static final Logger logger = Logger.getLogger(MonsterGoodDrop1002.class);
	public final int dropGoodScripNum = 1002;

	public final String startTime = "2011-07-31 00:00:00";
	public final String lostTime = "2011-08-02 23:59:59";
	//礼金兑换掉落配置
	public final int actionType1=102; // 兑换礼金物品掉落数量类型
	public final int dropGoodA = 21026; //
	public final int actionType1Count=10; // 兑换礼金物品掉落数量
    public final int bad1Count=2000;
    public final int jilvA=100;
    
    //道具兑换掉落配置
	public final int actionType2=103; // 兑换奖励物品掉落数量类型
	public final int dropGoodB = 21027; // 道具兑换掉落物品id
    public final int bad2Count=50; //包裹掉落物品数量上限
    public final int actionType2Count=1; // 兑换奖励物品掉落数量上限
    public final int jilvB=100;  //几率
  

//	@Override
//	public void onMonsterDropGoods(SApi api, SRole role, int dropScripNum,int monsterGrade) {
//		if (dropScripNum != dropGoodScripNum) {
//			return;
//		}
//		Date date = stringToDate(lostTime);
//		if (date == null || date.getTime() < System.currentTimeMillis()) {
//			return;
//		}
//		Date startDate = stringToDate(startTime);
//		if(startDate==null||startDate.getTime()>System.currentTimeMillis()){
//			return;
//		}
//		if(monsterGrade-role.getGrade()<5){
//			return;
//		}
//		dropGoodB(api, role);
//	}

       /**
        * 掉落兑换礼金的道具奖励物品
        * @param api
        * @param role
        */
	@SuppressWarnings("unused")
	private void dropGoodA(SApi api, SRole role) {
		dropGoodTypeA(api,role,actionType1,dropGoodA,actionType1Count,bad1Count,jilvA);
	}
    /**
     * 掉落兑换道具的道具奖励物品
     * @param api
     * @param role
     */
	private void dropGoodB(SApi api, SRole role) {
		dropGoodTypeA(api,role,actionType2,dropGoodB,actionType2Count,bad2Count,jilvB);
	}
    /**
     * 掉落物品
     * @param api
     * @param role
     */
	private void dropGoodTypeA(SApi api, SRole role,int actionType,int dropGood,int actionCount,int badCount,int jilv) {
//		if (role.getCount(actionType) >= actionCount) {
//			return;
//		}
		int dropGoodACount = role.getBadGoodCountByGoodId(dropGood)
				+ role.getStorageGoodCountByGoodId(dropGood)
				+ role.getStallGoodCountByGoodId(dropGood);
		if (dropGoodACount >= badCount) {
			return;
		}
		boolean b = isGenerateToBoolean(jilv, 10000);
		if (b) {
			SGoods good = api.createCharacterGood(dropGood, 1, 0, 0,
					stringToDate(lostTime));
			boolean isTrue=api.addCharacterGoods(role, good);
			if(isTrue){
			   api.sendScripGoodToBadMsg(role, dropGood);
			}
		}
	}
	/**
	 * 返回在0-maxcout之间产生的随机数时候小于num
	 * 
	 * @param num
	 * @return
	 */
	private boolean isGenerateToBoolean(int num, int maxcout) {
		double count = Math.random() * maxcout;
		if (count < num) {
			return true;
		}
		return false;
	}

	public Date stringToDate(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		}
		return null;
	}

	@Override
	public int getInterestEvent() {
		return IEventListener.Evt_MonsterGoodDrop;
	}

	@Override
	public void handleEvent(SApi api, Object[] args) {
		//SRole role, int dropScripNum,int monsterGrade
		SRole role = (SRole)args[0];
		int dropScripNum = (Integer)args[1];
		int monsterGrade = (Integer) args[2];
		
		if (dropScripNum != dropGoodScripNum) {
			return;
		}
		Date date = stringToDate(lostTime);
		if (date == null || date.getTime() < System.currentTimeMillis()) {
			return;
		}
		Date startDate = stringToDate(startTime);
		if(startDate==null||startDate.getTime()>System.currentTimeMillis()){
			return;
		}
		if(monsterGrade-role.getGrade()<5){
			return;
		}
		dropGoodB(api, role);
	}
}
