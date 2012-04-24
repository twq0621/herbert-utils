package net.snake.gamemodel.hero.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyAdditionControllerImp;
import net.snake.gamemodel.hero.logic.PropertyEntity;
import net.snake.netio.ServerResponse;

/**
 * 人物新属性 Copyright (c) 2011 Kingnet
 * 
 * @author serv_dev
 */
public class CharacterAttributesMsg49992 extends ServerResponse {
	public CharacterAttributesMsg49992(Hero character) {
		setMsgCode(49992);
		CharacterPropertyAdditionControllerImp propertyController = (CharacterPropertyAdditionControllerImp) character.getPropertyAdditionController();
		writeInt(character.getId());
		PropertyEntity zhuangbei = propertyController.getZhuangbei();
		PropertyEntity bow = propertyController.getBow();
		PropertyEntity lianti = propertyController.getLianTi();
		writeInt(zhuangbei.getGjl() + bow.getGjl());
		writeInt(lianti.getGjl());
		writeInt(zhuangbei.getFtsh());
		writeInt(lianti.getFtsh());
		writeInt(zhuangbei.getHsfy() + bow.getHsfy());
		writeInt(lianti.getHsfy());
		writeInt(zhuangbei.getShjm());
		writeInt(lianti.getShjm());
		writeInt(character.getCharacterHiddenWeaponController().getCharacterHiddenWeapon() == null ? 0 : Math.round(character.getCharacterHiddenWeaponController()
				.getCharacterHiddenWeapon().getHiddenWeapons().getTribLv() / 100f));
		writeInt(Math.round(lianti.getAqJv() / 100f));
	}
}
