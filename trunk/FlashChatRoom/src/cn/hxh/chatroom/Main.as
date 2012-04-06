package cn.hxh.chatroom {
	import flash.display.Sprite;

	/**
	 * ...
	 * @author Jobe Makar - jobe@electrotank.com
	 */
	 [SWF(width="900", height="700", 
	backgroundColor="#FFFFFF", frameRate="60")]
	public class Main extends Sprite {
		public function Main() : void {
			// create the chat flow
			var chatFlow : ChatFlow = new ChatFlow();
			addChild(chatFlow);
		}
	}
}