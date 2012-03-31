package game.chat.common;

public class ErrorCode {

	public static final int SUCCESS = 0;

	public static final int FAIL = 1;

	public static final int PWD_INVALID = 2;

	public static final int USER_NAME_EXIST = 3;

	public static final int ROLE_NAME_ALREADY_EXIST = 4;

	public static final int SELECT_ROLE_NAME_INVALID = 5;

	/** 聊天消息异常 */
	public static final int CHAT_SESSION_INVALID = 10001;

	/** 角色异常 */
	public static final int CHAT_ROLE_INVALID = 10002;

	/** 目标不在线 */
	public static final int CHAT_TARGET_OFFLINE = 10003;

}
