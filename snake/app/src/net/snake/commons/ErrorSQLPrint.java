package net.snake.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;


public class ErrorSQLPrint {
	private static final Logger logger = Logger.getLogger(ErrorSQLPrint.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static void println(String sql) {
		String date = sdf.format(new Date());
		String path = "errorsql/" + date + ".txt";
		File f = new File(path);
		FileOutputStream fos = null;
		try {
			sql = DateFormatUtil.getNowStringDate() + "---" + sql + "\r\n";
			fos = new FileOutputStream(f, true);
			fos.write(sql.getBytes());
			fos.flush();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (fos == null) {
				return;
			}
			try {
				fos.close();
			} catch (IOException e) {
			}
		}
	}
}
