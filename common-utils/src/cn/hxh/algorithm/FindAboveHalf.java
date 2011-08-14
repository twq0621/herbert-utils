package cn.hxh.algorithm;

/**
 * 寻找发帖“水王”
★
Tango 是微软亚洲研究院的一个试验项目。研究院的员工和实习生们都很喜欢在Tango
上面交流灌水。传说，Tango 有一大“水王”，他不但喜欢发贴，还会回复其他ID 发的每个帖
子。坊间风闻该“水王”发帖数目超过了帖子总数的一半。如果你有一个当前论坛上所有帖子
（包括回帖）的列表，其中帖子作者的ID 也在表中，你能快速找出这个传说中的Tango 水
王吗？
 * @author hexuhui
 *
 */
public class FindAboveHalf {

	/**
	 * 采用不同的值对消的方法，减掉之后，最后剩下的肯定是数量大于半数以上的值
	 * @param IDs
	 * @param N
	 * @return
	 */
	public static <T> T find(T[] IDs, int N) {
		T candidate = null;
		int nTimes, i;
		for (i = nTimes = 0; i < N; i++) {
			if (nTimes == 0) {
				candidate = IDs[i];
				nTimes = 1;
			} else {
				if (candidate == IDs[i]) {
					nTimes++;
				} else {
					nTimes--;
				}
			}
		}
		return candidate;
	}

}
