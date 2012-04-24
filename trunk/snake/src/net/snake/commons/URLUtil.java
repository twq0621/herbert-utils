package net.snake.commons;

public class URLUtil {
    public static String getURL(String appurl, Object ...param) {
        StringBuilder sb = new StringBuilder();
        sb.append(appurl);
        boolean isfirst = true;
        if (param == null || param.length==0) {
        	return sb.toString();
		}
        try {
                sb.append('?');
                for (int i = 0; i < param.length; i += 2) {
                	if (param[i+1] == null) {
						continue;
					}
                        if (!isfirst) {
                            sb.append('&');
                        }
                        sb.append(param[i]);
                        sb.append('=');
                        String value = param[i + 1].toString();
                        value = java.net.URLEncoder.encode(value, "utf-8");
                        sb.append(value);
                        isfirst = false;
                }
        } catch (Exception ex) {
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(createUrlParam("aa","1","bb","鍒樻亽"));
//    }
    public static String createUrlParam(Object ...param) {
        StringBuilder sb = new StringBuilder();
        boolean isfirst = true;
        try {
            if (param != null && param.length > 1) {
                for (int i = 0; i < param.length; i += 2) {
                    if (param[i + 1] != null) {
                        if (!isfirst) {
                            sb.append('&');
                        }
                        sb.append(param[i]);
                        sb.append('=');
                        String value = param[i + 1].toString();
                        value = java.net.URLEncoder.encode(value, "utf-8");
                        sb.append(value);
                        isfirst = false;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return sb.toString();
    }


}
