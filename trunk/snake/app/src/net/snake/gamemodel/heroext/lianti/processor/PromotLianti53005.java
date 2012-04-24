package net.snake.gamemodel.heroext.lianti.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.BuffId;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.goods.bean.Goodmodel;
import net.snake.gamemodel.goods.persistence.GoodmodelManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.LiantiDataProvider;
import net.snake.gamemodel.heroext.lianti.bean.Lianti;
import net.snake.gamemodel.heroext.lianti.logic.CharacterLianTiController;
import net.snake.gamemodel.heroext.lianti.response.TupoLianTiResultFail53006;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 请求主角提升炼体境界面板
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 53005)
public class PromotLianti53005 extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		request.getByte();// 当前级别，这个字节服务器未使用到
		// boolean isusePUTIJINGCUI=false;
		// try {
		byte type = request.getByte();
		// isusePUTIJINGCUI=useputijingcui==1;
		// } catch (Exception e) {
		// //为了兼容协议所以try
		// }

		CharacterLianTiController lianticontroller = character.getLiantiController();

		if (lianticontroller.getLiantiJingjieId() >= LiantiDataProvider.getInstance().getMaxLiantiJingjie()) {
			character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60015));// 您的肉身境界已经达到上限,无法提升
			return;
		}
		if (!lianticontroller.isMaxProperties()) {
			character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60016, lianticontroller.getLianTiData().getFoodGoodsnameI18n()));
			return;
		}
		if (lianticontroller.getLiantiJingjieId() >= 4 && character.getEffectController().getBufferInBufferListByBufferId(BuffId.free_roushentuotaihuangu) != null
				&& lianticontroller.getPutiCardUsecount() > 0) {
			character.getLiantiController().tupo(0);
			return;
		}
		if (type == 0) {// 只使用菩提丹
			Lianti lianti = lianticontroller.getLianTiData();
			int PUTIDANCOUNT = lianti.getTupoNeedGoodscount();
			if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(lianti.getTupoNeedGoodsmodel()) < PUTIDANCOUNT) {
				Goodmodel goodmodel = GoodmodelManager.getInstance().get(lianti.getTupoNeedGoodsmodel());
				character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60017, goodmodel.getNameI18n()));
				return;
			}
			character.getLiantiController().tupo(0);

		} else if (type == 1) {// 使用菩提精萃
			if (lianticontroller.getLiantiJingjieId() < 4) {
				character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60024));
				return;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(GoodItemId.PUTIJINGCUI) < 1) {
				character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60025));// 很抱歉，您背包中没有菩提精萃
				return;
			}
			Lianti lianti = lianticontroller.getLianTiData();
			int PUTIDANCOUNT = lianti.getTupoNeedGoodscount() - 1;
			if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(lianti.getTupoNeedGoodsmodel()) < PUTIDANCOUNT) {
				Goodmodel goodmodel = GoodmodelManager.getInstance().get(lianti.getTupoNeedGoodsmodel());
				character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60017, goodmodel.getNameI18n()));
				return;
			}
			character.getLiantiController().tupo(1);
		} else if (type == 2) {// 使用神奇菩提丹
			if (lianticontroller.getLiantiJingjieId() < 4) {
				character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60028));
				return;
			}
			if (character.getCharacterGoodController().getBagGoodsContiner().getGoodsCountByModelID(GoodItemId.SHENJIPUTIDAN) < 1) {
				character.sendMsg(new TupoLianTiResultFail53006(lianticontroller.getLiantiJingjieId(), 60026));// 很抱歉，您背包中没有神级菩提丹
				return;
			}
			character.getLiantiController().tupo(2);
		}
		//
		//
		// character.getLiantiController().tupo(isusePUTIJINGCUI);
		// // character.sendMsg(new LiantiPromotPanelInfo53004(character));
	}
}
