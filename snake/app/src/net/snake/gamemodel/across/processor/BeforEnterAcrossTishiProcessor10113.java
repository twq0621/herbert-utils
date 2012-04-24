package net.snake.gamemodel.across.processor;

import java.util.Date;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.DateFormatUtil;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.bean.CharacterAcross;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.across.persistence.CharacterAcrossDateCenterManager;
import net.snake.gamemodel.across.response.AcrossTishiMsg10114;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.faction.bean.FactionCharacter;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 返回跨服服务器状态
 * 
 * @author serv_dev 跨服服务器数量（byte）{lineID(byte)，lineIP(str),linePort（str）,lineName(str), 在线人数(int)} 【同时客户端打开跨服包括界面，请求跨服包裹数据】【失败则直接返回22224】
 */
@MsgCodeAnn(msgcode = 10113)
public class BeforEnterAcrossTishiProcessor10113 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {

		byte lineId = request.getByte();
		if (character.getGrade() < 60) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1302, 60 + ""));
			return;
		}
		if (!character.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1303));
			return;
		}
		AcrossServerDate as = AcrossServerDateManager.getInstance().getAcrossServerDateById(lineId);
		if (as.getEnable() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1309));
			return;
		}
		Horse horse = character.getMaxBagPinjieHorse();
		int maxKind = 0;
		if (horse != null) {
			if (maxKind < horse.getKind()) {
				maxKind = horse.getKind();
			}
		}
		Horse storagehorse = character.getMaxStoragePinjieHorse();
		if (storagehorse != null) {
			if (maxKind < storagehorse.getKind()) {
				maxKind = storagehorse.getKind();
			}
		}
		if (maxKind < 8) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1314));
			return;
		}
		if (!as.isTimeExpression()) {
			Date date = as.getNextKuafuzhan();
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1304, DateFormatUtil.getStringDate(date)));
			return;
		}
		if (!character.getMyCharacterAcrossIncomeManager().isInit()) {
			character.getMyCharacterAcrossIncomeManager().init(false);
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1312));
			return;
		}
		if (character.getMyFactionManager().isBangzhu()) {
			character.sendMsg(new AcrossTishiMsg10114(lineId, CommonUseNumber.byte1, as.getLoginservername()));
			return;
		}
		FactionCharacter fc = character.getMyFactionManager().getFactionController().getBangzhu();
		CharacterAcross ca = CharacterAcrossDateCenterManager.getInstance().getCharacterAcrossByCharacterId(fc.getCharacterId());
		if (ca != null) {
			if (ca.getShouyiState() == 1 && (ca.getKuafuDate().getTime() - System.currentTimeMillis()) < 60 * 60 * 1000 * 2) {
				if (lineId != ca.getAcrossServerId()) {
					AcrossServerDate bangzhuAs = AcrossServerDateManager.getInstance().getAcrossServerDateById(ca.getAcrossServerId());
					character.sendMsg(new AcrossTishiMsg10114(lineId, (byte) 2, bangzhuAs.getLoginservername()));
				} else {
					character.sendMsg(new AcrossTishiMsg10114(lineId, (byte) 2, ""));
				}
				return;
			}
			character.sendMsg(new AcrossTishiMsg10114(lineId, (byte) 2, ""));
			return;
		} else {
			character.sendMsg(new AcrossTishiMsg10114(lineId, (byte) 2, ""));
		}
	}
}
