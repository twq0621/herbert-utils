package net.snake.gamemodel.activities.response;

import java.util.List;

import net.snake.gamemodel.activities.bean.XianshiActivity;
import net.snake.gamemodel.activities.persistence.XianshiActivityController;
import net.snake.netio.ServerResponse;

/**
 * 返回开启活动类别
 * 
 * @author serv-dev
 */

public class ActivityTypsMsg53062 extends ServerResponse {

	/**
	 * @param list
	 * 
	 */
	public ActivityTypsMsg53062(List<XianshiActivityController> list) {
		this.setMsgCode(53062);
		try {
			XianshiActivity temp = list.get(0).getXianshiActivity();
			this.writeUTF(temp.getUiBottomDescI18n());
			this.writeByte(list.size());
			for (XianshiActivityController type : list) {
				XianshiActivity xa = type.getXianshiActivity();
				this.writeInt(type.getXianshiActivity().getId());
				this.writeUTF(xa.getNameI18n());
				this.writeUTF(xa.getDescI18n());
				this.writeUTF(xa.getMenuDescI18n());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
