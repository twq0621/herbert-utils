package cn.hxh;

public class ExceptionTest {

	private static final int count = 1000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		int index = count;
		while (index-- > 0) {
			try {
				work();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void work() {
		String value = null;
		value.length();
	}

}
