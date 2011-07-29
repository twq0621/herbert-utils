package cn.hxh.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hxh.script.GameScriptEngineManager;

public class AlgorithmTest extends TestCase {

	private static final Logger logger = LoggerFactory.getLogger(GameScriptEngineManager.class);

	public void testBinarySearchDownlimit() {
		List<Integer> countList = new ArrayList<Integer>();
		countList.add(5);
		countList.add(200);
		countList.add(100);
		countList.add(0);
		//排序
		Collections.sort(countList);
		logger.info(countList.toString());
		int number = 5;
		//二叉搜索
		int resIndex = MyCollectionUtils.binarySearchDownlimit(countList, number);
		assertEquals(1, resIndex);
		number = 2;
		resIndex = MyCollectionUtils.binarySearchDownlimit(countList, number);
		assertEquals(0, resIndex);
		number = 150;
		resIndex = MyCollectionUtils.binarySearchDownlimit(countList, number);
		assertEquals(2, resIndex);
		number = 200;
		resIndex = MyCollectionUtils.binarySearchDownlimit(countList, number);
		assertEquals(3, resIndex);
		number = 250;
		resIndex = MyCollectionUtils.binarySearchDownlimit(countList, number);
		assertEquals(3, resIndex);
		number = -5;
		resIndex = MyCollectionUtils.binarySearchDownlimit(countList, number);
		assertEquals(0, resIndex);
		number = 0;
		resIndex = MyCollectionUtils.binarySearchDownlimit(countList, number);
		assertEquals(0, resIndex);
	}

	public void testBinarySearchUplimit() {
		List<Integer> countList = new ArrayList<Integer>();
		countList.add(5);
		countList.add(200);
		countList.add(100);
		countList.add(0);
		//排序
		Collections.sort(countList);
		logger.info(countList.toString());
		int number = 5;
		int resIndex = MyCollectionUtils.binarySearchUplimit(countList, number);
		assertEquals(1, resIndex);
		number = 2;
		resIndex = MyCollectionUtils.binarySearchUplimit(countList, number);
		assertEquals(1, resIndex);
		number = 150;
		resIndex = MyCollectionUtils.binarySearchUplimit(countList, number);
		assertEquals(3, resIndex);
		number = 200;
		resIndex = MyCollectionUtils.binarySearchUplimit(countList, number);
		assertEquals(3, resIndex);
		number = 250;
		resIndex = MyCollectionUtils.binarySearchUplimit(countList, number);
		assertEquals(3, resIndex);
		number = -5;
		resIndex = MyCollectionUtils.binarySearchUplimit(countList, number);
		assertEquals(0, resIndex);
		number = 0;
		resIndex = MyCollectionUtils.binarySearchUplimit(countList, number);
		assertEquals(0, resIndex);
	}

}
