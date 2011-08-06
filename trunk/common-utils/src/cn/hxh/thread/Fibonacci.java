package cn.hxh.thread;

import java.util.concurrent.RecursiveTask;

public class Fibonacci extends RecursiveTask<Integer> {
	private static final long serialVersionUID = 5959563300624935536L;
	final int n;

	Fibonacci(int n) {
		this.n = n;
	}

	private int compute(int small) {
		final int[] results = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
		return results[small];
	}

	public Integer compute() {
		if (n <= 10) {
			return compute(n);
		}
		Fibonacci f1 = new Fibonacci(n - 1);
		Fibonacci f2 = new Fibonacci(n - 2);
		System.out.println("fork new thread for " + (n - 1));
		f1.fork();
		System.out.println("fork new thread for " + (n - 2));
		f2.fork();
		return f1.join() + f2.join();
	}
}
