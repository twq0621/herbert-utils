package cn.hxh.algorithm.tree;

/**
 * 二叉树节点中的最长距离
 * 
 * @author Administrator
 * 
 */
public class BinaryTreeMaxLen {

	/**
	 * 根节点到叶节点的最大距离
	 * 
	 * @param tree
	 * @return
	 */
	public int findMaxLength(BinaryTree tree) {
		int ret = 0;
		int maxLeft = 0;
		int maxRight = 0;
		if (tree.getLeftNode() != null) {
			maxLeft = 1 + findMaxLength(tree.getLeftNode());
		}
		if (tree.getRightNode() != null) {
			maxRight = 1 + findMaxLength(tree.getRightNode());
		}
		ret = Math.max(maxLeft, maxRight);
		return ret;
	}

	/**
	 * 二叉树节点中的最长距离
	 * @param tree
	 * @return
	 */
	public int findMaxLeaf(BinaryTree tree) {
		int ret = 0;
		int maxLeft = 0;
		int maxRight = 0;
		int maxLeafLeft = 0;
		int maxLeafRight = 0;
		if (tree.getLeftNode() != null) {
			maxLeft = findMaxLeaf(tree.getLeftNode());
			maxLeafLeft = 1 + findMaxLength(tree.getLeftNode());
		}
		if (tree.getRightNode() != null) {
			maxRight = findMaxLeaf(tree.getRightNode());
			maxLeafRight = 1 + findMaxLength(tree.getRightNode());
		}
		int max3 = maxLeafLeft + maxLeafRight;
		ret = Math.max(maxLeft, maxRight);
		ret = Math.max(ret, max3);
		return ret;
	}
}
