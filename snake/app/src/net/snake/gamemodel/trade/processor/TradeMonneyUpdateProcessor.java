package net.snake.gamemodel.trade.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.account.bean.Account;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.trade.logic.IMyTradeController;
import net.snake.gamemodel.trade.response.TradeMonneyMsg10844;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10843 A|B-S: “金钱放入交易栏“ 金钱类别（byte）1铜币/2元宝,金钱数（int）
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 10843, accessLimit = 200)
public class TradeMonneyUpdateProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte type = request.getByte();
		int count = request.getInt();
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		if (count < 0) {
			return;
		}
		IMyTradeController mtc = character.getMyTradeController();
		if (!mtc.isGoodOperate()) {
			return;
		}
		if (type == 1) {
			if (character.getCopper() + mtc.getCopper() - count < 0) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 828));
				return;
			}
			CharacterPropertyManager.changeCopper(character, mtc.getCopper() - count, CopperAction.DEPOSIT);
			mtc.setCopper(count);
			TradeMonneyMsg10844 monneyMsg = new TradeMonneyMsg10844(mtc);
			character.sendMsg(monneyMsg);
			mtc.getTradeCharacter().sendMsg(monneyMsg);
		} else if (type == 2) {
			Account account = character.getAccount();
			if (account.getYuanbao() + mtc.getYuanbao() - count < 0) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 943));
				return;
			}
			account.getAccountMonneyManager().changeRoleTradeYuanbao(character, count);
			TradeMonneyMsg10844 monneyMsg = new TradeMonneyMsg10844(mtc);
			character.sendMsg(monneyMsg);
			mtc.getTradeCharacter().sendMsg(monneyMsg);
		}

	}

}
