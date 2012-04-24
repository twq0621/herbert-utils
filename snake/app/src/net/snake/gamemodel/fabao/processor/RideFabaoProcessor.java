package net.snake.gamemodel.fabao.processor;

import org.apache.log4j.Logger;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.VisibleObjectState;
import net.snake.consts.Position;
import net.snake.consts.TakeMethod;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fabao.response.RideFabaoResponse;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;

/**
 * 法宝(站上或者收起) 11101 标志byte(0-收起 1-站上)
 * 
 * @author jack
 * 
 */
@MsgCodeAnn(msgcode = 11101)
public class RideFabaoProcessor extends CharacterMsgProcessor {
	private static Logger logger = Logger.getLogger(RideFabaoProcessor.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		CharacterGoods fabaogoods = character.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);

		if (character.getObjectState() == VisibleObjectState.Dazuo) {
			character.setObjectState(VisibleObjectState.Idle);
		}
		if(character.getObjectState() ==VisibleObjectState.Attack){
			return;
		}
		if (character.isZeroHp()) {// 血量等于0就不允许攻击了。也就是玩家已经死掉了
			return;
		}
		if (character.getEffectController().isImmb()) {// 被定身
			return;
		}
		if (character.getEffectController().isSleep()) {// 被沉睡
			return;
		}
		if (fabaogoods == null) {
			logger.warn("dont have fabao");
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60046));
			return;
		}
		byte b = request.getByte();
		fabaogoods.setIsUse(b);
		character.getCharacterGoodController().updateCharacterGoods(fabaogoods);
		TakeMethod onoff = TakeMethod.on;
		if (b == 0) {
			onoff = TakeMethod.off;
		}
		if(fabaogoods.getCurrDurability()==null || fabaogoods.getCurrDurability()==0){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60066));
			return;
		}
		character.getEquipmentController().changeProperty(character, fabaogoods, onoff);
		character.getEyeShotManager().sendMsg(new RideFabaoResponse(character.getId(), b, fabaogoods.getGoodmodelId()));
	}
}
