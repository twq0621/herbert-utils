package net.snake.gamemodel.chest.processor;

/**
 * 

 * 宝箱加成数据显示
 */
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.chest.response.ChestResponse60104;
import net.snake.gamemodel.chest.response.ChestSquirrelResponse60100;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 60111)
public class ChestTreasureMap_SquirrelShowProcess60111 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		// 查看配包里边有没有宝藏地图
		int typeid = request.getInt();// 宝藏类别 或者松子类别
		CharacterGoods cGoods = character.getCharacterGoodController().getBagGoodsContiner().getFirstGoodsByModelid(typeid);
		if (cGoods == null) {
			character.sendMsg(new ChestResponse60104(CommonUseNumber.byte0, typeid));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 837));
			return;
		}
		if (cGoods.getGoodModel().getKind() != 17 && cGoods.getGoodModel().getKind() != 20 && cGoods.getGoodModel().getKind() != 26) {
			character.sendMsg(new ChestResponse60104(CommonUseNumber.byte0, typeid));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 838));
			return;
		}
		if (cGoods.getGoodModel().getKind() == 17) {
			cGoods.getUseGoodAction().useGoodDoSomething(character, cGoods.getGoodmodelId(), cGoods.getPosition(), character.getSceneRef().getUseItemListeners());
			return;
		}
		// if (cGoods.getGoodModel().getKind() == 26) { // 金松果相关
		// cGoods.getUseGoodAction().useGoodDoSomething(character, cGoods.getGoodmodelId(),
		// cGoods.getPosition());
		// return;
		// }
		if (character.getMyChestManager().getChestCount().getChesttypeExchangeCount() == -1) {// 金松果重置验证
			if (!character.getMyChestManager().getYanZhengJinSongGuo()) {
				return;
			}
		}

		// 验证有效期

		if (cGoods.isExpired()) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 877));
			return;
		}

		if (character.getGrade() < cGoods.getGoodModel().getLimitGrade().intValue()) {
			//
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1021, cGoods.getGoodModel().getLimitGrade().intValue() + ""));
			return;
		}
		Short beibaoindex = character.getCharacterGoodController().getBagGoodsContiner().findFirstIdleGirdPosition();
		if (beibaoindex == 0) {
			character.sendMsg(new ChestResponse60104(CommonUseNumber.byte0, cGoods.getGoodmodelId()));
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return;
		}
		character.sendMsg(new ChestSquirrelResponse60100(character, cGoods.getGoodModel()));

	}

}
