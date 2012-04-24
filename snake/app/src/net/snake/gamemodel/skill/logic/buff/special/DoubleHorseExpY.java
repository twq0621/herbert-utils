package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 双倍坐骑经验(药品)
 * @author serv_dev
 *
 */
public class DoubleHorseExpY extends Buff {

	public DoubleHorseExpY(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
//		VisibleObject vo = controller.getVo();
//		if (vo instanceof Character) {
//			Character character = (Character)vo;//关闭时保存时间
//			CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
//			characterOnHoorConfig.setHorseExpdanTime(effect.getDuration());
//			character.getCharacterOnHoorController().saveOnHoorConfig();
//			character.sendMsg(new UpdateAfkTimeResponse50590(3,characterOnHoorConfig.getHorseExpdanTime()));
//			character.getCharacterOnHoorController().setDoubleZuoqiExpEffect(effect);
//			character.getCharacterOnHoorController().setDoubleZuoqiExpBeginTime(System.currentTimeMillis());
//			controller.setDoubleHorseExp(true);
//			character.getCharacterOnHoorController().setDoubleZuoqiExpState(OnHoorState.on);
//		}
		controller.setDoubleHorseExp(true);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
		
//		controller.setDoubleHorseExp(false);
//		VisibleObject vo = controller.getVo();
//		if (vo instanceof Character) {
//			Character character = (Character)vo;
//			character.getCharacterOnHoorController().setDoubleZuoqiExpState(OnHoorState.off);
//		}
//		effect.setDuration(0);
		controller.setDoubleHorseExp(false);
//		VisibleObject vo = controller.getVo();
//		if (leaveCondition(System.currentTimeMillis())) {
//			if (vo instanceof Character) {
//				Character character = (Character)vo;
//	//			character.getCharacterOnHoorController().setDoubleZhenqiState(OnHoorState.off);
//				CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
//				if (characterOnHoorConfig.getHorseExpdan()) {
//					//自动续用
//					if (character.getCharacterGoodController().isEnoughGoods(ClientConfig.DoubleZuoqiJingYan, 1)){
//						character.getCharacterGoodController().deleteGoodsFromBag(ClientConfig.DoubleZuoqiJingYan, 1);
//						effect.setDuration(effect.getEffect().getDuration());
//						character.getEffectController().addEffect(effect);
//					}
//				}
//			}
//		}
		
		return true;
	}

}
