package net.snake.gamemodel.gift.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.snake.commons.program.Updatable;
import net.snake.commons.script.IEventListener;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.CopperAction;
import net.snake.events.AppEventCtlor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.CharacterGiftpacksManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.netio.ServerResponse;
//import net.snake.script.ScriptEventCenter;
import net.snake.shell.Options;

/**
 * 角色礼包管理器
 * 
 * @author serv_dev
 */
public class MyCharacterGiftpacksManger implements Updatable {
	private static Logger logger = Logger.getLogger(MyCharacterGiftpacksManger.class);

	private Map<Integer, CharacterGiftpacks> map = new HashMap<Integer, CharacterGiftpacks>();
	private Hero character;
	private RoleGradeGiftPacksManger roleGradeGiftPacksManger = new RoleGradeGiftPacksManger(this);
	private RoleMeetGiftPacksManger roleMeetGiftPacksManger = new RoleMeetGiftPacksManger(this);
	private RoleLoginGiftPacksManger roleLoginGiftPacksManger = new RoleLoginGiftPacksManger(this);
	private RoleOlineGiftPacksManger roleOnlineGiftPacksManger = new RoleOlineGiftPacksManger(this);
	private RoleInviteGiftPacksManger roleInviteGiftPacksManger = new RoleInviteGiftPacksManger(this);
	private RoleDayLineGiftPacks roleDayLineGiftPacks = new RoleDayLineGiftPacks(this);
	private RoleEachLevelGiftPacksManager roleEachLevelGiftPacksManager = new RoleEachLevelGiftPacksManager(this);
	private RoleDayQiandaoGiftPacksManager roleDayQiandaoGiftPacksManager = new RoleDayQiandaoGiftPacksManager(this);

	// private boolean isFirst=true;
	public MyCharacterGiftpacksManger(Hero character) {
		this.character = character;
	}

	public void destory() {
		map.clear();
	}

