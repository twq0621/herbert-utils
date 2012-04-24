package net.snake.gamemodel.guide.processor;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.message.SimpleResponse;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.guide.bean.CharacterIdea;
import net.snake.gamemodel.guide.persistence.CharacterIdeaManger;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;

/**
 * 意见提交 28001
 * 
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 28001, accessLimit = 1000)
public class CharacterSubmitIdeaProcessor extends CharacterMsgProcessor implements IThreadProcessor {

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		byte type = request.getByte();
		String title = request.getString();
		String content = request.getString();
		CharacterIdea ci = new CharacterIdea();
		ci.settType((int) type);
		ci.setTitle(title);
		ci.setContent(content);
		ci.setCharacterId(character.getId());
		CharacterIdeaManger.getInstance().insert(ci);
		character.sendMsg(SimpleResponse.onlyMsgCodeMsg(28002));
	}
}
