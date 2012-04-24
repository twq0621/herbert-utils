package net.snake.gamemodel.gift.response;

import java.util.List;

import net.snake.gamemodel.gift.bean.CharacterGiftpacks;
import net.snake.gamemodel.gift.bean.GiftPacks;
import net.snake.gamemodel.gift.logic.MyCharacterGiftpacksManger;
import net.snake.gamemodel.gift.logic.RoleInviteGiftPacksManger;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.ServerResponse;

public class GiftPacksInviteListMsg50732 extends ServerResponse {

	public GiftPacksInviteListMsg50732(RoleInviteGiftPacksManger roleInviteGiftManger, List<GiftPacks> list, Hero character) {
		this.setMsgCode(50732);
		MyCharacterGiftpacksManger myCharacterGiftpacksManger = character.getMyCharacterGiftpacksManger();
		int inviteCount = roleInviteGiftManger.getInviteCount();
		this.writeShort(inviteCount);
		this.writeByte(list.size());
		for (GiftPacks gp : list) {
			this.writeInt(gp.getId());
			this.writeShort(gp.getInviteLimit());
			this.writeInt(gp.getCopper());
			this.writeInt(gp.getLijin());
			List<CharacterGoods> goodlist = gp.getGoodlist(character.getPopsinger());
			this.writeByte(goodlist.size());
			for (CharacterGoods cg : goodlist) {
				this.writeInt(cg.getGoodmodelId());
				this.writeInt(cg.getCount());
			}
			if (gp.getGradeLimit() <= inviteCount) {
				CharacterGiftpacks cgp = myCharacterGiftpacksManger.getCharacterGiftpacksByGiftId(gp.getId());
				if (cgp == null) {
					this.writeByte(0);
				} else {
					this.writeByte(1);
				}
			} else if (gp.getGradeLimit() > inviteCount) {
				this.writeByte(0);
			}
		}

	}
}
