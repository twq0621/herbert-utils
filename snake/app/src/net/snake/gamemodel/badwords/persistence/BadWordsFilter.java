package net.snake.gamemodel.badwords.persistence;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.snake.commons.dbversioncache.CacheUpdateListener;
import net.snake.gamemodel.badwords.bean.BadWords;
import net.snake.ibatis.SystemFactory;

import org.apache.log4j.Logger;

public class BadWordsFilter implements CacheUpdateListener {
	private static Logger logger = Logger.getLogger(BadWordsFilter.class);

	private volatile List<String> arrt = new ArrayList<String>();// 文字
	private volatile Node rootNode = new Node('R');
	private BadWordsDAO badWordsDAO = new BadWordsDAO(SystemFactory.getGamedataSqlMapClient());

	private static BadWordsFilter filter;

	private BadWordsFilter() {
	}

	public static BadWordsFilter getInstance() {
		if (filter == null)
			filter = new BadWordsFilter();
		return filter;
	}

	/**
	 * 过滤敏感词
	 * 
	 * @param content
	 * @return
	 */
	public String badWordstFilterStr(String content) {
		char[] chars = content.toCharArray();
		Node node = rootNode;
		StringBuilder buffer = new StringBuilder();
		List<String> badList = new ArrayList<String>();
		int a = 0;
		while (a < chars.length) {
			node = findNode(node, chars[a]);
			if (node == null) {
				node = rootNode;
				a = a - badList.size();
				if (badList.size() > 0) {
					badList.clear();
				}
				buffer.append(chars[a]);
			} else if (node.flag == 1) {
				badList.add(String.valueOf(chars[a]));
				for (int i = 0; i < badList.size(); i++) {
					buffer.append("*");
				}
				node = rootNode;
				badList.clear();
			} else {
				badList.add(String.valueOf(chars[a]));
				if (a == chars.length - 1) {
					for (int i = 0; i < badList.size(); i++) {
						buffer.append(badList.get(i));
					}
				}
			}
			a++;
		}
		return buffer.toString();
	}

	private void initWords() {
		// try {
		// BadWords badwords = badWordsDAO.selectById(1);
		// BufferedReader br = new BufferedReader(new
		// StringReader(badwords.getBadwords()));
		// String line = null;
		// StringBuilder tempText = new StringBuilder();
		// while ((line = br.readLine()) != null) {
		// if (line.trim().equals(""))
		// continue;
		// tempText.append(line.trim());
		// }
		// getNewString(tempText.toString());
		// br.close();
		// } catch (Exception e) {
		//			logger.error("initWords()", e); //$NON-NLS-1$
		// }
		Set<String> wordset = new HashSet<String>();
		StringBuilder builder = new StringBuilder();
		try {
			List<BadWords> words = badWordsDAO.selectAll();
			for (Iterator<BadWords> iterator = words.iterator(); iterator.hasNext();) {
				BadWords txt = iterator.next();
				StringReader sr = new StringReader(txt.getBadwords() + ",");
				BufferedReader reader = new BufferedReader(sr);
				String line = reader.readLine();
				while (line != null) {
					builder.append(line);
					line = reader.readLine();
				}
				reader.close();
				sr.close();
			}
			String badwords = builder.toString();
			String[] wordArr = badwords.split(",");
			for (int i = 0; i < wordArr.length; i++) {
				if (wordArr[i].equals("")) {
					continue;
				}
				wordset.add(wordArr[i]);
			}

			builder = new StringBuilder();
			for (Iterator<String> iterator = wordset.iterator(); iterator.hasNext();) {
				String word = iterator.next();
				builder.append(word);
				builder.append(",");
			}
			getNewString(builder.toString());
		} catch (Exception e) {
			logger.error("initWords()", e); //$NON-NLS-1$
		}
	}

	private void createTree() {
		for (String str : arrt) {
			char[] chars = str.toCharArray();
			if (chars.length > 0)
				insertNode(rootNode, chars, 0);
		}
	}

	private void insertNode(Node node, char[] cs, int index) {
		Node n = findNode(node, cs[index]);
		if (n == null) {
			n = new Node(cs[index]);
			node.nodes.put(String.valueOf(cs[index]), n);
		}

		if (index == (cs.length - 1))
			n.flag = 1;

		index++;
		if (index < cs.length)
			insertNode(n, cs, index);
	}

	private void getNewString(String str) {
		if (str == null) {
			return;
		}

		String tempstr[] = str.split(",");
		for (int i = 0; i < tempstr.length; i++) {
			String ss = tempstr[i];

			arrt.add(ss.replace("\"", "").trim());

		}
	}

	/**
	 * 是否有坏字
	 * 
	 * @param content
	 * @return true有坏字 false 没有
	 */
	public boolean hashBadWords(String content) {
		char[] chars = content.toCharArray();
		Node node = rootNode;
		StringBuilder buffer = new StringBuilder();
		List<String> word = new ArrayList<String>();
		int a = 0;
		while (a < chars.length) {
			node = findNode(node, chars[a]);
			if (node == null) {
				node = rootNode;
				a = a - word.size();
				buffer.append(chars[a]);
				word.clear();
			} else if (node.flag == 1) {
				node = null;
				return true;
			} else {
				word.add(String.valueOf(chars[a]));
			}
			a++;
		}
		chars = null;
		word.clear();
		word = null;
		return false;
	}

	private Node findNode(Node node, char c) {
		// for (Iterator iterator = node.nodes.keySet().iterator();
		// iterator.hasNext();) {
		// String str = (String) iterator.next();
		// }
		return node.nodes.get(String.valueOf(c));
	}

	private static class Node {
		// public char c;
		public int flag;
		public HashMap<String, Node> nodes = new HashMap<String, Node>();

		public Node(char c) {
			this(c, 0);
		}

		public Node(char c, int flag) {
			// this.c = c;
			this.flag = flag;
		}
	}

	@Override
	public void reload() {
		rootNode = new Node('R');
		arrt.clear();
		initWords();
		createTree();
	}

	@Override
	public String getAppname() {
		return net.snake.consts.GameConstant.GAME_SERVER_NAME;
	}

	@Override
	public String getCachename() {
		return "badword";
	}
}
