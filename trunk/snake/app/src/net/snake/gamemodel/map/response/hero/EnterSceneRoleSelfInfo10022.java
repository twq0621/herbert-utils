package net.snake.gamemodel.map.response.hero;

import net.snake.consts.Position;
import net.snake.gamemodel.equipment.response.CharacterPropertyResponse10108;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.CharacterOnHoorConfig;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.netio.ServerResponse;
import net.snake.shell.Options;

/**
 * 
 * 
 */
public class EnterSceneRoleSelfInfo10022 extends ServerResponse {

	public EnterSceneRoleSelfInfo10022(Hero character, short bornx, short borny) {
		setMsgCode(10022);
		try {
			writeInt(character.getId());
			writeByte(character.getAccount().getGm());
			// =================================================================
			writeUTF(character.getViewName());
			writeByte(character.getHeadimg());
			writeByte(character.getPopsinger());
			if (Options.IsCrossServ) {
				writeByte(1);
				writeInt(character.getMyFactionManager().getFactionId());
				writeUTF(character.getMyFactionManager().getFactionName());
				writeByte(character.getMyFactionManager().getFactionPosition());
				writeByte(1); // 帮旗等级
				writeUTF("");// 帮旗名字
				writeByte(0);
				writeUTF("");
			} else if (character.getMyFactionManager().isFaction()) {
				FactionController factionC = character.getMyFactionManager().getFactionController();
				FactionCharacter fc = factionC.getFactionCharacterByCharacterId(character.getId());
				if (fc != null) {
					Faction faction = factionC.getFaction();
					writeByte(1);
					writeInt(faction.getId());
					writeUTF(faction.getViewName());
					writeByte(fc.getPosition());
					writeByte(faction.getFactionFlag().getfGrade());
					writeUTF(faction.getBangqiName());
					writeByte(faction.getIcoId());
					writeUTF(faction.getIcoStr());
				} else {
					writeByte(0);
				}
			} else {
				writeByte(0);
			}
			writeInt(character.getNowAppellationid());
			writeByte((byte) character.getFightController().getPkModel().intValue());
			CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
			writeByte(characterOnHoorConfig.getRevertHp().byteValue());
			writeByte(characterOnHoorConfig.getRevertMp().byteValue());
			writeByte(characterOnHoorConfig.getRevertSp().byteValue());
			CharacterEnterEyeShot10028.SendCharacterShowInfo(character, this);
			CharacterPropertyResponse10108.writeRankTitle(this, character);
			if (!character.getMyFriendManager().getRoleWedingManager().isWedding()) {
				writeInt(0);
			} else {
				if (Options.IsCrossServ) {
					String peiou = character.getMyFriendManager().getRoleWedingManager().getWedderName();
					WeddingRing ring = character.getMyFriendManager().getRoleWedingManager().getFuqiWeddingRing();
					writeInt(1);
					writeUTF(peiou);
					if (character.isMale()) {
						writeInt(ring.getMaleGood());
					} else {
						writeInt(ring.getFemaleGood());
					}

				} else {
					Couples couples = character.getMyFriendManager().getRoleWedingManager().getFuqi().getCouples();
					if (character.isMale()) {
						writeInt(couples.getFemaleId());
						writeUTF(couples.getFemaleCce().getViewName());
						writeInt(couples.getWr().getMaleGood());
					} else {
						writeInt(couples.getMaleId());
						writeUTF(couples.getMaleCce().getViewName());
						writeInt(couples.getWr().getFemaleGood());
					}
				}
			}

			CharacterHiddenWeapon characterHiddenWeapon = character.getCharacterHiddenWeaponController().getCharacterHiddenWeapon();
			writeBoolean(characterHiddenWeapon == null ? false : characterHiddenWeapon.getIsOpenHiddenProps());
			writeByte(character.getBowController().getBow() == null ? 0 : character.getBowController().getBow().getModel().getId());
			CharacterGoods wuqigoods = character.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_WUQI);
			if (wuqigoods != null) {
				writeByte(wuqigoods.getPin());
				writeByte(wuqigoods.getJie());
			} else {
				writeByte(0);
				writeByte(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
