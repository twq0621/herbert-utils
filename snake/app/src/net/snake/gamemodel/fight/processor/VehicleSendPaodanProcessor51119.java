package net.snake.gamemodel.fight.processor;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import net.snake.ai.formula.DistanceFormula;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.ClientConfig;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.npc.bean.Npc;
import net.snake.gamemodel.npc.persistence.NpcManager;
import net.snake.netio.message.RequestMsg;

/**
 * 发射炮弹
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 51119)
public class VehicleSendPaodanProcessor51119 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int npcId = request.getInt();
		int vehicleId = request.getInt();
		short x = request.getShort();
		short y = request.getShort();
		NpcManager npcManager = NpcManager.getInstance();
		Npc npc = npcManager.get(npcId);
		if (vehicleId < 500 || vehicleId > 600) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		if (npc == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 897));
			return;
		}
		int npcSceneId = npc.getSceneid();
		if (!npc.getFunctions().contains(vehicleId + "")) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14514));
			return;
		}
		if (character.getScene() != npcSceneId) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14515));
			return;
		}
		if (DistanceFormula.getDistanceRound(npc.getX(), npc.getY(), character.getX(), character.getY()) > ClientConfig.VEHICLE_SEBDER_LANG) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14516));
			return;
		}
		// if(!character.getMyFactionCityZhengDuoManager().isHaveTodayGongchengInfo()){
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14520));
		// return;
		// }
		// int lineid=character.getVlineserver().getLineid();
		// if(lineid!=FactionCityManager.gongchengLine){
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,
		// 14517,FactionCityManager.gongchengLine));
		// return;
		// }
		// if(!GongchengTsMap.isGongchenging){
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14520));
		// return;
		// }
		character.getMyCharacterVehicleManager().startSendShells(character.getSceneRef(), vehicleId, x, y);

	}

	public int getTodayHourse() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
		return hours;
	}
}
