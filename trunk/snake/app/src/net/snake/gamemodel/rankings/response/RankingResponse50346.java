package net.snake.gamemodel.rankings.response;

import java.util.List;

import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.netio.ServerResponse;


/**
 * @author serv_dev 副本
 *
 */
public class RankingResponse50346 extends ServerResponse {

	private static int msgcode = 50346;


	public RankingResponse50346(List<Fubenranking> fubenrankinglist,int mingci,int pagecount) {
		super.setMsgCode(msgcode);
		try {
			if(null != fubenrankinglist){
				writeByte(pagecount);
				writeByte(mingci);
			    writeByte(fubenrankinglist.size());
			for (Fubenranking fubenranking : fubenrankinglist) {
					writeInt(fubenranking.getCharacterId());
					writeUTF(fubenranking.getCce().getViewName());
					writeInt(fubenranking.getFubenTime());
					writeShort(fubenranking.getMetop()); 
			}
			}else {
				writeByte(0);
				writeByte(0);
				writeByte(0);
			}
		}
			 catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		
	}

	

