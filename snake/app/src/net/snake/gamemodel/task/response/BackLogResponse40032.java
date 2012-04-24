package net.snake.gamemodel.task.response;

import java.util.List;

import net.snake.gamemodel.task.bean.Backlog;
import net.snake.netio.ServerResponse;


/**
 *@author serv_dev
 */
public class BackLogResponse40032 extends ServerResponse {
//	数量byte{任务名string，完成数short，总数short},代闯阵法string，
//	boss刷新时间string，获得经验int，杀怪数int，获得装备数int，完成任务数int，
//	获得真气数int，击杀boss数int，获得坐骑数int，闯通阵法数int
//	数量byte{任务名string，完成数short，总数short},代闯阵法string，
//	boss刷新时间string，获得经验int，杀怪数int，获得装备数int，完成任务数int，
//	获得真气数int，击杀boss数int，获得坐骑数int，闯通阵法数int,
//	变强推荐（byte）0-n


	public BackLogResponse40032(List<Backlog> tasks, List<Backlog> zhenfas, String showTime, long exp, int monsters, int equips, int task_compl, int zhenqi, int boss_killed, int shengwang, int zhenfas_pass,int tip) throws Exception{
		setMsgCode(40032);
		if(tasks!=null&&tasks.size()>0){
			writeByte(tasks.size());
			for (Backlog task : tasks) {
				writeInt(task.getTargetid());
				writeUTF(task.getTitle());
				writeShort(task.getFinshnum());
				writeShort(task.getSumnum());
			}
		}else{
			writeByte(0);
		}
		if(zhenfas!=null&&zhenfas.size()>0){
			writeByte(zhenfas.size());
			for (Backlog zhenfa : zhenfas) {
				writeUTF(zhenfa.getTitle());
				writeInt(zhenfa.getTargetid());
			}
		}else{
			writeByte(0);
		}
		writeUTF(showTime);
		writeDouble(exp);
		writeInt(monsters);
		writeInt(equips);
		writeInt(task_compl);
		writeInt(zhenqi);
		writeInt(boss_killed);
		writeInt(shengwang);
		writeInt(zhenfas_pass);
		writeByte(tip);
	}
}
