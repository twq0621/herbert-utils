package net.snake.gamemodel.guide.logic;

import java.util.List;

import net.snake.gamemodel.guide.bean.CharacterMsg;
import net.snake.gamemodel.guide.persistence.CharacterMsgManager;
import net.snake.gamemodel.hero.bean.Hero;


/**
 * 玩家上线后提示消息管理器
 * 
 * @author serv_dev
 * 
 */
public class MyCharacterMsgManager {
	private Hero character;
	private List<CharacterMsg> msgList;

	public MyCharacterMsgManager(Hero character) {
		this.character = character;
	}

	/**
	 * 初始话玩家是否有上线提示消息
	 */
	public void destory() {
		if (msgList != null) {
			msgList.clear();
			msgList=null;
		}
	}

	public void init() {
		try {
			CharacterMsgManager characterMsgManager = CharacterMsgManager
					.getInstance();
			List<CharacterMsg> list = characterMsgManager
					.getListByCharacterId(character.getId());
			if (list != null && list.size() > 0) {
				this.msgList = list;
			}
		} catch (Exception e) {

			// logger.error(e.getMessage(),e);
		}
	}

	/**
	 * 发送玩家提示消息删除数据库中过时的玩家提示消息
	 */
	public void sendCharacterMsg() {
//		if (this.msgList != null && this.msgList.size() > 0) {
//			for (int i = 0; i < msgList.size(); i++) {
//				CharacterMsg characterMsg = msgList.get(i);
//				String msg = characterMsg.getMsg();
//				if (msg.contains(":")) {
//					String[] strP = msg.split(":");
//					String[] canshu = msg.split(",");
//					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,
//							Integer.parseInt(strP[0]), canshu));
//				} else {
//					character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,
//							Integer.parseInt(msg)));
//				}
//
//			}
//			this.msgList.clear();
//			CharacterMsgManager characterMsgManager = CharacterMsgManager
//					.getInstance();
//			characterMsgManager.deleteCharacterMsgByCharacterId(character
//					.getId());
//			this.msgList = null;
//		}
	}

}
