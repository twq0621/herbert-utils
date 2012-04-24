/**
 * 
 */
package net.snake.across.character;

import net.snake.gamemodel.across.bean.AcrossEtc;
import net.snake.gamemodel.across.persistence.AcrossEtcManager;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.map.logic.KuafuZhanTsMap;
import net.snake.gamemodel.map.logic.Scene;
import net.snake.serverenv.vline.VLineServer;
import net.snake.shell.Options;

/**
 * 角色跨服管理器
 */
public class CharacterAcrossServerManager {
	private Hero character;
	private AcrossEtc ace;

	public CharacterAcrossServerManager(Hero character) {
		this.character = character;
	}

	/**
	 * 
	 */
	public void init() {
		if (Options.IsCrossServ) {
			ace = AcrossEtcManager.getInstance().getAcorssByCharacterId(character.getId());
			int enterSceneId = KuafuZhanTsMap.KuafuSceneId;
			VLineServer line = character.getVlineserver();
			Scene scene = line.getSceneManager().getScene(enterSceneId);
			character.setScene(enterSceneId);
			character.setX(scene.getEnterX());
			character.setY(scene.getEnterY());
		}
	}

	public AcrossEtc getAce() {
		return ace;
	}

	public void setAce(AcrossEtc ace) {
		this.ace = ace;
	}

}
