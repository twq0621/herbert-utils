package net.snake.across.acrserverprocessor;

import java.io.IOException;
import java.util.List;

import net.snake.commons.msgprocess.AuthSTSHandler;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYiGoods;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiDAO;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiGoodsDAO;
import net.snake.gmtool.net.ByteArrayWriter;
import net.snake.gmtool.net.Msg;
import net.snake.ibatis.SystemFactory;
import net.snake.shell.Options;

import org.apache.mina.core.session.IoSession;

/**
 * 处理游戏服天下第一排行请求,返回排行数据.
 * 
 * @author serv_dev.
 * @version: 1.0
 * @Create at: 2011-7-1 下午05:50:53
 */
public class AcrossWorldFirstHandler1011 extends AuthSTSHandler {

	@SuppressWarnings("unchecked")
	@Override
	public void handleAuthMessage(IoSession session, Msg message) throws Exception {
		if (!Options.IsCrossServ) {
			return;
		}
		CharacterTianXiaDiYiDAO dao = new CharacterTianXiaDiYiDAO(SystemFactory.getCharacterSqlMapClient());
		CharacterTianXiaDiYiGoodsDAO daoGoods = new CharacterTianXiaDiYiGoodsDAO(SystemFactory.getCharacterSqlMapClient());
		session.write(new Result1012(dao.select(), daoGoods.select()));
	}

}

class Result1012 extends Msg {
	public Result1012(List<CharacterTianXiaDiYi> list, List<CharacterTianXiaDiYiGoods> listgoods) throws IOException {
		setFunction(1012);
		ByteArrayWriter out = getContentWriter();
		if (list != null) {
			out.writeByte(list.size());
			for (CharacterTianXiaDiYi obj : list) {
				out.writeIObject(obj);
			}

		} else {
			out.writeByte(0);
		}

		if (listgoods != null) {
			out.writeInt(listgoods.size());
			for (CharacterTianXiaDiYiGoods obj : listgoods) {
				out.writeIObject(obj);
			}
		} else {
			out.writeByte(0);
		}
	}
}
