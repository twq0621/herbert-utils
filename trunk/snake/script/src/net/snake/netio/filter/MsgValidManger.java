package net.snake.netio.filter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.session.IoSession;
public class MsgValidManger {
	public final static String MSG_FIRE_WALL="msgFirewALL";
	public final static int OK_Validity=0;
	public final static int Alert_Validity=1;
	public final static int Frequently_Validity=2;
	
	private Map<Integer, MsgValid> msgTimeMap = new ConcurrentHashMap<Integer, MsgValid>();
	
	public MsgValidManger(IoSession session){
		session.setAttribute(MSG_FIRE_WALL, this);
	}
	/**
	 * 返回消息合法性 0合法，1正常非法 ，2异常非法
	 * @param code
	 * @return
	 */
	public int isValidMsg(int code){
		MsgValid mv=msgTimeMap.get(code);
		if(mv==null){
			addMsgValid(code);
			return 0;
		}
		return mv.isValidMsg();
	}
	private void addMsgValid(int code){
		Integer time=MsgValidDateManager.getInstance().getMsgIntervalTime(code);
		if(time==null){
			 msgTimeMap.put(code, new MsgValid(0));
		}else{
			 msgTimeMap.put(code, new MsgValid(time));
		}
	}
}
