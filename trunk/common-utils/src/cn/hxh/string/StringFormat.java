package cn.hxh.string;

public class StringFormat {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder("00100");
		if (sb.charAt(2) == '1') {
			sb.replace(2, 3, "0");
			System.out.println(sb);
		}

		String[] idList = "1231".split(",");
		System.out.println("id list length:" + idList.length);
		String parmas = "aid=7759&amount=10&appname=demoapp1&goods=%s&num=100&orderid=testorder21111&ts=1277442397&uid=Ls38DVgdb5&vendor=partner1&ver=1&mypasswd1";
		System.out.println(String.format(parmas, "魔晶"));
		String uinfo = "tip=%s&rank=%s&qnum=%s";
		String aa = String.format(uinfo, "骑士",20,30);
		System.out.println(aa);
		
		
		String x = "54321";
		System.out.println(x.charAt(0));
	}
}
