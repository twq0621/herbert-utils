package net.snake.gamemodel.rankings.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.commons.PagedListHolder;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.pet.bean.HorseCharacterView;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.rankings.response.RankingResponse50310;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;



/**
 * 坐骑同级排行
 * @author serv_dev
 * 
 */
@MsgCodeAnn(msgcode = 50311,accessLimit=100)
public class RankingHorseSimilarProcess50311 extends CharacterMsgProcessor implements IThreadProcessor {
	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		//跨服判断
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		
		int mingci = 0;
		int pagecount = 0;
		byte yema = request.getByte();
		// character 人物角色characterHorseController
		// 得到当前人物骑到的马
		Horse horse = character.getCharacterHorseController().getShowHorse();
		List<HorseCharacterView> list = null;
		if (null != horse) {
			HorseModel horseModel = horse.getHorseModel();
			list = RankingManager.getInstance().getMapcharacterRankingMAList().get(horseModel.getId());
			RankingGetTopMeTools.setTopHeHorseCharacterView(list);
			String keyWord = request.getString();
			if (!"".equals(keyWord)&& null != list) {
				List<HorseCharacterView> list2 = new ArrayList<HorseCharacterView>(list.size());
				for (HorseCharacterView horseCharacterView : list) {
					if (horseCharacterView.getName().indexOf(keyWord) != -1) {
						list2.add(horseCharacterView);
					}
				}
				list = list2;
			}
		}
		if (null != list) {
			mingci = RankingGetTopMeTools.getTopMeHorseCharacterView(list, character);
			PagedListHolder<HorseCharacterView> phHolder = new PagedListHolder<HorseCharacterView>(list);
			phHolder.setPageSize(10);
			phHolder.setPage(yema - 1);
			list = phHolder.getPageList();
			pagecount = phHolder.getPageCount();

		}
		RankingResponse50310 response50310 = new RankingResponse50310(list, mingci, pagecount);
		character.sendMsg(response50310);
	}

}
