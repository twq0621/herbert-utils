package net.snake.gameserver.event.goods;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.snake.commons.script.IEventListener;
import net.snake.commons.script.SApi;
import net.snake.commons.script.SGoods;
import net.snake.commons.script.SHorse;
import net.snake.commons.script.SRole;
import net.snake.script.util.GenerateUtil;

import org.apache.log4j.Logger;

public class MonsterGoodDrop1001 implements IEventListener{
	private static final Logger logger = Logger.getLogger(MonsterGoodDrop1001.class);
	public final int dropGoodScripNum = 1001;
	public final int zuoqizhufudanId = 1211;
	public final int chaojizizhidanId = 1210;
	public final int actionType1 = 1;
	public final int actionType2 = 2;
	public final int actionType3 = 3;
	public final int actionType4 = 4;
	public final int actionType5= 5; //暗灵石掉落类别
	public final int chaojiSavvyId = 1214;
	public final int shenqiSkillId = 1218;
	public final int longxuedanId = 1221;
	public final int longlindanId = 1223;
	public final int duantijindanId = 1303;
	public final int anlingshiId = 1302;
	public final int bowZhufudangId = 1305;
	public final int bowJianshuBaojiId = 1306;
	public final int dantianZhufu=1308;
	public final int yangtianmijiId=9117;
	public final int zuoqiChuanshuoId=1309; //坐骑传说秘籍丹
	public final String startTime = "2011-07-31 00:00:00";
	public final String lostTime = "2011-08-02 23:59:59";

