package cn.hxh.fel;

import junit.framework.TestCase;

import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;

public class FelTest extends TestCase {

	private FelEngine fel;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fel = new FelEngineImpl();
	}

	public void testSimple() {
		Object result = fel.eval("5000*12+7500");
		System.out.println(result);
	}

	public void testParameter() {
		FelContext ctx = fel.getContext();
		ctx.set("单价", 5000);
		ctx.set("数量", 12);
		ctx.set("运费", 8000);
		Object result = fel.eval("单价*数量+运费");
		System.out.println(result);
	}

	public void testInvokJava() {
		FelContext ctx = fel.getContext();
		ctx.set("out", System.out);
		fel.eval("out.println('Hello Everybody'.substring(6))");
	}
}
