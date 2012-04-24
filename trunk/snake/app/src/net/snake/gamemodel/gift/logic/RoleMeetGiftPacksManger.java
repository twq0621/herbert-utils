package net.snake.gamemodel.gift.logic;

import java.util.List;

import net.snake.GameServer;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.CharacterGiftpacksManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.gift.response.GiftPacksMeetListMsg50722;
import net.snake.gamemodel.gift.response.GiftPacksMeetMsg50714;
import net.snake.gamemodel.gift.response.GiftPacksMeetMsg50716;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.shell.Options;

/**
 * 角色见面有礼管理
 * 
 * @author serv_dev
 */
public class RoleMeetGiftPacksManger {
	private long startTime = System.currentTimeMillis();
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	private CharacterGiftpacks nowGift;
	/** a是否第一次发送列表 */
	private boolean isFirst = true; //

	public RoleMeetGiftPacksManger(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public void init() {
		if (this.nowGift == null) {
			List<GiftPacks> gplist = GiftPacksManager.getInstance().getMeetGiftList();
			if(gplist.size()<=0){
				return;
			}
			GiftPacks gp = gplist.get(0);
			CharacterGiftpacks cgp = new CharacterGiftpacks();
			cgp.setCharacterId(myCharacterGiftpacksManger.getCharacter().getId());
			cgp.setGiftPacksId(gp.getId());
			cgp.setGp(gp);
			cgp.setIsGet(false);
			cgp.setIsOwner(false);
			cgp.setTime(0l);
			this.nowGift = cgp;
			GameServer.executorServiceForDB.execute(new Runnable() {
				@Override
				public void run() {
					CharacterGiftpacksManager.getInstance().insert(nowGift);
				}
			});
		}

	}

	/**
	 * 见面有礼领取时间
	 * 
	 * @return
	 */
	public boolean openMeetGiftPacks() {
		if (Options.IsCrossServ) {
			return false;
		}
		Hero character = myCharacterGiftpacksManger.getCharacter();
		if (this.nowGift.getIsOwner()) {
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 664));
			return false;
		}
		GiftPacks gp = this.nowGift.getGp();
		long now = System.currentTimeMillis();
		long time = this.nowGift.getTime() + now - startTime;
		long lingquTime = this.nowGift.getGp().getTimeLimit() * 60000;
		if (time > lingquTime) {
			this.nowGift.setIsGet(true);
		}
		if (!this.nowGift.getIsGet()) {
			int t = (int) ((time - lingquTime) / 1000);
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50714(nowGift, t));
			return false;
		}
		if (gp.getCopper() + character.getCopper() > MaxLimit.BAG_COPPER_MAX) {
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50716(nowGift));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 658));
			return false;
		}
		if (gp.getLijin() + character.getJiaozi() > MaxLimit.LIJIN_MAX) {
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50716(nowGift));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 659));
			return false;
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForRemoveAndAddGoods(null, gp.getGoodlist(character.getPopsinger()))) {
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50716(nowGift));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 660));
			return false;
		}
		CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
		CharacterPropertyManager.changeLijin(character, gp.getLijin());
		character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(null, gp.getGoodlist(character.getPopsinger()));
		addGiftPacksMeet();
		if (!isFirst) {
			sendMeetGiftList();
		}
		return true;
	}

	private void addGiftPacksMeet() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		GiftPacks nextGp = getNextMeetGift(this.nowGift.getGp());
		if (nextGp == null) {
			nowGift.setIsGet(true);
			nowGift.setIsOwner(true);
			CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(character, nowGift);
			return;
		}
		nowGift.setGiftPacksId(nextGp.getId());
		nowGift.setGp(nextGp);
		nowGift.setIsGet(false);
		nowGift.setIsOwner(false);
		nowGift.setTime(0l);
		this.startTime = System.currentTimeMillis();
		CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(character, nowGift);
		// 发送新的计时物品
		sendMeetGiftToClient();
	}

	private GiftPacks getNextMeetGift(GiftPacks nowgp) {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getMeetGiftList();
		int id = nowgp.getId();
		for (GiftPacks gp : gplist) {
			if (gp.getId() > id) {
				return gp;
			}
		}
		return null;
	}

	/**
	 * 发送见面有礼
	 */
	public void sendMeetGiftToClient() {
		if (this.nowGift == null || this.nowGift.getIsOwner()) {
			return;
		}
		long now = System.currentTimeMillis();
		long time = this.nowGift.getTime() + now - startTime;
		long lingquTime = this.nowGift.getGp().getTimeLimit() * 60000;
		if (time > lingquTime) {
			this.nowGift.setIsGet(true);
		}
		if (this.nowGift.getIsGet()) {
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50716(nowGift));
		} else {
			int t = (int) ((lingquTime - time) / 1000);
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50714(nowGift, t));
		}
	}

	public void sendMeetGiftList() {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getMeetGiftList();
		myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetListMsg50722(nowGift, gplist, myCharacterGiftpacksManger.getCharacter()));
		this.isFirst = false;
	}

	/**
	 * 更新调用此方法
	 */
	public void update() {
		if (nowGift==null || this.nowGift.getIsOwner() || this.nowGift.getIsGet()) {
			return;
		}
		long now = System.currentTimeMillis();
		long time = this.nowGift.getTime() + now - startTime;
		long lingquTime = this.nowGift.getGp().getTimeLimit() * 60000;
		if (time > lingquTime) {
			this.nowGift.setIsGet(true);
		}
		if (this.nowGift.getIsGet()) {
			myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksMeetMsg50716(nowGift));
		}
	}

	public void downline() {
		if (this.nowGift == null) {
			return;
		}

		if (this.nowGift.getIsGet() || this.nowGift.getIsOwner()) {
			CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(myCharacterGiftpacksManger.getCharacter(), nowGift);
			return;
		}
		long now = System.currentTimeMillis();
		long time = this.nowGift.getTime() + now - startTime;
		long lingquTime = this.nowGift.getGp().getTimeLimit() * 60000;
		this.nowGift.setTime(time);
		if (time > lingquTime) {
			this.nowGift.setIsGet(true);
		}
		CharacterGiftpacksManager.getInstance().asynUpdateCharacterGiftpacks(myCharacterGiftpacksManger.getCharacter(), nowGift);

	}

	public CharacterGiftpacks getNowGift() {
		return nowGift;
	}

	public void setNowGift(CharacterGiftpacks nowGift) {
		this.nowGift = nowGift;
	}
}
