package net.snake.gamemodel.trade.processor;

import java.util.Collection;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.HorseContainerType;
import net.snake.consts.HorseStateConsts;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.catchpet.CharacterHorseController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.pet.logic.HorseContainer;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 13007, accessLimit = 100)
public class MoveHorseToStallProcessor extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int horseid = request.getInt();
		int copper = request.getInt();
		int ingot = request.getInt();
		if (copper < 0 || ingot < 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 694));
			return;
		}
		if (copper < 0 || ingot < 0 || copper > MaxLimit.BAG_COPPER_MAX || ingot > MaxLimit.INGOT_MAX) {

			return;
		}

		CharacterHorseController controller = character.getCharacterHorseController();
		HorseContainer srccontainer = controller.getBagHorseContainer();
		HorseContainer destcontainer = controller.getStallHorseContainer();
		Horse horse = srccontainer.getHorseByID(horseid);
		if (horse == null) {
			// 已经在显示中了不
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 889));
			return;
		}
		if (horse.rideing) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 957));
			return;
		}
		if (horse.getHorseModel().getBind() == 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 940));
			return;
		}
		Collection<CharacterGoods> collection = horse.getGoodsContainer().getGoodsList();
		if (collection.size() > 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 17002));
			return;
		}
		if (copper <= 0 && ingot <= 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 958));
			return;
		}
		if (destcontainer.getLeaveSpace() < 1) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 959));
			return;
		}
		if (srccontainer.getContainerType().equals(HorseContainerType.onBag)) {
			if (horse.getCharacterHorse().getStatus() == HorseStateConsts.SHOW) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 960));
				return;
			}
			if (horse.getCharacterHorse().getStatus() == HorseStateConsts.SHOW) {
				character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 961));
				return;
			}
		}
		horse.getCharacterHorse().setStallCopper(copper);
		horse.getCharacterHorse().setStallIngot(ingot);
		srccontainer.moveToContainer(destcontainer, horse);
	}

}
