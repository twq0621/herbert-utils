package net.snake.gamemodel.panel.response;

import net.snake.gamemodel.panel.bean.PanelDate;
import net.snake.netio.ServerResponse;

/**
 * 面板列表。
 * 
 * @author serv_dev.
 */
public class PanelListMsg52058 extends ServerResponse {

	public PanelListMsg52058(PanelDate p) {
		this.setMsgCode(52058);
		this.writeShort(p.getfPanelId().shortValue());
		this.writeBoolean(p.getfIsOpen());
		if (p.getfIsOpen() && p.getfPanelId() < 100) {
			long time = p.getfStopTime().getTime() - System.currentTimeMillis();
			int timeMiao = (int) (time / 1000);
			this.writeInt(timeMiao);
		}
	}

}
