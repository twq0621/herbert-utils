/**
 * 
 */
package net.snake.gamemodel.hero.response;


import net.snake.netio.ServerResponse;


/**
 * 返回随机名字
 */


public class CharacterRandomNameMsg10010 extends ServerResponse {

	/**
	 * 返回随机名字
	 */
	public CharacterRandomNameMsg10010(String name) {
		this.setMsgCode(10010);
		try {
			this.writeUTF(name);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
