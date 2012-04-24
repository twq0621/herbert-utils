package net.snake.across.gameprocessor;

import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYiGoods;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiGoodsManager;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;
import net.snake.gmtool.net.ByteArrayReader;
import net.snake.gmtool.net.Msg;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;


/**
 * 处理跨服天下第一排行返回排行数据请求,插入数据库.
 * 
 */
public class AcrossWorldFirstHandler1012 extends AuthSTSHandler {

	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if(Options.IsCrossServ){
			return;
		}
		ByteArrayReader read = message.getContentReader();
		byte readByte = read.readByte();
		for(int i=0;i<readByte;i++){
			CharacterTianXiaDiYi obj = (CharacterTianXiaDiYi)read.readIObject();
			if(!CharacterTianXiaDiYiManager.getInstance().getLisTianXiaDiYi().contains(obj)){
				CharacterTianXiaDiYiManager.getInstance().insert(obj);
			}
		}
		int readByte1 = read.readInt();
		for(int i=0;i<readByte1;i++){
			CharacterTianXiaDiYiGoods obj = (CharacterTianXiaDiYiGoods)read.readIObject();
			if(!CharacterTianXiaDiYiGoodsManager.getInstance().getListCharacterTianXiaDiYiGoods().contains(obj)){
				CharacterTianXiaDiYiGoodsManager.getInstance().insertCharacterTianXiaDiYiGoods(obj);
			}
		}
		session.close(true);
	}
}

