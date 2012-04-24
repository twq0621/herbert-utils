package net.snake.api.copy;

import net.snake.api.scene.IScene;


/**
 * 副本场景管理器
 * @author wutao
 * 
 */

public interface ICopySceneMnger {

	// 获得副本场景的模型，获得的场景不能直接在副本中使用，需要clone一个
	public IScene getCopyScene(Integer sceneId);

}
