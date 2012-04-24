package net.snake.commons.message;

import java.io.IOException;

import net.snake.netio.ServerResponse;
import net.snake.netio.message.ResponseMsg;

/**
 * 消息内容比较简单的服务端响应。
 * 
 * @author serv_dev
 * 
 */
public class SimpleResponse {
	/**
	 * 消息格式 状态(byte)
	 * 
	 * @param msgcode
	 * @param value
	 * @return
	 */
	public static ResponseMsg byteStatusMsg(int msgcode, int value) {
		return new ByteStatusMsg(msgcode, value);
	}

	/**
	 * 消息格式 状态(byte)
	 * 
	 * @param msgcode
	 * @param value
	 * @return
	 */
	public static ResponseMsg intMsg(int msgcode, int value) {
		return new IntStatusMsg(msgcode, value);
	}

	/**
	 * 只有消息号的消息;
	 * 
	 * @param msgcode
	 * @param value
	 * @return
	 */
	public static ResponseMsg onlyMsgCodeMsg(int msgcode) {
		ServerResponse response = new ServerResponse();
		response.setMsgCode(msgcode);
		return response;
	}

	/**
	 * 请求失败，及原因消息。
	 * 消息号(int) 失败 0(byte) 失败消息 (utf);
	 * 
	 * @param msgcode
	 * @param value
	 * @return
	 */
	public static ResponseMsg failReasonMsg(int msgcode, int msgKey) {
		return new FailReasonMsg(msgcode, msgKey);
	}

	/**
	 * 消息格式 成功 1(byte) 失败消息 (utf);
	 * 
	 * @param msgcode
	 * @param value
	 * @return
	 */
	public static ResponseMsg successMsg(int msgcode, int msgKey) {
		return new SuccessMsg(msgcode, msgKey);
	}

	/**
	 * 只包含状态的响应消息
	 * 
	 * @author serv_dev
	 * 
	 */
	private static class ByteStatusMsg extends ServerResponse {
		public ByteStatusMsg(int msgcode, int value) {
			setMsgCode(msgcode);
			writeByte(value);
		}
	}

	/**
	 * 只包含状态的响应消息
	 * 
	 * @author serv_dev
	 * 
	 */
	private static class IntStatusMsg extends ServerResponse {
		public IntStatusMsg(int msgcode, int value) {
			setMsgCode(msgcode);
			writeInt(value);
		}
	}

	/**
	 * 请求失败，及原因消息
	 * 
	 * @author serv_dev
	 * 
	 */
	private static class FailReasonMsg extends ServerResponse {

		public FailReasonMsg(int msgcode, int msgKey) {
			setMsgCode(msgcode);
			try {
				writeByte(0);
				writeInterUTF(msgKey);
			} catch (IOException e) {
			}
		}
	}

	private static class SuccessMsg extends ServerResponse {

		public SuccessMsg(int msgcode, int msgKey) {
			setMsgCode(msgcode);
			try {
				writeByte(1);
				writeInterUTF(msgKey);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
