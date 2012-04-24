package net.snake.gamemodel.fight.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.fight.bean.GongchengVehicle;
import net.snake.gamemodel.fight.persistence.GongchengVehicleManager;
import net.snake.gamemodel.fight.response.VehicleMsg51118;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 炮弹购买操作
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 51117)
public class VehicleNpcBuyProcessor51117 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int npcId = request.getInt();
		if(npcId<0){
			return;
		}
		int vehicleId = request.getInt();
		int count = request.getInt();
		if (count < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		if (count > 1000) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16505, 1000 + ""));
			return;
		}
		GongchengVehicle vehicle = GongchengVehicleManager.getInstance().getGongchengVehicleByVehicleId(vehicleId);
		if (vehicle == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		if (vehicle.getCopper() * count < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 16506));
			return;
		}
		if (character.getCopper() < vehicle.getCopper() * count) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14518));
			return;
		}
		CharacterPropertyManager.changeCopper(character, -vehicle.getCopper() * count, CopperAction.CONSUME);
		character.getMyCharacterVehicleManager().addVehicle(vehicleId, count);
		character.sendMsg(new VehicleMsg51118());
	}

}
