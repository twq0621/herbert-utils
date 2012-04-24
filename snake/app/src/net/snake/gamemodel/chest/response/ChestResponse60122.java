package net.snake.gamemodel.chest.response;


import net.snake.netio.ServerResponse;


/**    
 *     
 *    
 * @author serv_dev     
 */    
public class ChestResponse60122 extends ServerResponse{

	public ChestResponse60122(int chestType){
		setMsgCode(60122);
		
//		try {
			writeInt(chestType);
		
//		} catch (IOException e) {
//			logger.error(e.getMessage(),e);
//		}

	}
	
}
