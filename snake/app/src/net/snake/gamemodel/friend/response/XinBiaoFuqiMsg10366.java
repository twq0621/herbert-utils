package net.snake.gamemodel.friend.response;

import net.snake.GameServer;
import net.snake.gamemodel.hero.bean.CharacterCacheEntry;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.gamemodel.wedding.logic.CouplesController;
import net.snake.netio.ServerResponse;


import org.apache.log4j.Logger;
public class XinBiaoFuqiMsg10366 extends ServerResponse {

	private static Logger logger = Logger.getLogger(XinBiaoFuqiMsg10366.class);

	public XinBiaoFuqiMsg10366(Hero character, CouplesController fuqi) {
		this.setMsgCode(10366);
		try {
			this.writeUTF(character.getNowBiaoqing());
			this.writeUTF(character.getNowXingqing());
			if (fuqi == null) {
				this.writeByte(0);
				return;
			}
			Couples c = fuqi.getCouples();
			if (c == null) {
				this.writeByte(0);
				return;
			}
			this.writeByte(1);
			Hero wedder = null;
			CharacterCacheEntry cce = null;
			if (character.isMale()) {
				this.writeInt(c.getFemaleId());
				this.writeUTF(c.getFemaleCce().getViewName());
				this.writeInt(c.getMaleFavor());
				cce = c.getFemaleCce();
				wedder = GameServer.vlineServerManager.getCharacterById(c.getFemaleId());
			} else {
				this.writeInt(c.getMaleId());
				this.writeUTF(c.getMaleCce().getViewName());
				this.writeInt(c.getFemaleFavor());
				cce = c.getMaleCce();
				wedder = GameServer.vlineServerManager.getCharacterById(c.getMaleId());
			}
			if (wedder != null) {
				this.writeByte(1);
				this.writeByte(wedder.getCharacterStatus());
			} else {
				this.writeByte(0);
				this.writeByte(0);
			}
			this.writeByte(cce.getHeadimg());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
