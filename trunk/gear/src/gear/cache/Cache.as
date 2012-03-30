package gear.cache {
	import flash.display.BitmapData;

	import gear.core.IDispose;
	import gear.log4a.GLogger;

	import flash.utils.Dictionary;

	/**
	 * 数据Cash
	 * @author Cafe
	 */
	public class Cache implements IDispose {
		private var _list : Dictionary = new Dictionary();

		/**
		 * 永久存在的缓存
		 */
		public function addPermanent(key : String, value : *) : void {
			if (_list[key]) {
				GLogger.debug("放入永久缓存的时候发现该key已经有值，并且覆盖掉该缓存了。");
			} else {
				_list[key] = new Object();
			}
			_list[key].data = value;
			_list[key].useCount = -1;
		}

		/**
		 * 取得一个缓存，同时将计数器+1
		 */
		public function getAtKey(key : String) : * {
			if (key == null || key == "" || _list.hasOwnProperty(key) == false ) return null;
			if (_list[key]) {
				_list[key].useCount++;
				return _list[key].data;
			} else {
				return null;
			}
		}

		/**
		 * 通过key放入一个缓存,同时将计算器置0，如果是一个已经存在的对象那么计数器减1，不同的对象则覆盖同时计数器置0
		 */
		public function putAtKey(key : String, value : *) : void {
			// 如果是永久性缓存也跳出判断
			if (key == null || key == "" || value == null || (_list[key] && _list[key].useCount == -1)) return ;
			if (_list[key] == null || (_list[key] && _list[key].data != value)) {
				_list[key] = new Object();
				_list[key].data = value;
				_list[key].useCount = 0;
			} else {
				//TODO Cafe 缓存类自动释放有问题
				return;
				var data : * = _list[key];
				if (data) {
					data.useCount--;
					if (data.useCount <= 0) {
						// 如果它是一个手工释放对象或者bitamapdata对象那则进行手工释放
//						if (_list[key].data is IDispose || _list[key].data is BitmapData) {
//							_list[key].data.dispose();
//						}
//						_list[key].data = null;
//						_list[key].useCount = null;
//						delete _list[key];
					}
				}
			}
		}

		/**
		 * 将数据缓存中的数据全部释放掉
		 */
		public function dispose() : void {
			for (var key : String in _list) {
				if (_list[key].data is IDispose) {
					_list[key].data.dispose();
				}
				delete _list[key];
			}
			_list = null;
		}
	}
}
