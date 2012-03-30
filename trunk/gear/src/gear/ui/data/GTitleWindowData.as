package gear.ui.data {
	import gear.net.AssetData;
	import gear.ui.core.GBaseData;
	import gear.ui.manager.UIManager;
	import gear.ui.skin.button.ButtonSkin;

	import flash.display.Sprite;

	/**
	 * @author thinkpad
	 */
	public class GTitleWindowData extends GBaseData {
		public var titleBarData : GTitleBarData;
		public var panelData : GPanelData;
		public var modal : Boolean = false;
		public var allowDrag : Boolean = false;
		public var allowScale : Boolean = false;
		public function GTitleWindowData() {
			titleBarData = new GTitleBarData();
			panelData = new GPanelData();
			width = 100;
			height = 100;
		}
	}
}
