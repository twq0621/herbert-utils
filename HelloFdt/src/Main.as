package {
	import cn.hxh.Circle;
	import cn.hxh.NumberGuessingGame;

	import game.service.LoginManager;

	import flash.display.Sprite;
	import flash.events.MouseEvent;

	public class Main extends Sprite {
		[Embed(source="assets/qb.jpg")]
		private var ActiveTutsLogo : Class;
		private var aCircle : Circle;
		private var loginManager : LoginManager;
		private var guessingGame : NumberGuessingGame;

		public function Main() {
			guessingGame = new NumberGuessingGame(stage);
			// loginManager = new LoginManager();
			// showShape();
			// var logo : Bitmap = new ActiveTutsLogo();
			// logo.alpha = 0.5;
			// addChild(logo);
			// this.stage.addEventListener(MouseEvent.CLICK, changePosition);
			// this.stage.addEventListener(MouseEvent.CLICK, changeColor);
		}

		private function changePosition(event : MouseEvent) : void {
			var posX : int = Math.random() * 50 + 350;
			var posY : int = Math.random() * 50 + 150;
			aCircle.changePosition(posX, posY);
		}

		private function changeColor(event : MouseEvent) : void {
			loginManager.callLogin("niumang", "123456");
			aCircle.color = 0xffffff * Math.random();
			aCircle.size = Math.random() * 100;
			aCircle.alpha = 0.1;
			aCircle.drawCircle();
		}

		private function showShape() : void {
			aCircle = new Circle(0xff0000, 100);
			aCircle.drawCircle();
			addChild(aCircle);
		}
	}
}
