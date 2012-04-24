package net.snake.gamemodel.map.response.hero;

import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.ServerResponse;


/**
 * 
 * @author serv_dev
 *
 */
public class EnterSceneBronPosInfo10020 extends ServerResponse{

	public EnterSceneBronPosInfo10020(Scene scene, Hero character){
		setMsgCode(10020);
		try {
			writeByte(1);
			writeInt(scene.getId());
			writeUTF(scene.getName());
			writeShort(character.getX());
			writeShort(character.getY());
		} catch (Exception e) {
		}
	}
	
	public EnterSceneBronPosInfo10020(int msgKey){
		setMsgCode(10020);
		try {
			writeByte(0);
			writeInterUTF(msgKey);
		} catch (Exception e) {
		}
	}
}
