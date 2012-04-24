package net.snake.gamemodel.skill.response.hiddenweapon;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.skill.bean.CharacterHiddenWeapon;
import net.snake.gamemodel.skill.persistence.HiddenWeaponsManager;
import net.snake.netio.ServerResponse;

public class QueryOtherCharacterHiddenWeaponInfoMsg52018 extends ServerResponse {

	public QueryOtherCharacterHiddenWeaponInfoMsg52018(CharacterHiddenWeapon characterHiddenWeapon, Hero othercharacter) {
		setMsgCode(52018);
		if (characterHiddenWeapon == null) {
			writeBoolean(false);
		} else {
			writeBoolean(true);
			writeInt(characterHiddenWeapon.getHiddenWeapons().getId());
			writeInt(characterHiddenWeapon.getNowMastery());
			writeInt(characterHiddenWeapon.getXiuGrade());
			writeInt(HiddenWeaponsManager.getInstance().getMaxGrade(characterHiddenWeapon.getGrade()));
			writeInt(characterHiddenWeapon.getLuckValue());
			writeBoolean(characterHiddenWeapon.getIsUse());
			writeBoolean(characterHiddenWeapon.getIsOpenHiddenProps());
			writeInt(othercharacter.getPropertyAdditionController().getExtraAttack());
		}
	}
}
