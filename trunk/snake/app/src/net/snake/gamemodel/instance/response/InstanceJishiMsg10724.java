package net.snake.gamemodel.instance.response;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.gamemodel.instance.logic.InstanceController;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;

public class InstanceJishiMsg10724 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(InstanceJishiMsg10724.class);

	public InstanceJishiMsg10724(InstanceController instanceController, Fubenranking fuben, Fubenranking one, Hero character, int time) {
		this.setMsgCode(10724);
		try {
			int fubenTime = 0;
			this.writeInt(instanceController.getInstanceId());
			if (fuben == null) {
				this.writeInt(0);
				this.writeInt(0);
			} else {
				this.writeInt(fuben.getPreviousTime());
				this.writeInt(fuben.getFubenTime());
				fubenTime = fuben.getFubenTime();
			}
			if (one == null) {
				this.writeUTF("");
				this.writeInt(0);
			} else {
				if (fubenTime > 0 && fubenTime < one.getFubenTime()) {
					this.writeUTF(character.getViewName());
					this.writeInt(fubenTime);
				} else {
					this.writeUTF(one.getCce().getViewName());
					this.writeInt(one.getFubenTime());
				}
			}
			this.writeInt(time);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
