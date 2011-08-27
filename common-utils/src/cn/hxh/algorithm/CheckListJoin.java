package cn.hxh.algorithm;

/**
 * 给出两个单向链表的头指针（如图 3-8 所示），比如 h1、h2，判断这两个链表是否 相交。这里为了简化问题，我们假设两个链表均不带环。
 * 
 * @author Administrator
 * 
 */
public class CheckListJoin {
	public static boolean isListJoint(LinkList l1, LinkList l2) {
		boolean ret = false;
		if (l1 == l2) {
			ret = true;
			return ret;
		}
		while (l1.next != null) {
			l1 = l1.next;
		}
		while (l2.next != null) {
			l2 = l2.next;
		}
		if (l1 == l2) {
			ret = true;
		}
		return ret;
	}
}