	/**
	 * 初始化角色礼包数据
	 */
	public void init() {
		try {
			if (Options.IsCrossServ) {
				return;
			}
			List<CharacterGiftpacks> list = CharacterGiftpacksManager.getInstance().selecteListByCharacterId(character.getId());
			GiftPacksManager gpm = GiftPacksManager.getInstance();
			for (CharacterGiftpacks cgp : list) {
				map.put(cgp.getGiftPacksId(), cgp);
				GiftPacks gp = gpm.getGiftPacksById(cgp.getGiftPacksId());
				cgp.setGp(gp);
				if (gp.getType() == GiftPacks.gradeType) {
					roleGradeGiftPacksManger.setNowGift(cgp);
				} else if (gp.getType() == GiftPacks.meetType) {
					roleMeetGiftPacksManger.setNowGift(cgp);
				} else if (gp.getType() == GiftPacks.inviteType) {
					roleMeetGiftPacksManger.setNowGift(cgp);
				} else if (gp.getType() == GiftPacks.onlineType) {
					roleOnlineGiftPacksManger.setNowGift(cgp);
				} else if (gp.getType() == GiftPacks.dayQiandaoType) {
					roleDayQiandaoGiftPacksManager.setNowGift(cgp);
				} else if (gp.getType() == GiftPacks.eachLevelType) {
					roleEachLevelGiftPacksManager.setNowGift(cgp);
				}
			}
			roleGradeGiftPacksManger.init();
			roleMeetGiftPacksManger.init();
			roleLoginGiftPacksManger.init();
			roleOnlineGiftPacksManger.init();
			roleInviteGiftPacksManger.init();
			roleDayLineGiftPacks.init();
			roleDayQiandaoGiftPacksManager.init();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 进入场景发送礼包信息
	 */
	public void sendGiftMsgToClientWhenFirstEnterScene() {
		if (Options.IsCrossServ) {
			return;
		}
		if (!character.isFristEnterMap()) {
			return;
		}
		// isFirst=false;
		roleMeetGiftPacksManger.sendMeetGiftToClient();
		online();
	}

	public RoleInviteGiftPacksManger getRoleInviteGiftPacksManger() {
		return roleInviteGiftPacksManger;
	}

	public CharacterGiftpacks getCharacterGiftpacksByGiftId(int giftId) {
		return map.get(giftId);
	}

	public CharacterGiftpacks addCharacterGiftpacks(CharacterGiftpacks cgp) {
		return map.put(cgp.getGiftPacksId(), cgp);
	}

	public void removeCharacterGiftpacks(CharacterGiftpacks cgp) {
		map.remove(cgp.getGiftPacksId());
	}

	public Hero getCharacter() {
		return character;
	}

	public void setCharacter(Hero character) {
		this.character = character;
	}

	public void sendMsgToClient(ServerResponse msg) {
		this.character.sendMsg(msg);
	}

	public RoleGradeGiftPacksManger getRoleGradeGiftPacksManger() {
		return roleGradeGiftPacksManger;
	}

	public RoleMeetGiftPacksManger getRoleMeetGiftPacksManger() {
		return roleMeetGiftPacksManger;
	}

	@Override
	public void update(long now) {
		if (Options.IsCrossServ) {
			return;
		}
		roleMeetGiftPacksManger.update();
		roleLoginGiftPacksManger.update();
		roleOnlineGiftPacksManger.update();
		roleDayLineGiftPacks.update();
		roleDayQiandaoGiftPacksManager.update();
	}

	public void updateWhenDownLine() {
		if (Options.IsCrossServ) {
			return;
		}
		roleMeetGiftPacksManger.downline();
		roleLoginGiftPacksManger.downline();
		// roleLoginGiftPacksManger.downline();
		roleOnlineGiftPacksManger.downline();
		roleDayLineGiftPacks.downLine();
	}

	public RoleLoginGiftPacksManger getRoleLoginGiftPacksManger() {
		return roleLoginGiftPacksManger;
	}

	public RoleOlineGiftPacksManger getRoleOnlineGiftPacksManger() {
		return roleOnlineGiftPacksManger;
	}

	public RoleDayLineGiftPacks getRoleDayLineGiftPacks() {
		return roleDayLineGiftPacks;
	}

	public RoleDayQiandaoGiftPacksManager getRoleDayQiandaoGiftPacksManager() {
		return roleDayQiandaoGiftPacksManager;
	}

	/**
	 * 打开物品礼包
	 * 
	 * @param cg
	 */
	public void openGoodGiftPacks(CharacterGoods cg) {
		if (cg.isInTrade()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 653));
			return;
		}
		GiftPacks gp = GiftPacksManager.getInstance().getGiftPacksById(cg.getGoodModel().getGiftPacksId());
		if (gp == null) {
			return;
		}
		if (gp.getType() == 5) {
			List<CharacterGoods> remove = new ArrayList<CharacterGoods>();
			CharacterGoods delGoods = null;
			try {
				delGoods = (CharacterGoods) cg.clone();
			} catch (CloneNotSupportedException e) {
				logger.error(e.getMessage(), e);
			}
			if (delGoods == null) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 654));
				return;
			}
			delGoods.setCount(1);
			remove.add(delGoods);
			List<CharacterGoods> list = new ArrayList<CharacterGoods>();
			Collection<CharacterGoods> collection = gp.getGoodlist(character.getPopsinger());
			for (CharacterGoods cgs : collection) {
				CharacterGoods temp;
				try {
					temp = (CharacterGoods) cgs.clone();
					if (cgs.isIgnoreBind()) {
						temp.setBind(CommonUseNumber.byte0);
					} else {
						temp.setBind(cg.getBind());
					}
					if (cgs.getToBadLostTime() > 0) {
						temp.setLastDate(new Date(System.currentTimeMillis() + cgs.getToBadLostTime() * 1000));
					}
					list.add(temp);
				} catch (CloneNotSupportedException e) {
					logger.error(e.getMessage(), e);
				}
			}
			boolean b = character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(remove, list);
			if (!b) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1150));
			} else {
				CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
				CharacterPropertyManager.changeLijin(character, gp.getLijin());
				// ScriptEventCenter.getInstance().onRoleUseGoods(null, character,
				// cg);
				AppEventCtlor.getInstance().publishEvent(IEventListener.Evt_UseGoods, new Object[] { character, cg });
				sendGiftPaksNotic(cg, list);
			}
		} else {
			roleGradeGiftPacksManger.useGoodDoSomething(cg);
		}

	}

	public void sendGiftPaksNotic(CharacterGoods libao, List<CharacterGoods> list) {
		List<CharacterGoods> temp = new ArrayList<CharacterGoods>();
		for (CharacterGoods cg : list) {
			if (cg.isNoticeGood) {
				temp.add(cg);
			}
		}
		if (temp.size() == 0) {
			return;
		}
		String str = null;
		for (int i = 0; i < temp.size(); i++) {
			CharacterGoods cg = temp.get(i);
			if (str == null) {
				str = cg.getGoodModel().getNameI18n();
			} else {
				str = str + "," + cg.getGoodModel().getNameI18n();
			}
		}
		character.sendMsg(new PrompMsg(TipMsg.QICHU_MSG, 19200, character.getViewName(), libao.getGoodModel().getNameI18n(), str));
	}

	public void online() {
		if (Options.IsCrossServ) {
			return;
		}
		roleLoginGiftPacksManger.online();
		roleOnlineGiftPacksManger.sendHaveGetGiftPacksMsg();
	}

	public void sendHaveGetGiftPacksMsg() {
		roleOnlineGiftPacksManger.sendHaveGetGiftPacksMsg();
		roleLoginGiftPacksManger.sendHaveGetGiftPacksMsg();
	}

	public static Date stringToDate(String dateStr) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		try {
			Date date = df.parse(dateStr);
			return date;
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("stringToDate(String) - " + ex.getMessage()); //$NON-NLS-1$
			}
		}
		return null;
	}

	/**
	 * 清除成就
	 * 
	 * @param countType
	 * @param count
	 */
	public void clearAchieve(int countType, int count) {
		character.getMyCharacterAchieveCountManger().getGiftPacksCount().clearGiftPacksCount(countType, count);
	}

	/**
	 * 清除成就
	 * 
	 * @param countType
	 * @param count
	 */
	public void initAchieve(int countType, int count) {
		character.getMyCharacterAchieveCountManger().getGiftPacksCount().initGiftAchieveMemoryCount(countType, count);
	}

	/**
	 * 更新成就
	 * 
	 * @param countType
	 * @param count
	 */
	public void updateAchieve(int countType, int count) {
		character.getMyCharacterAchieveCountManger().getGiftPacksCount().giftPacksCount(countType, count);
	}

	public RoleEachLevelGiftPacksManager getRoleEachLevelGiftPacksManager() {
		return roleEachLevelGiftPacksManager;
	}

}
