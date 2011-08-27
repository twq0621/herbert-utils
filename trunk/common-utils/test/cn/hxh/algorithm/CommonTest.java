package cn.hxh.algorithm;

import junit.framework.TestCase;

public class CommonTest extends TestCase {

	private LinkList l1;
	private LinkList l2;

	@Override
	protected void setUp() throws Exception {
		l1 = new LinkList("t1");
		l1.next = new LinkList("t2");
		l2 = new LinkList("p1");
		l2.next = new LinkList("p2");
		LinkList end = new LinkList("t3");
		l1.next.next = end;
		l2.next.next = end;
	}

	public void testJoint() {
		boolean ret = CheckListJoin.isListJoint(l1, l2);
		assertEquals(ret, true);
	}
	
	public void testCount1() {
		int ret = Count1.count(23);
		assertEquals(ret, 4);
	}

	public void testCount2() {
		int ret = Count1.count2(23);
		assertEquals(ret, 4);
	}

}
