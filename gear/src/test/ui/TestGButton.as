package test.ui {
	import flash.events.MouseEvent;

	import gear.core.Game;
	import gear.net.LibData;
	import gear.net.SWFLoader;
	import gear.ui.controls.GButton;
	import gear.ui.data.GButtonData;

	import flash.events.Event;

	/**
	 * @author bright
	 * @version 20111125
	 */
	[SWF(width=550,height=400,backgroundColor=0x333333,frameRate="30")]
	public class TestGButton extends Game {
		private var _button_a : GButton;
		private var _button_b : GButton;

		override protected function startup() : void {
			_res.add(new SWFLoader(new LibData("ui/ui.swf", "ui")));
			_res.addEventListener(Event.COMPLETE, res_complateHandler);
			_res.load();
		}

		private function res_complateHandler(event : Event) : void {
			addGButton();
		}

		protected function addGButton() : void {
			var data : GButtonData = new GButtonData();
			data.labelData.text = "test1";
			data.width = 60;
			data.height = 30;
			_button_a = new GButton(data);
			addChild(_button_a);
			data = data.clone();
			data.x = 100;
			data.width = 80;
			data.labelData.text = "test2";
			_button_b = new GButton(data);
			addChild(_button_b);
			_button_a.addEventListener(MouseEvent.CLICK, buttonA_clickHandler);
			_button_b.addEventListener(MouseEvent.CLICK, buttonB_clickHandler);
		}

		private function buttonA_clickHandler(event : MouseEvent) : void {
			trace("button a");
		}

		private function buttonB_clickHandler(event : MouseEvent) : void {
			trace("button b");
		}
	}
}
