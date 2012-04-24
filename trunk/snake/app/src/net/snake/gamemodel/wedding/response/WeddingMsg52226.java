/**
 * 
 */
package net.snake.gamemodel.wedding.response;


import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.WeddingRing;
import net.snake.netio.ServerResponse;


/**
 * 转发请求 
 * 
 * @author serv_dev
 */

public class WeddingMsg52226 extends ServerResponse {
	public WeddingMsg52226(Hero appllyer, WeddingRing wr) {
		this.setMsgCode(52226);
		try {
			this.writeInt(appllyer.getId());
			this.writeByte(appllyer.getHeadimg());
			this.writeUTF(appllyer.getViewName());
			this.writeShort(appllyer.getGrade());
			this.writeUTF(appllyer.getMyFactionManager()
					.getFactionName());
			this.writeByte(appllyer.getPopsinger());
			this.writeInt(wr.getRingId());
			this.writeInt(wr.getSkillA());
			this.writeInt(wr.getSkillB());
			this.writeInt(wr.getMaleGood());
			this.writeInt(wr.getFemaleGood());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
