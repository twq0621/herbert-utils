package net.snake.gamemodel.activities.response;

import net.snake.gamemodel.activities.bean.DailyActivity;
import net.snake.netio.ServerResponse;

/**
 * @author serv_dev
 */
public class DailyActivityResponse40042 extends ServerResponse {
	// // 数量byte，{时间string，活动名称strin，等级short,发布人名称string，发布人id int（无发布人为0），
	// 已完成次数short，总次数short，奖励信息byte（0表示没星，1半星，2一星，3,一星半......），
	// 详细信息string xml格式,类型:byte(类型1进行中,2全天,3即将举行,4已过期)，完成状态（0未完成/1完成）}

	public DailyActivityResponse40042(DailyActivity[] acts) throws Exception {
		setMsgCode(40042);
		if (acts != null && acts.length > 0) {
			writeByte(acts.length);
			for (DailyActivity act : acts) {
				writeUTF(act.getTime());
				writeUTF(act.getName());
				writeShort(act.getGrade());
				writeUTF(act.getNpcname());
				writeInt(act.getNpcid());

				// TODO 把FINSHcount合并到getmaxcount中 根据act.getIscount()判断
				short iscount = act.getIscount();
				String count = "";
				if (iscount == 1) {
					count = act.getFinshcount() + "/" + act.getMaxcount();
				} else {
					count = act.getMaxcount();
				}
				// writeShort();
				writeUTF(count);
				writeByte(act.getLeave());
				writeUTF(act.getDesc());
				writeByte(act.getType());
				writeByte(act.getState());
				writeUTF(act.getTongguanjl());
			}
		} else {
			writeByte(0);
		}

	}

}
