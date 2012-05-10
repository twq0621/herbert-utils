package net.snake.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author serv_dev
 */
abstract public class IOTool {
	private static Logger logger = LoggerFactory.getLogger(IOTool.class);
	/*
	 * public static MappedByteBuffer getFileMapBuffer(String fileName) throws
	 * IOException { File file = new File(fileName); FileInputStream fis = new
	 * FileInputStream(file); fis.getChannel(); MappedByteBuffer mbb =
	 * fis.getChannel().map(MapMode.READ_ONLY, 0, file.length()); return mbb; }
	 */
	/**
	 * 
	 * 
	 * @param filename
	 *            String
	 * @param obj
	 *            Object
	 * @throws Exception
	 */
	public static void writeObjectToFile(String filename, Object obj)
			throws Exception {
		String tmpfilename = filename + "_tmp" + Thread.currentThread().getId();
		File tmpfile = new File(tmpfilename);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(tmpfile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
			oos.flush();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException ex) {
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException ex1) {
				}
			}

		}

		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
		tmpfile.renameTo(file);
	}

	public static Object getObjectFromFile(String filename) throws Exception {
		FileInputStream fis = null;
		ObjectInputStream ofis = null;
		try {
			fis = new FileInputStream(filename);
			ofis = new ObjectInputStream(fis);
			Object obj = ofis.readObject();
			return obj;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
			if (ofis != null) {
				try {
					ofis.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}
	}

	public static String getFileExtendNameWithDot(String name) {
		String extendnamewithdot = "";
		int dotidx = name.lastIndexOf('.');
		if (dotidx >= 0) {
			extendnamewithdot = name.substring(dotidx);
		}
		return extendnamewithdot;
	}

	public static String getFileExtendNameWithDot(File file) {
		return getFileExtendNameWithDot(file.getName());
	}

	public static String getFileWithoutExtendName(String filename) {
		String extendname = getFileExtendNameWithDot(filename);
		return filename.substring(0, filename.length() - extendname.length());

	}

	public static void writeBinaryToFile(String filename, byte[] bytes)
			throws Exception {

		File tmpfile = new File(filename);
		System.out.println("杈撳嚭璺緞鏄�==="+filename);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(tmpfile);
			fos.write(bytes);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					logger.error(e.getMessage(),e);
				}
			}
		}

	}

	public static byte[] getBinaryFromFile(String fileabsolutepath)
			throws IOException {
		File file = new File(fileabsolutepath);
		byte[] b = new byte[(int) file.length()];
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			is.read(b);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return b;
	}

	public static void main(String[] args) {
		try {
			byte[] t = IOTool.getBinaryFromFile("http://www.baidu.com");
			System.out.println(new String(t, "gbk"));
		} catch (Exception ex) {
		}
	}

	public static void testUrlconnection() {
		try {
			URLConnection uco = null;
			for (int i = 0; i < 10; i++) {
				URL url = new URL("http://localhost:8083/hg/j.jsp");
				URLConnection uc = url.openConnection();
				System.out.println(uc == uco);
				uco = uc;

				InputStream is = uc.getInputStream();
				transferIOStream(is, null);

				System.out.println(uc + "" + is);
			}

		} catch (IOException ex) {
		}
	}

	public static ObjectInputStream getURLObjectInputStream(String urlstring)
			throws Exception {
		URL url = new URL(urlstring);
		// url.openConnection()
		InputStream is = url.openStream();
		// System.out.println(is);
		ObjectInputStream ois = new ObjectInputStream(is);
		return ois;
	}

	public static void transferIOStream(InputStream is, OutputStream os)
			throws IOException {
		int bytesRead = 0;
		byte[] buffer = new byte[2048];
		while ((bytesRead = is.read(buffer, 0, 2048)) != -1) {
			if (os != null) {
				os.write(buffer, 0, bytesRead);
			}
		}
	}

	public static void transferIOStream(InputStream is, OutputStream os,
			int buffersize) throws IOException {
		int bytesRead = 0;
		byte[] buffer = new byte[buffersize];
		while ((bytesRead = is.read(buffer, 0, buffersize)) != -1) {
			if (os != null) {
				os.write(buffer, 0, bytesRead);
			}
		}
	}

}
