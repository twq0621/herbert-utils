package gear.utils {
	/**
	 * GArrayUtil
	 * 
	 * @author bright
	 * @version 20111128
	 */
	public class GArrayUtil {
		public static function shuffle(value : Array) : void {
			var source : Array = value.concat();
			var index : int;
			value.splice(0);
			while (source.length > 0) {
				index = MathUtil.random(0, source.length - 1);
				value.push(source[index]);
				source.splice(index, 1);
			}
		}

		public static function trim(value : Array) : void {
			var index : int = 0;
			while (index < value.length) {
				if (value[index] == null) {
					value.splice(index, 1);
				} else {
					index++;
				}
			}
		}

		public static function repeat(value : *, total : int) : Array {
			var list : Array = new Array();
			for (var i : int = 0;i < total;i++) {
				list.push(value);
			}
			return list;
		}

		public static function binarySearch(keys : Array, target : int) : int {
			var high : int = keys.length;
			var low : int = -1;
			while (high - low > 1) {
				var probe : int = (low + high) * 0.5;
				if (keys[probe] > target)
					high = probe;
				else
					low = probe;
			}
			if (low == -1 || keys[low] !== target)
				return -1;
			else
				return low;
		}
	}
}
