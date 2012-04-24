package net.snake.gamemodel.rankings.processor;

import java.util.List;

import net.snake.ann.MsgCodeAnn;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.gamemodel.instance.persistence.InstanceDataProvider;
import net.snake.gamemodel.pet.bean.HorseCharacterView;
import net.snake.gamemodel.pet.bean.HorseModel;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.rankings.response.RankingResponse50398;
import net.snake.gamemodel.skill.bean.HiddenWeaponDataEntry;
import net.snake.netio.message.RequestMsg;
import net.snake.netio.message.process.IThreadProcessor;
import net.snake.shell.Options;

/**
 * 我自己在排行的名次
 * @author serv_dev 
 *
 */
@MsgCodeAnn(msgcode = 50399,accessLimit=100)
public class RankingGetMeTopProcess50399  extends CharacterMsgProcessor implements IThreadProcessor {
	@Override
	public void process(Hero character, RequestMsg request)
			throws Exception {
		
		//跨服判断
		if(Options.IsCrossServ){
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP,1306));
			return;
		}
		
		//等级，财富，坐骑，连斩，boss，声望，武功，经脉，成就，暗器，
		//连斩，天关，八百里，夫子阵，迷踪阵 1 2 3 4 6
		byte type =request.getByte();//客户端发过来的标识
		StringBuilder sb = new StringBuilder();
		
		//character 人物等级
		List<CharacterRanking> list = RankingManager.getInstance().getCharacterRankingList();
		if(null != list && list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 人物等级同级
		sb.append("-1");
		sb.append(",");
		//character 人物钱
		list = RankingManager.getInstance().getCharacterRankingMoneyList();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 人物钱同级
		list = RankingManager.getInstance().getMapcharacterRankingMoneyList()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//马排行
		List<HorseCharacterView> listHorse = RankingManager.getInstance().getCharacterRankingMaList();
		if(null != listHorse&& listHorse.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeHorseCharacterView(listHorse, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//马排行同级
		// 得到当前人物骑到的马
		Horse horse = character.getCharacterHorseController().getShowHorse();
		if(null != horse){
			int mingCi = -1;
			HorseModel horseModel = horse.getHorseModel();
			listHorse = RankingManager.getInstance().getMapcharacterRankingMAList().get(horseModel.getId());
			mingCi = RankingGetTopMeTools.getTopMeHorseCharacterView(listHorse, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		//character 连斩
		list = RankingManager.getInstance().getCharacterRankingLianZhanList();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 连斩同级
		list = RankingManager.getInstance().getMapcharacterRankingLianZhanList()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		//character boss
		list = RankingManager.getInstance().getCharacterRankingBossList();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character boss同级
		list = RankingManager.getInstance().getMapcharacterRankingBossList()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		//character 声望
//		list = RankingManager.getInstance().getCharacterRankingZhanChangList();
//		if(null != list&& list.size() >0){
//			int mingCi = -1;
//			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
//			mingCi = reSetMingCi(mingCi);
//			sb.append(mingCi);
//			sb.append(",");
//		}else {
//			sb.append("-1");
//			sb.append(",");
//		}
//		//character 声望同级
//		list = RankingManager.getInstance().getMapcharacterRankingZhanChangList()
//		.get((int) character.getGrade());
//		if(null != list&& list.size() >0){
//			int mingCi = -1;
//			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
//			mingCi = reSetMingCi(mingCi);
//			sb.append(mingCi);
//			sb.append(",");
//		}else {
//			sb.append("-1");
//			sb.append(",");
//		}
		
		//character 武功
		list = RankingManager.getInstance().getCharacterRankingWuGongJingJieList();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 武功同级
		list = RankingManager.getInstance().getMapcharacterRankingWuGongJingJieList()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		//character 经脉
		list = RankingManager.getInstance().getCharacterRankingJingMaiList();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 经脉同级
		list = RankingManager.getInstance().getMapcharacterRankingJingMaiList()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		//character 成就
		list = RankingManager.getInstance().getCharacterRankingChengjiuList();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 成就同级
		list = RankingManager.getInstance().getMapCharacterRankingChengjiu()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		//character 暗器
		List<HiddenWeaponDataEntry> listHiddenWeaponDataEntry = RankingManager.getInstance().getCharacterRankingAnQiList();
		if(null != listHiddenWeaponDataEntry&& listHiddenWeaponDataEntry.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeHiddenWeaponDataEntry(listHiddenWeaponDataEntry, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 暗器同级
		listHiddenWeaponDataEntry = RankingManager.getInstance().getMapCharacterRankingAnQiList().get(character.getGrade());
		if(null != listHiddenWeaponDataEntry&& listHiddenWeaponDataEntry.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeHiddenWeaponDataEntry(listHiddenWeaponDataEntry, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
	
		int fuBenId[] = InstanceDataProvider.getInstance().getInstanCeId();
		for (int i = 0; i < fuBenId.length; i++) {
			if(fuBenId[i] == 5){
				continue;
			}
			List<Fubenranking> listFubenranking = null;
			 listFubenranking = RankingManager.getInstance().getMapfubenRanking().get(fuBenId[i]);
				if(null != listFubenranking&& listFubenranking.size() >0){
					int mingCi = -1;
					mingCi = RankingGetTopMeTools.getTopMeFubenranking(listFubenranking, character);
					mingCi = reSetMingCi(mingCi);
					sb.append(mingCi);
					sb.append(",");
				}else {
					sb.append("-1");
					sb.append(",");
				}
				//character 副本6同级  最后一个不用加“，”号
				listFubenranking = RankingGetTopMeTools.reSetTongJiList(fuBenId[i], character.getGrade());
				if(null != listFubenranking&& listFubenranking.size() >0){
					int mingCi = -1;
					mingCi = RankingGetTopMeTools.getTopMeHiddenWeaponDataEntry(listHiddenWeaponDataEntry, character);
					mingCi = reSetMingCi(mingCi);
					sb.append(mingCi);
					sb.append(",");
				}else {
					sb.append("-1");
					sb.append(",");
				}
		}
		
		
		//character 论剑
		list = RankingManager.getInstance().getCharacterRankingLunJian();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 论剑同级
		list = RankingManager.getInstance().getMapCharacterRankingLunJianList()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		//character 城战
		list = RankingManager.getInstance().getCharacterRankingChengZhan();
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		//character 城战同级
		list = RankingManager.getInstance().getMapCharacterRankingChengZhanList()
		.get((int) character.getGrade());
		if(null != list&& list.size() >0){
			int mingCi = -1;
			mingCi = RankingGetTopMeTools.getTopMeCharacterRanking(list, character);
			mingCi = reSetMingCi(mingCi);
			sb.append(mingCi);
			sb.append(",");
		}else {
			sb.append("-1");
			sb.append(",");
		}
		
		RankingResponse50398 response50398 = new RankingResponse50398(type,sb.toString());
		character.sendMsg(response50398);
		
	}
	public int reSetMingCi(int mingCi){
		int a = -1;
		if(mingCi ==0 ){
			mingCi = a;
		}else {
			a = mingCi;
		}
		return a;
	}
}
