package net.snake.gamemodel.friend.processor;

import java.util.ArrayList;
import java.util.List;

import net.snake.GameServer;
import net.snake.ann.MsgCodeAnn;
import net.snake.consts.CommonUseNumber;
import net.snake.consts.GoodItemId;
import net.snake.gamemodel.common.processor.CharacterMsgProcessor;
import net.snake.gamemodel.common.response.PrompMsg;
import net.snake.gamemodel.common.response.TipMsg;
import net.snake.gamemodel.friend.bean.CharacterFriend;
import net.snake.gamemodel.friend.persistence.CharacterFriendManager;
import net.snake.gamemodel.friend.response.FriendFavorUpdateMsg10316;
import net.snake.gamemodel.friend.response.FriendFlowerMsg10378;
import net.snake.gamemodel.friend.response.FriendFlowerMsg10380;
import net.snake.gamemodel.goods.bean.CharacterGoods;
import net.snake.gamemodel.hero.bean.Hero;
import net.snake.gamemodel.wedding.logic.CouplesController;
import net.snake.netio.message.RequestMsg;
import net.snake.shell.Options;

/**
 * 10377 曾花处理
 * 
 * 
 */
@MsgCodeAnn(msgcode = 10377, accessLimit = 500)
public class FriendSongFlowerProcessor extends CharacterMsgProcessor {
	public static int favor_max = 9999999;
	private int[] meiguihuaid = new int[] { GoodItemId.HongMeiGui, GoodItemId.HuangMeiGui, GoodItemId.LanMeiGui, GoodItemId.LvMeiGui, GoodItemId.BaiMeiGui, GoodItemId.HeiMeiGui };

	@Override
	public void process(Hero character, RequestMsg request) throws Exception {
		if (Options.IsCrossServ) {
			character.sendMsg(new PrompMsg(TipMsg.MSGPOSITION_ERRORTIP, 1306));
			return;
		}
		int characterId = request.getInt();
		Hero his = GameServer.vlineServerManager.getCharacterById(characterId);
		if (his == null) {
			character.sendMsg(new FriendFlowerMsg10378(character, 1135));
			return;
		}
		byte count = request.getByte();
		List<Short> list = new ArrayList<Short>();
		List<CharacterGoods> glist = new ArrayList<CharacterGoods>();
		for (int i = 0; i < count; i++) {
			Short position = request.getShort();
			boolean b = ckeckSamePositon(position, list);
			if (b) {
				character.sendMsg(new FriendFlowerMsg10378(character, 1136));
				return;
			}
			list.add(position);
			CharacterGoods cg = character.getCharacterGoodController().getGoodsByPositon(position);
			if (cg == null) {
				character.sendMsg(new FriendFlowerMsg10378(character, 856));
				return;
			}

			if (cg.getBind() == 1) {
				character.sendMsg(new FriendFlowerMsg10378(character, 1137));
				return;
			}
			if (cg.isInTrade()) {
				character.sendMsg(new FriendFlowerMsg10378(character, 1138));
				return;
			}
			if (!isMeigui(meiguihuaid, cg.getGoodmodelId())) {
				character.sendMsg(new FriendFlowerMsg10378(character, 1139));
				return;
			}
			glist.add(cg);
		}
		if (glist.size() == 0) {
			character.sendMsg(new FriendFlowerMsg10378(character, 1140));
			return;
		}
		boolean b = his.getCharacterGoodController().getBagGoodsContiner().isHasSpaceForAddGoods(glist);
		if (!b) {
			character.sendMsg(new FriendFlowerMsg10378(character, 1141));
			return;
		}
		int fcount = 0;
		for (CharacterGoods cgs : glist) {
			fcount = fcount + cgs.getCount();
			character.getCharacterGoodController().deleteCharacterGoods(cgs);
			cgs.setBind(CommonUseNumber.byte1);
			his.getCharacterGoodController().addGoodsToBag(cgs);
		}
		if (character.getMyFriendManager().getRoleWedingManager().isWeddingWith(his.getId())) {
			CouplesController cc = character.getMyFriendManager().getRoleWedingManager().getFuqi();
			int countflower = cc.updateFuqiFavor(fcount * 3);
			character.sendMsg(new FriendFlowerMsg10378(his, (short) fcount, countflower));
			his.sendMsg(new FriendFlowerMsg10380(character, GoodItemId.HongMeiGui, (short) fcount, countflower));
			return;
		}
		CharacterFriend cf = character.getMyFriendManager().getRoleFriendManager().getCharacterFriend(characterId);
		CharacterFriend hiscf = his.getMyFriendManager().getRoleFriendManager().getCharacterFriend(character.getId());
		int countflower = addFavor(character, cf, fcount);
		addFavor(his, hiscf, fcount);
		CharacterFriendManager.getInstance().asynUpdateCharacterFriend(his, hiscf);
		CharacterFriendManager.getInstance().asynUpdateCharacterFriend(character, cf);
		character.sendMsg(new FriendFlowerMsg10378(his, (short) fcount, countflower));
		his.sendMsg(new FriendFlowerMsg10380(character, GoodItemId.HongMeiGui, (short) fcount, countflower));
	}

	/**
	 * 返回true 是玫瑰花
	 * 
	 * @param meiguihuaid
	 * @param goodId
	 * @return
	 */
	private boolean isMeigui(int[] meiguihuaid, int goodId) {
		for (int id : meiguihuaid) {
			if (goodId == id) {
				return true;
			}
		}
		return false;
	}

	private int addFavor(Hero character, CharacterFriend cf, int flower) {
		if (cf == null) {
			return 0;
		}
		int addFavor = flower * 3;
		if (cf != null && cf.getFavor() + addFavor > favor_max) {
			addFavor = favor_max - cf.getFavor();
		}
		cf.setFavor(addFavor + cf.getFavor());
		if (addFavor > 0) {
			character.sendMsg(new FriendFavorUpdateMsg10316(cf));
		}
		return addFavor;
	}

	/**
	 * 检查送花位置是否有重复
	 * 
	 * @param position
	 * @param list
	 * @return
	 */
	public boolean ckeckSamePositon(short position, List<Short> list) {
		for (Short p : list) {
			if (p == position) {
				return true;
			}
		}
		return false;
	}

}
