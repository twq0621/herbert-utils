package net.snake.common;

import java.io.File;
import java.io.FileOutputStream;

public class StringUtil {
	
	public static final String EMPTY_STRING = "";
	public static final int ZERO = 0;

	/**
	 * 为0锟斤拷锟斤拷锟斤拷锟斤拷蟊坏锟斤拷锟斤拷锟�
	 */
	public static boolean isEmptyOrZero (Integer i) {
		return ( i == null || ZERO == i.intValue());
	}
	public static boolean isEmpty (Integer i) {
		return ( i == null );
	}
	
	/**
	 * 锟秸达拷锟斤拷锟斤拷为锟角匡拷
	 */
	public static boolean isEmpty(String s ){
		return (s == null || EMPTY_STRING.equals(s));
	}
    public static boolean isNotEmpty(String s ){
        return (s != null && !EMPTY_STRING.equals(s));
    }
    public static boolean isNotEmpty(Object s ){
        return (s != null && !EMPTY_STRING.equals(s));
    }    
 
    /**string转锟斤拷int锟斤拷锟斤拷
     * @param numbers
     * @return
     */
    public static int[] string_To_int(String[] numbers){
    	
    	 int[] ia=new int[numbers.length];
    	    for(int i=0;i<numbers.length;i++){
    	       ia[i]=Integer.parseInt(numbers[i]);
    	    }
			return ia;
    	
    	
    }
    
   
 
    /**
     * 锟斤拷锟侥憋拷锟侥硷拷锟斤拷写锟斤拷锟斤拷锟捷伙拷追锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷锟絘ppend为true锟斤拷直锟斤拷追锟斤拷锟斤拷锟斤拷锟斤拷,<br>
     * 锟斤拷锟絘ppend为false锟津覆革拷原锟斤拷锟斤拷锟斤拷锟斤拷<br>
     * 
     * @param path
     * @param content
     * @param append
     */
    public void writeFile(String path, String content, boolean append) {
        File writefile;
        try {
            // 通锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷卸锟斤拷欠锟斤拷锟斤拷谋锟斤拷募锟斤拷锟阶凤拷锟斤拷锟斤拷锟�
             boolean addStr = append;

            writefile = new File(path);

            // 锟斤拷锟斤拷谋锟斤拷募锟斤拷锟斤拷锟斤拷锟斤拷虼唇锟斤拷锟�
            if (writefile.exists() == false) {
                writefile.createNewFile();
                writefile = new File(path); // 锟斤拷锟斤拷实锟斤拷
            }

            FileOutputStream fw = new FileOutputStream(writefile,addStr);
            if(addStr){
            fw.write(content.getBytes());
            }else {
             fw.write(content.getBytes());
			}
            fw.flush();
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    
	public static void  main(String []aa){
	
		//System.out.println(MyworUtil.getMd5Passwd("锟斤拷锟�));
		
		//stringUtil.writeFile("d:/1.txt", "1010", true);
     }
    
}
