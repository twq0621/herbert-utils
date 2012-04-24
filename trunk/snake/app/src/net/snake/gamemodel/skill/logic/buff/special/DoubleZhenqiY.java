package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;

/**
 * 双倍真气(药品)
 * @author serv_dev
 *
 */
public class DoubleZhenqiY extends Buff {

	
	public DoubleZhenqiY(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
//		VisibleObject vo = controller.getVo();
//		if (vo instanceof Character) {
//			Character character = (Character)vo;//关闭时保存时间
//			CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
//			characterOnHoorConfig.setZhenqidanTime(effect.getDuration());
//			character.getCharacterOnHoorController().saveOnHoorConfig();
//			character.sendMsg(new UpdateAfkTimeResponse50590(1,characterOnHoorConfig.getZhenqidanTime()));
//			character.getCharacterOnHoorController().setDoubleZhenqiEffect(effect);
//			character.getCharacterOnHoorController().setDoubleZhenqiBeginTime(System.currentTimeMillis());
//			controller.setDoubleZhenqi(true);
//			character.getCharacterOnHoorController().setDoubleZhenqiState(OnHoorState.on);
//		}
		
		controller.setDoubleZhenqi(true);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
			
		controller.setDoubleZhenqi(false);
//		VisibleObject vo = controller.getVo();
//		if (leaveCondition(System.currentTimeMillis())) {
//			if (vo instanceof Character) {
//				Character character = (Character)vo;
//	//			character.getCharacterOnHoorController().setDoubleZhenqiState(OnHoorState.off);
//				CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
//				if (characterOnHoorConfig.getZhenqidan()) {
//					//自动续用
//					if (character.getCharacterGoodController().isEnoughGoods(ClientConfig.DoubleZhenqi, 1)){
//						character.getCharacterGoodController().deleteGoodsFromBag(ClientConfig.DoubleZhenqi, 1);
//						effect.setDuration(effect.getEffect().getDuration());
//						character.getEffectController().addEffect(effect);
//					}
//				}
//			}
//		}
		
		return true;
	}

}
