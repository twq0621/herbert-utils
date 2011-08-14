package cn.hxh.algorithm;

import junit.framework.TestCase;
import cn.hxh.algorithm.tree.BinaryTree;
import cn.hxh.algorithm.tree.BinaryTreeMaxLen;

public class TreeTest extends TestCase {

	private BinaryTree bt;

	public void testBinaryMaxLength() {
		BinaryTreeMaxLen len = new BinaryTreeMaxLen();
		int ret = len.findMaxLength(bt);
		assertEquals(ret, 3);
	}

	public void testBinaryTreeMaxLen() {
		BinaryTreeMaxLen len = new BinaryTreeMaxLen();
		int ret = len.findMaxLeaf(bt);
		assertEquals(ret, 4);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		BinaryTree root = new BinaryTree();
		root.setLeftNode(new BinaryTree());
		root.setRightNode(new BinaryTree());
		BinaryTree left1 = root.getLeftNode();
		BinaryTree right1 = root.getRightNode();
		left1.setLeftNode(new BinaryTree());
		left1.setRightNode(new BinaryTree());
		BinaryTree left2 = left1.getLeftNode();
		left2.setLeftNode(new BinaryTree());
		left2.setRightNode(new BinaryTree());
		bt = root;
	}

}
