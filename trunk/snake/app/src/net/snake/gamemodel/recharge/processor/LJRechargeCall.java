package net.snake.gamemodel.recharge.processor;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.consts.MaxLimit;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.logic.CharacterPropertyManager;
import net.snake.gamemodel.hero.persistence.CharacterManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.INotNeedAuthProcessor;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.netio.message.process.MsgProcessor;
import net.snake.netio.player.GamePlayer;

/**
 * 礼金充值
 * 
 * @author serv_dev
 */
@MsgCodeAnn(msgcode = 112)
public class LJRechargeCall extends MsgProcessor implements IThreadProcessor, INotNeedAuthProcessor {

	@Override
	public void process(GamePlayer session, RequestMsg request) throws Exception {
		if (session.getIoSession().getRemoteAddress().toString().indexOf("127.0.0.1") != -1) {
			int id = request.getInt();// characterid
			int amount = request.getInt();// 礼金
			if (amount > MaxLimit.LIJIN_MAX || amount < 0) {
				// 非法数值
				session.sendMsg(SimpleResponse.onlyMsgCodeMsg(0));
				return;
			}
			Hero character = GameServer.vlineServerManager.getCharacterById(id);
			if (character != null) {
				int changeLijin = CharacterPropertyManager.changeLijin(character, amount);
				if (changeLijin == 0) {
					// 增加礼金失败
				} else {
					// 增加礼金成功
				}
			} else {
				character = CharacterManager.getInstance().getCharacterById(id);
				character.setJiaozi(character.getJiaozi() + amount);
				CharacterManager.getInstance().cacheToDB(character);
			}

		}
		session.sendMsg(SimpleResponse.onlyMsgCodeMsg(0));
	}
}
