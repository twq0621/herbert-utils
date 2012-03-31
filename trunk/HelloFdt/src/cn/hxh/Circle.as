package cn.hxh {
	import flash.display.Shape;

	/**
	 * @author hexuhui
	 */
	public class Circle extends Shape {
		private var _color : int;
		private var _size : int;

		public function Circle(color : int, size : int) {
			_color = color;
			_size = size;
		}

		public function get color() : int {
			return _color;
		}

		public function set color(color : int) : void {
			_color = color;
		}

		public function drawCircle() : void {
			this.graphics.clear();
			this.graphics.beginFill(color);
			this.graphics.drawCircle(400, 150, _size);
		}

		public function get size() : int {
			return _size;
		}

		public function set size(size : int) : void {
			_size = size;
		}
	}
}
