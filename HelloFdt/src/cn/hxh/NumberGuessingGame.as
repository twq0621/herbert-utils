package cn.hxh {
	import flash.display.Stage;
	import flash.events.KeyboardEvent;
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
		private var stage : Stage;

		public function NumberGuessingGame(stage : Stage) {
			this.stage = stage;
			setupTextFields();
			startGame();
		}

		private function startGame() : void {
			// init parameters
			startMsg = "请输入0-99之间的一个数字";
			mysteryNum = 25;
			output.text = startMsg;
			input.text = "";
			stage.addEventListener(KeyboardEvent.KEY_DOWN, keyPressListener);
		}

		private function keyPressListener(evt : KeyboardEvent) : void {
			if (evt.keyCode == Keyboard.ENTER) {
				playGame();
			}
		}

		private function playGame() : void {
			currentGuessNum = parseInt(input.text);
			if (currentGuessNum > mysteryNum) {
				output.text = "too high";
			} else if (currentGuessNum < mysteryNum) {
				output.text = "too low!";
			} else {
				output.text = "you got it!";
			}
		}

		private function setupTextFields() : void {
			// set the text format
			format.font = "Helvetica";
			format.size = 32;
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
