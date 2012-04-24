package net.snake.ant.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Pattern;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.netio.message.process.MsgProcessor;

import org.apache.log4j.Logger;

/**
 * 扫描指定包（包括jar）下的class文件 <br>
 * <a href="http://sjsky.iteye.com">http://sjsky.iteye.com</a>
 * 
 * @author michael
 */
public class ClassPathScanHandler {

	/**
	 * logger
	 */
	private static final Logger logger = Logger.getLogger(ClassPathScanHandler.class);
	ClassScanCondition classScanCondition;

	/**
	 * 无参构造器，默认是排除内部类、并搜索符合规则
	 */
	public ClassPathScanHandler() {
		classScanCondition = new ClassScanCondition();
	}

	public ClassPathScanHandler(ClassScanCondition con) {
		classScanCondition = con;
	}

	/**
	 * 扫描包
	 * 
	 * @param basePackage
	 *            基础包
	 * @param recursive
	 *            是否递归搜索子包
	 * @return Set
	 */
	public Set<Class<?>> getPackageAllClasses(String basePackage, boolean recursive) {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		String packageName = basePackage;
		if (packageName.endsWith(".")) {
			packageName = packageName.substring(0, packageName.lastIndexOf('.'));
		}
		String package2Path = packageName.replace('.', '/');
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(package2Path);
			while (dirs.hasMoreElements()) {
				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				if ("file".equals(protocol)) {
					logger.info("扫描file类型的class文件....");
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					doScanPackageClassesByFile(classes, packageName, filePath, recursive);
				} else if ("jar".equals(protocol)) {
					doScanPackageClassesByJar(packageName, url, recursive, classes);
				}
			}
		} catch (IOException e) {
			logger.error("IOException error:", e);
		}

		return classes;
	}

	/**
	 * 以jar的方式扫描包下的所有Class文件<br>
	 * 
	 * @param basePackage
	 *            eg：michael.utils.
	 * @param url
	 * @param recursive
	 * @param classes
	 */
	private void doScanPackageClassesByJar(String basePackage, URL url, final boolean recursive, Set<Class<?>> classes) {
		String packageName = basePackage;
		String package2Path = packageName.replace('.', '/');
		JarFile jar;
		try {
			jar = ((JarURLConnection) url.openConnection()).getJarFile();
			Enumeration<JarEntry> entries = jar.entries();
			while (entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				if (!name.startsWith(package2Path) || entry.isDirectory()) {
					continue;
				}

				// 判断是否递归搜索子包
				if (!recursive && name.lastIndexOf('/') != package2Path.length()) {
					continue;
				}
				// 判断是否过滤 inner class
				if (classScanCondition.isExcludeInner() && name.indexOf('$') != -1) {
					// logger.info("exclude inner class with name:" + name);
					continue;
				}
				String classSimpleName = name.substring(name.lastIndexOf('/') + 1);
				// 判定是否符合过滤条件
				if (this.filterClassName(classSimpleName)) {
					String className = name.replace('/', '.');
					className = className.substring(0, className.length() - 6);
					this.loadClass(classes, packageName, className);
				}
			}
		} catch (IOException e) {
			logger.error("IOException error:", e);
		}
	}

	/**
	 * 以文件的方式扫描包下的所有Class文件
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 */
	private void doScanPackageClassesByFile(Set<Class<?>> classes, String packageName, String packagePath, boolean recursive) {
		File dir = new File(packagePath);
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		final boolean fileRecursive = recursive;
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义文件过滤规则
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return fileRecursive;
				}
				String filename = file.getName();
				if (classScanCondition.isExcludeInner() && filename.indexOf('$') != -1) {
					// logger.info("exclude inner class with name:" + filename);
					return false;
				}
				return filterClassName(filename);
			}
		});
		for (File file : dirfiles) {
			if (file.isDirectory()) {
				doScanPackageClassesByFile(classes, packageName + "." + file.getName(), file.getAbsolutePath(), recursive);
			} else {
				String className = file.getName().substring(0, file.getName().length() - 6);
				this.loadClass(classes, packageName, className);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadClass(Set<Class<?>> classes, String packageName, String className) {
		String name = packageName + '.' + className;
		try {
			Class c = Thread.currentThread().getContextClassLoader().loadClass(name);
			if (c.getGenericSuperclass() == null) {
				return;
			}
			String superName = c.getGenericSuperclass().toString().substring(6);
			for (int i = 0; i < classScanCondition.getExtendClass().length; i++) {
				Class exc = classScanCondition.getExtendClass()[i];
				if (!superName.equals(exc.getCanonicalName())) {
					continue;
				}
				if (c.getAnnotation(MsgCodeAnn.class) == null) {
					continue;
				}
				classes.add(c);
			}
		} catch (ClassNotFoundException e) {
			logger.error(name + "---not found", e);
		}
	}

	/**
	 * 根据过滤规则判断类名
	 * 
	 * @param className
	 * @return
	 */
	private boolean filterClassName(String className) {
		if (!className.endsWith(".class")) {
			return false;
		}
		if (null == classScanCondition.getClassFilters() || classScanCondition.getClassFilters().isEmpty()) {
			return true;
		}
		String tmpName = className.substring(0, className.length() - 6);
		boolean flag = false;
		for (String str : classScanCondition.getClassFilters()) {
			String tmpreg = "^" + str.replace("*", ".*") + "$";
			Pattern p = Pattern.compile(tmpreg);
			if (p.matcher(tmpName).find()) {
				flag = true;
				break;
			}
		}
		return (classScanCondition.isCheckInOrEx() && flag) || (!classScanCondition.isCheckInOrEx() && !flag);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 自定义过滤规则
		List<String> classFilters = new ArrayList<String>();
		// classFilters.add("File*");
		ClassScanCondition con = new ClassScanCondition();
		con.setCheckInOrEx(true);
		con.setExcludeInner(true);
		con.setClassFilters(classFilters);
		con.setExtendClass(new Class[] { MsgProcessor.class, CharacterMsgProcessor.class });
		// 创建一个扫描处理器，排除内部类 扫描符合条件的类
		ClassPathScanHandler handler = new ClassPathScanHandler(con);

		Set<Class<?>> calssList = handler.getPackageAllClasses("net.snake", true);
		Properties pro = new Properties();
		for (Class<?> cla : calssList) {
			MsgCodeAnn ann = cla.getAnnotation(MsgCodeAnn.class);
			if (ann.accessLimit() == 0) {
				pro.put(ann.msgcode(), cla.getName());
			} else {
				pro.put(ann.msgcode(), cla.getName() + "," + ann.accessLimit());
			}
		}
		CleanProcessor.sortProcessor(pro);
	}
}