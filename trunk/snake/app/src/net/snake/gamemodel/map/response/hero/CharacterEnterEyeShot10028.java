package net.snake.gamemodel.map.response.hero;

import java.io.IOException;
import java.util.List;

import net.snake.commons.script.SPropertyAdditionController;
import net.snake.consts.Position;
import net.snake.gamemodel.equipment.response.CharacterPropertyResponse10108;
import net.snake.gamemodel.faction.bean.Faction;
import net.snake.gamemodel.faction.logic.FactionController;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.goods.logic.container.IBody;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.heroext.biguandazuo.logic.MyDazuoAndUnDaduoManager;
import net.snake.gamemodel.pet.logic.Horse;
import net.snake.gamemodel.skill.bean.EffectInfo;
import net.snake.gamemodel.skill.bow.bean.Bow;
import net.snake.gamemodel.vip.bean.CharacterVip;
import net.snake.netio.ServerResponse;
import net.snake.shell.Options;

public class CharacterEnterEyeShot10028 extends ServerResponse {
	public CharacterEnterEyeShot10028(Hero otherCharacter) {
		setMsgCode(10028);
		try {
			writeInt(otherCharacter.getId());
			writeByte(otherCharacter.getAccount().getGm());
			writeShort(otherCharacter.getMoveController().getV());
			// =================================================================
			writeUTF(otherCharacter.getViewName());
			writeByte(otherCharacter.getGrade());
			writeByte(otherCharacter.getHeadimg());
			writeByte(otherCharacter.getPopsinger());
			writeShort(otherCharacter.getX());// 其他玩家当前x坐标
			writeShort(otherCharacter.getY());// 其他玩家当前y坐标
			SPropertyAdditionController propertycontroller = otherCharacter.getPropertyAdditionController();
			writeInt(otherCharacter.getNowHp());// hp
			writeInt(propertycontroller.getExtraMaxHp());// hp
			writeInt(otherCharacter.getNowMp());// mp
			writeInt(propertycontroller.getExtraMaxMp());// maxmp
			writeInt(otherCharacter.getNowSp());// mp
			writeInt(propertycontroller.getExtraMaxSp());// maxmp
			// 帮派状态
			if (Options.IsCrossServ) {
				writeByte(1);
				writeInt(otherCharacter.getMyFactionManager().getFactionId());
				writeUTF(otherCharacter.getMyFactionManager().getFactionName());
			} else if (otherCharacter.getMyFactionManager().isFaction()) {
				FactionController factionC = otherCharacter.getMyFactionManager().getFactionController();
				Faction faction = factionC.getFaction();
				writeByte(1);
				writeInt(faction.getId());
				writeUTF(faction.getViewName());
			} else {
				writeByte(0);
			}
			// 发送玩家称号显示信息==========================
			writeInt(otherCharacter.getNowAppellationid());
			// 死亡状态============================
			writeBoolean(otherCharacter.getNowHp() != 0);
			SendCharacterShowInfo(otherCharacter, this);
			writeShort(otherCharacter.getWuxueJingjie());
			CharacterPropertyResponse10108.writeRankTitle(this, otherCharacter);
			writeInt(otherCharacter.getDanTianController().getQixuan1ResID());
			writeInt(otherCharacter.getDanTianController().getQixuan2ResID());
			writeInt(otherCharacter.getDgwdController().getDZEffectId());
			CharacterGoods wuqigoods = otherCharacter.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_WUQI);
			if (wuqigoods != null) {
				writeByte(wuqigoods.getPin());
				writeByte(wuqigoods.getJie());
			} else {
				writeByte(0);
				writeByte(0);
			}
			// ============================================================
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	public static void SendCharacterShowInfo(Hero otherCharacter, ServerResponse out) throws IOException {
		// 换装数据
		// buff数量
		// out.writeByte(0);
		List<EffectInfo> t = otherCharacter.getEffectController().getBuffList();
		out.writeByte(t.size());
		for (EffectInfo bse : t) {
			out.writeInt(bse.getId());
			out.writeInt((int) (System.currentTimeMillis() - bse.getBufBeginTime()));
			out.writeInt(bse.getDuration());
			out.writeInt(bse.getRemainPoint());
			out.writeInt(bse.getDuration2());
		}
		List<CharacterVip> vipList = otherCharacter.getMyCharacterVipManger().getCharacterVipList();
		out.writeByte(vipList.size());
		for (CharacterVip cv : vipList) {
			out.writeInt(cv.getBufferId());
			out.writeDouble(cv.getEndTime().getTime());
		}
		// 可显示的buff数量(byte){buff id(int)}
		// 主显示装备===================================================
		IBody body = otherCharacter.getCharacterGoodController().getBodyGoodsContiner();
		// 衣服===============================
		CharacterGoods good1 = body.getGoodsByPostion(Position.POSTION_YIFU);
		// 武器
		CharacterGoods good2 = body.getGoodsByPostion(Position.POSTION_WUQI);
		// 骑战兵器
		CharacterGoods good3 = body.getGoodsByPostion(Position.POSTION_QIBING);
		// 弓
		Bow bow = otherCharacter.getBowController().getBow();
		// 游龙之刃
		CharacterGoods youlong = otherCharacter.getMyFactionManager().getYoulongZhiren();
		// 轩辕剑
		CharacterGoods xuanyuanjian = otherCharacter.getMycharacterAcrossZhengzuoManager().getXuanyuanjian();
		int count = (good1 != null ? 1 : 0) + (good2 != null ? 1 : 0) + (good3 != null ? 1 : 0) + (bow != null ? 1 : 0) + (youlong != null ? 1 : 0)
				+ (xuanyuanjian != null ? 1 : 0);
		out.writeByte(count);
		if (good1 != null) {
			out.writeInt(good1.getGoodmodelId());
		}
		if (good2 != null) {
			out.writeInt(good2.getGoodmodelId());
		}
		if (good3 != null) {
			out.writeInt(good3.getGoodmodelId());
		}
		if (bow != null) {
			out.writeInt(bow.getGoodModelId());
		}

		if (youlong != null) {
			out.writeInt(youlong.getGoodmodelId());
		}
		if (xuanyuanjian != null) {
			out.writeInt(xuanyuanjian.getGoodmodelId());
		}

		// ===================================
		CharacterGoods characterGoods = otherCharacter.getCharacterGoodController().getBodyGoodsContiner().getGoodsByPostion(Position.POSTION_TESHU);
		if (characterGoods == null || characterGoods.getIsUse() == 0) {
			out.writeByte(0);
		} else {
			out.writeByte(1);
			out.writeInt(characterGoods.getGoodmodelId());
		}
		Horse horse = otherCharacter.getCharacterHorseController().getShowHorse();
		if (horse == null) {
			out.writeByte(0);
		} else {
			out.writeByte(1);
			out.writeInt(horse.getHorseModel().getId());
			out.writeInt(horse.getId());
		}
		// 普通+真龙
		out.writeByte(otherCharacter.getMyChannelManager().getDatongjinmai() + otherCharacter.getMyChannelManager().getDatongjinmaiZhenLong());
		// 打坐状态(byte 0非打坐状态 1打坐状态 2双修状态){如果为双修状态：对方ID(int)，对方logicPoint}
		MyDazuoAndUnDaduoManager mydazuoManger = otherCharacter.getMyDazuoManager();
		byte state = mydazuoManger.getCharacterState();
		out.writeByte(state);// 打坐状态
		if (state == 2) {
			Hero shuangxiu = mydazuoManger.getShuangXiu();
			out.writeInt(shuangxiu.getId());
			out.writeShort(shuangxiu.getX());
			out.writeShort(shuangxiu.getY());
		}
		Hero shoushanger = mydazuoManger.getShoushanger();
		Hero liaoshanger = mydazuoManger.getLiaoshanger();
		if (shoushanger != null) {
			out.writeByte(1);
			out.writeInt(shoushanger.getId());
			out.writeShort(shoushanger.getX());
			out.writeShort(shoushanger.getY());
		} else if (liaoshanger != null) {
			out.writeByte(2);
			out.writeInt(liaoshanger.getId());
			out.writeShort(liaoshanger.getX());
			out.writeShort(liaoshanger.getY());
		} else {
			out.writeByte(0);
		}

	}
}
