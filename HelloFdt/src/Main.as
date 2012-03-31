package {
	import cn.hxh.Circle;
	import cn.hxh.NumberCmp;

	import flash.display.Bitmap;
	import flash.display.Sprite;
	import flash.events.MouseEvent;

	public class Main extends Sprite {
		[Embed(source="assets/qb.jpg")]
		private var ActiveTutsLogo : Class;
		private var aCircle : Circle;

		public function Main() {
			trace("Hello World!");
			NumberCmp.relationOp();
			var logo : Bitmap = new ActiveTutsLogo();
			addChild(logo);
			showShape();
			this.stage.addEventListener(MouseEvent.CLICK, changeColor);
		}

		private function changeColor(event : MouseEvent) : void {
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
