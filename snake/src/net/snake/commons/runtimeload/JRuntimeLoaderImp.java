package net.snake.commons.runtimeload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import org.apache.log4j.Logger;

public class JRuntimeLoaderImp implements CacheUpdateListener, JRuntimeLoader {
	public JRuntimeLoaderImp() {
	}

	private static Logger logger = Logger.getLogger(JRuntimeLoaderImp.class);

	public static DateFormat YMDHMS_DATEFORMAT = new SimpleDateFormat("yyyyMMddHHmm");

	volatile ClassLoader classLoader;
	String runtimeDir;// 脚本运行时路径
	String scriptRefreshPath;// 该脚本路径

	String appname;
	String cachename;

	/**
	 * 设定运行时的jar包目录
	 * 
	 * @param runtimeDir
	 */
	public void setRuntimeDir(String runtimeDir) {
		this.runtimeDir = runtimeDir;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	/**
	 * 设定原始的jar包路径
	 * 
	 * @param runtimeDir
	 */
	public void setScriptRefreshPath(String scriptRefreshPath) {
		this.scriptRefreshPath = scriptRefreshPath;
	}

	public Object newObject(String classname) throws Exception {
		Object obj = classLoader.loadClass(classname).newInstance();
		return obj;
	}

	@Override
	public Object newObject(String classname, Object... args) throws Exception {
		Class<?> clazz = classLoader.loadClass(classname);
		Constructor<?> constructor = clazz.getConstructor(getClassType(args));
		return constructor.newInstance(args);
	}

	public Object runScript(String classname, String method, Object... args) throws Exception {
		Object obj = classLoader.loadClass(classname).newInstance();
		return invokeObjMethod(obj, method, args);
	}

	private URLClassLoader load(URL url) throws Exception {
		URLClassLoader loader = new URLClassLoader(new URL[] { url }, this.getClass().getClassLoader());
		return loader;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public void setCachename(String cachename) {
		this.cachename = cachename;
	}

	@Override
	public String getAppname() {
		return appname;
	}

	@Override
	public String getCachename() {
		return cachename;
	}

	@Override
	public void reload() {

		if (runtimeDir == null || scriptRefreshPath == null) {
			this.classLoader = getClass().getClassLoader();
			return;
		}

		InputStream iStream = null;
		OutputStream oStream = null;

		File runtimedir = new File(runtimeDir);
		if (!runtimedir.exists()) {
			runtimedir.mkdir();
		}

		File scriptRefreshPathfile = new File(scriptRefreshPath);
		if (!scriptRefreshPathfile.exists()) {
			this.classLoader = getClass().getClassLoader();
			return;
		}
		final String extName = scriptRefreshPathfile.getName();
		File[] timedjar = runtimedir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(extName)) {
					return true;
				}
				return false;
			}
		});
		for (int i = 0; i < timedjar.length; i++) {
			timedjar[i].delete();
		}
		try {
			File file = new File(runtimeDir, YMDHMS_DATEFORMAT.format(new Date()) + "_" + scriptRefreshPathfile.getName());
			oStream = new FileOutputStream(file);
			iStream = new FileInputStream(scriptRefreshPathfile);
			transferIOStream(iStream, oStream);
			URLClassLoader urlclassloader = load(file.toURI().toURL());
			this.classLoader = urlclassloader;
			// } else {
			// this.classLoader = getClass().getClassLoader();
			// }
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			logger.error("loader extern jar file fail ,has use default loader");
			this.classLoader = getClass().getClassLoader();
		} finally {
			if (iStream != null) {
				try {
					iStream.close();
				} catch (Exception e2) {
				}
			}
			if (oStream != null) {
				try {
					oStream.close();
				} catch (Exception e2) {
				}
			}
		}

	}

	private static Object invokeObjMethod(Object methodtarget, String methodname, Object... arg) throws Exception {
		Class<?>[] cs = getClassType(arg);
		Method method = methodtarget.getClass().getMethod(methodname, cs);
		Object result = method.invoke(methodtarget, arg);
		return result;
	}

	private static Class<?>[] getClassType(Object... arg) {
		Class<?>[] cs = new Class[arg.length];
		for (int i = 0; i < arg.length; i++) {
			cs[i] = primitiveClass(arg[i]);
		}
		return cs;
	}

	private static Class<?> primitiveClass(Object object) {
		Class<?> cls = object.getClass();
		if (cls == Integer.class) {
			return int.class;
		} else if (cls == Short.class) {
			return short.class;
		} else if (cls == Long.class) {
			return long.class;
		} else if (cls == Float.class) {
			return float.class;
		} else if (cls == Double.class) {
			return double.class;
		} else if (cls == Byte.class) {
			return byte.class;
		} else {
			return cls;
		}
	}

	public void afterPropertiesSet() throws Exception {
		reload();
	}

	public static void transferIOStream(InputStream is, OutputStream os) throws IOException {
		int bytesRead = 0;
		byte[] buffer = new byte[2048];
		while ((bytesRead = is.read(buffer, 0, 2048)) != -1) {
			if (os != null) {
				os.write(buffer, 0, bytesRead);
			}
		}
	}

}
