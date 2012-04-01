package cn.hxh {
	import avmplus.getQualifiedClassName;

	import flash.events.TimerEvent;
	import flash.utils.Dictionary;
	import flash.utils.Timer;

	/**
	 * @author hexuhui
	 */
	public class CallPool {
		private var _callbacks : Dictionary;
		private var _list : Array;
		private var _timer : Timer;
		// 是否为排队，间隔处理消息
		private var _queue : Boolean = true;

		private function timerHandler(event : TimerEvent) : void {
			if (_list.length == 0) {
				_timer.stop();
				return;
			} else {
				// execute(_list.shift());
			}
		}

		public function CallPool() {
			_callbacks = new Dictionary(true);
			_list = new Array();
			_timer = new Timer(30);
			_timer.addEventListener(TimerEvent.TIMER, timerHandler);
		}

		public function handleResponse(obj : Object) : void {
			var methodName : String = extractMethodName(obj);
			execute(methodName, obj);
		}

		private function extractMethodName(obj : Object) : String {
			var longClassName : String = getQualifiedClassName(obj);
			var startIndex : uint = longClassName.indexOf("::") + 2;
			var endIndex : uint = longClassName.indexOf("_S2C");
			var simpleClassName : String = longClassName.substring(startIndex, endIndex);
			return simpleClassName.toLowerCase();
		}

		private function execute(methodName : String, obj : Object) : void {
			var calls : Array = _callbacks[methodName];
			if (calls == null || calls.length == 0) {
				trace(methodName + " not found callbacks");
				return;
			}
			for each (var callback:Function in calls) {
				try {
					var args:Array = new Array(obj);
					callback.apply(null, args);
				} catch(e : Error) {
					trace(methodName + e.getStackTrace());
				}
			}
		}

		/**
		 * addCallback 加入反射
		 * 
		 * @param method String 方法名
		 * @param callback Function 回调函数 
		 */
		public function addCallback(method : String, callback : Function) : void {
			if (_callbacks[method] == null) {
				_callbacks[method] = [];
			}
			var index : int = findAt(method, callback);
			if (index == -1) {
				_callbacks[method].push(callback);
			}
		}

		private function findAt(method : String, callback : Function) : int {
			var calls : Array = _callbacks[method];
			if (calls == null)
				return -1;
			var index : int = 0;
			for each (var call:Function in calls) {
				if (call == callback)
					return index;
				index++;
			}
			return -1;
		}
	}
}
