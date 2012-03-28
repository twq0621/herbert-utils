package cn.hxh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.jboss.netty.buffer.ChannelBuffer;

public class Utils {

	private Utils() {
	}

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static final char BYTE_SEPARATOR = ' ';

	public static final String DTO_END_STR = "_C2S";

	public static String toHex(final byte[] ba) {
		return toHex(ba, false);
	}

	public static String toHex(final byte[] ba, final boolean withSeparator) {
		return toHex(ba, 0, ba.length, withSeparator);
	}

	public static String toHex(final byte[] ba, final int offset,
			final int length, final boolean withSeparator) {
		final char[] buf;
		if (withSeparator) {
			buf = new char[length * 3];
		} else {
			buf = new char[length * 2];
		}
		for (int i = offset, j = 0; i < offset + length;) {
			final char[] chars = toHexChars(ba[i++]);
			buf[j++] = chars[0];
			buf[j++] = chars[1];
			if (withSeparator) {
				buf[j++] = BYTE_SEPARATOR;
			}
		}
		return new String(buf);
	}

	private static char[] toHexChars(final int b) {
		final char left = HEX_DIGITS[(b >>> 4) & 0x0F];
		final char right = HEX_DIGITS[b & 0x0F];
		return new char[] { left, right };
	}

	public static String toHex(final byte b) {
		final char[] chars = toHexChars(b);
		return String.valueOf(chars);
	}

	public static byte[] fromHex(final char[] hex) {
		final int length = hex.length / 2;
		final byte[] raw = new byte[length];
		for (int i = 0; i < length; i++) {
			final int high = Character.digit(hex[i * 2], 16);
			final int low = Character.digit(hex[i * 2 + 1], 16);
			int value = (high << 4) | low;
			if (value > 127) {
				value -= 256;
			}
			raw[i] = (byte) value;
		}
		return raw;
	}

	public static byte[] fromHex(final String s) {
		return fromHex(s.replace(" ", "").toCharArray());
	}

	public static byte[] toInt24(final int value) {
		return new byte[] { (byte) (value >>> 16), (byte) (value >>> 8),
				(byte) value };
	}

	public static int readInt32Reverse(final ChannelBuffer in) {
		final byte a = in.readByte();
		final byte b = in.readByte();
		final byte c = in.readByte();
		final byte d = in.readByte();
		int val = 0;
		val += d << 24;
		val += c << 16;
		val += b << 8;
		val += a;
		return val;
	}

	public static void writeInt32Reverse(final ChannelBuffer out,
			final int value) {
		out.writeByte((byte) (0xFF & value));
		out.writeByte((byte) (0xFF & (value >> 8)));
		out.writeByte((byte) (0xFF & (value >> 16)));
		out.writeByte((byte) (0xFF & (value >> 24)));
	}

	public static CharSequence readAsString(String fileName) {
		return readAsString(new File(fileName));
	}

	public static CharSequence readAsString(File file) {
		StringBuilder sb = new StringBuilder();
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					fis));
			String s;
			while ((s = reader.readLine()) != null) {
				sb.append(s);
			}
			return sb;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] readAsByteArray(String fileName) {
		File file = new File(fileName);
		return readAsByteArray(file, file.length());
	}

	public static byte[] readAsByteArray(String fileName, int length) {
		return readAsByteArray(new File(fileName), length);
	}

	public static byte[] readAsByteArray(File file) {
		return readAsByteArray(file, file.length());
	}

	public static byte[] readAsByteArray(File file, long length) {
		try {
			byte[] bytes = new byte[(int) length];
			int offset = 0;
			int numRead = 0;
			FileInputStream is = new FileInputStream(file);
			while (offset < bytes.length
					&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			is.close();
			return bytes;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] sha256(final byte[] message, final byte[] key) {
		final Mac mac;
		try {
			mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(key, "HmacSHA256"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return mac.doFinal(message);
	}

	public static void sendStopSignal(int port) {
		try {
			Socket s = new Socket(InetAddress.getByName("127.0.0.1"), port);
			OutputStream out = s.getOutputStream();
			System.err.println("sending server stop request");
			out.write(("\r\n").getBytes());
			out.flush();
			s.close();
		} catch (Exception e) {
			// can happen when called twice by jvm shutdown hook
			System.err.println("stop monitor thread has terminated");
		}
	}

	private static final String COPYRIGHT_NOTICE = "\nFlazr <http://flazr.com> Copyright (C) 2009  Peter Thomas.\n"
			+ "This program comes with ABSOLUTELY NO WARRANTY.\n"
			+ "Flazr is free software, and you are welcome to redistribute it\n"
			+ "under certain conditions.  You should have received a copy of the\n"
			+ "GNU Lesser General Public License along with Flazr.\n"
			+ "If not, see <http://www.gnu.org/licenses/>\n";

	public static void printlnCopyrightNotice() {
		System.err.println(COPYRIGHT_NOTICE);
	}

	public static String trimSlashes(final String raw) {
		if (raw == null) {
			return null;
		}
		return raw.replace("/", "").replace("\\", "");
	}

}
