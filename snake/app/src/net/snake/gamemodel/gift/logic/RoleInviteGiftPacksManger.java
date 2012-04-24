package net.snake.gamemodel.gift.logic;

import java.util.ArrayList;
import java.util.List;

import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.CharacterGiftpacksManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.gift.response.GiftPacksInviteListMsg50732;
import net.snake.gamemodel.gift.response.GiftPacksInviteMsg50734;
import net.snake.gamemodel.gift.response.GiftPacksUpdate50798;
import net.snake.gamemodel.gift.response.InviteCharacterListMsg50736;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

/**
 * 登入有礼 按周做统计
 * 
 * @author serv_dev
 * 
 */
public class RoleInviteGiftPacksManger {
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	private int inviteCount = 1;

	public RoleInviteGiftPacksManger(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public void init() {
		inviteCount = 9;
	}

	public int getInviteCount() {
		return inviteCount;
	}

	public void setInviteCount(int inviteCount) {
		this.inviteCount = inviteCount;
	}

	/**
	 * 见面有礼领取时间
	 * 
	 * @return
	 */
	public boolean openGiftPacks(int giftId) {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		CharacterGiftpacks nowGift = myCharacterGiftpacksManger.getCharacterGiftpacksByGiftId(giftId);
		if (nowGift != null) {
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 661));
			return false;
		}
		GiftPacks gp = GiftPacksManager.getInstance().getGiftPacksById(giftId);
		if (gp == null || gp.getType() != GiftPacks.inviteType) {
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 662));
			return false;
		}
		if (gp.getInviteLimit() > inviteCount) {
			//
			myCharacterGiftpacksManger.sendMsgToClient(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 987, inviteCount + ""));
			return false;
		}
		if (gp.getCopper() + character.getCopper() > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 658));
			return false;
		}
		if (gp.getLijin() + character.getJiaozi() > MaxLimit.LIJIN_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 659));
			return false;
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForRemoveAndAddGoods(null, gp.getGoodlist(character.getPopsinger()))) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 660));
			return false;
		}
		CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
		CharacterPropertyManager.changeLijin(character, gp.getLijin());
		character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(null, gp.getGoodlist(character.getPopsinger()));
		addGiftPacksInvite(gp);
		character.sendMsg(new GiftPacksInviteMsg50734(nowGift));
		return true;
	}

	private void addGiftPacksInvite(GiftPacks gp) {
		CharacterGiftpacks nowGift = new CharacterGiftpacks();
		Hero character = myCharacterGiftpacksManger.getCharacter();
		nowGift.setCharacterId(character.getId());
		nowGift.setGiftPacksId(gp.getId());
		nowGift.setIsGet(true);
		nowGift.setIsOwner(true);
		CharacterGiftpacksManager.getInstance().asynInsertCharacterGiftpacks(character, nowGift);
	}

	public void sendGiftList() {
		List<GiftPacks> gplist = GiftPacksManager.getInstance().getInviteGiftList();
		myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksInviteListMsg50732(this, gplist, myCharacterGiftpacksManger.getCharacter()));
	}

	public void sendUpdateInviteCharacterList() {
		List<CharacterCacheEntry> list = new ArrayList<CharacterCacheEntry>();
		myCharacterGiftpacksManger.sendMsgToClient(new InviteCharacterListMsg50736(list));
	}

	/**
	 * 更新调用此方法
	 */
	public void update() {

	}

	public void downline() {

	}

	public void sendHaveGetGiftPacksMsg() {
		List<GiftPacks> list = GiftPacksManager.getInstance().getInviteGiftList();
		for (GiftPacks gp : list) {
			if (gp.getInviteLimit() < inviteCount) {
				CharacterGiftpacks cgp = myCharacterGiftpacksManger.getCharacterGiftpacksByGiftId(gp.getId());
				if (cgp == null) {
					myCharacterGiftpacksManger.sendMsgToClient(new GiftPacksUpdate50798(GiftPacks.inviteType));
				}
			}
		}
	}
}
