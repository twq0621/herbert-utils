package net.snake.gamemodel.exception;
import org.apache.log4j.Logger;


/**
 * 数据访问层抛出的自定义异常类
 * 
 * @author benchild
 * 
 */
public class DataException extends Exception {

	private static Logger logger = Logger.getLogger(DataException.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String classname; // the name of the class
	private String method; // the name of the method
	private String message; // a detailed message
	private Exception e = null; // the exception which was caught
	private String separator = "\n"; // line separator

	public DataException(String classname, String method, String message, Exception e) {
		this.classname = classname;// 捕捉到这个错误的类的名字
		this.method = method;// 捕捉到这个错误的方法的名字。
		this.message = message;// 用来描述整个事件的情况。
		this.e = e;
		logger.error(e.getMessage(), e);
	}

	public String getMessage() {
		if (message == null) {
			return "";
		}
		return message;
	}

	/**
	 * 产生一个包含在异常类中存储的数据的一个回溯。使用newline标示作为分隔符
	 * 
	 * @return
	 */
	public String traceBack() {
		return traceBack("\n");
	}

	/**
	 * 和上一个其实是相同的东西，使用的分隔符可以自己定义。
	 * 
	 * @param sep
	 * @return
	 */
	public String traceBack(String sep) {
		this.separator = sep;
		String text = line("Calling sequence (top to bottom)");
		text += line("Class/Method: " + classname + "/" + method);
		text += line("Message : " + message);
		logger.error(text + "/n" + this.e.getStackTrace());
		// this.logger.error(e.getMessage(),e);
		return text;
	}

	private String line(String s) {
		return s + separator;
	}
}
