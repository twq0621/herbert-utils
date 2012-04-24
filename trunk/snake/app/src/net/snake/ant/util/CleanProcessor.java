package net.snake.ant.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

public class CleanProcessor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties pro = new Properties();
		try {
			// //
			// F:/workspace/game_app/src-processor/processorconfig/processors.properties
			InputStream is = new FileInputStream("f:/a.properties");
			pro.load(is);
			sortProcessor(pro);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void sortProcessor(Properties pro) {
		Iterator<Entry<Object, Object>> it = pro.entrySet().iterator();
		List<Integer> list = new ArrayList<Integer>();
		while (it.hasNext()) {
			Entry<Object, Object> entry = it.next();
			list.add(Integer.parseInt(entry.getKey().toString()));
		}
		Collections.sort(list);
		for (Integer key : list) {
			String name = pro.get(key).toString();
			String names[] = name.split(",");
			String acc = null;
			if (names.length == 2) {
				acc = names[1];
			}
		}
	}

}
