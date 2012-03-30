package gear.data {
	import flash.utils.ByteArray;
	import flash.utils.Endian;

	/**
	 * @author cafe
	 */
	public class ByteArrayLite extends ByteArray {
		public static const CHARSET : String = "gb2312";
		public static const MAX_NAMESIZE : int = 32;

		public function ByteArrayLite() {
			endian = Endian.LITTLE_ENDIAN;
		}

		/**
		 * 写定长字符串
		 */
		public function writeFixedString(ln : uint, str : String) : void {
			var oldP : uint = position;
			writeMultiByte(str, CHARSET);
			var cost : uint = position - oldP;
			length += ln - cost;
			position += ln - cost;
		}

		/**
		 * 写字符串(长度为1个字节)
		 */
		public function writeString1(ln : uint, str : String) : void {
			writeByte(ln);
			writeMultiByte(str, CHARSET);
		}

		/**
		 * 写字符串(长度为2个字节)
		 */
		public function writeString2(ln : uint, str : String) : void {
			writeShort(ln);
			writeMultiByte(str, CHARSET);
		}

		/**
		 * 写字符串(长度为4个字节)
		 */
		public function writeString4(ln : uint, str : String) : void {
			writeUnsignedInt(ln);
			writeMultiByte(str, CHARSET);
		}

		/**
		 * 写8个字节的大整数
		 */
		public function writeUnsignedInt8(value : Number) : void {
			writeUnsignedInt(value);
			writeUnsignedInt(value / 0x100000000);
		}

		/**
		 * 写GUID
		 */
		public function writeGlobalId(guid : GUID) : void {
			writeShort(guid.zoneId);
			writeUnsignedInt(guid.time);
			writeUnsignedInt(guid.mapId);
			writeShort(guid.index);
		}

		public function writeName(name : String) : void {
			writeFixedString(MAX_NAMESIZE, name);
		}

		public function writeNameWithServerId(name : String, serverId : String) : void {
			var serverIdIndex : int = name.indexOf(".");
			if (name != "" && serverIdIndex == -1) name += serverId;
			else {
				var nameServerId : String = name.substring(serverIdIndex, name.length);
				if (nameServerId != serverId) {
					nameServerId = nameServerId.toLocaleUpperCase();
				}
				name = name.substring(0, serverIdIndex) + nameServerId;
			}
			writeFixedString(MAX_NAMESIZE, name);
		}

		/**
		 * 读取一个ip地址
		 */
		public function readIp() : String {
			var ip1 : uint = readUnsignedByte();
			var ip2 : uint = readUnsignedByte();
			var ip3 : uint = readUnsignedByte();
			var ip4 : uint = readUnsignedByte();
			return ( ip1 + "." + ip2 + "." + ip3 + "." + ip4 );
		}

		/**
		 * 读取唯一编号
		 */
		public function readGlobalId() : GUID {
			var guid : GUID = new GUID();
			guid.zoneId = readUnsignedShort();
			guid.time = readUnsignedInt();
			guid.mapId = readUnsignedInt();
			guid.index = readUnsignedShort();
			return guid;
		}

		/**
		 * 读8个字节的大整数
		 */
		public function readUnsignedInt8() : Number {
			var n : Number = readUnsignedInt();
			n += readUnsignedInt() * 0x100000000;
			return n;
		}

		/**
		 * 读取中文字
		 * @param ln 字符长度
		 */
		public function readChinese(ln : uint) : String {
			return readMultiByte(ln, CHARSET);
		}

		public function readTureName() : String {
			return readChinese(MAX_NAMESIZE);
		}

		/**
		 * 读字符串(长度为1个字节)
		 */
		public function readString1() : String {
			return readChinese(readUnsignedByte());
		}

		/**
		 * 读字符串(长度为2个字节)
		 */
		public function readString2() : String {
			return readChinese(readUnsignedShort());
		}

		/**
		 * 读字符串(长度为4个字节)
		 */
		public function readString4() : String {
			return readChinese(readUnsignedInt());
		}

		/**
		 * 获取位
		 */
		public function getBitAt(index : uint = 0) : Boolean {
			index++;
			if (index > length * 8) {
				throw new RangeError("提供的位数超出索引");
				return false;
			}
			var byteIndex : uint = Math.ceil(index / 8) - 1;
			var flag : uint = 1 << (7 - (length * 8 - index) % 8);
			var result : Boolean = Boolean(this[byteIndex] & flag);
			return result;
		}

		public function get bitLength() : uint {
			return length * 8;
		}
	}
}
