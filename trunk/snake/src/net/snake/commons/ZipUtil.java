package net.snake.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

/**
 * java对byte数组解压缩(zip,gzip,bzip2,jzlib) <br>
 * 压缩率: zip < bZip2 < gZip < jzlib
 * 
 */
public class ZipUtil {
	private static final Logger logger = Logger.getLogger(ZipUtil.class);
	/***
	 * 压缩GZip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] gZip(byte[] data) {
		byte[] b = null;
		ByteArrayOutputStream bos = null;
		GZIPOutputStream gzip = null;
		try {
			bos = new ByteArrayOutputStream();
			gzip = new GZIPOutputStream(bos);
			gzip.write(data);
			gzip.finish();
			b = bos.toByteArray();
		} catch (IOException ex) {
			logger.error(ex.getMessage(),ex);
		} finally {
			try {
				gzip.close();
				bos.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			} finally {
				bos = null;
				gzip = null;
			}
		}
		return b;
	}

	/***
	 * 解压GZip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unGZip(byte[] data) {
		byte[] b = null;
		ByteArrayInputStream bis = null;
		GZIPInputStream gzip = null;
		ByteArrayOutputStream baos = null;
		try {
			bis = new ByteArrayInputStream(data);
			gzip = new GZIPInputStream(bis);
			byte[] buf = new byte[1024];
			int num = -1;
			baos = new ByteArrayOutputStream();
			while ((num = gzip.read(buf, 0, buf.length)) != -1) {
				baos.write(buf, 0, num);
			}
			b = baos.toByteArray();
			baos.flush();
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		} finally {
			try {
				baos.close();
				gzip.close();
				bis.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			} finally {
				bis = null;
				gzip = null;
				baos = null;
			}
		}
		return b;
	}

	/***
	 * 压缩Zip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] zip(byte[] data) {
		byte[] b = null;
		ByteArrayOutputStream bos = null;
		ZipOutputStream zip = null;
		try {
			bos = new ByteArrayOutputStream();
			zip = new ZipOutputStream(bos);

			ZipEntry entry = new ZipEntry("z");
			entry.setTime(1288237967945l);
			// entry.setSize(data.length);
			zip.putNextEntry(entry);
			zip.write(data);
			// zip.closeEntry();
			zip.finish();
			b = bos.toByteArray();
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		} finally {
			try {
				zip.close();
				bos.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			} finally {
				bos = null;
				zip = null;
			}
		}
		return b;
	}

	/***
	 * 解压Zip
	 * 
	 * @param data
	 * @return
	 */
	public static byte[] unZip(byte[] data) {
		byte[] b = null;
		ByteArrayInputStream bis = null;
		ZipInputStream zip = null;
		try {
			bis = new ByteArrayInputStream(data);
			zip = new ZipInputStream(bis);
			while (zip.getNextEntry() != null) {
				byte[] buf = new byte[1024];
				int num = -1;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((num = zip.read(buf, 0, buf.length)) != -1) {
					baos.write(buf, 0, num);
				}
				b = baos.toByteArray();
				baos.flush();
				baos.close();
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(),ex);
		} finally {
			try {
				zip.close();
				bis.close();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			} finally {
				bis = null;
				zip = null;
			}
		}
		return b;
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @param bArray
	 * @return
	 */
	public static String bytesToHexString(byte[] bArray) {
		StringBuilder sb = new StringBuilder(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}

//	public static void main(String[] args) {
//		String s = "this is a test";
//
//		byte[] b1 = zip(s.getBytes());
//		System.out.println("zip:" + bytesToHexString(b1));
//		byte[] b2 = unZip(b1);
//		System.out.println("unZip:" + new String(b2));
//
//		byte[] b5 = gZip(s.getBytes());
//		System.out.println("bZip2:" + bytesToHexString(b5));
//		byte[] b6 = unGZip(b5);
//		System.out.println("unBZip2:" + new String(b6));
//	}
}
