package gear.render {
	import flash.display.DisplayObject;

	import gear.core.IDispose;
	import gear.utils.ColorMatrixUtil;

	import flash.display.Bitmap;
	import flash.display.BitmapData;
	import flash.filters.ColorMatrixFilter;
	import flash.geom.ColorTransform;
	import flash.geom.Matrix;
	import flash.geom.Point;
	import flash.geom.Rectangle;

	/**
	 * 位图数据定义
	 * 
	 * @author bright
	 * @version 20111024
	 */
	public class BDList implements IDispose {
		public var key : String;
		protected var _list : Vector.<BDUnit>;
		protected var _total : int;
		protected var _hasFlipH : Boolean;
		protected var _hasShadow : Boolean;
		protected var _matrix : Matrix;
		protected var _flipH_list : Vector.<BDUnit>;
		protected var _shadow_list : Vector.<BDUnit>;
		protected var _shadow : BDUnit;
		protected var _flipH_shadow_list : Vector.<BDUnit>;
		private var _rect : Rectangle;

		protected function createFlipHList() : void {
			if (_flipH_list != null) {
				return;
			}
			var total : int = _list.length;
			_flipH_list = new Vector.<BDUnit>(total, true);
			var unit : BDUnit;
			var bd : BitmapData;
			_matrix.identity();
			_matrix.scale(-1, 1);
			for (var i : int = 0;i < total;i++) {
				unit = _list[i];
				if (unit == null || unit.bd == null) {
					continue;
				}
				bd = new BitmapData(unit.bd.width, unit.bd.height, true, 0);
				_matrix.tx = bd.width;
				bd.draw(unit.bd, _matrix);
				_flipH_list[i] = new BDUnit(-unit.offsetX - bd.width, unit.offsetY, bd);
			}
		}

		protected function createShadowList() : void {
			if (_shadow_list != null) {
				return;
			}
			var total : int = _list.length;
			_shadow_list = new Vector.<BDUnit>(total, true);
			var unit : BDUnit;
			var bd : BitmapData;
			_matrix.identity();
			_matrix.c = 0.35;
			_matrix.d = 0.45;
			var size : Point;
			var offset : Point;
			for (var i : int = 0;i < total;i++) {
				unit = _list[i];
				if (unit == null || unit.bd == null) {
					continue;
				}
				size = _matrix.transformPoint(new Point(unit.bd.width, unit.bd.height));
				offset = _matrix.transformPoint(new Point(unit.offsetX, unit.offsetY));
				bd = new BitmapData(size.x, size.y, true, 0);
				bd.draw(unit.bd, _matrix, new ColorTransform(0, 0, 0, 0.5));
				_shadow_list[i] = new BDUnit(offset.x, offset.y, bd);
			}
		}

		protected function createFlipHShadowList() : void {
			if (_flipH_shadow_list != null) {
				return;
			}
			var total : int = _list.length;
			_flipH_shadow_list = new Vector.<BDUnit>(total, true);
			var unit : BDUnit;
			var bd : BitmapData;
			_matrix.identity();
			_matrix.c = 0.35;
			_matrix.d = 0.45;
			var size : Point;
			var offset : Point;
			for (var i : int = 0;i < total;i++) {
				unit = _flipH_list[i];
				if (unit == null || unit.bd == null) {
					continue;
				}
				size = _matrix.transformPoint(new Point(unit.bd.width, unit.bd.height));
				offset = _matrix.transformPoint(new Point(unit.offsetX, unit.offsetY));
				bd = new BitmapData(size.x, size.y, true, 0);
				bd.draw(unit.bd, _matrix, new ColorTransform(0, 0, 0, 0.5));
				_flipH_shadow_list[i] = new BDUnit(offset.x, offset.y, bd);
			}
		}

		/**
		 * 构造函数
		 * 
		 * @param value 位图数组.
		 */
		public function BDList(value : Vector.<BDUnit>) {
			_list = value;
			_total = _list.length;
			_matrix = new Matrix();
		}

		public function create(hasFlipH : Boolean, hasShadow : Boolean, obj : DisplayObject = null) : void {
			_hasFlipH = hasFlipH;
			_hasShadow = hasShadow;
			if (_hasFlipH) {
				createFlipHList();
			}
			if (_hasShadow && obj == null) {
				createShadowList();
				if (_hasFlipH) {
					createFlipHShadowList();
				}
			} else if (obj) {
				if (_shadow) _shadow.dispose();
				_shadow = new BDUnit(-obj.width / 2, -obj.height / 2, new BitmapData(obj.width, obj.height, true, 0));
				_shadow.bd.draw(obj);
			}
		}

		/** 
		 * 指定影子
		 */
		private function createShadowAtObj(value : DisplayObject) : void {
		}

		/**
		 * 获得指定帧的位图
		 * 
		 * @param frame 第几帧
		 * @return 位图
		 */
		public function getAt(value : int, flipH : Boolean = false) : BDUnit {
			if (value < 0 || value >= _list.length) {
				return null;
			}
			if (flipH && _hasFlipH) {
				return _flipH_list[value];
			} else {
				return _list[value];
			}
		}

		public function get hasShadow() : Boolean {
			return _hasShadow;
		}

		public function getShadowAt(value : int, flipH : Boolean) : BDUnit {
			if (_shadow) return _shadow;
			if (_hasShadow) {
				if (flipH && _hasFlipH) {
					return _flipH_shadow_list[value];
				} else {
					return _shadow_list[value];
				}
			} else {
				return null;
			}
		}

		/**
		 * 获得总帧数
		 * 
		 * @return 总帧数
		 */
		public function get total() : int {
			return _total;
		}

		/**
		 * @inheritDoc
		 */
		public function dispose() : void {
			if (_list == null) {
				return;
			}
			for each (var unit:BDUnit in _list) {
				unit.dispose();
			}
			_list = null;
		}

		private function getMaxRect() : void {
			_rect = new Rectangle();
			if (_list) {
				for each (var data : BDUnit in _list) {
					var bdw : int = data.offsetX + data.bd.width;
					var bdh : int = data.offsetY + data.bd.height;
					if (bdw > _rect.width) _rect.width = bdw;
					if (bdh > _rect.height) _rect.height = bdh;
				}
			}
		}

		public function alpha(value : Number) : void {
			var bd : BitmapData;
			for (var i : int = 0; i < _list.length; i++) {
				bd = _list[i].bd;
				var ct : ColorTransform = new ColorTransform(1.0, 1.0, 1.0, value);
				bd.colorTransform(bd.rect, ct);
				_list[i] = new BDUnit(_list[i].offsetX, _list[i].offsetY, bd);
			}
		}

		/**
		 * 调整亮度,对比度,饱和度,色相
		 */
		public function adjustColor(brightness : int, contrast : int, saturation : int, hue : int) : void {
			var point : Point = new Point(0, 0);
			var colorMatrixFilter : ColorMatrixFilter = new ColorMatrixFilter();
			var data : Array = ColorMatrixUtil.adjustBrightness(brightness);
			ColorMatrixUtil.multiplyMatrix(data, ColorMatrixUtil.adjustContrast(contrast));
			ColorMatrixUtil.multiplyMatrix(data, ColorMatrixUtil.adjustSaturation(saturation));
			ColorMatrixUtil.multiplyMatrix(data, ColorMatrixUtil.adjustHue(hue));
			colorMatrixFilter.matrix = data;
			var bd : BitmapData;
			for (var i : int = 0; i < _list.length; i++) {
				bd = _list[i].bd;
				bd.applyFilter(bd, bd.rect, point, colorMatrixFilter);
			}
			if (_flipH_list) {
				for (var j : int = 0; j < _flipH_list.length; j++) {
					bd = _list[j].bd;
					bd.applyFilter(bd, bd.rect, point, colorMatrixFilter);
				}
			}
		}

		public function get maxHeight() : int {
			if (_rect == null) getMaxRect();
			return _rect.height;
		}

		public function get maxWidth() : int {
			if (_rect == null) getMaxRect();
			return _rect.width;
		}

		public function get list() : Vector.<BDUnit> {
			return _list;
		}
	}
}