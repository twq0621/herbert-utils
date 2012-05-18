package lion.common;

/**
 * 数据加密
 * @author serv_dev
 *
 */
public class DataEncryption {
/**
 * 按位取反
 * @param data 
 * @return
 */
 public  static boolean BitReversion(byte [] data,int begin)
 {
	 for(int i=begin;i<data.length;i++)
		{
			int temp=(int)data[i];
			temp=~temp;
			data[i]=(byte)temp;
		}
	 return true;
 }
}
