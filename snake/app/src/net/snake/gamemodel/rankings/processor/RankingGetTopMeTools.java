package net.snake.gamemodel.rankings.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.gamemodel.hero.bean.CharacterRanking;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.hero.persistence.RankingManager;
import net.snake.gamemodel.instance.bean.Fubenranking;
import net.snake.gamemodel.pet.bean.HorseCharacterView;
import net.snake.gamemodel.skill.bean.HiddenWeaponDataEntry;


/**
 * 
 * 
 * @author serv_dev
 * @version 1.0
 * @created 2011-5-4 下午02:32:57
 */

public class RankingGetTopMeTools {

	
	
	
	public static int getTopMeCharacterRanking(List<CharacterRanking> list, Hero character) {
		int jishu = 0;
		int mingci = 0;
		if(list ==null){
			return mingci;
		}
		for (CharacterRanking characterRanking : list) {
			jishu = jishu + 1;
			if (character.getId().intValue() == characterRanking.getId()) {
				mingci = jishu;
				break;
			}
		}
		return mingci;
	}
	
	public static void setTopHeCharacterRanking(List<CharacterRanking> list) {
		int jishu = 0;
		if(list !=null){
			for (CharacterRanking characterRanking : list) {
				jishu = jishu + 1;
				characterRanking.setMetop(jishu);
				}
			}
		}
		
		
	

	// for (Fubenranking fubenranking : list) {
	// jishu=jishu +1;
	// if(character.getId().intValue()==fubenranking.getCharacterId()){
	// mingci = jishu;
	// break;
	// }
	// }
	public static int getTopMeFubenranking(List<Fubenranking> list, Hero character) {
		int jishu = 0;
		int mingci = 0;
		if(list ==null){
			return mingci;
		}
		for (Fubenranking fubenranking : list) {
			jishu = jishu + 1;
			if (character.getId().intValue() == fubenranking.getCharacterId().intValue()) {
				mingci = jishu;
				break;
			}
		}
		return mingci;
	}
	
	public static void setTopHeFubenranking(List<Fubenranking> list) {
		int jishu = 0;
		if(list !=null){
			for (Fubenranking fubenranking : list) {
				jishu = jishu + 1;
				fubenranking.setMetop(jishu);
				}
			}
		}
	// for (HiddenWeaponDataEntry characterRanking : list) {
	// jishu=jishu +1;
	// if(character.getId().intValue()==characterRanking.getCharacterId()){
	// mingci = jishu;
	// break;
	// }
	// }
	public static int getTopMeHiddenWeaponDataEntry(List<HiddenWeaponDataEntry> list, Hero character) {
		int jishu = 0;
		int mingci = 0;
		if(list ==null){
			return mingci;
		}
		for (HiddenWeaponDataEntry hiddenWeaponDataEntry : list) {
			jishu = jishu + 1;
			if (character.getId().intValue() == hiddenWeaponDataEntry.getCharacterId().intValue()) {
				mingci = jishu;
				break;
			}
		}
		return mingci;
	}
	public static void setTopHeHiddenWeaponDataEntry(List<HiddenWeaponDataEntry> list) {
		int jishu = 0;
		if(list !=null){
			for (HiddenWeaponDataEntry hiddenWeaponDataEntry : list) {
				jishu = jishu + 1;
				hiddenWeaponDataEntry.setMetop(jishu);
				}
			}
		}
//	for (HorseCharacterView horseCharacterView : list) {
//		jishu=jishu +1;
//		if(character.getId().intValue()==horseCharacterView.getCharacterId().intValue()){
//			mingci = jishu;
//			break;
//		}
//	}
	public static int getTopMeHorseCharacterView(List<HorseCharacterView> list, Hero character) {
		int jishu = 0;
		int mingci = 0;
		if(list ==null){
			return mingci;
		}
		for (HorseCharacterView horseCharacterView : list) {
			jishu = jishu + 1;
			if(character.getId().intValue()==horseCharacterView.getCharacterId().intValue()){
				mingci = jishu;
				break;
			}
		}
		return mingci;
	}
	public static void setTopHeHorseCharacterView(List<HorseCharacterView> list) {
		int jishu = 0;
		if(list !=null){
			for (HorseCharacterView horseCharacterView : list) {
				jishu = jishu + 1;
				horseCharacterView.setMetop(jishu);
				}
			}
		}
	
	public static List<Fubenranking> reSetTongJiList(int fuBenId, int characterGrade) {
		List<Fubenranking> list = RankingManager.getInstance().getMapfubenRankingTongJi().get(characterGrade);
		int jishu = 0;
		if (list == null) {
			return null;
		}
		List<Fubenranking> list2 = new ArrayList<Fubenranking>();
		for (Fubenranking fubenranking : list) {
			if (fubenranking.getFubenId() == fuBenId) {
				jishu = jishu + 1;
				fubenranking.setMetop(jishu);
				list2.add(fubenranking);
			}
		}
		return list2;
	}
}
