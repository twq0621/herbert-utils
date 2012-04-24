package net.snake.gamemodel.across.processor;

import java.util.Date;

import net.snake.AcrossServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.DateFormatUtil;
import net.snake.commons.ZipUtil;
import net.snake.ctsnet.CtsConnectSessionMananger;
import net.snake.gamemodel.across.bean.AcrossServerDate;
import net.snake.gamemodel.across.persistence.AcrossServerDateManager;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gmtool.net.Msg;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;



import datatransport.util.CharacterDateIoUtil;
import org.apache.log4j.Logger;
/**
 * 获得切线令牌 10109
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 10115)
public class GetAcrossTokenProcessor10115 extends CharacterMsgProcessor implements IThreadProcessor {
	private static Logger logger = Logger.getLogger(GetAcrossTokenProcessor10115.class);

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		int serverid = request.getByte();
		AcrossServerDate as = AcrossServerDateManager.getInstance().getAcrossServerDateById(serverid);
		if (as == character.getVlineserver()) {
			return;// 分线相同，直接返回
		}
		// if (character.getSceneRef() instanceof GongchengTsMap) {
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 14534));
		// return;
		// }
		// if (character.getSceneRef() instanceof LunjiantaiTsMap) {
		// character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1170));
		// return;
		// }
		if (character.getGrade() < 60) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1302, 60 + ""));
			return;
		}
		if (!character.getMyFactionManager().isFaction()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1303));
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
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1307));
			return;
		}
		if (character.getAcrossLock().isAcrossLock()) {
			return;
		}
		character.getMyCharacterAcrossIncomeManager().clearShenglvInfo(as);
		character.getAcrossLock().lockCharacterAction(as.getLockTime() * 1000, character);
		Msg msg = CharacterDateIoUtil.characterToIo(character, as.getServerId());
		if (msg == null) {
			character.getAcrossLock().clearCharacterLockAction(character);
			return;
		}
		long startTime = System.currentTimeMillis();
		logger.info("压缩前数据大小为:" + msg.getContent().length);
		msg.setContent(ZipUtil.zip(msg.getContent()));
		logger.info("压缩后数据大小为:" + msg.getContent().length + "  压缩所用时间为:" + (System.currentTimeMillis() - startTime));
		CtsConnectSessionMananger.getInstance().sendMsgToServer(as.getLoginServerIp(), AcrossServer.acrossPort, msg);
	}
}
