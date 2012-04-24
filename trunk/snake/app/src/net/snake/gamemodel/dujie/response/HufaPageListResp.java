package net.snake.gamemodel.dujie.response;

import java.util.List;

import net.snake.gamemodel.dujie.bean.GuardImg;
import net.snake.gamemodel.dujie.bean.Tianshen;
import net.snake.gamemodel.dujie.logic.GsCalculator;
import net.snake.netio.ServerResponse;

/**
 * 护法页面列表
 * 
 * @author serv_dev<br>
 *         Copyright (c) 2011 Kingnet
 */
public class HufaPageListResp extends ServerResponse {
	public HufaPageListResp(int suc) {
		setMsgCode(60324);
		writeByte(suc);
	}

	public void setTianshens(int lvl, List<Tianshen> tianshen) {
		writeByte(lvl);
		writeByte(tianshen.size());

		try {
			for (int i = 0; i < tianshen.size(); i++) {
				Tianshen ts = tianshen.get(i);
				writeInt(ts.getId());
				writeUTF(ts.getName());
				writeInt(ts.getGs());
				writeInt(ts.getFee());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setHeros(int lvl, List<GuardImg> heros,int tianjieNum) {
		writeByte(lvl);
		writeByte(heros.size());

		try {
			for (int i = 0; i < heros.size(); i++) {
				GuardImg ts = heros.get(i);
				writeInt(ts.id);
				writeUTF(ts.name);
				
				writeInt(ts.gs);
				writeInt(GsCalculator.calcHeroFee(ts.gs, tianjieNum));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
