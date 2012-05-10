package net.snake.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
/**
 * 鐢ㄤ簬瀵硅浜嬫湰鐨勬搷浣�
 * 
 * @author serv_dev
 * 
 * 
 */
public class NoteOperate {
	
	private static final Logger logger = Logger.getLogger(NoteOperate.class);
	// txt鏂囦欢璺緞
	private String filePath;
	/**
	 * 鏋勯�鍑芥暟
	 * 
	 * @param filePath
	 *            鏂囨湰鏂囦欢鍏ㄨ矾寰�
	 */
	public NoteOperate(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 鏋勯�鍑芥暟
	 * 
	 * @param file
	 *            闇�璇诲彇鐨勬枃鏈枃浠�
	 */
	public NoteOperate(File file) {
		this.filePath = file.getPath();
	}
	/**
	 * 鍒ゆ柇鏂囨湰鏂囦欢鏄惁瀛樺湪
	 * 
	 * @return 瀛樺湪杩斿洖true 鍚﹀垯杩斿洖false
	 */
	public boolean exists() {
		File file = new File(this.filePath);
		return file.exists();
	}
	/**
	 * 寰楀埌杩欎釜txt鎵�湁鐨勫垪鐨勬暟鎹�绌鸿灏嗚嚜鍔ㄨ烦杩�骞惰嚜鍔ㄥ垹闄ゆ枃瀛楀墠鍚庣殑绌烘牸
	 * 
	 * @return List<String>
	 * @throws IOException
	 */
	public List<String> fileLinesContent() throws IOException {
		List<String> strs = new ArrayList<String>();
		File file = new File(this.filePath);
		 InputStreamReader read = new InputStreamReader (new FileInputStream(file),"gbk");
		BufferedReader br = new BufferedReader(read);// 寤虹珛BufferedReader瀵硅薄锛屽苟瀹炰緥鍖栦负br
		String Line = br.readLine();// 浠庢枃浠惰鍙栦竴琛屽瓧绗︿覆
		// 鍒ゆ柇璇诲彇鍒扮殑瀛楃涓叉槸鍚︿笉涓虹┖
		while (Line != null) {
			if (!"".equals(Line))
				strs.add(Line.trim());
			Line = br.readLine();// 浠庢枃浠朵腑缁х画璇诲彇涓�鏁版嵁
		}
		br.close();// 鍏抽棴BufferedReader瀵硅薄
		read.close();// 鍏抽棴鏂囦欢
		return strs;
	}
	/**
	 * 鍒涘缓涓�釜绌虹殑璁颁簨鏈枃妗�濡傛灉杩欎釜璁颁簨鏈枃妗ｅ瓨鍦ㄥ氨涓嶅啀鍒涘缓 鍑芥暟杩樻湭鍐欏疄鐜伴儴鍒�br/>
	 * 濡傛灉鏂囨湰宸茬粡瀛樺湪鍒欎笉鍐嶅垱寤�
	 * 
	 * @throws IOException
	 */
	public void createEmptyNote() throws IOException {
		File file = new File(this.filePath);
		if (!file.exists())
			file.createNewFile();
	}
	/**
	 * 灏嗗唴瀹瑰啓鍏ヨ繖涓枃鏈�娉ㄦ剰浠ュ墠鐨勫唴瀹瑰皢浼氳鍒犻櫎
	 * 
	 * @param str
	 *            灏嗚鍐欏叆鐨勫唴瀹�
	 * @throws IOException
	 */
	public void writeString(String str) throws IOException {
		File file = new File(this.filePath);
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		output.write(str);
		output.close();// 鍏抽棴BufferedReader瀵硅薄
	}
	/**
	 * 鍦ㄦ枃鏈殑鎸囧畾琛屾彃鍏ユ枃瀛椼�濡傛灉璇ヨ宸茬粡瀛樺湪锛岃琛屽唴瀹瑰皢浼氳鍒犻櫎銆傚鏋滆鍙蜂笉瀛樺湪锛屽皢浼氳鎻掑叆鍒版渶鍚庝竴琛�
	 * 
	 * @param i
	 *            琛屽彿 琛屽彿涓�鏃讹紝灏嗘彃鍏ュ埌鏈�悗涓�
	 * @param str
	 *            灏嗚鎻掑叆鐨勫唴瀹�
	 * @throws IOException
	 */
	public void insertWords(int i, String str) throws IOException {
		List<String> strs = fileLinesContent();
		// 杩涜鎻掑叆鎿嶄綔
		if (i == 0 || strs.size() < i) // 鎻掑叆鍒版渶鍚庝竴琛�
		{
			strs.add(str);
		} else { // 鎻掑叆鍒版枃鏈腑
			strs.set(i - 1, str);
		}
		// 閲嶆柊鍐欏叆鍒版枃鏈�
		StringBuffer sb = new StringBuffer();
		for (String temp : strs) {
			sb.append(temp.replace("\r\n", "") + "\r\n");
		}
		writeString(sb.toString());
	}
	public static void main(String []aa){
		NoteOperate no= new NoteOperate("f:/ip");
		try {
			List<String> strList=no.fileLinesContent();
			for (String string : strList) {
				System.out.println(string);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
