package gear.ui.data {
	import gear.net.AssetData;
	import gear.ui.core.GAlign;
	import gear.ui.core.GBaseData;
	import gear.ui.manager.UIManager;
	import gear.ui.skin.button.ButtonSkin;

	import flash.display.Sprite;

	/**
	 * @author bright
	 */
	public class GTitleBarData extends GBaseData {
		public var bgAsset : AssetData = new AssetData("GTitleBar_bgSkin");
		public var closeButtonData : GButtonData = new GButtonData();
		public var labelData : GLabelData;

		public function GTitleBarData() {
			width = 100;
			height = 30;
			labelData = new GLabelData();
			labelData.align = new GAlign(-1, -1, -1, -1, 0, 0);
			closeButtonData.width =	closeButtonData.height = 45;
			var upSkin : Sprite = UIManager.getSkin(new AssetData("juese_juese_guanbi_001"));
			var overSkin : Sprite = UIManager.getSkin(new AssetData("juese_juese_guanbi_002"));
			var downSkin : Sprite = UIManager.getSkin(new AssetData("juese_juese_guanbi_003"));
			var disabledSkin : Sprite = UIManager.getSkin(new AssetData("juese_juese_guanbi_003"));
			closeButtonData.skin = new ButtonSkin(upSkin, overSkin, downSkin, disabledSkin);
			closeButtonData.align = new GAlign(-1, -10, 20, -1, 0, 0);
		}
	}
}
