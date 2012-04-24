package net.snake.gamemodel.gift.logic;

import java.util.ArrayList;
import java.util.List;

import net.snake.commons.message.SimpleResponse;
import net.snake.consts.CopperAction;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.persistence.CharacterGiftpacksManager;
import net.snake.gamemodel.gift.persistence.GiftPacksManager;
import net.snake.gamemodel.gift.response.GiftPacksEachLevelResponse;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;

public class RoleEachLevelGiftPacksManager {
	private MyCharacterGiftpacksManger myCharacterGiftpacksManger;
	private List<CharacterGiftpacks> eachLevelGiftPacks = new ArrayList<CharacterGiftpacks>();// 玩家已经得到的每级惊喜礼包

	public RoleEachLevelGiftPacksManager(MyCharacterGiftpacksManger myCharacterGiftpacksManger) {
		this.myCharacterGiftpacksManger = myCharacterGiftpacksManger;
	}

	public void setNowGift(CharacterGiftpacks nowGift) {
		eachLevelGiftPacks.add(nowGift);
	}

	public List<CharacterGiftpacks> getEachLevelGiftPacks() {
		return eachLevelGiftPacks;
	}

	/**
	 * 添加打开升级礼包
	 */
	public void addGradeGiftPacks() {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		GiftPacks nextGp = GiftPacksManager.getInstance().getEachLevelByGrade(character.getGrade());
		if (nextGp == null) {
			return;
		}
		CharacterGiftpacks nowGift = new CharacterGiftpacks();
		nowGift.setCharacterId(character.getId());
		nowGift.setGiftPacksId(nextGp.getId());
		nowGift.setGp(nextGp);
		nowGift.setIsGet(true);
		nowGift.setIsOwner(false);
		nowGift.setTime(0l);
		eachLevelGiftPacks.add(nowGift);
		myCharacterGiftpacksManger.addCharacterGiftpacks(nowGift);
		CharacterGiftpacksManager.getInstance().asynInsertCharacterGiftpacks(character, nowGift);
		character.sendMsg(new GiftPacksEachLevelResponse(nowGift,character.getPopsinger()));
	}

	public void getGradeGiftPacks(int giftPacksId) {
		Hero character = myCharacterGiftpacksManger.getCharacter();
		CharacterGiftpacks cgp = myCharacterGiftpacksManger.getCharacterGiftpacksByGiftId(giftPacksId);
		if(cgp==null){
			return;
		}
		GiftPacks gp = cgp.getGp();
		if (gp.getCopper() + character.getCopper() > MaxLimit.BAG_COPPER_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 658));
			return;
		}
		if (gp.getLijin() + character.getJiaozi() > MaxLimit.LIJIN_MAX) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 659));
			return;
		}
		if (!character.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForRemoveAndAddGoods(null, gp.getGoodlist(character.getPopsinger()))) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 660));
			return;
		}
		CharacterPropertyManager.changeCopper(character, gp.getCopper(), CopperAction.ADD_OTHER);
		CharacterPropertyManager.changeLijin(character, gp.getLijin());
		boolean tf = character.getCharacterGoodController().getBagGoodsContiner().removeAndAddGoods(null, gp.getGoodlist(character.getPopsinger()));
		if (tf) {
			character.sendMsg(SimpleResponse.intMsg(60202, gp.getId()));
			eachLevelGiftPacks.remove(cgp);
			myCharacterGiftpacksManger.removeCharacterGiftpacks(cgp);
			CharacterGiftpacksManager.getInstance().asynDeleteCharacterGiftpacks(character, cgp);
		}
		
	}
}
