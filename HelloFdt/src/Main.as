package {
	import cn.hxh.Circle;

	import game.service.LoginManager;

	import flash.display.Bitmap;
	import flash.display.Sprite;
	import flash.events.MouseEvent;

	public class Main extends Sprite {
		[Embed(source="assets/qb.jpg")]
		private var ActiveTutsLogo : Class;
		private var aCircle : Circle;
		private var loginManager : LoginManager;

		public function Main() {
			loginManager = new LoginManager();
			var logo : Bitmap = new ActiveTutsLogo();
			addChild(logo);
			showShape();
			this.stage.addEventListener(MouseEvent.CLICK, changeColor);
		}

		private function changeColor(event : MouseEvent) : void {
			loginManager.callLogin("niumang", "123456");
			aCircle.color = 0xffffff * Math.random();
			aCircle.size = Math.random() * 100;
			aCircle.drawCircle();
		}

		private function showShape() : void {
			aCircle = new Circle(0xff0000, 100);
			aCircle.drawCircle();
			addChild(aCircle);
		}
	}
}
