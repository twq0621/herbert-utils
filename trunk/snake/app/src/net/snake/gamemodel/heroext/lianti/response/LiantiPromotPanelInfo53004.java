package net.snake.gamemodel.heroext.lianti.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.lianti.logic.CharacterLianTiController;
import net.snake.netio.ServerResponse;
import net.snake.netio.message.ResponseMsg;

public class LiantiPromotPanelInfo53004 extends ServerResponse implements ResponseMsg {
	public LiantiPromotPanelInfo53004(Hero character) {
		setMsgCode(53004);
		CharacterLianTiController liantic = character.getLiantiController();
		// 当前炼体级别（byte,1到8），当前祝福值(int),如祝福值大于0则发{祝福值清零倒计时(int,单位:秒)}
		writeByte(liantic.getLiantiJingjieId());
		writeInt(liantic.getZhufu());
		if (liantic.getZhufu() <= 0) {
			return;
		}
		int daojishi = 0;
		if (liantic.getZhufuStarttime() != null && liantic.getZhufuStarttime().getTime() > 0) {
			daojishi = (int) ((liantic.getZhufuStarttime().getTime() + 24 * 60 * 60 * 1000 - System.currentTimeMillis()) / 1000);
			if (daojishi < 0) {
				daojishi = 0;
			}
		}
		writeInt(daojishi);
	}
}
