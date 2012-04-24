package net.snake.gamemodel.guide.response;

import net.snake.gamemodel.activities.bean.ActivityData;
import net.snake.netio.ServerResponse;


/**
 * 活动开启提示
 */


public class NewGuideActivityMsg50678 extends ServerResponse {
	public NewGuideActivityMsg50678(ActivityData ac){
		this.setMsgCode(50678);
		try {
			this.writeUTF(ac.getfName());
			this.writeUTF(ac.getfNewgaidMsgI18n());
			this.writeUTF(ac.getfNewgaidAction());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