	/**
	 * 坐骑传说秘籍丹掉落
	 */
	private void dropGoodO(SApi api, SRole role) {
		int jilv = 0;
		int count = role.getBadGoodCountByGoodId(zuoqiChuanshuoId)
				+ role.getStorageGoodCountByGoodId(zuoqiChuanshuoId)
				+ role.getStallGoodCountByGoodId(zuoqiChuanshuoId);
		if (count > 0) {
			return;
		}
		jilv = 5000;
		boolean isDrop = isGenerateToBoolean(jilv, 10000);
		if (isDrop) {
			SGoods good = api.createCharacterGood(zuoqiChuanshuoId, 1, 0, 1,
					stringToDate(lostTime));
			boolean b = api.addCharacterGoods(role, good);
			if (b) {
				api.sendScripGoodToBadMsg(role, zuoqiChuanshuoId);
			}
		}
	}
	/**
	 * 养天秘籍掉落
	 */
	@SuppressWarnings("unused")
	private void dropGoodN(SApi api, SRole role) {
		int jilv = 0;
		int count = role.getBadGoodCountByGoodId(yangtianmijiId)
				+ role.getStorageGoodCountByGoodId(yangtianmijiId)
				+ role.getStallGoodCountByGoodId(yangtianmijiId);
		if (count > 0) {
			return;
		}
		jilv = 5000;
		boolean isDrop = isGenerateToBoolean(jilv, 10000);
		if (isDrop) {
			SGoods good = api.createCharacterGood(yangtianmijiId, 1, 0, 1,
					stringToDate(lostTime));
			boolean b = api.addCharacterGoods(role, good);
			if (b) {
				api.sendScripGoodToBadMsg(role, yangtianmijiId);
			}
		}
	}
	/**
	 * 暗灵石掉落
	 * 
	 * @param api
	 * @param role
	 */
	@SuppressWarnings("unused")
	private void dropGoodI(SApi api, SRole role) {
		if (role.getCount(actionType5) > 0) {
			return;
		}
		int maxkind = 0;
		int jilv = 3000;
		SHorse horse = role.getMaxBagPinjieHorse();
		if (horse != null) {
			if (maxkind < horse.getKind()) {
				maxkind = horse.getKind();
			}
		}
		SHorse storagehorse = role.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxkind < storagehorse.getKind()) {
				maxkind = storagehorse.getKind();
			}
		}
		if (maxkind > 7) {
			jilv = 10000;
		}
		boolean isDrop = isGenerateToBoolean(jilv, 10000);
		if (isDrop) {
			SGoods good = api.createCharacterGood(anlingshiId, 1, 0, 1,
					stringToDate(lostTime));
			boolean b = api.addCharacterGoods(role, good);
			if (b) {
				api.sendScripGoodToBadMsg(role, anlingshiId);
				api.characterCount(role, actionType5, 1);
			}
			return;
		}
	}
	/**
	 * 段体金丹掉落
	 */
	@SuppressWarnings("unused")
	private void dropGoodH(SApi api, SRole role) {
		int jilv = 0;
		int count = role.getBadGoodCountByGoodId(duantijindanId)
				+ role.getStorageGoodCountByGoodId(duantijindanId)
				+ role.getStallGoodCountByGoodId(duantijindanId);
		if (count > 0) {
			return;
		}
		jilv = 5000;
		boolean zuoqizhufudan = isGenerateToBoolean(jilv, 10000);
		if (zuoqizhufudan) {
			SGoods good = api.createCharacterGood(duantijindanId, 1, 0, 1,
					stringToDate(lostTime));
			boolean b = api.addCharacterGoods(role, good);
			if (b) {
				api.sendScripGoodToBadMsg(role, duantijindanId);
			}
		}
	}
	@SuppressWarnings("unused")
	private void dropGoodA(SApi api, SRole role) {
		int rand = GenerateUtil.randomIntValue(0, 10000);
		int quejian = 500;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(1201, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(6105, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(6101, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(6102, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}

		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(6106, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(6103, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(6104, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(1014, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 1000;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(9105, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(9101, 1, 0, 0, null);
			api.addCharacterGoods(role, good);
			return;
		}
		quejian = quejian + 300;
		if (rand < quejian) {
			SGoods good = api.createCharacterGood(1018, 1, 0, 1, null);
			api.addCharacterGoods(role, good);
			return;
		}
	}

	/**
	 * 祝福丹
	 * 
	 * @param api
	 * @param role
	 */
	private void dropGoodB(SApi api, SRole role) {
		int jilv = 0;
		int zhufudanCount = role.getBadGoodCountByGoodId(zuoqizhufudanId)
				+ role.getStorageGoodCountByGoodId(zuoqizhufudanId)
				+ role.getStallGoodCountByGoodId(zuoqizhufudanId);
		if (zhufudanCount > 0) {
			return;
		}
		jilv = 5000;
		boolean zuoqizhufudan = isGenerateToBoolean(jilv, 10000);
		if (zuoqizhufudan) {
			SGoods good = api.createCharacterGood(zuoqizhufudanId, 1, 0, 1,
					stringToDate(lostTime));
			api.addCharacterGoods(role, good);
			api.sendScripGoodToBadMsg(role, zuoqizhufudanId);
			return;
		}
	}
	/**
	 * 丹田祝福丹
	 * 
	 * @param api
	 * @param role
	 */
	@SuppressWarnings("unused")
	private void dropGoodM(SApi api, SRole role) {
		int jilv = 0;
		int zhufudanCount = role.getBadGoodCountByGoodId(dantianZhufu)
				+ role.getStorageGoodCountByGoodId(dantianZhufu)
				+ role.getStallGoodCountByGoodId(dantianZhufu);
		if (zhufudanCount > 0) {
			return;
		}
		jilv = 5000;
		boolean zuoqizhufudan = isGenerateToBoolean(jilv, 10000);
		if (zuoqizhufudan) {
			SGoods good = api.createCharacterGood(dantianZhufu, 1, 0, 1,
					stringToDate(lostTime));
			api.addCharacterGoods(role, good);
			api.sendScripGoodToBadMsg(role, dantianZhufu);
			return;
		}
	}

	/**
	 * 坐骑超级资质丹
	 * 
	 * @param api
	 * @param role
	 */
	private void dropGoodC(SApi api, SRole role) {
		int chaozizhiCount = role.getBadGoodCountByGoodId(chaojizizhidanId)
				+ role.getStorageGoodCountByGoodId(chaojizizhidanId)
				+ role.getStallGoodCountByGoodId(chaojizizhidanId);
		if (chaozizhiCount > 0) {
			return;
		}
		int jilv = 0;
		int maxkind = 0;
		SHorse horse = role.getMaxBagPinjieHorse();
		if (horse != null) {
			if (maxkind < horse.getKind()) {
				maxkind = horse.getKind();
			}
		}
		SHorse storagehorse = role.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxkind < storagehorse.getKind()) {
				maxkind = storagehorse.getKind();
			}
		}
		if (maxkind < 7) {
			return;
		} else if (maxkind == 7) {
			jilv = 10;
		} else if (maxkind > 7) {
			jilv = 5000;
		}
		boolean zuoqizhufudan = isGenerateToBoolean(jilv, 10000);
		if (zuoqizhufudan) {
			SGoods good = api.createCharacterGood(chaojizizhidanId, 1, 0, 1,
					stringToDate(lostTime));
			api.addCharacterGoods(role, good);
			api.sendScripGoodToBadMsg(role, chaojizizhidanId);
			return;
		}
	}

	/**
	 * 神奇技能丹
	 * 
	 * @param api
	 * @param role
	 */
	private void dropGoodD(SApi api, SRole role) {
		if (role.getCount(actionType4) > 0) {
			return;
		}
		int maxkind = 0;
		int jilv = 3000;
		SHorse horse = role.getMaxBagPinjieHorse();
		if (horse != null) {
			if (maxkind < horse.getKind()) {
				maxkind = horse.getKind();
			}
		}
		SHorse storagehorse = role.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxkind < storagehorse.getKind()) {
				maxkind = storagehorse.getKind();
			}
		}
		if (maxkind > 7) {
			jilv = 10000;
		}
		boolean isDrop = isGenerateToBoolean(jilv, 10000);
		if (isDrop) {
			SGoods good = api.createCharacterGood(shenqiSkillId, 1, 0, 1,
					stringToDate(lostTime));
			boolean b = api.addCharacterGoods(role, good);
			if (b) {
				api.sendScripGoodToBadMsg(role, shenqiSkillId);
				api.characterCount(role, actionType4, 1);
			}
			return;
		}
	}

	/**
	 * 悟性丹
	 * 
	 * @param api
	 * @param role
	 */
	private void dropGoodE(SApi api, SRole role) {
		if (role.getCount(actionType2) > 0) {
			return;
		}
		int maxkind = 0;
		int jilv = 3000;
		SHorse horse = role.getMaxBagPinjieHorse();
		if (horse != null) {
			if (maxkind < horse.getKind()) {
				maxkind = horse.getKind();
			}
		}
		SHorse storagehorse = role.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxkind < storagehorse.getKind()) {
				maxkind = storagehorse.getKind();
			}
		}
		if (maxkind > 7) {
			jilv = 10000;
		}
		boolean isDrop = isGenerateToBoolean(jilv, 10000);
		if (isDrop) {
			SGoods good = api.createCharacterGood(chaojiSavvyId, 1, 0, 1,
					stringToDate(lostTime));
			boolean b = api.addCharacterGoods(role, good);
			if (b) {
				api.sendScripGoodToBadMsg(role, chaojiSavvyId);
				api.characterCount(role, actionType2, 1);
			}
			return;
		}
	}

	/**
	 * 坐骑溶血丹
	 * 
	 * @param api
	 * @param role
	 */
	@SuppressWarnings("unused")
	private void dropGoodF(SApi api, SRole role) {
		if (role.getCount(actionType3) > 1) {
			return;
		}
		int maxkind = 0;
		SHorse horse = role.getMaxBagPinjieHorse();
		if (horse != null) {
			if (maxkind < horse.getKind()) {
				maxkind = horse.getKind();
			}
		}
		SHorse storagehorse = role.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxkind < storagehorse.getKind()) {
				maxkind = storagehorse.getKind();
			}
		}
		if (maxkind < 9) {
			return;
		}
		int jilv = 8000;
		boolean longxue = isGenerateToBoolean(jilv, 10000);
		if (longxue) {
			SGoods good = api.createCharacterGood(longxuedanId, 1, 0, 1,
					stringToDate(lostTime));
			boolean b = api.addCharacterGoods(role, good);
			if (b) {
				api.sendScripGoodToBadMsg(role, longxuedanId);
				api.characterCount(role, actionType3, 1);
			}
			return;
		}
	}

	/**
	 * 龙林丹掉落
	 * 
	 * @param api
	 * @param role
	 */
	private void dropGoodG(SApi api, SRole role) {
		int jilv = 0;
		int zhufudanCount = role.getBadGoodCountByGoodId(longlindanId)
				+ role.getStorageGoodCountByGoodId(longlindanId)
				+ role.getStallGoodCountByGoodId(longlindanId);
		if (zhufudanCount > 0) {
			return;
		}
		jilv = 5000;
		boolean zuoqizhufudan = isGenerateToBoolean(jilv, 10000);
		if (zuoqizhufudan) {
			SGoods good = api.createCharacterGood(longlindanId, 1, 0, 1,
					stringToDate(lostTime));
			api.addCharacterGoods(role, good);
			api.sendScripGoodToBadMsg(role, longlindanId);
			return;
		}
	}

	/**
	 * 弓箭祝福丹
	 * 
	 * @param api
	 * @param role
	 */
	private void dropGoodK(SApi api, SRole role) {
		int count = role.getBadGoodCountByGoodId(bowZhufudangId)
				+ role.getStorageGoodCountByGoodId(bowZhufudangId)
				+ role.getStallGoodCountByGoodId(bowZhufudangId);
		if (count > 0) {
			return;
		}
		int jilv = 0;
		int maxkind = 0;
		SHorse horse = role.getMaxBagPinjieHorse();
		if (horse != null) {
			if (maxkind < horse.getKind()) {
				maxkind = horse.getKind();
			}
		}
		SHorse storagehorse = role.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxkind < storagehorse.getKind()) {
				maxkind = storagehorse.getKind();
			}
		}
		 if (maxkind <= 7) {
			jilv = 10;
		} else if (maxkind > 7) {
			jilv = 5000;
		}
		boolean zuoqizhufudan = isGenerateToBoolean(jilv, 10000);
		if (zuoqizhufudan) {
			SGoods good = api.createCharacterGood(bowZhufudangId, 1, 0, 1,
					stringToDate(lostTime));
			api.addCharacterGoods(role, good);
			api.sendScripGoodToBadMsg(role, bowZhufudangId);
			return;
		}
	}

	/**
	 * 弓箭剑术保级丹
	 * 
	 * @param api
	 * @param role
	 */
	private void dropGoodL(SApi api, SRole role) {
		int count = role.getBadGoodCountByGoodId(bowJianshuBaojiId)
				+ role.getStorageGoodCountByGoodId(bowJianshuBaojiId)
				+ role.getStallGoodCountByGoodId(bowJianshuBaojiId);
		if (count > 0) {
			return;
		}
		int jilv = 0;
		int maxkind = 0;
		SHorse horse = role.getMaxBagPinjieHorse();
		if (horse != null) {
			if (maxkind < horse.getKind()) {
				maxkind = horse.getKind();
			}
		}
		SHorse storagehorse = role.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxkind < storagehorse.getKind()) {
				maxkind = storagehorse.getKind();
			}
		}
		 if (maxkind <= 7) {
			jilv = 10;
		} else if (maxkind > 7) {
			jilv = 5000;
		}
		boolean zuoqizhufudan = isGenerateToBoolean(jilv, 10000);
		if (zuoqizhufudan) {
			SGoods good = api.createCharacterGood(bowJianshuBaojiId, 1, 0, 1,
					stringToDate(lostTime));
			api.addCharacterGoods(role, good);
			api.sendScripGoodToBadMsg(role, bowJianshuBaojiId);
			return;
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
		@SuppressWarnings("unused")
		int monsterGrade=(Integer)args[2];
		
		if (dropScripNum != dropGoodScripNum) {
			return;
		}
		Date date = stringToDate(lostTime);
		if (date == null || date.getTime() < System.currentTimeMillis()) {
			return;
		}
		Date startDate = stringToDate(startTime);
		if (startDate == null
				|| startDate.getTime() > System.currentTimeMillis()) {
			return;
		}
	  // dropGoodA(api, role);
		 dropGoodB(api, role); // 坐骑祝福丹
		 dropGoodC(api, role);//坐骑超级资质丹
		 dropGoodD(api, role);//神奇技能丹
		 dropGoodE(api, role);//坐骑悟性丹
  //	 dropGoodF(api, role);
		 dropGoodG(api, role);//龙鳞丹
	 //	 dropGoodH(api, role);
	  // dropGoodI(api, role);//暗灵石掉落
		 dropGoodK(api, role);//弓箭祝福丹
		 dropGoodL(api, role);//弓箭保级丹
//		 dropGoodM(api, role);//丹田祝福丹
	//	 dropGoodN(api, role);//养天秘籍掉落
		 dropGoodO(api, role);//坐骑传说秘籍丹掉落
		
	}
}
