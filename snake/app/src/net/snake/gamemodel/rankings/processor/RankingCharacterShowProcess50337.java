package net.snake.gamemodel.rankings.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.commons.BeanUtils;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.rankings.response.RankingResponse50338;
import net.snake.gamemodel.rankings.response.RankingTianXiaDiYiResponse50338;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYi;
import net.snake.gamemodel.tianxiadiyi.bean.CharacterTianXiaDiYiGoods;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiGoodsManager;
import net.snake.gamemodel.tianxiadiyi.persistence.CharacterTianXiaDiYiManager;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 请求人物显示
 * @author serv_dev
 *
 */
@MsgCodeAnn(msgcode = 50337,accessLimit=100)
public class RankingCharacterShowProcess50337 extends CharacterMsgProcessor implements IThreadProcessor {
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		//跨服判断
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		Integer characterid =request.getInt();
		byte flashsuoyin=request.getByte();
		//判断是不是天下第一排行榜如果是的话要在接受一个str类型表示是那个服务器的
		if(flashsuoyin == 100){
			String wufuqiId = request.getString();
			int wufuid = Integer.valueOf(wufuqiId);
			CharacterTianXiaDiYi characterTianXiaDiYi = CharacterTianXiaDiYiManager.getInstance().getHaracterTianXiaDiYiMap().get(characterid.toString()+wufuid);
			Collection<CharacterTianXiaDiYiGoods> txdYiGoods = CharacterTianXiaDiYiGoodsManager.getInstance().getListCharacterTianXiaDiYiGoods();
			List<CharacterGoods> listGoods = new ArrayList<CharacterGoods>(txdYiGoods.size());
			
			
			for (CharacterTianXiaDiYiGoods characterTianXiaDiYiGoods : txdYiGoods) {
				if(characterTianXiaDiYiGoods.getCharacterId().intValue()==characterid && wufuid == characterTianXiaDiYiGoods.getServerId().intValue() ){
					CharacterGoods characterGoods = new CharacterGoods();
					BeanUtils.copyProperties(characterTianXiaDiYiGoods, characterGoods);
					listGoods.add(characterGoods);
				}
			}
			RankingTianXiaDiYiResponse50338 rankingTianXiaDiYiResponse50338 = new RankingTianXiaDiYiResponse50338(listGoods, characterTianXiaDiYi,characterid, flashsuoyin);
			character.sendMsg(rankingTianXiaDiYiResponse50338);
			return;
		}
		//character 人物角色
	    Hero character2 =GameServer.vlineServerManager.getCharacterById(characterid);
	    RankingResponse50338 response50338 = new RankingResponse50338(character2,characterid,flashsuoyin);
	    character.sendMsg(response50338);
	}

}
