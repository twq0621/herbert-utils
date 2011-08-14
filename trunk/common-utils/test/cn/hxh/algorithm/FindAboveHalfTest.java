package cn.hxh.algorithm;

import junit.framework.TestCase;

public class FindAboveHalfTest extends TestCase {

	private Integer[] Ids;

	public void test1() {
		int ret = FindAboveHalf.find(Ids, 7);
		assertEquals(ret, 10);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		Ids = new Integer[] { 10, 4, 5, 10, 56, 10, 10 };
	}

}
