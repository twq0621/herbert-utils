package net.snake.commons;

import java.lang.reflect.InvocationTargetException;

public class BeanUtils {

	public static void copyProperties(Object fromObj,Object toObj){
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(toObj,fromObj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
