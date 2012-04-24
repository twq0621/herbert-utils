package net.snake.gamemodel.common.response;

import org.apache.log4j.Logger;

import net.snake.consts.Symbol;
import net.snake.netio.ServerResponse;


/**
 * 提示消息
 * 
 * @author wutao
 * 
 */
public class PrompMsg extends ServerResponse {
	private static Logger logger = Logger.getLogger(PrompMsg.class);

	public PrompMsg(int msgkey) {
		setMsgCode(22224);// 固定的消息号
		try {
			writeUTF(TipMsg.MSGPOSITION_ERRORTIP + "");
			writeInt(msgkey);
			writeByte(0);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param position
	 *            消息的位置
	 * @param msgkey
	 *            消息key
	 * @param vars
	 *            消息参数
	 */
	public PrompMsg(int position, int msgkey, String... vars) {
		setMsgCode(22224);// 固定的消息号
		try {
			writeUTF(position + "");
			writeInt(msgkey);
			writeByte(vars.length);
			for (String var : vars) {
				writeUTF(var);
			}
			if (position == 4) {
				writeUTF(String.valueOf(System.currentTimeMillis()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * @param position
	 *            消息的位置
	 * @param msgkey
	 *            消息key
	 * @param vars
	 *            消息参数
	 */
	public PrompMsg(String position, int msgkey, String... vars) {
		setMsgCode(22224);// 固定的消息号
		try {
			writeUTF(position);
			writeInt(msgkey);
			writeByte(vars.length);
			for (String var : vars) {
				writeUTF(var);
			}
			boolean ishassysbort = false;
			String posit[] = position.split(Symbol.DOUHAO);
			for (String t : posit) {
				if (t != null && t.equals("4")) {
					ishassysbort = true;
				}
			}
			if (ishassysbort) {
				writeUTF(String.valueOf(System.currentTimeMillis()));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public PrompMsg(int position, int msgkey, Object... vars) {
		setMsgCode(22224);// 固定的消息号
		try {
			writeUTF(position + "");
			writeInt(msgkey);
			writeByte(vars.length);
			for (Object var : vars) {
				writeUTF(var.toString());
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
