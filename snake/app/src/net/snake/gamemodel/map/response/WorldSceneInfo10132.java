package net.snake.gamemodel.map.response;


import net.snake.gamemodel.map.logic.Scene;
import net.snake.netio.ServerResponse;


public class WorldSceneInfo10132 extends ServerResponse {
	public WorldSceneInfo10132(Scene scene,int yuanbao){
		this.setMsgCode(10132);
		try {
			this.writeInt(scene.getId());
			this.writeUTF(scene.getShowName());
			this.writeShort(scene.getEnterLevelLimit());
			this.writeUTF(scene.getMonsterDesc());
			this.writeUTF(scene.getExerciseDesc());
			this.writeUTF(scene.getBossDesc());
			this.writeUTF(scene.getBossTimeDesc());
			this.writeUTF(scene.getBossDropGoods());
			this.writeInt(yuanbao);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	
	}
}
