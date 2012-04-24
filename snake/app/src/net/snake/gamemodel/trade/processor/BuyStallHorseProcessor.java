package net.snake.gamemodel.trade.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CopperAction;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.gamemodel.trade.response.StallDetailInfo13012;
import net.snake.gamemodel.trade.response.StallSalelog13018;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 13013, accessLimit = 100)
public class BuyStallHorseProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		character.getMyTradeController().cancelTrade();
		int stallOwnerid = request.getInt();// 摊位主人
		if (stallOwnerid == character.getId()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 948));
			return;
		}
		int horseid = request.getInt();// 索引id
		Hero stallowner = GameServer.vlineServerManager.getCharacterById(stallOwnerid);
		if (stallowner == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 949));
			return;
		}
		// 为了安全,只从容器里取
		HorseContainer stall = stallowner.getCharacterHorseController().getStallHorseContainer();
		Horse horse = stall.getHorseByID(horseid);
		if (horse == null) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 953));
			// 刷新摊位详情
			character.sendMsg(new StallDetailInfo13012(stallowner));
			return;
		}
		int oldCopper = request.getInt();
		int oldYuanbao = request.getInt();
		if (oldCopper < horse.getCharacterHorse().getStallCopper() || oldYuanbao < horse.getCharacterHorse().getStallIngot()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1151));
			character.sendMsg(new StallDetailInfo13012(stallowner));
			return;
		}
		if (character.getCopper() < horse.getCharacterHorse().getStallCopper() || character.getAccount().getYuanbao() < horse.getCharacterHorse().getStallIngot()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 951));
			return;
		}
		HorseContainer mybag = character.getCharacterHorseController().getBagHorseContainer();
		if (mybag.getLeaveSpace() < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 954));
			return;
		}
		stallowner.getCharacterHorseController().getStallHorseContainer().releaseHorse(horse);
		character.getCharacterHorseController().getBagHorseContainer().receiveOthersHorse(horse);
		if (horse.getCharacterHorse().getStallCopper() > 0) {
			CharacterPropertyManager.changeCopper(stallowner, horse.getCharacterHorse().getStallCopper(), CopperAction.ADD_DEAL);
			CharacterPropertyManager.changeCopper(character, -horse.getCharacterHorse().getStallCopper(), CopperAction.CONSUME);
		}
		if (horse.getCharacterHorse().getStallIngot() > 0) {
			stallowner.getAccount().getAccountMonneyManager().changeTradeAddYuanbao(stallowner, horse.getCharacterHorse().getStallIngot());
			character.getAccount().getAccountMonneyManager().changeTradeReduceYuanbao(character, horse.getCharacterHorse().getStallIngot());
//			character.getAccount().getAccountMonneyLogManager().logStallTradeHourseYuanbaoLog(character, horse, stallowner);
		}
		// 刷新摊位详情
		String[] salemsg = { horse.getHorseModel().getNameI18n(), horse.getCharacterHorse().getStallCopper() + "", +horse.getCharacterHorse().getStallIngot() + "" };
		String buymsg = horse.getHorseModel().getNameI18n();
		stallowner.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT + "," + TipMsg.MSGPOSITION_RIGHT, 1040, salemsg));
		character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_RIGHT + "," + TipMsg.MSGPOSITION_RIGHT, 1041, buymsg));
		// 刷新摊位详情
		character.sendMsg(new StallDetailInfo13012(stallowner));
		character.sendMsg(new StallDetailInfo13012(stallowner));
		stallowner.sendMsg(new StallSalelog13018(character, stallowner, horse));
	}

}
