package net.snake.across.character.msg;


import net.snake.ai.formula.CharacterFormula;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.monster.logic.SceneXuanyuanMonster;
import net.snake.netio.ServerResponse;

import org.apache.log4j.Logger;


/**
 * 跨服战消息提示
 */

public class KuafuzhanTishiMsg51150 extends ServerResponse {
	private static final Logger logger = Logger.getLogger(KuafuzhanTishiMsg51150.class);
	public KuafuzhanTishiMsg51150(Hero character, SceneXuanyuanMonster[] sceneXuanyuanMonsters) {
		this.setMsgCode(51150);
		try {
			int jiacheng=character.getMyFactionManager().getXuanyuanjianJiacheng();
	    	this.writeShort(jiacheng);
	    	this.writeUTF(character.getMyFactionManager().getXuanyuanjianRoleName());
	    	this.writeShort(character.getMycharacterAcrossZhengzuoManager().getRegionContinuumExp());
	    //	this.writeByte(character.getMyFactionManager().isBangzhu()?1:0);
	    	long _experience = CharacterFormula.getKuafuOneMinute(character);
	    	this.writeInt((int)_experience);
	    	this.writeShort(character.getMycharacterAcrossZhengzuoManager().getOnlinTime());
	    	for(int i=0;i<sceneXuanyuanMonsters.length;i++){
	    		Hero role=sceneXuanyuanMonsters[i].getXuanyuanjianCharacter();
	    		if(role==null){
	    			this.writeShort(sceneXuanyuanMonsters[i].getX());
	    			this.writeShort(sceneXuanyuanMonsters[i].getY());
	    		}else{
	    			this.writeShort(role.getX());
	    			this.writeShort(role.getY());
	    		}
	    		
	    	}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
