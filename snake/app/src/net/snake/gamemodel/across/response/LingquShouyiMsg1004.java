package net.snake.gamemodel.across.response;

import java.io.IOException;

import org.apache.log4j.Logger;

import net.snake.gamemodel.across.bean.CharacterAcross;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gmtool.net.ByteArrayWriter;
import net.snake.gmtool.net.Msg;


public class LingquShouyiMsg1004 extends Msg {
	private static final Logger logger = Logger.getLogger(LingquShouyiMsg1004.class);
	public LingquShouyiMsg1004(Hero character){
		this.setFunction(1004);
		ByteArrayWriter out=this.getContentWriter();
		try {
			out.writeInt(character.getOriginalSid());
			out.writeInt(character.getAccountInitiallyId());
			out.writeInt(character.getCharacterInitiallyId());
			out.writeInt(character.getId());
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		
	}

	public LingquShouyiMsg1004(CharacterAcross temp) {
		this.setFunction(1004);
		ByteArrayWriter out=this.getContentWriter();
		try {
			out.writeInt(temp.getServerId());
			out.writeInt(temp.getAccountInitiallyId());
			out.writeInt(temp.getCharacterInitiallyId());
			out.writeInt(temp.getCharacterId());
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}

}
