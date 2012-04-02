package cn.hxh {
	import flash.display.Loader;
	import flash.display.Sprite;
	import flash.display.Stage;
	import flash.events.KeyboardEvent;
	import flash.events.MouseEvent;
	import flash.net.URLRequest;
	import flash.text.TextField;
	import flash.text.TextFormat;
	import flash.text.TextFormatAlign;
	import flash.ui.Keyboard;

	/**
	 * @author Administrator
	 */
	public class NumberGuessingGame {
		public var format : TextFormat = new TextFormat();
		public var output : TextField = new TextField();
		public var input : TextField = new TextField();
		public var startMsg : String;
		public var mysteryNum : uint;
		public var currentGuessNum : uint;
		public var guessesRemaining : uint;
		public var guessesMade : uint;
		public var gameStatus : String;
		public var gameWon : Boolean;
		private var stage : Stage;
		// Create URLRequest objects to get the paths to the images
		public var buttonUpURL : URLRequest = new URLRequest("../src/assets/buttonUp.png");
		public var buttonOverURL : URLRequest = new URLRequest("../src/assets/buttonOver.png");
		public var buttonDownURL : URLRequest = new URLRequest("../src/assets/buttonDown.png");
		// Create Loaders for the images
		public var buttonUpImage : Loader = new Loader();
		public var buttonOverImage : Loader = new Loader();
		public var buttonDownImage : Loader = new Loader();
		// Create a single Sprite to contain the images
		public var button : Sprite = new Sprite();

		public function NumberGuessingGame(stage : Stage) {
			this.stage = stage;
			initButtons();
			setupTextFields();
			startGame();
		}

		private function initButtons() : void {
			buttonUpImage.load(buttonUpURL);
			buttonDownImage.load(buttonDownURL);
			buttonOverImage.load(buttonOverURL);
			buttonUpImage.visible = true;
			buttonDownImage.visible = false;
			buttonOverImage.visible = false;
			button.addChild(buttonUpImage);
			button.addChild(buttonDownImage);
			button.addChild(buttonOverImage);
			button.buttonMode = true;
			button.mouseEnabled = true;
			button.useHandCursor = true;
			button.mouseChildren = false;
			stage.addChild(button);
			button.x = 300;
			button.y = 175;
			button.addEventListener(MouseEvent.ROLL_OVER, overHandler);
			button.addEventListener(MouseEvent.MOUSE_DOWN, downHandler);
			button.addEventListener(MouseEvent.ROLL_OUT, resetHandler);
			button.addEventListener(MouseEvent.CLICK, clickHandler);
		}

		private function resetHandler(event : MouseEvent) : void {
			buttonUpImage.visible = true;
			buttonDownImage.visible = false;
			buttonOverImage.visible = false;
		}

		private function clickHandler(event : MouseEvent) : void {
			buttonUpImage.visible = true;
			buttonDownImage.visible = false;
			buttonOverImage.visible = false;
		}

		private function downHandler(event : MouseEvent) : void {
			buttonUpImage.visible = false;
			buttonDownImage.visible = true;
			buttonOverImage.visible = false;
		}

		private function overHandler(event : MouseEvent) : void {
			buttonUpImage.visible = false;
			buttonDownImage.visible = false;
			buttonOverImage.visible = true;
		}

		private function startGame() : void {
			// init parameters
			startMsg = "请输入0-99之间的一个数字";
			mysteryNum = Math.floor(Math.random() * 100);
			trace("mysteryNum:" + mysteryNum);
			output.text = startMsg;
			input.text = "";
			guessesRemaining = 10;
			guessesMade = 0;
			gameStatus = "";
			gameWon = false;
			stage.addEventListener(KeyboardEvent.KEY_DOWN, keyPressListener);
		}

		private function keyPressListener(evt : KeyboardEvent) : void {
			if (evt.keyCode == Keyboard.ENTER) {
				playGame();
			}
		}

		private function playGame() : void {
			guessesRemaining--;
			guessesMade++;
			gameStatus = "Guess: " + guessesMade + ", Remaining: " + guessesRemaining;
			currentGuessNum = parseInt(input.text);
			if (currentGuessNum > mysteryNum) {
				output.text = "too high." + "\n" + gameStatus;
				checkGameOver();
			} else if (currentGuessNum < mysteryNum) {
				output.text = "too low!." + "\n" + gameStatus;
				checkGameOver();
			} else {
				output.text = "you got it!";
				gameWon = true;
				endGame();
			}
		}

		private function checkGameOver() : void {
			if (guessesRemaining < 1) {
				endGame();
			}
		}

		private function endGame() : void {
			if (gameWon) {
				output.text = "Yes, it's " + mysteryNum + "!" + "\n" + "It only took you " + guessesMade + " guesses.";
			} else {
				output.text = "No more guesses left!" + "\n" + "The number was: " + mysteryNum + ".";
			}
			stage.removeEventListener(KeyboardEvent.KEY_DOWN, keyPressListener);
			input.type = "dynamic";
			input.alpha = 0.4;
			button.removeEventListener(MouseEvent.ROLL_OVER, overHandler);
			button.removeEventListener(MouseEvent.MOUSE_DOWN, downHandler);
			button.removeEventListener(MouseEvent.ROLL_OUT, resetHandler);
			button.removeEventListener(MouseEvent.CLICK, clickHandler);
			button.alpha = 0.5;
			button.mouseEnabled = false;
		}

		private function setupTextFields() : void {
			// set the text format
			format.font = "Helvetica";
			format.size = 20;
			format.color = 0xff0000;
			format.align = TextFormatAlign.LEFT;
			// configure the output of text field
			output.defaultTextFormat = format;
			output.width = 300;
			output.height = 100;
			output.border = true;
			output.wordWrap = true;
			output.text = "test";

			// display and position the output text field
			stage.addChild(output);
			output.x = 70;
			output.y = 65;

			// configure the input text field
			format.size = 60;
			input.defaultTextFormat = format;
			input.width = 100;
			input.height = 60;
			input.border = true;
			input.type = "input";
			input.maxChars = 2;
			input.restrict = "0-9";
			input.background = true;
			input.backgroundColor = 0xCCCCCC;
			input.text = "";

			// Display and position the input text field
			stage.addChild(input);
			input.x = 70;
			input.y = 200;
			stage.focus = input;
		}
	}
}
