﻿package gear.ui.skin {
	import gear.net.AssetData;
	import gear.ui.theme.ITheme;

	import flash.display.BitmapData;
	import flash.display.Graphics;
	import flash.display.Sprite;
	import flash.utils.Dictionary;

	/**
	 * ASSkin 脚本皮肤
	 * 
	 * @author bright
	 * @version 20111121
	 */
	public class ASSkin {
		private static var _lib : Dictionary = new Dictionary(true);
		private static var _list : Dictionary = new Dictionary(true);

		public static function get errorSkin() : Sprite {
			var skin : Sprite = new Sprite();
			skin.name = "errorSkin";
			var g : Graphics = skin.graphics;
			g.beginFill(0xFF0000, 0.5);
			g.drawRect(0, 0, 20, 20);
			g.endFill();
			return skin;
		}

		public static function get emptySkin() : Sprite {
			var skin : Sprite = new Sprite();
			var g : Graphics = skin.graphics;
			g.beginFill(0x000000, 0);
			g.drawRect(0, 0, 20, 20);
			g.endFill();
			return skin;
		}

		public static function get modalSkin() : Sprite {
			var skin : Sprite = new Sprite();
			var g : Graphics = skin.graphics;
			g.beginFill(0x000000, 0.3);
			g.drawRect(0, 0, 20, 20);
			g.endFill();
			return skin;
		}

		public static function setTheme(libId : String, theme : ITheme) : void {
			_lib[libId] = theme;
		}

		public static function setAt(key : String, skin : Skin) : void {
			_list[key] = skin;
		}

		public static function getBy(asset : AssetData) : Sprite {
			var libId : String = (asset.libId == AssetData.SWF_LIB ? AssetData.AS_LIB : asset.libId);
			var theme : ITheme = _lib[libId] as ITheme;
			var skin : Sprite;
			if (theme == null) {
				skin = errorSkin;
				skin.mouseEnabled = skin.mouseChildren = false;
				return skin;
			}
			switch(asset.className) {
				case SkinStyle.errorSkin:
					skin = errorSkin;
					break;
				case SkinStyle.emptySkin:
					skin = emptySkin;
					break;
				default:
					try {
						skin = theme[asset.className];
					} catch(e : Error) {
						var s : Skin = _list[asset.className] as Skin;
						if (s != null) {
							skin = s.clone();
						} else {
							skin = errorSkin;
						}
					}
					break;
			}
			skin.mouseEnabled = skin.mouseChildren = false;
			return skin;
		}

		public static function get texture() : BitmapData {
			var bd : BitmapData = BitmapData(_list["texture"]);
			if (bd != null) {
				return bd;
			}
			bd = new BitmapData(2, 2, true, 0);
			bd.setPixel32(0, 0, 0x99000000);
			bd.setPixel32(1, 1, 0x99000000);
			bd.setPixel32(0, 1, 0xCC000000);
			bd.setPixel32(1, 0, 0xCC000000);
			_list["texture"] = bd;
			return bd;
		}
	}
}