package cn.hxh.thread;

import java.util.concurrent.ForkJoinPool;

import junit.framework.TestCase;

public class ForkJoinTest extends TestCase {

	public void testFibonacci() {
		int index = 5;
		int nThreads = 10;
		Fibonacci fibonacci = new Fibonacci(index);
		ForkJoinPool fjPool = new ForkJoinPool(nThreads);
		fjPool.invoke(fibonacci);
		assertEquals(Integer.valueOf(8), fibonacci.getRawResult());
	}

	public void testFibonacciTrue() {
		int index = 11;
		int nThreads = 10;
		Fibonacci fibonacci = new Fibonacci(index);
		ForkJoinPool fjPool = new ForkJoinPool(nThreads);
		fjPool.invoke(fibonacci);
		assertEquals(Integer.valueOf(144), fibonacci.getRawResult());
	}

	public void testBinarySearch() {
		int[] toSearch = new int[] { 5, 6, 10, 15, 23, 35, 45, 55, 61, 100,
				120, 145, 178, 198, 210, 335, 1200, 2501 };
		int key = 23;
		int nThreads = 10;
		BiSearchWithForkJoin search = new BiSearchWithForkJoin(toSearch, key);
		ForkJoinPool fjPool = new ForkJoinPool(nThreads);
		fjPool.invoke(search);
		assertEquals(5, search.result + 1);
		key = 65;
		search = new BiSearchWithForkJoin(toSearch, key);
		fjPool = new ForkJoinPool(nThreads);
		fjPool.invoke(search);
		assertEquals(-1, search.result);
		key = 100;
		search = new BiSearchWithForkJoin(toSearch, key);
		fjPool = new ForkJoinPool(nThreads);
		fjPool.invoke(search);
		assertEquals(9, search.result);
	}

}
