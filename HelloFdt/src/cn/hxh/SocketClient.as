package cn.hxh {
	import flash.errors.IOError;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IOErrorEvent;
	import flash.events.ProgressEvent;
	import flash.events.SecurityErrorEvent;
	import flash.net.ObjectEncoding;
	import flash.net.Socket;
	import flash.system.Security;
	import flash.utils.ByteArray;

	/**
	 * @author hexuhui
	 */
	public class SocketClient  extends EventDispatcher {
		public static const IO_ERROR : String = "ioError";
		public static const SECURITY_ERROR : String = "securityError";
		public static const CLOSE : String = "close";
		private var _buffer : ByteArray;
		private var _socket : Socket;
		private var _data : SocketData;
		private var _length : int;
		private var _writeBytes : uint = 0;
		private var _readBytes : uint = 0;

		public function SocketClient() {
			init();
		}

		private function init() : void {
			reset();
			_socket = new Socket();
			_socket.objectEncoding = ObjectEncoding.AMF3;
			_data = new SocketData("default", "127.0.0.1", 1863);
			addSocketEvents();
		}

		private function addSocketEvents() : void {
			_socket.addEventListener(IOErrorEvent.IO_ERROR, socket_ioErrorHandler);
			_socket.addEventListener(SecurityErrorEvent.SECURITY_ERROR, socket_securityErrorHandler);
			_socket.addEventListener(Event.CONNECT, socket_connectHandler);
			_socket.addEventListener(ProgressEvent.SOCKET_DATA, socket_dataHandler);
			_socket.addEventListener(Event.CLOSE, socket_closeHandler);
		}

		private function socket_closeHandler() : void {
			trace("socket_closeHandler");
		}

		private function socket_dataHandler(event : ProgressEvent) : void {
			trace("socket_dataHandler");
			while (_socket.bytesAvailable > 4) {
				if (_length == 0) {
					_length = _socket.readInt();
				}
				if (_socket.bytesAvailable < _length) {
					break;
				}
				try {
					_buffer = new ByteArray();
					_socket.readBytes(_buffer, 0, _length);
					var value : Object = _buffer.readObject();
					trace(value);
					_readBytes += _length + 4;
				} catch(e : Error) {
					trace(_data.toString(), _length, e.getStackTrace());
				}
				_length = 0;
			}
		}

		private function socket_connectHandler(event : Event) : void {
			trace("socket_connectHandler");
		}

		private function socket_securityErrorHandler() : void {
			trace("socket_securityErrorHandler");
		}

		private function socket_ioErrorHandler() : void {
			trace("socket_ioErrorHandler");
		}

		private function reset() : void {
			_buffer = new ByteArray();
			_length = 0;
		}

		/**
		 * connected
		 * 
		 * @return Boolean 
		 */
		public function connected(data : SocketData) : Boolean {
			return _data.equals(data) && _socket.connected;
		}

		public function connect(data : SocketData) : void {
			if (connected(data)) {
				return;
			}
			if (_socket.connected) {
				_socket.close();
				reset();
			}
			_data = data;
			try {
				Security.loadPolicyFile("xmlsocket://" + _data.host + ":" + _data.port);
				_socket.connect(_data.host, _data.port);
			} catch(e : Error) {
				trace(e.getStackTrace());
			}
		}

		public function disconnect() : void {
			if (_socket.connected) {
				try {
					reset();
					_socket.close();
				} catch(e : IOError) {
					trace(e.getStackTrace());
				}
			}
		}

		public function get isActive() : Boolean {
			return _socket.connected;
		}

		/**
		 * call 远程调用
		 * 
		 * @param method 方法名称
		 * @param args 参数数组
		 */
		public function call(callObj : Object) : void {
			if (!_socket.connected) {
				return;
			}
			var ba : ByteArray = new ByteArray();
			ba.writeObject(callObj);
			_socket.writeUnsignedInt(ba.length);
			_socket.writeBytes(ba, 0, ba.length);
			_socket.flush();
			_writeBytes += ba.length + 4;
		}

		public function get writeBytes() : uint {
			return _writeBytes;
		}

		public function get readBytes() : uint {
			return _readBytes;
		}
	}
}
