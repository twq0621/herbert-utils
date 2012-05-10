package net.snake.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-7-1 涓嬪崍03:15:06
 */

public class ReflecTools {
	public Object copy(Object obj) throws IllegalArgumentException,
	SecurityException, InstantiationException, IllegalAccessException,
	InvocationTargetException, NoSuchMethodException {

// 鑾峰緱瀵硅薄鐨勭被鍨�
Class<?> classType = obj.getClass();
System.out.println("璇ュ璞＄殑绫诲瀷鏄細" + classType.toString());

// 閫氳繃榛樿鏋勯�鏂规硶鍘诲垱寤轰竴涓柊鐨勫璞★紝getConstructor鐨勮鍏跺弬鏁板喅瀹氳皟鐢ㄥ摢涓瀯閫犳柟娉�
Object objectCopy = classType.getConstructor(new Class[] {})
		.newInstance(new Object[] {});

// 鑾峰緱瀵硅薄鐨勬墍鏈夊睘鎬�
Field[] fields = classType.getDeclaredFields();

for (int i = 0; i < fields.length; i++) {
	// 鑾峰彇鏁扮粍涓搴旂殑灞炴�
	Field field = fields[i];

	String fieldName = field.getName();
	String stringLetter = fieldName.substring(0, 1).toUpperCase();

	// 鑾峰緱鐩稿簲灞炴�鐨刧etXXX鍜宻etXXX鏂规硶鍚嶇О
	String getName = "get" + stringLetter + fieldName.substring(1);
	String setName = "set" + stringLetter + fieldName.substring(1);

	// 鑾峰彇鐩稿簲鐨勬柟娉�
	Method getMethod = classType.getMethod(getName, new Class[] {});
	Method setMethod = classType.getMethod(setName,
			new Class[] { field.getType() });

	// 璋冪敤婧愬璞＄殑getXXX锛堬級鏂规硶
	Object value = getMethod.invoke(obj, new Object[] {});
	System.out.println(fieldName + " :" + value);

	// &lt; < 灏忎簬鍙�
	// &gt; > 澶т簬鍙�
	// &amp; & 鍜�
	// &apos; ' 鍗曞紩鍙�
	// &quot; " 鍙屽紩鍙�

	if (value instanceof String) {
		value = ((String) value).replace("<", "&lt;");
		value = ((String) value).replace(">", "&gt;");
		value = ((String) value).replace("'", "&apos;");
		value = ((String) value).replace("\"", "&quot;");
		value = ((String) value).replace("&", "&amp;;");
		// System.out.println("String鏇挎崲===="+value);
	}

	// 璋冪敤鎷疯礉瀵硅薄鐨剆etXXX锛堬級鏂规硶
	setMethod.invoke(objectCopy, new Object[] { value });

}

return objectCopy;
}
}
