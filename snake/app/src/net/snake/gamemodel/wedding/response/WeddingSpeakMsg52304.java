/**
 * 
 */
package net.snake.gamemodel.wedding.response;

import java.util.List;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.bean.CouplesSpeak;
import net.snake.gamemodel.wedding.logic.CouplesSpeakController;
import net.snake.netio.ServerResponse;


/**
 * 
 * 
 * @author serv_dev
 */

public class WeddingSpeakMsg52304 extends ServerResponse {

	/**
	 * @param character
	 */
	public WeddingSpeakMsg52304(Hero character) {
		CouplesSpeakController csc = character.getMyFriendManager()
				.getRoleCouplesSpeakManager().getCouplesSpeakController();
        this.setMsgCode(52304);
		try {
			if (csc == null) {
				this.writeByte(0);
				return;
			}
			List<CouplesSpeak> list=csc.getList();
			this.writeByte(list.size());
			int cId=character.getId();
			for(CouplesSpeak cs:list){
				this.writeDouble(cs.getSpeakDate().getTime());
				if(cId==cs.getSpeakId()){
					this.writeByte(1);
				}else{
					this.writeByte(2);
				}
				this.writeInt(cs.getTempId());
				this.writeUTF(cs.getContent());
				
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}

}
