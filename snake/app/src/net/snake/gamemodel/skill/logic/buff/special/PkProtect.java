package net.snake.gamemodel.skill.logic.buff.special;

import net.snake.ai.fight.controller.EffectController;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.logic.buff.Buff;


/**
 * PK保护的buff
 * @author serv_dev
 *
 */
public class PkProtect extends Buff {

	public PkProtect(EffectInfo effectInfo) {
		super(effectInfo);
	}

	@Override
	public boolean enter(EffectController controller) {
		controller.setIspkProtect(true);
		//SENDMSG
		controller.setPkProtectEffect(effect);
		return true;
	}
	

	@Override
	public boolean leave(EffectController controller) {
		controller.setIspkProtect(false);
		
		controller.setPkProtectEffect(null);
		
		if (leaveCondition(System.currentTimeMillis())) {
			//SENDMSG  倒计时结束时和平保护BUFF消失，返回系统消息提示： 和平保护状态已经过期，在江湖中行走时注意小心防备“
 controller.getVo().sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,764));
		}
		return true;
	}

}
