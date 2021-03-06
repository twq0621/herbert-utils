package cn.hxh {
	import flash.display.DisplayObject;
	import flash.display.Sprite;
	import flash.display.Stage;
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;

	/**
	 * @author hexuhui
	 */
	public class KeyboardControl {
		// Create the game character objects
		public var character : Sprite = new Sprite();
		[Embed(source="assets/character.png")]
		public var CharacterImg : Class;
		public var characterImage : DisplayObject = new CharacterImg();
		// Create and initialize the vx and vy variable
		public var vx : int = 0;
		public var vy : int = 0;
		private var stage : Stage;

		public function KeyboardControl(stage : Stage) {
			// Load the image and add the character to the stage
			this.stage = stage;
			character.addChild(characterImage);
			stage.addChild(character);
			character.x = 225;
			character.y = 150;

			// Add event listeners
			stage.addEventListener(KeyboardEvent.KEY_DOWN, keyDownHandler);
			stage.addEventListener(KeyboardEvent.KEY_UP, keyUpHandler);
			stage.addEventListener(Event.ENTER_FRAME, enterFrameHandler);
		}

		public function keyDownHandler(event : KeyboardEvent) : void {
			if (event.keyCode == Keyboard.LEFT) {
				vx = -5;
			} else if (event.keyCode == Keyboard.RIGHT) {
				vx = 5;
			} else if (event.keyCode == Keyboard.UP) {
				vy = -5;
			} else if (event.keyCode == Keyboard.DOWN) {
				vy = 5;
			}
		}

		public function keyUpHandler(event : KeyboardEvent) : void {
			if (event.keyCode == Keyboard.LEFT || event.keyCode == Keyboard.RIGHT) {
				vx = 0;
			} else if (event.keyCode == Keyboard.DOWN || event.keyCode == Keyboard.UP) {
				vy = 0;
			}
		}

		public function enterFrameHandler(event : Event) : void {
			// Move the player
			character.x += vx;
			character.y += vy;
			// Stop the character at the stage edges
			if (character.x < 0) {
				character.x = 0;
			}
			if (character.y < 0) {
				character.y = 0;
			}
			if (character.x + character.width > stage.stageWidth) {
				character.x = stage.stageWidth - character.width;
			}
			if (character.y + character.height > stage.stageHeight) {
				character.y = stage.stageHeight - character.height;
			}
		}
	}
}
