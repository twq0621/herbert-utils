package game.scrolling {
	import flash.display.DisplayObject;
	import flash.display.Sprite;
	import flash.display.Stage;
	import flash.events.Event;
	import flash.events.KeyboardEvent;
	import flash.ui.Keyboard;

	/**
	 * @author Administrator
	 */
	public class BasicScrolling {
		[Embed(source="assets/background.jpg")]
		public var ImageClass : Class;
		public var backgroundImg : DisplayObject = new ImageClass();
		public var backroundSprite : Sprite = new Sprite();
		[Embed(source="assets/character.png")]
		public var CharacterImg : Class;
		public var characterImage : DisplayObject = new CharacterImg();
		public var character : Sprite = new Sprite();
		private var vx : int = 0;
		private var vy : int = 0;

		public function BasicScrolling(stage : Stage) {
			// add the background
			backroundSprite.addChild(backgroundImg);
			stage.addChild(backroundSprite);
			// add the character
			character.addChild(characterImage);
			stage.addChild(character);
			character.x = 250;
			character.y = 100;
			// Add the event listeners
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
			// Move the background
			backroundSprite.x -= vx;
			backroundSprite.y -= vy;

			// Check the stage boundaries
			if (backroundSprite.x > 0) {
				backroundSprite.x = 0;
			}
			if (backroundSprite.y > 0) {
				backroundSprite.y = 0;
			}
			if (backroundSprite.x < backroundSprite.stage.stageWidth - backroundSprite.width) {
				backroundSprite.x = backroundSprite.stage.stageWidth - backroundSprite.width;
			}
			if (backroundSprite.y < backroundSprite.stage.stageHeight - backroundSprite.height) {
				backroundSprite.y = backroundSprite.stage.stageHeight - backroundSprite.height;
			}
		}
	}
}
