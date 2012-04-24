package net.snake.gamemodel.rankings.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.commons.Language;
import net.snake.consts.CommonUseNumber;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.wedding.bean.Couples;
import net.snake.netio.ServerResponse;


public class RankingResponse50338 extends ServerResponse {

	private static int msgcode = 50338;
	public RankingResponse50338(Hero character,int oldcharacterid,byte flashsuoyin) {
		super.setMsgCode(msgcode);
		try {
//			private int contemptcount; //人物鄙视
//			private int worshipcount;//人物崇拜
		
			if (null != character) {
			writeByte(flashsuoyin);
			writeInt(character.getWorshipcount());
			writeInt(character.getContemptcount());
			//=======================================
			Collection<CharacterGoods> goodslist=character.getCharacterGoodController().getBodyGoodsList();
			List<CharacterGoods> list=new ArrayList<CharacterGoods>();
			list.addAll(goodslist);
			Bow bow = character.getBowController().getBow();
			if(bow!=null){
				list.add(bow.getGoods());
			}
			//游龙之刃
			CharacterGoods youlong=character.getMyFactionManager().getYoulongZhiren();
			//轩辕剑
			CharacterGoods xuanyuanjian=character.getMycharacterAcrossZhengzuoManager().getXuanyuanjian();
			if(youlong!=null){
				list.add(youlong);
			}
			if(xuanyuanjian!=null){
				list.add(xuanyuanjian);
			}
			writeByte(list.size());
			for(CharacterGoods goods:list){
				writeInt(goods.getGoodmodelId());
				writeShort(goods.getPosition());
				writeInt(goods.getPingzhiColor());
				if(goods.isAllStar()){
					writeByte(CommonUseNumber.byte1);
				}else {
					writeByte(CommonUseNumber.byte0);
				}
				writeByte(goods.getJinjie());
				writeBoolean((goods.getTotem() != null && !goods.getTotem().isEmpty()));//是否刻有战纹图腾
				writeBoolean(goods.isManxingGems());//是否是全满星得
				writeBoolean(goods.isBestEquipmment());//是否是最好的装备
				writeBoolean(goods.isMaxBornAttribute());
			}
			writeUTF(Language.CHENGHAO);
			writeInt(oldcharacterid);
			//验证是不是骑马
			Horse horse=character.getCharacterHorseController().getCurrentRideHorse();
				if(null != horse){
				writeByte(1);
				}else {
					writeByte(0);
				}
				//人物门派
				writeInt(character.getPopsinger());
				//马的id
				if(null != horse){
					writeInt(horse.getCharacterHorse().getHorseModelId());
					}else {
					writeInt(0);
					}
				if (!character.getMyFriendManager().getRoleWedingManager()
						.isWedding()) {
					writeInt(0);
				} else {
					Couples couples = character.getMyFriendManager()
							.getRoleWedingManager().getFuqi().getCouples();
					if (character.isMale()) {
						writeInt(couples.getWr().getMaleGood());
					} else {
						writeInt(couples.getWr().getFemaleGood());
					}
				}
			}else {
				writeByte(flashsuoyin);
				writeInt(-1);
				writeInt(-1);
			}
		}
			 catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
		}
		
	}

	

