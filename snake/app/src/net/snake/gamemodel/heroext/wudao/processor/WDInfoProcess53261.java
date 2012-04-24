package net.snake.gamemodel.heroext.wudao.processor;

import java.util.Collection;

import net.snake.ann.MsgCodeAnn;
import net.snake.consts.Position;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.fight.persistence.DGWDManager;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.wudao.bean.DgwdModel;
import net.snake.gamemodel.heroext.wudao.persistence.DGWDController;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.RequestMsg;

@MsgCodeAnn(msgcode = 53261, accessLimit = 200)
public class WDInfoProcess53261 extends CharacterMsgProcessor {
	public int gradelimit = 62;

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (character.getGrade() < gradelimit) {
			character.sendRightPrompMsg(50079, gradelimit + "");
			return;
		}
		Collection<DgwdModel> allModel = DGWDManager.getInstance().getAllModel();
		character.sendMsg(new WDInfoResult53262(allModel, character));
	}

}

class WDInfoResult53262 extends ServerResponse {
	public WDInfoResult53262(Collection<DgwdModel> set, Hero c) throws Exception {
		setMsgCode(53262);
		ServerResponse out = this;
		DGWDController dc = c.getDgwdController();
		if (set != null && set.size() > 0) {
			out.writeByte(set.size());
			for (DgwdModel model : set) {
				out.writeInt(model.getId());
				out.writeUTF(model.getNameI18n());
				out.writeByte(dc.hasDGWD(model) ? 1 : 0);
				out.writeInt(model.getAddattackShow());
				out.writeInt(model.getAdddefenceShow());
				out.writeInt(model.getAddmaxhpShow());
				out.writeInt(model.getAddmpShow());
				out.writeInt(model.getAddspShow());
			}
		} else {
			out.writeByte(0);
		}
		CharacterGoods goodsByPostion = c.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_YIFU);
		out.writeByte(c.getPopsinger());
		out.writeInt(goodsByPostion == null ? 0 : goodsByPostion.getGoodmodelId());
	}
}
