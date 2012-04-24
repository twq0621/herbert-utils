package net.snake.gamemodel.wedding.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.snake.netio.ServerResponse;
import net.snake.commons.script.SPropertyAdditionController;
import net.snake.gamemodel.achieve.persistence.AchieveManager;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.channel.persistence.ChannelManager;
import net.snake.gamemodel.heroext.channel.persistence.ChannelRealdragonManager;
import net.snake.gamemodel.heroext.wudao.persistence.DGWDController;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.skill.persistence.SkillManager;
import net.snake.gamemodel.wedding.bean.Couples;


public class WeddingPeiouInfoResponse52230 extends ServerResponse {
	private static final int MSGCODE = 52230;

	/**
	 * 装备数量（byte）{装备模板id(int) 位置(short)}
	 * 
	 * @param character
	 * 
	 */
	public WeddingPeiouInfoResponse52230(int ringId,Hero character) {
		// TODO 等待修改
		try {
			setMsgCode(MSGCODE);
			ServerResponse out = this;
			writeInt(ringId);
			// 玩家ID（int）,玩家name（str）,等级(short)、
			writeByte(2); //标识存在配偶 切在线
			out.writeInt(character.getId());
			out.writeUTF(character.getViewName());
			out.writeShort(character.getGrade());
			// 门派（byte）、战场声望（int）、帮派（str）,配偶（str）,
			out.writeByte(character.getPopsinger());
			out.writeInt(character.getChengzhanShengWang());
			out.writeInt(character.getLunjianShengWang());
			out.writeUTF(character.getMyFactionManager().getFactionName());
			if (character.getMyFactionManager().isFaction()) {
				Faction faction = character.getMyFactionManager()
						.getFactionController().getFaction();
				out.writeByte(faction.getIcoId());
				out.writeUTF(faction.getIcoStr());
			} else {
				out.writeByte(0);
				out.writeUTF("");
			}
			//if (character.getConsortName() == null) {
				out.writeUTF("");
//			} else {
//				out.writeUTF(character.getConsortName());
//			}
			SPropertyAdditionController cpc = character
					.getPropertyAdditionController();

			// 经验值(当前(int)/上限(int))，
			out.writeDouble(character.getNowExperience());
			out.writeDouble(character.getNextExperience());
			out.writeInt(character.getZhenqi());

			// 生命值(当前(int)/上限(int))
			out.writeInt(character.getNowHp());
			/** 生命值(当前) **/
			out.writeInt(cpc.getExtraMaxHp());
			/** 生命值(上限) **/
			// 内力值(当前(int)/上限(int))
			out.writeInt(character.getNowMp());
			/** 内力值(当前) **/
			out.writeInt(cpc.getExtraMaxMp());
			/** 内力值(上限) **/
			// 体力值(当前(int)/上限(int))、
			out.writeInt(character.getNowSp());
			/** 体力值(当前) **/
			out.writeInt(cpc.getExtraMaxSp());
			/** 体力值(上限) **/
			// =====================================
			out.writeInt(cpc.getExtraAttack());
			/** 攻击 **/
			out.writeInt(cpc.getExtraDefend());
			/** 防御 **/
			out.writeInt(cpc.getExtraCrt());
			/** 会心 **/
			out.writeInt(cpc.getExtraDodge());
			/** 闪避 **/
			out.writeInt(cpc.getExtraAttackSpeed());
			/** 攻速 **/
			out.writeInt(cpc.getExtraMoveSpeed());
			/** 移速 **/
			// ======================================
			// 武功境界(当前(byte)/上限(byte))
			out.writeShort(character.getWuxueJingjie());
			out.writeShort(SkillManager.getInstance().getTotalwugongjingjie(
					character.getPopsinger()));
			// 经脉进度(当前(byte)/上限(byte))
			out.writeShort(character.getChannelXuewei());
			out.writeShort(ChannelManager.getInstance().getTotalchannelcount()
					+ ChannelRealdragonManager.getInstance()
							.getTotalchannelcount());
			// 成就进度(当前(short)/上限(short))
			out.writeShort(character.getChengjiuPoint());
			out.writeShort(AchieveManager.getInstance().getMaxPoint());
			// =======================================
			Collection<CharacterGoods> goodslist = character
					.getCharacterGoodController().getBodyGoodsList();
			List<CharacterGoods> list=new ArrayList<CharacterGoods>();
			list.addAll(goodslist);			
			Bow bow = character.getBowController().getBow();
			if(bow!=null){
				list.add(bow.getGoods());
			}

			//游龙之刃
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

			out.writeByte(list.size());
			for (CharacterGoods goods : list) {
				// 装备模板id(int) 位置(short),强化成功次数(byte),品质(byte),绑定(byte 1绑定
				// 0不绑定),是否满星(byte),强化总次数(byte)
				out.writeInt(goods.getGoodmodelId());
				out.writeShort(goods.getPosition());
				out.writeByte(goods.getJinjie());
				out.writeByte(goods.getPingzhiColor());
				out.writeByte(goods.getBind());
				out.writeBoolean(goods.isAllStar());
				out.writeByte(goods.getStrengthenNum());
				out.writeBoolean((goods.getTotem() != null && !goods.getTotem().isEmpty()));//是否刻有战纹图腾
				out.writeBoolean(goods.isManxingGems());//是否是全满星得
				out.writeBoolean(goods.isBestEquipmment());//是否是最好的装备
				out.writeBoolean(goods.isMaxBornAttribute());
			}
			Horse currenthorse = character.getCharacterHorseController()
					.getCurrentRideHorse();
			if (currenthorse != null) {
				out.writeByte(1);
				out.writeInt(currenthorse.getHorseModel().getId());
			} else {
				out.writeByte(0);
			}
			out.writeInt(character.getAttackAddpoint());
			out.writeInt(character.getDefenceAddpoint());
			out.writeInt(character.getLightAddpoint());
			out.writeInt(character.getStrongAddpoint());
			out.writeInt(character.getPotential());
			Couples couples = character.getMyFriendManager()
					.getRoleWedingManager().getFuqi().getCouples();
			if (character.isMale()) {
				out.writeInt(couples.getWr().getMaleGood());
				out.writeUTF(couples.getFemaleName());
			} else {
				out.writeInt(couples.getWr().getFemaleGood());
				out.writeUTF(couples.getMaleName());
			}
			DGWDController ct = character.getDgwdController();
			writeInt(ct.getProb("crt"));
			writeInt(ct.getProb("aqjv"));
			writeInt(ct.getProb("hwhurt"));
			writeInt(ct.getProb("bow"));
			writeInt(ct.getProb("dantian"));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * @param i
	 */
	public WeddingPeiouInfoResponse52230(int ringId,int type) {
		setMsgCode(MSGCODE);
		 try {
			 writeInt(ringId);
			writeByte(type);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
