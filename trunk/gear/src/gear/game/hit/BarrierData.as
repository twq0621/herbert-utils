package gear.game.hit {
	import flash.geom.Rectangle;

	/**
	 * 障碍定义
	 * 
	 * @author bright
	 * @version 20111206
	 */
	public final class BarrierData {
		private var _x : int;
		private var _y : int;
		private var _z : int;
		private var _source : Rectangle;
		private var _hitRect : Rectangle;
		private var _barrier : Rectangle;
		private var _halfW : int;
		private var _halfH : int;

		public function BarrierData(halfW : int, halfH : int, hitRect : Rectangle = null) {
			_halfW = halfW;
			_halfH = halfH;
			_barrier = new Rectangle(-halfW, -halfH, halfW * 2, halfH * 2);
			_source = hitRect;
			if (_source != null) {
				_hitRect = _source.clone();
			}
		}

		/**
		 * 为方便调整参数加的重设碰撞区域函数
		 */
		public function resetRect(hitRect : Rectangle, dist : int = 0, high : int = 0) : void {
			_source = hitRect;
			_hitRect = _source.clone();
			_halfW = dist;
			_halfH = high;
		}

		public function set x(value : int) : void {
			_x = value;
			_barrier.x = _x - _halfW;
		}

		public function get x() : int {
			return _x;
		}

		public function set y(value : int) : void {
			_y = value;
			_barrier.y = _y - _halfH;
		}

		public function get y() : int {
			return _y;
		}

		public function get halfW() : int {
			return _halfW;
		}

		public function get halfH() : int {
			return _halfH;
		}

		/**
		 * 障碍矩形-处理寻路
		 * 
		 * @return Rectangle
		 */
		public function get barrier() : Rectangle {
			return _barrier;
		}

		/**
		 * 遮挡矩形-处理透明遮挡与攻击碰撞
		 * 
		 * @return Rectangle
		 */
		public function get hitRect() : Rectangle {
			return _hitRect;
		}

		public function reset(nx : int, ny : int, nz : int = 0, flipH : Boolean = false) : void {
			_x = nx;
			_y = ny;
			_barrier.x = _x - _halfW;
			_barrier.y = _y - _halfH;
			_z = nz;
			if (_hitRect) {
				if (flipH) {
					_hitRect.x = _x - _source.x - _source.width;
				} else {
					_hitRect.x = _x + _source.x;
				}
				_hitRect.y = _y + _source.y + _z;
			}
		}

		public function hit(value : BarrierData) : Boolean {
			return _barrier.intersects(value.barrier);
		}

		public function get source() : Rectangle {
			return _source;
		}
	}
}