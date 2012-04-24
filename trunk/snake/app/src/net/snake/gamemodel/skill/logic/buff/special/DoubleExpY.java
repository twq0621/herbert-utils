package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.bean.SceneObj;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * 双倍经验(药品)
 * @author serv_dev
 *
 */
public class DoubleExpY extends Buff {

	public DoubleExpY(EffectInfo effect) {
		super(effect);
	}

	@Override
	public boolean enter(EffectController controller) {
//		VisibleObject vo = controller.getVo();
//		if (vo instanceof Character) {
//			Character character = (Character)vo;//关闭时保存时间
//			CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
//			characterOnHoorConfig.setExpdanTime(effect.getDuration());
//			character.sendMsg(new UpdateAfkTimeResponse50590(2,characterOnHoorConfig.getExpdanTime()));
//			character.getCharacterOnHoorController().saveOnHoorConfig();
//			character.getCharacterOnHoorController().setDoubleExpEffect(effect);
//			character.getCharacterOnHoorController().setDoubleExpBeginTime(System.currentTimeMillis());
//			controller.setDoubleExp(true);
//			character.getCharacterOnHoorController().setDoubleExpState(OnHoorState.on);
//		}
		
		
		controller.setDoubleExp(true);
		return true;
	}

	@Override
	public boolean leave(EffectController controller) {
//		VisibleObject vo = controller.getVo();
//		controller.setDoubleExp(false);
//		if (vo instanceof Character) {
//			Character character = (Character)vo;//关闭时保存时间
//			character.getCharacterOnHoorController().setDoubleExpState(OnHoorState.off);
//		}
//		effect.setDuration(0);
		controller.setDoubleExp(false);
//		if (leaveCondition(System.currentTimeMillis())) {
//			VisibleObject vo = controller.getVo();
//			if (vo instanceof Character) {
//				Character character = (Character)vo;
//	//			character.getCharacterOnHoorController().setDoubleZhenqiState(OnHoorState.off);
//				CharacterOnHoorConfig characterOnHoorConfig = character.getCharacterOnHoorController().getCharacterOnHoorConfig();
//				if (characterOnHoorConfig.getExpdan()) {
//					//自动续用
//					if (character.getCharacterGoodController().isEnoughGoods(ClientConfig.DoubleJingYan, 1)){
//						character.getCharacterGoodController().deleteGoodsFromBag(ClientConfig.DoubleJingYan, 1);
//						effect.setDuration(effect.getEffect().getDuration());
//						character.getEffectController().addEffect(effect);
//					}
//				}
//			}
//		}
		
		return true;
	}
	
	
	@Override
	public boolean init(EffectController controller) {
		if (!(controller.getVo().getSceneObjType()==SceneObj.SceneObjType_Hero)) return false;
		
		Hero character = (Hero)(controller.getVo());
		if (character.getDoubExpNum() > 2) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT,20085));
			return false;
		}
		return true;
	}
}
