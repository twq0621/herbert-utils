package net.snake.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;


public class StringUtil {
	private static Logger logger = Logger.getLogger(StringUtil.class);

	public static final String EMPTY_STRING = "";
	public static final int ZERO = 0;

	/**
	 * 为0的整数对象被当作空
	 */
	public static boolean isEmptyOrZero(Integer i) {
		return (i == null || ZERO == i.intValue());
	}

	public static boolean isEmpty(Integer i) {
		return (i == null);
	}

	/**
	 * 空串被认为是空
	 */
	public static boolean isEmpty(String s) {
		return (s == null || EMPTY_STRING.equals(s));
	}

	public static boolean isNotEmpty(String s) {
		return (s != null && !EMPTY_STRING.equals(s));
	}

	public static boolean isNotEmpty(Object s) {
		return (s != null && !EMPTY_STRING.equals(s));
	}

	/**
	 * string转成int数组
	 * 
	 * @param numbers
	 * @return
	 */
	public static int[] string_To_int(String[] numbers) {

		int[] ia = new int[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			ia[i] = Integer.parseInt(numbers[i]);
		}
		return ia;

	}

	/**
	 * 向文本文件中写入内容或追加新内容,如果append为true则直接追加新内容,<br>
	 * 如果append为false则覆盖原来的内容<br>
	 * 
	 * @param path
	 * @param content
	 * @param append
	 */
	public void writeFile(String path, String content, boolean append) {
		File writefile = new File(path);
		FileOutputStream fw = null;
		try {
			// 如果文本文件不存在则创建它
			if (writefile.exists() == false) {
				writefile.createNewFile();
			}

			fw = new FileOutputStream(writefile, append);
			fw.write(content.getBytes());
			fw.flush();

		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
			}
		}
	}

}
