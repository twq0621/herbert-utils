package net.snake.gamemodel.chest.processor;

/**
 * 

 * 请求宝箱友好度
 */
import java.util.ArrayList;
import java.util.Collection;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.chest.bean.ChestCount;
import net.snake.gamemodel.chest.response.ChestResponse60118;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.CharacterGoodController;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

@MsgCodeAnn(msgcode = 60119)
public class ChestReceiveExchangeProcess60119 extends CharacterMsgProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		// 跨服判断
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}

		ChestCount chestCount = character.getMyChestManager().getChestCount();
		if (character.getMyChestManager().getCountJinSongGuo() == 0) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 60012));
			return;
		}
		int count3011 = character.getMyChestManager().getCount3011();
		int count3012 = character.getMyChestManager().getCount3012();
		int count3013 = character.getMyChestManager().getCount3013();
		int count3014 = character.getMyChestManager().getCount3014();
		int count3015 = character.getMyChestManager().getCount3015();
		Collection<CharacterGoods> characterGoodsList = new ArrayList<CharacterGoods>();
		if (count3011 > 0) {
			addYanZhengWeiZhi(character, 3011, count3011, characterGoodsList);
		}
		if (count3012 > 0) {
			addYanZhengWeiZhi(character, 3012, count3012, characterGoodsList);
		}
		if (count3013 > 0) {
			addYanZhengWeiZhi(character, 3013, count3013, characterGoodsList);
		}
		if (count3014 > 0) {
			addYanZhengWeiZhi(character, 3014, count3014, characterGoodsList);
		}
		if (count3015 > 0) {
			addYanZhengWeiZhi(character, 3015, count3015, characterGoodsList);
		}
		CharacterGoodController characterGoodController = character.getCharacterGoodController();
		if (!characterGoodController.getBagGoodsContiner().isHasSpaceForAddGoods(characterGoodsList)) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 798));
			return;
		}
		if (count3011 > 0) {
			chestCount.setChesttypeReceiveTrue3011(chestCount.getChesttypeReceiveTrue3011() + count3011);
		}
		if (count3012 > 0) {
			chestCount.setChesttypeReceiveTrue3012(chestCount.getChesttypeReceiveTrue3012() + count3012);
		}
		if (count3013 > 0) {
			chestCount.setChesttypeReceiveTrue3013(chestCount.getChesttypeReceiveTrue3013() + count3013);
		}
		if (count3014 > 0) {
			chestCount.setChesttypeReceiveTrue3014(chestCount.getChesttypeReceiveTrue3014() + count3014);
		}
		if (count3015 > 0) {
			chestCount.setChesttypeReceiveTrue3015(chestCount.getChesttypeReceiveTrue3015() + count3015);
		}
		characterGoodController.getBagGoodsContiner().addGoods(characterGoodsList);

		character.sendMsg(new ChestResponse60118(chestCount));

	}

	public void addYanZhengWeiZhi(Hero character, int goodModelId, int count, Collection<CharacterGoods> characterGoodsList) {
		CharacterGoods characterGoods = CharacterGoods.createCharacterGoods(count, goodModelId, 0);
		characterGoodsList.add(characterGoods);
	}
}
