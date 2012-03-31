package {
	import cn.hxh.Circle;
	import cn.hxh.NumberCmp;
	import cn.hxh.SocketClient;
	import cn.hxh.SocketData;

	import game.dto.Login_C2S;

	import flash.display.Bitmap;
	import flash.display.Sprite;
	import flash.events.MouseEvent;

	public class Main extends Sprite {
		[Embed(source="assets/qb.jpg")]
		private var ActiveTutsLogo : Class;
		private var aCircle : Circle;
		private var socketClient : SocketClient;

		public function Main() {
			trace("Hello World!");
			socketClient = new SocketClient();
			var socketData : SocketData = new SocketData("default", "127.0.0.1", 8653);
			socketClient.connect(socketData);
			trace("connect successfully!");
			NumberCmp.relationOp();
			var logo : Bitmap = new ActiveTutsLogo();
			addChild(logo);
			showShape();
			this.stage.addEventListener(MouseEvent.CLICK, changeColor);
		}

		private function changeColor(event : MouseEvent) : void {
			var loginMsg : Login_C2S = new Login_C2S();
			loginMsg.name = "wujian";
			loginMsg.pwd = "123456";
			socketClient.call(loginMsg);
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
