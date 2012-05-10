package net.snake.common;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Timer {
	private static final Logger logger = Logger.getLogger(Timer.class);
	private static SimpleDateFormat sdf2 = new SimpleDateFormat(
	"yyyy-MM-dd");
	
	public static String getNowStringDate() {
		return sdf2.format(new Date());
	}
	
	
	
	public static int getHour(long oldTime,long newTime) {
		long time = (newTime - oldTime) / 1000;
		return (int)(time / 3600);
	}
	
	public static long getNow(){
		return System.currentTimeMillis();
	}
	
	 /** 
     * 获得昨天日期 
     *  
     * @return 
     */  
    public static Date getYesterday() {  
        Date date = new Date();  
        date = new Date(date.getTime() - 1000 * 60 * 60 * 24);  
        SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");  
        return date;  
    }  

    public static Date getDateByString(String date) {
		try {
			return sdf2.parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		
			return null;
		}
	}
    public static String getNowTime(String dateformat){   
        Date   now   =   new   Date();      
        SimpleDateFormat   dateFormat   =   new   SimpleDateFormat(dateformat);//锟斤拷锟皆凤拷锟斤拷锟斤拷薷锟斤拷锟斤拷诟锟绞�     
        String  hehe  = dateFormat.format(now);      
        return hehe;   
    }  
    
    
    public static String  timeJiSuan(String ksdate,String jsdate) throws ParseException{
  	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     java.util.Date now = df.parse(jsdate);
	     java.util.Date date=df.parse(ksdate);
	     long l=now.getTime()-date.getTime();
	     long day=l/(24*60*60*1000);
	     long hour=(l/(60*60*1000)-day*24);
	     long min=((l/(60*1000))-day*24*60-hour*60);
	     long s=(l/1000-day*24*60*60-hour*60*60-min*60);
	     return "上次操作公用:"+hour+"小时"+min+"分"+s+"秒";
    }
    
    public static void main(String [] aa){
    	
    	String nameString=getNowTime("yyyy-MM-dd HH:mm:ss");
    	System.out.println(getDateByString(getNowStringDate()));
    	String naString =getNowTime("yyyy-MM-dd HH:mm:ss");
    	try {
			System.out.println(timeJiSuan(nameString,naString));
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
    }
}
