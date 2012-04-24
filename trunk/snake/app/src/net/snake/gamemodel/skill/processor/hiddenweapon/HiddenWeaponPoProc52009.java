package net.snake.gamemodel.skill.processor.hiddenweapon;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;


@MsgCodeAnn(msgcode = 52009,accessLimit=500)
public class HiddenWeaponPoProc52009 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		if (Options.IsCrossServ) return;
		boolean shenqi = request.getByte() == 1? true  : false;
//		boolean freeTuPoActive = character.getEffectController().getBufferInBufferListByBufferId(BuffId.anqitupomianshuliandu) == null ? false : true;
//		CharacterHiddenWeapon characterHiddenWeapon = character.getCharacterHiddenWeaponController().getCharacterHiddenWeapon();
		//TODO 
//		if (characterHiddenWeapon != null && freeTuPoActive) {
//			int xiuGrade = characterHiddenWeapon.getHiddenWeapons().getXiuGrade();
//			int maxGrade = HiddenWeaponsManager.getInstance().getMaxGrade(characterHiddenWeapon.getGrade());
//			if (xiuGrade < maxGrade) {
//				HiddenWeapons oldhw = characterHiddenWeapon.getHiddenWeapons();
//				 HiddenWeapons hw = HiddenWeaponsManager.getInstance().getHiddenWeaponsByGrade(characterHiddenWeapon.getGrade(), maxGrade);
//				if (hw != null) {
//					characterHiddenWeapon.setXiuGrade(maxGrade);
//					 characterHiddenWeapon.setHiddenWeapons(hw);
//					 character.getCharacterHiddenWeaponController().po(shenqi);
//					if (maxGrade == characterHiddenWeapon.getXiuGrade()) {
//						 characterHiddenWeapon.setXiuGrade(xiuGrade);
//						 characterHiddenWeapon.setHiddenWeapons(oldhw);
//					}
//				}
//			} else {
//				character.getCharacterHiddenWeaponController().po(shenqi);
//			}
//		} else {
			character.getCharacterHiddenWeaponController().po(shenqi);
//		}
	}
}
